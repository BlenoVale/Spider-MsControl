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
import model.Datasprocedimentoanalise;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;
import model.Procedimentodeanalise;
import model.Registroprocedimentoanalise;

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
        if (procedimentodeanalise.getDatasprocedimentoanaliseList() == null) {
            procedimentodeanalise.setDatasprocedimentoanaliseList(new ArrayList<Datasprocedimentoanalise>());
        }
        if (procedimentodeanalise.getRegistroprocedimentoanaliseList() == null) {
            procedimentodeanalise.setRegistroprocedimentoanaliseList(new ArrayList<Registroprocedimentoanalise>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Indicador indicadorid = procedimentodeanalise.getIndicadorid();
            if (indicadorid != null) {
                indicadorid = em.getReference(indicadorid.getClass(), indicadorid.getId());
                procedimentodeanalise.setIndicadorid(indicadorid);
            }
            List<Datasprocedimentoanalise> attachedDatasprocedimentoanaliseList = new ArrayList<Datasprocedimentoanalise>();
            for (Datasprocedimentoanalise datasprocedimentoanaliseListDatasprocedimentoanaliseToAttach : procedimentodeanalise.getDatasprocedimentoanaliseList()) {
                datasprocedimentoanaliseListDatasprocedimentoanaliseToAttach = em.getReference(datasprocedimentoanaliseListDatasprocedimentoanaliseToAttach.getClass(), datasprocedimentoanaliseListDatasprocedimentoanaliseToAttach.getId());
                attachedDatasprocedimentoanaliseList.add(datasprocedimentoanaliseListDatasprocedimentoanaliseToAttach);
            }
            procedimentodeanalise.setDatasprocedimentoanaliseList(attachedDatasprocedimentoanaliseList);
            List<Registroprocedimentoanalise> attachedRegistroprocedimentoanaliseList = new ArrayList<Registroprocedimentoanalise>();
            for (Registroprocedimentoanalise registroprocedimentoanaliseListRegistroprocedimentoanaliseToAttach : procedimentodeanalise.getRegistroprocedimentoanaliseList()) {
                registroprocedimentoanaliseListRegistroprocedimentoanaliseToAttach = em.getReference(registroprocedimentoanaliseListRegistroprocedimentoanaliseToAttach.getClass(), registroprocedimentoanaliseListRegistroprocedimentoanaliseToAttach.getId());
                attachedRegistroprocedimentoanaliseList.add(registroprocedimentoanaliseListRegistroprocedimentoanaliseToAttach);
            }
            procedimentodeanalise.setRegistroprocedimentoanaliseList(attachedRegistroprocedimentoanaliseList);
            em.persist(procedimentodeanalise);
            if (indicadorid != null) {
                indicadorid.getProcedimentodeanaliseList().add(procedimentodeanalise);
                indicadorid = em.merge(indicadorid);
            }
            for (Datasprocedimentoanalise datasprocedimentoanaliseListDatasprocedimentoanalise : procedimentodeanalise.getDatasprocedimentoanaliseList()) {
                Procedimentodeanalise oldProcedimentoDeAnaliseidOfDatasprocedimentoanaliseListDatasprocedimentoanalise = datasprocedimentoanaliseListDatasprocedimentoanalise.getProcedimentoDeAnaliseid();
                datasprocedimentoanaliseListDatasprocedimentoanalise.setProcedimentoDeAnaliseid(procedimentodeanalise);
                datasprocedimentoanaliseListDatasprocedimentoanalise = em.merge(datasprocedimentoanaliseListDatasprocedimentoanalise);
                if (oldProcedimentoDeAnaliseidOfDatasprocedimentoanaliseListDatasprocedimentoanalise != null) {
                    oldProcedimentoDeAnaliseidOfDatasprocedimentoanaliseListDatasprocedimentoanalise.getDatasprocedimentoanaliseList().remove(datasprocedimentoanaliseListDatasprocedimentoanalise);
                    oldProcedimentoDeAnaliseidOfDatasprocedimentoanaliseListDatasprocedimentoanalise = em.merge(oldProcedimentoDeAnaliseidOfDatasprocedimentoanaliseListDatasprocedimentoanalise);
                }
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
            Indicador indicadoridOld = persistentProcedimentodeanalise.getIndicadorid();
            Indicador indicadoridNew = procedimentodeanalise.getIndicadorid();
            List<Datasprocedimentoanalise> datasprocedimentoanaliseListOld = persistentProcedimentodeanalise.getDatasprocedimentoanaliseList();
            List<Datasprocedimentoanalise> datasprocedimentoanaliseListNew = procedimentodeanalise.getDatasprocedimentoanaliseList();
            List<Registroprocedimentoanalise> registroprocedimentoanaliseListOld = persistentProcedimentodeanalise.getRegistroprocedimentoanaliseList();
            List<Registroprocedimentoanalise> registroprocedimentoanaliseListNew = procedimentodeanalise.getRegistroprocedimentoanaliseList();
            List<String> illegalOrphanMessages = null;
            for (Datasprocedimentoanalise datasprocedimentoanaliseListOldDatasprocedimentoanalise : datasprocedimentoanaliseListOld) {
                if (!datasprocedimentoanaliseListNew.contains(datasprocedimentoanaliseListOldDatasprocedimentoanalise)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Datasprocedimentoanalise " + datasprocedimentoanaliseListOldDatasprocedimentoanalise + " since its procedimentoDeAnaliseid field is not nullable.");
                }
            }
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
            if (indicadoridNew != null) {
                indicadoridNew = em.getReference(indicadoridNew.getClass(), indicadoridNew.getId());
                procedimentodeanalise.setIndicadorid(indicadoridNew);
            }
            List<Datasprocedimentoanalise> attachedDatasprocedimentoanaliseListNew = new ArrayList<Datasprocedimentoanalise>();
            for (Datasprocedimentoanalise datasprocedimentoanaliseListNewDatasprocedimentoanaliseToAttach : datasprocedimentoanaliseListNew) {
                datasprocedimentoanaliseListNewDatasprocedimentoanaliseToAttach = em.getReference(datasprocedimentoanaliseListNewDatasprocedimentoanaliseToAttach.getClass(), datasprocedimentoanaliseListNewDatasprocedimentoanaliseToAttach.getId());
                attachedDatasprocedimentoanaliseListNew.add(datasprocedimentoanaliseListNewDatasprocedimentoanaliseToAttach);
            }
            datasprocedimentoanaliseListNew = attachedDatasprocedimentoanaliseListNew;
            procedimentodeanalise.setDatasprocedimentoanaliseList(datasprocedimentoanaliseListNew);
            List<Registroprocedimentoanalise> attachedRegistroprocedimentoanaliseListNew = new ArrayList<Registroprocedimentoanalise>();
            for (Registroprocedimentoanalise registroprocedimentoanaliseListNewRegistroprocedimentoanaliseToAttach : registroprocedimentoanaliseListNew) {
                registroprocedimentoanaliseListNewRegistroprocedimentoanaliseToAttach = em.getReference(registroprocedimentoanaliseListNewRegistroprocedimentoanaliseToAttach.getClass(), registroprocedimentoanaliseListNewRegistroprocedimentoanaliseToAttach.getId());
                attachedRegistroprocedimentoanaliseListNew.add(registroprocedimentoanaliseListNewRegistroprocedimentoanaliseToAttach);
            }
            registroprocedimentoanaliseListNew = attachedRegistroprocedimentoanaliseListNew;
            procedimentodeanalise.setRegistroprocedimentoanaliseList(registroprocedimentoanaliseListNew);
            procedimentodeanalise = em.merge(procedimentodeanalise);
            if (indicadoridOld != null && !indicadoridOld.equals(indicadoridNew)) {
                indicadoridOld.getProcedimentodeanaliseList().remove(procedimentodeanalise);
                indicadoridOld = em.merge(indicadoridOld);
            }
            if (indicadoridNew != null && !indicadoridNew.equals(indicadoridOld)) {
                indicadoridNew.getProcedimentodeanaliseList().add(procedimentodeanalise);
                indicadoridNew = em.merge(indicadoridNew);
            }
            for (Datasprocedimentoanalise datasprocedimentoanaliseListNewDatasprocedimentoanalise : datasprocedimentoanaliseListNew) {
                if (!datasprocedimentoanaliseListOld.contains(datasprocedimentoanaliseListNewDatasprocedimentoanalise)) {
                    Procedimentodeanalise oldProcedimentoDeAnaliseidOfDatasprocedimentoanaliseListNewDatasprocedimentoanalise = datasprocedimentoanaliseListNewDatasprocedimentoanalise.getProcedimentoDeAnaliseid();
                    datasprocedimentoanaliseListNewDatasprocedimentoanalise.setProcedimentoDeAnaliseid(procedimentodeanalise);
                    datasprocedimentoanaliseListNewDatasprocedimentoanalise = em.merge(datasprocedimentoanaliseListNewDatasprocedimentoanalise);
                    if (oldProcedimentoDeAnaliseidOfDatasprocedimentoanaliseListNewDatasprocedimentoanalise != null && !oldProcedimentoDeAnaliseidOfDatasprocedimentoanaliseListNewDatasprocedimentoanalise.equals(procedimentodeanalise)) {
                        oldProcedimentoDeAnaliseidOfDatasprocedimentoanaliseListNewDatasprocedimentoanalise.getDatasprocedimentoanaliseList().remove(datasprocedimentoanaliseListNewDatasprocedimentoanalise);
                        oldProcedimentoDeAnaliseidOfDatasprocedimentoanaliseListNewDatasprocedimentoanalise = em.merge(oldProcedimentoDeAnaliseidOfDatasprocedimentoanaliseListNewDatasprocedimentoanalise);
                    }
                }
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
            List<Datasprocedimentoanalise> datasprocedimentoanaliseListOrphanCheck = procedimentodeanalise.getDatasprocedimentoanaliseList();
            for (Datasprocedimentoanalise datasprocedimentoanaliseListOrphanCheckDatasprocedimentoanalise : datasprocedimentoanaliseListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Procedimentodeanalise (" + procedimentodeanalise + ") cannot be destroyed since the Datasprocedimentoanalise " + datasprocedimentoanaliseListOrphanCheckDatasprocedimentoanalise + " in its datasprocedimentoanaliseList field has a non-nullable procedimentoDeAnaliseid field.");
            }
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
            Indicador indicadorid = procedimentodeanalise.getIndicadorid();
            if (indicadorid != null) {
                indicadorid.getProcedimentodeanaliseList().remove(procedimentodeanalise);
                indicadorid = em.merge(indicadorid);
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
