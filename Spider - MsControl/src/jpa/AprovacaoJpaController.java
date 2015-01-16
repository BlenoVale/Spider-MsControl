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
import model.Registroaprovacao;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;
import model.Aprovacao;

/**
 *
 * @author Dan
 */
public class AprovacaoJpaController implements Serializable {

    public AprovacaoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Aprovacao aprovacao) {
        if (aprovacao.getRegistroaprovacaoList() == null) {
            aprovacao.setRegistroaprovacaoList(new ArrayList<Registroaprovacao>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Definicao definicao = aprovacao.getDefinicao();
            if (definicao != null) {
                definicao = em.getReference(definicao.getClass(), definicao.getDefinicaoPK());
                aprovacao.setDefinicao(definicao);
            }
            List<Registroaprovacao> attachedRegistroaprovacaoList = new ArrayList<Registroaprovacao>();
            for (Registroaprovacao registroaprovacaoListRegistroaprovacaoToAttach : aprovacao.getRegistroaprovacaoList()) {
                registroaprovacaoListRegistroaprovacaoToAttach = em.getReference(registroaprovacaoListRegistroaprovacaoToAttach.getClass(), registroaprovacaoListRegistroaprovacaoToAttach.getRegistroaprovacaoPK());
                attachedRegistroaprovacaoList.add(registroaprovacaoListRegistroaprovacaoToAttach);
            }
            aprovacao.setRegistroaprovacaoList(attachedRegistroaprovacaoList);
            em.persist(aprovacao);
            if (definicao != null) {
                definicao.getAprovacaoList().add(aprovacao);
                definicao = em.merge(definicao);
            }
            for (Registroaprovacao registroaprovacaoListRegistroaprovacao : aprovacao.getRegistroaprovacaoList()) {
                Aprovacao oldAprovacaoOfRegistroaprovacaoListRegistroaprovacao = registroaprovacaoListRegistroaprovacao.getAprovacao();
                registroaprovacaoListRegistroaprovacao.setAprovacao(aprovacao);
                registroaprovacaoListRegistroaprovacao = em.merge(registroaprovacaoListRegistroaprovacao);
                if (oldAprovacaoOfRegistroaprovacaoListRegistroaprovacao != null) {
                    oldAprovacaoOfRegistroaprovacaoListRegistroaprovacao.getRegistroaprovacaoList().remove(registroaprovacaoListRegistroaprovacao);
                    oldAprovacaoOfRegistroaprovacaoListRegistroaprovacao = em.merge(oldAprovacaoOfRegistroaprovacaoListRegistroaprovacao);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Aprovacao aprovacao) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Aprovacao persistentAprovacao = em.find(Aprovacao.class, aprovacao.getId());
            Definicao definicaoOld = persistentAprovacao.getDefinicao();
            Definicao definicaoNew = aprovacao.getDefinicao();
            List<Registroaprovacao> registroaprovacaoListOld = persistentAprovacao.getRegistroaprovacaoList();
            List<Registroaprovacao> registroaprovacaoListNew = aprovacao.getRegistroaprovacaoList();
            List<String> illegalOrphanMessages = null;
            for (Registroaprovacao registroaprovacaoListOldRegistroaprovacao : registroaprovacaoListOld) {
                if (!registroaprovacaoListNew.contains(registroaprovacaoListOldRegistroaprovacao)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Registroaprovacao " + registroaprovacaoListOldRegistroaprovacao + " since its aprovacao field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (definicaoNew != null) {
                definicaoNew = em.getReference(definicaoNew.getClass(), definicaoNew.getDefinicaoPK());
                aprovacao.setDefinicao(definicaoNew);
            }
            List<Registroaprovacao> attachedRegistroaprovacaoListNew = new ArrayList<Registroaprovacao>();
            for (Registroaprovacao registroaprovacaoListNewRegistroaprovacaoToAttach : registroaprovacaoListNew) {
                registroaprovacaoListNewRegistroaprovacaoToAttach = em.getReference(registroaprovacaoListNewRegistroaprovacaoToAttach.getClass(), registroaprovacaoListNewRegistroaprovacaoToAttach.getRegistroaprovacaoPK());
                attachedRegistroaprovacaoListNew.add(registroaprovacaoListNewRegistroaprovacaoToAttach);
            }
            registroaprovacaoListNew = attachedRegistroaprovacaoListNew;
            aprovacao.setRegistroaprovacaoList(registroaprovacaoListNew);
            aprovacao = em.merge(aprovacao);
            if (definicaoOld != null && !definicaoOld.equals(definicaoNew)) {
                definicaoOld.getAprovacaoList().remove(aprovacao);
                definicaoOld = em.merge(definicaoOld);
            }
            if (definicaoNew != null && !definicaoNew.equals(definicaoOld)) {
                definicaoNew.getAprovacaoList().add(aprovacao);
                definicaoNew = em.merge(definicaoNew);
            }
            for (Registroaprovacao registroaprovacaoListNewRegistroaprovacao : registroaprovacaoListNew) {
                if (!registroaprovacaoListOld.contains(registroaprovacaoListNewRegistroaprovacao)) {
                    Aprovacao oldAprovacaoOfRegistroaprovacaoListNewRegistroaprovacao = registroaprovacaoListNewRegistroaprovacao.getAprovacao();
                    registroaprovacaoListNewRegistroaprovacao.setAprovacao(aprovacao);
                    registroaprovacaoListNewRegistroaprovacao = em.merge(registroaprovacaoListNewRegistroaprovacao);
                    if (oldAprovacaoOfRegistroaprovacaoListNewRegistroaprovacao != null && !oldAprovacaoOfRegistroaprovacaoListNewRegistroaprovacao.equals(aprovacao)) {
                        oldAprovacaoOfRegistroaprovacaoListNewRegistroaprovacao.getRegistroaprovacaoList().remove(registroaprovacaoListNewRegistroaprovacao);
                        oldAprovacaoOfRegistroaprovacaoListNewRegistroaprovacao = em.merge(oldAprovacaoOfRegistroaprovacaoListNewRegistroaprovacao);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = aprovacao.getId();
                if (findAprovacao(id) == null) {
                    throw new NonexistentEntityException("The aprovacao with id " + id + " no longer exists.");
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
            Aprovacao aprovacao;
            try {
                aprovacao = em.getReference(Aprovacao.class, id);
                aprovacao.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The aprovacao with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Registroaprovacao> registroaprovacaoListOrphanCheck = aprovacao.getRegistroaprovacaoList();
            for (Registroaprovacao registroaprovacaoListOrphanCheckRegistroaprovacao : registroaprovacaoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Aprovacao (" + aprovacao + ") cannot be destroyed since the Registroaprovacao " + registroaprovacaoListOrphanCheckRegistroaprovacao + " in its registroaprovacaoList field has a non-nullable aprovacao field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Definicao definicao = aprovacao.getDefinicao();
            if (definicao != null) {
                definicao.getAprovacaoList().remove(aprovacao);
                definicao = em.merge(definicao);
            }
            em.remove(aprovacao);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Aprovacao> findAprovacaoEntities() {
        return findAprovacaoEntities(true, -1, -1);
    }

    public List<Aprovacao> findAprovacaoEntities(int maxResults, int firstResult) {
        return findAprovacaoEntities(false, maxResults, firstResult);
    }

    private List<Aprovacao> findAprovacaoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Aprovacao.class));
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

    public Aprovacao findAprovacao(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Aprovacao.class, id);
        } finally {
            em.close();
        }
    }

    public int getAprovacaoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Aprovacao> rt = cq.from(Aprovacao.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
