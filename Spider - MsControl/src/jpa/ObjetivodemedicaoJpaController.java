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
import model.Projeto;
import model.Objetivodequestao;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;
import jpa.exceptions.PreexistingEntityException;
import model.Objetivodemedicao;
import model.ObjetivodemedicaoPK;
import model.Registroobjetivomedicao;

/**
 *
 * @author Spider
 */
public class ObjetivodemedicaoJpaController implements Serializable {

    public ObjetivodemedicaoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Objetivodemedicao objetivodemedicao) throws PreexistingEntityException, Exception {
        if (objetivodemedicao.getObjetivodemedicaoPK() == null) {
            objetivodemedicao.setObjetivodemedicaoPK(new ObjetivodemedicaoPK());
        }
        if (objetivodemedicao.getObjetivodequestaoList() == null) {
            objetivodemedicao.setObjetivodequestaoList(new ArrayList<Objetivodequestao>());
        }
        if (objetivodemedicao.getRegistroobjetivomedicaoList() == null) {
            objetivodemedicao.setRegistroobjetivomedicaoList(new ArrayList<Registroobjetivomedicao>());
        }
        objetivodemedicao.getObjetivodemedicaoPK().setProjetoid(objetivodemedicao.getProjeto().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Projeto projeto = objetivodemedicao.getProjeto();
            if (projeto != null) {
                projeto = em.getReference(projeto.getClass(), projeto.getId());
                objetivodemedicao.setProjeto(projeto);
            }
            List<Objetivodequestao> attachedObjetivodequestaoList = new ArrayList<Objetivodequestao>();
            for (Objetivodequestao objetivodequestaoListObjetivodequestaoToAttach : objetivodemedicao.getObjetivodequestaoList()) {
                objetivodequestaoListObjetivodequestaoToAttach = em.getReference(objetivodequestaoListObjetivodequestaoToAttach.getClass(), objetivodequestaoListObjetivodequestaoToAttach.getId());
                attachedObjetivodequestaoList.add(objetivodequestaoListObjetivodequestaoToAttach);
            }
            objetivodemedicao.setObjetivodequestaoList(attachedObjetivodequestaoList);
            List<Registroobjetivomedicao> attachedRegistroobjetivomedicaoList = new ArrayList<Registroobjetivomedicao>();
            for (Registroobjetivomedicao registroobjetivomedicaoListRegistroobjetivomedicaoToAttach : objetivodemedicao.getRegistroobjetivomedicaoList()) {
                registroobjetivomedicaoListRegistroobjetivomedicaoToAttach = em.getReference(registroobjetivomedicaoListRegistroobjetivomedicaoToAttach.getClass(), registroobjetivomedicaoListRegistroobjetivomedicaoToAttach.getId());
                attachedRegistroobjetivomedicaoList.add(registroobjetivomedicaoListRegistroobjetivomedicaoToAttach);
            }
            objetivodemedicao.setRegistroobjetivomedicaoList(attachedRegistroobjetivomedicaoList);
            em.persist(objetivodemedicao);
            if (projeto != null) {
                projeto.getObjetivodemedicaoList().add(objetivodemedicao);
                projeto = em.merge(projeto);
            }
            for (Objetivodequestao objetivodequestaoListObjetivodequestao : objetivodemedicao.getObjetivodequestaoList()) {
                Objetivodemedicao oldObjetivodemedicaoOfObjetivodequestaoListObjetivodequestao = objetivodequestaoListObjetivodequestao.getObjetivodemedicao();
                objetivodequestaoListObjetivodequestao.setObjetivodemedicao(objetivodemedicao);
                objetivodequestaoListObjetivodequestao = em.merge(objetivodequestaoListObjetivodequestao);
                if (oldObjetivodemedicaoOfObjetivodequestaoListObjetivodequestao != null) {
                    oldObjetivodemedicaoOfObjetivodequestaoListObjetivodequestao.getObjetivodequestaoList().remove(objetivodequestaoListObjetivodequestao);
                    oldObjetivodemedicaoOfObjetivodequestaoListObjetivodequestao = em.merge(oldObjetivodemedicaoOfObjetivodequestaoListObjetivodequestao);
                }
            }
            for (Registroobjetivomedicao registroobjetivomedicaoListRegistroobjetivomedicao : objetivodemedicao.getRegistroobjetivomedicaoList()) {
                Objetivodemedicao oldObjetivodemedicaoOfRegistroobjetivomedicaoListRegistroobjetivomedicao = registroobjetivomedicaoListRegistroobjetivomedicao.getObjetivodemedicao();
                registroobjetivomedicaoListRegistroobjetivomedicao.setObjetivodemedicao(objetivodemedicao);
                registroobjetivomedicaoListRegistroobjetivomedicao = em.merge(registroobjetivomedicaoListRegistroobjetivomedicao);
                if (oldObjetivodemedicaoOfRegistroobjetivomedicaoListRegistroobjetivomedicao != null) {
                    oldObjetivodemedicaoOfRegistroobjetivomedicaoListRegistroobjetivomedicao.getRegistroobjetivomedicaoList().remove(registroobjetivomedicaoListRegistroobjetivomedicao);
                    oldObjetivodemedicaoOfRegistroobjetivomedicaoListRegistroobjetivomedicao = em.merge(oldObjetivodemedicaoOfRegistroobjetivomedicaoListRegistroobjetivomedicao);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findObjetivodemedicao(objetivodemedicao.getObjetivodemedicaoPK()) != null) {
                throw new PreexistingEntityException("Objetivodemedicao " + objetivodemedicao + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Objetivodemedicao objetivodemedicao) throws IllegalOrphanException, NonexistentEntityException, Exception {
        objetivodemedicao.getObjetivodemedicaoPK().setProjetoid(objetivodemedicao.getProjeto().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Objetivodemedicao persistentObjetivodemedicao = em.find(Objetivodemedicao.class, objetivodemedicao.getObjetivodemedicaoPK());
            Projeto projetoOld = persistentObjetivodemedicao.getProjeto();
            Projeto projetoNew = objetivodemedicao.getProjeto();
            List<Objetivodequestao> objetivodequestaoListOld = persistentObjetivodemedicao.getObjetivodequestaoList();
            List<Objetivodequestao> objetivodequestaoListNew = objetivodemedicao.getObjetivodequestaoList();
            List<Registroobjetivomedicao> registroobjetivomedicaoListOld = persistentObjetivodemedicao.getRegistroobjetivomedicaoList();
            List<Registroobjetivomedicao> registroobjetivomedicaoListNew = objetivodemedicao.getRegistroobjetivomedicaoList();
            List<String> illegalOrphanMessages = null;
            for (Objetivodequestao objetivodequestaoListOldObjetivodequestao : objetivodequestaoListOld) {
                if (!objetivodequestaoListNew.contains(objetivodequestaoListOldObjetivodequestao)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Objetivodequestao " + objetivodequestaoListOldObjetivodequestao + " since its objetivodemedicao field is not nullable.");
                }
            }
            for (Registroobjetivomedicao registroobjetivomedicaoListOldRegistroobjetivomedicao : registroobjetivomedicaoListOld) {
                if (!registroobjetivomedicaoListNew.contains(registroobjetivomedicaoListOldRegistroobjetivomedicao)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Registroobjetivomedicao " + registroobjetivomedicaoListOldRegistroobjetivomedicao + " since its objetivodemedicao field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (projetoNew != null) {
                projetoNew = em.getReference(projetoNew.getClass(), projetoNew.getId());
                objetivodemedicao.setProjeto(projetoNew);
            }
            List<Objetivodequestao> attachedObjetivodequestaoListNew = new ArrayList<Objetivodequestao>();
            for (Objetivodequestao objetivodequestaoListNewObjetivodequestaoToAttach : objetivodequestaoListNew) {
                objetivodequestaoListNewObjetivodequestaoToAttach = em.getReference(objetivodequestaoListNewObjetivodequestaoToAttach.getClass(), objetivodequestaoListNewObjetivodequestaoToAttach.getId());
                attachedObjetivodequestaoListNew.add(objetivodequestaoListNewObjetivodequestaoToAttach);
            }
            objetivodequestaoListNew = attachedObjetivodequestaoListNew;
            objetivodemedicao.setObjetivodequestaoList(objetivodequestaoListNew);
            List<Registroobjetivomedicao> attachedRegistroobjetivomedicaoListNew = new ArrayList<Registroobjetivomedicao>();
            for (Registroobjetivomedicao registroobjetivomedicaoListNewRegistroobjetivomedicaoToAttach : registroobjetivomedicaoListNew) {
                registroobjetivomedicaoListNewRegistroobjetivomedicaoToAttach = em.getReference(registroobjetivomedicaoListNewRegistroobjetivomedicaoToAttach.getClass(), registroobjetivomedicaoListNewRegistroobjetivomedicaoToAttach.getId());
                attachedRegistroobjetivomedicaoListNew.add(registroobjetivomedicaoListNewRegistroobjetivomedicaoToAttach);
            }
            registroobjetivomedicaoListNew = attachedRegistroobjetivomedicaoListNew;
            objetivodemedicao.setRegistroobjetivomedicaoList(registroobjetivomedicaoListNew);
            objetivodemedicao = em.merge(objetivodemedicao);
            if (projetoOld != null && !projetoOld.equals(projetoNew)) {
                projetoOld.getObjetivodemedicaoList().remove(objetivodemedicao);
                projetoOld = em.merge(projetoOld);
            }
            if (projetoNew != null && !projetoNew.equals(projetoOld)) {
                projetoNew.getObjetivodemedicaoList().add(objetivodemedicao);
                projetoNew = em.merge(projetoNew);
            }
            for (Objetivodequestao objetivodequestaoListNewObjetivodequestao : objetivodequestaoListNew) {
                if (!objetivodequestaoListOld.contains(objetivodequestaoListNewObjetivodequestao)) {
                    Objetivodemedicao oldObjetivodemedicaoOfObjetivodequestaoListNewObjetivodequestao = objetivodequestaoListNewObjetivodequestao.getObjetivodemedicao();
                    objetivodequestaoListNewObjetivodequestao.setObjetivodemedicao(objetivodemedicao);
                    objetivodequestaoListNewObjetivodequestao = em.merge(objetivodequestaoListNewObjetivodequestao);
                    if (oldObjetivodemedicaoOfObjetivodequestaoListNewObjetivodequestao != null && !oldObjetivodemedicaoOfObjetivodequestaoListNewObjetivodequestao.equals(objetivodemedicao)) {
                        oldObjetivodemedicaoOfObjetivodequestaoListNewObjetivodequestao.getObjetivodequestaoList().remove(objetivodequestaoListNewObjetivodequestao);
                        oldObjetivodemedicaoOfObjetivodequestaoListNewObjetivodequestao = em.merge(oldObjetivodemedicaoOfObjetivodequestaoListNewObjetivodequestao);
                    }
                }
            }
            for (Registroobjetivomedicao registroobjetivomedicaoListNewRegistroobjetivomedicao : registroobjetivomedicaoListNew) {
                if (!registroobjetivomedicaoListOld.contains(registroobjetivomedicaoListNewRegistroobjetivomedicao)) {
                    Objetivodemedicao oldObjetivodemedicaoOfRegistroobjetivomedicaoListNewRegistroobjetivomedicao = registroobjetivomedicaoListNewRegistroobjetivomedicao.getObjetivodemedicao();
                    registroobjetivomedicaoListNewRegistroobjetivomedicao.setObjetivodemedicao(objetivodemedicao);
                    registroobjetivomedicaoListNewRegistroobjetivomedicao = em.merge(registroobjetivomedicaoListNewRegistroobjetivomedicao);
                    if (oldObjetivodemedicaoOfRegistroobjetivomedicaoListNewRegistroobjetivomedicao != null && !oldObjetivodemedicaoOfRegistroobjetivomedicaoListNewRegistroobjetivomedicao.equals(objetivodemedicao)) {
                        oldObjetivodemedicaoOfRegistroobjetivomedicaoListNewRegistroobjetivomedicao.getRegistroobjetivomedicaoList().remove(registroobjetivomedicaoListNewRegistroobjetivomedicao);
                        oldObjetivodemedicaoOfRegistroobjetivomedicaoListNewRegistroobjetivomedicao = em.merge(oldObjetivodemedicaoOfRegistroobjetivomedicaoListNewRegistroobjetivomedicao);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                ObjetivodemedicaoPK id = objetivodemedicao.getObjetivodemedicaoPK();
                if (findObjetivodemedicao(id) == null) {
                    throw new NonexistentEntityException("The objetivodemedicao with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(ObjetivodemedicaoPK id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Objetivodemedicao objetivodemedicao;
            try {
                objetivodemedicao = em.getReference(Objetivodemedicao.class, id);
                objetivodemedicao.getObjetivodemedicaoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The objetivodemedicao with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Objetivodequestao> objetivodequestaoListOrphanCheck = objetivodemedicao.getObjetivodequestaoList();
            for (Objetivodequestao objetivodequestaoListOrphanCheckObjetivodequestao : objetivodequestaoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Objetivodemedicao (" + objetivodemedicao + ") cannot be destroyed since the Objetivodequestao " + objetivodequestaoListOrphanCheckObjetivodequestao + " in its objetivodequestaoList field has a non-nullable objetivodemedicao field.");
            }
            List<Registroobjetivomedicao> registroobjetivomedicaoListOrphanCheck = objetivodemedicao.getRegistroobjetivomedicaoList();
            for (Registroobjetivomedicao registroobjetivomedicaoListOrphanCheckRegistroobjetivomedicao : registroobjetivomedicaoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Objetivodemedicao (" + objetivodemedicao + ") cannot be destroyed since the Registroobjetivomedicao " + registroobjetivomedicaoListOrphanCheckRegistroobjetivomedicao + " in its registroobjetivomedicaoList field has a non-nullable objetivodemedicao field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Projeto projeto = objetivodemedicao.getProjeto();
            if (projeto != null) {
                projeto.getObjetivodemedicaoList().remove(objetivodemedicao);
                projeto = em.merge(projeto);
            }
            em.remove(objetivodemedicao);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Objetivodemedicao> findObjetivodemedicaoEntities() {
        return findObjetivodemedicaoEntities(true, -1, -1);
    }

    public List<Objetivodemedicao> findObjetivodemedicaoEntities(int maxResults, int firstResult) {
        return findObjetivodemedicaoEntities(false, maxResults, firstResult);
    }

    private List<Objetivodemedicao> findObjetivodemedicaoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Objetivodemedicao.class));
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

    public Objetivodemedicao findObjetivodemedicao(ObjetivodemedicaoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Objetivodemedicao.class, id);
        } finally {
            em.close();
        }
    }

    public int getObjetivodemedicaoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Objetivodemedicao> rt = cq.from(Objetivodemedicao.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
