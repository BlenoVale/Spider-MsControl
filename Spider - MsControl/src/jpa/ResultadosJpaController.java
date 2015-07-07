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
import model.Registroresultados;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;
import model.Resultados;

/**
 *
 * @author Paulo
 */
public class ResultadosJpaController implements Serializable {

    public ResultadosJpaController(EntityManagerFactory emf)
    {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager()
    {
        return emf.createEntityManager();
    }

    public void create(Resultados resultados)
    {
        if (resultados.getRegistroresultadosList() == null) {
            resultados.setRegistroresultadosList(new ArrayList<Registroresultados>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Analise analiseid = resultados.getAnaliseid();
            if (analiseid != null) {
                analiseid = em.getReference(analiseid.getClass(), analiseid.getId());
                resultados.setAnaliseid(analiseid);
            }
            List<Registroresultados> attachedRegistroresultadosList = new ArrayList<Registroresultados>();
            for (Registroresultados registroresultadosListRegistroresultadosToAttach : resultados.getRegistroresultadosList()) {
                registroresultadosListRegistroresultadosToAttach = em.getReference(registroresultadosListRegistroresultadosToAttach.getClass(), registroresultadosListRegistroresultadosToAttach.getId());
                attachedRegistroresultadosList.add(registroresultadosListRegistroresultadosToAttach);
            }
            resultados.setRegistroresultadosList(attachedRegistroresultadosList);
            em.persist(resultados);
            if (analiseid != null) {
                analiseid.getResultadosList().add(resultados);
                analiseid = em.merge(analiseid);
            }
            for (Registroresultados registroresultadosListRegistroresultados : resultados.getRegistroresultadosList()) {
                Resultados oldResultadosidOfRegistroresultadosListRegistroresultados = registroresultadosListRegistroresultados.getResultadosid();
                registroresultadosListRegistroresultados.setResultadosid(resultados);
                registroresultadosListRegistroresultados = em.merge(registroresultadosListRegistroresultados);
                if (oldResultadosidOfRegistroresultadosListRegistroresultados != null) {
                    oldResultadosidOfRegistroresultadosListRegistroresultados.getRegistroresultadosList().remove(registroresultadosListRegistroresultados);
                    oldResultadosidOfRegistroresultadosListRegistroresultados = em.merge(oldResultadosidOfRegistroresultadosListRegistroresultados);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Resultados resultados) throws IllegalOrphanException, NonexistentEntityException, Exception
    {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Resultados persistentResultados = em.find(Resultados.class, resultados.getId());
            Analise analiseidOld = persistentResultados.getAnaliseid();
            Analise analiseidNew = resultados.getAnaliseid();
            List<Registroresultados> registroresultadosListOld = persistentResultados.getRegistroresultadosList();
            List<Registroresultados> registroresultadosListNew = resultados.getRegistroresultadosList();
            List<String> illegalOrphanMessages = null;
            for (Registroresultados registroresultadosListOldRegistroresultados : registroresultadosListOld) {
                if (!registroresultadosListNew.contains(registroresultadosListOldRegistroresultados)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Registroresultados " + registroresultadosListOldRegistroresultados + " since its resultadosid field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (analiseidNew != null) {
                analiseidNew = em.getReference(analiseidNew.getClass(), analiseidNew.getId());
                resultados.setAnaliseid(analiseidNew);
            }
            List<Registroresultados> attachedRegistroresultadosListNew = new ArrayList<Registroresultados>();
            for (Registroresultados registroresultadosListNewRegistroresultadosToAttach : registroresultadosListNew) {
                registroresultadosListNewRegistroresultadosToAttach = em.getReference(registroresultadosListNewRegistroresultadosToAttach.getClass(), registroresultadosListNewRegistroresultadosToAttach.getId());
                attachedRegistroresultadosListNew.add(registroresultadosListNewRegistroresultadosToAttach);
            }
            registroresultadosListNew = attachedRegistroresultadosListNew;
            resultados.setRegistroresultadosList(registroresultadosListNew);
            resultados = em.merge(resultados);
            if (analiseidOld != null && !analiseidOld.equals(analiseidNew)) {
                analiseidOld.getResultadosList().remove(resultados);
                analiseidOld = em.merge(analiseidOld);
            }
            if (analiseidNew != null && !analiseidNew.equals(analiseidOld)) {
                analiseidNew.getResultadosList().add(resultados);
                analiseidNew = em.merge(analiseidNew);
            }
            for (Registroresultados registroresultadosListNewRegistroresultados : registroresultadosListNew) {
                if (!registroresultadosListOld.contains(registroresultadosListNewRegistroresultados)) {
                    Resultados oldResultadosidOfRegistroresultadosListNewRegistroresultados = registroresultadosListNewRegistroresultados.getResultadosid();
                    registroresultadosListNewRegistroresultados.setResultadosid(resultados);
                    registroresultadosListNewRegistroresultados = em.merge(registroresultadosListNewRegistroresultados);
                    if (oldResultadosidOfRegistroresultadosListNewRegistroresultados != null && !oldResultadosidOfRegistroresultadosListNewRegistroresultados.equals(resultados)) {
                        oldResultadosidOfRegistroresultadosListNewRegistroresultados.getRegistroresultadosList().remove(registroresultadosListNewRegistroresultados);
                        oldResultadosidOfRegistroresultadosListNewRegistroresultados = em.merge(oldResultadosidOfRegistroresultadosListNewRegistroresultados);
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

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException
    {
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
            List<Registroresultados> registroresultadosListOrphanCheck = resultados.getRegistroresultadosList();
            for (Registroresultados registroresultadosListOrphanCheckRegistroresultados : registroresultadosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Resultados (" + resultados + ") cannot be destroyed since the Registroresultados " + registroresultadosListOrphanCheckRegistroresultados + " in its registroresultadosList field has a non-nullable resultadosid field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Analise analiseid = resultados.getAnaliseid();
            if (analiseid != null) {
                analiseid.getResultadosList().remove(resultados);
                analiseid = em.merge(analiseid);
            }
            em.remove(resultados);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Resultados> findResultadosEntities()
    {
        return findResultadosEntities(true, -1, -1);
    }

    public List<Resultados> findResultadosEntities(int maxResults, int firstResult)
    {
        return findResultadosEntities(false, maxResults, firstResult);
    }

    private List<Resultados> findResultadosEntities(boolean all, int maxResults, int firstResult)
    {
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

    public Resultados findResultados(Integer id)
    {
        EntityManager em = getEntityManager();
        try {
            return em.find(Resultados.class, id);
        } finally {
            em.close();
        }
    }

    public int getResultadosCount()
    {
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
