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
import model.Projeto;
import model.Relatorios;

/**
 *
 * @author Bleno Vale
 */
public class RelatoriosJpaController implements Serializable {

    public RelatoriosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Relatorios relatorios) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Projeto projetoid = relatorios.getProjetoid();
            if (projetoid != null) {
                projetoid = em.getReference(projetoid.getClass(), projetoid.getId());
                relatorios.setProjetoid(projetoid);
            }
            em.persist(relatorios);
            if (projetoid != null) {
                projetoid.getRelatoriosList().add(relatorios);
                projetoid = em.merge(projetoid);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Relatorios relatorios) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Relatorios persistentRelatorios = em.find(Relatorios.class, relatorios.getIdRelatorio());
            Projeto projetoidOld = persistentRelatorios.getProjetoid();
            Projeto projetoidNew = relatorios.getProjetoid();
            if (projetoidNew != null) {
                projetoidNew = em.getReference(projetoidNew.getClass(), projetoidNew.getId());
                relatorios.setProjetoid(projetoidNew);
            }
            relatorios = em.merge(relatorios);
            if (projetoidOld != null && !projetoidOld.equals(projetoidNew)) {
                projetoidOld.getRelatoriosList().remove(relatorios);
                projetoidOld = em.merge(projetoidOld);
            }
            if (projetoidNew != null && !projetoidNew.equals(projetoidOld)) {
                projetoidNew.getRelatoriosList().add(relatorios);
                projetoidNew = em.merge(projetoidNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = relatorios.getIdRelatorio();
                if (findRelatorios(id) == null) {
                    throw new NonexistentEntityException("The relatorios with id " + id + " no longer exists.");
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
            Relatorios relatorios;
            try {
                relatorios = em.getReference(Relatorios.class, id);
                relatorios.getIdRelatorio();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The relatorios with id " + id + " no longer exists.", enfe);
            }
            Projeto projetoid = relatorios.getProjetoid();
            if (projetoid != null) {
                projetoid.getRelatoriosList().remove(relatorios);
                projetoid = em.merge(projetoid);
            }
            em.remove(relatorios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Relatorios> findRelatoriosEntities() {
        return findRelatoriosEntities(true, -1, -1);
    }

    public List<Relatorios> findRelatoriosEntities(int maxResults, int firstResult) {
        return findRelatoriosEntities(false, maxResults, firstResult);
    }

    private List<Relatorios> findRelatoriosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Relatorios.class));
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

    public Relatorios findRelatorios(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Relatorios.class, id);
        } finally {
            em.close();
        }
    }

    public int getRelatoriosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Relatorios> rt = cq.from(Relatorios.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
