/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.Objetivodemedicacao;
import model.Objetivodequestao;
import model.ObjetivodequestaoPK;

/**
 *
 * @author Spider
 */
public class ObjetivodequestaoJpaController implements Serializable {

    public ObjetivodequestaoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Objetivodequestao objetivodequestao) throws PreexistingEntityException, Exception {
        if (objetivodequestao.getObjetivodequestaoPK() == null) {
            objetivodequestao.setObjetivodequestaoPK(new ObjetivodequestaoPK());
        }
        objetivodequestao.getObjetivodequestaoPK().setObjetivoDeMedicacaoid(objetivodequestao.getObjetivodemedicacao().getObjetivodemedicacaoPK().getId());
        objetivodequestao.getObjetivodequestaoPK().setObjetivoDeMedicacaoProjetoid(objetivodequestao.getObjetivodemedicacao().getObjetivodemedicacaoPK().getProjetoid());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Objetivodemedicacao objetivodemedicacao = objetivodequestao.getObjetivodemedicacao();
            if (objetivodemedicacao != null) {
                objetivodemedicacao = em.getReference(objetivodemedicacao.getClass(), objetivodemedicacao.getObjetivodemedicacaoPK());
                objetivodequestao.setObjetivodemedicacao(objetivodemedicacao);
            }
            em.persist(objetivodequestao);
            if (objetivodemedicacao != null) {
                objetivodemedicacao.getObjetivodequestaoList().add(objetivodequestao);
                objetivodemedicacao = em.merge(objetivodemedicacao);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findObjetivodequestao(objetivodequestao.getObjetivodequestaoPK()) != null) {
                throw new PreexistingEntityException("Objetivodequestao " + objetivodequestao + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Objetivodequestao objetivodequestao) throws NonexistentEntityException, Exception {
        objetivodequestao.getObjetivodequestaoPK().setObjetivoDeMedicacaoid(objetivodequestao.getObjetivodemedicacao().getObjetivodemedicacaoPK().getId());
        objetivodequestao.getObjetivodequestaoPK().setObjetivoDeMedicacaoProjetoid(objetivodequestao.getObjetivodemedicacao().getObjetivodemedicacaoPK().getProjetoid());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Objetivodequestao persistentObjetivodequestao = em.find(Objetivodequestao.class, objetivodequestao.getObjetivodequestaoPK());
            Objetivodemedicacao objetivodemedicacaoOld = persistentObjetivodequestao.getObjetivodemedicacao();
            Objetivodemedicacao objetivodemedicacaoNew = objetivodequestao.getObjetivodemedicacao();
            if (objetivodemedicacaoNew != null) {
                objetivodemedicacaoNew = em.getReference(objetivodemedicacaoNew.getClass(), objetivodemedicacaoNew.getObjetivodemedicacaoPK());
                objetivodequestao.setObjetivodemedicacao(objetivodemedicacaoNew);
            }
            objetivodequestao = em.merge(objetivodequestao);
            if (objetivodemedicacaoOld != null && !objetivodemedicacaoOld.equals(objetivodemedicacaoNew)) {
                objetivodemedicacaoOld.getObjetivodequestaoList().remove(objetivodequestao);
                objetivodemedicacaoOld = em.merge(objetivodemedicacaoOld);
            }
            if (objetivodemedicacaoNew != null && !objetivodemedicacaoNew.equals(objetivodemedicacaoOld)) {
                objetivodemedicacaoNew.getObjetivodequestaoList().add(objetivodequestao);
                objetivodemedicacaoNew = em.merge(objetivodemedicacaoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                ObjetivodequestaoPK id = objetivodequestao.getObjetivodequestaoPK();
                if (findObjetivodequestao(id) == null) {
                    throw new NonexistentEntityException("The objetivodequestao with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(ObjetivodequestaoPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Objetivodequestao objetivodequestao;
            try {
                objetivodequestao = em.getReference(Objetivodequestao.class, id);
                objetivodequestao.getObjetivodequestaoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The objetivodequestao with id " + id + " no longer exists.", enfe);
            }
            Objetivodemedicacao objetivodemedicacao = objetivodequestao.getObjetivodemedicacao();
            if (objetivodemedicacao != null) {
                objetivodemedicacao.getObjetivodequestaoList().remove(objetivodequestao);
                objetivodemedicacao = em.merge(objetivodemedicacao);
            }
            em.remove(objetivodequestao);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Objetivodequestao> findObjetivodequestaoEntities() {
        return findObjetivodequestaoEntities(true, -1, -1);
    }

    public List<Objetivodequestao> findObjetivodequestaoEntities(int maxResults, int firstResult) {
        return findObjetivodequestaoEntities(false, maxResults, firstResult);
    }

    private List<Objetivodequestao> findObjetivodequestaoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Objetivodequestao.class));
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

    public Objetivodequestao findObjetivodequestao(ObjetivodequestaoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Objetivodequestao.class, id);
        } finally {
            em.close();
        }
    }

    public int getObjetivodequestaoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Objetivodequestao> rt = cq.from(Objetivodequestao.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
