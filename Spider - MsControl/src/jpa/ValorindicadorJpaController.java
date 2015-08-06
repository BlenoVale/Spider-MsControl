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
import model.Indicador;
import model.Valorindicador;

/**
 *
 * @author Bleno Vale
 */
public class ValorindicadorJpaController implements Serializable {

    public ValorindicadorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Valorindicador valorindicador) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Indicador indicadorid = valorindicador.getIndicadorid();
            if (indicadorid != null) {
                indicadorid = em.getReference(indicadorid.getClass(), indicadorid.getId());
                valorindicador.setIndicadorid(indicadorid);
            }
            em.persist(valorindicador);
            if (indicadorid != null) {
                indicadorid.getValorindicadorList().add(valorindicador);
                indicadorid = em.merge(indicadorid);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Valorindicador valorindicador) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Valorindicador persistentValorindicador = em.find(Valorindicador.class, valorindicador.getId());
            Indicador indicadoridOld = persistentValorindicador.getIndicadorid();
            Indicador indicadoridNew = valorindicador.getIndicadorid();
            if (indicadoridNew != null) {
                indicadoridNew = em.getReference(indicadoridNew.getClass(), indicadoridNew.getId());
                valorindicador.setIndicadorid(indicadoridNew);
            }
            valorindicador = em.merge(valorindicador);
            if (indicadoridOld != null && !indicadoridOld.equals(indicadoridNew)) {
                indicadoridOld.getValorindicadorList().remove(valorindicador);
                indicadoridOld = em.merge(indicadoridOld);
            }
            if (indicadoridNew != null && !indicadoridNew.equals(indicadoridOld)) {
                indicadoridNew.getValorindicadorList().add(valorindicador);
                indicadoridNew = em.merge(indicadoridNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = valorindicador.getId();
                if (findValorindicador(id) == null) {
                    throw new NonexistentEntityException("The valorindicador with id " + id + " no longer exists.");
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
            Valorindicador valorindicador;
            try {
                valorindicador = em.getReference(Valorindicador.class, id);
                valorindicador.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The valorindicador with id " + id + " no longer exists.", enfe);
            }
            Indicador indicadorid = valorindicador.getIndicadorid();
            if (indicadorid != null) {
                indicadorid.getValorindicadorList().remove(valorindicador);
                indicadorid = em.merge(indicadorid);
            }
            em.remove(valorindicador);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Valorindicador> findValorindicadorEntities() {
        return findValorindicadorEntities(true, -1, -1);
    }

    public List<Valorindicador> findValorindicadorEntities(int maxResults, int firstResult) {
        return findValorindicadorEntities(false, maxResults, firstResult);
    }

    private List<Valorindicador> findValorindicadorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Valorindicador.class));
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

    public Valorindicador findValorindicador(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Valorindicador.class, id);
        } finally {
            em.close();
        }
    }

    public int getValorindicadorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Valorindicador> rt = cq.from(Valorindicador.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
