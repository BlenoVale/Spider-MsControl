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
            Definicao idDefinicao = aprovacao.getIdDefinicao();
            if (idDefinicao != null) {
                idDefinicao = em.getReference(idDefinicao.getClass(), idDefinicao.getDefinicaoPK());
                aprovacao.setIdDefinicao(idDefinicao);
            }
            em.persist(aprovacao);
            if (idDefinicao != null) {
                idDefinicao.getAprovacaoList().add(aprovacao);
                idDefinicao = em.merge(idDefinicao);
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
            Definicao idDefinicaoOld = persistentAprovacao.getIdDefinicao();
            Definicao idDefinicaoNew = aprovacao.getIdDefinicao();
            if (idDefinicaoNew != null) {
                idDefinicaoNew = em.getReference(idDefinicaoNew.getClass(), idDefinicaoNew.getDefinicaoPK());
                aprovacao.setIdDefinicao(idDefinicaoNew);
            }
            aprovacao = em.merge(aprovacao);
            if (idDefinicaoOld != null && !idDefinicaoOld.equals(idDefinicaoNew)) {
                idDefinicaoOld.getAprovacaoList().remove(aprovacao);
                idDefinicaoOld = em.merge(idDefinicaoOld);
            }
            if (idDefinicaoNew != null && !idDefinicaoNew.equals(idDefinicaoOld)) {
                idDefinicaoNew.getAprovacaoList().add(aprovacao);
                idDefinicaoNew = em.merge(idDefinicaoNew);
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
            Definicao idDefinicao = aprovacao.getIdDefinicao();
            if (idDefinicao != null) {
                idDefinicao.getAprovacaoList().remove(aprovacao);
                idDefinicao = em.merge(idDefinicao);
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
