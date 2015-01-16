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
import jpa.exceptions.PreexistingEntityException;
import model.Aprovacao;
import model.Registroaprovacao;
import model.RegistroaprovacaoPK;

/**
 *
 * @author Dan
 */
public class RegistroaprovacaoJpaController implements Serializable {

    public RegistroaprovacaoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Registroaprovacao registroaprovacao) throws PreexistingEntityException, Exception {
        if (registroaprovacao.getRegistroaprovacaoPK() == null) {
            registroaprovacao.setRegistroaprovacaoPK(new RegistroaprovacaoPK());
        }
        registroaprovacao.getRegistroaprovacaoPK().setAprovacaoid(registroaprovacao.getAprovacao().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Aprovacao aprovacao = registroaprovacao.getAprovacao();
            if (aprovacao != null) {
                aprovacao = em.getReference(aprovacao.getClass(), aprovacao.getId());
                registroaprovacao.setAprovacao(aprovacao);
            }
            em.persist(registroaprovacao);
            if (aprovacao != null) {
                aprovacao.getRegistroaprovacaoList().add(registroaprovacao);
                aprovacao = em.merge(aprovacao);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRegistroaprovacao(registroaprovacao.getRegistroaprovacaoPK()) != null) {
                throw new PreexistingEntityException("Registroaprovacao " + registroaprovacao + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Registroaprovacao registroaprovacao) throws NonexistentEntityException, Exception {
        registroaprovacao.getRegistroaprovacaoPK().setAprovacaoid(registroaprovacao.getAprovacao().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Registroaprovacao persistentRegistroaprovacao = em.find(Registroaprovacao.class, registroaprovacao.getRegistroaprovacaoPK());
            Aprovacao aprovacaoOld = persistentRegistroaprovacao.getAprovacao();
            Aprovacao aprovacaoNew = registroaprovacao.getAprovacao();
            if (aprovacaoNew != null) {
                aprovacaoNew = em.getReference(aprovacaoNew.getClass(), aprovacaoNew.getId());
                registroaprovacao.setAprovacao(aprovacaoNew);
            }
            registroaprovacao = em.merge(registroaprovacao);
            if (aprovacaoOld != null && !aprovacaoOld.equals(aprovacaoNew)) {
                aprovacaoOld.getRegistroaprovacaoList().remove(registroaprovacao);
                aprovacaoOld = em.merge(aprovacaoOld);
            }
            if (aprovacaoNew != null && !aprovacaoNew.equals(aprovacaoOld)) {
                aprovacaoNew.getRegistroaprovacaoList().add(registroaprovacao);
                aprovacaoNew = em.merge(aprovacaoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                RegistroaprovacaoPK id = registroaprovacao.getRegistroaprovacaoPK();
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

    public void destroy(RegistroaprovacaoPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Registroaprovacao registroaprovacao;
            try {
                registroaprovacao = em.getReference(Registroaprovacao.class, id);
                registroaprovacao.getRegistroaprovacaoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The registroaprovacao with id " + id + " no longer exists.", enfe);
            }
            Aprovacao aprovacao = registroaprovacao.getAprovacao();
            if (aprovacao != null) {
                aprovacao.getRegistroaprovacaoList().remove(registroaprovacao);
                aprovacao = em.merge(aprovacao);
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

    public Registroaprovacao findRegistroaprovacao(RegistroaprovacaoPK id) {
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
