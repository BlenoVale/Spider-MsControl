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
import model.Perfisinteressadosprocedimentoanalise;
import model.Procedimentodeanalise;

/**
 *
 * @author Bleno Vale
 */
public class PerfisinteressadosprocedimentoanaliseJpaController implements Serializable {

    public PerfisinteressadosprocedimentoanaliseJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Perfisinteressadosprocedimentoanalise perfisinteressadosprocedimentoanalise) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Procedimentodeanalise procedimentoDeAnaliseid = perfisinteressadosprocedimentoanalise.getProcedimentoDeAnaliseid();
            if (procedimentoDeAnaliseid != null) {
                procedimentoDeAnaliseid = em.getReference(procedimentoDeAnaliseid.getClass(), procedimentoDeAnaliseid.getId());
                perfisinteressadosprocedimentoanalise.setProcedimentoDeAnaliseid(procedimentoDeAnaliseid);
            }
            em.persist(perfisinteressadosprocedimentoanalise);
            if (procedimentoDeAnaliseid != null) {
                procedimentoDeAnaliseid.getPerfisinteressadosprocedimentoanaliseList().add(perfisinteressadosprocedimentoanalise);
                procedimentoDeAnaliseid = em.merge(procedimentoDeAnaliseid);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Perfisinteressadosprocedimentoanalise perfisinteressadosprocedimentoanalise) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Perfisinteressadosprocedimentoanalise persistentPerfisinteressadosprocedimentoanalise = em.find(Perfisinteressadosprocedimentoanalise.class, perfisinteressadosprocedimentoanalise.getId());
            Procedimentodeanalise procedimentoDeAnaliseidOld = persistentPerfisinteressadosprocedimentoanalise.getProcedimentoDeAnaliseid();
            Procedimentodeanalise procedimentoDeAnaliseidNew = perfisinteressadosprocedimentoanalise.getProcedimentoDeAnaliseid();
            if (procedimentoDeAnaliseidNew != null) {
                procedimentoDeAnaliseidNew = em.getReference(procedimentoDeAnaliseidNew.getClass(), procedimentoDeAnaliseidNew.getId());
                perfisinteressadosprocedimentoanalise.setProcedimentoDeAnaliseid(procedimentoDeAnaliseidNew);
            }
            perfisinteressadosprocedimentoanalise = em.merge(perfisinteressadosprocedimentoanalise);
            if (procedimentoDeAnaliseidOld != null && !procedimentoDeAnaliseidOld.equals(procedimentoDeAnaliseidNew)) {
                procedimentoDeAnaliseidOld.getPerfisinteressadosprocedimentoanaliseList().remove(perfisinteressadosprocedimentoanalise);
                procedimentoDeAnaliseidOld = em.merge(procedimentoDeAnaliseidOld);
            }
            if (procedimentoDeAnaliseidNew != null && !procedimentoDeAnaliseidNew.equals(procedimentoDeAnaliseidOld)) {
                procedimentoDeAnaliseidNew.getPerfisinteressadosprocedimentoanaliseList().add(perfisinteressadosprocedimentoanalise);
                procedimentoDeAnaliseidNew = em.merge(procedimentoDeAnaliseidNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = perfisinteressadosprocedimentoanalise.getId();
                if (findPerfisinteressadosprocedimentoanalise(id) == null) {
                    throw new NonexistentEntityException("The perfisinteressadosprocedimentoanalise with id " + id + " no longer exists.");
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
            Perfisinteressadosprocedimentoanalise perfisinteressadosprocedimentoanalise;
            try {
                perfisinteressadosprocedimentoanalise = em.getReference(Perfisinteressadosprocedimentoanalise.class, id);
                perfisinteressadosprocedimentoanalise.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The perfisinteressadosprocedimentoanalise with id " + id + " no longer exists.", enfe);
            }
            Procedimentodeanalise procedimentoDeAnaliseid = perfisinteressadosprocedimentoanalise.getProcedimentoDeAnaliseid();
            if (procedimentoDeAnaliseid != null) {
                procedimentoDeAnaliseid.getPerfisinteressadosprocedimentoanaliseList().remove(perfisinteressadosprocedimentoanalise);
                procedimentoDeAnaliseid = em.merge(procedimentoDeAnaliseid);
            }
            em.remove(perfisinteressadosprocedimentoanalise);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Perfisinteressadosprocedimentoanalise> findPerfisinteressadosprocedimentoanaliseEntities() {
        return findPerfisinteressadosprocedimentoanaliseEntities(true, -1, -1);
    }

    public List<Perfisinteressadosprocedimentoanalise> findPerfisinteressadosprocedimentoanaliseEntities(int maxResults, int firstResult) {
        return findPerfisinteressadosprocedimentoanaliseEntities(false, maxResults, firstResult);
    }

    private List<Perfisinteressadosprocedimentoanalise> findPerfisinteressadosprocedimentoanaliseEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Perfisinteressadosprocedimentoanalise.class));
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

    public Perfisinteressadosprocedimentoanalise findPerfisinteressadosprocedimentoanalise(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Perfisinteressadosprocedimentoanalise.class, id);
        } finally {
            em.close();
        }
    }

    public int getPerfisinteressadosprocedimentoanaliseCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Perfisinteressadosprocedimentoanalise> rt = cq.from(Perfisinteressadosprocedimentoanalise.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
