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
import model.Indicador;
import model.Objetivodequestao;

/**
 *
 * @author Bleno Vale
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
        if (objetivodequestao.getRegistroobjetivoquestaoList() == null) {
            objetivodequestao.setRegistroobjetivoquestaoList(new ArrayList<Registroobjetivoquestao>());
        }
        if (objetivodequestao.getIndicadorList() == null) {
            objetivodequestao.setIndicadorList(new ArrayList<Indicador>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Objetivodemedicao objetivoDeMedicaoid = objetivodequestao.getObjetivoDeMedicaoid();
            if (objetivoDeMedicaoid != null) {
                objetivoDeMedicaoid = em.getReference(objetivoDeMedicaoid.getClass(), objetivoDeMedicaoid.getId());
                objetivodequestao.setObjetivoDeMedicaoid(objetivoDeMedicaoid);
            }
            List<Registroobjetivoquestao> attachedRegistroobjetivoquestaoList = new ArrayList<Registroobjetivoquestao>();
            for (Registroobjetivoquestao registroobjetivoquestaoListRegistroobjetivoquestaoToAttach : objetivodequestao.getRegistroobjetivoquestaoList()) {
                registroobjetivoquestaoListRegistroobjetivoquestaoToAttach = em.getReference(registroobjetivoquestaoListRegistroobjetivoquestaoToAttach.getClass(), registroobjetivoquestaoListRegistroobjetivoquestaoToAttach.getId());
                attachedRegistroobjetivoquestaoList.add(registroobjetivoquestaoListRegistroobjetivoquestaoToAttach);
            }
            objetivodequestao.setRegistroobjetivoquestaoList(attachedRegistroobjetivoquestaoList);
            List<Indicador> attachedIndicadorList = new ArrayList<Indicador>();
            for (Indicador indicadorListIndicadorToAttach : objetivodequestao.getIndicadorList()) {
                indicadorListIndicadorToAttach = em.getReference(indicadorListIndicadorToAttach.getClass(), indicadorListIndicadorToAttach.getId());
                attachedIndicadorList.add(indicadorListIndicadorToAttach);
            }
            objetivodequestao.setIndicadorList(attachedIndicadorList);
            em.persist(objetivodequestao);
            if (objetivoDeMedicaoid != null) {
                objetivoDeMedicaoid.getObjetivodequestaoList().add(objetivodequestao);
                objetivoDeMedicaoid = em.merge(objetivoDeMedicaoid);
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
            for (Indicador indicadorListIndicador : objetivodequestao.getIndicadorList()) {
                Objetivodequestao oldObjetivoDeQuestaoidOfIndicadorListIndicador = indicadorListIndicador.getObjetivoDeQuestaoid();
                indicadorListIndicador.setObjetivoDeQuestaoid(objetivodequestao);
                indicadorListIndicador = em.merge(indicadorListIndicador);
                if (oldObjetivoDeQuestaoidOfIndicadorListIndicador != null) {
                    oldObjetivoDeQuestaoidOfIndicadorListIndicador.getIndicadorList().remove(indicadorListIndicador);
                    oldObjetivoDeQuestaoidOfIndicadorListIndicador = em.merge(oldObjetivoDeQuestaoidOfIndicadorListIndicador);
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
            Objetivodemedicao objetivoDeMedicaoidOld = persistentObjetivodequestao.getObjetivoDeMedicaoid();
            Objetivodemedicao objetivoDeMedicaoidNew = objetivodequestao.getObjetivoDeMedicaoid();
            List<Registroobjetivoquestao> registroobjetivoquestaoListOld = persistentObjetivodequestao.getRegistroobjetivoquestaoList();
            List<Registroobjetivoquestao> registroobjetivoquestaoListNew = objetivodequestao.getRegistroobjetivoquestaoList();
            List<Indicador> indicadorListOld = persistentObjetivodequestao.getIndicadorList();
            List<Indicador> indicadorListNew = objetivodequestao.getIndicadorList();
            List<String> illegalOrphanMessages = null;
            for (Registroobjetivoquestao registroobjetivoquestaoListOldRegistroobjetivoquestao : registroobjetivoquestaoListOld) {
                if (!registroobjetivoquestaoListNew.contains(registroobjetivoquestaoListOldRegistroobjetivoquestao)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Registroobjetivoquestao " + registroobjetivoquestaoListOldRegistroobjetivoquestao + " since its objetivoDeQuestaoid field is not nullable.");
                }
            }
            for (Indicador indicadorListOldIndicador : indicadorListOld) {
                if (!indicadorListNew.contains(indicadorListOldIndicador)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Indicador " + indicadorListOldIndicador + " since its objetivoDeQuestaoid field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (objetivoDeMedicaoidNew != null) {
                objetivoDeMedicaoidNew = em.getReference(objetivoDeMedicaoidNew.getClass(), objetivoDeMedicaoidNew.getId());
                objetivodequestao.setObjetivoDeMedicaoid(objetivoDeMedicaoidNew);
            }
            List<Registroobjetivoquestao> attachedRegistroobjetivoquestaoListNew = new ArrayList<Registroobjetivoquestao>();
            for (Registroobjetivoquestao registroobjetivoquestaoListNewRegistroobjetivoquestaoToAttach : registroobjetivoquestaoListNew) {
                registroobjetivoquestaoListNewRegistroobjetivoquestaoToAttach = em.getReference(registroobjetivoquestaoListNewRegistroobjetivoquestaoToAttach.getClass(), registroobjetivoquestaoListNewRegistroobjetivoquestaoToAttach.getId());
                attachedRegistroobjetivoquestaoListNew.add(registroobjetivoquestaoListNewRegistroobjetivoquestaoToAttach);
            }
            registroobjetivoquestaoListNew = attachedRegistroobjetivoquestaoListNew;
            objetivodequestao.setRegistroobjetivoquestaoList(registroobjetivoquestaoListNew);
            List<Indicador> attachedIndicadorListNew = new ArrayList<Indicador>();
            for (Indicador indicadorListNewIndicadorToAttach : indicadorListNew) {
                indicadorListNewIndicadorToAttach = em.getReference(indicadorListNewIndicadorToAttach.getClass(), indicadorListNewIndicadorToAttach.getId());
                attachedIndicadorListNew.add(indicadorListNewIndicadorToAttach);
            }
            indicadorListNew = attachedIndicadorListNew;
            objetivodequestao.setIndicadorList(indicadorListNew);
            objetivodequestao = em.merge(objetivodequestao);
            if (objetivoDeMedicaoidOld != null && !objetivoDeMedicaoidOld.equals(objetivoDeMedicaoidNew)) {
                objetivoDeMedicaoidOld.getObjetivodequestaoList().remove(objetivodequestao);
                objetivoDeMedicaoidOld = em.merge(objetivoDeMedicaoidOld);
            }
            if (objetivoDeMedicaoidNew != null && !objetivoDeMedicaoidNew.equals(objetivoDeMedicaoidOld)) {
                objetivoDeMedicaoidNew.getObjetivodequestaoList().add(objetivodequestao);
                objetivoDeMedicaoidNew = em.merge(objetivoDeMedicaoidNew);
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
            for (Indicador indicadorListNewIndicador : indicadorListNew) {
                if (!indicadorListOld.contains(indicadorListNewIndicador)) {
                    Objetivodequestao oldObjetivoDeQuestaoidOfIndicadorListNewIndicador = indicadorListNewIndicador.getObjetivoDeQuestaoid();
                    indicadorListNewIndicador.setObjetivoDeQuestaoid(objetivodequestao);
                    indicadorListNewIndicador = em.merge(indicadorListNewIndicador);
                    if (oldObjetivoDeQuestaoidOfIndicadorListNewIndicador != null && !oldObjetivoDeQuestaoidOfIndicadorListNewIndicador.equals(objetivodequestao)) {
                        oldObjetivoDeQuestaoidOfIndicadorListNewIndicador.getIndicadorList().remove(indicadorListNewIndicador);
                        oldObjetivoDeQuestaoidOfIndicadorListNewIndicador = em.merge(oldObjetivoDeQuestaoidOfIndicadorListNewIndicador);
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
            List<Indicador> indicadorListOrphanCheck = objetivodequestao.getIndicadorList();
            for (Indicador indicadorListOrphanCheckIndicador : indicadorListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Objetivodequestao (" + objetivodequestao + ") cannot be destroyed since the Indicador " + indicadorListOrphanCheckIndicador + " in its indicadorList field has a non-nullable objetivoDeQuestaoid field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Objetivodemedicao objetivoDeMedicaoid = objetivodequestao.getObjetivoDeMedicaoid();
            if (objetivoDeMedicaoid != null) {
                objetivoDeMedicaoid.getObjetivodequestaoList().remove(objetivodequestao);
                objetivoDeMedicaoid = em.merge(objetivoDeMedicaoid);
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
