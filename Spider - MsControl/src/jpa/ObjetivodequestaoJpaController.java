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
import model.Medida;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;
import model.Objetivodequestao;
import model.Registroobjetivoquestao;

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

    public void create(Objetivodequestao objetivodequestao) {
        if (objetivodequestao.getMedidaList() == null) {
            objetivodequestao.setMedidaList(new ArrayList<Medida>());
        }
        if (objetivodequestao.getRegistroobjetivoquestaoList() == null) {
            objetivodequestao.setRegistroobjetivoquestaoList(new ArrayList<Registroobjetivoquestao>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Objetivodemedicao objetivodemedicao = objetivodequestao.getObjetivodemedicao();
            if (objetivodemedicao != null) {
                objetivodemedicao = em.getReference(objetivodemedicao.getClass(), objetivodemedicao.getObjetivodemedicaoPK());
                objetivodequestao.setObjetivodemedicao(objetivodemedicao);
            }
            List<Medida> attachedMedidaList = new ArrayList<Medida>();
            for (Medida medidaListMedidaToAttach : objetivodequestao.getMedidaList()) {
                medidaListMedidaToAttach = em.getReference(medidaListMedidaToAttach.getClass(), medidaListMedidaToAttach.getMedidaPK());
                attachedMedidaList.add(medidaListMedidaToAttach);
            }
            objetivodequestao.setMedidaList(attachedMedidaList);
            List<Registroobjetivoquestao> attachedRegistroobjetivoquestaoList = new ArrayList<Registroobjetivoquestao>();
            for (Registroobjetivoquestao registroobjetivoquestaoListRegistroobjetivoquestaoToAttach : objetivodequestao.getRegistroobjetivoquestaoList()) {
                registroobjetivoquestaoListRegistroobjetivoquestaoToAttach = em.getReference(registroobjetivoquestaoListRegistroobjetivoquestaoToAttach.getClass(), registroobjetivoquestaoListRegistroobjetivoquestaoToAttach.getId());
                attachedRegistroobjetivoquestaoList.add(registroobjetivoquestaoListRegistroobjetivoquestaoToAttach);
            }
            objetivodequestao.setRegistroobjetivoquestaoList(attachedRegistroobjetivoquestaoList);
            em.persist(objetivodequestao);
            if (objetivodemedicao != null) {
                objetivodemedicao.getObjetivodequestaoList().add(objetivodequestao);
                objetivodemedicao = em.merge(objetivodemedicao);
            }
            for (Medida medidaListMedida : objetivodequestao.getMedidaList()) {
                medidaListMedida.getObjetivodequestaoList().add(objetivodequestao);
                medidaListMedida = em.merge(medidaListMedida);
            }
            for (Registroobjetivoquestao registroobjetivoquestaoListRegistroobjetivoquestao : objetivodequestao.getRegistroobjetivoquestaoList()) {
                Objetivodequestao oldObjetivoDeQuestaoidOfRegistroobjetivoquestaoListRegistroobjetivoquestao = registroobjetivoquestaoListRegistroobjetivoquestao.getObjetivoDeQuestaoid();
                registroobjetivoquestaoListRegistroobjetivoquestao.setObjetivoDeQuestaoid(objetivodequestao);
                registroobjetivoquestaoListRegistroobjetivoquestao = em.merge(registroobjetivoquestaoListRegistroobjetivoquestao);
                if (oldObjetivoDeQuestaoidOfRegistroobjetivoquestaoListRegistroobjetivoquestao != null) {
                    oldObjetivoDeQuestaoidOfRegistroobjetivoquestaoListRegistroobjetivoquestao.getRegistroobjetivoquestaoList().remove(registroobjetivoquestaoListRegistroobjetivoquestao);
                    oldObjetivoDeQuestaoidOfRegistroobjetivoquestaoListRegistroobjetivoquestao = em.merge(oldObjetivoDeQuestaoidOfRegistroobjetivoquestaoListRegistroobjetivoquestao);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Objetivodequestao objetivodequestao) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Objetivodequestao persistentObjetivodequestao = em.find(Objetivodequestao.class, objetivodequestao.getId());
            Objetivodemedicao objetivodemedicaoOld = persistentObjetivodequestao.getObjetivodemedicao();
            Objetivodemedicao objetivodemedicaoNew = objetivodequestao.getObjetivodemedicao();
            List<Medida> medidaListOld = persistentObjetivodequestao.getMedidaList();
            List<Medida> medidaListNew = objetivodequestao.getMedidaList();
            List<Registroobjetivoquestao> registroobjetivoquestaoListOld = persistentObjetivodequestao.getRegistroobjetivoquestaoList();
            List<Registroobjetivoquestao> registroobjetivoquestaoListNew = objetivodequestao.getRegistroobjetivoquestaoList();
            List<String> illegalOrphanMessages = null;
            for (Registroobjetivoquestao registroobjetivoquestaoListOldRegistroobjetivoquestao : registroobjetivoquestaoListOld) {
                if (!registroobjetivoquestaoListNew.contains(registroobjetivoquestaoListOldRegistroobjetivoquestao)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Registroobjetivoquestao " + registroobjetivoquestaoListOldRegistroobjetivoquestao + " since its objetivoDeQuestaoid field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (objetivodemedicaoNew != null) {
                objetivodemedicaoNew = em.getReference(objetivodemedicaoNew.getClass(), objetivodemedicaoNew.getObjetivodemedicaoPK());
                objetivodequestao.setObjetivodemedicao(objetivodemedicaoNew);
            }
            List<Medida> attachedMedidaListNew = new ArrayList<Medida>();
            for (Medida medidaListNewMedidaToAttach : medidaListNew) {
                medidaListNewMedidaToAttach = em.getReference(medidaListNewMedidaToAttach.getClass(), medidaListNewMedidaToAttach.getMedidaPK());
                attachedMedidaListNew.add(medidaListNewMedidaToAttach);
            }
            medidaListNew = attachedMedidaListNew;
            objetivodequestao.setMedidaList(medidaListNew);
            List<Registroobjetivoquestao> attachedRegistroobjetivoquestaoListNew = new ArrayList<Registroobjetivoquestao>();
            for (Registroobjetivoquestao registroobjetivoquestaoListNewRegistroobjetivoquestaoToAttach : registroobjetivoquestaoListNew) {
                registroobjetivoquestaoListNewRegistroobjetivoquestaoToAttach = em.getReference(registroobjetivoquestaoListNewRegistroobjetivoquestaoToAttach.getClass(), registroobjetivoquestaoListNewRegistroobjetivoquestaoToAttach.getId());
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
            for (Medida medidaListOldMedida : medidaListOld) {
                if (!medidaListNew.contains(medidaListOldMedida)) {
                    medidaListOldMedida.getObjetivodequestaoList().remove(objetivodequestao);
                    medidaListOldMedida = em.merge(medidaListOldMedida);
                }
            }
            for (Medida medidaListNewMedida : medidaListNew) {
                if (!medidaListOld.contains(medidaListNewMedida)) {
                    medidaListNewMedida.getObjetivodequestaoList().add(objetivodequestao);
                    medidaListNewMedida = em.merge(medidaListNewMedida);
                }
            }
            for (Registroobjetivoquestao registroobjetivoquestaoListNewRegistroobjetivoquestao : registroobjetivoquestaoListNew) {
                if (!registroobjetivoquestaoListOld.contains(registroobjetivoquestaoListNewRegistroobjetivoquestao)) {
                    Objetivodequestao oldObjetivoDeQuestaoidOfRegistroobjetivoquestaoListNewRegistroobjetivoquestao = registroobjetivoquestaoListNewRegistroobjetivoquestao.getObjetivoDeQuestaoid();
                    registroobjetivoquestaoListNewRegistroobjetivoquestao.setObjetivoDeQuestaoid(objetivodequestao);
                    registroobjetivoquestaoListNewRegistroobjetivoquestao = em.merge(registroobjetivoquestaoListNewRegistroobjetivoquestao);
                    if (oldObjetivoDeQuestaoidOfRegistroobjetivoquestaoListNewRegistroobjetivoquestao != null && !oldObjetivoDeQuestaoidOfRegistroobjetivoquestaoListNewRegistroobjetivoquestao.equals(objetivodequestao)) {
                        oldObjetivoDeQuestaoidOfRegistroobjetivoquestaoListNewRegistroobjetivoquestao.getRegistroobjetivoquestaoList().remove(registroobjetivoquestaoListNewRegistroobjetivoquestao);
                        oldObjetivoDeQuestaoidOfRegistroobjetivoquestaoListNewRegistroobjetivoquestao = em.merge(oldObjetivoDeQuestaoidOfRegistroobjetivoquestaoListNewRegistroobjetivoquestao);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = objetivodequestao.getId();
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

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Objetivodequestao objetivodequestao;
            try {
                objetivodequestao = em.getReference(Objetivodequestao.class, id);
                objetivodequestao.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The objetivodequestao with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Registroobjetivoquestao> registroobjetivoquestaoListOrphanCheck = objetivodequestao.getRegistroobjetivoquestaoList();
            for (Registroobjetivoquestao registroobjetivoquestaoListOrphanCheckRegistroobjetivoquestao : registroobjetivoquestaoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Objetivodequestao (" + objetivodequestao + ") cannot be destroyed since the Registroobjetivoquestao " + registroobjetivoquestaoListOrphanCheckRegistroobjetivoquestao + " in its registroobjetivoquestaoList field has a non-nullable objetivoDeQuestaoid field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Objetivodemedicao objetivodemedicao = objetivodequestao.getObjetivodemedicao();
            if (objetivodemedicao != null) {
                objetivodemedicao.getObjetivodequestaoList().remove(objetivodequestao);
                objetivodemedicao = em.merge(objetivodemedicao);
            }
            List<Medida> medidaList = objetivodequestao.getMedidaList();
            for (Medida medidaListMedida : medidaList) {
                medidaListMedida.getObjetivodequestaoList().remove(objetivodequestao);
                medidaListMedida = em.merge(medidaListMedida);
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

    public Objetivodequestao findObjetivodequestao(Integer id) {
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
