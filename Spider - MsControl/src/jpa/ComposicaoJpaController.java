/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.Definicao;
import model.Registrocomposicao;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;
import model.Composicao;

/**
 *
 * @author Dan
 */
public class ComposicaoJpaController implements Serializable {

    public ComposicaoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Composicao composicao) {
        if (composicao.getRegistrocomposicaoList() == null) {
            composicao.setRegistrocomposicaoList(new ArrayList<Registrocomposicao>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Definicao definicao = composicao.getDefinicao();
            if (definicao != null) {
                definicao = em.getReference(definicao.getClass(), definicao.getDefinicaoPK());
                composicao.setDefinicao(definicao);
            }
            List<Registrocomposicao> attachedRegistrocomposicaoList = new ArrayList<Registrocomposicao>();
            for (Registrocomposicao registrocomposicaoListRegistrocomposicaoToAttach : composicao.getRegistrocomposicaoList()) {
                registrocomposicaoListRegistrocomposicaoToAttach = em.getReference(registrocomposicaoListRegistrocomposicaoToAttach.getClass(), registrocomposicaoListRegistrocomposicaoToAttach.getRegistrocomposicaoPK());
                attachedRegistrocomposicaoList.add(registrocomposicaoListRegistrocomposicaoToAttach);
            }
            composicao.setRegistrocomposicaoList(attachedRegistrocomposicaoList);
            em.persist(composicao);
            if (definicao != null) {
                definicao.getComposicaoList().add(composicao);
                definicao = em.merge(definicao);
            }
            for (Registrocomposicao registrocomposicaoListRegistrocomposicao : composicao.getRegistrocomposicaoList()) {
                Composicao oldComposicaoOfRegistrocomposicaoListRegistrocomposicao = registrocomposicaoListRegistrocomposicao.getComposicao();
                registrocomposicaoListRegistrocomposicao.setComposicao(composicao);
                registrocomposicaoListRegistrocomposicao = em.merge(registrocomposicaoListRegistrocomposicao);
                if (oldComposicaoOfRegistrocomposicaoListRegistrocomposicao != null) {
                    oldComposicaoOfRegistrocomposicaoListRegistrocomposicao.getRegistrocomposicaoList().remove(registrocomposicaoListRegistrocomposicao);
                    oldComposicaoOfRegistrocomposicaoListRegistrocomposicao = em.merge(oldComposicaoOfRegistrocomposicaoListRegistrocomposicao);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Composicao composicao) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Composicao persistentComposicao = em.find(Composicao.class, composicao.getId());
            Definicao definicaoOld = persistentComposicao.getDefinicao();
            Definicao definicaoNew = composicao.getDefinicao();
            List<Registrocomposicao> registrocomposicaoListOld = persistentComposicao.getRegistrocomposicaoList();
            List<Registrocomposicao> registrocomposicaoListNew = composicao.getRegistrocomposicaoList();
            List<String> illegalOrphanMessages = null;
            for (Registrocomposicao registrocomposicaoListOldRegistrocomposicao : registrocomposicaoListOld) {
                if (!registrocomposicaoListNew.contains(registrocomposicaoListOldRegistrocomposicao)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Registrocomposicao " + registrocomposicaoListOldRegistrocomposicao + " since its composicao field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (definicaoNew != null) {
                definicaoNew = em.getReference(definicaoNew.getClass(), definicaoNew.getDefinicaoPK());
                composicao.setDefinicao(definicaoNew);
            }
            List<Registrocomposicao> attachedRegistrocomposicaoListNew = new ArrayList<Registrocomposicao>();
            for (Registrocomposicao registrocomposicaoListNewRegistrocomposicaoToAttach : registrocomposicaoListNew) {
                registrocomposicaoListNewRegistrocomposicaoToAttach = em.getReference(registrocomposicaoListNewRegistrocomposicaoToAttach.getClass(), registrocomposicaoListNewRegistrocomposicaoToAttach.getRegistrocomposicaoPK());
                attachedRegistrocomposicaoListNew.add(registrocomposicaoListNewRegistrocomposicaoToAttach);
            }
            registrocomposicaoListNew = attachedRegistrocomposicaoListNew;
            composicao.setRegistrocomposicaoList(registrocomposicaoListNew);
            composicao = em.merge(composicao);
            if (definicaoOld != null && !definicaoOld.equals(definicaoNew)) {
                definicaoOld.getComposicaoList().remove(composicao);
                definicaoOld = em.merge(definicaoOld);
            }
            if (definicaoNew != null && !definicaoNew.equals(definicaoOld)) {
                definicaoNew.getComposicaoList().add(composicao);
                definicaoNew = em.merge(definicaoNew);
            }
            for (Registrocomposicao registrocomposicaoListNewRegistrocomposicao : registrocomposicaoListNew) {
                if (!registrocomposicaoListOld.contains(registrocomposicaoListNewRegistrocomposicao)) {
                    Composicao oldComposicaoOfRegistrocomposicaoListNewRegistrocomposicao = registrocomposicaoListNewRegistrocomposicao.getComposicao();
                    registrocomposicaoListNewRegistrocomposicao.setComposicao(composicao);
                    registrocomposicaoListNewRegistrocomposicao = em.merge(registrocomposicaoListNewRegistrocomposicao);
                    if (oldComposicaoOfRegistrocomposicaoListNewRegistrocomposicao != null && !oldComposicaoOfRegistrocomposicaoListNewRegistrocomposicao.equals(composicao)) {
                        oldComposicaoOfRegistrocomposicaoListNewRegistrocomposicao.getRegistrocomposicaoList().remove(registrocomposicaoListNewRegistrocomposicao);
                        oldComposicaoOfRegistrocomposicaoListNewRegistrocomposicao = em.merge(oldComposicaoOfRegistrocomposicaoListNewRegistrocomposicao);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = composicao.getId();
                if (findComposicao(id) == null) {
                    throw new NonexistentEntityException("The composicao with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Composicao composicao;
            try {
                composicao = em.getReference(Composicao.class, id);
                composicao.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The composicao with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Registrocomposicao> registrocomposicaoListOrphanCheck = composicao.getRegistrocomposicaoList();
            for (Registrocomposicao registrocomposicaoListOrphanCheckRegistrocomposicao : registrocomposicaoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Composicao (" + composicao + ") cannot be destroyed since the Registrocomposicao " + registrocomposicaoListOrphanCheckRegistrocomposicao + " in its registrocomposicaoList field has a non-nullable composicao field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Definicao definicao = composicao.getDefinicao();
            if (definicao != null) {
                definicao.getComposicaoList().remove(composicao);
                definicao = em.merge(definicao);
            }
            em.remove(composicao);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Composicao> findComposicaoEntities() {
        return findComposicaoEntities(true, -1, -1);
    }

    public List<Composicao> findComposicaoEntities(int maxResults, int firstResult) {
        return findComposicaoEntities(false, maxResults, firstResult);
    }

    private List<Composicao> findComposicaoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Composicao.class));
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

    public Composicao findComposicao(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Composicao.class, id);
        } finally {
            em.close();
        }
    }

    public int getComposicaoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Composicao> rt = cq.from(Composicao.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
