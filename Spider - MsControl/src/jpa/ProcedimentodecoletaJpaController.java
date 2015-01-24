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
 * @author Spider
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
            Medida medida = procedimentodecoleta.getMedida();
            if (medida != null) {
                medida = em.getReference(medida.getClass(), medida.getMedidaPK());
                procedimentodecoleta.setMedida(medida);
            }
            List<Registroprocedimentocoleta> attachedRegistroprocedimentocoletaList = new ArrayList<Registroprocedimentocoleta>();
            for (Registroprocedimentocoleta registroprocedimentocoletaListRegistroprocedimentocoletaToAttach : procedimentodecoleta.getRegistroprocedimentocoletaList()) {
                registroprocedimentocoletaListRegistroprocedimentocoletaToAttach = em.getReference(registroprocedimentocoletaListRegistroprocedimentocoletaToAttach.getClass(), registroprocedimentocoletaListRegistroprocedimentocoletaToAttach.getId());
                attachedRegistroprocedimentocoletaList.add(registroprocedimentocoletaListRegistroprocedimentocoletaToAttach);
            }
            procedimentodecoleta.setRegistroprocedimentocoletaList(attachedRegistroprocedimentocoletaList);
            em.persist(procedimentodecoleta);
            if (medida != null) {
                medida.getProcedimentodecoletaList().add(procedimentodecoleta);
                medida = em.merge(medida);
            }
            for (Registroprocedimentocoleta registroprocedimentocoletaListRegistroprocedimentocoleta : procedimentodecoleta.getRegistroprocedimentocoletaList()) {
                Procedimentodecoleta oldProcedimentoDeColetaidOfRegistroprocedimentocoletaListRegistroprocedimentocoleta = registroprocedimentocoletaListRegistroprocedimentocoleta.getProcedimentoDeColetaid();
                registroprocedimentocoletaListRegistroprocedimentocoleta.setProcedimentoDeColetaid(procedimentodecoleta);
                registroprocedimentocoletaListRegistroprocedimentocoleta = em.merge(registroprocedimentocoletaListRegistroprocedimentocoleta);
                if (oldProcedimentoDeColetaidOfRegistroprocedimentocoletaListRegistroprocedimentocoleta != null) {
                    oldProcedimentoDeColetaidOfRegistroprocedimentocoletaListRegistroprocedimentocoleta.getRegistroprocedimentocoletaList().remove(registroprocedimentocoletaListRegistroprocedimentocoleta);
                    oldProcedimentoDeColetaidOfRegistroprocedimentocoletaListRegistroprocedimentocoleta = em.merge(oldProcedimentoDeColetaidOfRegistroprocedimentocoletaListRegistroprocedimentocoleta);
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
            Medida medidaOld = persistentProcedimentodecoleta.getMedida();
            Medida medidaNew = procedimentodecoleta.getMedida();
            List<Registroprocedimentocoleta> registroprocedimentocoletaListOld = persistentProcedimentodecoleta.getRegistroprocedimentocoletaList();
            List<Registroprocedimentocoleta> registroprocedimentocoletaListNew = procedimentodecoleta.getRegistroprocedimentocoletaList();
            List<String> illegalOrphanMessages = null;
            for (Registroprocedimentocoleta registroprocedimentocoletaListOldRegistroprocedimentocoleta : registroprocedimentocoletaListOld) {
                if (!registroprocedimentocoletaListNew.contains(registroprocedimentocoletaListOldRegistroprocedimentocoleta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Registroprocedimentocoleta " + registroprocedimentocoletaListOldRegistroprocedimentocoleta + " since its procedimentoDeColetaid field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (medidaNew != null) {
                medidaNew = em.getReference(medidaNew.getClass(), medidaNew.getMedidaPK());
                procedimentodecoleta.setMedida(medidaNew);
            }
            List<Registroprocedimentocoleta> attachedRegistroprocedimentocoletaListNew = new ArrayList<Registroprocedimentocoleta>();
            for (Registroprocedimentocoleta registroprocedimentocoletaListNewRegistroprocedimentocoletaToAttach : registroprocedimentocoletaListNew) {
                registroprocedimentocoletaListNewRegistroprocedimentocoletaToAttach = em.getReference(registroprocedimentocoletaListNewRegistroprocedimentocoletaToAttach.getClass(), registroprocedimentocoletaListNewRegistroprocedimentocoletaToAttach.getId());
                attachedRegistroprocedimentocoletaListNew.add(registroprocedimentocoletaListNewRegistroprocedimentocoletaToAttach);
            }
            registroprocedimentocoletaListNew = attachedRegistroprocedimentocoletaListNew;
            procedimentodecoleta.setRegistroprocedimentocoletaList(registroprocedimentocoletaListNew);
            procedimentodecoleta = em.merge(procedimentodecoleta);
            if (medidaOld != null && !medidaOld.equals(medidaNew)) {
                medidaOld.getProcedimentodecoletaList().remove(procedimentodecoleta);
                medidaOld = em.merge(medidaOld);
            }
            if (medidaNew != null && !medidaNew.equals(medidaOld)) {
                medidaNew.getProcedimentodecoletaList().add(procedimentodecoleta);
                medidaNew = em.merge(medidaNew);
            }
            for (Registroprocedimentocoleta registroprocedimentocoletaListNewRegistroprocedimentocoleta : registroprocedimentocoletaListNew) {
                if (!registroprocedimentocoletaListOld.contains(registroprocedimentocoletaListNewRegistroprocedimentocoleta)) {
                    Procedimentodecoleta oldProcedimentoDeColetaidOfRegistroprocedimentocoletaListNewRegistroprocedimentocoleta = registroprocedimentocoletaListNewRegistroprocedimentocoleta.getProcedimentoDeColetaid();
                    registroprocedimentocoletaListNewRegistroprocedimentocoleta.setProcedimentoDeColetaid(procedimentodecoleta);
                    registroprocedimentocoletaListNewRegistroprocedimentocoleta = em.merge(registroprocedimentocoletaListNewRegistroprocedimentocoleta);
                    if (oldProcedimentoDeColetaidOfRegistroprocedimentocoletaListNewRegistroprocedimentocoleta != null && !oldProcedimentoDeColetaidOfRegistroprocedimentocoletaListNewRegistroprocedimentocoleta.equals(procedimentodecoleta)) {
                        oldProcedimentoDeColetaidOfRegistroprocedimentocoletaListNewRegistroprocedimentocoleta.getRegistroprocedimentocoletaList().remove(registroprocedimentocoletaListNewRegistroprocedimentocoleta);
                        oldProcedimentoDeColetaidOfRegistroprocedimentocoletaListNewRegistroprocedimentocoleta = em.merge(oldProcedimentoDeColetaidOfRegistroprocedimentocoletaListNewRegistroprocedimentocoleta);
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
                illegalOrphanMessages.add("This Procedimentodecoleta (" + procedimentodecoleta + ") cannot be destroyed since the Registroprocedimentocoleta " + registroprocedimentocoletaListOrphanCheckRegistroprocedimentocoleta + " in its registroprocedimentocoletaList field has a non-nullable procedimentoDeColetaid field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Medida medida = procedimentodecoleta.getMedida();
            if (medida != null) {
                medida.getProcedimentodecoletaList().remove(procedimentodecoleta);
                medida = em.merge(medida);
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
