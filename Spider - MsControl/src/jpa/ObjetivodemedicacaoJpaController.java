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
import model.Objetivodemedicacao;
import model.ObjetivodemedicacaoPK;

/**
 *
 * @author Spider
 */
public class ObjetivodemedicacaoJpaController implements Serializable {

    public ObjetivodemedicacaoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Objetivodemedicacao objetivodemedicacao) throws PreexistingEntityException, Exception {
        if (objetivodemedicacao.getObjetivodemedicacaoPK() == null) {
            objetivodemedicacao.setObjetivodemedicacaoPK(new ObjetivodemedicacaoPK());
        }
        objetivodemedicacao.getObjetivodemedicacaoPK().setProjetoid(objetivodemedicacao.getProjeto().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(objetivodemedicacao);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findObjetivodemedicacao(objetivodemedicacao.getObjetivodemedicacaoPK()) != null) {
                throw new PreexistingEntityException("Objetivodemedicacao " + objetivodemedicacao + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Objetivodemedicacao objetivodemedicacao) throws NonexistentEntityException, Exception {
        objetivodemedicacao.getObjetivodemedicacaoPK().setProjetoid(objetivodemedicacao.getProjeto().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            objetivodemedicacao = em.merge(objetivodemedicacao);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                ObjetivodemedicacaoPK id = objetivodemedicacao.getObjetivodemedicacaoPK();
                if (findObjetivodemedicacao(id) == null) {
                    throw new NonexistentEntityException("The objetivodemedicacao with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(ObjetivodemedicacaoPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Objetivodemedicacao objetivodemedicacao;
            try {
                objetivodemedicacao = em.getReference(Objetivodemedicacao.class, id);
                objetivodemedicacao.getObjetivodemedicacaoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The objetivodemedicacao with id " + id + " no longer exists.", enfe);
            }
            em.remove(objetivodemedicacao);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Objetivodemedicacao> findObjetivodemedicacaoEntities() {
        return findObjetivodemedicacaoEntities(true, -1, -1);
    }

    public List<Objetivodemedicacao> findObjetivodemedicacaoEntities(int maxResults, int firstResult) {
        return findObjetivodemedicacaoEntities(false, maxResults, firstResult);
    }

    private List<Objetivodemedicacao> findObjetivodemedicacaoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Objetivodemedicacao.class));
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

    public Objetivodemedicacao findObjetivodemedicacao(ObjetivodemedicacaoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Objetivodemedicacao.class, id);
        } finally {
            em.close();
        }
    }

    public int getObjetivodemedicacaoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Objetivodemedicacao> rt = cq.from(Objetivodemedicacao.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
