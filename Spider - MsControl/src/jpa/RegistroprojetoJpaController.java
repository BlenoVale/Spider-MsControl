/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import jpa.exceptions.NonexistentEntityException;
import jpa.exceptions.PreexistingEntityException;
import model.Projeto;
import model.Registroprojeto;
import model.RegistroprojetoPK;

/**
 *
 * @author Dan
 */
public class RegistroprojetoJpaController implements Serializable {

    public RegistroprojetoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Registroprojeto registroprojeto) throws PreexistingEntityException, Exception {
        if (registroprojeto.getRegistroprojetoPK() == null) {
            registroprojeto.setRegistroprojetoPK(new RegistroprojetoPK());
        }
        registroprojeto.getRegistroprojetoPK().setProjetoid(registroprojeto.getProjeto().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Projeto projeto = registroprojeto.getProjeto();
            if (projeto != null) {
                projeto = em.getReference(projeto.getClass(), projeto.getId());
                registroprojeto.setProjeto(projeto);
            }
            em.persist(registroprojeto);
            if (projeto != null) {
                projeto.getRegistroprojetoList().add(registroprojeto);
                projeto = em.merge(projeto);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRegistroprojeto(registroprojeto.getRegistroprojetoPK()) != null) {
                throw new PreexistingEntityException("Registroprojeto " + registroprojeto + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Registroprojeto registroprojeto) throws NonexistentEntityException, Exception {
        registroprojeto.getRegistroprojetoPK().setProjetoid(registroprojeto.getProjeto().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Registroprojeto persistentRegistroprojeto = em.find(Registroprojeto.class, registroprojeto.getRegistroprojetoPK());
            Projeto projetoOld = persistentRegistroprojeto.getProjeto();
            Projeto projetoNew = registroprojeto.getProjeto();
            if (projetoNew != null) {
                projetoNew = em.getReference(projetoNew.getClass(), projetoNew.getId());
                registroprojeto.setProjeto(projetoNew);
            }
            registroprojeto = em.merge(registroprojeto);
            if (projetoOld != null && !projetoOld.equals(projetoNew)) {
                projetoOld.getRegistroprojetoList().remove(registroprojeto);
                projetoOld = em.merge(projetoOld);
            }
            if (projetoNew != null && !projetoNew.equals(projetoOld)) {
                projetoNew.getRegistroprojetoList().add(registroprojeto);
                projetoNew = em.merge(projetoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                RegistroprojetoPK id = registroprojeto.getRegistroprojetoPK();
                if (findRegistroprojeto(id) == null) {
                    throw new NonexistentEntityException("The registroprojeto with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(RegistroprojetoPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Registroprojeto registroprojeto;
            try {
                registroprojeto = em.getReference(Registroprojeto.class, id);
                registroprojeto.getRegistroprojetoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The registroprojeto with id " + id + " no longer exists.", enfe);
            }
            Projeto projeto = registroprojeto.getProjeto();
            if (projeto != null) {
                projeto.getRegistroprojetoList().remove(registroprojeto);
                projeto = em.merge(projeto);
            }
            em.remove(registroprojeto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Registroprojeto> findRegistroprojetoEntities() {
        return findRegistroprojetoEntities(true, -1, -1);
    }

    public List<Registroprojeto> findRegistroprojetoEntities(int maxResults, int firstResult) {
        return findRegistroprojetoEntities(false, maxResults, firstResult);
    }

    private List<Registroprojeto> findRegistroprojetoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Registroprojeto.class));
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

    public Registroprojeto findRegistroprojeto(RegistroprojetoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Registroprojeto.class, id);
        } finally {
            em.close();
        }
    }

    public int getRegistroprojetoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Registroprojeto> rt = cq.from(Registroprojeto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
