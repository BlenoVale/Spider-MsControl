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
import model.Objetivodequestao;
import model.Registroobjetivoquestao;
import model.RegistroobjetivoquestaoPK;

/**
 *
 * @author Dan
 */
public class RegistroobjetivoquestaoJpaController implements Serializable {

    public RegistroobjetivoquestaoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Registroobjetivoquestao registroobjetivoquestao) throws PreexistingEntityException, Exception {
        if (registroobjetivoquestao.getRegistroobjetivoquestaoPK() == null) {
            registroobjetivoquestao.setRegistroobjetivoquestaoPK(new RegistroobjetivoquestaoPK());
        }
        registroobjetivoquestao.getRegistroobjetivoquestaoPK().setObjetivoDeQuestaoid(registroobjetivoquestao.getObjetivodequestao().getObjetivodequestaoPK().getId());
        registroobjetivoquestao.getRegistroobjetivoquestaoPK().setObjetivoDeQuestaoObjetivoDeMedicaoProjetoid(registroobjetivoquestao.getObjetivodequestao().getObjetivodequestaoPK().getObjetivoDeMedicaoProjetoid());
        registroobjetivoquestao.getRegistroobjetivoquestaoPK().setObjetivoDeQuestaoObjetivoDeMedicaoid(registroobjetivoquestao.getObjetivodequestao().getObjetivodequestaoPK().getObjetivoDeMedicaoid());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Objetivodequestao objetivodequestao = registroobjetivoquestao.getObjetivodequestao();
            if (objetivodequestao != null) {
                objetivodequestao = em.getReference(objetivodequestao.getClass(), objetivodequestao.getObjetivodequestaoPK());
                registroobjetivoquestao.setObjetivodequestao(objetivodequestao);
            }
            em.persist(registroobjetivoquestao);
            if (objetivodequestao != null) {
                objetivodequestao.getRegistroobjetivoquestaoList().add(registroobjetivoquestao);
                objetivodequestao = em.merge(objetivodequestao);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRegistroobjetivoquestao(registroobjetivoquestao.getRegistroobjetivoquestaoPK()) != null) {
                throw new PreexistingEntityException("Registroobjetivoquestao " + registroobjetivoquestao + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Registroobjetivoquestao registroobjetivoquestao) throws NonexistentEntityException, Exception {
        registroobjetivoquestao.getRegistroobjetivoquestaoPK().setObjetivoDeQuestaoid(registroobjetivoquestao.getObjetivodequestao().getObjetivodequestaoPK().getId());
        registroobjetivoquestao.getRegistroobjetivoquestaoPK().setObjetivoDeQuestaoObjetivoDeMedicaoProjetoid(registroobjetivoquestao.getObjetivodequestao().getObjetivodequestaoPK().getObjetivoDeMedicaoProjetoid());
        registroobjetivoquestao.getRegistroobjetivoquestaoPK().setObjetivoDeQuestaoObjetivoDeMedicaoid(registroobjetivoquestao.getObjetivodequestao().getObjetivodequestaoPK().getObjetivoDeMedicaoid());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Registroobjetivoquestao persistentRegistroobjetivoquestao = em.find(Registroobjetivoquestao.class, registroobjetivoquestao.getRegistroobjetivoquestaoPK());
            Objetivodequestao objetivodequestaoOld = persistentRegistroobjetivoquestao.getObjetivodequestao();
            Objetivodequestao objetivodequestaoNew = registroobjetivoquestao.getObjetivodequestao();
            if (objetivodequestaoNew != null) {
                objetivodequestaoNew = em.getReference(objetivodequestaoNew.getClass(), objetivodequestaoNew.getObjetivodequestaoPK());
                registroobjetivoquestao.setObjetivodequestao(objetivodequestaoNew);
            }
            registroobjetivoquestao = em.merge(registroobjetivoquestao);
            if (objetivodequestaoOld != null && !objetivodequestaoOld.equals(objetivodequestaoNew)) {
                objetivodequestaoOld.getRegistroobjetivoquestaoList().remove(registroobjetivoquestao);
                objetivodequestaoOld = em.merge(objetivodequestaoOld);
            }
            if (objetivodequestaoNew != null && !objetivodequestaoNew.equals(objetivodequestaoOld)) {
                objetivodequestaoNew.getRegistroobjetivoquestaoList().add(registroobjetivoquestao);
                objetivodequestaoNew = em.merge(objetivodequestaoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                RegistroobjetivoquestaoPK id = registroobjetivoquestao.getRegistroobjetivoquestaoPK();
                if (findRegistroobjetivoquestao(id) == null) {
                    throw new NonexistentEntityException("The registroobjetivoquestao with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(RegistroobjetivoquestaoPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Registroobjetivoquestao registroobjetivoquestao;
            try {
                registroobjetivoquestao = em.getReference(Registroobjetivoquestao.class, id);
                registroobjetivoquestao.getRegistroobjetivoquestaoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The registroobjetivoquestao with id " + id + " no longer exists.", enfe);
            }
            Objetivodequestao objetivodequestao = registroobjetivoquestao.getObjetivodequestao();
            if (objetivodequestao != null) {
                objetivodequestao.getRegistroobjetivoquestaoList().remove(registroobjetivoquestao);
                objetivodequestao = em.merge(objetivodequestao);
            }
            em.remove(registroobjetivoquestao);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Registroobjetivoquestao> findRegistroobjetivoquestaoEntities() {
        return findRegistroobjetivoquestaoEntities(true, -1, -1);
    }

    public List<Registroobjetivoquestao> findRegistroobjetivoquestaoEntities(int maxResults, int firstResult) {
        return findRegistroobjetivoquestaoEntities(false, maxResults, firstResult);
    }

    private List<Registroobjetivoquestao> findRegistroobjetivoquestaoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Registroobjetivoquestao.class));
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

    public Registroobjetivoquestao findRegistroobjetivoquestao(RegistroobjetivoquestaoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Registroobjetivoquestao.class, id);
        } finally {
            em.close();
        }
    }

    public int getRegistroobjetivoquestaoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Registroobjetivoquestao> rt = cq.from(Registroobjetivoquestao.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
