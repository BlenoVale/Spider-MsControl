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
import model.Procedimentodeanalise;
import model.Registrodatacomunicacao;

/**
 *
 * @author paulosouza
 */
public class RegistrodatacomunicacaoJpaController implements Serializable {

    public RegistrodatacomunicacaoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Registrodatacomunicacao registrodatacomunicacao) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Procedimentodeanalise procedimentoDeAnaliseid = registrodatacomunicacao.getProcedimentoDeAnaliseid();
            if (procedimentoDeAnaliseid != null) {
                procedimentoDeAnaliseid = em.getReference(procedimentoDeAnaliseid.getClass(), procedimentoDeAnaliseid.getId());
                registrodatacomunicacao.setProcedimentoDeAnaliseid(procedimentoDeAnaliseid);
            }
            em.persist(registrodatacomunicacao);
            if (procedimentoDeAnaliseid != null) {
                procedimentoDeAnaliseid.getRegistrodatacomunicacaoList().add(registrodatacomunicacao);
                procedimentoDeAnaliseid = em.merge(procedimentoDeAnaliseid);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Registrodatacomunicacao registrodatacomunicacao) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Registrodatacomunicacao persistentRegistrodatacomunicacao = em.find(Registrodatacomunicacao.class, registrodatacomunicacao.getId());
            Procedimentodeanalise procedimentoDeAnaliseidOld = persistentRegistrodatacomunicacao.getProcedimentoDeAnaliseid();
            Procedimentodeanalise procedimentoDeAnaliseidNew = registrodatacomunicacao.getProcedimentoDeAnaliseid();
            if (procedimentoDeAnaliseidNew != null) {
                procedimentoDeAnaliseidNew = em.getReference(procedimentoDeAnaliseidNew.getClass(), procedimentoDeAnaliseidNew.getId());
                registrodatacomunicacao.setProcedimentoDeAnaliseid(procedimentoDeAnaliseidNew);
            }
            registrodatacomunicacao = em.merge(registrodatacomunicacao);
            if (procedimentoDeAnaliseidOld != null && !procedimentoDeAnaliseidOld.equals(procedimentoDeAnaliseidNew)) {
                procedimentoDeAnaliseidOld.getRegistrodatacomunicacaoList().remove(registrodatacomunicacao);
                procedimentoDeAnaliseidOld = em.merge(procedimentoDeAnaliseidOld);
            }
            if (procedimentoDeAnaliseidNew != null && !procedimentoDeAnaliseidNew.equals(procedimentoDeAnaliseidOld)) {
                procedimentoDeAnaliseidNew.getRegistrodatacomunicacaoList().add(registrodatacomunicacao);
                procedimentoDeAnaliseidNew = em.merge(procedimentoDeAnaliseidNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = registrodatacomunicacao.getId();
                if (findRegistrodatacomunicacao(id) == null) {
                    throw new NonexistentEntityException("The registrodatacomunicacao with id " + id + " no longer exists.");
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
            Registrodatacomunicacao registrodatacomunicacao;
            try {
                registrodatacomunicacao = em.getReference(Registrodatacomunicacao.class, id);
                registrodatacomunicacao.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The registrodatacomunicacao with id " + id + " no longer exists.", enfe);
            }
            Procedimentodeanalise procedimentoDeAnaliseid = registrodatacomunicacao.getProcedimentoDeAnaliseid();
            if (procedimentoDeAnaliseid != null) {
                procedimentoDeAnaliseid.getRegistrodatacomunicacaoList().remove(registrodatacomunicacao);
                procedimentoDeAnaliseid = em.merge(procedimentoDeAnaliseid);
            }
            em.remove(registrodatacomunicacao);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Registrodatacomunicacao> findRegistrodatacomunicacaoEntities() {
        return findRegistrodatacomunicacaoEntities(true, -1, -1);
    }

    public List<Registrodatacomunicacao> findRegistrodatacomunicacaoEntities(int maxResults, int firstResult) {
        return findRegistrodatacomunicacaoEntities(false, maxResults, firstResult);
    }

    private List<Registrodatacomunicacao> findRegistrodatacomunicacaoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Registrodatacomunicacao.class));
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

    public Registrodatacomunicacao findRegistrodatacomunicacao(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Registrodatacomunicacao.class, id);
        } finally {
            em.close();
        }
    }

    public int getRegistrodatacomunicacaoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Registrodatacomunicacao> rt = cq.from(Registrodatacomunicacao.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
