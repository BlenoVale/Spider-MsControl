/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import jpa.exceptions.NonexistentEntityException;
import jpa.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.Analise;
import model.AnalisePK;
import model.Medida;

/**
 *
 * @author Spider
 */
public class AnaliseJpaController implements Serializable {

    public AnaliseJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Analise analise) throws PreexistingEntityException, Exception {
        if (analise.getAnalisePK() == null) {
            analise.setAnalisePK(new AnalisePK());
        }
        analise.getAnalisePK().setMedidaid(analise.getMedida().getMedidaPK().getId());
        analise.getAnalisePK().setMedidaProjetoid(analise.getMedida().getMedidaPK().getProjetoid());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Medida medida = analise.getMedida();
            if (medida != null) {
                medida = em.getReference(medida.getClass(), medida.getMedidaPK());
                analise.setMedida(medida);
            }
            em.persist(analise);
            if (medida != null) {
                medida.getAnaliseList().add(analise);
                medida = em.merge(medida);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAnalise(analise.getAnalisePK()) != null) {
                throw new PreexistingEntityException("Analise " + analise + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Analise analise) throws NonexistentEntityException, Exception {
        analise.getAnalisePK().setMedidaid(analise.getMedida().getMedidaPK().getId());
        analise.getAnalisePK().setMedidaProjetoid(analise.getMedida().getMedidaPK().getProjetoid());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Analise persistentAnalise = em.find(Analise.class, analise.getAnalisePK());
            Medida medidaOld = persistentAnalise.getMedida();
            Medida medidaNew = analise.getMedida();
            if (medidaNew != null) {
                medidaNew = em.getReference(medidaNew.getClass(), medidaNew.getMedidaPK());
                analise.setMedida(medidaNew);
            }
            analise = em.merge(analise);
            if (medidaOld != null && !medidaOld.equals(medidaNew)) {
                medidaOld.getAnaliseList().remove(analise);
                medidaOld = em.merge(medidaOld);
            }
            if (medidaNew != null && !medidaNew.equals(medidaOld)) {
                medidaNew.getAnaliseList().add(analise);
                medidaNew = em.merge(medidaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                AnalisePK id = analise.getAnalisePK();
                if (findAnalise(id) == null) {
                    throw new NonexistentEntityException("The analise with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(AnalisePK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Analise analise;
            try {
                analise = em.getReference(Analise.class, id);
                analise.getAnalisePK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The analise with id " + id + " no longer exists.", enfe);
            }
            Medida medida = analise.getMedida();
            if (medida != null) {
                medida.getAnaliseList().remove(analise);
                medida = em.merge(medida);
            }
            em.remove(analise);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Analise> findAnaliseEntities() {
        return findAnaliseEntities(true, -1, -1);
    }

    public List<Analise> findAnaliseEntities(int maxResults, int firstResult) {
        return findAnaliseEntities(false, maxResults, firstResult);
    }

    private List<Analise> findAnaliseEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Analise.class));
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

    public Analise findAnalise(AnalisePK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Analise.class, id);
        } finally {
            em.close();
        }
    }

    public int getAnaliseCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Analise> rt = cq.from(Analise.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
