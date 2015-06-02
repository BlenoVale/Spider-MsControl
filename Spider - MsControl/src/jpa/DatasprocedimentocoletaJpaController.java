/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import jpa.exceptions.NonexistentEntityException;
import jpa.exceptions.PreexistingEntityException;
import model.Datasprocedimentocoleta;
import model.Procedimentodecoleta;

/**
 *
 * @author BlenoVale
 */
public class DatasprocedimentocoletaJpaController implements Serializable {

    public DatasprocedimentocoletaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Datasprocedimentocoleta datasprocedimentocoleta) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Procedimentodecoleta procedimentoDeColetaid = datasprocedimentocoleta.getProcedimentoDeColetaid();
            if (procedimentoDeColetaid != null) {
                procedimentoDeColetaid = em.getReference(procedimentoDeColetaid.getClass(), procedimentoDeColetaid.getId());
                datasprocedimentocoleta.setProcedimentoDeColetaid(procedimentoDeColetaid);
            }
            em.persist(datasprocedimentocoleta);
            if (procedimentoDeColetaid != null) {
                procedimentoDeColetaid.getDatasprocedimentocoletaList().add(datasprocedimentocoleta);
                procedimentoDeColetaid = em.merge(procedimentoDeColetaid);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDatasprocedimentocoleta(datasprocedimentocoleta.getId()) != null) {
                throw new PreexistingEntityException("Datasprocedimentocoleta " + datasprocedimentocoleta + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Datasprocedimentocoleta datasprocedimentocoleta) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Datasprocedimentocoleta persistentDatasprocedimentocoleta = em.find(Datasprocedimentocoleta.class, datasprocedimentocoleta.getId());
            Procedimentodecoleta procedimentoDeColetaidOld = persistentDatasprocedimentocoleta.getProcedimentoDeColetaid();
            Procedimentodecoleta procedimentoDeColetaidNew = datasprocedimentocoleta.getProcedimentoDeColetaid();
            if (procedimentoDeColetaidNew != null) {
                procedimentoDeColetaidNew = em.getReference(procedimentoDeColetaidNew.getClass(), procedimentoDeColetaidNew.getId());
                datasprocedimentocoleta.setProcedimentoDeColetaid(procedimentoDeColetaidNew);
            }
            datasprocedimentocoleta = em.merge(datasprocedimentocoleta);
            if (procedimentoDeColetaidOld != null && !procedimentoDeColetaidOld.equals(procedimentoDeColetaidNew)) {
                procedimentoDeColetaidOld.getDatasprocedimentocoletaList().remove(datasprocedimentocoleta);
                procedimentoDeColetaidOld = em.merge(procedimentoDeColetaidOld);
            }
            if (procedimentoDeColetaidNew != null && !procedimentoDeColetaidNew.equals(procedimentoDeColetaidOld)) {
                procedimentoDeColetaidNew.getDatasprocedimentocoletaList().add(datasprocedimentocoleta);
                procedimentoDeColetaidNew = em.merge(procedimentoDeColetaidNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = datasprocedimentocoleta.getId();
                if (findDatasprocedimentocoleta(id) == null) {
                    throw new NonexistentEntityException("The datasprocedimentocoleta with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Datasprocedimentocoleta datasprocedimentocoleta;
            try {
                datasprocedimentocoleta = em.getReference(Datasprocedimentocoleta.class, id);
                datasprocedimentocoleta.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The datasprocedimentocoleta with id " + id + " no longer exists.", enfe);
            }
            Procedimentodecoleta procedimentoDeColetaid = datasprocedimentocoleta.getProcedimentoDeColetaid();
            if (procedimentoDeColetaid != null) {
                procedimentoDeColetaid.getDatasprocedimentocoletaList().remove(datasprocedimentocoleta);
                procedimentoDeColetaid = em.merge(procedimentoDeColetaid);
            }
            em.remove(datasprocedimentocoleta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Datasprocedimentocoleta> findDatasprocedimentocoletaEntities() {
        return findDatasprocedimentocoletaEntities(true, -1, -1);
    }

    public List<Datasprocedimentocoleta> findDatasprocedimentocoletaEntities(int maxResults, int firstResult) {
        return findDatasprocedimentocoletaEntities(false, maxResults, firstResult);
    }

    private List<Datasprocedimentocoleta> findDatasprocedimentocoletaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Datasprocedimentocoleta.class));
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

    public Datasprocedimentocoleta findDatasprocedimentocoleta(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Datasprocedimentocoleta.class, id);
        } finally {
            em.close();
        }
    }

    public int getDatasprocedimentocoletaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Datasprocedimentocoleta> rt = cq.from(Datasprocedimentocoleta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
