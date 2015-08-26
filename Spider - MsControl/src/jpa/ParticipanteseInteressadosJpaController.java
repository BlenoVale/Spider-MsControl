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
import model.ParticipanteseInteressados;
import model.Resultados;

/**
 *
 * @author Bleno Vale
 */
public class ParticipanteseInteressadosJpaController implements Serializable {

    public ParticipanteseInteressadosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ParticipanteseInteressados participanteseInteressados) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Resultados resultadosid = participanteseInteressados.getResultadosid();
            if (resultadosid != null) {
                resultadosid = em.getReference(resultadosid.getClass(), resultadosid.getId());
                participanteseInteressados.setResultadosid(resultadosid);
            }
            em.persist(participanteseInteressados);
            if (resultadosid != null) {
                resultadosid.getParticipanteseInteressadosList().add(participanteseInteressados);
                resultadosid = em.merge(resultadosid);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ParticipanteseInteressados participanteseInteressados) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ParticipanteseInteressados persistentParticipanteseInteressados = em.find(ParticipanteseInteressados.class, participanteseInteressados.getId());
            Resultados resultadosidOld = persistentParticipanteseInteressados.getResultadosid();
            Resultados resultadosidNew = participanteseInteressados.getResultadosid();
            if (resultadosidNew != null) {
                resultadosidNew = em.getReference(resultadosidNew.getClass(), resultadosidNew.getId());
                participanteseInteressados.setResultadosid(resultadosidNew);
            }
            participanteseInteressados = em.merge(participanteseInteressados);
            if (resultadosidOld != null && !resultadosidOld.equals(resultadosidNew)) {
                resultadosidOld.getParticipanteseInteressadosList().remove(participanteseInteressados);
                resultadosidOld = em.merge(resultadosidOld);
            }
            if (resultadosidNew != null && !resultadosidNew.equals(resultadosidOld)) {
                resultadosidNew.getParticipanteseInteressadosList().add(participanteseInteressados);
                resultadosidNew = em.merge(resultadosidNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = participanteseInteressados.getId();
                if (findParticipanteseInteressados(id) == null) {
                    throw new NonexistentEntityException("The participanteseInteressados with id " + id + " no longer exists.");
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
            ParticipanteseInteressados participanteseInteressados;
            try {
                participanteseInteressados = em.getReference(ParticipanteseInteressados.class, id);
                participanteseInteressados.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The participanteseInteressados with id " + id + " no longer exists.", enfe);
            }
            Resultados resultadosid = participanteseInteressados.getResultadosid();
            if (resultadosid != null) {
                resultadosid.getParticipanteseInteressadosList().remove(participanteseInteressados);
                resultadosid = em.merge(resultadosid);
            }
            em.remove(participanteseInteressados);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ParticipanteseInteressados> findParticipanteseInteressadosEntities() {
        return findParticipanteseInteressadosEntities(true, -1, -1);
    }

    public List<ParticipanteseInteressados> findParticipanteseInteressadosEntities(int maxResults, int firstResult) {
        return findParticipanteseInteressadosEntities(false, maxResults, firstResult);
    }

    private List<ParticipanteseInteressados> findParticipanteseInteressadosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ParticipanteseInteressados.class));
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

    public ParticipanteseInteressados findParticipanteseInteressados(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ParticipanteseInteressados.class, id);
        } finally {
            em.close();
        }
    }

    public int getParticipanteseInteressadosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ParticipanteseInteressados> rt = cq.from(ParticipanteseInteressados.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
