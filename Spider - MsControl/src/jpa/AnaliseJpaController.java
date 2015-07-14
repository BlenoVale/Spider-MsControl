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
import model.Indicador;
import model.Resultados;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;
import model.Analise;
import model.Registroanalise;

/**
 *
 * @author paulosouza
 */
public class AnaliseJpaController implements Serializable {

    public AnaliseJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Analise analise) {
        if (analise.getResultadosList() == null) {
            analise.setResultadosList(new ArrayList<Resultados>());
        }
        if (analise.getRegistroanaliseList() == null) {
            analise.setRegistroanaliseList(new ArrayList<Registroanalise>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Indicador indicadorid = analise.getIndicadorid();
            if (indicadorid != null) {
                indicadorid = em.getReference(indicadorid.getClass(), indicadorid.getId());
                analise.setIndicadorid(indicadorid);
            }
            List<Resultados> attachedResultadosList = new ArrayList<Resultados>();
            for (Resultados resultadosListResultadosToAttach : analise.getResultadosList()) {
                resultadosListResultadosToAttach = em.getReference(resultadosListResultadosToAttach.getClass(), resultadosListResultadosToAttach.getId());
                attachedResultadosList.add(resultadosListResultadosToAttach);
            }
            analise.setResultadosList(attachedResultadosList);
            List<Registroanalise> attachedRegistroanaliseList = new ArrayList<Registroanalise>();
            for (Registroanalise registroanaliseListRegistroanaliseToAttach : analise.getRegistroanaliseList()) {
                registroanaliseListRegistroanaliseToAttach = em.getReference(registroanaliseListRegistroanaliseToAttach.getClass(), registroanaliseListRegistroanaliseToAttach.getId());
                attachedRegistroanaliseList.add(registroanaliseListRegistroanaliseToAttach);
            }
            analise.setRegistroanaliseList(attachedRegistroanaliseList);
            em.persist(analise);
            if (indicadorid != null) {
                indicadorid.getAnaliseList().add(analise);
                indicadorid = em.merge(indicadorid);
            }
            for (Resultados resultadosListResultados : analise.getResultadosList()) {
                Analise oldAnaliseidOfResultadosListResultados = resultadosListResultados.getAnaliseid();
                resultadosListResultados.setAnaliseid(analise);
                resultadosListResultados = em.merge(resultadosListResultados);
                if (oldAnaliseidOfResultadosListResultados != null) {
                    oldAnaliseidOfResultadosListResultados.getResultadosList().remove(resultadosListResultados);
                    oldAnaliseidOfResultadosListResultados = em.merge(oldAnaliseidOfResultadosListResultados);
                }
            }
            for (Registroanalise registroanaliseListRegistroanalise : analise.getRegistroanaliseList()) {
                Analise oldAnaliseidOfRegistroanaliseListRegistroanalise = registroanaliseListRegistroanalise.getAnaliseid();
                registroanaliseListRegistroanalise.setAnaliseid(analise);
                registroanaliseListRegistroanalise = em.merge(registroanaliseListRegistroanalise);
                if (oldAnaliseidOfRegistroanaliseListRegistroanalise != null) {
                    oldAnaliseidOfRegistroanaliseListRegistroanalise.getRegistroanaliseList().remove(registroanaliseListRegistroanalise);
                    oldAnaliseidOfRegistroanaliseListRegistroanalise = em.merge(oldAnaliseidOfRegistroanaliseListRegistroanalise);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Analise analise) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Analise persistentAnalise = em.find(Analise.class, analise.getId());
            Indicador indicadoridOld = persistentAnalise.getIndicadorid();
            Indicador indicadoridNew = analise.getIndicadorid();
            List<Resultados> resultadosListOld = persistentAnalise.getResultadosList();
            List<Resultados> resultadosListNew = analise.getResultadosList();
            List<Registroanalise> registroanaliseListOld = persistentAnalise.getRegistroanaliseList();
            List<Registroanalise> registroanaliseListNew = analise.getRegistroanaliseList();
            List<String> illegalOrphanMessages = null;
            for (Resultados resultadosListOldResultados : resultadosListOld) {
                if (!resultadosListNew.contains(resultadosListOldResultados)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Resultados " + resultadosListOldResultados + " since its analiseid field is not nullable.");
                }
            }
            for (Registroanalise registroanaliseListOldRegistroanalise : registroanaliseListOld) {
                if (!registroanaliseListNew.contains(registroanaliseListOldRegistroanalise)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Registroanalise " + registroanaliseListOldRegistroanalise + " since its analiseid field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (indicadoridNew != null) {
                indicadoridNew = em.getReference(indicadoridNew.getClass(), indicadoridNew.getId());
                analise.setIndicadorid(indicadoridNew);
            }
            List<Resultados> attachedResultadosListNew = new ArrayList<Resultados>();
            for (Resultados resultadosListNewResultadosToAttach : resultadosListNew) {
                resultadosListNewResultadosToAttach = em.getReference(resultadosListNewResultadosToAttach.getClass(), resultadosListNewResultadosToAttach.getId());
                attachedResultadosListNew.add(resultadosListNewResultadosToAttach);
            }
            resultadosListNew = attachedResultadosListNew;
            analise.setResultadosList(resultadosListNew);
            List<Registroanalise> attachedRegistroanaliseListNew = new ArrayList<Registroanalise>();
            for (Registroanalise registroanaliseListNewRegistroanaliseToAttach : registroanaliseListNew) {
                registroanaliseListNewRegistroanaliseToAttach = em.getReference(registroanaliseListNewRegistroanaliseToAttach.getClass(), registroanaliseListNewRegistroanaliseToAttach.getId());
                attachedRegistroanaliseListNew.add(registroanaliseListNewRegistroanaliseToAttach);
            }
            registroanaliseListNew = attachedRegistroanaliseListNew;
            analise.setRegistroanaliseList(registroanaliseListNew);
            analise = em.merge(analise);
            if (indicadoridOld != null && !indicadoridOld.equals(indicadoridNew)) {
                indicadoridOld.getAnaliseList().remove(analise);
                indicadoridOld = em.merge(indicadoridOld);
            }
            if (indicadoridNew != null && !indicadoridNew.equals(indicadoridOld)) {
                indicadoridNew.getAnaliseList().add(analise);
                indicadoridNew = em.merge(indicadoridNew);
            }
            for (Resultados resultadosListNewResultados : resultadosListNew) {
                if (!resultadosListOld.contains(resultadosListNewResultados)) {
                    Analise oldAnaliseidOfResultadosListNewResultados = resultadosListNewResultados.getAnaliseid();
                    resultadosListNewResultados.setAnaliseid(analise);
                    resultadosListNewResultados = em.merge(resultadosListNewResultados);
                    if (oldAnaliseidOfResultadosListNewResultados != null && !oldAnaliseidOfResultadosListNewResultados.equals(analise)) {
                        oldAnaliseidOfResultadosListNewResultados.getResultadosList().remove(resultadosListNewResultados);
                        oldAnaliseidOfResultadosListNewResultados = em.merge(oldAnaliseidOfResultadosListNewResultados);
                    }
                }
            }
            for (Registroanalise registroanaliseListNewRegistroanalise : registroanaliseListNew) {
                if (!registroanaliseListOld.contains(registroanaliseListNewRegistroanalise)) {
                    Analise oldAnaliseidOfRegistroanaliseListNewRegistroanalise = registroanaliseListNewRegistroanalise.getAnaliseid();
                    registroanaliseListNewRegistroanalise.setAnaliseid(analise);
                    registroanaliseListNewRegistroanalise = em.merge(registroanaliseListNewRegistroanalise);
                    if (oldAnaliseidOfRegistroanaliseListNewRegistroanalise != null && !oldAnaliseidOfRegistroanaliseListNewRegistroanalise.equals(analise)) {
                        oldAnaliseidOfRegistroanaliseListNewRegistroanalise.getRegistroanaliseList().remove(registroanaliseListNewRegistroanalise);
                        oldAnaliseidOfRegistroanaliseListNewRegistroanalise = em.merge(oldAnaliseidOfRegistroanaliseListNewRegistroanalise);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = analise.getId();
                if (findAnalise(id) == null) {
                    throw new NonexistentEntityException("The analise with id " + id + " no longer exists.");
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
            Analise analise;
            try {
                analise = em.getReference(Analise.class, id);
                analise.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The analise with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Resultados> resultadosListOrphanCheck = analise.getResultadosList();
            for (Resultados resultadosListOrphanCheckResultados : resultadosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Analise (" + analise + ") cannot be destroyed since the Resultados " + resultadosListOrphanCheckResultados + " in its resultadosList field has a non-nullable analiseid field.");
            }
            List<Registroanalise> registroanaliseListOrphanCheck = analise.getRegistroanaliseList();
            for (Registroanalise registroanaliseListOrphanCheckRegistroanalise : registroanaliseListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Analise (" + analise + ") cannot be destroyed since the Registroanalise " + registroanaliseListOrphanCheckRegistroanalise + " in its registroanaliseList field has a non-nullable analiseid field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Indicador indicadorid = analise.getIndicadorid();
            if (indicadorid != null) {
                indicadorid.getAnaliseList().remove(analise);
                indicadorid = em.merge(indicadorid);
            }
            em.remove(analise);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Analise> findAnaliseEntities() {
        return findAnaliseEntities(true, -1, -1);
    }

    public List<Analise> findAnaliseEntities(int maxResults, int firstResult) {
        return findAnaliseEntities(false, maxResults, firstResult);
    }

    private List<Analise> findAnaliseEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Analise.class));
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

    public Analise findAnalise(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Analise.class, id);
        } finally {
            em.close();
        }
    }

    public int getAnaliseCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Analise> rt = cq.from(Analise.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
