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
import model.Objetivodequestao;
import model.Medida;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;
import model.Procedimentodeanalise;
import model.Registroindicador;
import model.Analise;
import model.Indicador;

/**
 *
 * @author Spider
 */
public class IndicadorJpaController implements Serializable {

    public IndicadorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Indicador indicador) {
        if (indicador.getMedidaList() == null) {
            indicador.setMedidaList(new ArrayList<Medida>());
        }
        if (indicador.getProcedimentodeanaliseList() == null) {
            indicador.setProcedimentodeanaliseList(new ArrayList<Procedimentodeanalise>());
        }
        if (indicador.getRegistroindicadorList() == null) {
            indicador.setRegistroindicadorList(new ArrayList<Registroindicador>());
        }
        if (indicador.getAnaliseList() == null) {
            indicador.setAnaliseList(new ArrayList<Analise>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Objetivodequestao objetivoDeQuestaoid = indicador.getObjetivoDeQuestaoid();
            if (objetivoDeQuestaoid != null) {
                objetivoDeQuestaoid = em.getReference(objetivoDeQuestaoid.getClass(), objetivoDeQuestaoid.getId());
                indicador.setObjetivoDeQuestaoid(objetivoDeQuestaoid);
            }
            List<Medida> attachedMedidaList = new ArrayList<Medida>();
            for (Medida medidaListMedidaToAttach : indicador.getMedidaList()) {
                medidaListMedidaToAttach = em.getReference(medidaListMedidaToAttach.getClass(), medidaListMedidaToAttach.getId());
                attachedMedidaList.add(medidaListMedidaToAttach);
            }
            indicador.setMedidaList(attachedMedidaList);
            List<Procedimentodeanalise> attachedProcedimentodeanaliseList = new ArrayList<Procedimentodeanalise>();
            for (Procedimentodeanalise procedimentodeanaliseListProcedimentodeanaliseToAttach : indicador.getProcedimentodeanaliseList()) {
                procedimentodeanaliseListProcedimentodeanaliseToAttach = em.getReference(procedimentodeanaliseListProcedimentodeanaliseToAttach.getClass(), procedimentodeanaliseListProcedimentodeanaliseToAttach.getId());
                attachedProcedimentodeanaliseList.add(procedimentodeanaliseListProcedimentodeanaliseToAttach);
            }
            indicador.setProcedimentodeanaliseList(attachedProcedimentodeanaliseList);
            List<Registroindicador> attachedRegistroindicadorList = new ArrayList<Registroindicador>();
            for (Registroindicador registroindicadorListRegistroindicadorToAttach : indicador.getRegistroindicadorList()) {
                registroindicadorListRegistroindicadorToAttach = em.getReference(registroindicadorListRegistroindicadorToAttach.getClass(), registroindicadorListRegistroindicadorToAttach.getId());
                attachedRegistroindicadorList.add(registroindicadorListRegistroindicadorToAttach);
            }
            indicador.setRegistroindicadorList(attachedRegistroindicadorList);
            List<Analise> attachedAnaliseList = new ArrayList<Analise>();
            for (Analise analiseListAnaliseToAttach : indicador.getAnaliseList()) {
                analiseListAnaliseToAttach = em.getReference(analiseListAnaliseToAttach.getClass(), analiseListAnaliseToAttach.getId());
                attachedAnaliseList.add(analiseListAnaliseToAttach);
            }
            indicador.setAnaliseList(attachedAnaliseList);
            em.persist(indicador);
            if (objetivoDeQuestaoid != null) {
                objetivoDeQuestaoid.getIndicadorList().add(indicador);
                objetivoDeQuestaoid = em.merge(objetivoDeQuestaoid);
            }
            for (Medida medidaListMedida : indicador.getMedidaList()) {
                medidaListMedida.getIndicadorList().add(indicador);
                medidaListMedida = em.merge(medidaListMedida);
            }
            for (Procedimentodeanalise procedimentodeanaliseListProcedimentodeanalise : indicador.getProcedimentodeanaliseList()) {
                Indicador oldIndicadoridOfProcedimentodeanaliseListProcedimentodeanalise = procedimentodeanaliseListProcedimentodeanalise.getIndicadorid();
                procedimentodeanaliseListProcedimentodeanalise.setIndicadorid(indicador);
                procedimentodeanaliseListProcedimentodeanalise = em.merge(procedimentodeanaliseListProcedimentodeanalise);
                if (oldIndicadoridOfProcedimentodeanaliseListProcedimentodeanalise != null) {
                    oldIndicadoridOfProcedimentodeanaliseListProcedimentodeanalise.getProcedimentodeanaliseList().remove(procedimentodeanaliseListProcedimentodeanalise);
                    oldIndicadoridOfProcedimentodeanaliseListProcedimentodeanalise = em.merge(oldIndicadoridOfProcedimentodeanaliseListProcedimentodeanalise);
                }
            }
            for (Registroindicador registroindicadorListRegistroindicador : indicador.getRegistroindicadorList()) {
                Indicador oldIndicadoridOfRegistroindicadorListRegistroindicador = registroindicadorListRegistroindicador.getIndicadorid();
                registroindicadorListRegistroindicador.setIndicadorid(indicador);
                registroindicadorListRegistroindicador = em.merge(registroindicadorListRegistroindicador);
                if (oldIndicadoridOfRegistroindicadorListRegistroindicador != null) {
                    oldIndicadoridOfRegistroindicadorListRegistroindicador.getRegistroindicadorList().remove(registroindicadorListRegistroindicador);
                    oldIndicadoridOfRegistroindicadorListRegistroindicador = em.merge(oldIndicadoridOfRegistroindicadorListRegistroindicador);
                }
            }
            for (Analise analiseListAnalise : indicador.getAnaliseList()) {
                Indicador oldIndicadoridOfAnaliseListAnalise = analiseListAnalise.getIndicadorid();
                analiseListAnalise.setIndicadorid(indicador);
                analiseListAnalise = em.merge(analiseListAnalise);
                if (oldIndicadoridOfAnaliseListAnalise != null) {
                    oldIndicadoridOfAnaliseListAnalise.getAnaliseList().remove(analiseListAnalise);
                    oldIndicadoridOfAnaliseListAnalise = em.merge(oldIndicadoridOfAnaliseListAnalise);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Indicador indicador) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Indicador persistentIndicador = em.find(Indicador.class, indicador.getId());
            Objetivodequestao objetivoDeQuestaoidOld = persistentIndicador.getObjetivoDeQuestaoid();
            Objetivodequestao objetivoDeQuestaoidNew = indicador.getObjetivoDeQuestaoid();
            List<Medida> medidaListOld = persistentIndicador.getMedidaList();
            List<Medida> medidaListNew = indicador.getMedidaList();
            List<Procedimentodeanalise> procedimentodeanaliseListOld = persistentIndicador.getProcedimentodeanaliseList();
            List<Procedimentodeanalise> procedimentodeanaliseListNew = indicador.getProcedimentodeanaliseList();
            List<Registroindicador> registroindicadorListOld = persistentIndicador.getRegistroindicadorList();
            List<Registroindicador> registroindicadorListNew = indicador.getRegistroindicadorList();
            List<Analise> analiseListOld = persistentIndicador.getAnaliseList();
            List<Analise> analiseListNew = indicador.getAnaliseList();
            List<String> illegalOrphanMessages = null;
            for (Procedimentodeanalise procedimentodeanaliseListOldProcedimentodeanalise : procedimentodeanaliseListOld) {
                if (!procedimentodeanaliseListNew.contains(procedimentodeanaliseListOldProcedimentodeanalise)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Procedimentodeanalise " + procedimentodeanaliseListOldProcedimentodeanalise + " since its indicadorid field is not nullable.");
                }
            }
            for (Registroindicador registroindicadorListOldRegistroindicador : registroindicadorListOld) {
                if (!registroindicadorListNew.contains(registroindicadorListOldRegistroindicador)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Registroindicador " + registroindicadorListOldRegistroindicador + " since its indicadorid field is not nullable.");
                }
            }
            for (Analise analiseListOldAnalise : analiseListOld) {
                if (!analiseListNew.contains(analiseListOldAnalise)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Analise " + analiseListOldAnalise + " since its indicadorid field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (objetivoDeQuestaoidNew != null) {
                objetivoDeQuestaoidNew = em.getReference(objetivoDeQuestaoidNew.getClass(), objetivoDeQuestaoidNew.getId());
                indicador.setObjetivoDeQuestaoid(objetivoDeQuestaoidNew);
            }
            List<Medida> attachedMedidaListNew = new ArrayList<Medida>();
            for (Medida medidaListNewMedidaToAttach : medidaListNew) {
                medidaListNewMedidaToAttach = em.getReference(medidaListNewMedidaToAttach.getClass(), medidaListNewMedidaToAttach.getId());
                attachedMedidaListNew.add(medidaListNewMedidaToAttach);
            }
            medidaListNew = attachedMedidaListNew;
            indicador.setMedidaList(medidaListNew);
            List<Procedimentodeanalise> attachedProcedimentodeanaliseListNew = new ArrayList<Procedimentodeanalise>();
            for (Procedimentodeanalise procedimentodeanaliseListNewProcedimentodeanaliseToAttach : procedimentodeanaliseListNew) {
                procedimentodeanaliseListNewProcedimentodeanaliseToAttach = em.getReference(procedimentodeanaliseListNewProcedimentodeanaliseToAttach.getClass(), procedimentodeanaliseListNewProcedimentodeanaliseToAttach.getId());
                attachedProcedimentodeanaliseListNew.add(procedimentodeanaliseListNewProcedimentodeanaliseToAttach);
            }
            procedimentodeanaliseListNew = attachedProcedimentodeanaliseListNew;
            indicador.setProcedimentodeanaliseList(procedimentodeanaliseListNew);
            List<Registroindicador> attachedRegistroindicadorListNew = new ArrayList<Registroindicador>();
            for (Registroindicador registroindicadorListNewRegistroindicadorToAttach : registroindicadorListNew) {
                registroindicadorListNewRegistroindicadorToAttach = em.getReference(registroindicadorListNewRegistroindicadorToAttach.getClass(), registroindicadorListNewRegistroindicadorToAttach.getId());
                attachedRegistroindicadorListNew.add(registroindicadorListNewRegistroindicadorToAttach);
            }
            registroindicadorListNew = attachedRegistroindicadorListNew;
            indicador.setRegistroindicadorList(registroindicadorListNew);
            List<Analise> attachedAnaliseListNew = new ArrayList<Analise>();
            for (Analise analiseListNewAnaliseToAttach : analiseListNew) {
                analiseListNewAnaliseToAttach = em.getReference(analiseListNewAnaliseToAttach.getClass(), analiseListNewAnaliseToAttach.getId());
                attachedAnaliseListNew.add(analiseListNewAnaliseToAttach);
            }
            analiseListNew = attachedAnaliseListNew;
            indicador.setAnaliseList(analiseListNew);
            indicador = em.merge(indicador);
            if (objetivoDeQuestaoidOld != null && !objetivoDeQuestaoidOld.equals(objetivoDeQuestaoidNew)) {
                objetivoDeQuestaoidOld.getIndicadorList().remove(indicador);
                objetivoDeQuestaoidOld = em.merge(objetivoDeQuestaoidOld);
            }
            if (objetivoDeQuestaoidNew != null && !objetivoDeQuestaoidNew.equals(objetivoDeQuestaoidOld)) {
                objetivoDeQuestaoidNew.getIndicadorList().add(indicador);
                objetivoDeQuestaoidNew = em.merge(objetivoDeQuestaoidNew);
            }
            for (Medida medidaListOldMedida : medidaListOld) {
                if (!medidaListNew.contains(medidaListOldMedida)) {
                    medidaListOldMedida.getIndicadorList().remove(indicador);
                    medidaListOldMedida = em.merge(medidaListOldMedida);
                }
            }
            for (Medida medidaListNewMedida : medidaListNew) {
                if (!medidaListOld.contains(medidaListNewMedida)) {
                    medidaListNewMedida.getIndicadorList().add(indicador);
                    medidaListNewMedida = em.merge(medidaListNewMedida);
                }
            }
            for (Procedimentodeanalise procedimentodeanaliseListNewProcedimentodeanalise : procedimentodeanaliseListNew) {
                if (!procedimentodeanaliseListOld.contains(procedimentodeanaliseListNewProcedimentodeanalise)) {
                    Indicador oldIndicadoridOfProcedimentodeanaliseListNewProcedimentodeanalise = procedimentodeanaliseListNewProcedimentodeanalise.getIndicadorid();
                    procedimentodeanaliseListNewProcedimentodeanalise.setIndicadorid(indicador);
                    procedimentodeanaliseListNewProcedimentodeanalise = em.merge(procedimentodeanaliseListNewProcedimentodeanalise);
                    if (oldIndicadoridOfProcedimentodeanaliseListNewProcedimentodeanalise != null && !oldIndicadoridOfProcedimentodeanaliseListNewProcedimentodeanalise.equals(indicador)) {
                        oldIndicadoridOfProcedimentodeanaliseListNewProcedimentodeanalise.getProcedimentodeanaliseList().remove(procedimentodeanaliseListNewProcedimentodeanalise);
                        oldIndicadoridOfProcedimentodeanaliseListNewProcedimentodeanalise = em.merge(oldIndicadoridOfProcedimentodeanaliseListNewProcedimentodeanalise);
                    }
                }
            }
            for (Registroindicador registroindicadorListNewRegistroindicador : registroindicadorListNew) {
                if (!registroindicadorListOld.contains(registroindicadorListNewRegistroindicador)) {
                    Indicador oldIndicadoridOfRegistroindicadorListNewRegistroindicador = registroindicadorListNewRegistroindicador.getIndicadorid();
                    registroindicadorListNewRegistroindicador.setIndicadorid(indicador);
                    registroindicadorListNewRegistroindicador = em.merge(registroindicadorListNewRegistroindicador);
                    if (oldIndicadoridOfRegistroindicadorListNewRegistroindicador != null && !oldIndicadoridOfRegistroindicadorListNewRegistroindicador.equals(indicador)) {
                        oldIndicadoridOfRegistroindicadorListNewRegistroindicador.getRegistroindicadorList().remove(registroindicadorListNewRegistroindicador);
                        oldIndicadoridOfRegistroindicadorListNewRegistroindicador = em.merge(oldIndicadoridOfRegistroindicadorListNewRegistroindicador);
                    }
                }
            }
            for (Analise analiseListNewAnalise : analiseListNew) {
                if (!analiseListOld.contains(analiseListNewAnalise)) {
                    Indicador oldIndicadoridOfAnaliseListNewAnalise = analiseListNewAnalise.getIndicadorid();
                    analiseListNewAnalise.setIndicadorid(indicador);
                    analiseListNewAnalise = em.merge(analiseListNewAnalise);
                    if (oldIndicadoridOfAnaliseListNewAnalise != null && !oldIndicadoridOfAnaliseListNewAnalise.equals(indicador)) {
                        oldIndicadoridOfAnaliseListNewAnalise.getAnaliseList().remove(analiseListNewAnalise);
                        oldIndicadoridOfAnaliseListNewAnalise = em.merge(oldIndicadoridOfAnaliseListNewAnalise);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = indicador.getId();
                if (findIndicador(id) == null) {
                    throw new NonexistentEntityException("The indicador with id " + id + " no longer exists.");
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
            Indicador indicador;
            try {
                indicador = em.getReference(Indicador.class, id);
                indicador.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The indicador with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Procedimentodeanalise> procedimentodeanaliseListOrphanCheck = indicador.getProcedimentodeanaliseList();
            for (Procedimentodeanalise procedimentodeanaliseListOrphanCheckProcedimentodeanalise : procedimentodeanaliseListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Indicador (" + indicador + ") cannot be destroyed since the Procedimentodeanalise " + procedimentodeanaliseListOrphanCheckProcedimentodeanalise + " in its procedimentodeanaliseList field has a non-nullable indicadorid field.");
            }
            List<Registroindicador> registroindicadorListOrphanCheck = indicador.getRegistroindicadorList();
            for (Registroindicador registroindicadorListOrphanCheckRegistroindicador : registroindicadorListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Indicador (" + indicador + ") cannot be destroyed since the Registroindicador " + registroindicadorListOrphanCheckRegistroindicador + " in its registroindicadorList field has a non-nullable indicadorid field.");
            }
            List<Analise> analiseListOrphanCheck = indicador.getAnaliseList();
            for (Analise analiseListOrphanCheckAnalise : analiseListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Indicador (" + indicador + ") cannot be destroyed since the Analise " + analiseListOrphanCheckAnalise + " in its analiseList field has a non-nullable indicadorid field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Objetivodequestao objetivoDeQuestaoid = indicador.getObjetivoDeQuestaoid();
            if (objetivoDeQuestaoid != null) {
                objetivoDeQuestaoid.getIndicadorList().remove(indicador);
                objetivoDeQuestaoid = em.merge(objetivoDeQuestaoid);
            }
            List<Medida> medidaList = indicador.getMedidaList();
            for (Medida medidaListMedida : medidaList) {
                medidaListMedida.getIndicadorList().remove(indicador);
                medidaListMedida = em.merge(medidaListMedida);
            }
            em.remove(indicador);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Indicador> findIndicadorEntities() {
        return findIndicadorEntities(true, -1, -1);
    }

    public List<Indicador> findIndicadorEntities(int maxResults, int firstResult) {
        return findIndicadorEntities(false, maxResults, firstResult);
    }

    private List<Indicador> findIndicadorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Indicador.class));
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

    public Indicador findIndicador(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Indicador.class, id);
        } finally {
            em.close();
        }
    }

    public int getIndicadorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Indicador> rt = cq.from(Indicador.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
