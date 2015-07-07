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
import model.Perfilinteressado;
import model.Projeto;

/**
 *
 * @author Paulo
 */
public class PerfilinteressadoJpaController implements Serializable {

    public PerfilinteressadoJpaController(EntityManagerFactory emf)
    {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager()
    {
        return emf.createEntityManager();
    }

    public void create(Perfilinteressado perfilinteressado)
    {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Projeto projetoid = perfilinteressado.getProjetoid();
            if (projetoid != null) {
                projetoid = em.getReference(projetoid.getClass(), projetoid.getId());
                perfilinteressado.setProjetoid(projetoid);
            }
            em.persist(perfilinteressado);
            if (projetoid != null) {
                projetoid.getPerfilinteressadoList().add(perfilinteressado);
                projetoid = em.merge(projetoid);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Perfilinteressado perfilinteressado) throws NonexistentEntityException, Exception
    {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Perfilinteressado persistentPerfilinteressado = em.find(Perfilinteressado.class, perfilinteressado.getId());
            Projeto projetoidOld = persistentPerfilinteressado.getProjetoid();
            Projeto projetoidNew = perfilinteressado.getProjetoid();
            if (projetoidNew != null) {
                projetoidNew = em.getReference(projetoidNew.getClass(), projetoidNew.getId());
                perfilinteressado.setProjetoid(projetoidNew);
            }
            perfilinteressado = em.merge(perfilinteressado);
            if (projetoidOld != null && !projetoidOld.equals(projetoidNew)) {
                projetoidOld.getPerfilinteressadoList().remove(perfilinteressado);
                projetoidOld = em.merge(projetoidOld);
            }
            if (projetoidNew != null && !projetoidNew.equals(projetoidOld)) {
                projetoidNew.getPerfilinteressadoList().add(perfilinteressado);
                projetoidNew = em.merge(projetoidNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = perfilinteressado.getId();
                if (findPerfilinteressado(id) == null) {
                    throw new NonexistentEntityException("The perfilinteressado with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException
    {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Perfilinteressado perfilinteressado;
            try {
                perfilinteressado = em.getReference(Perfilinteressado.class, id);
                perfilinteressado.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The perfilinteressado with id " + id + " no longer exists.", enfe);
            }
            Projeto projetoid = perfilinteressado.getProjetoid();
            if (projetoid != null) {
                projetoid.getPerfilinteressadoList().remove(perfilinteressado);
                projetoid = em.merge(projetoid);
            }
            em.remove(perfilinteressado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Perfilinteressado> findPerfilinteressadoEntities()
    {
        return findPerfilinteressadoEntities(true, -1, -1);
    }

    public List<Perfilinteressado> findPerfilinteressadoEntities(int maxResults, int firstResult)
    {
        return findPerfilinteressadoEntities(false, maxResults, firstResult);
    }

    private List<Perfilinteressado> findPerfilinteressadoEntities(boolean all, int maxResults, int firstResult)
    {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Perfilinteressado.class));
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

    public Perfilinteressado findPerfilinteressado(Integer id)
    {
        EntityManager em = getEntityManager();
        try {
            return em.find(Perfilinteressado.class, id);
        } finally {
            em.close();
        }
    }

    public int getPerfilinteressadoCount()
    {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Perfilinteressado> rt = cq.from(Perfilinteressado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
