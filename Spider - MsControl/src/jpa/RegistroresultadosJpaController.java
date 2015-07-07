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
import model.Registroresultados;
import model.Resultados;

/**
 *
 * @author BlenoVale
 */
public class RegistroresultadosJpaController implements Serializable {

    public RegistroresultadosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Registroresultados registroresultados) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Resultados resultadosid = registroresultados.getResultadosid();
            if (resultadosid != null) {
                resultadosid = em.getReference(resultadosid.getClass(), resultadosid.getId());
                registroresultados.setResultadosid(resultadosid);
            }
            em.persist(registroresultados);
            if (resultadosid != null) {
                resultadosid.getRegistroresultadosList().add(registroresultados);
                resultadosid = em.merge(resultadosid);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Registroresultados registroresultados) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Registroresultados persistentRegistroresultados = em.find(Registroresultados.class, registroresultados.getId());
            Resultados resultadosidOld = persistentRegistroresultados.getResultadosid();
            Resultados resultadosidNew = registroresultados.getResultadosid();
            if (resultadosidNew != null) {
                resultadosidNew = em.getReference(resultadosidNew.getClass(), resultadosidNew.getId());
                registroresultados.setResultadosid(resultadosidNew);
            }
            registroresultados = em.merge(registroresultados);
            if (resultadosidOld != null && !resultadosidOld.equals(resultadosidNew)) {
                resultadosidOld.getRegistroresultadosList().remove(registroresultados);
                resultadosidOld = em.merge(resultadosidOld);
            }
            if (resultadosidNew != null && !resultadosidNew.equals(resultadosidOld)) {
                resultadosidNew.getRegistroresultadosList().add(registroresultados);
                resultadosidNew = em.merge(resultadosidNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = registroresultados.getId();
                if (findRegistroresultados(id) == null) {
                    throw new NonexistentEntityException("The registroresultados with id " + id + " no longer exists.");
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
            Registroresultados registroresultados;
            try {
                registroresultados = em.getReference(Registroresultados.class, id);
                registroresultados.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The registroresultados with id " + id + " no longer exists.", enfe);
            }
            Resultados resultadosid = registroresultados.getResultadosid();
            if (resultadosid != null) {
                resultadosid.getRegistroresultadosList().remove(registroresultados);
                resultadosid = em.merge(resultadosid);
            }
            em.remove(registroresultados);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Registroresultados> findRegistroresultadosEntities() {
        return findRegistroresultadosEntities(true, -1, -1);
    }

    public List<Registroresultados> findRegistroresultadosEntities(int maxResults, int firstResult) {
        return findRegistroresultadosEntities(false, maxResults, firstResult);
    }

    private List<Registroresultados> findRegistroresultadosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Registroresultados.class));
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

    public Registroresultados findRegistroresultados(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Registroresultados.class, id);
        } finally {
            em.close();
        }
    }

    public int getRegistroresultadosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Registroresultados> rt = cq.from(Registroresultados.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
