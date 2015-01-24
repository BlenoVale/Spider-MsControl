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
import model.Medida;
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
 * @author Spider
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
            Medida medida = procedimentodeanalise.getMedida();
            if (medida != null) {
                medida = em.getReference(medida.getClass(), medida.getMedidaPK());
                procedimentodeanalise.setMedida(medida);
            }
            List<Registroprocedimentoanalise> attachedRegistroprocedimentoanaliseList = new ArrayList<Registroprocedimentoanalise>();
            for (Registroprocedimentoanalise registroprocedimentoanaliseListRegistroprocedimentoanaliseToAttach : procedimentodeanalise.getRegistroprocedimentoanaliseList()) {
                registroprocedimentoanaliseListRegistroprocedimentoanaliseToAttach = em.getReference(registroprocedimentoanaliseListRegistroprocedimentoanaliseToAttach.getClass(), registroprocedimentoanaliseListRegistroprocedimentoanaliseToAttach.getId());
                attachedRegistroprocedimentoanaliseList.add(registroprocedimentoanaliseListRegistroprocedimentoanaliseToAttach);
            }
            procedimentodeanalise.setRegistroprocedimentoanaliseList(attachedRegistroprocedimentoanaliseList);
            em.persist(procedimentodeanalise);
            if (medida != null) {
                medida.getProcedimentodeanaliseList().add(procedimentodeanalise);
                medida = em.merge(medida);
            }
            for (Registroprocedimentoanalise registroprocedimentoanaliseListRegistroprocedimentoanalise : procedimentodeanalise.getRegistroprocedimentoanaliseList()) {
                Procedimentodeanalise oldProcedimentoDeAnaliseidOfRegistroprocedimentoanaliseListRegistroprocedimentoanalise = registroprocedimentoanaliseListRegistroprocedimentoanalise.getProcedimentoDeAnaliseid();
                registroprocedimentoanaliseListRegistroprocedimentoanalise.setProcedimentoDeAnaliseid(procedimentodeanalise);
                registroprocedimentoanaliseListRegistroprocedimentoanalise = em.merge(registroprocedimentoanaliseListRegistroprocedimentoanalise);
                if (oldProcedimentoDeAnaliseidOfRegistroprocedimentoanaliseListRegistroprocedimentoanalise != null) {
                    oldProcedimentoDeAnaliseidOfRegistroprocedimentoanaliseListRegistroprocedimentoanalise.getRegistroprocedimentoanaliseList().remove(registroprocedimentoanaliseListRegistroprocedimentoanalise);
                    oldProcedimentoDeAnaliseidOfRegistroprocedimentoanaliseListRegistroprocedimentoanalise = em.merge(oldProcedimentoDeAnaliseidOfRegistroprocedimentoanaliseListRegistroprocedimentoanalise);
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
            Medida medidaOld = persistentProcedimentodeanalise.getMedida();
            Medida medidaNew = procedimentodeanalise.getMedida();
            List<Registroprocedimentoanalise> registroprocedimentoanaliseListOld = persistentProcedimentodeanalise.getRegistroprocedimentoanaliseList();
            List<Registroprocedimentoanalise> registroprocedimentoanaliseListNew = procedimentodeanalise.getRegistroprocedimentoanaliseList();
            List<String> illegalOrphanMessages = null;
            for (Registroprocedimentoanalise registroprocedimentoanaliseListOldRegistroprocedimentoanalise : registroprocedimentoanaliseListOld) {
                if (!registroprocedimentoanaliseListNew.contains(registroprocedimentoanaliseListOldRegistroprocedimentoanalise)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Registroprocedimentoanalise " + registroprocedimentoanaliseListOldRegistroprocedimentoanalise + " since its procedimentoDeAnaliseid field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (medidaNew != null) {
                medidaNew = em.getReference(medidaNew.getClass(), medidaNew.getMedidaPK());
                procedimentodeanalise.setMedida(medidaNew);
            }
            List<Registroprocedimentoanalise> attachedRegistroprocedimentoanaliseListNew = new ArrayList<Registroprocedimentoanalise>();
            for (Registroprocedimentoanalise registroprocedimentoanaliseListNewRegistroprocedimentoanaliseToAttach : registroprocedimentoanaliseListNew) {
                registroprocedimentoanaliseListNewRegistroprocedimentoanaliseToAttach = em.getReference(registroprocedimentoanaliseListNewRegistroprocedimentoanaliseToAttach.getClass(), registroprocedimentoanaliseListNewRegistroprocedimentoanaliseToAttach.getId());
                attachedRegistroprocedimentoanaliseListNew.add(registroprocedimentoanaliseListNewRegistroprocedimentoanaliseToAttach);
            }
            registroprocedimentoanaliseListNew = attachedRegistroprocedimentoanaliseListNew;
            procedimentodeanalise.setRegistroprocedimentoanaliseList(registroprocedimentoanaliseListNew);
            procedimentodeanalise = em.merge(procedimentodeanalise);
            if (medidaOld != null && !medidaOld.equals(medidaNew)) {
                medidaOld.getProcedimentodeanaliseList().remove(procedimentodeanalise);
                medidaOld = em.merge(medidaOld);
            }
            if (medidaNew != null && !medidaNew.equals(medidaOld)) {
                medidaNew.getProcedimentodeanaliseList().add(procedimentodeanalise);
                medidaNew = em.merge(medidaNew);
            }
            for (Registroprocedimentoanalise registroprocedimentoanaliseListNewRegistroprocedimentoanalise : registroprocedimentoanaliseListNew) {
                if (!registroprocedimentoanaliseListOld.contains(registroprocedimentoanaliseListNewRegistroprocedimentoanalise)) {
                    Procedimentodeanalise oldProcedimentoDeAnaliseidOfRegistroprocedimentoanaliseListNewRegistroprocedimentoanalise = registroprocedimentoanaliseListNewRegistroprocedimentoanalise.getProcedimentoDeAnaliseid();
                    registroprocedimentoanaliseListNewRegistroprocedimentoanalise.setProcedimentoDeAnaliseid(procedimentodeanalise);
                    registroprocedimentoanaliseListNewRegistroprocedimentoanalise = em.merge(registroprocedimentoanaliseListNewRegistroprocedimentoanalise);
                    if (oldProcedimentoDeAnaliseidOfRegistroprocedimentoanaliseListNewRegistroprocedimentoanalise != null && !oldProcedimentoDeAnaliseidOfRegistroprocedimentoanaliseListNewRegistroprocedimentoanalise.equals(procedimentodeanalise)) {
                        oldProcedimentoDeAnaliseidOfRegistroprocedimentoanaliseListNewRegistroprocedimentoanalise.getRegistroprocedimentoanaliseList().remove(registroprocedimentoanaliseListNewRegistroprocedimentoanalise);
                        oldProcedimentoDeAnaliseidOfRegistroprocedimentoanaliseListNewRegistroprocedimentoanalise = em.merge(oldProcedimentoDeAnaliseidOfRegistroprocedimentoanaliseListNewRegistroprocedimentoanalise);
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
                illegalOrphanMessages.add("This Procedimentodeanalise (" + procedimentodeanalise + ") cannot be destroyed since the Registroprocedimentoanalise " + registroprocedimentoanaliseListOrphanCheckRegistroprocedimentoanalise + " in its registroprocedimentoanaliseList field has a non-nullable procedimentoDeAnaliseid field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Medida medida = procedimentodeanalise.getMedida();
            if (medida != null) {
                medida.getProcedimentodeanaliseList().remove(procedimentodeanalise);
                medida = em.merge(medida);
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
