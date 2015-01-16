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
import model.Composicao;
import model.Registrocomposicao;
import model.RegistrocomposicaoPK;

/**
 *
 * @author Dan
 */
public class RegistrocomposicaoJpaController implements Serializable {

    public RegistrocomposicaoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Registrocomposicao registrocomposicao) throws PreexistingEntityException, Exception {
        if (registrocomposicao.getRegistrocomposicaoPK() == null) {
            registrocomposicao.setRegistrocomposicaoPK(new RegistrocomposicaoPK());
        }
        registrocomposicao.getRegistrocomposicaoPK().setComposicaoid(registrocomposicao.getComposicao().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Composicao composicao = registrocomposicao.getComposicao();
            if (composicao != null) {
                composicao = em.getReference(composicao.getClass(), composicao.getId());
                registrocomposicao.setComposicao(composicao);
            }
            em.persist(registrocomposicao);
            if (composicao != null) {
                composicao.getRegistrocomposicaoList().add(registrocomposicao);
                composicao = em.merge(composicao);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRegistrocomposicao(registrocomposicao.getRegistrocomposicaoPK()) != null) {
                throw new PreexistingEntityException("Registrocomposicao " + registrocomposicao + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Registrocomposicao registrocomposicao) throws NonexistentEntityException, Exception {
        registrocomposicao.getRegistrocomposicaoPK().setComposicaoid(registrocomposicao.getComposicao().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Registrocomposicao persistentRegistrocomposicao = em.find(Registrocomposicao.class, registrocomposicao.getRegistrocomposicaoPK());
            Composicao composicaoOld = persistentRegistrocomposicao.getComposicao();
            Composicao composicaoNew = registrocomposicao.getComposicao();
            if (composicaoNew != null) {
                composicaoNew = em.getReference(composicaoNew.getClass(), composicaoNew.getId());
                registrocomposicao.setComposicao(composicaoNew);
            }
            registrocomposicao = em.merge(registrocomposicao);
            if (composicaoOld != null && !composicaoOld.equals(composicaoNew)) {
                composicaoOld.getRegistrocomposicaoList().remove(registrocomposicao);
                composicaoOld = em.merge(composicaoOld);
            }
            if (composicaoNew != null && !composicaoNew.equals(composicaoOld)) {
                composicaoNew.getRegistrocomposicaoList().add(registrocomposicao);
                composicaoNew = em.merge(composicaoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                RegistrocomposicaoPK id = registrocomposicao.getRegistrocomposicaoPK();
                if (findRegistrocomposicao(id) == null) {
                    throw new NonexistentEntityException("The registrocomposicao with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(RegistrocomposicaoPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Registrocomposicao registrocomposicao;
            try {
                registrocomposicao = em.getReference(Registrocomposicao.class, id);
                registrocomposicao.getRegistrocomposicaoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The registrocomposicao with id " + id + " no longer exists.", enfe);
            }
            Composicao composicao = registrocomposicao.getComposicao();
            if (composicao != null) {
                composicao.getRegistrocomposicaoList().remove(registrocomposicao);
                composicao = em.merge(composicao);
            }
            em.remove(registrocomposicao);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Registrocomposicao> findRegistrocomposicaoEntities() {
        return findRegistrocomposicaoEntities(true, -1, -1);
    }

    public List<Registrocomposicao> findRegistrocomposicaoEntities(int maxResults, int firstResult) {
        return findRegistrocomposicaoEntities(false, maxResults, firstResult);
    }

    private List<Registrocomposicao> findRegistrocomposicaoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Registrocomposicao.class));
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

    public Registrocomposicao findRegistrocomposicao(RegistrocomposicaoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Registrocomposicao.class, id);
        } finally {
            em.close();
        }
    }

    public int getRegistrocomposicaoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Registrocomposicao> rt = cq.from(Registrocomposicao.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
