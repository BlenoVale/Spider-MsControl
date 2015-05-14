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
import model.Projeto;
import model.Registroprojeto;

/**
 *
 * @author BlenoVale
 */
public class RegistroprojetoJpaController implements Serializable {

    public RegistroprojetoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Registroprojeto registroprojeto) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Projeto projetoid = registroprojeto.getProjetoid();
            if (projetoid != null) {
                projetoid = em.getReference(projetoid.getClass(), projetoid.getId());
                registroprojeto.setProjetoid(projetoid);
            }
            em.persist(registroprojeto);
            if (projetoid != null) {
                projetoid.getRegistroprojetoList().add(registroprojeto);
                projetoid = em.merge(projetoid);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Registroprojeto registroprojeto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Registroprojeto persistentRegistroprojeto = em.find(Registroprojeto.class, registroprojeto.getId());
            Projeto projetoidOld = persistentRegistroprojeto.getProjetoid();
            Projeto projetoidNew = registroprojeto.getProjetoid();
            if (projetoidNew != null) {
                projetoidNew = em.getReference(projetoidNew.getClass(), projetoidNew.getId());
                registroprojeto.setProjetoid(projetoidNew);
            }
            registroprojeto = em.merge(registroprojeto);
            if (projetoidOld != null && !projetoidOld.equals(projetoidNew)) {
                projetoidOld.getRegistroprojetoList().remove(registroprojeto);
                projetoidOld = em.merge(projetoidOld);
            }
            if (projetoidNew != null && !projetoidNew.equals(projetoidOld)) {
                projetoidNew.getRegistroprojetoList().add(registroprojeto);
                projetoidNew = em.merge(projetoidNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = registroprojeto.getId();
                if (findRegistroprojeto(id) == null) {
                    throw new NonexistentEntityException("The registroprojeto with id " + id + " no longer exists.");
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
            Registroprojeto registroprojeto;
            try {
                registroprojeto = em.getReference(Registroprojeto.class, id);
                registroprojeto.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The registroprojeto with id " + id + " no longer exists.", enfe);
            }
            Projeto projetoid = registroprojeto.getProjetoid();
            if (projetoid != null) {
                projetoid.getRegistroprojetoList().remove(registroprojeto);
                projetoid = em.merge(projetoid);
            }
            em.remove(registroprojeto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Registroprojeto> findRegistroprojetoEntities() {
        return findRegistroprojetoEntities(true, -1, -1);
    }

    public List<Registroprojeto> findRegistroprojetoEntities(int maxResults, int firstResult) {
        return findRegistroprojetoEntities(false, maxResults, firstResult);
    }

    private List<Registroprojeto> findRegistroprojetoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Registroprojeto.class));
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

    public Registroprojeto findRegistroprojeto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Registroprojeto.class, id);
        } finally {
            em.close();
        }
    }

    public int getRegistroprojetoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Registroprojeto> rt = cq.from(Registroprojeto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
