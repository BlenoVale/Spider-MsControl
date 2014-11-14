/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.Coleta;
import model.Procedimentodeanalise;

/**
 *
 * @author Dan
 */
public class ProcedimentodeanaliseJpaController implements Serializable {

    public ProcedimentodeanaliseJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Procedimentodeanalise procedimentodeanalise) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Coleta coleta = procedimentodeanalise.getColeta();
            if (coleta != null) {
                coleta = em.getReference(coleta.getClass(), coleta.getColetaPK());
                procedimentodeanalise.setColeta(coleta);
            }
            em.persist(procedimentodeanalise);
            if (coleta != null) {
                coleta.getProcedimentodeanaliseList().add(procedimentodeanalise);
                coleta = em.merge(coleta);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Procedimentodeanalise procedimentodeanalise) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Procedimentodeanalise persistentProcedimentodeanalise = em.find(Procedimentodeanalise.class, procedimentodeanalise.getId());
            Coleta coletaOld = persistentProcedimentodeanalise.getColeta();
            Coleta coletaNew = procedimentodeanalise.getColeta();
            if (coletaNew != null) {
                coletaNew = em.getReference(coletaNew.getClass(), coletaNew.getColetaPK());
                procedimentodeanalise.setColeta(coletaNew);
            }
            procedimentodeanalise = em.merge(procedimentodeanalise);
            if (coletaOld != null && !coletaOld.equals(coletaNew)) {
                coletaOld.getProcedimentodeanaliseList().remove(procedimentodeanalise);
                coletaOld = em.merge(coletaOld);
            }
            if (coletaNew != null && !coletaNew.equals(coletaOld)) {
                coletaNew.getProcedimentodeanaliseList().add(procedimentodeanalise);
                coletaNew = em.merge(coletaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = procedimentodeanalise.getId();
                if (findProcedimentodeanalise(id) == null) {
                    throw new NonexistentEntityException("The procedimentodeanalise with id " + id + " no longer exists.");
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
            Procedimentodeanalise procedimentodeanalise;
            try {
                procedimentodeanalise = em.getReference(Procedimentodeanalise.class, id);
                procedimentodeanalise.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The procedimentodeanalise with id " + id + " no longer exists.", enfe);
            }
            Coleta coleta = procedimentodeanalise.getColeta();
            if (coleta != null) {
                coleta.getProcedimentodeanaliseList().remove(procedimentodeanalise);
                coleta = em.merge(coleta);
            }
            em.remove(procedimentodeanalise);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Procedimentodeanalise> findProcedimentodeanaliseEntities() {
        return findProcedimentodeanaliseEntities(true, -1, -1);
    }

    public List<Procedimentodeanalise> findProcedimentodeanaliseEntities(int maxResults, int firstResult) {
        return findProcedimentodeanaliseEntities(false, maxResults, firstResult);
    }

    private List<Procedimentodeanalise> findProcedimentodeanaliseEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Procedimentodeanalise.class));
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

    public Procedimentodeanalise findProcedimentodeanalise(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Procedimentodeanalise.class, id);
        } finally {
            em.close();
        }
    }

    public int getProcedimentodeanaliseCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Procedimentodeanalise> rt = cq.from(Procedimentodeanalise.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
