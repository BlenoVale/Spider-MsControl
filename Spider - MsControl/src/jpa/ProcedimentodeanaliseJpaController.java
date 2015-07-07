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
import model.Meiosprocedimentoanalise;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;
import model.Perfisinteressadosprocedimentoanalise;
import model.Procedimentodeanalise;
import model.Registroprocedimentoanalise;
import model.Registrodatacomunicacao;

/**
 *
 * @author BlenoVale
 */
public class ProcedimentodeanaliseJpaController implements Serializable {

    public ProcedimentodeanaliseJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Procedimentodeanalise procedimentodeanalise) {
        if (procedimentodeanalise.getMeiosprocedimentoanaliseList() == null) {
            procedimentodeanalise.setMeiosprocedimentoanaliseList(new ArrayList<Meiosprocedimentoanalise>());
        }
        if (procedimentodeanalise.getPerfisinteressadosprocedimentoanaliseList() == null) {
            procedimentodeanalise.setPerfisinteressadosprocedimentoanaliseList(new ArrayList<Perfisinteressadosprocedimentoanalise>());
        }
        if (procedimentodeanalise.getRegistroprocedimentoanaliseList() == null) {
            procedimentodeanalise.setRegistroprocedimentoanaliseList(new ArrayList<Registroprocedimentoanalise>());
        }
        if (procedimentodeanalise.getRegistrodatacomunicacaoList() == null) {
            procedimentodeanalise.setRegistrodatacomunicacaoList(new ArrayList<Registrodatacomunicacao>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Indicador indicadorid = procedimentodeanalise.getIndicadorid();
            if (indicadorid != null) {
                indicadorid = em.getReference(indicadorid.getClass(), indicadorid.getId());
                procedimentodeanalise.setIndicadorid(indicadorid);
            }
            List<Meiosprocedimentoanalise> attachedMeiosprocedimentoanaliseList = new ArrayList<Meiosprocedimentoanalise>();
            for (Meiosprocedimentoanalise meiosprocedimentoanaliseListMeiosprocedimentoanaliseToAttach : procedimentodeanalise.getMeiosprocedimentoanaliseList()) {
                meiosprocedimentoanaliseListMeiosprocedimentoanaliseToAttach = em.getReference(meiosprocedimentoanaliseListMeiosprocedimentoanaliseToAttach.getClass(), meiosprocedimentoanaliseListMeiosprocedimentoanaliseToAttach.getId());
                attachedMeiosprocedimentoanaliseList.add(meiosprocedimentoanaliseListMeiosprocedimentoanaliseToAttach);
            }
            procedimentodeanalise.setMeiosprocedimentoanaliseList(attachedMeiosprocedimentoanaliseList);
            List<Perfisinteressadosprocedimentoanalise> attachedPerfisinteressadosprocedimentoanaliseList = new ArrayList<Perfisinteressadosprocedimentoanalise>();
            for (Perfisinteressadosprocedimentoanalise perfisinteressadosprocedimentoanaliseListPerfisinteressadosprocedimentoanaliseToAttach : procedimentodeanalise.getPerfisinteressadosprocedimentoanaliseList()) {
                perfisinteressadosprocedimentoanaliseListPerfisinteressadosprocedimentoanaliseToAttach = em.getReference(perfisinteressadosprocedimentoanaliseListPerfisinteressadosprocedimentoanaliseToAttach.getClass(), perfisinteressadosprocedimentoanaliseListPerfisinteressadosprocedimentoanaliseToAttach.getId());
                attachedPerfisinteressadosprocedimentoanaliseList.add(perfisinteressadosprocedimentoanaliseListPerfisinteressadosprocedimentoanaliseToAttach);
            }
            procedimentodeanalise.setPerfisinteressadosprocedimentoanaliseList(attachedPerfisinteressadosprocedimentoanaliseList);
            List<Registroprocedimentoanalise> attachedRegistroprocedimentoanaliseList = new ArrayList<Registroprocedimentoanalise>();
            for (Registroprocedimentoanalise registroprocedimentoanaliseListRegistroprocedimentoanaliseToAttach : procedimentodeanalise.getRegistroprocedimentoanaliseList()) {
                registroprocedimentoanaliseListRegistroprocedimentoanaliseToAttach = em.getReference(registroprocedimentoanaliseListRegistroprocedimentoanaliseToAttach.getClass(), registroprocedimentoanaliseListRegistroprocedimentoanaliseToAttach.getId());
                attachedRegistroprocedimentoanaliseList.add(registroprocedimentoanaliseListRegistroprocedimentoanaliseToAttach);
            }
            procedimentodeanalise.setRegistroprocedimentoanaliseList(attachedRegistroprocedimentoanaliseList);
            List<Registrodatacomunicacao> attachedRegistrodatacomunicacaoList = new ArrayList<Registrodatacomunicacao>();
            for (Registrodatacomunicacao registrodatacomunicacaoListRegistrodatacomunicacaoToAttach : procedimentodeanalise.getRegistrodatacomunicacaoList()) {
                registrodatacomunicacaoListRegistrodatacomunicacaoToAttach = em.getReference(registrodatacomunicacaoListRegistrodatacomunicacaoToAttach.getClass(), registrodatacomunicacaoListRegistrodatacomunicacaoToAttach.getId());
                attachedRegistrodatacomunicacaoList.add(registrodatacomunicacaoListRegistrodatacomunicacaoToAttach);
            }
            procedimentodeanalise.setRegistrodatacomunicacaoList(attachedRegistrodatacomunicacaoList);
            em.persist(procedimentodeanalise);
            if (indicadorid != null) {
                indicadorid.getProcedimentodeanaliseList().add(procedimentodeanalise);
                indicadorid = em.merge(indicadorid);
            }
            for (Meiosprocedimentoanalise meiosprocedimentoanaliseListMeiosprocedimentoanalise : procedimentodeanalise.getMeiosprocedimentoanaliseList()) {
                Procedimentodeanalise oldProcedimentoDeAnaliseidOfMeiosprocedimentoanaliseListMeiosprocedimentoanalise = meiosprocedimentoanaliseListMeiosprocedimentoanalise.getProcedimentoDeAnaliseid();
                meiosprocedimentoanaliseListMeiosprocedimentoanalise.setProcedimentoDeAnaliseid(procedimentodeanalise);
                meiosprocedimentoanaliseListMeiosprocedimentoanalise = em.merge(meiosprocedimentoanaliseListMeiosprocedimentoanalise);
                if (oldProcedimentoDeAnaliseidOfMeiosprocedimentoanaliseListMeiosprocedimentoanalise != null) {
                    oldProcedimentoDeAnaliseidOfMeiosprocedimentoanaliseListMeiosprocedimentoanalise.getMeiosprocedimentoanaliseList().remove(meiosprocedimentoanaliseListMeiosprocedimentoanalise);
                    oldProcedimentoDeAnaliseidOfMeiosprocedimentoanaliseListMeiosprocedimentoanalise = em.merge(oldProcedimentoDeAnaliseidOfMeiosprocedimentoanaliseListMeiosprocedimentoanalise);
                }
            }
            for (Perfisinteressadosprocedimentoanalise perfisinteressadosprocedimentoanaliseListPerfisinteressadosprocedimentoanalise : procedimentodeanalise.getPerfisinteressadosprocedimentoanaliseList()) {
                Procedimentodeanalise oldProcedimentoDeAnaliseidOfPerfisinteressadosprocedimentoanaliseListPerfisinteressadosprocedimentoanalise = perfisinteressadosprocedimentoanaliseListPerfisinteressadosprocedimentoanalise.getProcedimentoDeAnaliseid();
                perfisinteressadosprocedimentoanaliseListPerfisinteressadosprocedimentoanalise.setProcedimentoDeAnaliseid(procedimentodeanalise);
                perfisinteressadosprocedimentoanaliseListPerfisinteressadosprocedimentoanalise = em.merge(perfisinteressadosprocedimentoanaliseListPerfisinteressadosprocedimentoanalise);
                if (oldProcedimentoDeAnaliseidOfPerfisinteressadosprocedimentoanaliseListPerfisinteressadosprocedimentoanalise != null) {
                    oldProcedimentoDeAnaliseidOfPerfisinteressadosprocedimentoanaliseListPerfisinteressadosprocedimentoanalise.getPerfisinteressadosprocedimentoanaliseList().remove(perfisinteressadosprocedimentoanaliseListPerfisinteressadosprocedimentoanalise);
                    oldProcedimentoDeAnaliseidOfPerfisinteressadosprocedimentoanaliseListPerfisinteressadosprocedimentoanalise = em.merge(oldProcedimentoDeAnaliseidOfPerfisinteressadosprocedimentoanaliseListPerfisinteressadosprocedimentoanalise);
                }
            }
            for (Registroprocedimentoanalise registroprocedimentoanaliseListRegistroprocedimentoanalise : procedimentodeanalise.getRegistroprocedimentoanaliseList()) {
                Procedimentodeanalise oldProcedimentoDeAnaliseidOfRegistroprocedimentoanaliseListRegistroprocedimentoanalise = registroprocedimentoanaliseListRegistroprocedimentoanalise.getProcedimentoDeAnaliseid();
                registroprocedimentoanaliseListRegistroprocedimentoanalise.setProcedimentoDeAnaliseid(procedimentodeanalise);
                registroprocedimentoanaliseListRegistroprocedimentoanalise = em.merge(registroprocedimentoanaliseListRegistroprocedimentoanalise);
                if (oldProcedimentoDeAnaliseidOfRegistroprocedimentoanaliseListRegistroprocedimentoanalise != null) {
                    oldProcedimentoDeAnaliseidOfRegistroprocedimentoanaliseListRegistroprocedimentoanalise.getRegistroprocedimentoanaliseList().remove(registroprocedimentoanaliseListRegistroprocedimentoanalise);
                    oldProcedimentoDeAnaliseidOfRegistroprocedimentoanaliseListRegistroprocedimentoanalise = em.merge(oldProcedimentoDeAnaliseidOfRegistroprocedimentoanaliseListRegistroprocedimentoanalise);
                }
            }
            for (Registrodatacomunicacao registrodatacomunicacaoListRegistrodatacomunicacao : procedimentodeanalise.getRegistrodatacomunicacaoList()) {
                Procedimentodeanalise oldProcedimentoDeAnaliseidOfRegistrodatacomunicacaoListRegistrodatacomunicacao = registrodatacomunicacaoListRegistrodatacomunicacao.getProcedimentoDeAnaliseid();
                registrodatacomunicacaoListRegistrodatacomunicacao.setProcedimentoDeAnaliseid(procedimentodeanalise);
                registrodatacomunicacaoListRegistrodatacomunicacao = em.merge(registrodatacomunicacaoListRegistrodatacomunicacao);
                if (oldProcedimentoDeAnaliseidOfRegistrodatacomunicacaoListRegistrodatacomunicacao != null) {
                    oldProcedimentoDeAnaliseidOfRegistrodatacomunicacaoListRegistrodatacomunicacao.getRegistrodatacomunicacaoList().remove(registrodatacomunicacaoListRegistrodatacomunicacao);
                    oldProcedimentoDeAnaliseidOfRegistrodatacomunicacaoListRegistrodatacomunicacao = em.merge(oldProcedimentoDeAnaliseidOfRegistrodatacomunicacaoListRegistrodatacomunicacao);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Procedimentodeanalise procedimentodeanalise) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Procedimentodeanalise persistentProcedimentodeanalise = em.find(Procedimentodeanalise.class, procedimentodeanalise.getId());
            Indicador indicadoridOld = persistentProcedimentodeanalise.getIndicadorid();
            Indicador indicadoridNew = procedimentodeanalise.getIndicadorid();
            List<Meiosprocedimentoanalise> meiosprocedimentoanaliseListOld = persistentProcedimentodeanalise.getMeiosprocedimentoanaliseList();
            List<Meiosprocedimentoanalise> meiosprocedimentoanaliseListNew = procedimentodeanalise.getMeiosprocedimentoanaliseList();
            List<Perfisinteressadosprocedimentoanalise> perfisinteressadosprocedimentoanaliseListOld = persistentProcedimentodeanalise.getPerfisinteressadosprocedimentoanaliseList();
            List<Perfisinteressadosprocedimentoanalise> perfisinteressadosprocedimentoanaliseListNew = procedimentodeanalise.getPerfisinteressadosprocedimentoanaliseList();
            List<Registroprocedimentoanalise> registroprocedimentoanaliseListOld = persistentProcedimentodeanalise.getRegistroprocedimentoanaliseList();
            List<Registroprocedimentoanalise> registroprocedimentoanaliseListNew = procedimentodeanalise.getRegistroprocedimentoanaliseList();
            List<Registrodatacomunicacao> registrodatacomunicacaoListOld = persistentProcedimentodeanalise.getRegistrodatacomunicacaoList();
            List<Registrodatacomunicacao> registrodatacomunicacaoListNew = procedimentodeanalise.getRegistrodatacomunicacaoList();
            List<String> illegalOrphanMessages = null;
            for (Meiosprocedimentoanalise meiosprocedimentoanaliseListOldMeiosprocedimentoanalise : meiosprocedimentoanaliseListOld) {
                if (!meiosprocedimentoanaliseListNew.contains(meiosprocedimentoanaliseListOldMeiosprocedimentoanalise)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Meiosprocedimentoanalise " + meiosprocedimentoanaliseListOldMeiosprocedimentoanalise + " since its procedimentoDeAnaliseid field is not nullable.");
                }
            }
            for (Perfisinteressadosprocedimentoanalise perfisinteressadosprocedimentoanaliseListOldPerfisinteressadosprocedimentoanalise : perfisinteressadosprocedimentoanaliseListOld) {
                if (!perfisinteressadosprocedimentoanaliseListNew.contains(perfisinteressadosprocedimentoanaliseListOldPerfisinteressadosprocedimentoanalise)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Perfisinteressadosprocedimentoanalise " + perfisinteressadosprocedimentoanaliseListOldPerfisinteressadosprocedimentoanalise + " since its procedimentoDeAnaliseid field is not nullable.");
                }
            }
            for (Registroprocedimentoanalise registroprocedimentoanaliseListOldRegistroprocedimentoanalise : registroprocedimentoanaliseListOld) {
                if (!registroprocedimentoanaliseListNew.contains(registroprocedimentoanaliseListOldRegistroprocedimentoanalise)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Registroprocedimentoanalise " + registroprocedimentoanaliseListOldRegistroprocedimentoanalise + " since its procedimentoDeAnaliseid field is not nullable.");
                }
            }
            for (Registrodatacomunicacao registrodatacomunicacaoListOldRegistrodatacomunicacao : registrodatacomunicacaoListOld) {
                if (!registrodatacomunicacaoListNew.contains(registrodatacomunicacaoListOldRegistrodatacomunicacao)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Registrodatacomunicacao " + registrodatacomunicacaoListOldRegistrodatacomunicacao + " since its procedimentoDeAnaliseid field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (indicadoridNew != null) {
                indicadoridNew = em.getReference(indicadoridNew.getClass(), indicadoridNew.getId());
                procedimentodeanalise.setIndicadorid(indicadoridNew);
            }
            List<Meiosprocedimentoanalise> attachedMeiosprocedimentoanaliseListNew = new ArrayList<Meiosprocedimentoanalise>();
            for (Meiosprocedimentoanalise meiosprocedimentoanaliseListNewMeiosprocedimentoanaliseToAttach : meiosprocedimentoanaliseListNew) {
                meiosprocedimentoanaliseListNewMeiosprocedimentoanaliseToAttach = em.getReference(meiosprocedimentoanaliseListNewMeiosprocedimentoanaliseToAttach.getClass(), meiosprocedimentoanaliseListNewMeiosprocedimentoanaliseToAttach.getId());
                attachedMeiosprocedimentoanaliseListNew.add(meiosprocedimentoanaliseListNewMeiosprocedimentoanaliseToAttach);
            }
            meiosprocedimentoanaliseListNew = attachedMeiosprocedimentoanaliseListNew;
            procedimentodeanalise.setMeiosprocedimentoanaliseList(meiosprocedimentoanaliseListNew);
            List<Perfisinteressadosprocedimentoanalise> attachedPerfisinteressadosprocedimentoanaliseListNew = new ArrayList<Perfisinteressadosprocedimentoanalise>();
            for (Perfisinteressadosprocedimentoanalise perfisinteressadosprocedimentoanaliseListNewPerfisinteressadosprocedimentoanaliseToAttach : perfisinteressadosprocedimentoanaliseListNew) {
                perfisinteressadosprocedimentoanaliseListNewPerfisinteressadosprocedimentoanaliseToAttach = em.getReference(perfisinteressadosprocedimentoanaliseListNewPerfisinteressadosprocedimentoanaliseToAttach.getClass(), perfisinteressadosprocedimentoanaliseListNewPerfisinteressadosprocedimentoanaliseToAttach.getId());
                attachedPerfisinteressadosprocedimentoanaliseListNew.add(perfisinteressadosprocedimentoanaliseListNewPerfisinteressadosprocedimentoanaliseToAttach);
            }
            perfisinteressadosprocedimentoanaliseListNew = attachedPerfisinteressadosprocedimentoanaliseListNew;
            procedimentodeanalise.setPerfisinteressadosprocedimentoanaliseList(perfisinteressadosprocedimentoanaliseListNew);
            List<Registroprocedimentoanalise> attachedRegistroprocedimentoanaliseListNew = new ArrayList<Registroprocedimentoanalise>();
            for (Registroprocedimentoanalise registroprocedimentoanaliseListNewRegistroprocedimentoanaliseToAttach : registroprocedimentoanaliseListNew) {
                registroprocedimentoanaliseListNewRegistroprocedimentoanaliseToAttach = em.getReference(registroprocedimentoanaliseListNewRegistroprocedimentoanaliseToAttach.getClass(), registroprocedimentoanaliseListNewRegistroprocedimentoanaliseToAttach.getId());
                attachedRegistroprocedimentoanaliseListNew.add(registroprocedimentoanaliseListNewRegistroprocedimentoanaliseToAttach);
            }
            registroprocedimentoanaliseListNew = attachedRegistroprocedimentoanaliseListNew;
            procedimentodeanalise.setRegistroprocedimentoanaliseList(registroprocedimentoanaliseListNew);
            List<Registrodatacomunicacao> attachedRegistrodatacomunicacaoListNew = new ArrayList<Registrodatacomunicacao>();
            for (Registrodatacomunicacao registrodatacomunicacaoListNewRegistrodatacomunicacaoToAttach : registrodatacomunicacaoListNew) {
                registrodatacomunicacaoListNewRegistrodatacomunicacaoToAttach = em.getReference(registrodatacomunicacaoListNewRegistrodatacomunicacaoToAttach.getClass(), registrodatacomunicacaoListNewRegistrodatacomunicacaoToAttach.getId());
                attachedRegistrodatacomunicacaoListNew.add(registrodatacomunicacaoListNewRegistrodatacomunicacaoToAttach);
            }
            registrodatacomunicacaoListNew = attachedRegistrodatacomunicacaoListNew;
            procedimentodeanalise.setRegistrodatacomunicacaoList(registrodatacomunicacaoListNew);
            procedimentodeanalise = em.merge(procedimentodeanalise);
            if (indicadoridOld != null && !indicadoridOld.equals(indicadoridNew)) {
                indicadoridOld.getProcedimentodeanaliseList().remove(procedimentodeanalise);
                indicadoridOld = em.merge(indicadoridOld);
            }
            if (indicadoridNew != null && !indicadoridNew.equals(indicadoridOld)) {
                indicadoridNew.getProcedimentodeanaliseList().add(procedimentodeanalise);
                indicadoridNew = em.merge(indicadoridNew);
            }
            for (Meiosprocedimentoanalise meiosprocedimentoanaliseListNewMeiosprocedimentoanalise : meiosprocedimentoanaliseListNew) {
                if (!meiosprocedimentoanaliseListOld.contains(meiosprocedimentoanaliseListNewMeiosprocedimentoanalise)) {
                    Procedimentodeanalise oldProcedimentoDeAnaliseidOfMeiosprocedimentoanaliseListNewMeiosprocedimentoanalise = meiosprocedimentoanaliseListNewMeiosprocedimentoanalise.getProcedimentoDeAnaliseid();
                    meiosprocedimentoanaliseListNewMeiosprocedimentoanalise.setProcedimentoDeAnaliseid(procedimentodeanalise);
                    meiosprocedimentoanaliseListNewMeiosprocedimentoanalise = em.merge(meiosprocedimentoanaliseListNewMeiosprocedimentoanalise);
                    if (oldProcedimentoDeAnaliseidOfMeiosprocedimentoanaliseListNewMeiosprocedimentoanalise != null && !oldProcedimentoDeAnaliseidOfMeiosprocedimentoanaliseListNewMeiosprocedimentoanalise.equals(procedimentodeanalise)) {
                        oldProcedimentoDeAnaliseidOfMeiosprocedimentoanaliseListNewMeiosprocedimentoanalise.getMeiosprocedimentoanaliseList().remove(meiosprocedimentoanaliseListNewMeiosprocedimentoanalise);
                        oldProcedimentoDeAnaliseidOfMeiosprocedimentoanaliseListNewMeiosprocedimentoanalise = em.merge(oldProcedimentoDeAnaliseidOfMeiosprocedimentoanaliseListNewMeiosprocedimentoanalise);
                    }
                }
            }
            for (Perfisinteressadosprocedimentoanalise perfisinteressadosprocedimentoanaliseListNewPerfisinteressadosprocedimentoanalise : perfisinteressadosprocedimentoanaliseListNew) {
                if (!perfisinteressadosprocedimentoanaliseListOld.contains(perfisinteressadosprocedimentoanaliseListNewPerfisinteressadosprocedimentoanalise)) {
                    Procedimentodeanalise oldProcedimentoDeAnaliseidOfPerfisinteressadosprocedimentoanaliseListNewPerfisinteressadosprocedimentoanalise = perfisinteressadosprocedimentoanaliseListNewPerfisinteressadosprocedimentoanalise.getProcedimentoDeAnaliseid();
                    perfisinteressadosprocedimentoanaliseListNewPerfisinteressadosprocedimentoanalise.setProcedimentoDeAnaliseid(procedimentodeanalise);
                    perfisinteressadosprocedimentoanaliseListNewPerfisinteressadosprocedimentoanalise = em.merge(perfisinteressadosprocedimentoanaliseListNewPerfisinteressadosprocedimentoanalise);
                    if (oldProcedimentoDeAnaliseidOfPerfisinteressadosprocedimentoanaliseListNewPerfisinteressadosprocedimentoanalise != null && !oldProcedimentoDeAnaliseidOfPerfisinteressadosprocedimentoanaliseListNewPerfisinteressadosprocedimentoanalise.equals(procedimentodeanalise)) {
                        oldProcedimentoDeAnaliseidOfPerfisinteressadosprocedimentoanaliseListNewPerfisinteressadosprocedimentoanalise.getPerfisinteressadosprocedimentoanaliseList().remove(perfisinteressadosprocedimentoanaliseListNewPerfisinteressadosprocedimentoanalise);
                        oldProcedimentoDeAnaliseidOfPerfisinteressadosprocedimentoanaliseListNewPerfisinteressadosprocedimentoanalise = em.merge(oldProcedimentoDeAnaliseidOfPerfisinteressadosprocedimentoanaliseListNewPerfisinteressadosprocedimentoanalise);
                    }
                }
            }
            for (Registroprocedimentoanalise registroprocedimentoanaliseListNewRegistroprocedimentoanalise : registroprocedimentoanaliseListNew) {
                if (!registroprocedimentoanaliseListOld.contains(registroprocedimentoanaliseListNewRegistroprocedimentoanalise)) {
                    Procedimentodeanalise oldProcedimentoDeAnaliseidOfRegistroprocedimentoanaliseListNewRegistroprocedimentoanalise = registroprocedimentoanaliseListNewRegistroprocedimentoanalise.getProcedimentoDeAnaliseid();
                    registroprocedimentoanaliseListNewRegistroprocedimentoanalise.setProcedimentoDeAnaliseid(procedimentodeanalise);
                    registroprocedimentoanaliseListNewRegistroprocedimentoanalise = em.merge(registroprocedimentoanaliseListNewRegistroprocedimentoanalise);
                    if (oldProcedimentoDeAnaliseidOfRegistroprocedimentoanaliseListNewRegistroprocedimentoanalise != null && !oldProcedimentoDeAnaliseidOfRegistroprocedimentoanaliseListNewRegistroprocedimentoanalise.equals(procedimentodeanalise)) {
                        oldProcedimentoDeAnaliseidOfRegistroprocedimentoanaliseListNewRegistroprocedimentoanalise.getRegistroprocedimentoanaliseList().remove(registroprocedimentoanaliseListNewRegistroprocedimentoanalise);
                        oldProcedimentoDeAnaliseidOfRegistroprocedimentoanaliseListNewRegistroprocedimentoanalise = em.merge(oldProcedimentoDeAnaliseidOfRegistroprocedimentoanaliseListNewRegistroprocedimentoanalise);
                    }
                }
            }
            for (Registrodatacomunicacao registrodatacomunicacaoListNewRegistrodatacomunicacao : registrodatacomunicacaoListNew) {
                if (!registrodatacomunicacaoListOld.contains(registrodatacomunicacaoListNewRegistrodatacomunicacao)) {
                    Procedimentodeanalise oldProcedimentoDeAnaliseidOfRegistrodatacomunicacaoListNewRegistrodatacomunicacao = registrodatacomunicacaoListNewRegistrodatacomunicacao.getProcedimentoDeAnaliseid();
                    registrodatacomunicacaoListNewRegistrodatacomunicacao.setProcedimentoDeAnaliseid(procedimentodeanalise);
                    registrodatacomunicacaoListNewRegistrodatacomunicacao = em.merge(registrodatacomunicacaoListNewRegistrodatacomunicacao);
                    if (oldProcedimentoDeAnaliseidOfRegistrodatacomunicacaoListNewRegistrodatacomunicacao != null && !oldProcedimentoDeAnaliseidOfRegistrodatacomunicacaoListNewRegistrodatacomunicacao.equals(procedimentodeanalise)) {
                        oldProcedimentoDeAnaliseidOfRegistrodatacomunicacaoListNewRegistrodatacomunicacao.getRegistrodatacomunicacaoList().remove(registrodatacomunicacaoListNewRegistrodatacomunicacao);
                        oldProcedimentoDeAnaliseidOfRegistrodatacomunicacaoListNewRegistrodatacomunicacao = em.merge(oldProcedimentoDeAnaliseidOfRegistrodatacomunicacaoListNewRegistrodatacomunicacao);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = procedimentodeanalise.getId();
                if (findProcedimentodeanalise(id) == null) {
                    throw new NonexistentEntityException("The procedimentodeanalise with id " + id + " no longer exists.");
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
            Procedimentodeanalise procedimentodeanalise;
            try {
                procedimentodeanalise = em.getReference(Procedimentodeanalise.class, id);
                procedimentodeanalise.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The procedimentodeanalise with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Meiosprocedimentoanalise> meiosprocedimentoanaliseListOrphanCheck = procedimentodeanalise.getMeiosprocedimentoanaliseList();
            for (Meiosprocedimentoanalise meiosprocedimentoanaliseListOrphanCheckMeiosprocedimentoanalise : meiosprocedimentoanaliseListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Procedimentodeanalise (" + procedimentodeanalise + ") cannot be destroyed since the Meiosprocedimentoanalise " + meiosprocedimentoanaliseListOrphanCheckMeiosprocedimentoanalise + " in its meiosprocedimentoanaliseList field has a non-nullable procedimentoDeAnaliseid field.");
            }
            List<Perfisinteressadosprocedimentoanalise> perfisinteressadosprocedimentoanaliseListOrphanCheck = procedimentodeanalise.getPerfisinteressadosprocedimentoanaliseList();
            for (Perfisinteressadosprocedimentoanalise perfisinteressadosprocedimentoanaliseListOrphanCheckPerfisinteressadosprocedimentoanalise : perfisinteressadosprocedimentoanaliseListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Procedimentodeanalise (" + procedimentodeanalise + ") cannot be destroyed since the Perfisinteressadosprocedimentoanalise " + perfisinteressadosprocedimentoanaliseListOrphanCheckPerfisinteressadosprocedimentoanalise + " in its perfisinteressadosprocedimentoanaliseList field has a non-nullable procedimentoDeAnaliseid field.");
            }
            List<Registroprocedimentoanalise> registroprocedimentoanaliseListOrphanCheck = procedimentodeanalise.getRegistroprocedimentoanaliseList();
            for (Registroprocedimentoanalise registroprocedimentoanaliseListOrphanCheckRegistroprocedimentoanalise : registroprocedimentoanaliseListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Procedimentodeanalise (" + procedimentodeanalise + ") cannot be destroyed since the Registroprocedimentoanalise " + registroprocedimentoanaliseListOrphanCheckRegistroprocedimentoanalise + " in its registroprocedimentoanaliseList field has a non-nullable procedimentoDeAnaliseid field.");
            }
            List<Registrodatacomunicacao> registrodatacomunicacaoListOrphanCheck = procedimentodeanalise.getRegistrodatacomunicacaoList();
            for (Registrodatacomunicacao registrodatacomunicacaoListOrphanCheckRegistrodatacomunicacao : registrodatacomunicacaoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Procedimentodeanalise (" + procedimentodeanalise + ") cannot be destroyed since the Registrodatacomunicacao " + registrodatacomunicacaoListOrphanCheckRegistrodatacomunicacao + " in its registrodatacomunicacaoList field has a non-nullable procedimentoDeAnaliseid field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Indicador indicadorid = procedimentodeanalise.getIndicadorid();
            if (indicadorid != null) {
                indicadorid.getProcedimentodeanaliseList().remove(procedimentodeanalise);
                indicadorid = em.merge(indicadorid);
            }
            em.remove(procedimentodeanalise);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Procedimentodeanalise> findProcedimentodeanaliseEntities() {
        return findProcedimentodeanaliseEntities(true, -1, -1);
    }

    public List<Procedimentodeanalise> findProcedimentodeanaliseEntities(int maxResults, int firstResult) {
        return findProcedimentodeanaliseEntities(false, maxResults, firstResult);
    }

    private List<Procedimentodeanalise> findProcedimentodeanaliseEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Procedimentodeanalise.class));
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

    public Procedimentodeanalise findProcedimentodeanalise(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Procedimentodeanalise.class, id);
        } finally {
            em.close();
        }
    }

    public int getProcedimentodeanaliseCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Procedimentodeanalise> rt = cq.from(Procedimentodeanalise.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
