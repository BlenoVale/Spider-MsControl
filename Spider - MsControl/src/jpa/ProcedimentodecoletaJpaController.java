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
import model.Coleta;
import model.Registroprocedimentocoleta;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;
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
        if (procedimentodecoleta.getRegistroprocedimentocoletaList() == null) {
            procedimentodecoleta.setRegistroprocedimentocoletaList(new ArrayList<Registroprocedimentocoleta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Coleta coleta = procedimentodecoleta.getColeta();
            if (coleta != null) {
                coleta = em.getReference(coleta.getClass(), coleta.getColetaPK());
                procedimentodecoleta.setColeta(coleta);
            }
            List<Registroprocedimentocoleta> attachedRegistroprocedimentocoletaList = new ArrayList<Registroprocedimentocoleta>();
            for (Registroprocedimentocoleta registroprocedimentocoletaListRegistroprocedimentocoletaToAttach : procedimentodecoleta.getRegistroprocedimentocoletaList()) {
                registroprocedimentocoletaListRegistroprocedimentocoletaToAttach = em.getReference(registroprocedimentocoletaListRegistroprocedimentocoletaToAttach.getClass(), registroprocedimentocoletaListRegistroprocedimentocoletaToAttach.getRegistroprocedimentocoletaPK());
                attachedRegistroprocedimentocoletaList.add(registroprocedimentocoletaListRegistroprocedimentocoletaToAttach);
            }
            procedimentodecoleta.setRegistroprocedimentocoletaList(attachedRegistroprocedimentocoletaList);
            em.persist(procedimentodecoleta);
            if (coleta != null) {
                coleta.getProcedimentodecoletaList().add(procedimentodecoleta);
                coleta = em.merge(coleta);
            }
            for (Registroprocedimentocoleta registroprocedimentocoletaListRegistroprocedimentocoleta : procedimentodecoleta.getRegistroprocedimentocoletaList()) {
                Procedimentodecoleta oldProcedimentodecoletaOfRegistroprocedimentocoletaListRegistroprocedimentocoleta = registroprocedimentocoletaListRegistroprocedimentocoleta.getProcedimentodecoleta();
                registroprocedimentocoletaListRegistroprocedimentocoleta.setProcedimentodecoleta(procedimentodecoleta);
                registroprocedimentocoletaListRegistroprocedimentocoleta = em.merge(registroprocedimentocoletaListRegistroprocedimentocoleta);
                if (oldProcedimentodecoletaOfRegistroprocedimentocoletaListRegistroprocedimentocoleta != null) {
                    oldProcedimentodecoletaOfRegistroprocedimentocoletaListRegistroprocedimentocoleta.getRegistroprocedimentocoletaList().remove(registroprocedimentocoletaListRegistroprocedimentocoleta);
                    oldProcedimentodecoletaOfRegistroprocedimentocoletaListRegistroprocedimentocoleta = em.merge(oldProcedimentodecoletaOfRegistroprocedimentocoletaListRegistroprocedimentocoleta);
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
            Coleta coletaOld = persistentProcedimentodecoleta.getColeta();
            Coleta coletaNew = procedimentodecoleta.getColeta();
            List<Registroprocedimentocoleta> registroprocedimentocoletaListOld = persistentProcedimentodecoleta.getRegistroprocedimentocoletaList();
            List<Registroprocedimentocoleta> registroprocedimentocoletaListNew = procedimentodecoleta.getRegistroprocedimentocoletaList();
            List<String> illegalOrphanMessages = null;
            for (Registroprocedimentocoleta registroprocedimentocoletaListOldRegistroprocedimentocoleta : registroprocedimentocoletaListOld) {
                if (!registroprocedimentocoletaListNew.contains(registroprocedimentocoletaListOldRegistroprocedimentocoleta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Registroprocedimentocoleta " + registroprocedimentocoletaListOldRegistroprocedimentocoleta + " since its procedimentodecoleta field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (coletaNew != null) {
                coletaNew = em.getReference(coletaNew.getClass(), coletaNew.getColetaPK());
                procedimentodecoleta.setColeta(coletaNew);
            }
            List<Registroprocedimentocoleta> attachedRegistroprocedimentocoletaListNew = new ArrayList<Registroprocedimentocoleta>();
            for (Registroprocedimentocoleta registroprocedimentocoletaListNewRegistroprocedimentocoletaToAttach : registroprocedimentocoletaListNew) {
                registroprocedimentocoletaListNewRegistroprocedimentocoletaToAttach = em.getReference(registroprocedimentocoletaListNewRegistroprocedimentocoletaToAttach.getClass(), registroprocedimentocoletaListNewRegistroprocedimentocoletaToAttach.getRegistroprocedimentocoletaPK());
                attachedRegistroprocedimentocoletaListNew.add(registroprocedimentocoletaListNewRegistroprocedimentocoletaToAttach);
            }
            registroprocedimentocoletaListNew = attachedRegistroprocedimentocoletaListNew;
            procedimentodecoleta.setRegistroprocedimentocoletaList(registroprocedimentocoletaListNew);
            procedimentodecoleta = em.merge(procedimentodecoleta);
            if (coletaOld != null && !coletaOld.equals(coletaNew)) {
                coletaOld.getProcedimentodecoletaList().remove(procedimentodecoleta);
                coletaOld = em.merge(coletaOld);
            }
            if (coletaNew != null && !coletaNew.equals(coletaOld)) {
                coletaNew.getProcedimentodecoletaList().add(procedimentodecoleta);
                coletaNew = em.merge(coletaNew);
            }
            for (Registroprocedimentocoleta registroprocedimentocoletaListNewRegistroprocedimentocoleta : registroprocedimentocoletaListNew) {
                if (!registroprocedimentocoletaListOld.contains(registroprocedimentocoletaListNewRegistroprocedimentocoleta)) {
                    Procedimentodecoleta oldProcedimentodecoletaOfRegistroprocedimentocoletaListNewRegistroprocedimentocoleta = registroprocedimentocoletaListNewRegistroprocedimentocoleta.getProcedimentodecoleta();
                    registroprocedimentocoletaListNewRegistroprocedimentocoleta.setProcedimentodecoleta(procedimentodecoleta);
                    registroprocedimentocoletaListNewRegistroprocedimentocoleta = em.merge(registroprocedimentocoletaListNewRegistroprocedimentocoleta);
                    if (oldProcedimentodecoletaOfRegistroprocedimentocoletaListNewRegistroprocedimentocoleta != null && !oldProcedimentodecoletaOfRegistroprocedimentocoletaListNewRegistroprocedimentocoleta.equals(procedimentodecoleta)) {
                        oldProcedimentodecoletaOfRegistroprocedimentocoletaListNewRegistroprocedimentocoleta.getRegistroprocedimentocoletaList().remove(registroprocedimentocoletaListNewRegistroprocedimentocoleta);
                        oldProcedimentodecoletaOfRegistroprocedimentocoletaListNewRegistroprocedimentocoleta = em.merge(oldProcedimentodecoletaOfRegistroprocedimentocoletaListNewRegistroprocedimentocoleta);
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
            List<Registroprocedimentocoleta> registroprocedimentocoletaListOrphanCheck = procedimentodecoleta.getRegistroprocedimentocoletaList();
            for (Registroprocedimentocoleta registroprocedimentocoletaListOrphanCheckRegistroprocedimentocoleta : registroprocedimentocoletaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Procedimentodecoleta (" + procedimentodecoleta + ") cannot be destroyed since the Registroprocedimentocoleta " + registroprocedimentocoletaListOrphanCheckRegistroprocedimentocoleta + " in its registroprocedimentocoletaList field has a non-nullable procedimentodecoleta field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Coleta coleta = procedimentodecoleta.getColeta();
            if (coleta != null) {
                coleta.getProcedimentodecoletaList().remove(procedimentodecoleta);
                coleta = em.merge(coleta);
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
