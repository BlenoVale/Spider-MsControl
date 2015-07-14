/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.Perfil;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;
import model.Funcionalidade;

/**
 *
 * @author paulosouza
 */
public class FuncionalidadeJpaController implements Serializable {

    public FuncionalidadeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Funcionalidade funcionalidade) {
        if (funcionalidade.getPerfilList() == null) {
            funcionalidade.setPerfilList(new ArrayList<Perfil>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Perfil> attachedPerfilList = new ArrayList<Perfil>();
            for (Perfil perfilListPerfilToAttach : funcionalidade.getPerfilList()) {
                perfilListPerfilToAttach = em.getReference(perfilListPerfilToAttach.getClass(), perfilListPerfilToAttach.getId());
                attachedPerfilList.add(perfilListPerfilToAttach);
            }
            funcionalidade.setPerfilList(attachedPerfilList);
            em.persist(funcionalidade);
            for (Perfil perfilListPerfil : funcionalidade.getPerfilList()) {
                perfilListPerfil.getFuncionalidadeList().add(funcionalidade);
                perfilListPerfil = em.merge(perfilListPerfil);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Funcionalidade funcionalidade) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Funcionalidade persistentFuncionalidade = em.find(Funcionalidade.class, funcionalidade.getId());
            List<Perfil> perfilListOld = persistentFuncionalidade.getPerfilList();
            List<Perfil> perfilListNew = funcionalidade.getPerfilList();
            List<Perfil> attachedPerfilListNew = new ArrayList<Perfil>();
            for (Perfil perfilListNewPerfilToAttach : perfilListNew) {
                perfilListNewPerfilToAttach = em.getReference(perfilListNewPerfilToAttach.getClass(), perfilListNewPerfilToAttach.getId());
                attachedPerfilListNew.add(perfilListNewPerfilToAttach);
            }
            perfilListNew = attachedPerfilListNew;
            funcionalidade.setPerfilList(perfilListNew);
            funcionalidade = em.merge(funcionalidade);
            for (Perfil perfilListOldPerfil : perfilListOld) {
                if (!perfilListNew.contains(perfilListOldPerfil)) {
                    perfilListOldPerfil.getFuncionalidadeList().remove(funcionalidade);
                    perfilListOldPerfil = em.merge(perfilListOldPerfil);
                }
            }
            for (Perfil perfilListNewPerfil : perfilListNew) {
                if (!perfilListOld.contains(perfilListNewPerfil)) {
                    perfilListNewPerfil.getFuncionalidadeList().add(funcionalidade);
                    perfilListNewPerfil = em.merge(perfilListNewPerfil);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = funcionalidade.getId();
                if (findFuncionalidade(id) == null) {
                    throw new NonexistentEntityException("The funcionalidade with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Funcionalidade funcionalidade;
            try {
                funcionalidade = em.getReference(Funcionalidade.class, id);
                funcionalidade.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The funcionalidade with id " + id + " no longer exists.", enfe);
            }
            List<Perfil> perfilList = funcionalidade.getPerfilList();
            for (Perfil perfilListPerfil : perfilList) {
                perfilListPerfil.getFuncionalidadeList().remove(funcionalidade);
                perfilListPerfil = em.merge(perfilListPerfil);
            }
            em.remove(funcionalidade);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Funcionalidade> findFuncionalidadeEntities() {
        return findFuncionalidadeEntities(true, -1, -1);
    }

    public List<Funcionalidade> findFuncionalidadeEntities(int maxResults, int firstResult) {
        return findFuncionalidadeEntities(false, maxResults, firstResult);
    }

    private List<Funcionalidade> findFuncionalidadeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Funcionalidade.class));
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

    public Funcionalidade findFuncionalidade(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Funcionalidade.class, id);
        } finally {
            em.close();
        }
    }

    public int getFuncionalidadeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Funcionalidade> rt = cq.from(Funcionalidade.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
