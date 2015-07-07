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
import model.Objetivodequestao;
import model.Registroobjetivoquestao;

/**
 *
 * @author Paulo
 */
public class RegistroobjetivoquestaoJpaController implements Serializable {

    public RegistroobjetivoquestaoJpaController(EntityManagerFactory emf)
    {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager()
    {
        return emf.createEntityManager();
    }

    public void create(Registroobjetivoquestao registroobjetivoquestao)
    {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Objetivodequestao objetivoDeQuestaoid = registroobjetivoquestao.getObjetivoDeQuestaoid();
            if (objetivoDeQuestaoid != null) {
                objetivoDeQuestaoid = em.getReference(objetivoDeQuestaoid.getClass(), objetivoDeQuestaoid.getId());
                registroobjetivoquestao.setObjetivoDeQuestaoid(objetivoDeQuestaoid);
            }
            em.persist(registroobjetivoquestao);
            if (objetivoDeQuestaoid != null) {
                objetivoDeQuestaoid.getRegistroobjetivoquestaoList().add(registroobjetivoquestao);
                objetivoDeQuestaoid = em.merge(objetivoDeQuestaoid);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Registroobjetivoquestao registroobjetivoquestao) throws NonexistentEntityException, Exception
    {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Registroobjetivoquestao persistentRegistroobjetivoquestao = em.find(Registroobjetivoquestao.class, registroobjetivoquestao.getId());
            Objetivodequestao objetivoDeQuestaoidOld = persistentRegistroobjetivoquestao.getObjetivoDeQuestaoid();
            Objetivodequestao objetivoDeQuestaoidNew = registroobjetivoquestao.getObjetivoDeQuestaoid();
            if (objetivoDeQuestaoidNew != null) {
                objetivoDeQuestaoidNew = em.getReference(objetivoDeQuestaoidNew.getClass(), objetivoDeQuestaoidNew.getId());
                registroobjetivoquestao.setObjetivoDeQuestaoid(objetivoDeQuestaoidNew);
            }
            registroobjetivoquestao = em.merge(registroobjetivoquestao);
            if (objetivoDeQuestaoidOld != null && !objetivoDeQuestaoidOld.equals(objetivoDeQuestaoidNew)) {
                objetivoDeQuestaoidOld.getRegistroobjetivoquestaoList().remove(registroobjetivoquestao);
                objetivoDeQuestaoidOld = em.merge(objetivoDeQuestaoidOld);
            }
            if (objetivoDeQuestaoidNew != null && !objetivoDeQuestaoidNew.equals(objetivoDeQuestaoidOld)) {
                objetivoDeQuestaoidNew.getRegistroobjetivoquestaoList().add(registroobjetivoquestao);
                objetivoDeQuestaoidNew = em.merge(objetivoDeQuestaoidNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = registroobjetivoquestao.getId();
                if (findRegistroobjetivoquestao(id) == null) {
                    throw new NonexistentEntityException("The registroobjetivoquestao with id " + id + " no longer exists.");
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
            Registroobjetivoquestao registroobjetivoquestao;
            try {
                registroobjetivoquestao = em.getReference(Registroobjetivoquestao.class, id);
                registroobjetivoquestao.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The registroobjetivoquestao with id " + id + " no longer exists.", enfe);
            }
            Objetivodequestao objetivoDeQuestaoid = registroobjetivoquestao.getObjetivoDeQuestaoid();
            if (objetivoDeQuestaoid != null) {
                objetivoDeQuestaoid.getRegistroobjetivoquestaoList().remove(registroobjetivoquestao);
                objetivoDeQuestaoid = em.merge(objetivoDeQuestaoid);
            }
            em.remove(registroobjetivoquestao);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Registroobjetivoquestao> findRegistroobjetivoquestaoEntities()
    {
        return findRegistroobjetivoquestaoEntities(true, -1, -1);
    }

    public List<Registroobjetivoquestao> findRegistroobjetivoquestaoEntities(int maxResults, int firstResult)
    {
        return findRegistroobjetivoquestaoEntities(false, maxResults, firstResult);
    }

    private List<Registroobjetivoquestao> findRegistroobjetivoquestaoEntities(boolean all, int maxResults, int firstResult)
    {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Registroobjetivoquestao.class));
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

    public Registroobjetivoquestao findRegistroobjetivoquestao(Integer id)
    {
        EntityManager em = getEntityManager();
        try {
            return em.find(Registroobjetivoquestao.class, id);
        } finally {
            em.close();
        }
    }

    public int getRegistroobjetivoquestaoCount()
    {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Registroobjetivoquestao> rt = cq.from(Registroobjetivoquestao.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
