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
import model.Meiosprocedimentoanalise;
import model.Procedimentodeanalise;

/**
 *
 * @author paulosouza
 */
public class MeiosprocedimentoanaliseJpaController implements Serializable {

    public MeiosprocedimentoanaliseJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Meiosprocedimentoanalise meiosprocedimentoanalise) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Procedimentodeanalise procedimentoDeAnaliseid = meiosprocedimentoanalise.getProcedimentoDeAnaliseid();
            if (procedimentoDeAnaliseid != null) {
                procedimentoDeAnaliseid = em.getReference(procedimentoDeAnaliseid.getClass(), procedimentoDeAnaliseid.getId());
                meiosprocedimentoanalise.setProcedimentoDeAnaliseid(procedimentoDeAnaliseid);
            }
            em.persist(meiosprocedimentoanalise);
            if (procedimentoDeAnaliseid != null) {
                procedimentoDeAnaliseid.getMeiosprocedimentoanaliseList().add(meiosprocedimentoanalise);
                procedimentoDeAnaliseid = em.merge(procedimentoDeAnaliseid);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Meiosprocedimentoanalise meiosprocedimentoanalise) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Meiosprocedimentoanalise persistentMeiosprocedimentoanalise = em.find(Meiosprocedimentoanalise.class, meiosprocedimentoanalise.getId());
            Procedimentodeanalise procedimentoDeAnaliseidOld = persistentMeiosprocedimentoanalise.getProcedimentoDeAnaliseid();
            Procedimentodeanalise procedimentoDeAnaliseidNew = meiosprocedimentoanalise.getProcedimentoDeAnaliseid();
            if (procedimentoDeAnaliseidNew != null) {
                procedimentoDeAnaliseidNew = em.getReference(procedimentoDeAnaliseidNew.getClass(), procedimentoDeAnaliseidNew.getId());
                meiosprocedimentoanalise.setProcedimentoDeAnaliseid(procedimentoDeAnaliseidNew);
            }
            meiosprocedimentoanalise = em.merge(meiosprocedimentoanalise);
            if (procedimentoDeAnaliseidOld != null && !procedimentoDeAnaliseidOld.equals(procedimentoDeAnaliseidNew)) {
                procedimentoDeAnaliseidOld.getMeiosprocedimentoanaliseList().remove(meiosprocedimentoanalise);
                procedimentoDeAnaliseidOld = em.merge(procedimentoDeAnaliseidOld);
            }
            if (procedimentoDeAnaliseidNew != null && !procedimentoDeAnaliseidNew.equals(procedimentoDeAnaliseidOld)) {
                procedimentoDeAnaliseidNew.getMeiosprocedimentoanaliseList().add(meiosprocedimentoanalise);
                procedimentoDeAnaliseidNew = em.merge(procedimentoDeAnaliseidNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = meiosprocedimentoanalise.getId();
                if (findMeiosprocedimentoanalise(id) == null) {
                    throw new NonexistentEntityException("The meiosprocedimentoanalise with id " + id + " no longer exists.");
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
            Meiosprocedimentoanalise meiosprocedimentoanalise;
            try {
                meiosprocedimentoanalise = em.getReference(Meiosprocedimentoanalise.class, id);
                meiosprocedimentoanalise.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The meiosprocedimentoanalise with id " + id + " no longer exists.", enfe);
            }
            Procedimentodeanalise procedimentoDeAnaliseid = meiosprocedimentoanalise.getProcedimentoDeAnaliseid();
            if (procedimentoDeAnaliseid != null) {
                procedimentoDeAnaliseid.getMeiosprocedimentoanaliseList().remove(meiosprocedimentoanalise);
                procedimentoDeAnaliseid = em.merge(procedimentoDeAnaliseid);
            }
            em.remove(meiosprocedimentoanalise);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Meiosprocedimentoanalise> findMeiosprocedimentoanaliseEntities() {
        return findMeiosprocedimentoanaliseEntities(true, -1, -1);
    }

    public List<Meiosprocedimentoanalise> findMeiosprocedimentoanaliseEntities(int maxResults, int firstResult) {
        return findMeiosprocedimentoanaliseEntities(false, maxResults, firstResult);
    }

    private List<Meiosprocedimentoanalise> findMeiosprocedimentoanaliseEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Meiosprocedimentoanalise.class));
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

    public Meiosprocedimentoanalise findMeiosprocedimentoanalise(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Meiosprocedimentoanalise.class, id);
        } finally {
            em.close();
        }
    }

    public int getMeiosprocedimentoanaliseCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Meiosprocedimentoanalise> rt = cq.from(Meiosprocedimentoanalise.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
