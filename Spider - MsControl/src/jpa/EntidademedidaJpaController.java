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
import model.Entidademedida;
import model.Projeto;

/**
 *
 * @author paulosouza
 */
public class EntidademedidaJpaController implements Serializable {

    public EntidademedidaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Entidademedida entidademedida) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Projeto projetoid = entidademedida.getProjetoid();
            if (projetoid != null) {
                projetoid = em.getReference(projetoid.getClass(), projetoid.getId());
                entidademedida.setProjetoid(projetoid);
            }
            em.persist(entidademedida);
            if (projetoid != null) {
                projetoid.getEntidademedidaList().add(entidademedida);
                projetoid = em.merge(projetoid);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Entidademedida entidademedida) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Entidademedida persistentEntidademedida = em.find(Entidademedida.class, entidademedida.getId());
            Projeto projetoidOld = persistentEntidademedida.getProjetoid();
            Projeto projetoidNew = entidademedida.getProjetoid();
            if (projetoidNew != null) {
                projetoidNew = em.getReference(projetoidNew.getClass(), projetoidNew.getId());
                entidademedida.setProjetoid(projetoidNew);
            }
            entidademedida = em.merge(entidademedida);
            if (projetoidOld != null && !projetoidOld.equals(projetoidNew)) {
                projetoidOld.getEntidademedidaList().remove(entidademedida);
                projetoidOld = em.merge(projetoidOld);
            }
            if (projetoidNew != null && !projetoidNew.equals(projetoidOld)) {
                projetoidNew.getEntidademedidaList().add(entidademedida);
                projetoidNew = em.merge(projetoidNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = entidademedida.getId();
                if (findEntidademedida(id) == null) {
                    throw new NonexistentEntityException("The entidademedida with id " + id + " no longer exists.");
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
            Entidademedida entidademedida;
            try {
                entidademedida = em.getReference(Entidademedida.class, id);
                entidademedida.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The entidademedida with id " + id + " no longer exists.", enfe);
            }
            Projeto projetoid = entidademedida.getProjetoid();
            if (projetoid != null) {
                projetoid.getEntidademedidaList().remove(entidademedida);
                projetoid = em.merge(projetoid);
            }
            em.remove(entidademedida);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Entidademedida> findEntidademedidaEntities() {
        return findEntidademedidaEntities(true, -1, -1);
    }

    public List<Entidademedida> findEntidademedidaEntities(int maxResults, int firstResult) {
        return findEntidademedidaEntities(false, maxResults, firstResult);
    }

    private List<Entidademedida> findEntidademedidaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Entidademedida.class));
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

    public Entidademedida findEntidademedida(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Entidademedida.class, id);
        } finally {
            em.close();
        }
    }

    public int getEntidademedidaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Entidademedida> rt = cq.from(Entidademedida.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
