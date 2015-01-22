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
import model.Analise;
import model.Registroanalise;
import model.RegistroanalisePK;

/**
 *
 * @author Dan
 */
public class RegistroanaliseJpaController implements Serializable {

    public RegistroanaliseJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Registroanalise registroanalise) throws PreexistingEntityException, Exception {
        if (registroanalise.getRegistroanalisePK() == null) {
            registroanalise.setRegistroanalisePK(new RegistroanalisePK());
        }
        registroanalise.getRegistroanalisePK().setAnaliseMedidaid(registroanalise.getAnalise().getAnalisePK().getMedidaid());
        registroanalise.getRegistroanalisePK().setAnaliseid(registroanalise.getAnalise().getAnalisePK().getId());
        registroanalise.getRegistroanalisePK().setAnaliseMedidaProjetoid(registroanalise.getAnalise().getAnalisePK().getMedidaProjetoid());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Analise analise = registroanalise.getAnalise();
            if (analise != null) {
                analise = em.getReference(analise.getClass(), analise.getAnalisePK());
                registroanalise.setAnalise(analise);
            }
            em.persist(registroanalise);
            if (analise != null) {
                analise.getRegistroanaliseList().add(registroanalise);
                analise = em.merge(analise);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRegistroanalise(registroanalise.getRegistroanalisePK()) != null) {
                throw new PreexistingEntityException("Registroanalise " + registroanalise + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Registroanalise registroanalise) throws NonexistentEntityException, Exception {
        registroanalise.getRegistroanalisePK().setAnaliseMedidaid(registroanalise.getAnalise().getAnalisePK().getMedidaid());
        registroanalise.getRegistroanalisePK().setAnaliseid(registroanalise.getAnalise().getAnalisePK().getId());
        registroanalise.getRegistroanalisePK().setAnaliseMedidaProjetoid(registroanalise.getAnalise().getAnalisePK().getMedidaProjetoid());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Registroanalise persistentRegistroanalise = em.find(Registroanalise.class, registroanalise.getRegistroanalisePK());
            Analise analiseOld = persistentRegistroanalise.getAnalise();
            Analise analiseNew = registroanalise.getAnalise();
            if (analiseNew != null) {
                analiseNew = em.getReference(analiseNew.getClass(), analiseNew.getAnalisePK());
                registroanalise.setAnalise(analiseNew);
            }
            registroanalise = em.merge(registroanalise);
            if (analiseOld != null && !analiseOld.equals(analiseNew)) {
                analiseOld.getRegistroanaliseList().remove(registroanalise);
                analiseOld = em.merge(analiseOld);
            }
            if (analiseNew != null && !analiseNew.equals(analiseOld)) {
                analiseNew.getRegistroanaliseList().add(registroanalise);
                analiseNew = em.merge(analiseNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                RegistroanalisePK id = registroanalise.getRegistroanalisePK();
                if (findRegistroanalise(id) == null) {
                    throw new NonexistentEntityException("The registroanalise with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(RegistroanalisePK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Registroanalise registroanalise;
            try {
                registroanalise = em.getReference(Registroanalise.class, id);
                registroanalise.getRegistroanalisePK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The registroanalise with id " + id + " no longer exists.", enfe);
            }
            Analise analise = registroanalise.getAnalise();
            if (analise != null) {
                analise.getRegistroanaliseList().remove(registroanalise);
                analise = em.merge(analise);
            }
            em.remove(registroanalise);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Registroanalise> findRegistroanaliseEntities() {
        return findRegistroanaliseEntities(true, -1, -1);
    }

    public List<Registroanalise> findRegistroanaliseEntities(int maxResults, int firstResult) {
        return findRegistroanaliseEntities(false, maxResults, firstResult);
    }

    private List<Registroanalise> findRegistroanaliseEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Registroanalise.class));
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

    public Registroanalise findRegistroanalise(RegistroanalisePK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Registroanalise.class, id);
        } finally {
            em.close();
        }
    }

    public int getRegistroanaliseCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Registroanalise> rt = cq.from(Registroanalise.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
