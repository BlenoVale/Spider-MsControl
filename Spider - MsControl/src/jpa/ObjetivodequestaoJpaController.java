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
import model.Objetivodemedicao;
import model.Registroobjetivoquestao;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;
import jpa.exceptions.PreexistingEntityException;
import model.Objetivodequestao;
import model.ObjetivodequestaoPK;

/**
 *
 * @author Dan
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
        if (objetivodequestao.getRegistroobjetivoquestaoList() == null) {
            objetivodequestao.setRegistroobjetivoquestaoList(new ArrayList<Registroobjetivoquestao>());
        }
        objetivodequestao.getObjetivodequestaoPK().setObjetivoDeMedicaoid(objetivodequestao.getObjetivodemedicao().getObjetivodemedicaoPK().getId());
        objetivodequestao.getObjetivodequestaoPK().setObjetivoDeMedicaoProjetoid(objetivodequestao.getObjetivodemedicao().getObjetivodemedicaoPK().getProjetoid());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Objetivodemedicao objetivodemedicao = objetivodequestao.getObjetivodemedicao();
            if (objetivodemedicao != null) {
                objetivodemedicao = em.getReference(objetivodemedicao.getClass(), objetivodemedicao.getObjetivodemedicaoPK());
                objetivodequestao.setObjetivodemedicao(objetivodemedicao);
            }
            List<Registroobjetivoquestao> attachedRegistroobjetivoquestaoList = new ArrayList<Registroobjetivoquestao>();
            for (Registroobjetivoquestao registroobjetivoquestaoListRegistroobjetivoquestaoToAttach : objetivodequestao.getRegistroobjetivoquestaoList()) {
                registroobjetivoquestaoListRegistroobjetivoquestaoToAttach = em.getReference(registroobjetivoquestaoListRegistroobjetivoquestaoToAttach.getClass(), registroobjetivoquestaoListRegistroobjetivoquestaoToAttach.getRegistroobjetivoquestaoPK());
                attachedRegistroobjetivoquestaoList.add(registroobjetivoquestaoListRegistroobjetivoquestaoToAttach);
            }
            objetivodequestao.setRegistroobjetivoquestaoList(attachedRegistroobjetivoquestaoList);
            em.persist(objetivodequestao);
            if (objetivodemedicao != null) {
                objetivodemedicao.getObjetivodequestaoList().add(objetivodequestao);
                objetivodemedicao = em.merge(objetivodemedicao);
            }
            for (Registroobjetivoquestao registroobjetivoquestaoListRegistroobjetivoquestao : objetivodequestao.getRegistroobjetivoquestaoList()) {
                Objetivodequestao oldObjetivodequestaoOfRegistroobjetivoquestaoListRegistroobjetivoquestao = registroobjetivoquestaoListRegistroobjetivoquestao.getObjetivodequestao();
                registroobjetivoquestaoListRegistroobjetivoquestao.setObjetivodequestao(objetivodequestao);
                registroobjetivoquestaoListRegistroobjetivoquestao = em.merge(registroobjetivoquestaoListRegistroobjetivoquestao);
                if (oldObjetivodequestaoOfRegistroobjetivoquestaoListRegistroobjetivoquestao != null) {
                    oldObjetivodequestaoOfRegistroobjetivoquestaoListRegistroobjetivoquestao.getRegistroobjetivoquestaoList().remove(registroobjetivoquestaoListRegistroobjetivoquestao);
                    oldObjetivodequestaoOfRegistroobjetivoquestaoListRegistroobjetivoquestao = em.merge(oldObjetivodequestaoOfRegistroobjetivoquestaoListRegistroobjetivoquestao);
                }
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

    public void edit(Objetivodequestao objetivodequestao) throws IllegalOrphanException, NonexistentEntityException, Exception {
        objetivodequestao.getObjetivodequestaoPK().setObjetivoDeMedicaoid(objetivodequestao.getObjetivodemedicao().getObjetivodemedicaoPK().getId());
        objetivodequestao.getObjetivodequestaoPK().setObjetivoDeMedicaoProjetoid(objetivodequestao.getObjetivodemedicao().getObjetivodemedicaoPK().getProjetoid());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Objetivodequestao persistentObjetivodequestao = em.find(Objetivodequestao.class, objetivodequestao.getObjetivodequestaoPK());
            Objetivodemedicao objetivodemedicaoOld = persistentObjetivodequestao.getObjetivodemedicao();
            Objetivodemedicao objetivodemedicaoNew = objetivodequestao.getObjetivodemedicao();
            List<Registroobjetivoquestao> registroobjetivoquestaoListOld = persistentObjetivodequestao.getRegistroobjetivoquestaoList();
            List<Registroobjetivoquestao> registroobjetivoquestaoListNew = objetivodequestao.getRegistroobjetivoquestaoList();
            List<String> illegalOrphanMessages = null;
            for (Registroobjetivoquestao registroobjetivoquestaoListOldRegistroobjetivoquestao : registroobjetivoquestaoListOld) {
                if (!registroobjetivoquestaoListNew.contains(registroobjetivoquestaoListOldRegistroobjetivoquestao)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Registroobjetivoquestao " + registroobjetivoquestaoListOldRegistroobjetivoquestao + " since its objetivodequestao field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (objetivodemedicaoNew != null) {
                objetivodemedicaoNew = em.getReference(objetivodemedicaoNew.getClass(), objetivodemedicaoNew.getObjetivodemedicaoPK());
                objetivodequestao.setObjetivodemedicao(objetivodemedicaoNew);
            }
            List<Registroobjetivoquestao> attachedRegistroobjetivoquestaoListNew = new ArrayList<Registroobjetivoquestao>();
            for (Registroobjetivoquestao registroobjetivoquestaoListNewRegistroobjetivoquestaoToAttach : registroobjetivoquestaoListNew) {
                registroobjetivoquestaoListNewRegistroobjetivoquestaoToAttach = em.getReference(registroobjetivoquestaoListNewRegistroobjetivoquestaoToAttach.getClass(), registroobjetivoquestaoListNewRegistroobjetivoquestaoToAttach.getRegistroobjetivoquestaoPK());
                attachedRegistroobjetivoquestaoListNew.add(registroobjetivoquestaoListNewRegistroobjetivoquestaoToAttach);
            }
            registroobjetivoquestaoListNew = attachedRegistroobjetivoquestaoListNew;
            objetivodequestao.setRegistroobjetivoquestaoList(registroobjetivoquestaoListNew);
            objetivodequestao = em.merge(objetivodequestao);
            if (objetivodemedicaoOld != null && !objetivodemedicaoOld.equals(objetivodemedicaoNew)) {
                objetivodemedicaoOld.getObjetivodequestaoList().remove(objetivodequestao);
                objetivodemedicaoOld = em.merge(objetivodemedicaoOld);
            }
            if (objetivodemedicaoNew != null && !objetivodemedicaoNew.equals(objetivodemedicaoOld)) {
                objetivodemedicaoNew.getObjetivodequestaoList().add(objetivodequestao);
                objetivodemedicaoNew = em.merge(objetivodemedicaoNew);
            }
            for (Registroobjetivoquestao registroobjetivoquestaoListNewRegistroobjetivoquestao : registroobjetivoquestaoListNew) {
                if (!registroobjetivoquestaoListOld.contains(registroobjetivoquestaoListNewRegistroobjetivoquestao)) {
                    Objetivodequestao oldObjetivodequestaoOfRegistroobjetivoquestaoListNewRegistroobjetivoquestao = registroobjetivoquestaoListNewRegistroobjetivoquestao.getObjetivodequestao();
                    registroobjetivoquestaoListNewRegistroobjetivoquestao.setObjetivodequestao(objetivodequestao);
                    registroobjetivoquestaoListNewRegistroobjetivoquestao = em.merge(registroobjetivoquestaoListNewRegistroobjetivoquestao);
                    if (oldObjetivodequestaoOfRegistroobjetivoquestaoListNewRegistroobjetivoquestao != null && !oldObjetivodequestaoOfRegistroobjetivoquestaoListNewRegistroobjetivoquestao.equals(objetivodequestao)) {
                        oldObjetivodequestaoOfRegistroobjetivoquestaoListNewRegistroobjetivoquestao.getRegistroobjetivoquestaoList().remove(registroobjetivoquestaoListNewRegistroobjetivoquestao);
                        oldObjetivodequestaoOfRegistroobjetivoquestaoListNewRegistroobjetivoquestao = em.merge(oldObjetivodequestaoOfRegistroobjetivoquestaoListNewRegistroobjetivoquestao);
                    }
                }
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

    public void destroy(ObjetivodequestaoPK id) throws IllegalOrphanException, NonexistentEntityException {
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
            List<String> illegalOrphanMessages = null;
            List<Registroobjetivoquestao> registroobjetivoquestaoListOrphanCheck = objetivodequestao.getRegistroobjetivoquestaoList();
            for (Registroobjetivoquestao registroobjetivoquestaoListOrphanCheckRegistroobjetivoquestao : registroobjetivoquestaoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Objetivodequestao (" + objetivodequestao + ") cannot be destroyed since the Registroobjetivoquestao " + registroobjetivoquestaoListOrphanCheckRegistroobjetivoquestao + " in its registroobjetivoquestaoList field has a non-nullable objetivodequestao field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Objetivodemedicao objetivodemedicao = objetivodequestao.getObjetivodemedicao();
            if (objetivodemedicao != null) {
                objetivodemedicao.getObjetivodequestaoList().remove(objetivodequestao);
                objetivodemedicao = em.merge(objetivodemedicao);
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
