/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.Coleta;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.Procedimentodecoleta;

/**
 *
 * @author Dan
 */
public class ProcedimentodecoletaJpaController implements Serializable {

    public ProcedimentodecoletaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Procedimentodecoleta procedimentodecoleta) {
        if (procedimentodecoleta.getColetaList() == null) {
            procedimentodecoleta.setColetaList(new ArrayList<Coleta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Coleta> attachedColetaList = new ArrayList<Coleta>();
            for (Coleta coletaListColetaToAttach : procedimentodecoleta.getColetaList()) {
                coletaListColetaToAttach = em.getReference(coletaListColetaToAttach.getClass(), coletaListColetaToAttach.getColetaPK());
                attachedColetaList.add(coletaListColetaToAttach);
            }
            procedimentodecoleta.setColetaList(attachedColetaList);
            em.persist(procedimentodecoleta);
            for (Coleta coletaListColeta : procedimentodecoleta.getColetaList()) {
                Procedimentodecoleta oldIdProcedimentoDeColetaOfColetaListColeta = coletaListColeta.getIdProcedimentoDeColeta();
                coletaListColeta.setIdProcedimentoDeColeta(procedimentodecoleta);
                coletaListColeta = em.merge(coletaListColeta);
                if (oldIdProcedimentoDeColetaOfColetaListColeta != null) {
                    oldIdProcedimentoDeColetaOfColetaListColeta.getColetaList().remove(coletaListColeta);
                    oldIdProcedimentoDeColetaOfColetaListColeta = em.merge(oldIdProcedimentoDeColetaOfColetaListColeta);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Procedimentodecoleta procedimentodecoleta) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Procedimentodecoleta persistentProcedimentodecoleta = em.find(Procedimentodecoleta.class, procedimentodecoleta.getId());
            List<Coleta> coletaListOld = persistentProcedimentodecoleta.getColetaList();
            List<Coleta> coletaListNew = procedimentodecoleta.getColetaList();
            List<String> illegalOrphanMessages = null;
            for (Coleta coletaListOldColeta : coletaListOld) {
                if (!coletaListNew.contains(coletaListOldColeta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Coleta " + coletaListOldColeta + " since its idProcedimentoDeColeta field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Coleta> attachedColetaListNew = new ArrayList<Coleta>();
            for (Coleta coletaListNewColetaToAttach : coletaListNew) {
                coletaListNewColetaToAttach = em.getReference(coletaListNewColetaToAttach.getClass(), coletaListNewColetaToAttach.getColetaPK());
                attachedColetaListNew.add(coletaListNewColetaToAttach);
            }
            coletaListNew = attachedColetaListNew;
            procedimentodecoleta.setColetaList(coletaListNew);
            procedimentodecoleta = em.merge(procedimentodecoleta);
            for (Coleta coletaListNewColeta : coletaListNew) {
                if (!coletaListOld.contains(coletaListNewColeta)) {
                    Procedimentodecoleta oldIdProcedimentoDeColetaOfColetaListNewColeta = coletaListNewColeta.getIdProcedimentoDeColeta();
                    coletaListNewColeta.setIdProcedimentoDeColeta(procedimentodecoleta);
                    coletaListNewColeta = em.merge(coletaListNewColeta);
                    if (oldIdProcedimentoDeColetaOfColetaListNewColeta != null && !oldIdProcedimentoDeColetaOfColetaListNewColeta.equals(procedimentodecoleta)) {
                        oldIdProcedimentoDeColetaOfColetaListNewColeta.getColetaList().remove(coletaListNewColeta);
                        oldIdProcedimentoDeColetaOfColetaListNewColeta = em.merge(oldIdProcedimentoDeColetaOfColetaListNewColeta);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = procedimentodecoleta.getId();
                if (findProcedimentodecoleta(id) == null) {
                    throw new NonexistentEntityException("The procedimentodecoleta with id " + id + " no longer exists.");
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
            Procedimentodecoleta procedimentodecoleta;
            try {
                procedimentodecoleta = em.getReference(Procedimentodecoleta.class, id);
                procedimentodecoleta.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The procedimentodecoleta with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Coleta> coletaListOrphanCheck = procedimentodecoleta.getColetaList();
            for (Coleta coletaListOrphanCheckColeta : coletaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Procedimentodecoleta (" + procedimentodecoleta + ") cannot be destroyed since the Coleta " + coletaListOrphanCheckColeta + " in its coletaList field has a non-nullable idProcedimentoDeColeta field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(procedimentodecoleta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Procedimentodecoleta> findProcedimentodecoletaEntities() {
        return findProcedimentodecoletaEntities(true, -1, -1);
    }

    public List<Procedimentodecoleta> findProcedimentodecoletaEntities(int maxResults, int firstResult) {
        return findProcedimentodecoletaEntities(false, maxResults, firstResult);
    }

    private List<Procedimentodecoleta> findProcedimentodecoletaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Procedimentodecoleta.class));
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

    public Procedimentodecoleta findProcedimentodecoleta(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Procedimentodecoleta.class, id);
        } finally {
            em.close();
        }
    }

    public int getProcedimentodecoletaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Procedimentodecoleta> rt = cq.from(Procedimentodecoleta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
