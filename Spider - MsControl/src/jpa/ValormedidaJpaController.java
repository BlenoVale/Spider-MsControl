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
import model.Medida;
import model.Valormedida;

/**
 *
 * @author BlenoVale
 */
public class ValormedidaJpaController implements Serializable {

    public ValormedidaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Valormedida valormedida) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Medida medidaid = valormedida.getMedidaid();
            if (medidaid != null) {
                medidaid = em.getReference(medidaid.getClass(), medidaid.getId());
                valormedida.setMedidaid(medidaid);
            }
            em.persist(valormedida);
            if (medidaid != null) {
                medidaid.getValormedidaList().add(valormedida);
                medidaid = em.merge(medidaid);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Valormedida valormedida) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Valormedida persistentValormedida = em.find(Valormedida.class, valormedida.getId());
            Medida medidaidOld = persistentValormedida.getMedidaid();
            Medida medidaidNew = valormedida.getMedidaid();
            if (medidaidNew != null) {
                medidaidNew = em.getReference(medidaidNew.getClass(), medidaidNew.getId());
                valormedida.setMedidaid(medidaidNew);
            }
            valormedida = em.merge(valormedida);
            if (medidaidOld != null && !medidaidOld.equals(medidaidNew)) {
                medidaidOld.getValormedidaList().remove(valormedida);
                medidaidOld = em.merge(medidaidOld);
            }
            if (medidaidNew != null && !medidaidNew.equals(medidaidOld)) {
                medidaidNew.getValormedidaList().add(valormedida);
                medidaidNew = em.merge(medidaidNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = valormedida.getId();
                if (findValormedida(id) == null) {
                    throw new NonexistentEntityException("The valormedida with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Valormedida valormedida;
            try {
                valormedida = em.getReference(Valormedida.class, id);
                valormedida.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The valormedida with id " + id + " no longer exists.", enfe);
            }
            Medida medidaid = valormedida.getMedidaid();
            if (medidaid != null) {
                medidaid.getValormedidaList().remove(valormedida);
                medidaid = em.merge(medidaid);
            }
            em.remove(valormedida);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Valormedida> findValormedidaEntities() {
        return findValormedidaEntities(true, -1, -1);
    }

    public List<Valormedida> findValormedidaEntities(int maxResults, int firstResult) {
        return findValormedidaEntities(false, maxResults, firstResult);
    }

    private List<Valormedida> findValormedidaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Valormedida.class));
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

    public Valormedida findValormedida(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Valormedida.class, id);
        } finally {
            em.close();
        }
    }

    public int getValormedidaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Valormedida> rt = cq.from(Valormedida.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
