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
import model.Indicador;
import model.Registroindicador;

/**
 *
 * @author Dan
 */
public class RegistroindicadorJpaController implements Serializable {

    public RegistroindicadorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Registroindicador registroindicador) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Indicador indicadorid = registroindicador.getIndicadorid();
            if (indicadorid != null) {
                indicadorid = em.getReference(indicadorid.getClass(), indicadorid.getId());
                registroindicador.setIndicadorid(indicadorid);
            }
            em.persist(registroindicador);
            if (indicadorid != null) {
                indicadorid.getRegistroindicadorList().add(registroindicador);
                indicadorid = em.merge(indicadorid);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Registroindicador registroindicador) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Registroindicador persistentRegistroindicador = em.find(Registroindicador.class, registroindicador.getId());
            Indicador indicadoridOld = persistentRegistroindicador.getIndicadorid();
            Indicador indicadoridNew = registroindicador.getIndicadorid();
            if (indicadoridNew != null) {
                indicadoridNew = em.getReference(indicadoridNew.getClass(), indicadoridNew.getId());
                registroindicador.setIndicadorid(indicadoridNew);
            }
            registroindicador = em.merge(registroindicador);
            if (indicadoridOld != null && !indicadoridOld.equals(indicadoridNew)) {
                indicadoridOld.getRegistroindicadorList().remove(registroindicador);
                indicadoridOld = em.merge(indicadoridOld);
            }
            if (indicadoridNew != null && !indicadoridNew.equals(indicadoridOld)) {
                indicadoridNew.getRegistroindicadorList().add(registroindicador);
                indicadoridNew = em.merge(indicadoridNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = registroindicador.getId();
                if (findRegistroindicador(id) == null) {
                    throw new NonexistentEntityException("The registroindicador with id " + id + " no longer exists.");
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
            Registroindicador registroindicador;
            try {
                registroindicador = em.getReference(Registroindicador.class, id);
                registroindicador.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The registroindicador with id " + id + " no longer exists.", enfe);
            }
            Indicador indicadorid = registroindicador.getIndicadorid();
            if (indicadorid != null) {
                indicadorid.getRegistroindicadorList().remove(registroindicador);
                indicadorid = em.merge(indicadorid);
            }
            em.remove(registroindicador);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Registroindicador> findRegistroindicadorEntities() {
        return findRegistroindicadorEntities(true, -1, -1);
    }

    public List<Registroindicador> findRegistroindicadorEntities(int maxResults, int firstResult) {
        return findRegistroindicadorEntities(false, maxResults, firstResult);
    }

    private List<Registroindicador> findRegistroindicadorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Registroindicador.class));
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

    public Registroindicador findRegistroindicador(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Registroindicador.class, id);
        } finally {
            em.close();
        }
    }

    public int getRegistroindicadorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Registroindicador> rt = cq.from(Registroindicador.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
