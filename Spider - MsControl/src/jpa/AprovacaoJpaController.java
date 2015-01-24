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
import model.Medida;
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
 * @author Spider
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
            Medida medida = aprovacao.getMedida();
            if (medida != null) {
                medida = em.getReference(medida.getClass(), medida.getMedidaPK());
                aprovacao.setMedida(medida);
            }
            List<Registroaprovacao> attachedRegistroaprovacaoList = new ArrayList<Registroaprovacao>();
            for (Registroaprovacao registroaprovacaoListRegistroaprovacaoToAttach : aprovacao.getRegistroaprovacaoList()) {
                registroaprovacaoListRegistroaprovacaoToAttach = em.getReference(registroaprovacaoListRegistroaprovacaoToAttach.getClass(), registroaprovacaoListRegistroaprovacaoToAttach.getId());
                attachedRegistroaprovacaoList.add(registroaprovacaoListRegistroaprovacaoToAttach);
            }
            aprovacao.setRegistroaprovacaoList(attachedRegistroaprovacaoList);
            em.persist(aprovacao);
            if (medida != null) {
                medida.getAprovacaoList().add(aprovacao);
                medida = em.merge(medida);
            }
            for (Registroaprovacao registroaprovacaoListRegistroaprovacao : aprovacao.getRegistroaprovacaoList()) {
                Aprovacao oldAprovacaoidOfRegistroaprovacaoListRegistroaprovacao = registroaprovacaoListRegistroaprovacao.getAprovacaoid();
                registroaprovacaoListRegistroaprovacao.setAprovacaoid(aprovacao);
                registroaprovacaoListRegistroaprovacao = em.merge(registroaprovacaoListRegistroaprovacao);
                if (oldAprovacaoidOfRegistroaprovacaoListRegistroaprovacao != null) {
                    oldAprovacaoidOfRegistroaprovacaoListRegistroaprovacao.getRegistroaprovacaoList().remove(registroaprovacaoListRegistroaprovacao);
                    oldAprovacaoidOfRegistroaprovacaoListRegistroaprovacao = em.merge(oldAprovacaoidOfRegistroaprovacaoListRegistroaprovacao);
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
            Medida medidaOld = persistentAprovacao.getMedida();
            Medida medidaNew = aprovacao.getMedida();
            List<Registroaprovacao> registroaprovacaoListOld = persistentAprovacao.getRegistroaprovacaoList();
            List<Registroaprovacao> registroaprovacaoListNew = aprovacao.getRegistroaprovacaoList();
            List<String> illegalOrphanMessages = null;
            for (Registroaprovacao registroaprovacaoListOldRegistroaprovacao : registroaprovacaoListOld) {
                if (!registroaprovacaoListNew.contains(registroaprovacaoListOldRegistroaprovacao)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Registroaprovacao " + registroaprovacaoListOldRegistroaprovacao + " since its aprovacaoid field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (medidaNew != null) {
                medidaNew = em.getReference(medidaNew.getClass(), medidaNew.getMedidaPK());
                aprovacao.setMedida(medidaNew);
            }
            List<Registroaprovacao> attachedRegistroaprovacaoListNew = new ArrayList<Registroaprovacao>();
            for (Registroaprovacao registroaprovacaoListNewRegistroaprovacaoToAttach : registroaprovacaoListNew) {
                registroaprovacaoListNewRegistroaprovacaoToAttach = em.getReference(registroaprovacaoListNewRegistroaprovacaoToAttach.getClass(), registroaprovacaoListNewRegistroaprovacaoToAttach.getId());
                attachedRegistroaprovacaoListNew.add(registroaprovacaoListNewRegistroaprovacaoToAttach);
            }
            registroaprovacaoListNew = attachedRegistroaprovacaoListNew;
            aprovacao.setRegistroaprovacaoList(registroaprovacaoListNew);
            aprovacao = em.merge(aprovacao);
            if (medidaOld != null && !medidaOld.equals(medidaNew)) {
                medidaOld.getAprovacaoList().remove(aprovacao);
                medidaOld = em.merge(medidaOld);
            }
            if (medidaNew != null && !medidaNew.equals(medidaOld)) {
                medidaNew.getAprovacaoList().add(aprovacao);
                medidaNew = em.merge(medidaNew);
            }
            for (Registroaprovacao registroaprovacaoListNewRegistroaprovacao : registroaprovacaoListNew) {
                if (!registroaprovacaoListOld.contains(registroaprovacaoListNewRegistroaprovacao)) {
                    Aprovacao oldAprovacaoidOfRegistroaprovacaoListNewRegistroaprovacao = registroaprovacaoListNewRegistroaprovacao.getAprovacaoid();
                    registroaprovacaoListNewRegistroaprovacao.setAprovacaoid(aprovacao);
                    registroaprovacaoListNewRegistroaprovacao = em.merge(registroaprovacaoListNewRegistroaprovacao);
                    if (oldAprovacaoidOfRegistroaprovacaoListNewRegistroaprovacao != null && !oldAprovacaoidOfRegistroaprovacaoListNewRegistroaprovacao.equals(aprovacao)) {
                        oldAprovacaoidOfRegistroaprovacaoListNewRegistroaprovacao.getRegistroaprovacaoList().remove(registroaprovacaoListNewRegistroaprovacao);
                        oldAprovacaoidOfRegistroaprovacaoListNewRegistroaprovacao = em.merge(oldAprovacaoidOfRegistroaprovacaoListNewRegistroaprovacao);
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
                illegalOrphanMessages.add("This Aprovacao (" + aprovacao + ") cannot be destroyed since the Registroaprovacao " + registroaprovacaoListOrphanCheckRegistroaprovacao + " in its registroaprovacaoList field has a non-nullable aprovacaoid field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Medida medida = aprovacao.getMedida();
            if (medida != null) {
                medida.getAprovacaoList().remove(aprovacao);
                medida = em.merge(medida);
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
