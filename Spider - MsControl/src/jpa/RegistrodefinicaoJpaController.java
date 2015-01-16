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
import model.Definicao;
import model.Registrodefinicao;
import model.RegistrodefinicaoPK;

/**
 *
 * @author Dan
 */
public class RegistrodefinicaoJpaController implements Serializable {

    public RegistrodefinicaoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Registrodefinicao registrodefinicao) throws PreexistingEntityException, Exception {
        if (registrodefinicao.getRegistrodefinicaoPK() == null) {
            registrodefinicao.setRegistrodefinicaoPK(new RegistrodefinicaoPK());
        }
        registrodefinicao.getRegistrodefinicaoPK().setDefinicaoMedidaid(registrodefinicao.getDefinicao().getDefinicaoPK().getMedidaid());
        registrodefinicao.getRegistrodefinicaoPK().setDefinicaoid(registrodefinicao.getDefinicao().getDefinicaoPK().getId());
        registrodefinicao.getRegistrodefinicaoPK().setDefinicaoMedidaProjetoid(registrodefinicao.getDefinicao().getDefinicaoPK().getMedidaProjetoid());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Definicao definicao = registrodefinicao.getDefinicao();
            if (definicao != null) {
                definicao = em.getReference(definicao.getClass(), definicao.getDefinicaoPK());
                registrodefinicao.setDefinicao(definicao);
            }
            em.persist(registrodefinicao);
            if (definicao != null) {
                definicao.getRegistrodefinicaoList().add(registrodefinicao);
                definicao = em.merge(definicao);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRegistrodefinicao(registrodefinicao.getRegistrodefinicaoPK()) != null) {
                throw new PreexistingEntityException("Registrodefinicao " + registrodefinicao + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Registrodefinicao registrodefinicao) throws NonexistentEntityException, Exception {
        registrodefinicao.getRegistrodefinicaoPK().setDefinicaoMedidaid(registrodefinicao.getDefinicao().getDefinicaoPK().getMedidaid());
        registrodefinicao.getRegistrodefinicaoPK().setDefinicaoid(registrodefinicao.getDefinicao().getDefinicaoPK().getId());
        registrodefinicao.getRegistrodefinicaoPK().setDefinicaoMedidaProjetoid(registrodefinicao.getDefinicao().getDefinicaoPK().getMedidaProjetoid());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Registrodefinicao persistentRegistrodefinicao = em.find(Registrodefinicao.class, registrodefinicao.getRegistrodefinicaoPK());
            Definicao definicaoOld = persistentRegistrodefinicao.getDefinicao();
            Definicao definicaoNew = registrodefinicao.getDefinicao();
            if (definicaoNew != null) {
                definicaoNew = em.getReference(definicaoNew.getClass(), definicaoNew.getDefinicaoPK());
                registrodefinicao.setDefinicao(definicaoNew);
            }
            registrodefinicao = em.merge(registrodefinicao);
            if (definicaoOld != null && !definicaoOld.equals(definicaoNew)) {
                definicaoOld.getRegistrodefinicaoList().remove(registrodefinicao);
                definicaoOld = em.merge(definicaoOld);
            }
            if (definicaoNew != null && !definicaoNew.equals(definicaoOld)) {
                definicaoNew.getRegistrodefinicaoList().add(registrodefinicao);
                definicaoNew = em.merge(definicaoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                RegistrodefinicaoPK id = registrodefinicao.getRegistrodefinicaoPK();
                if (findRegistrodefinicao(id) == null) {
                    throw new NonexistentEntityException("The registrodefinicao with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(RegistrodefinicaoPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Registrodefinicao registrodefinicao;
            try {
                registrodefinicao = em.getReference(Registrodefinicao.class, id);
                registrodefinicao.getRegistrodefinicaoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The registrodefinicao with id " + id + " no longer exists.", enfe);
            }
            Definicao definicao = registrodefinicao.getDefinicao();
            if (definicao != null) {
                definicao.getRegistrodefinicaoList().remove(registrodefinicao);
                definicao = em.merge(definicao);
            }
            em.remove(registrodefinicao);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Registrodefinicao> findRegistrodefinicaoEntities() {
        return findRegistrodefinicaoEntities(true, -1, -1);
    }

    public List<Registrodefinicao> findRegistrodefinicaoEntities(int maxResults, int firstResult) {
        return findRegistrodefinicaoEntities(false, maxResults, firstResult);
    }

    private List<Registrodefinicao> findRegistrodefinicaoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Registrodefinicao.class));
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

    public Registrodefinicao findRegistrodefinicao(RegistrodefinicaoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Registrodefinicao.class, id);
        } finally {
            em.close();
        }
    }

    public int getRegistrodefinicaoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Registrodefinicao> rt = cq.from(Registrodefinicao.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
