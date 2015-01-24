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
import model.Procedimentodeanalise;
import model.Procedimentodecoleta;
import model.Coleta;
import model.Aprovacao;
import model.Analise;
import model.Medida;
import model.MedidaPK;
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

    public void create(Medida medida) throws PreexistingEntityException, Exception {
        if (medida.getMedidaPK() == null) {
            medida.setMedidaPK(new MedidaPK());
        }
        if (medida.getObjetivodequestaoList() == null) {
            medida.setObjetivodequestaoList(new ArrayList<Objetivodequestao>());
        }
        if (medida.getProcedimentodeanaliseList() == null) {
            medida.setProcedimentodeanaliseList(new ArrayList<Procedimentodeanalise>());
        }
        if (medida.getProcedimentodecoletaList() == null) {
            medida.setProcedimentodecoletaList(new ArrayList<Procedimentodecoleta>());
        }
        if (medida.getColetaList() == null) {
            medida.setColetaList(new ArrayList<Coleta>());
        }
        if (medida.getAprovacaoList() == null) {
            medida.setAprovacaoList(new ArrayList<Aprovacao>());
        }
        if (medida.getAnaliseList() == null) {
            medida.setAnaliseList(new ArrayList<Analise>());
        }
        if (medida.getRegistromedidaList() == null) {
            medida.setRegistromedidaList(new ArrayList<Registromedida>());
        }
        medida.getMedidaPK().setProjetoid(medida.getProjeto().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Projeto projeto = medida.getProjeto();
            if (projeto != null) {
                projeto = em.getReference(projeto.getClass(), projeto.getId());
                medida.setProjeto(projeto);
            }
            List<Objetivodequestao> attachedObjetivodequestaoList = new ArrayList<Objetivodequestao>();
            for (Objetivodequestao objetivodequestaoListObjetivodequestaoToAttach : medida.getObjetivodequestaoList()) {
                objetivodequestaoListObjetivodequestaoToAttach = em.getReference(objetivodequestaoListObjetivodequestaoToAttach.getClass(), objetivodequestaoListObjetivodequestaoToAttach.getId());
                attachedObjetivodequestaoList.add(objetivodequestaoListObjetivodequestaoToAttach);
            }
            medida.setObjetivodequestaoList(attachedObjetivodequestaoList);
            List<Procedimentodeanalise> attachedProcedimentodeanaliseList = new ArrayList<Procedimentodeanalise>();
            for (Procedimentodeanalise procedimentodeanaliseListProcedimentodeanaliseToAttach : medida.getProcedimentodeanaliseList()) {
                procedimentodeanaliseListProcedimentodeanaliseToAttach = em.getReference(procedimentodeanaliseListProcedimentodeanaliseToAttach.getClass(), procedimentodeanaliseListProcedimentodeanaliseToAttach.getId());
                attachedProcedimentodeanaliseList.add(procedimentodeanaliseListProcedimentodeanaliseToAttach);
            }
            medida.setProcedimentodeanaliseList(attachedProcedimentodeanaliseList);
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
            List<Aprovacao> attachedAprovacaoList = new ArrayList<Aprovacao>();
            for (Aprovacao aprovacaoListAprovacaoToAttach : medida.getAprovacaoList()) {
                aprovacaoListAprovacaoToAttach = em.getReference(aprovacaoListAprovacaoToAttach.getClass(), aprovacaoListAprovacaoToAttach.getId());
                attachedAprovacaoList.add(aprovacaoListAprovacaoToAttach);
            }
            medida.setAprovacaoList(attachedAprovacaoList);
            List<Analise> attachedAnaliseList = new ArrayList<Analise>();
            for (Analise analiseListAnaliseToAttach : medida.getAnaliseList()) {
                analiseListAnaliseToAttach = em.getReference(analiseListAnaliseToAttach.getClass(), analiseListAnaliseToAttach.getId());
                attachedAnaliseList.add(analiseListAnaliseToAttach);
            }
            medida.setAnaliseList(attachedAnaliseList);
            List<Registromedida> attachedRegistromedidaList = new ArrayList<Registromedida>();
            for (Registromedida registromedidaListRegistromedidaToAttach : medida.getRegistromedidaList()) {
                registromedidaListRegistromedidaToAttach = em.getReference(registromedidaListRegistromedidaToAttach.getClass(), registromedidaListRegistromedidaToAttach.getId());
                attachedRegistromedidaList.add(registromedidaListRegistromedidaToAttach);
            }
            medida.setRegistromedidaList(attachedRegistromedidaList);
            em.persist(medida);
            if (projeto != null) {
                projeto.getMedidaList().add(medida);
                projeto = em.merge(projeto);
            }
            for (Objetivodequestao objetivodequestaoListObjetivodequestao : medida.getObjetivodequestaoList()) {
                objetivodequestaoListObjetivodequestao.getMedidaList().add(medida);
                objetivodequestaoListObjetivodequestao = em.merge(objetivodequestaoListObjetivodequestao);
            }
            for (Procedimentodeanalise procedimentodeanaliseListProcedimentodeanalise : medida.getProcedimentodeanaliseList()) {
                Medida oldMedidaOfProcedimentodeanaliseListProcedimentodeanalise = procedimentodeanaliseListProcedimentodeanalise.getMedida();
                procedimentodeanaliseListProcedimentodeanalise.setMedida(medida);
                procedimentodeanaliseListProcedimentodeanalise = em.merge(procedimentodeanaliseListProcedimentodeanalise);
                if (oldMedidaOfProcedimentodeanaliseListProcedimentodeanalise != null) {
                    oldMedidaOfProcedimentodeanaliseListProcedimentodeanalise.getProcedimentodeanaliseList().remove(procedimentodeanaliseListProcedimentodeanalise);
                    oldMedidaOfProcedimentodeanaliseListProcedimentodeanalise = em.merge(oldMedidaOfProcedimentodeanaliseListProcedimentodeanalise);
                }
            }
            for (Procedimentodecoleta procedimentodecoletaListProcedimentodecoleta : medida.getProcedimentodecoletaList()) {
                Medida oldMedidaOfProcedimentodecoletaListProcedimentodecoleta = procedimentodecoletaListProcedimentodecoleta.getMedida();
                procedimentodecoletaListProcedimentodecoleta.setMedida(medida);
                procedimentodecoletaListProcedimentodecoleta = em.merge(procedimentodecoletaListProcedimentodecoleta);
                if (oldMedidaOfProcedimentodecoletaListProcedimentodecoleta != null) {
                    oldMedidaOfProcedimentodecoletaListProcedimentodecoleta.getProcedimentodecoletaList().remove(procedimentodecoletaListProcedimentodecoleta);
                    oldMedidaOfProcedimentodecoletaListProcedimentodecoleta = em.merge(oldMedidaOfProcedimentodecoletaListProcedimentodecoleta);
                }
            }
            for (Coleta coletaListColeta : medida.getColetaList()) {
                Medida oldMedidaOfColetaListColeta = coletaListColeta.getMedida();
                coletaListColeta.setMedida(medida);
                coletaListColeta = em.merge(coletaListColeta);
                if (oldMedidaOfColetaListColeta != null) {
                    oldMedidaOfColetaListColeta.getColetaList().remove(coletaListColeta);
                    oldMedidaOfColetaListColeta = em.merge(oldMedidaOfColetaListColeta);
                }
            }
            for (Aprovacao aprovacaoListAprovacao : medida.getAprovacaoList()) {
                Medida oldMedidaOfAprovacaoListAprovacao = aprovacaoListAprovacao.getMedida();
                aprovacaoListAprovacao.setMedida(medida);
                aprovacaoListAprovacao = em.merge(aprovacaoListAprovacao);
                if (oldMedidaOfAprovacaoListAprovacao != null) {
                    oldMedidaOfAprovacaoListAprovacao.getAprovacaoList().remove(aprovacaoListAprovacao);
                    oldMedidaOfAprovacaoListAprovacao = em.merge(oldMedidaOfAprovacaoListAprovacao);
                }
            }
            for (Analise analiseListAnalise : medida.getAnaliseList()) {
                Medida oldMedidaOfAnaliseListAnalise = analiseListAnalise.getMedida();
                analiseListAnalise.setMedida(medida);
                analiseListAnalise = em.merge(analiseListAnalise);
                if (oldMedidaOfAnaliseListAnalise != null) {
                    oldMedidaOfAnaliseListAnalise.getAnaliseList().remove(analiseListAnalise);
                    oldMedidaOfAnaliseListAnalise = em.merge(oldMedidaOfAnaliseListAnalise);
                }
            }
            for (Registromedida registromedidaListRegistromedida : medida.getRegistromedidaList()) {
                Medida oldMedidaOfRegistromedidaListRegistromedida = registromedidaListRegistromedida.getMedida();
                registromedidaListRegistromedida.setMedida(medida);
                registromedidaListRegistromedida = em.merge(registromedidaListRegistromedida);
                if (oldMedidaOfRegistromedidaListRegistromedida != null) {
                    oldMedidaOfRegistromedidaListRegistromedida.getRegistromedidaList().remove(registromedidaListRegistromedida);
                    oldMedidaOfRegistromedidaListRegistromedida = em.merge(oldMedidaOfRegistromedidaListRegistromedida);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMedida(medida.getMedidaPK()) != null) {
                throw new PreexistingEntityException("Medida " + medida + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Medida medida) throws IllegalOrphanException, NonexistentEntityException, Exception {
        medida.getMedidaPK().setProjetoid(medida.getProjeto().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Medida persistentMedida = em.find(Medida.class, medida.getMedidaPK());
            Projeto projetoOld = persistentMedida.getProjeto();
            Projeto projetoNew = medida.getProjeto();
            List<Objetivodequestao> objetivodequestaoListOld = persistentMedida.getObjetivodequestaoList();
            List<Objetivodequestao> objetivodequestaoListNew = medida.getObjetivodequestaoList();
            List<Procedimentodeanalise> procedimentodeanaliseListOld = persistentMedida.getProcedimentodeanaliseList();
            List<Procedimentodeanalise> procedimentodeanaliseListNew = medida.getProcedimentodeanaliseList();
            List<Procedimentodecoleta> procedimentodecoletaListOld = persistentMedida.getProcedimentodecoletaList();
            List<Procedimentodecoleta> procedimentodecoletaListNew = medida.getProcedimentodecoletaList();
            List<Coleta> coletaListOld = persistentMedida.getColetaList();
            List<Coleta> coletaListNew = medida.getColetaList();
            List<Aprovacao> aprovacaoListOld = persistentMedida.getAprovacaoList();
            List<Aprovacao> aprovacaoListNew = medida.getAprovacaoList();
            List<Analise> analiseListOld = persistentMedida.getAnaliseList();
            List<Analise> analiseListNew = medida.getAnaliseList();
            List<Registromedida> registromedidaListOld = persistentMedida.getRegistromedidaList();
            List<Registromedida> registromedidaListNew = medida.getRegistromedidaList();
            List<String> illegalOrphanMessages = null;
            for (Procedimentodeanalise procedimentodeanaliseListOldProcedimentodeanalise : procedimentodeanaliseListOld) {
                if (!procedimentodeanaliseListNew.contains(procedimentodeanaliseListOldProcedimentodeanalise)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Procedimentodeanalise " + procedimentodeanaliseListOldProcedimentodeanalise + " since its medida field is not nullable.");
                }
            }
            for (Procedimentodecoleta procedimentodecoletaListOldProcedimentodecoleta : procedimentodecoletaListOld) {
                if (!procedimentodecoletaListNew.contains(procedimentodecoletaListOldProcedimentodecoleta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Procedimentodecoleta " + procedimentodecoletaListOldProcedimentodecoleta + " since its medida field is not nullable.");
                }
            }
            for (Coleta coletaListOldColeta : coletaListOld) {
                if (!coletaListNew.contains(coletaListOldColeta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Coleta " + coletaListOldColeta + " since its medida field is not nullable.");
                }
            }
            for (Aprovacao aprovacaoListOldAprovacao : aprovacaoListOld) {
                if (!aprovacaoListNew.contains(aprovacaoListOldAprovacao)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Aprovacao " + aprovacaoListOldAprovacao + " since its medida field is not nullable.");
                }
            }
            for (Analise analiseListOldAnalise : analiseListOld) {
                if (!analiseListNew.contains(analiseListOldAnalise)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Analise " + analiseListOldAnalise + " since its medida field is not nullable.");
                }
            }
            for (Registromedida registromedidaListOldRegistromedida : registromedidaListOld) {
                if (!registromedidaListNew.contains(registromedidaListOldRegistromedida)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Registromedida " + registromedidaListOldRegistromedida + " since its medida field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (projetoNew != null) {
                projetoNew = em.getReference(projetoNew.getClass(), projetoNew.getId());
                medida.setProjeto(projetoNew);
            }
            List<Objetivodequestao> attachedObjetivodequestaoListNew = new ArrayList<Objetivodequestao>();
            for (Objetivodequestao objetivodequestaoListNewObjetivodequestaoToAttach : objetivodequestaoListNew) {
                objetivodequestaoListNewObjetivodequestaoToAttach = em.getReference(objetivodequestaoListNewObjetivodequestaoToAttach.getClass(), objetivodequestaoListNewObjetivodequestaoToAttach.getId());
                attachedObjetivodequestaoListNew.add(objetivodequestaoListNewObjetivodequestaoToAttach);
            }
            objetivodequestaoListNew = attachedObjetivodequestaoListNew;
            medida.setObjetivodequestaoList(objetivodequestaoListNew);
            List<Procedimentodeanalise> attachedProcedimentodeanaliseListNew = new ArrayList<Procedimentodeanalise>();
            for (Procedimentodeanalise procedimentodeanaliseListNewProcedimentodeanaliseToAttach : procedimentodeanaliseListNew) {
                procedimentodeanaliseListNewProcedimentodeanaliseToAttach = em.getReference(procedimentodeanaliseListNewProcedimentodeanaliseToAttach.getClass(), procedimentodeanaliseListNewProcedimentodeanaliseToAttach.getId());
                attachedProcedimentodeanaliseListNew.add(procedimentodeanaliseListNewProcedimentodeanaliseToAttach);
            }
            procedimentodeanaliseListNew = attachedProcedimentodeanaliseListNew;
            medida.setProcedimentodeanaliseList(procedimentodeanaliseListNew);
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
            List<Aprovacao> attachedAprovacaoListNew = new ArrayList<Aprovacao>();
            for (Aprovacao aprovacaoListNewAprovacaoToAttach : aprovacaoListNew) {
                aprovacaoListNewAprovacaoToAttach = em.getReference(aprovacaoListNewAprovacaoToAttach.getClass(), aprovacaoListNewAprovacaoToAttach.getId());
                attachedAprovacaoListNew.add(aprovacaoListNewAprovacaoToAttach);
            }
            aprovacaoListNew = attachedAprovacaoListNew;
            medida.setAprovacaoList(aprovacaoListNew);
            List<Analise> attachedAnaliseListNew = new ArrayList<Analise>();
            for (Analise analiseListNewAnaliseToAttach : analiseListNew) {
                analiseListNewAnaliseToAttach = em.getReference(analiseListNewAnaliseToAttach.getClass(), analiseListNewAnaliseToAttach.getId());
                attachedAnaliseListNew.add(analiseListNewAnaliseToAttach);
            }
            analiseListNew = attachedAnaliseListNew;
            medida.setAnaliseList(analiseListNew);
            List<Registromedida> attachedRegistromedidaListNew = new ArrayList<Registromedida>();
            for (Registromedida registromedidaListNewRegistromedidaToAttach : registromedidaListNew) {
                registromedidaListNewRegistromedidaToAttach = em.getReference(registromedidaListNewRegistromedidaToAttach.getClass(), registromedidaListNewRegistromedidaToAttach.getId());
                attachedRegistromedidaListNew.add(registromedidaListNewRegistromedidaToAttach);
            }
            registromedidaListNew = attachedRegistromedidaListNew;
            medida.setRegistromedidaList(registromedidaListNew);
            medida = em.merge(medida);
            if (projetoOld != null && !projetoOld.equals(projetoNew)) {
                projetoOld.getMedidaList().remove(medida);
                projetoOld = em.merge(projetoOld);
            }
            if (projetoNew != null && !projetoNew.equals(projetoOld)) {
                projetoNew.getMedidaList().add(medida);
                projetoNew = em.merge(projetoNew);
            }
            for (Objetivodequestao objetivodequestaoListOldObjetivodequestao : objetivodequestaoListOld) {
                if (!objetivodequestaoListNew.contains(objetivodequestaoListOldObjetivodequestao)) {
                    objetivodequestaoListOldObjetivodequestao.getMedidaList().remove(medida);
                    objetivodequestaoListOldObjetivodequestao = em.merge(objetivodequestaoListOldObjetivodequestao);
                }
            }
            for (Objetivodequestao objetivodequestaoListNewObjetivodequestao : objetivodequestaoListNew) {
                if (!objetivodequestaoListOld.contains(objetivodequestaoListNewObjetivodequestao)) {
                    objetivodequestaoListNewObjetivodequestao.getMedidaList().add(medida);
                    objetivodequestaoListNewObjetivodequestao = em.merge(objetivodequestaoListNewObjetivodequestao);
                }
            }
            for (Procedimentodeanalise procedimentodeanaliseListNewProcedimentodeanalise : procedimentodeanaliseListNew) {
                if (!procedimentodeanaliseListOld.contains(procedimentodeanaliseListNewProcedimentodeanalise)) {
                    Medida oldMedidaOfProcedimentodeanaliseListNewProcedimentodeanalise = procedimentodeanaliseListNewProcedimentodeanalise.getMedida();
                    procedimentodeanaliseListNewProcedimentodeanalise.setMedida(medida);
                    procedimentodeanaliseListNewProcedimentodeanalise = em.merge(procedimentodeanaliseListNewProcedimentodeanalise);
                    if (oldMedidaOfProcedimentodeanaliseListNewProcedimentodeanalise != null && !oldMedidaOfProcedimentodeanaliseListNewProcedimentodeanalise.equals(medida)) {
                        oldMedidaOfProcedimentodeanaliseListNewProcedimentodeanalise.getProcedimentodeanaliseList().remove(procedimentodeanaliseListNewProcedimentodeanalise);
                        oldMedidaOfProcedimentodeanaliseListNewProcedimentodeanalise = em.merge(oldMedidaOfProcedimentodeanaliseListNewProcedimentodeanalise);
                    }
                }
            }
            for (Procedimentodecoleta procedimentodecoletaListNewProcedimentodecoleta : procedimentodecoletaListNew) {
                if (!procedimentodecoletaListOld.contains(procedimentodecoletaListNewProcedimentodecoleta)) {
                    Medida oldMedidaOfProcedimentodecoletaListNewProcedimentodecoleta = procedimentodecoletaListNewProcedimentodecoleta.getMedida();
                    procedimentodecoletaListNewProcedimentodecoleta.setMedida(medida);
                    procedimentodecoletaListNewProcedimentodecoleta = em.merge(procedimentodecoletaListNewProcedimentodecoleta);
                    if (oldMedidaOfProcedimentodecoletaListNewProcedimentodecoleta != null && !oldMedidaOfProcedimentodecoletaListNewProcedimentodecoleta.equals(medida)) {
                        oldMedidaOfProcedimentodecoletaListNewProcedimentodecoleta.getProcedimentodecoletaList().remove(procedimentodecoletaListNewProcedimentodecoleta);
                        oldMedidaOfProcedimentodecoletaListNewProcedimentodecoleta = em.merge(oldMedidaOfProcedimentodecoletaListNewProcedimentodecoleta);
                    }
                }
            }
            for (Coleta coletaListNewColeta : coletaListNew) {
                if (!coletaListOld.contains(coletaListNewColeta)) {
                    Medida oldMedidaOfColetaListNewColeta = coletaListNewColeta.getMedida();
                    coletaListNewColeta.setMedida(medida);
                    coletaListNewColeta = em.merge(coletaListNewColeta);
                    if (oldMedidaOfColetaListNewColeta != null && !oldMedidaOfColetaListNewColeta.equals(medida)) {
                        oldMedidaOfColetaListNewColeta.getColetaList().remove(coletaListNewColeta);
                        oldMedidaOfColetaListNewColeta = em.merge(oldMedidaOfColetaListNewColeta);
                    }
                }
            }
            for (Aprovacao aprovacaoListNewAprovacao : aprovacaoListNew) {
                if (!aprovacaoListOld.contains(aprovacaoListNewAprovacao)) {
                    Medida oldMedidaOfAprovacaoListNewAprovacao = aprovacaoListNewAprovacao.getMedida();
                    aprovacaoListNewAprovacao.setMedida(medida);
                    aprovacaoListNewAprovacao = em.merge(aprovacaoListNewAprovacao);
                    if (oldMedidaOfAprovacaoListNewAprovacao != null && !oldMedidaOfAprovacaoListNewAprovacao.equals(medida)) {
                        oldMedidaOfAprovacaoListNewAprovacao.getAprovacaoList().remove(aprovacaoListNewAprovacao);
                        oldMedidaOfAprovacaoListNewAprovacao = em.merge(oldMedidaOfAprovacaoListNewAprovacao);
                    }
                }
            }
            for (Analise analiseListNewAnalise : analiseListNew) {
                if (!analiseListOld.contains(analiseListNewAnalise)) {
                    Medida oldMedidaOfAnaliseListNewAnalise = analiseListNewAnalise.getMedida();
                    analiseListNewAnalise.setMedida(medida);
                    analiseListNewAnalise = em.merge(analiseListNewAnalise);
                    if (oldMedidaOfAnaliseListNewAnalise != null && !oldMedidaOfAnaliseListNewAnalise.equals(medida)) {
                        oldMedidaOfAnaliseListNewAnalise.getAnaliseList().remove(analiseListNewAnalise);
                        oldMedidaOfAnaliseListNewAnalise = em.merge(oldMedidaOfAnaliseListNewAnalise);
                    }
                }
            }
            for (Registromedida registromedidaListNewRegistromedida : registromedidaListNew) {
                if (!registromedidaListOld.contains(registromedidaListNewRegistromedida)) {
                    Medida oldMedidaOfRegistromedidaListNewRegistromedida = registromedidaListNewRegistromedida.getMedida();
                    registromedidaListNewRegistromedida.setMedida(medida);
                    registromedidaListNewRegistromedida = em.merge(registromedidaListNewRegistromedida);
                    if (oldMedidaOfRegistromedidaListNewRegistromedida != null && !oldMedidaOfRegistromedidaListNewRegistromedida.equals(medida)) {
                        oldMedidaOfRegistromedidaListNewRegistromedida.getRegistromedidaList().remove(registromedidaListNewRegistromedida);
                        oldMedidaOfRegistromedidaListNewRegistromedida = em.merge(oldMedidaOfRegistromedidaListNewRegistromedida);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                MedidaPK id = medida.getMedidaPK();
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

    public void destroy(MedidaPK id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Medida medida;
            try {
                medida = em.getReference(Medida.class, id);
                medida.getMedidaPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The medida with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Procedimentodeanalise> procedimentodeanaliseListOrphanCheck = medida.getProcedimentodeanaliseList();
            for (Procedimentodeanalise procedimentodeanaliseListOrphanCheckProcedimentodeanalise : procedimentodeanaliseListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Medida (" + medida + ") cannot be destroyed since the Procedimentodeanalise " + procedimentodeanaliseListOrphanCheckProcedimentodeanalise + " in its procedimentodeanaliseList field has a non-nullable medida field.");
            }
            List<Procedimentodecoleta> procedimentodecoletaListOrphanCheck = medida.getProcedimentodecoletaList();
            for (Procedimentodecoleta procedimentodecoletaListOrphanCheckProcedimentodecoleta : procedimentodecoletaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Medida (" + medida + ") cannot be destroyed since the Procedimentodecoleta " + procedimentodecoletaListOrphanCheckProcedimentodecoleta + " in its procedimentodecoletaList field has a non-nullable medida field.");
            }
            List<Coleta> coletaListOrphanCheck = medida.getColetaList();
            for (Coleta coletaListOrphanCheckColeta : coletaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Medida (" + medida + ") cannot be destroyed since the Coleta " + coletaListOrphanCheckColeta + " in its coletaList field has a non-nullable medida field.");
            }
            List<Aprovacao> aprovacaoListOrphanCheck = medida.getAprovacaoList();
            for (Aprovacao aprovacaoListOrphanCheckAprovacao : aprovacaoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Medida (" + medida + ") cannot be destroyed since the Aprovacao " + aprovacaoListOrphanCheckAprovacao + " in its aprovacaoList field has a non-nullable medida field.");
            }
            List<Analise> analiseListOrphanCheck = medida.getAnaliseList();
            for (Analise analiseListOrphanCheckAnalise : analiseListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Medida (" + medida + ") cannot be destroyed since the Analise " + analiseListOrphanCheckAnalise + " in its analiseList field has a non-nullable medida field.");
            }
            List<Registromedida> registromedidaListOrphanCheck = medida.getRegistromedidaList();
            for (Registromedida registromedidaListOrphanCheckRegistromedida : registromedidaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Medida (" + medida + ") cannot be destroyed since the Registromedida " + registromedidaListOrphanCheckRegistromedida + " in its registromedidaList field has a non-nullable medida field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Projeto projeto = medida.getProjeto();
            if (projeto != null) {
                projeto.getMedidaList().remove(medida);
                projeto = em.merge(projeto);
            }
            List<Objetivodequestao> objetivodequestaoList = medida.getObjetivodequestaoList();
            for (Objetivodequestao objetivodequestaoListObjetivodequestao : objetivodequestaoList) {
                objetivodequestaoListObjetivodequestao.getMedidaList().remove(medida);
                objetivodequestaoListObjetivodequestao = em.merge(objetivodequestaoListObjetivodequestao);
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

    public Medida findMedida(MedidaPK id) {
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
