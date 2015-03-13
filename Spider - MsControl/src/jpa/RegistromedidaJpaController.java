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
import model.Medida;
import model.Registromedida;

/**
 *
 * @author Spider
 */
public class RegistromedidaJpaController implements Serializable {

    public RegistromedidaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Registromedida registromedida) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Medida medidaid = registromedida.getMedidaid();
            if (medidaid != null) {
                medidaid = em.getReference(medidaid.getClass(), medidaid.getId());
                registromedida.setMedidaid(medidaid);
            }
            em.persist(registromedida);
            if (medidaid != null) {
                medidaid.getRegistromedidaList().add(registromedida);
                medidaid = em.merge(medidaid);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Registromedida registromedida) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Registromedida persistentRegistromedida = em.find(Registromedida.class, registromedida.getId());
            Medida medidaidOld = persistentRegistromedida.getMedidaid();
            Medida medidaidNew = registromedida.getMedidaid();
            if (medidaidNew != null) {
                medidaidNew = em.getReference(medidaidNew.getClass(), medidaidNew.getId());
                registromedida.setMedidaid(medidaidNew);
            }
            registromedida = em.merge(registromedida);
            if (medidaidOld != null && !medidaidOld.equals(medidaidNew)) {
                medidaidOld.getRegistromedidaList().remove(registromedida);
                medidaidOld = em.merge(medidaidOld);
            }
            if (medidaidNew != null && !medidaidNew.equals(medidaidOld)) {
                medidaidNew.getRegistromedidaList().add(registromedida);
                medidaidNew = em.merge(medidaidNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = registromedida.getId();
                if (findRegistromedida(id) == null) {
                    throw new NonexistentEntityException("The registromedida with id " + id + " no longer exists.");
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
            Registromedida registromedida;
            try {
                registromedida = em.getReference(Registromedida.class, id);
                registromedida.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The registromedida with id " + id + " no longer exists.", enfe);
            }
            Medida medidaid = registromedida.getMedidaid();
            if (medidaid != null) {
                medidaid.getRegistromedidaList().remove(registromedida);
                medidaid = em.merge(medidaid);
            }
            em.remove(registromedida);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Registromedida> findRegistromedidaEntities() {
        return findRegistromedidaEntities(true, -1, -1);
    }

    public List<Registromedida> findRegistromedidaEntities(int maxResults, int firstResult) {
        return findRegistromedidaEntities(false, maxResults, firstResult);
    }

    private List<Registromedida> findRegistromedidaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Registromedida.class));
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

    public Registromedida findRegistromedida(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Registromedida.class, id);
        } finally {
            em.close();
        }
    }

    public int getRegistromedidaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Registromedida> rt = cq.from(Registromedida.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
