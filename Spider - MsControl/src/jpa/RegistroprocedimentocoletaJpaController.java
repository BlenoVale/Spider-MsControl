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
import model.Procedimentodecoleta;
import model.Registroprocedimentocoleta;
import model.RegistroprocedimentocoletaPK;

/**
 *
 * @author Dan
 */
public class RegistroprocedimentocoletaJpaController implements Serializable {

    public RegistroprocedimentocoletaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Registroprocedimentocoleta registroprocedimentocoleta) throws PreexistingEntityException, Exception {
        if (registroprocedimentocoleta.getRegistroprocedimentocoletaPK() == null) {
            registroprocedimentocoleta.setRegistroprocedimentocoletaPK(new RegistroprocedimentocoletaPK());
        }
        registroprocedimentocoleta.getRegistroprocedimentocoletaPK().setProcedimentoDeColetaid(registroprocedimentocoleta.getProcedimentodecoleta().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Procedimentodecoleta procedimentodecoleta = registroprocedimentocoleta.getProcedimentodecoleta();
            if (procedimentodecoleta != null) {
                procedimentodecoleta = em.getReference(procedimentodecoleta.getClass(), procedimentodecoleta.getId());
                registroprocedimentocoleta.setProcedimentodecoleta(procedimentodecoleta);
            }
            em.persist(registroprocedimentocoleta);
            if (procedimentodecoleta != null) {
                procedimentodecoleta.getRegistroprocedimentocoletaList().add(registroprocedimentocoleta);
                procedimentodecoleta = em.merge(procedimentodecoleta);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRegistroprocedimentocoleta(registroprocedimentocoleta.getRegistroprocedimentocoletaPK()) != null) {
                throw new PreexistingEntityException("Registroprocedimentocoleta " + registroprocedimentocoleta + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Registroprocedimentocoleta registroprocedimentocoleta) throws NonexistentEntityException, Exception {
        registroprocedimentocoleta.getRegistroprocedimentocoletaPK().setProcedimentoDeColetaid(registroprocedimentocoleta.getProcedimentodecoleta().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Registroprocedimentocoleta persistentRegistroprocedimentocoleta = em.find(Registroprocedimentocoleta.class, registroprocedimentocoleta.getRegistroprocedimentocoletaPK());
            Procedimentodecoleta procedimentodecoletaOld = persistentRegistroprocedimentocoleta.getProcedimentodecoleta();
            Procedimentodecoleta procedimentodecoletaNew = registroprocedimentocoleta.getProcedimentodecoleta();
            if (procedimentodecoletaNew != null) {
                procedimentodecoletaNew = em.getReference(procedimentodecoletaNew.getClass(), procedimentodecoletaNew.getId());
                registroprocedimentocoleta.setProcedimentodecoleta(procedimentodecoletaNew);
            }
            registroprocedimentocoleta = em.merge(registroprocedimentocoleta);
            if (procedimentodecoletaOld != null && !procedimentodecoletaOld.equals(procedimentodecoletaNew)) {
                procedimentodecoletaOld.getRegistroprocedimentocoletaList().remove(registroprocedimentocoleta);
                procedimentodecoletaOld = em.merge(procedimentodecoletaOld);
            }
            if (procedimentodecoletaNew != null && !procedimentodecoletaNew.equals(procedimentodecoletaOld)) {
                procedimentodecoletaNew.getRegistroprocedimentocoletaList().add(registroprocedimentocoleta);
                procedimentodecoletaNew = em.merge(procedimentodecoletaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                RegistroprocedimentocoletaPK id = registroprocedimentocoleta.getRegistroprocedimentocoletaPK();
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

    public void destroy(RegistroprocedimentocoletaPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Registroprocedimentocoleta registroprocedimentocoleta;
            try {
                registroprocedimentocoleta = em.getReference(Registroprocedimentocoleta.class, id);
                registroprocedimentocoleta.getRegistroprocedimentocoletaPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The registroprocedimentocoleta with id " + id + " no longer exists.", enfe);
            }
            Procedimentodecoleta procedimentodecoleta = registroprocedimentocoleta.getProcedimentodecoleta();
            if (procedimentodecoleta != null) {
                procedimentodecoleta.getRegistroprocedimentocoletaList().remove(registroprocedimentocoleta);
                procedimentodecoleta = em.merge(procedimentodecoleta);
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

    public Registroprocedimentocoleta findRegistroprocedimentocoleta(RegistroprocedimentocoletaPK id) {
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
