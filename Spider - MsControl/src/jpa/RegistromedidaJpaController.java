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
import model.Medida;
import model.Registromedida;
import model.RegistromedidaPK;

/**
 *
 * @author Dan
 */
public class RegistromedidaJpaController implements Serializable {

    public RegistromedidaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Registromedida registromedida) throws PreexistingEntityException, Exception {
        if (registromedida.getRegistromedidaPK() == null) {
            registromedida.setRegistromedidaPK(new RegistromedidaPK());
        }
        registromedida.getRegistromedidaPK().setMedidaProjetoid(registromedida.getMedida().getMedidaPK().getProjetoid());
        registromedida.getRegistromedidaPK().setMedidaid(registromedida.getMedida().getMedidaPK().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Medida medida = registromedida.getMedida();
            if (medida != null) {
                medida = em.getReference(medida.getClass(), medida.getMedidaPK());
                registromedida.setMedida(medida);
            }
            em.persist(registromedida);
            if (medida != null) {
                medida.getRegistromedidaList().add(registromedida);
                medida = em.merge(medida);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRegistromedida(registromedida.getRegistromedidaPK()) != null) {
                throw new PreexistingEntityException("Registromedida " + registromedida + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Registromedida registromedida) throws NonexistentEntityException, Exception {
        registromedida.getRegistromedidaPK().setMedidaProjetoid(registromedida.getMedida().getMedidaPK().getProjetoid());
        registromedida.getRegistromedidaPK().setMedidaid(registromedida.getMedida().getMedidaPK().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Registromedida persistentRegistromedida = em.find(Registromedida.class, registromedida.getRegistromedidaPK());
            Medida medidaOld = persistentRegistromedida.getMedida();
            Medida medidaNew = registromedida.getMedida();
            if (medidaNew != null) {
                medidaNew = em.getReference(medidaNew.getClass(), medidaNew.getMedidaPK());
                registromedida.setMedida(medidaNew);
            }
            registromedida = em.merge(registromedida);
            if (medidaOld != null && !medidaOld.equals(medidaNew)) {
                medidaOld.getRegistromedidaList().remove(registromedida);
                medidaOld = em.merge(medidaOld);
            }
            if (medidaNew != null && !medidaNew.equals(medidaOld)) {
                medidaNew.getRegistromedidaList().add(registromedida);
                medidaNew = em.merge(medidaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                RegistromedidaPK id = registromedida.getRegistromedidaPK();
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

    public void destroy(RegistromedidaPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Registromedida registromedida;
            try {
                registromedida = em.getReference(Registromedida.class, id);
                registromedida.getRegistromedidaPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The registromedida with id " + id + " no longer exists.", enfe);
            }
            Medida medida = registromedida.getMedida();
            if (medida != null) {
                medida.getRegistromedidaList().remove(registromedida);
                medida = em.merge(medida);
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

    public Registromedida findRegistromedida(RegistromedidaPK id) {
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
