/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.Medida;
import model.Composicao;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.Aprovacao;
import model.Definicao;
import model.DefinicaoPK;

/**
 *
 * @author Spider
 */
public class DefinicaoJpaController implements Serializable {

    public DefinicaoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Definicao definicao) throws PreexistingEntityException, Exception {
        if (definicao.getDefinicaoPK() == null) {
            definicao.setDefinicaoPK(new DefinicaoPK());
        }
        if (definicao.getComposicaoList() == null) {
            definicao.setComposicaoList(new ArrayList<Composicao>());
        }
        if (definicao.getAprovacaoList() == null) {
            definicao.setAprovacaoList(new ArrayList<Aprovacao>());
        }
        definicao.getDefinicaoPK().setMedidaid(definicao.getMedida().getMedidaPK().getId());
        definicao.getDefinicaoPK().setMedidaProjetoid(definicao.getMedida().getMedidaPK().getProjetoid());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Medida medida = definicao.getMedida();
            if (medida != null) {
                medida = em.getReference(medida.getClass(), medida.getMedidaPK());
                definicao.setMedida(medida);
            }
            List<Composicao> attachedComposicaoList = new ArrayList<Composicao>();
            for (Composicao composicaoListComposicaoToAttach : definicao.getComposicaoList()) {
                composicaoListComposicaoToAttach = em.getReference(composicaoListComposicaoToAttach.getClass(), composicaoListComposicaoToAttach.getId());
                attachedComposicaoList.add(composicaoListComposicaoToAttach);
            }
            definicao.setComposicaoList(attachedComposicaoList);
            List<Aprovacao> attachedAprovacaoList = new ArrayList<Aprovacao>();
            for (Aprovacao aprovacaoListAprovacaoToAttach : definicao.getAprovacaoList()) {
                aprovacaoListAprovacaoToAttach = em.getReference(aprovacaoListAprovacaoToAttach.getClass(), aprovacaoListAprovacaoToAttach.getId());
                attachedAprovacaoList.add(aprovacaoListAprovacaoToAttach);
            }
            definicao.setAprovacaoList(attachedAprovacaoList);
            em.persist(definicao);
            if (medida != null) {
                medida.getDefinicaoList().add(definicao);
                medida = em.merge(medida);
            }
            for (Composicao composicaoListComposicao : definicao.getComposicaoList()) {
                Definicao oldDefinicaoOfComposicaoListComposicao = composicaoListComposicao.getDefinicao();
                composicaoListComposicao.setDefinicao(definicao);
                composicaoListComposicao = em.merge(composicaoListComposicao);
                if (oldDefinicaoOfComposicaoListComposicao != null) {
                    oldDefinicaoOfComposicaoListComposicao.getComposicaoList().remove(composicaoListComposicao);
                    oldDefinicaoOfComposicaoListComposicao = em.merge(oldDefinicaoOfComposicaoListComposicao);
                }
            }
            for (Aprovacao aprovacaoListAprovacao : definicao.getAprovacaoList()) {
                Definicao oldDefinicaoOfAprovacaoListAprovacao = aprovacaoListAprovacao.getDefinicao();
                aprovacaoListAprovacao.setDefinicao(definicao);
                aprovacaoListAprovacao = em.merge(aprovacaoListAprovacao);
                if (oldDefinicaoOfAprovacaoListAprovacao != null) {
                    oldDefinicaoOfAprovacaoListAprovacao.getAprovacaoList().remove(aprovacaoListAprovacao);
                    oldDefinicaoOfAprovacaoListAprovacao = em.merge(oldDefinicaoOfAprovacaoListAprovacao);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDefinicao(definicao.getDefinicaoPK()) != null) {
                throw new PreexistingEntityException("Definicao " + definicao + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Definicao definicao) throws IllegalOrphanException, NonexistentEntityException, Exception {
        definicao.getDefinicaoPK().setMedidaid(definicao.getMedida().getMedidaPK().getId());
        definicao.getDefinicaoPK().setMedidaProjetoid(definicao.getMedida().getMedidaPK().getProjetoid());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Definicao persistentDefinicao = em.find(Definicao.class, definicao.getDefinicaoPK());
            Medida medidaOld = persistentDefinicao.getMedida();
            Medida medidaNew = definicao.getMedida();
            List<Composicao> composicaoListOld = persistentDefinicao.getComposicaoList();
            List<Composicao> composicaoListNew = definicao.getComposicaoList();
            List<Aprovacao> aprovacaoListOld = persistentDefinicao.getAprovacaoList();
            List<Aprovacao> aprovacaoListNew = definicao.getAprovacaoList();
            List<String> illegalOrphanMessages = null;
            for (Composicao composicaoListOldComposicao : composicaoListOld) {
                if (!composicaoListNew.contains(composicaoListOldComposicao)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Composicao " + composicaoListOldComposicao + " since its definicao field is not nullable.");
                }
            }
            for (Aprovacao aprovacaoListOldAprovacao : aprovacaoListOld) {
                if (!aprovacaoListNew.contains(aprovacaoListOldAprovacao)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Aprovacao " + aprovacaoListOldAprovacao + " since its definicao field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (medidaNew != null) {
                medidaNew = em.getReference(medidaNew.getClass(), medidaNew.getMedidaPK());
                definicao.setMedida(medidaNew);
            }
            List<Composicao> attachedComposicaoListNew = new ArrayList<Composicao>();
            for (Composicao composicaoListNewComposicaoToAttach : composicaoListNew) {
                composicaoListNewComposicaoToAttach = em.getReference(composicaoListNewComposicaoToAttach.getClass(), composicaoListNewComposicaoToAttach.getId());
                attachedComposicaoListNew.add(composicaoListNewComposicaoToAttach);
            }
            composicaoListNew = attachedComposicaoListNew;
            definicao.setComposicaoList(composicaoListNew);
            List<Aprovacao> attachedAprovacaoListNew = new ArrayList<Aprovacao>();
            for (Aprovacao aprovacaoListNewAprovacaoToAttach : aprovacaoListNew) {
                aprovacaoListNewAprovacaoToAttach = em.getReference(aprovacaoListNewAprovacaoToAttach.getClass(), aprovacaoListNewAprovacaoToAttach.getId());
                attachedAprovacaoListNew.add(aprovacaoListNewAprovacaoToAttach);
            }
            aprovacaoListNew = attachedAprovacaoListNew;
            definicao.setAprovacaoList(aprovacaoListNew);
            definicao = em.merge(definicao);
            if (medidaOld != null && !medidaOld.equals(medidaNew)) {
                medidaOld.getDefinicaoList().remove(definicao);
                medidaOld = em.merge(medidaOld);
            }
            if (medidaNew != null && !medidaNew.equals(medidaOld)) {
                medidaNew.getDefinicaoList().add(definicao);
                medidaNew = em.merge(medidaNew);
            }
            for (Composicao composicaoListNewComposicao : composicaoListNew) {
                if (!composicaoListOld.contains(composicaoListNewComposicao)) {
                    Definicao oldDefinicaoOfComposicaoListNewComposicao = composicaoListNewComposicao.getDefinicao();
                    composicaoListNewComposicao.setDefinicao(definicao);
                    composicaoListNewComposicao = em.merge(composicaoListNewComposicao);
                    if (oldDefinicaoOfComposicaoListNewComposicao != null && !oldDefinicaoOfComposicaoListNewComposicao.equals(definicao)) {
                        oldDefinicaoOfComposicaoListNewComposicao.getComposicaoList().remove(composicaoListNewComposicao);
                        oldDefinicaoOfComposicaoListNewComposicao = em.merge(oldDefinicaoOfComposicaoListNewComposicao);
                    }
                }
            }
            for (Aprovacao aprovacaoListNewAprovacao : aprovacaoListNew) {
                if (!aprovacaoListOld.contains(aprovacaoListNewAprovacao)) {
                    Definicao oldDefinicaoOfAprovacaoListNewAprovacao = aprovacaoListNewAprovacao.getDefinicao();
                    aprovacaoListNewAprovacao.setDefinicao(definicao);
                    aprovacaoListNewAprovacao = em.merge(aprovacaoListNewAprovacao);
                    if (oldDefinicaoOfAprovacaoListNewAprovacao != null && !oldDefinicaoOfAprovacaoListNewAprovacao.equals(definicao)) {
                        oldDefinicaoOfAprovacaoListNewAprovacao.getAprovacaoList().remove(aprovacaoListNewAprovacao);
                        oldDefinicaoOfAprovacaoListNewAprovacao = em.merge(oldDefinicaoOfAprovacaoListNewAprovacao);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                DefinicaoPK id = definicao.getDefinicaoPK();
                if (findDefinicao(id) == null) {
                    throw new NonexistentEntityException("The definicao with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(DefinicaoPK id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Definicao definicao;
            try {
                definicao = em.getReference(Definicao.class, id);
                definicao.getDefinicaoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The definicao with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Composicao> composicaoListOrphanCheck = definicao.getComposicaoList();
            for (Composicao composicaoListOrphanCheckComposicao : composicaoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Definicao (" + definicao + ") cannot be destroyed since the Composicao " + composicaoListOrphanCheckComposicao + " in its composicaoList field has a non-nullable definicao field.");
            }
            List<Aprovacao> aprovacaoListOrphanCheck = definicao.getAprovacaoList();
            for (Aprovacao aprovacaoListOrphanCheckAprovacao : aprovacaoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Definicao (" + definicao + ") cannot be destroyed since the Aprovacao " + aprovacaoListOrphanCheckAprovacao + " in its aprovacaoList field has a non-nullable definicao field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Medida medida = definicao.getMedida();
            if (medida != null) {
                medida.getDefinicaoList().remove(definicao);
                medida = em.merge(medida);
            }
            em.remove(definicao);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Definicao> findDefinicaoEntities() {
        return findDefinicaoEntities(true, -1, -1);
    }

    public List<Definicao> findDefinicaoEntities(int maxResults, int firstResult) {
        return findDefinicaoEntities(false, maxResults, firstResult);
    }

    private List<Definicao> findDefinicaoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Definicao.class));
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

    public Definicao findDefinicao(DefinicaoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Definicao.class, id);
        } finally {
            em.close();
        }
    }

    public int getDefinicaoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Definicao> rt = cq.from(Definicao.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
