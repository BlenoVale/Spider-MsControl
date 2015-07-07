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
import model.Procedimentodecoleta;
import model.Registroprocedimentocoleta;

/**
 *
 * @author BlenoVale
 */
public class RegistroprocedimentocoletaJpaController implements Serializable {

    public RegistroprocedimentocoletaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Registroprocedimentocoleta registroprocedimentocoleta) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Procedimentodecoleta procedimentoDeColetaid = registroprocedimentocoleta.getProcedimentoDeColetaid();
            if (procedimentoDeColetaid != null) {
                procedimentoDeColetaid = em.getReference(procedimentoDeColetaid.getClass(), procedimentoDeColetaid.getId());
                registroprocedimentocoleta.setProcedimentoDeColetaid(procedimentoDeColetaid);
            }
            em.persist(registroprocedimentocoleta);
            if (procedimentoDeColetaid != null) {
                procedimentoDeColetaid.getRegistroprocedimentocoletaList().add(registroprocedimentocoleta);
                procedimentoDeColetaid = em.merge(procedimentoDeColetaid);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Registroprocedimentocoleta registroprocedimentocoleta) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Registroprocedimentocoleta persistentRegistroprocedimentocoleta = em.find(Registroprocedimentocoleta.class, registroprocedimentocoleta.getId());
            Procedimentodecoleta procedimentoDeColetaidOld = persistentRegistroprocedimentocoleta.getProcedimentoDeColetaid();
            Procedimentodecoleta procedimentoDeColetaidNew = registroprocedimentocoleta.getProcedimentoDeColetaid();
            if (procedimentoDeColetaidNew != null) {
                procedimentoDeColetaidNew = em.getReference(procedimentoDeColetaidNew.getClass(), procedimentoDeColetaidNew.getId());
                registroprocedimentocoleta.setProcedimentoDeColetaid(procedimentoDeColetaidNew);
            }
            registroprocedimentocoleta = em.merge(registroprocedimentocoleta);
            if (procedimentoDeColetaidOld != null && !procedimentoDeColetaidOld.equals(procedimentoDeColetaidNew)) {
                procedimentoDeColetaidOld.getRegistroprocedimentocoletaList().remove(registroprocedimentocoleta);
                procedimentoDeColetaidOld = em.merge(procedimentoDeColetaidOld);
            }
            if (procedimentoDeColetaidNew != null && !procedimentoDeColetaidNew.equals(procedimentoDeColetaidOld)) {
                procedimentoDeColetaidNew.getRegistroprocedimentocoletaList().add(registroprocedimentocoleta);
                procedimentoDeColetaidNew = em.merge(procedimentoDeColetaidNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = registroprocedimentocoleta.getId();
                if (findRegistroprocedimentocoleta(id) == null) {
                    throw new NonexistentEntityException("The registroprocedimentocoleta with id " + id + " no longer exists.");
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
            Registroprocedimentocoleta registroprocedimentocoleta;
            try {
                registroprocedimentocoleta = em.getReference(Registroprocedimentocoleta.class, id);
                registroprocedimentocoleta.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The registroprocedimentocoleta with id " + id + " no longer exists.", enfe);
            }
            Procedimentodecoleta procedimentoDeColetaid = registroprocedimentocoleta.getProcedimentoDeColetaid();
            if (procedimentoDeColetaid != null) {
                procedimentoDeColetaid.getRegistroprocedimentocoletaList().remove(registroprocedimentocoleta);
                procedimentoDeColetaid = em.merge(procedimentoDeColetaid);
            }
            em.remove(registroprocedimentocoleta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Registroprocedimentocoleta> findRegistroprocedimentocoletaEntities() {
        return findRegistroprocedimentocoletaEntities(true, -1, -1);
    }

    public List<Registroprocedimentocoleta> findRegistroprocedimentocoletaEntities(int maxResults, int firstResult) {
        return findRegistroprocedimentocoletaEntities(false, maxResults, firstResult);
    }

    private List<Registroprocedimentocoleta> findRegistroprocedimentocoletaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Registroprocedimentocoleta.class));
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

    public Registroprocedimentocoleta findRegistroprocedimentocoleta(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Registroprocedimentocoleta.class, id);
        } finally {
            em.close();
        }
    }

    public int getRegistroprocedimentocoletaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Registroprocedimentocoleta> rt = cq.from(Registroprocedimentocoleta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
