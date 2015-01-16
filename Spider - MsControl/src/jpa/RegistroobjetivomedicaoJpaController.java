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
import model.Objetivodemedicao;
import model.Registroobjetivomedicao;
import model.RegistroobjetivomedicaoPK;

/**
 *
 * @author Dan
 */
public class RegistroobjetivomedicaoJpaController implements Serializable {

    public RegistroobjetivomedicaoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Registroobjetivomedicao registroobjetivomedicao) throws PreexistingEntityException, Exception {
        if (registroobjetivomedicao.getRegistroobjetivomedicaoPK() == null) {
            registroobjetivomedicao.setRegistroobjetivomedicaoPK(new RegistroobjetivomedicaoPK());
        }
        registroobjetivomedicao.getRegistroobjetivomedicaoPK().setObjetivoDeMedicaoProjetoid(registroobjetivomedicao.getObjetivodemedicao().getObjetivodemedicaoPK().getProjetoid());
        registroobjetivomedicao.getRegistroobjetivomedicaoPK().setObjetivoDeMedicaoid(registroobjetivomedicao.getObjetivodemedicao().getObjetivodemedicaoPK().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Objetivodemedicao objetivodemedicao = registroobjetivomedicao.getObjetivodemedicao();
            if (objetivodemedicao != null) {
                objetivodemedicao = em.getReference(objetivodemedicao.getClass(), objetivodemedicao.getObjetivodemedicaoPK());
                registroobjetivomedicao.setObjetivodemedicao(objetivodemedicao);
            }
            em.persist(registroobjetivomedicao);
            if (objetivodemedicao != null) {
                objetivodemedicao.getRegistroobjetivomedicaoList().add(registroobjetivomedicao);
                objetivodemedicao = em.merge(objetivodemedicao);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRegistroobjetivomedicao(registroobjetivomedicao.getRegistroobjetivomedicaoPK()) != null) {
                throw new PreexistingEntityException("Registroobjetivomedicao " + registroobjetivomedicao + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Registroobjetivomedicao registroobjetivomedicao) throws NonexistentEntityException, Exception {
        registroobjetivomedicao.getRegistroobjetivomedicaoPK().setObjetivoDeMedicaoProjetoid(registroobjetivomedicao.getObjetivodemedicao().getObjetivodemedicaoPK().getProjetoid());
        registroobjetivomedicao.getRegistroobjetivomedicaoPK().setObjetivoDeMedicaoid(registroobjetivomedicao.getObjetivodemedicao().getObjetivodemedicaoPK().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Registroobjetivomedicao persistentRegistroobjetivomedicao = em.find(Registroobjetivomedicao.class, registroobjetivomedicao.getRegistroobjetivomedicaoPK());
            Objetivodemedicao objetivodemedicaoOld = persistentRegistroobjetivomedicao.getObjetivodemedicao();
            Objetivodemedicao objetivodemedicaoNew = registroobjetivomedicao.getObjetivodemedicao();
            if (objetivodemedicaoNew != null) {
                objetivodemedicaoNew = em.getReference(objetivodemedicaoNew.getClass(), objetivodemedicaoNew.getObjetivodemedicaoPK());
                registroobjetivomedicao.setObjetivodemedicao(objetivodemedicaoNew);
            }
            registroobjetivomedicao = em.merge(registroobjetivomedicao);
            if (objetivodemedicaoOld != null && !objetivodemedicaoOld.equals(objetivodemedicaoNew)) {
                objetivodemedicaoOld.getRegistroobjetivomedicaoList().remove(registroobjetivomedicao);
                objetivodemedicaoOld = em.merge(objetivodemedicaoOld);
            }
            if (objetivodemedicaoNew != null && !objetivodemedicaoNew.equals(objetivodemedicaoOld)) {
                objetivodemedicaoNew.getRegistroobjetivomedicaoList().add(registroobjetivomedicao);
                objetivodemedicaoNew = em.merge(objetivodemedicaoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                RegistroobjetivomedicaoPK id = registroobjetivomedicao.getRegistroobjetivomedicaoPK();
                if (findRegistroobjetivomedicao(id) == null) {
                    throw new NonexistentEntityException("The registroobjetivomedicao with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(RegistroobjetivomedicaoPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Registroobjetivomedicao registroobjetivomedicao;
            try {
                registroobjetivomedicao = em.getReference(Registroobjetivomedicao.class, id);
                registroobjetivomedicao.getRegistroobjetivomedicaoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The registroobjetivomedicao with id " + id + " no longer exists.", enfe);
            }
            Objetivodemedicao objetivodemedicao = registroobjetivomedicao.getObjetivodemedicao();
            if (objetivodemedicao != null) {
                objetivodemedicao.getRegistroobjetivomedicaoList().remove(registroobjetivomedicao);
                objetivodemedicao = em.merge(objetivodemedicao);
            }
            em.remove(registroobjetivomedicao);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Registroobjetivomedicao> findRegistroobjetivomedicaoEntities() {
        return findRegistroobjetivomedicaoEntities(true, -1, -1);
    }

    public List<Registroobjetivomedicao> findRegistroobjetivomedicaoEntities(int maxResults, int firstResult) {
        return findRegistroobjetivomedicaoEntities(false, maxResults, firstResult);
    }

    private List<Registroobjetivomedicao> findRegistroobjetivomedicaoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Registroobjetivomedicao.class));
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

    public Registroobjetivomedicao findRegistroobjetivomedicao(RegistroobjetivomedicaoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Registroobjetivomedicao.class, id);
        } finally {
            em.close();
        }
    }

    public int getRegistroobjetivomedicaoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Registroobjetivomedicao> rt = cq.from(Registroobjetivomedicao.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
