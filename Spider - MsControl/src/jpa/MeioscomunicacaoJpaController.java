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
import model.Meioscomunicacao;
import model.Projeto;

/**
 *
 * @author Bleno Vale
 */
public class MeioscomunicacaoJpaController implements Serializable {

    public MeioscomunicacaoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Meioscomunicacao meioscomunicacao) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Projeto projetoid = meioscomunicacao.getProjetoid();
            if (projetoid != null) {
                projetoid = em.getReference(projetoid.getClass(), projetoid.getId());
                meioscomunicacao.setProjetoid(projetoid);
            }
            em.persist(meioscomunicacao);
            if (projetoid != null) {
                projetoid.getMeioscomunicacaoList().add(meioscomunicacao);
                projetoid = em.merge(projetoid);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Meioscomunicacao meioscomunicacao) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Meioscomunicacao persistentMeioscomunicacao = em.find(Meioscomunicacao.class, meioscomunicacao.getId());
            Projeto projetoidOld = persistentMeioscomunicacao.getProjetoid();
            Projeto projetoidNew = meioscomunicacao.getProjetoid();
            if (projetoidNew != null) {
                projetoidNew = em.getReference(projetoidNew.getClass(), projetoidNew.getId());
                meioscomunicacao.setProjetoid(projetoidNew);
            }
            meioscomunicacao = em.merge(meioscomunicacao);
            if (projetoidOld != null && !projetoidOld.equals(projetoidNew)) {
                projetoidOld.getMeioscomunicacaoList().remove(meioscomunicacao);
                projetoidOld = em.merge(projetoidOld);
            }
            if (projetoidNew != null && !projetoidNew.equals(projetoidOld)) {
                projetoidNew.getMeioscomunicacaoList().add(meioscomunicacao);
                projetoidNew = em.merge(projetoidNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = meioscomunicacao.getId();
                if (findMeioscomunicacao(id) == null) {
                    throw new NonexistentEntityException("The meioscomunicacao with id " + id + " no longer exists.");
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
            Meioscomunicacao meioscomunicacao;
            try {
                meioscomunicacao = em.getReference(Meioscomunicacao.class, id);
                meioscomunicacao.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The meioscomunicacao with id " + id + " no longer exists.", enfe);
            }
            Projeto projetoid = meioscomunicacao.getProjetoid();
            if (projetoid != null) {
                projetoid.getMeioscomunicacaoList().remove(meioscomunicacao);
                projetoid = em.merge(projetoid);
            }
            em.remove(meioscomunicacao);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Meioscomunicacao> findMeioscomunicacaoEntities() {
        return findMeioscomunicacaoEntities(true, -1, -1);
    }

    public List<Meioscomunicacao> findMeioscomunicacaoEntities(int maxResults, int firstResult) {
        return findMeioscomunicacaoEntities(false, maxResults, firstResult);
    }

    private List<Meioscomunicacao> findMeioscomunicacaoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Meioscomunicacao.class));
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

    public Meioscomunicacao findMeioscomunicacao(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Meioscomunicacao.class, id);
        } finally {
            em.close();
        }
    }

    public int getMeioscomunicacaoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Meioscomunicacao> rt = cq.from(Meioscomunicacao.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
