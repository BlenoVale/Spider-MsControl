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
import model.Aprovacao;
import model.Definicao;

/**
 *
 * @author Dan
 */
public class AprovacaoJpaController implements Serializable {

    public AprovacaoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Aprovacao aprovacao) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Definicao definicao = aprovacao.getDefinicao();
            if (definicao != null) {
                definicao = em.getReference(definicao.getClass(), definicao.getDefinicaoPK());
                aprovacao.setDefinicao(definicao);
            }
            em.persist(aprovacao);
            if (definicao != null) {
                definicao.getAprovacaoList().add(aprovacao);
                definicao = em.merge(definicao);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Aprovacao aprovacao) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Aprovacao persistentAprovacao = em.find(Aprovacao.class, aprovacao.getId());
            Definicao definicaoOld = persistentAprovacao.getDefinicao();
            Definicao definicaoNew = aprovacao.getDefinicao();
            if (definicaoNew != null) {
                definicaoNew = em.getReference(definicaoNew.getClass(), definicaoNew.getDefinicaoPK());
                aprovacao.setDefinicao(definicaoNew);
            }
            aprovacao = em.merge(aprovacao);
            if (definicaoOld != null && !definicaoOld.equals(definicaoNew)) {
                definicaoOld.getAprovacaoList().remove(aprovacao);
                definicaoOld = em.merge(definicaoOld);
            }
            if (definicaoNew != null && !definicaoNew.equals(definicaoOld)) {
                definicaoNew.getAprovacaoList().add(aprovacao);
                definicaoNew = em.merge(definicaoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = aprovacao.getId();
                if (findAprovacao(id) == null) {
                    throw new NonexistentEntityException("The aprovacao with id " + id + " no longer exists.");
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
            Aprovacao aprovacao;
            try {
                aprovacao = em.getReference(Aprovacao.class, id);
                aprovacao.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The aprovacao with id " + id + " no longer exists.", enfe);
            }
            Definicao definicao = aprovacao.getDefinicao();
            if (definicao != null) {
                definicao.getAprovacaoList().remove(aprovacao);
                definicao = em.merge(definicao);
            }
            em.remove(aprovacao);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Aprovacao> findAprovacaoEntities() {
        return findAprovacaoEntities(true, -1, -1);
    }

    public List<Aprovacao> findAprovacaoEntities(int maxResults, int firstResult) {
        return findAprovacaoEntities(false, maxResults, firstResult);
    }

    private List<Aprovacao> findAprovacaoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Aprovacao.class));
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

    public Aprovacao findAprovacao(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Aprovacao.class, id);
        } finally {
            em.close();
        }
    }

    public int getAprovacaoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Aprovacao> rt = cq.from(Aprovacao.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
