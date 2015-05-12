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
import model.Datasprocedimentoanalise;
import model.Procedimentodeanalise;

/**
 *
 * @author Spider
 */
public class DatasprocedimentoanaliseJpaController implements Serializable {

    public DatasprocedimentoanaliseJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Datasprocedimentoanalise datasprocedimentoanalise) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Procedimentodeanalise procedimentoDeAnaliseid = datasprocedimentoanalise.getProcedimentoDeAnaliseid();
            if (procedimentoDeAnaliseid != null) {
                procedimentoDeAnaliseid = em.getReference(procedimentoDeAnaliseid.getClass(), procedimentoDeAnaliseid.getId());
                datasprocedimentoanalise.setProcedimentoDeAnaliseid(procedimentoDeAnaliseid);
            }
            em.persist(datasprocedimentoanalise);
            if (procedimentoDeAnaliseid != null) {
                procedimentoDeAnaliseid.getDatasprocedimentoanaliseList().add(datasprocedimentoanalise);
                procedimentoDeAnaliseid = em.merge(procedimentoDeAnaliseid);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Datasprocedimentoanalise datasprocedimentoanalise) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Datasprocedimentoanalise persistentDatasprocedimentoanalise = em.find(Datasprocedimentoanalise.class, datasprocedimentoanalise.getId());
            Procedimentodeanalise procedimentoDeAnaliseidOld = persistentDatasprocedimentoanalise.getProcedimentoDeAnaliseid();
            Procedimentodeanalise procedimentoDeAnaliseidNew = datasprocedimentoanalise.getProcedimentoDeAnaliseid();
            if (procedimentoDeAnaliseidNew != null) {
                procedimentoDeAnaliseidNew = em.getReference(procedimentoDeAnaliseidNew.getClass(), procedimentoDeAnaliseidNew.getId());
                datasprocedimentoanalise.setProcedimentoDeAnaliseid(procedimentoDeAnaliseidNew);
            }
            datasprocedimentoanalise = em.merge(datasprocedimentoanalise);
            if (procedimentoDeAnaliseidOld != null && !procedimentoDeAnaliseidOld.equals(procedimentoDeAnaliseidNew)) {
                procedimentoDeAnaliseidOld.getDatasprocedimentoanaliseList().remove(datasprocedimentoanalise);
                procedimentoDeAnaliseidOld = em.merge(procedimentoDeAnaliseidOld);
            }
            if (procedimentoDeAnaliseidNew != null && !procedimentoDeAnaliseidNew.equals(procedimentoDeAnaliseidOld)) {
                procedimentoDeAnaliseidNew.getDatasprocedimentoanaliseList().add(datasprocedimentoanalise);
                procedimentoDeAnaliseidNew = em.merge(procedimentoDeAnaliseidNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = datasprocedimentoanalise.getId();
                if (findDatasprocedimentoanalise(id) == null) {
                    throw new NonexistentEntityException("The datasprocedimentoanalise with id " + id + " no longer exists.");
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
            Datasprocedimentoanalise datasprocedimentoanalise;
            try {
                datasprocedimentoanalise = em.getReference(Datasprocedimentoanalise.class, id);
                datasprocedimentoanalise.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The datasprocedimentoanalise with id " + id + " no longer exists.", enfe);
            }
            Procedimentodeanalise procedimentoDeAnaliseid = datasprocedimentoanalise.getProcedimentoDeAnaliseid();
            if (procedimentoDeAnaliseid != null) {
                procedimentoDeAnaliseid.getDatasprocedimentoanaliseList().remove(datasprocedimentoanalise);
                procedimentoDeAnaliseid = em.merge(procedimentoDeAnaliseid);
            }
            em.remove(datasprocedimentoanalise);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Datasprocedimentoanalise> findDatasprocedimentoanaliseEntities() {
        return findDatasprocedimentoanaliseEntities(true, -1, -1);
    }

    public List<Datasprocedimentoanalise> findDatasprocedimentoanaliseEntities(int maxResults, int firstResult) {
        return findDatasprocedimentoanaliseEntities(false, maxResults, firstResult);
    }

    private List<Datasprocedimentoanalise> findDatasprocedimentoanaliseEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Datasprocedimentoanalise.class));
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

    public Datasprocedimentoanalise findDatasprocedimentoanalise(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Datasprocedimentoanalise.class, id);
        } finally {
            em.close();
        }
    }

    public int getDatasprocedimentoanaliseCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Datasprocedimentoanalise> rt = cq.from(Datasprocedimentoanalise.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
