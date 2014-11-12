/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.Composicao;
import model.Definicao;

/**
 *
 * @author Spider
 */
public class ComposicaoJpaController implements Serializable {

    public ComposicaoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Composicao composicao) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Definicao definicao = composicao.getDefinicao();
            if (definicao != null) {
                definicao = em.getReference(definicao.getClass(), definicao.getDefinicaoPK());
                composicao.setDefinicao(definicao);
            }
            em.persist(composicao);
            if (definicao != null) {
                definicao.getComposicaoList().add(composicao);
                definicao = em.merge(definicao);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Composicao composicao) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Composicao persistentComposicao = em.find(Composicao.class, composicao.getId());
            Definicao definicaoOld = persistentComposicao.getDefinicao();
            Definicao definicaoNew = composicao.getDefinicao();
            if (definicaoNew != null) {
                definicaoNew = em.getReference(definicaoNew.getClass(), definicaoNew.getDefinicaoPK());
                composicao.setDefinicao(definicaoNew);
            }
            composicao = em.merge(composicao);
            if (definicaoOld != null && !definicaoOld.equals(definicaoNew)) {
                definicaoOld.getComposicaoList().remove(composicao);
                definicaoOld = em.merge(definicaoOld);
            }
            if (definicaoNew != null && !definicaoNew.equals(definicaoOld)) {
                definicaoNew.getComposicaoList().add(composicao);
                definicaoNew = em.merge(definicaoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = composicao.getId();
                if (findComposicao(id) == null) {
                    throw new NonexistentEntityException("The composicao with id " + id + " no longer exists.");
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
            Composicao composicao;
            try {
                composicao = em.getReference(Composicao.class, id);
                composicao.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The composicao with id " + id + " no longer exists.", enfe);
            }
            Definicao definicao = composicao.getDefinicao();
            if (definicao != null) {
                definicao.getComposicaoList().remove(composicao);
                definicao = em.merge(definicao);
            }
            em.remove(composicao);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Composicao> findComposicaoEntities() {
        return findComposicaoEntities(true, -1, -1);
    }

    public List<Composicao> findComposicaoEntities(int maxResults, int firstResult) {
        return findComposicaoEntities(false, maxResults, firstResult);
    }

    private List<Composicao> findComposicaoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Composicao.class));
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

    public Composicao findComposicao(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Composicao.class, id);
        } finally {
            em.close();
        }
    }

    public int getComposicaoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Composicao> rt = cq.from(Composicao.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
