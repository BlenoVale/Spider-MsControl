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
 * @author Dan
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
            Definicao idDefinicao = composicao.getIdDefinicao();
            if (idDefinicao != null) {
                idDefinicao = em.getReference(idDefinicao.getClass(), idDefinicao.getDefinicaoPK());
                composicao.setIdDefinicao(idDefinicao);
            }
            em.persist(composicao);
            if (idDefinicao != null) {
                idDefinicao.getComposicaoList().add(composicao);
                idDefinicao = em.merge(idDefinicao);
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
            Definicao idDefinicaoOld = persistentComposicao.getIdDefinicao();
            Definicao idDefinicaoNew = composicao.getIdDefinicao();
            if (idDefinicaoNew != null) {
                idDefinicaoNew = em.getReference(idDefinicaoNew.getClass(), idDefinicaoNew.getDefinicaoPK());
                composicao.setIdDefinicao(idDefinicaoNew);
            }
            composicao = em.merge(composicao);
            if (idDefinicaoOld != null && !idDefinicaoOld.equals(idDefinicaoNew)) {
                idDefinicaoOld.getComposicaoList().remove(composicao);
                idDefinicaoOld = em.merge(idDefinicaoOld);
            }
            if (idDefinicaoNew != null && !idDefinicaoNew.equals(idDefinicaoOld)) {
                idDefinicaoNew.getComposicaoList().add(composicao);
                idDefinicaoNew = em.merge(idDefinicaoNew);
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
            Definicao idDefinicao = composicao.getIdDefinicao();
            if (idDefinicao != null) {
                idDefinicao.getComposicaoList().remove(composicao);
                idDefinicao = em.merge(idDefinicao);
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
