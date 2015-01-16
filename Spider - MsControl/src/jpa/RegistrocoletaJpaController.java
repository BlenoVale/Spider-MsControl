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
import model.Coleta;
import model.Registrocoleta;
import model.RegistrocoletaPK;

/**
 *
 * @author Dan
 */
public class RegistrocoletaJpaController implements Serializable {

    public RegistrocoletaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Registrocoleta registrocoleta) throws PreexistingEntityException, Exception {
        if (registrocoleta.getRegistrocoletaPK() == null) {
            registrocoleta.setRegistrocoletaPK(new RegistrocoletaPK());
        }
        registrocoleta.getRegistrocoletaPK().setColetaMedidaid(registrocoleta.getColeta().getColetaPK().getMedidaid());
        registrocoleta.getRegistrocoletaPK().setColetaid(registrocoleta.getColeta().getColetaPK().getId());
        registrocoleta.getRegistrocoletaPK().setColetaMedidaProjetoid(registrocoleta.getColeta().getColetaPK().getMedidaProjetoid());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Coleta coleta = registrocoleta.getColeta();
            if (coleta != null) {
                coleta = em.getReference(coleta.getClass(), coleta.getColetaPK());
                registrocoleta.setColeta(coleta);
            }
            em.persist(registrocoleta);
            if (coleta != null) {
                coleta.getRegistrocoletaList().add(registrocoleta);
                coleta = em.merge(coleta);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRegistrocoleta(registrocoleta.getRegistrocoletaPK()) != null) {
                throw new PreexistingEntityException("Registrocoleta " + registrocoleta + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Registrocoleta registrocoleta) throws NonexistentEntityException, Exception {
        registrocoleta.getRegistrocoletaPK().setColetaMedidaid(registrocoleta.getColeta().getColetaPK().getMedidaid());
        registrocoleta.getRegistrocoletaPK().setColetaid(registrocoleta.getColeta().getColetaPK().getId());
        registrocoleta.getRegistrocoletaPK().setColetaMedidaProjetoid(registrocoleta.getColeta().getColetaPK().getMedidaProjetoid());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Registrocoleta persistentRegistrocoleta = em.find(Registrocoleta.class, registrocoleta.getRegistrocoletaPK());
            Coleta coletaOld = persistentRegistrocoleta.getColeta();
            Coleta coletaNew = registrocoleta.getColeta();
            if (coletaNew != null) {
                coletaNew = em.getReference(coletaNew.getClass(), coletaNew.getColetaPK());
                registrocoleta.setColeta(coletaNew);
            }
            registrocoleta = em.merge(registrocoleta);
            if (coletaOld != null && !coletaOld.equals(coletaNew)) {
                coletaOld.getRegistrocoletaList().remove(registrocoleta);
                coletaOld = em.merge(coletaOld);
            }
            if (coletaNew != null && !coletaNew.equals(coletaOld)) {
                coletaNew.getRegistrocoletaList().add(registrocoleta);
                coletaNew = em.merge(coletaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                RegistrocoletaPK id = registrocoleta.getRegistrocoletaPK();
                if (findRegistrocoleta(id) == null) {
                    throw new NonexistentEntityException("The registrocoleta with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(RegistrocoletaPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Registrocoleta registrocoleta;
            try {
                registrocoleta = em.getReference(Registrocoleta.class, id);
                registrocoleta.getRegistrocoletaPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The registrocoleta with id " + id + " no longer exists.", enfe);
            }
            Coleta coleta = registrocoleta.getColeta();
            if (coleta != null) {
                coleta.getRegistrocoletaList().remove(registrocoleta);
                coleta = em.merge(coleta);
            }
            em.remove(registrocoleta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Registrocoleta> findRegistrocoletaEntities() {
        return findRegistrocoletaEntities(true, -1, -1);
    }

    public List<Registrocoleta> findRegistrocoletaEntities(int maxResults, int firstResult) {
        return findRegistrocoletaEntities(false, maxResults, firstResult);
    }

    private List<Registrocoleta> findRegistrocoletaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Registrocoleta.class));
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

    public Registrocoleta findRegistrocoleta(RegistrocoletaPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Registrocoleta.class, id);
        } finally {
            em.close();
        }
    }

    public int getRegistrocoletaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Registrocoleta> rt = cq.from(Registrocoleta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
