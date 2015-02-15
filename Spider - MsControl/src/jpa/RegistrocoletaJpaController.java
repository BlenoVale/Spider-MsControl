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
import model.Coleta;
import model.Registrocoleta;

/**
 *
 * @author BlenoVale
 */
public class RegistrocoletaJpaController implements Serializable {

    public RegistrocoletaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Registrocoleta registrocoleta) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Coleta coletaid = registrocoleta.getColetaid();
            if (coletaid != null) {
                coletaid = em.getReference(coletaid.getClass(), coletaid.getId());
                registrocoleta.setColetaid(coletaid);
            }
            em.persist(registrocoleta);
            if (coletaid != null) {
                coletaid.getRegistrocoletaList().add(registrocoleta);
                coletaid = em.merge(coletaid);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Registrocoleta registrocoleta) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Registrocoleta persistentRegistrocoleta = em.find(Registrocoleta.class, registrocoleta.getId());
            Coleta coletaidOld = persistentRegistrocoleta.getColetaid();
            Coleta coletaidNew = registrocoleta.getColetaid();
            if (coletaidNew != null) {
                coletaidNew = em.getReference(coletaidNew.getClass(), coletaidNew.getId());
                registrocoleta.setColetaid(coletaidNew);
            }
            registrocoleta = em.merge(registrocoleta);
            if (coletaidOld != null && !coletaidOld.equals(coletaidNew)) {
                coletaidOld.getRegistrocoletaList().remove(registrocoleta);
                coletaidOld = em.merge(coletaidOld);
            }
            if (coletaidNew != null && !coletaidNew.equals(coletaidOld)) {
                coletaidNew.getRegistrocoletaList().add(registrocoleta);
                coletaidNew = em.merge(coletaidNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = registrocoleta.getId();
                if (findRegistrocoleta(id) == null) {
                    throw new NonexistentEntityException("The registrocoleta with id " + id + " no longer exists.");
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
            Registrocoleta registrocoleta;
            try {
                registrocoleta = em.getReference(Registrocoleta.class, id);
                registrocoleta.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The registrocoleta with id " + id + " no longer exists.", enfe);
            }
            Coleta coletaid = registrocoleta.getColetaid();
            if (coletaid != null) {
                coletaid.getRegistrocoletaList().remove(registrocoleta);
                coletaid = em.merge(coletaid);
            }
            em.remove(registrocoleta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Registrocoleta> findRegistrocoletaEntities() {
        return findRegistrocoletaEntities(true, -1, -1);
    }

    public List<Registrocoleta> findRegistrocoletaEntities(int maxResults, int firstResult) {
        return findRegistrocoletaEntities(false, maxResults, firstResult);
    }

    private List<Registrocoleta> findRegistrocoletaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Registrocoleta.class));
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

    public Registrocoleta findRegistrocoleta(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Registrocoleta.class, id);
        } finally {
            em.close();
        }
    }

    public int getRegistrocoletaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Registrocoleta> rt = cq.from(Registrocoleta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
