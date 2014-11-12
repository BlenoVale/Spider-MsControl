/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.Funcionalidade;
import model.Perfil;
import model.Possui;
import model.PossuiPK;

/**
 *
 * @author Spider
 */
public class PossuiJpaController implements Serializable {

    public PossuiJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Possui possui) throws PreexistingEntityException, Exception {
        if (possui.getPossuiPK() == null) {
            possui.setPossuiPK(new PossuiPK());
        }
        possui.getPossuiPK().setPerfilid(possui.getPerfil().getId());
        possui.getPossuiPK().setFuncionalidadeid(possui.getFuncionalidade().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Funcionalidade funcionalidade = possui.getFuncionalidade();
            if (funcionalidade != null) {
                funcionalidade = em.getReference(funcionalidade.getClass(), funcionalidade.getId());
                possui.setFuncionalidade(funcionalidade);
            }
            Perfil perfil = possui.getPerfil();
            if (perfil != null) {
                perfil = em.getReference(perfil.getClass(), perfil.getId());
                possui.setPerfil(perfil);
            }
            em.persist(possui);
            if (funcionalidade != null) {
                funcionalidade.getPossuiList().add(possui);
                funcionalidade = em.merge(funcionalidade);
            }
            if (perfil != null) {
                perfil.getPossuiList().add(possui);
                perfil = em.merge(perfil);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPossui(possui.getPossuiPK()) != null) {
                throw new PreexistingEntityException("Possui " + possui + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Possui possui) throws NonexistentEntityException, Exception {
        possui.getPossuiPK().setPerfilid(possui.getPerfil().getId());
        possui.getPossuiPK().setFuncionalidadeid(possui.getFuncionalidade().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Possui persistentPossui = em.find(Possui.class, possui.getPossuiPK());
            Funcionalidade funcionalidadeOld = persistentPossui.getFuncionalidade();
            Funcionalidade funcionalidadeNew = possui.getFuncionalidade();
            Perfil perfilOld = persistentPossui.getPerfil();
            Perfil perfilNew = possui.getPerfil();
            if (funcionalidadeNew != null) {
                funcionalidadeNew = em.getReference(funcionalidadeNew.getClass(), funcionalidadeNew.getId());
                possui.setFuncionalidade(funcionalidadeNew);
            }
            if (perfilNew != null) {
                perfilNew = em.getReference(perfilNew.getClass(), perfilNew.getId());
                possui.setPerfil(perfilNew);
            }
            possui = em.merge(possui);
            if (funcionalidadeOld != null && !funcionalidadeOld.equals(funcionalidadeNew)) {
                funcionalidadeOld.getPossuiList().remove(possui);
                funcionalidadeOld = em.merge(funcionalidadeOld);
            }
            if (funcionalidadeNew != null && !funcionalidadeNew.equals(funcionalidadeOld)) {
                funcionalidadeNew.getPossuiList().add(possui);
                funcionalidadeNew = em.merge(funcionalidadeNew);
            }
            if (perfilOld != null && !perfilOld.equals(perfilNew)) {
                perfilOld.getPossuiList().remove(possui);
                perfilOld = em.merge(perfilOld);
            }
            if (perfilNew != null && !perfilNew.equals(perfilOld)) {
                perfilNew.getPossuiList().add(possui);
                perfilNew = em.merge(perfilNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                PossuiPK id = possui.getPossuiPK();
                if (findPossui(id) == null) {
                    throw new NonexistentEntityException("The possui with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(PossuiPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Possui possui;
            try {
                possui = em.getReference(Possui.class, id);
                possui.getPossuiPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The possui with id " + id + " no longer exists.", enfe);
            }
            Funcionalidade funcionalidade = possui.getFuncionalidade();
            if (funcionalidade != null) {
                funcionalidade.getPossuiList().remove(possui);
                funcionalidade = em.merge(funcionalidade);
            }
            Perfil perfil = possui.getPerfil();
            if (perfil != null) {
                perfil.getPossuiList().remove(possui);
                perfil = em.merge(perfil);
            }
            em.remove(possui);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Possui> findPossuiEntities() {
        return findPossuiEntities(true, -1, -1);
    }

    public List<Possui> findPossuiEntities(int maxResults, int firstResult) {
        return findPossuiEntities(false, maxResults, firstResult);
    }

    private List<Possui> findPossuiEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Possui.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Possui findPossui(PossuiPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Possui.class, id);
        } finally {
            em.close();
        }
    }

    public int getPossuiCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Possui> rt = cq.from(Possui.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
