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
import model.Procedimentodeanalise;

/**
 *
 * @author Dan
 */
public class ProcedimentodeanaliseJpaController implements Serializable {

    public ProcedimentodeanaliseJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Procedimentodeanalise procedimentodeanalise) {
        if (procedimentodeanalise.getColetaList() == null) {
            procedimentodeanalise.setColetaList(new ArrayList<Coleta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Coleta> attachedColetaList = new ArrayList<Coleta>();
            for (Coleta coletaListColetaToAttach : procedimentodeanalise.getColetaList()) {
                coletaListColetaToAttach = em.getReference(coletaListColetaToAttach.getClass(), coletaListColetaToAttach.getColetaPK());
                attachedColetaList.add(coletaListColetaToAttach);
            }
            procedimentodeanalise.setColetaList(attachedColetaList);
            em.persist(procedimentodeanalise);
            for (Coleta coletaListColeta : procedimentodeanalise.getColetaList()) {
                Procedimentodeanalise oldIdProcedimentoDeAnaliseOfColetaListColeta = coletaListColeta.getIdProcedimentoDeAnalise();
                coletaListColeta.setIdProcedimentoDeAnalise(procedimentodeanalise);
                coletaListColeta = em.merge(coletaListColeta);
                if (oldIdProcedimentoDeAnaliseOfColetaListColeta != null) {
                    oldIdProcedimentoDeAnaliseOfColetaListColeta.getColetaList().remove(coletaListColeta);
                    oldIdProcedimentoDeAnaliseOfColetaListColeta = em.merge(oldIdProcedimentoDeAnaliseOfColetaListColeta);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Procedimentodeanalise procedimentodeanalise) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Procedimentodeanalise persistentProcedimentodeanalise = em.find(Procedimentodeanalise.class, procedimentodeanalise.getId());
            List<Coleta> coletaListOld = persistentProcedimentodeanalise.getColetaList();
            List<Coleta> coletaListNew = procedimentodeanalise.getColetaList();
            List<String> illegalOrphanMessages = null;
            for (Coleta coletaListOldColeta : coletaListOld) {
                if (!coletaListNew.contains(coletaListOldColeta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Coleta " + coletaListOldColeta + " since its idProcedimentoDeAnalise field is not nullable.");
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
            procedimentodeanalise.setColetaList(coletaListNew);
            procedimentodeanalise = em.merge(procedimentodeanalise);
            for (Coleta coletaListNewColeta : coletaListNew) {
                if (!coletaListOld.contains(coletaListNewColeta)) {
                    Procedimentodeanalise oldIdProcedimentoDeAnaliseOfColetaListNewColeta = coletaListNewColeta.getIdProcedimentoDeAnalise();
                    coletaListNewColeta.setIdProcedimentoDeAnalise(procedimentodeanalise);
                    coletaListNewColeta = em.merge(coletaListNewColeta);
                    if (oldIdProcedimentoDeAnaliseOfColetaListNewColeta != null && !oldIdProcedimentoDeAnaliseOfColetaListNewColeta.equals(procedimentodeanalise)) {
                        oldIdProcedimentoDeAnaliseOfColetaListNewColeta.getColetaList().remove(coletaListNewColeta);
                        oldIdProcedimentoDeAnaliseOfColetaListNewColeta = em.merge(oldIdProcedimentoDeAnaliseOfColetaListNewColeta);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = procedimentodeanalise.getId();
                if (findProcedimentodeanalise(id) == null) {
                    throw new NonexistentEntityException("The procedimentodeanalise with id " + id + " no longer exists.");
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
            Procedimentodeanalise procedimentodeanalise;
            try {
                procedimentodeanalise = em.getReference(Procedimentodeanalise.class, id);
                procedimentodeanalise.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The procedimentodeanalise with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Coleta> coletaListOrphanCheck = procedimentodeanalise.getColetaList();
            for (Coleta coletaListOrphanCheckColeta : coletaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Procedimentodeanalise (" + procedimentodeanalise + ") cannot be destroyed since the Coleta " + coletaListOrphanCheckColeta + " in its coletaList field has a non-nullable idProcedimentoDeAnalise field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(procedimentodeanalise);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Procedimentodeanalise> findProcedimentodeanaliseEntities() {
        return findProcedimentodeanaliseEntities(true, -1, -1);
    }

    public List<Procedimentodeanalise> findProcedimentodeanaliseEntities(int maxResults, int firstResult) {
        return findProcedimentodeanaliseEntities(false, maxResults, firstResult);
    }

    private List<Procedimentodeanalise> findProcedimentodeanaliseEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Procedimentodeanalise.class));
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

    public Procedimentodeanalise findProcedimentodeanalise(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Procedimentodeanalise.class, id);
        } finally {
            em.close();
        }
    }

    public int getProcedimentodeanaliseCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Procedimentodeanalise> rt = cq.from(Procedimentodeanalise.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
