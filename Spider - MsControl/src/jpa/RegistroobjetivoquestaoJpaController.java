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
 * @author Dan
 */
public class RegistroobjetivoquestaoJpaController implements Serializable {

    public RegistroobjetivoquestaoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Registroobjetivoquestao registroobjetivoquestao) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Objetivodequestao objetivoDeQuestaoid1 = registroobjetivoquestao.getObjetivoDeQuestaoid1();
            if (objetivoDeQuestaoid1 != null) {
                objetivoDeQuestaoid1 = em.getReference(objetivoDeQuestaoid1.getClass(), objetivoDeQuestaoid1.getId());
                registroobjetivoquestao.setObjetivoDeQuestaoid1(objetivoDeQuestaoid1);
            }
            em.persist(registroobjetivoquestao);
            if (objetivoDeQuestaoid1 != null) {
                objetivoDeQuestaoid1.getRegistroobjetivoquestaoList().add(registroobjetivoquestao);
                objetivoDeQuestaoid1 = em.merge(objetivoDeQuestaoid1);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Registroobjetivoquestao registroobjetivoquestao) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Registroobjetivoquestao persistentRegistroobjetivoquestao = em.find(Registroobjetivoquestao.class, registroobjetivoquestao.getId());
            Objetivodequestao objetivoDeQuestaoid1Old = persistentRegistroobjetivoquestao.getObjetivoDeQuestaoid1();
            Objetivodequestao objetivoDeQuestaoid1New = registroobjetivoquestao.getObjetivoDeQuestaoid1();
            if (objetivoDeQuestaoid1New != null) {
                objetivoDeQuestaoid1New = em.getReference(objetivoDeQuestaoid1New.getClass(), objetivoDeQuestaoid1New.getId());
                registroobjetivoquestao.setObjetivoDeQuestaoid1(objetivoDeQuestaoid1New);
            }
            registroobjetivoquestao = em.merge(registroobjetivoquestao);
            if (objetivoDeQuestaoid1Old != null && !objetivoDeQuestaoid1Old.equals(objetivoDeQuestaoid1New)) {
                objetivoDeQuestaoid1Old.getRegistroobjetivoquestaoList().remove(registroobjetivoquestao);
                objetivoDeQuestaoid1Old = em.merge(objetivoDeQuestaoid1Old);
            }
            if (objetivoDeQuestaoid1New != null && !objetivoDeQuestaoid1New.equals(objetivoDeQuestaoid1Old)) {
                objetivoDeQuestaoid1New.getRegistroobjetivoquestaoList().add(registroobjetivoquestao);
                objetivoDeQuestaoid1New = em.merge(objetivoDeQuestaoid1New);
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

    public void destroy(Integer id) throws NonexistentEntityException {
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
            Objetivodequestao objetivoDeQuestaoid1 = registroobjetivoquestao.getObjetivoDeQuestaoid1();
            if (objetivoDeQuestaoid1 != null) {
                objetivoDeQuestaoid1.getRegistroobjetivoquestaoList().remove(registroobjetivoquestao);
                objetivoDeQuestaoid1 = em.merge(objetivoDeQuestaoid1);
            }
            em.remove(registroobjetivoquestao);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Registroobjetivoquestao> findRegistroobjetivoquestaoEntities() {
        return findRegistroobjetivoquestaoEntities(true, -1, -1);
    }

    public List<Registroobjetivoquestao> findRegistroobjetivoquestaoEntities(int maxResults, int firstResult) {
        return findRegistroobjetivoquestaoEntities(false, maxResults, firstResult);
    }

    private List<Registroobjetivoquestao> findRegistroobjetivoquestaoEntities(boolean all, int maxResults, int firstResult) {
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

    public Registroobjetivoquestao findRegistroobjetivoquestao(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Registroobjetivoquestao.class, id);
        } finally {
            em.close();
        }
    }

    public int getRegistroobjetivoquestaoCount() {
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
