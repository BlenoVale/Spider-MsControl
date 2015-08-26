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
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;
import model.ParticipanteseInteressados;
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
        if (resultados.getParticipanteseInteressadosList() == null) {
            resultados.setParticipanteseInteressadosList(new ArrayList<ParticipanteseInteressados>());
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
            List<ParticipanteseInteressados> attachedParticipanteseInteressadosList = new ArrayList<ParticipanteseInteressados>();
            for (ParticipanteseInteressados participanteseInteressadosListParticipanteseInteressadosToAttach : resultados.getParticipanteseInteressadosList()) {
                participanteseInteressadosListParticipanteseInteressadosToAttach = em.getReference(participanteseInteressadosListParticipanteseInteressadosToAttach.getClass(), participanteseInteressadosListParticipanteseInteressadosToAttach.getId());
                attachedParticipanteseInteressadosList.add(participanteseInteressadosListParticipanteseInteressadosToAttach);
            }
            resultados.setParticipanteseInteressadosList(attachedParticipanteseInteressadosList);
            em.persist(resultados);
            for (Analise analiseListAnalise : resultados.getAnaliseList()) {
                analiseListAnalise.getResultadosList().add(resultados);
                analiseListAnalise = em.merge(analiseListAnalise);
            }
            for (ParticipanteseInteressados participanteseInteressadosListParticipanteseInteressados : resultados.getParticipanteseInteressadosList()) {
                Resultados oldResultadosidOfParticipanteseInteressadosListParticipanteseInteressados = participanteseInteressadosListParticipanteseInteressados.getResultadosid();
                participanteseInteressadosListParticipanteseInteressados.setResultadosid(resultados);
                participanteseInteressadosListParticipanteseInteressados = em.merge(participanteseInteressadosListParticipanteseInteressados);
                if (oldResultadosidOfParticipanteseInteressadosListParticipanteseInteressados != null) {
                    oldResultadosidOfParticipanteseInteressadosListParticipanteseInteressados.getParticipanteseInteressadosList().remove(participanteseInteressadosListParticipanteseInteressados);
                    oldResultadosidOfParticipanteseInteressadosListParticipanteseInteressados = em.merge(oldResultadosidOfParticipanteseInteressadosListParticipanteseInteressados);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Resultados resultados) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Resultados persistentResultados = em.find(Resultados.class, resultados.getId());
            List<Analise> analiseListOld = persistentResultados.getAnaliseList();
            List<Analise> analiseListNew = resultados.getAnaliseList();
            List<ParticipanteseInteressados> participanteseInteressadosListOld = persistentResultados.getParticipanteseInteressadosList();
            List<ParticipanteseInteressados> participanteseInteressadosListNew = resultados.getParticipanteseInteressadosList();
            List<String> illegalOrphanMessages = null;
            for (ParticipanteseInteressados participanteseInteressadosListOldParticipanteseInteressados : participanteseInteressadosListOld) {
                if (!participanteseInteressadosListNew.contains(participanteseInteressadosListOldParticipanteseInteressados)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ParticipanteseInteressados " + participanteseInteressadosListOldParticipanteseInteressados + " since its resultadosid field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Analise> attachedAnaliseListNew = new ArrayList<Analise>();
            for (Analise analiseListNewAnaliseToAttach : analiseListNew) {
                analiseListNewAnaliseToAttach = em.getReference(analiseListNewAnaliseToAttach.getClass(), analiseListNewAnaliseToAttach.getId());
                attachedAnaliseListNew.add(analiseListNewAnaliseToAttach);
            }
            analiseListNew = attachedAnaliseListNew;
            resultados.setAnaliseList(analiseListNew);
            List<ParticipanteseInteressados> attachedParticipanteseInteressadosListNew = new ArrayList<ParticipanteseInteressados>();
            for (ParticipanteseInteressados participanteseInteressadosListNewParticipanteseInteressadosToAttach : participanteseInteressadosListNew) {
                participanteseInteressadosListNewParticipanteseInteressadosToAttach = em.getReference(participanteseInteressadosListNewParticipanteseInteressadosToAttach.getClass(), participanteseInteressadosListNewParticipanteseInteressadosToAttach.getId());
                attachedParticipanteseInteressadosListNew.add(participanteseInteressadosListNewParticipanteseInteressadosToAttach);
            }
            participanteseInteressadosListNew = attachedParticipanteseInteressadosListNew;
            resultados.setParticipanteseInteressadosList(participanteseInteressadosListNew);
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
            for (ParticipanteseInteressados participanteseInteressadosListNewParticipanteseInteressados : participanteseInteressadosListNew) {
                if (!participanteseInteressadosListOld.contains(participanteseInteressadosListNewParticipanteseInteressados)) {
                    Resultados oldResultadosidOfParticipanteseInteressadosListNewParticipanteseInteressados = participanteseInteressadosListNewParticipanteseInteressados.getResultadosid();
                    participanteseInteressadosListNewParticipanteseInteressados.setResultadosid(resultados);
                    participanteseInteressadosListNewParticipanteseInteressados = em.merge(participanteseInteressadosListNewParticipanteseInteressados);
                    if (oldResultadosidOfParticipanteseInteressadosListNewParticipanteseInteressados != null && !oldResultadosidOfParticipanteseInteressadosListNewParticipanteseInteressados.equals(resultados)) {
                        oldResultadosidOfParticipanteseInteressadosListNewParticipanteseInteressados.getParticipanteseInteressadosList().remove(participanteseInteressadosListNewParticipanteseInteressados);
                        oldResultadosidOfParticipanteseInteressadosListNewParticipanteseInteressados = em.merge(oldResultadosidOfParticipanteseInteressadosListNewParticipanteseInteressados);
                    }
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

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
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
            List<String> illegalOrphanMessages = null;
            List<ParticipanteseInteressados> participanteseInteressadosListOrphanCheck = resultados.getParticipanteseInteressadosList();
            for (ParticipanteseInteressados participanteseInteressadosListOrphanCheckParticipanteseInteressados : participanteseInteressadosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Resultados (" + resultados + ") cannot be destroyed since the ParticipanteseInteressados " + participanteseInteressadosListOrphanCheckParticipanteseInteressados + " in its participanteseInteressadosList field has a non-nullable resultadosid field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
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
