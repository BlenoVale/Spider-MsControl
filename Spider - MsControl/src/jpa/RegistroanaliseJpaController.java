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
import model.Analise;
import model.Registroanalise;

/**
 *
 * @author Paulo
 */
public class RegistroanaliseJpaController implements Serializable {

    public RegistroanaliseJpaController(EntityManagerFactory emf)
    {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager()
    {
        return emf.createEntityManager();
    }

    public void create(Registroanalise registroanalise)
    {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Analise analiseid = registroanalise.getAnaliseid();
            if (analiseid != null) {
                analiseid = em.getReference(analiseid.getClass(), analiseid.getId());
                registroanalise.setAnaliseid(analiseid);
            }
            em.persist(registroanalise);
            if (analiseid != null) {
                analiseid.getRegistroanaliseList().add(registroanalise);
                analiseid = em.merge(analiseid);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Registroanalise registroanalise) throws NonexistentEntityException, Exception
    {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Registroanalise persistentRegistroanalise = em.find(Registroanalise.class, registroanalise.getId());
            Analise analiseidOld = persistentRegistroanalise.getAnaliseid();
            Analise analiseidNew = registroanalise.getAnaliseid();
            if (analiseidNew != null) {
                analiseidNew = em.getReference(analiseidNew.getClass(), analiseidNew.getId());
                registroanalise.setAnaliseid(analiseidNew);
            }
            registroanalise = em.merge(registroanalise);
            if (analiseidOld != null && !analiseidOld.equals(analiseidNew)) {
                analiseidOld.getRegistroanaliseList().remove(registroanalise);
                analiseidOld = em.merge(analiseidOld);
            }
            if (analiseidNew != null && !analiseidNew.equals(analiseidOld)) {
                analiseidNew.getRegistroanaliseList().add(registroanalise);
                analiseidNew = em.merge(analiseidNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = registroanalise.getId();
                if (findRegistroanalise(id) == null) {
                    throw new NonexistentEntityException("The registroanalise with id " + id + " no longer exists.");
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
            Registroanalise registroanalise;
            try {
                registroanalise = em.getReference(Registroanalise.class, id);
                registroanalise.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The registroanalise with id " + id + " no longer exists.", enfe);
            }
            Analise analiseid = registroanalise.getAnaliseid();
            if (analiseid != null) {
                analiseid.getRegistroanaliseList().remove(registroanalise);
                analiseid = em.merge(analiseid);
            }
            em.remove(registroanalise);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Registroanalise> findRegistroanaliseEntities()
    {
        return findRegistroanaliseEntities(true, -1, -1);
    }

    public List<Registroanalise> findRegistroanaliseEntities(int maxResults, int firstResult)
    {
        return findRegistroanaliseEntities(false, maxResults, firstResult);
    }

    private List<Registroanalise> findRegistroanaliseEntities(boolean all, int maxResults, int firstResult)
    {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Registroanalise.class));
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

    public Registroanalise findRegistroanalise(Integer id)
    {
        EntityManager em = getEntityManager();
        try {
            return em.find(Registroanalise.class, id);
        } finally {
            em.close();
        }
    }

    public int getRegistroanaliseCount()
    {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Registroanalise> rt = cq.from(Registroanalise.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
