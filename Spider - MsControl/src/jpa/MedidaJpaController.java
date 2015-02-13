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
import model.Indicador;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;
import model.Procedimentodecoleta;
import model.Coleta;
import model.Medida;
import model.Registromedida;

/**
 *
 * @author Spider
 */
public class MedidaJpaController implements Serializable {

    public MedidaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Medida medida) {
        if (medida.getIndicadorList() == null) {
            medida.setIndicadorList(new ArrayList<Indicador>());
        }
        if (medida.getProcedimentodecoletaList() == null) {
            medida.setProcedimentodecoletaList(new ArrayList<Procedimentodecoleta>());
        }
        if (medida.getColetaList() == null) {
            medida.setColetaList(new ArrayList<Coleta>());
        }
        if (medida.getRegistromedidaList() == null) {
            medida.setRegistromedidaList(new ArrayList<Registromedida>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Indicador> attachedIndicadorList = new ArrayList<Indicador>();
            for (Indicador indicadorListIndicadorToAttach : medida.getIndicadorList()) {
                indicadorListIndicadorToAttach = em.getReference(indicadorListIndicadorToAttach.getClass(), indicadorListIndicadorToAttach.getId());
                attachedIndicadorList.add(indicadorListIndicadorToAttach);
            }
            medida.setIndicadorList(attachedIndicadorList);
            List<Procedimentodecoleta> attachedProcedimentodecoletaList = new ArrayList<Procedimentodecoleta>();
            for (Procedimentodecoleta procedimentodecoletaListProcedimentodecoletaToAttach : medida.getProcedimentodecoletaList()) {
                procedimentodecoletaListProcedimentodecoletaToAttach = em.getReference(procedimentodecoletaListProcedimentodecoletaToAttach.getClass(), procedimentodecoletaListProcedimentodecoletaToAttach.getId());
                attachedProcedimentodecoletaList.add(procedimentodecoletaListProcedimentodecoletaToAttach);
            }
            medida.setProcedimentodecoletaList(attachedProcedimentodecoletaList);
            List<Coleta> attachedColetaList = new ArrayList<Coleta>();
            for (Coleta coletaListColetaToAttach : medida.getColetaList()) {
                coletaListColetaToAttach = em.getReference(coletaListColetaToAttach.getClass(), coletaListColetaToAttach.getId());
                attachedColetaList.add(coletaListColetaToAttach);
            }
            medida.setColetaList(attachedColetaList);
            List<Registromedida> attachedRegistromedidaList = new ArrayList<Registromedida>();
            for (Registromedida registromedidaListRegistromedidaToAttach : medida.getRegistromedidaList()) {
                registromedidaListRegistromedidaToAttach = em.getReference(registromedidaListRegistromedidaToAttach.getClass(), registromedidaListRegistromedidaToAttach.getId());
                attachedRegistromedidaList.add(registromedidaListRegistromedidaToAttach);
            }
            medida.setRegistromedidaList(attachedRegistromedidaList);
            em.persist(medida);
            for (Indicador indicadorListIndicador : medida.getIndicadorList()) {
                indicadorListIndicador.getMedidaList().add(medida);
                indicadorListIndicador = em.merge(indicadorListIndicador);
            }
            for (Procedimentodecoleta procedimentodecoletaListProcedimentodecoleta : medida.getProcedimentodecoletaList()) {
                Medida oldMedidaidOfProcedimentodecoletaListProcedimentodecoleta = procedimentodecoletaListProcedimentodecoleta.getMedidaid();
                procedimentodecoletaListProcedimentodecoleta.setMedidaid(medida);
                procedimentodecoletaListProcedimentodecoleta = em.merge(procedimentodecoletaListProcedimentodecoleta);
                if (oldMedidaidOfProcedimentodecoletaListProcedimentodecoleta != null) {
                    oldMedidaidOfProcedimentodecoletaListProcedimentodecoleta.getProcedimentodecoletaList().remove(procedimentodecoletaListProcedimentodecoleta);
                    oldMedidaidOfProcedimentodecoletaListProcedimentodecoleta = em.merge(oldMedidaidOfProcedimentodecoletaListProcedimentodecoleta);
                }
            }
            for (Coleta coletaListColeta : medida.getColetaList()) {
                Medida oldMedidaidOfColetaListColeta = coletaListColeta.getMedidaid();
                coletaListColeta.setMedidaid(medida);
                coletaListColeta = em.merge(coletaListColeta);
                if (oldMedidaidOfColetaListColeta != null) {
                    oldMedidaidOfColetaListColeta.getColetaList().remove(coletaListColeta);
                    oldMedidaidOfColetaListColeta = em.merge(oldMedidaidOfColetaListColeta);
                }
            }
            for (Registromedida registromedidaListRegistromedida : medida.getRegistromedidaList()) {
                Medida oldMedidaidOfRegistromedidaListRegistromedida = registromedidaListRegistromedida.getMedidaid();
                registromedidaListRegistromedida.setMedidaid(medida);
                registromedidaListRegistromedida = em.merge(registromedidaListRegistromedida);
                if (oldMedidaidOfRegistromedidaListRegistromedida != null) {
                    oldMedidaidOfRegistromedidaListRegistromedida.getRegistromedidaList().remove(registromedidaListRegistromedida);
                    oldMedidaidOfRegistromedidaListRegistromedida = em.merge(oldMedidaidOfRegistromedidaListRegistromedida);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Medida medida) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Medida persistentMedida = em.find(Medida.class, medida.getId());
            List<Indicador> indicadorListOld = persistentMedida.getIndicadorList();
            List<Indicador> indicadorListNew = medida.getIndicadorList();
            List<Procedimentodecoleta> procedimentodecoletaListOld = persistentMedida.getProcedimentodecoletaList();
            List<Procedimentodecoleta> procedimentodecoletaListNew = medida.getProcedimentodecoletaList();
            List<Coleta> coletaListOld = persistentMedida.getColetaList();
            List<Coleta> coletaListNew = medida.getColetaList();
            List<Registromedida> registromedidaListOld = persistentMedida.getRegistromedidaList();
            List<Registromedida> registromedidaListNew = medida.getRegistromedidaList();
            List<String> illegalOrphanMessages = null;
            for (Procedimentodecoleta procedimentodecoletaListOldProcedimentodecoleta : procedimentodecoletaListOld) {
                if (!procedimentodecoletaListNew.contains(procedimentodecoletaListOldProcedimentodecoleta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Procedimentodecoleta " + procedimentodecoletaListOldProcedimentodecoleta + " since its medidaid field is not nullable.");
                }
            }
            for (Coleta coletaListOldColeta : coletaListOld) {
                if (!coletaListNew.contains(coletaListOldColeta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Coleta " + coletaListOldColeta + " since its medidaid field is not nullable.");
                }
            }
            for (Registromedida registromedidaListOldRegistromedida : registromedidaListOld) {
                if (!registromedidaListNew.contains(registromedidaListOldRegistromedida)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Registromedida " + registromedidaListOldRegistromedida + " since its medidaid field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Indicador> attachedIndicadorListNew = new ArrayList<Indicador>();
            for (Indicador indicadorListNewIndicadorToAttach : indicadorListNew) {
                indicadorListNewIndicadorToAttach = em.getReference(indicadorListNewIndicadorToAttach.getClass(), indicadorListNewIndicadorToAttach.getId());
                attachedIndicadorListNew.add(indicadorListNewIndicadorToAttach);
            }
            indicadorListNew = attachedIndicadorListNew;
            medida.setIndicadorList(indicadorListNew);
            List<Procedimentodecoleta> attachedProcedimentodecoletaListNew = new ArrayList<Procedimentodecoleta>();
            for (Procedimentodecoleta procedimentodecoletaListNewProcedimentodecoletaToAttach : procedimentodecoletaListNew) {
                procedimentodecoletaListNewProcedimentodecoletaToAttach = em.getReference(procedimentodecoletaListNewProcedimentodecoletaToAttach.getClass(), procedimentodecoletaListNewProcedimentodecoletaToAttach.getId());
                attachedProcedimentodecoletaListNew.add(procedimentodecoletaListNewProcedimentodecoletaToAttach);
            }
            procedimentodecoletaListNew = attachedProcedimentodecoletaListNew;
            medida.setProcedimentodecoletaList(procedimentodecoletaListNew);
            List<Coleta> attachedColetaListNew = new ArrayList<Coleta>();
            for (Coleta coletaListNewColetaToAttach : coletaListNew) {
                coletaListNewColetaToAttach = em.getReference(coletaListNewColetaToAttach.getClass(), coletaListNewColetaToAttach.getId());
                attachedColetaListNew.add(coletaListNewColetaToAttach);
            }
            coletaListNew = attachedColetaListNew;
            medida.setColetaList(coletaListNew);
            List<Registromedida> attachedRegistromedidaListNew = new ArrayList<Registromedida>();
            for (Registromedida registromedidaListNewRegistromedidaToAttach : registromedidaListNew) {
                registromedidaListNewRegistromedidaToAttach = em.getReference(registromedidaListNewRegistromedidaToAttach.getClass(), registromedidaListNewRegistromedidaToAttach.getId());
                attachedRegistromedidaListNew.add(registromedidaListNewRegistromedidaToAttach);
            }
            registromedidaListNew = attachedRegistromedidaListNew;
            medida.setRegistromedidaList(registromedidaListNew);
            medida = em.merge(medida);
            for (Indicador indicadorListOldIndicador : indicadorListOld) {
                if (!indicadorListNew.contains(indicadorListOldIndicador)) {
                    indicadorListOldIndicador.getMedidaList().remove(medida);
                    indicadorListOldIndicador = em.merge(indicadorListOldIndicador);
                }
            }
            for (Indicador indicadorListNewIndicador : indicadorListNew) {
                if (!indicadorListOld.contains(indicadorListNewIndicador)) {
                    indicadorListNewIndicador.getMedidaList().add(medida);
                    indicadorListNewIndicador = em.merge(indicadorListNewIndicador);
                }
            }
            for (Procedimentodecoleta procedimentodecoletaListNewProcedimentodecoleta : procedimentodecoletaListNew) {
                if (!procedimentodecoletaListOld.contains(procedimentodecoletaListNewProcedimentodecoleta)) {
                    Medida oldMedidaidOfProcedimentodecoletaListNewProcedimentodecoleta = procedimentodecoletaListNewProcedimentodecoleta.getMedidaid();
                    procedimentodecoletaListNewProcedimentodecoleta.setMedidaid(medida);
                    procedimentodecoletaListNewProcedimentodecoleta = em.merge(procedimentodecoletaListNewProcedimentodecoleta);
                    if (oldMedidaidOfProcedimentodecoletaListNewProcedimentodecoleta != null && !oldMedidaidOfProcedimentodecoletaListNewProcedimentodecoleta.equals(medida)) {
                        oldMedidaidOfProcedimentodecoletaListNewProcedimentodecoleta.getProcedimentodecoletaList().remove(procedimentodecoletaListNewProcedimentodecoleta);
                        oldMedidaidOfProcedimentodecoletaListNewProcedimentodecoleta = em.merge(oldMedidaidOfProcedimentodecoletaListNewProcedimentodecoleta);
                    }
                }
            }
            for (Coleta coletaListNewColeta : coletaListNew) {
                if (!coletaListOld.contains(coletaListNewColeta)) {
                    Medida oldMedidaidOfColetaListNewColeta = coletaListNewColeta.getMedidaid();
                    coletaListNewColeta.setMedidaid(medida);
                    coletaListNewColeta = em.merge(coletaListNewColeta);
                    if (oldMedidaidOfColetaListNewColeta != null && !oldMedidaidOfColetaListNewColeta.equals(medida)) {
                        oldMedidaidOfColetaListNewColeta.getColetaList().remove(coletaListNewColeta);
                        oldMedidaidOfColetaListNewColeta = em.merge(oldMedidaidOfColetaListNewColeta);
                    }
                }
            }
            for (Registromedida registromedidaListNewRegistromedida : registromedidaListNew) {
                if (!registromedidaListOld.contains(registromedidaListNewRegistromedida)) {
                    Medida oldMedidaidOfRegistromedidaListNewRegistromedida = registromedidaListNewRegistromedida.getMedidaid();
                    registromedidaListNewRegistromedida.setMedidaid(medida);
                    registromedidaListNewRegistromedida = em.merge(registromedidaListNewRegistromedida);
                    if (oldMedidaidOfRegistromedidaListNewRegistromedida != null && !oldMedidaidOfRegistromedidaListNewRegistromedida.equals(medida)) {
                        oldMedidaidOfRegistromedidaListNewRegistromedida.getRegistromedidaList().remove(registromedidaListNewRegistromedida);
                        oldMedidaidOfRegistromedidaListNewRegistromedida = em.merge(oldMedidaidOfRegistromedidaListNewRegistromedida);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = medida.getId();
                if (findMedida(id) == null) {
                    throw new NonexistentEntityException("The medida with id " + id + " no longer exists.");
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
            Medida medida;
            try {
                medida = em.getReference(Medida.class, id);
                medida.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The medida with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Procedimentodecoleta> procedimentodecoletaListOrphanCheck = medida.getProcedimentodecoletaList();
            for (Procedimentodecoleta procedimentodecoletaListOrphanCheckProcedimentodecoleta : procedimentodecoletaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Medida (" + medida + ") cannot be destroyed since the Procedimentodecoleta " + procedimentodecoletaListOrphanCheckProcedimentodecoleta + " in its procedimentodecoletaList field has a non-nullable medidaid field.");
            }
            List<Coleta> coletaListOrphanCheck = medida.getColetaList();
            for (Coleta coletaListOrphanCheckColeta : coletaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Medida (" + medida + ") cannot be destroyed since the Coleta " + coletaListOrphanCheckColeta + " in its coletaList field has a non-nullable medidaid field.");
            }
            List<Registromedida> registromedidaListOrphanCheck = medida.getRegistromedidaList();
            for (Registromedida registromedidaListOrphanCheckRegistromedida : registromedidaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Medida (" + medida + ") cannot be destroyed since the Registromedida " + registromedidaListOrphanCheckRegistromedida + " in its registromedidaList field has a non-nullable medidaid field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Indicador> indicadorList = medida.getIndicadorList();
            for (Indicador indicadorListIndicador : indicadorList) {
                indicadorListIndicador.getMedidaList().remove(medida);
                indicadorListIndicador = em.merge(indicadorListIndicador);
            }
            em.remove(medida);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Medida> findMedidaEntities() {
        return findMedidaEntities(true, -1, -1);
    }

    public List<Medida> findMedidaEntities(int maxResults, int firstResult) {
        return findMedidaEntities(false, maxResults, firstResult);
    }

    private List<Medida> findMedidaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Medida.class));
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

    public Medida findMedida(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Medida.class, id);
        } finally {
            em.close();
        }
    }

    public int getMedidaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Medida> rt = cq.from(Medida.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
