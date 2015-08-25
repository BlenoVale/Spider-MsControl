/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.Analise;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;
import model.Resultados;

/**
 *
 * @author Bleno Vale
 */
public class ResultadosJpaController implements Serializable {

    public ResultadosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Resultados resultados) {
        if (resultados.getAnaliseList() == null) {
            resultados.setAnaliseList(new ArrayList<Analise>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Analise> attachedAnaliseList = new ArrayList<Analise>();
            for (Analise analiseListAnaliseToAttach : resultados.getAnaliseList()) {
                analiseListAnaliseToAttach = em.getReference(analiseListAnaliseToAttach.getClass(), analiseListAnaliseToAttach.getId());
                attachedAnaliseList.add(analiseListAnaliseToAttach);
            }
            resultados.setAnaliseList(attachedAnaliseList);
            em.persist(resultados);
            for (Analise analiseListAnalise : resultados.getAnaliseList()) {
                analiseListAnalise.getResultadosList().add(resultados);
                analiseListAnalise = em.merge(analiseListAnalise);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Resultados resultados) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Resultados persistentResultados = em.find(Resultados.class, resultados.getId());
            List<Analise> analiseListOld = persistentResultados.getAnaliseList();
            List<Analise> analiseListNew = resultados.getAnaliseList();
            List<Analise> attachedAnaliseListNew = new ArrayList<Analise>();
            for (Analise analiseListNewAnaliseToAttach : analiseListNew) {
                analiseListNewAnaliseToAttach = em.getReference(analiseListNewAnaliseToAttach.getClass(), analiseListNewAnaliseToAttach.getId());
                attachedAnaliseListNew.add(analiseListNewAnaliseToAttach);
            }
            analiseListNew = attachedAnaliseListNew;
            resultados.setAnaliseList(analiseListNew);
            resultados = em.merge(resultados);
            for (Analise analiseListOldAnalise : analiseListOld) {
                if (!analiseListNew.contains(analiseListOldAnalise)) {
                    analiseListOldAnalise.getResultadosList().remove(resultados);
                    analiseListOldAnalise = em.merge(analiseListOldAnalise);
                }
            }
            for (Analise analiseListNewAnalise : analiseListNew) {
                if (!analiseListOld.contains(analiseListNewAnalise)) {
                    analiseListNewAnalise.getResultadosList().add(resultados);
                    analiseListNewAnalise = em.merge(analiseListNewAnalise);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = resultados.getId();
                if (findResultados(id) == null) {
                    throw new NonexistentEntityException("The resultados with id " + id + " no longer exists.");
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
            Resultados resultados;
            try {
                resultados = em.getReference(Resultados.class, id);
                resultados.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The resultados with id " + id + " no longer exists.", enfe);
            }
            List<Analise> analiseList = resultados.getAnaliseList();
            for (Analise analiseListAnalise : analiseList) {
                analiseListAnalise.getResultadosList().remove(resultados);
                analiseListAnalise = em.merge(analiseListAnalise);
            }
            em.remove(resultados);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Resultados> findResultadosEntities() {
        return findResultadosEntities(true, -1, -1);
    }

    public List<Resultados> findResultadosEntities(int maxResults, int firstResult) {
        return findResultadosEntities(false, maxResults, firstResult);
    }

    private List<Resultados> findResultadosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Resultados.class));
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

    public Resultados findResultados(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Resultados.class, id);
        } finally {
            em.close();
        }
    }

    public int getResultadosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Resultados> rt = cq.from(Resultados.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
