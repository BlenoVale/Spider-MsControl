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
import model.Procedimentodeanalise;
import model.Registroprocedimentoanalise;

/**
 *
 * @author Dan
 */
public class RegistroprocedimentoanaliseJpaController implements Serializable {

    public RegistroprocedimentoanaliseJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Registroprocedimentoanalise registroprocedimentoanalise) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Procedimentodeanalise procedimentoDeAnaliseid = registroprocedimentoanalise.getProcedimentoDeAnaliseid();
            if (procedimentoDeAnaliseid != null) {
                procedimentoDeAnaliseid = em.getReference(procedimentoDeAnaliseid.getClass(), procedimentoDeAnaliseid.getId());
                registroprocedimentoanalise.setProcedimentoDeAnaliseid(procedimentoDeAnaliseid);
            }
            em.persist(registroprocedimentoanalise);
            if (procedimentoDeAnaliseid != null) {
                procedimentoDeAnaliseid.getRegistroprocedimentoanaliseList().add(registroprocedimentoanalise);
                procedimentoDeAnaliseid = em.merge(procedimentoDeAnaliseid);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Registroprocedimentoanalise registroprocedimentoanalise) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Registroprocedimentoanalise persistentRegistroprocedimentoanalise = em.find(Registroprocedimentoanalise.class, registroprocedimentoanalise.getId());
            Procedimentodeanalise procedimentoDeAnaliseidOld = persistentRegistroprocedimentoanalise.getProcedimentoDeAnaliseid();
            Procedimentodeanalise procedimentoDeAnaliseidNew = registroprocedimentoanalise.getProcedimentoDeAnaliseid();
            if (procedimentoDeAnaliseidNew != null) {
                procedimentoDeAnaliseidNew = em.getReference(procedimentoDeAnaliseidNew.getClass(), procedimentoDeAnaliseidNew.getId());
                registroprocedimentoanalise.setProcedimentoDeAnaliseid(procedimentoDeAnaliseidNew);
            }
            registroprocedimentoanalise = em.merge(registroprocedimentoanalise);
            if (procedimentoDeAnaliseidOld != null && !procedimentoDeAnaliseidOld.equals(procedimentoDeAnaliseidNew)) {
                procedimentoDeAnaliseidOld.getRegistroprocedimentoanaliseList().remove(registroprocedimentoanalise);
                procedimentoDeAnaliseidOld = em.merge(procedimentoDeAnaliseidOld);
            }
            if (procedimentoDeAnaliseidNew != null && !procedimentoDeAnaliseidNew.equals(procedimentoDeAnaliseidOld)) {
                procedimentoDeAnaliseidNew.getRegistroprocedimentoanaliseList().add(registroprocedimentoanalise);
                procedimentoDeAnaliseidNew = em.merge(procedimentoDeAnaliseidNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = registroprocedimentoanalise.getId();
                if (findRegistroprocedimentoanalise(id) == null) {
                    throw new NonexistentEntityException("The registroprocedimentoanalise with id " + id + " no longer exists.");
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
            Registroprocedimentoanalise registroprocedimentoanalise;
            try {
                registroprocedimentoanalise = em.getReference(Registroprocedimentoanalise.class, id);
                registroprocedimentoanalise.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The registroprocedimentoanalise with id " + id + " no longer exists.", enfe);
            }
            Procedimentodeanalise procedimentoDeAnaliseid = registroprocedimentoanalise.getProcedimentoDeAnaliseid();
            if (procedimentoDeAnaliseid != null) {
                procedimentoDeAnaliseid.getRegistroprocedimentoanaliseList().remove(registroprocedimentoanalise);
                procedimentoDeAnaliseid = em.merge(procedimentoDeAnaliseid);
            }
            em.remove(registroprocedimentoanalise);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Registroprocedimentoanalise> findRegistroprocedimentoanaliseEntities() {
        return findRegistroprocedimentoanaliseEntities(true, -1, -1);
    }

    public List<Registroprocedimentoanalise> findRegistroprocedimentoanaliseEntities(int maxResults, int firstResult) {
        return findRegistroprocedimentoanaliseEntities(false, maxResults, firstResult);
    }

    private List<Registroprocedimentoanalise> findRegistroprocedimentoanaliseEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Registroprocedimentoanalise.class));
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

    public Registroprocedimentoanalise findRegistroprocedimentoanalise(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Registroprocedimentoanalise.class, id);
        } finally {
            em.close();
        }
    }

    public int getRegistroprocedimentoanaliseCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Registroprocedimentoanalise> rt = cq.from(Registroprocedimentoanalise.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
