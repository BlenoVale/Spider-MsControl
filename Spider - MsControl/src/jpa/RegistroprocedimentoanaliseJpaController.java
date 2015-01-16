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
import model.Procedimentodeanalise;
import model.Registroprocedimentoanalise;
import model.RegistroprocedimentoanalisePK;

/**
 *
 * @author Dan
 */
public class RegistroprocedimentoanaliseJpaController implements Serializable {

    public RegistroprocedimentoanaliseJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Registroprocedimentoanalise registroprocedimentoanalise) throws PreexistingEntityException, Exception {
        if (registroprocedimentoanalise.getRegistroprocedimentoanalisePK() == null) {
            registroprocedimentoanalise.setRegistroprocedimentoanalisePK(new RegistroprocedimentoanalisePK());
        }
        registroprocedimentoanalise.getRegistroprocedimentoanalisePK().setProcedimentoDeAnaliseid(registroprocedimentoanalise.getProcedimentodeanalise().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Procedimentodeanalise procedimentodeanalise = registroprocedimentoanalise.getProcedimentodeanalise();
            if (procedimentodeanalise != null) {
                procedimentodeanalise = em.getReference(procedimentodeanalise.getClass(), procedimentodeanalise.getId());
                registroprocedimentoanalise.setProcedimentodeanalise(procedimentodeanalise);
            }
            em.persist(registroprocedimentoanalise);
            if (procedimentodeanalise != null) {
                procedimentodeanalise.getRegistroprocedimentoanaliseList().add(registroprocedimentoanalise);
                procedimentodeanalise = em.merge(procedimentodeanalise);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRegistroprocedimentoanalise(registroprocedimentoanalise.getRegistroprocedimentoanalisePK()) != null) {
                throw new PreexistingEntityException("Registroprocedimentoanalise " + registroprocedimentoanalise + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Registroprocedimentoanalise registroprocedimentoanalise) throws NonexistentEntityException, Exception {
        registroprocedimentoanalise.getRegistroprocedimentoanalisePK().setProcedimentoDeAnaliseid(registroprocedimentoanalise.getProcedimentodeanalise().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Registroprocedimentoanalise persistentRegistroprocedimentoanalise = em.find(Registroprocedimentoanalise.class, registroprocedimentoanalise.getRegistroprocedimentoanalisePK());
            Procedimentodeanalise procedimentodeanaliseOld = persistentRegistroprocedimentoanalise.getProcedimentodeanalise();
            Procedimentodeanalise procedimentodeanaliseNew = registroprocedimentoanalise.getProcedimentodeanalise();
            if (procedimentodeanaliseNew != null) {
                procedimentodeanaliseNew = em.getReference(procedimentodeanaliseNew.getClass(), procedimentodeanaliseNew.getId());
                registroprocedimentoanalise.setProcedimentodeanalise(procedimentodeanaliseNew);
            }
            registroprocedimentoanalise = em.merge(registroprocedimentoanalise);
            if (procedimentodeanaliseOld != null && !procedimentodeanaliseOld.equals(procedimentodeanaliseNew)) {
                procedimentodeanaliseOld.getRegistroprocedimentoanaliseList().remove(registroprocedimentoanalise);
                procedimentodeanaliseOld = em.merge(procedimentodeanaliseOld);
            }
            if (procedimentodeanaliseNew != null && !procedimentodeanaliseNew.equals(procedimentodeanaliseOld)) {
                procedimentodeanaliseNew.getRegistroprocedimentoanaliseList().add(registroprocedimentoanalise);
                procedimentodeanaliseNew = em.merge(procedimentodeanaliseNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                RegistroprocedimentoanalisePK id = registroprocedimentoanalise.getRegistroprocedimentoanalisePK();
                if (findRegistroprocedimentoanalise(id) == null) {
                    throw new NonexistentEntityException("The registroprocedimentoanalise with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(RegistroprocedimentoanalisePK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Registroprocedimentoanalise registroprocedimentoanalise;
            try {
                registroprocedimentoanalise = em.getReference(Registroprocedimentoanalise.class, id);
                registroprocedimentoanalise.getRegistroprocedimentoanalisePK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The registroprocedimentoanalise with id " + id + " no longer exists.", enfe);
            }
            Procedimentodeanalise procedimentodeanalise = registroprocedimentoanalise.getProcedimentodeanalise();
            if (procedimentodeanalise != null) {
                procedimentodeanalise.getRegistroprocedimentoanaliseList().remove(registroprocedimentoanalise);
                procedimentodeanalise = em.merge(procedimentodeanalise);
            }
            em.remove(registroprocedimentoanalise);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Registroprocedimentoanalise> findRegistroprocedimentoanaliseEntities() {
        return findRegistroprocedimentoanaliseEntities(true, -1, -1);
    }

    public List<Registroprocedimentoanalise> findRegistroprocedimentoanaliseEntities(int maxResults, int firstResult) {
        return findRegistroprocedimentoanaliseEntities(false, maxResults, firstResult);
    }

    private List<Registroprocedimentoanalise> findRegistroprocedimentoanaliseEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Registroprocedimentoanalise.class));
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

    public Registroprocedimentoanalise findRegistroprocedimentoanalise(RegistroprocedimentoanalisePK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Registroprocedimentoanalise.class, id);
        } finally {
            em.close();
        }
    }

    public int getRegistroprocedimentoanaliseCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Registroprocedimentoanalise> rt = cq.from(Registroprocedimentoanalise.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
