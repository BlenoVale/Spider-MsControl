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
import model.Projeto;
import model.Objetivodequestao;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.Objetivodemedicacao;
import model.ObjetivodemedicacaoPK;

/**
 *
 * @author Dan
 */
public class ObjetivodemedicacaoJpaController implements Serializable {

    public ObjetivodemedicacaoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Objetivodemedicacao objetivodemedicacao) throws PreexistingEntityException, Exception {
        if (objetivodemedicacao.getObjetivodemedicacaoPK() == null) {
            objetivodemedicacao.setObjetivodemedicacaoPK(new ObjetivodemedicacaoPK());
        }
        if (objetivodemedicacao.getObjetivodequestaoList() == null) {
            objetivodemedicacao.setObjetivodequestaoList(new ArrayList<Objetivodequestao>());
        }
        objetivodemedicacao.getObjetivodemedicacaoPK().setProjetoid(objetivodemedicacao.getProjeto().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Projeto projeto = objetivodemedicacao.getProjeto();
            if (projeto != null) {
                projeto = em.getReference(projeto.getClass(), projeto.getId());
                objetivodemedicacao.setProjeto(projeto);
            }
            List<Objetivodequestao> attachedObjetivodequestaoList = new ArrayList<Objetivodequestao>();
            for (Objetivodequestao objetivodequestaoListObjetivodequestaoToAttach : objetivodemedicacao.getObjetivodequestaoList()) {
                objetivodequestaoListObjetivodequestaoToAttach = em.getReference(objetivodequestaoListObjetivodequestaoToAttach.getClass(), objetivodequestaoListObjetivodequestaoToAttach.getObjetivodequestaoPK());
                attachedObjetivodequestaoList.add(objetivodequestaoListObjetivodequestaoToAttach);
            }
            objetivodemedicacao.setObjetivodequestaoList(attachedObjetivodequestaoList);
            em.persist(objetivodemedicacao);
            if (projeto != null) {
                projeto.getObjetivodemedicacaoList().add(objetivodemedicacao);
                projeto = em.merge(projeto);
            }
            for (Objetivodequestao objetivodequestaoListObjetivodequestao : objetivodemedicacao.getObjetivodequestaoList()) {
                Objetivodemedicacao oldObjetivodemedicacaoOfObjetivodequestaoListObjetivodequestao = objetivodequestaoListObjetivodequestao.getObjetivodemedicacao();
                objetivodequestaoListObjetivodequestao.setObjetivodemedicacao(objetivodemedicacao);
                objetivodequestaoListObjetivodequestao = em.merge(objetivodequestaoListObjetivodequestao);
                if (oldObjetivodemedicacaoOfObjetivodequestaoListObjetivodequestao != null) {
                    oldObjetivodemedicacaoOfObjetivodequestaoListObjetivodequestao.getObjetivodequestaoList().remove(objetivodequestaoListObjetivodequestao);
                    oldObjetivodemedicacaoOfObjetivodequestaoListObjetivodequestao = em.merge(oldObjetivodemedicacaoOfObjetivodequestaoListObjetivodequestao);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findObjetivodemedicacao(objetivodemedicacao.getObjetivodemedicacaoPK()) != null) {
                throw new PreexistingEntityException("Objetivodemedicacao " + objetivodemedicacao + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Objetivodemedicacao objetivodemedicacao) throws IllegalOrphanException, NonexistentEntityException, Exception {
        objetivodemedicacao.getObjetivodemedicacaoPK().setProjetoid(objetivodemedicacao.getProjeto().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Objetivodemedicacao persistentObjetivodemedicacao = em.find(Objetivodemedicacao.class, objetivodemedicacao.getObjetivodemedicacaoPK());
            Projeto projetoOld = persistentObjetivodemedicacao.getProjeto();
            Projeto projetoNew = objetivodemedicacao.getProjeto();
            List<Objetivodequestao> objetivodequestaoListOld = persistentObjetivodemedicacao.getObjetivodequestaoList();
            List<Objetivodequestao> objetivodequestaoListNew = objetivodemedicacao.getObjetivodequestaoList();
            List<String> illegalOrphanMessages = null;
            for (Objetivodequestao objetivodequestaoListOldObjetivodequestao : objetivodequestaoListOld) {
                if (!objetivodequestaoListNew.contains(objetivodequestaoListOldObjetivodequestao)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Objetivodequestao " + objetivodequestaoListOldObjetivodequestao + " since its objetivodemedicacao field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (projetoNew != null) {
                projetoNew = em.getReference(projetoNew.getClass(), projetoNew.getId());
                objetivodemedicacao.setProjeto(projetoNew);
            }
            List<Objetivodequestao> attachedObjetivodequestaoListNew = new ArrayList<Objetivodequestao>();
            for (Objetivodequestao objetivodequestaoListNewObjetivodequestaoToAttach : objetivodequestaoListNew) {
                objetivodequestaoListNewObjetivodequestaoToAttach = em.getReference(objetivodequestaoListNewObjetivodequestaoToAttach.getClass(), objetivodequestaoListNewObjetivodequestaoToAttach.getObjetivodequestaoPK());
                attachedObjetivodequestaoListNew.add(objetivodequestaoListNewObjetivodequestaoToAttach);
            }
            objetivodequestaoListNew = attachedObjetivodequestaoListNew;
            objetivodemedicacao.setObjetivodequestaoList(objetivodequestaoListNew);
            objetivodemedicacao = em.merge(objetivodemedicacao);
            if (projetoOld != null && !projetoOld.equals(projetoNew)) {
                projetoOld.getObjetivodemedicacaoList().remove(objetivodemedicacao);
                projetoOld = em.merge(projetoOld);
            }
            if (projetoNew != null && !projetoNew.equals(projetoOld)) {
                projetoNew.getObjetivodemedicacaoList().add(objetivodemedicacao);
                projetoNew = em.merge(projetoNew);
            }
            for (Objetivodequestao objetivodequestaoListNewObjetivodequestao : objetivodequestaoListNew) {
                if (!objetivodequestaoListOld.contains(objetivodequestaoListNewObjetivodequestao)) {
                    Objetivodemedicacao oldObjetivodemedicacaoOfObjetivodequestaoListNewObjetivodequestao = objetivodequestaoListNewObjetivodequestao.getObjetivodemedicacao();
                    objetivodequestaoListNewObjetivodequestao.setObjetivodemedicacao(objetivodemedicacao);
                    objetivodequestaoListNewObjetivodequestao = em.merge(objetivodequestaoListNewObjetivodequestao);
                    if (oldObjetivodemedicacaoOfObjetivodequestaoListNewObjetivodequestao != null && !oldObjetivodemedicacaoOfObjetivodequestaoListNewObjetivodequestao.equals(objetivodemedicacao)) {
                        oldObjetivodemedicacaoOfObjetivodequestaoListNewObjetivodequestao.getObjetivodequestaoList().remove(objetivodequestaoListNewObjetivodequestao);
                        oldObjetivodemedicacaoOfObjetivodequestaoListNewObjetivodequestao = em.merge(oldObjetivodemedicacaoOfObjetivodequestaoListNewObjetivodequestao);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                ObjetivodemedicacaoPK id = objetivodemedicacao.getObjetivodemedicacaoPK();
                if (findObjetivodemedicacao(id) == null) {
                    throw new NonexistentEntityException("The objetivodemedicacao with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(ObjetivodemedicacaoPK id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Objetivodemedicacao objetivodemedicacao;
            try {
                objetivodemedicacao = em.getReference(Objetivodemedicacao.class, id);
                objetivodemedicacao.getObjetivodemedicacaoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The objetivodemedicacao with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Objetivodequestao> objetivodequestaoListOrphanCheck = objetivodemedicacao.getObjetivodequestaoList();
            for (Objetivodequestao objetivodequestaoListOrphanCheckObjetivodequestao : objetivodequestaoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Objetivodemedicacao (" + objetivodemedicacao + ") cannot be destroyed since the Objetivodequestao " + objetivodequestaoListOrphanCheckObjetivodequestao + " in its objetivodequestaoList field has a non-nullable objetivodemedicacao field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Projeto projeto = objetivodemedicacao.getProjeto();
            if (projeto != null) {
                projeto.getObjetivodemedicacaoList().remove(objetivodemedicacao);
                projeto = em.merge(projeto);
            }
            em.remove(objetivodemedicacao);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Objetivodemedicacao> findObjetivodemedicacaoEntities() {
        return findObjetivodemedicacaoEntities(true, -1, -1);
    }

    public List<Objetivodemedicacao> findObjetivodemedicacaoEntities(int maxResults, int firstResult) {
        return findObjetivodemedicacaoEntities(false, maxResults, firstResult);
    }

    private List<Objetivodemedicacao> findObjetivodemedicacaoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Objetivodemedicacao.class));
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

    public Objetivodemedicacao findObjetivodemedicacao(ObjetivodemedicacaoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Objetivodemedicacao.class, id);
        } finally {
            em.close();
        }
    }

    public int getObjetivodemedicacaoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Objetivodemedicacao> rt = cq.from(Objetivodemedicacao.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
