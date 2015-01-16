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
import model.Registroprocedimentoanalise;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;
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
        if (procedimentodeanalise.getRegistroprocedimentoanaliseList() == null) {
            procedimentodeanalise.setRegistroprocedimentoanaliseList(new ArrayList<Registroprocedimentoanalise>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Coleta coleta = procedimentodeanalise.getColeta();
            if (coleta != null) {
                coleta = em.getReference(coleta.getClass(), coleta.getColetaPK());
                procedimentodeanalise.setColeta(coleta);
            }
            List<Registroprocedimentoanalise> attachedRegistroprocedimentoanaliseList = new ArrayList<Registroprocedimentoanalise>();
            for (Registroprocedimentoanalise registroprocedimentoanaliseListRegistroprocedimentoanaliseToAttach : procedimentodeanalise.getRegistroprocedimentoanaliseList()) {
                registroprocedimentoanaliseListRegistroprocedimentoanaliseToAttach = em.getReference(registroprocedimentoanaliseListRegistroprocedimentoanaliseToAttach.getClass(), registroprocedimentoanaliseListRegistroprocedimentoanaliseToAttach.getRegistroprocedimentoanalisePK());
                attachedRegistroprocedimentoanaliseList.add(registroprocedimentoanaliseListRegistroprocedimentoanaliseToAttach);
            }
            procedimentodeanalise.setRegistroprocedimentoanaliseList(attachedRegistroprocedimentoanaliseList);
            em.persist(procedimentodeanalise);
            if (coleta != null) {
                coleta.getProcedimentodeanaliseList().add(procedimentodeanalise);
                coleta = em.merge(coleta);
            }
            for (Registroprocedimentoanalise registroprocedimentoanaliseListRegistroprocedimentoanalise : procedimentodeanalise.getRegistroprocedimentoanaliseList()) {
                Procedimentodeanalise oldProcedimentodeanaliseOfRegistroprocedimentoanaliseListRegistroprocedimentoanalise = registroprocedimentoanaliseListRegistroprocedimentoanalise.getProcedimentodeanalise();
                registroprocedimentoanaliseListRegistroprocedimentoanalise.setProcedimentodeanalise(procedimentodeanalise);
                registroprocedimentoanaliseListRegistroprocedimentoanalise = em.merge(registroprocedimentoanaliseListRegistroprocedimentoanalise);
                if (oldProcedimentodeanaliseOfRegistroprocedimentoanaliseListRegistroprocedimentoanalise != null) {
                    oldProcedimentodeanaliseOfRegistroprocedimentoanaliseListRegistroprocedimentoanalise.getRegistroprocedimentoanaliseList().remove(registroprocedimentoanaliseListRegistroprocedimentoanalise);
                    oldProcedimentodeanaliseOfRegistroprocedimentoanaliseListRegistroprocedimentoanalise = em.merge(oldProcedimentodeanaliseOfRegistroprocedimentoanaliseListRegistroprocedimentoanalise);
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
            Coleta coletaOld = persistentProcedimentodeanalise.getColeta();
            Coleta coletaNew = procedimentodeanalise.getColeta();
            List<Registroprocedimentoanalise> registroprocedimentoanaliseListOld = persistentProcedimentodeanalise.getRegistroprocedimentoanaliseList();
            List<Registroprocedimentoanalise> registroprocedimentoanaliseListNew = procedimentodeanalise.getRegistroprocedimentoanaliseList();
            List<String> illegalOrphanMessages = null;
            for (Registroprocedimentoanalise registroprocedimentoanaliseListOldRegistroprocedimentoanalise : registroprocedimentoanaliseListOld) {
                if (!registroprocedimentoanaliseListNew.contains(registroprocedimentoanaliseListOldRegistroprocedimentoanalise)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Registroprocedimentoanalise " + registroprocedimentoanaliseListOldRegistroprocedimentoanalise + " since its procedimentodeanalise field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (coletaNew != null) {
                coletaNew = em.getReference(coletaNew.getClass(), coletaNew.getColetaPK());
                procedimentodeanalise.setColeta(coletaNew);
            }
            List<Registroprocedimentoanalise> attachedRegistroprocedimentoanaliseListNew = new ArrayList<Registroprocedimentoanalise>();
            for (Registroprocedimentoanalise registroprocedimentoanaliseListNewRegistroprocedimentoanaliseToAttach : registroprocedimentoanaliseListNew) {
                registroprocedimentoanaliseListNewRegistroprocedimentoanaliseToAttach = em.getReference(registroprocedimentoanaliseListNewRegistroprocedimentoanaliseToAttach.getClass(), registroprocedimentoanaliseListNewRegistroprocedimentoanaliseToAttach.getRegistroprocedimentoanalisePK());
                attachedRegistroprocedimentoanaliseListNew.add(registroprocedimentoanaliseListNewRegistroprocedimentoanaliseToAttach);
            }
            registroprocedimentoanaliseListNew = attachedRegistroprocedimentoanaliseListNew;
            procedimentodeanalise.setRegistroprocedimentoanaliseList(registroprocedimentoanaliseListNew);
            procedimentodeanalise = em.merge(procedimentodeanalise);
            if (coletaOld != null && !coletaOld.equals(coletaNew)) {
                coletaOld.getProcedimentodeanaliseList().remove(procedimentodeanalise);
                coletaOld = em.merge(coletaOld);
            }
            if (coletaNew != null && !coletaNew.equals(coletaOld)) {
                coletaNew.getProcedimentodeanaliseList().add(procedimentodeanalise);
                coletaNew = em.merge(coletaNew);
            }
            for (Registroprocedimentoanalise registroprocedimentoanaliseListNewRegistroprocedimentoanalise : registroprocedimentoanaliseListNew) {
                if (!registroprocedimentoanaliseListOld.contains(registroprocedimentoanaliseListNewRegistroprocedimentoanalise)) {
                    Procedimentodeanalise oldProcedimentodeanaliseOfRegistroprocedimentoanaliseListNewRegistroprocedimentoanalise = registroprocedimentoanaliseListNewRegistroprocedimentoanalise.getProcedimentodeanalise();
                    registroprocedimentoanaliseListNewRegistroprocedimentoanalise.setProcedimentodeanalise(procedimentodeanalise);
                    registroprocedimentoanaliseListNewRegistroprocedimentoanalise = em.merge(registroprocedimentoanaliseListNewRegistroprocedimentoanalise);
                    if (oldProcedimentodeanaliseOfRegistroprocedimentoanaliseListNewRegistroprocedimentoanalise != null && !oldProcedimentodeanaliseOfRegistroprocedimentoanaliseListNewRegistroprocedimentoanalise.equals(procedimentodeanalise)) {
                        oldProcedimentodeanaliseOfRegistroprocedimentoanaliseListNewRegistroprocedimentoanalise.getRegistroprocedimentoanaliseList().remove(registroprocedimentoanaliseListNewRegistroprocedimentoanalise);
                        oldProcedimentodeanaliseOfRegistroprocedimentoanaliseListNewRegistroprocedimentoanalise = em.merge(oldProcedimentodeanaliseOfRegistroprocedimentoanaliseListNewRegistroprocedimentoanalise);
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
            List<Registroprocedimentoanalise> registroprocedimentoanaliseListOrphanCheck = procedimentodeanalise.getRegistroprocedimentoanaliseList();
            for (Registroprocedimentoanalise registroprocedimentoanaliseListOrphanCheckRegistroprocedimentoanalise : registroprocedimentoanaliseListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Procedimentodeanalise (" + procedimentodeanalise + ") cannot be destroyed since the Registroprocedimentoanalise " + registroprocedimentoanaliseListOrphanCheckRegistroprocedimentoanalise + " in its registroprocedimentoanaliseList field has a non-nullable procedimentodeanalise field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Coleta coleta = procedimentodeanalise.getColeta();
            if (coleta != null) {
                coleta.getProcedimentodeanaliseList().remove(procedimentodeanalise);
                coleta = em.merge(coleta);
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
