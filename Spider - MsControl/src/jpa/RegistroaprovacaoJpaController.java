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
import model.Registroaprovacao;

/**
 *
 * @author Spider
 */
public class RegistroaprovacaoJpaController implements Serializable {

    public RegistroaprovacaoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Registroaprovacao registroaprovacao) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Aprovacao aprovacaoid = registroaprovacao.getAprovacaoid();
            if (aprovacaoid != null) {
                aprovacaoid = em.getReference(aprovacaoid.getClass(), aprovacaoid.getId());
                registroaprovacao.setAprovacaoid(aprovacaoid);
            }
            em.persist(registroaprovacao);
            if (aprovacaoid != null) {
                aprovacaoid.getRegistroaprovacaoList().add(registroaprovacao);
                aprovacaoid = em.merge(aprovacaoid);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Registroaprovacao registroaprovacao) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Registroaprovacao persistentRegistroaprovacao = em.find(Registroaprovacao.class, registroaprovacao.getId());
            Aprovacao aprovacaoidOld = persistentRegistroaprovacao.getAprovacaoid();
            Aprovacao aprovacaoidNew = registroaprovacao.getAprovacaoid();
            if (aprovacaoidNew != null) {
                aprovacaoidNew = em.getReference(aprovacaoidNew.getClass(), aprovacaoidNew.getId());
                registroaprovacao.setAprovacaoid(aprovacaoidNew);
            }
            registroaprovacao = em.merge(registroaprovacao);
            if (aprovacaoidOld != null && !aprovacaoidOld.equals(aprovacaoidNew)) {
                aprovacaoidOld.getRegistroaprovacaoList().remove(registroaprovacao);
                aprovacaoidOld = em.merge(aprovacaoidOld);
            }
            if (aprovacaoidNew != null && !aprovacaoidNew.equals(aprovacaoidOld)) {
                aprovacaoidNew.getRegistroaprovacaoList().add(registroaprovacao);
                aprovacaoidNew = em.merge(aprovacaoidNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = registroaprovacao.getId();
                if (findRegistroaprovacao(id) == null) {
                    throw new NonexistentEntityException("The registroaprovacao with id " + id + " no longer exists.");
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
            Registroaprovacao registroaprovacao;
            try {
                registroaprovacao = em.getReference(Registroaprovacao.class, id);
                registroaprovacao.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The registroaprovacao with id " + id + " no longer exists.", enfe);
            }
            Aprovacao aprovacaoid = registroaprovacao.getAprovacaoid();
            if (aprovacaoid != null) {
                aprovacaoid.getRegistroaprovacaoList().remove(registroaprovacao);
                aprovacaoid = em.merge(aprovacaoid);
            }
            em.remove(registroaprovacao);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Registroaprovacao> findRegistroaprovacaoEntities() {
        return findRegistroaprovacaoEntities(true, -1, -1);
    }

    public List<Registroaprovacao> findRegistroaprovacaoEntities(int maxResults, int firstResult) {
        return findRegistroaprovacaoEntities(false, maxResults, firstResult);
    }

    private List<Registroaprovacao> findRegistroaprovacaoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Registroaprovacao.class));
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

    public Registroaprovacao findRegistroaprovacao(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Registroaprovacao.class, id);
        } finally {
            em.close();
        }
    }

    public int getRegistroaprovacaoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Registroaprovacao> rt = cq.from(Registroaprovacao.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
