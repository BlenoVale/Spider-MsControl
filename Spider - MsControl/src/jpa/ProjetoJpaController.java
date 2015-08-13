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
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;
import model.Relatorios;
import model.Perfilinteressado;
import model.Registroprojeto;
import model.Acessa;
import model.Entidademedida;
import model.Meioscomunicacao;
import model.Projeto;

/**
 *
 * @author Bleno Vale
 */
public class ProjetoJpaController implements Serializable {

    public ProjetoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Projeto projeto) {
        if (projeto.getObjetivodemedicaoList() == null) {
            projeto.setObjetivodemedicaoList(new ArrayList<Objetivodemedicao>());
        }
        if (projeto.getRelatoriosList() == null) {
            projeto.setRelatoriosList(new ArrayList<Relatorios>());
        }
        if (projeto.getPerfilinteressadoList() == null) {
            projeto.setPerfilinteressadoList(new ArrayList<Perfilinteressado>());
        }
        if (projeto.getRegistroprojetoList() == null) {
            projeto.setRegistroprojetoList(new ArrayList<Registroprojeto>());
        }
        if (projeto.getAcessaList() == null) {
            projeto.setAcessaList(new ArrayList<Acessa>());
        }
        if (projeto.getEntidademedidaList() == null) {
            projeto.setEntidademedidaList(new ArrayList<Entidademedida>());
        }
        if (projeto.getMeioscomunicacaoList() == null) {
            projeto.setMeioscomunicacaoList(new ArrayList<Meioscomunicacao>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Objetivodemedicao> attachedObjetivodemedicaoList = new ArrayList<Objetivodemedicao>();
            for (Objetivodemedicao objetivodemedicaoListObjetivodemedicaoToAttach : projeto.getObjetivodemedicaoList()) {
                objetivodemedicaoListObjetivodemedicaoToAttach = em.getReference(objetivodemedicaoListObjetivodemedicaoToAttach.getClass(), objetivodemedicaoListObjetivodemedicaoToAttach.getId());
                attachedObjetivodemedicaoList.add(objetivodemedicaoListObjetivodemedicaoToAttach);
            }
            projeto.setObjetivodemedicaoList(attachedObjetivodemedicaoList);
            List<Relatorios> attachedRelatoriosList = new ArrayList<Relatorios>();
            for (Relatorios relatoriosListRelatoriosToAttach : projeto.getRelatoriosList()) {
                relatoriosListRelatoriosToAttach = em.getReference(relatoriosListRelatoriosToAttach.getClass(), relatoriosListRelatoriosToAttach.getIdRelatorio());
                attachedRelatoriosList.add(relatoriosListRelatoriosToAttach);
            }
            projeto.setRelatoriosList(attachedRelatoriosList);
            List<Perfilinteressado> attachedPerfilinteressadoList = new ArrayList<Perfilinteressado>();
            for (Perfilinteressado perfilinteressadoListPerfilinteressadoToAttach : projeto.getPerfilinteressadoList()) {
                perfilinteressadoListPerfilinteressadoToAttach = em.getReference(perfilinteressadoListPerfilinteressadoToAttach.getClass(), perfilinteressadoListPerfilinteressadoToAttach.getId());
                attachedPerfilinteressadoList.add(perfilinteressadoListPerfilinteressadoToAttach);
            }
            projeto.setPerfilinteressadoList(attachedPerfilinteressadoList);
            List<Registroprojeto> attachedRegistroprojetoList = new ArrayList<Registroprojeto>();
            for (Registroprojeto registroprojetoListRegistroprojetoToAttach : projeto.getRegistroprojetoList()) {
                registroprojetoListRegistroprojetoToAttach = em.getReference(registroprojetoListRegistroprojetoToAttach.getClass(), registroprojetoListRegistroprojetoToAttach.getId());
                attachedRegistroprojetoList.add(registroprojetoListRegistroprojetoToAttach);
            }
            projeto.setRegistroprojetoList(attachedRegistroprojetoList);
            List<Acessa> attachedAcessaList = new ArrayList<Acessa>();
            for (Acessa acessaListAcessaToAttach : projeto.getAcessaList()) {
                acessaListAcessaToAttach = em.getReference(acessaListAcessaToAttach.getClass(), acessaListAcessaToAttach.getAcessaPK());
                attachedAcessaList.add(acessaListAcessaToAttach);
            }
            projeto.setAcessaList(attachedAcessaList);
            List<Entidademedida> attachedEntidademedidaList = new ArrayList<Entidademedida>();
            for (Entidademedida entidademedidaListEntidademedidaToAttach : projeto.getEntidademedidaList()) {
                entidademedidaListEntidademedidaToAttach = em.getReference(entidademedidaListEntidademedidaToAttach.getClass(), entidademedidaListEntidademedidaToAttach.getId());
                attachedEntidademedidaList.add(entidademedidaListEntidademedidaToAttach);
            }
            projeto.setEntidademedidaList(attachedEntidademedidaList);
            List<Meioscomunicacao> attachedMeioscomunicacaoList = new ArrayList<Meioscomunicacao>();
            for (Meioscomunicacao meioscomunicacaoListMeioscomunicacaoToAttach : projeto.getMeioscomunicacaoList()) {
                meioscomunicacaoListMeioscomunicacaoToAttach = em.getReference(meioscomunicacaoListMeioscomunicacaoToAttach.getClass(), meioscomunicacaoListMeioscomunicacaoToAttach.getId());
                attachedMeioscomunicacaoList.add(meioscomunicacaoListMeioscomunicacaoToAttach);
            }
            projeto.setMeioscomunicacaoList(attachedMeioscomunicacaoList);
            em.persist(projeto);
            for (Objetivodemedicao objetivodemedicaoListObjetivodemedicao : projeto.getObjetivodemedicaoList()) {
                Projeto oldProjetoidOfObjetivodemedicaoListObjetivodemedicao = objetivodemedicaoListObjetivodemedicao.getProjetoid();
                objetivodemedicaoListObjetivodemedicao.setProjetoid(projeto);
                objetivodemedicaoListObjetivodemedicao = em.merge(objetivodemedicaoListObjetivodemedicao);
                if (oldProjetoidOfObjetivodemedicaoListObjetivodemedicao != null) {
                    oldProjetoidOfObjetivodemedicaoListObjetivodemedicao.getObjetivodemedicaoList().remove(objetivodemedicaoListObjetivodemedicao);
                    oldProjetoidOfObjetivodemedicaoListObjetivodemedicao = em.merge(oldProjetoidOfObjetivodemedicaoListObjetivodemedicao);
                }
            }
            for (Relatorios relatoriosListRelatorios : projeto.getRelatoriosList()) {
                Projeto oldProjetoidOfRelatoriosListRelatorios = relatoriosListRelatorios.getProjetoid();
                relatoriosListRelatorios.setProjetoid(projeto);
                relatoriosListRelatorios = em.merge(relatoriosListRelatorios);
                if (oldProjetoidOfRelatoriosListRelatorios != null) {
                    oldProjetoidOfRelatoriosListRelatorios.getRelatoriosList().remove(relatoriosListRelatorios);
                    oldProjetoidOfRelatoriosListRelatorios = em.merge(oldProjetoidOfRelatoriosListRelatorios);
                }
            }
            for (Perfilinteressado perfilinteressadoListPerfilinteressado : projeto.getPerfilinteressadoList()) {
                Projeto oldProjetoidOfPerfilinteressadoListPerfilinteressado = perfilinteressadoListPerfilinteressado.getProjetoid();
                perfilinteressadoListPerfilinteressado.setProjetoid(projeto);
                perfilinteressadoListPerfilinteressado = em.merge(perfilinteressadoListPerfilinteressado);
                if (oldProjetoidOfPerfilinteressadoListPerfilinteressado != null) {
                    oldProjetoidOfPerfilinteressadoListPerfilinteressado.getPerfilinteressadoList().remove(perfilinteressadoListPerfilinteressado);
                    oldProjetoidOfPerfilinteressadoListPerfilinteressado = em.merge(oldProjetoidOfPerfilinteressadoListPerfilinteressado);
                }
            }
            for (Registroprojeto registroprojetoListRegistroprojeto : projeto.getRegistroprojetoList()) {
                Projeto oldProjetoidOfRegistroprojetoListRegistroprojeto = registroprojetoListRegistroprojeto.getProjetoid();
                registroprojetoListRegistroprojeto.setProjetoid(projeto);
                registroprojetoListRegistroprojeto = em.merge(registroprojetoListRegistroprojeto);
                if (oldProjetoidOfRegistroprojetoListRegistroprojeto != null) {
                    oldProjetoidOfRegistroprojetoListRegistroprojeto.getRegistroprojetoList().remove(registroprojetoListRegistroprojeto);
                    oldProjetoidOfRegistroprojetoListRegistroprojeto = em.merge(oldProjetoidOfRegistroprojetoListRegistroprojeto);
                }
            }
            for (Acessa acessaListAcessa : projeto.getAcessaList()) {
                Projeto oldProjetoOfAcessaListAcessa = acessaListAcessa.getProjeto();
                acessaListAcessa.setProjeto(projeto);
                acessaListAcessa = em.merge(acessaListAcessa);
                if (oldProjetoOfAcessaListAcessa != null) {
                    oldProjetoOfAcessaListAcessa.getAcessaList().remove(acessaListAcessa);
                    oldProjetoOfAcessaListAcessa = em.merge(oldProjetoOfAcessaListAcessa);
                }
            }
            for (Entidademedida entidademedidaListEntidademedida : projeto.getEntidademedidaList()) {
                Projeto oldProjetoidOfEntidademedidaListEntidademedida = entidademedidaListEntidademedida.getProjetoid();
                entidademedidaListEntidademedida.setProjetoid(projeto);
                entidademedidaListEntidademedida = em.merge(entidademedidaListEntidademedida);
                if (oldProjetoidOfEntidademedidaListEntidademedida != null) {
                    oldProjetoidOfEntidademedidaListEntidademedida.getEntidademedidaList().remove(entidademedidaListEntidademedida);
                    oldProjetoidOfEntidademedidaListEntidademedida = em.merge(oldProjetoidOfEntidademedidaListEntidademedida);
                }
            }
            for (Meioscomunicacao meioscomunicacaoListMeioscomunicacao : projeto.getMeioscomunicacaoList()) {
                Projeto oldProjetoidOfMeioscomunicacaoListMeioscomunicacao = meioscomunicacaoListMeioscomunicacao.getProjetoid();
                meioscomunicacaoListMeioscomunicacao.setProjetoid(projeto);
                meioscomunicacaoListMeioscomunicacao = em.merge(meioscomunicacaoListMeioscomunicacao);
                if (oldProjetoidOfMeioscomunicacaoListMeioscomunicacao != null) {
                    oldProjetoidOfMeioscomunicacaoListMeioscomunicacao.getMeioscomunicacaoList().remove(meioscomunicacaoListMeioscomunicacao);
                    oldProjetoidOfMeioscomunicacaoListMeioscomunicacao = em.merge(oldProjetoidOfMeioscomunicacaoListMeioscomunicacao);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Projeto projeto) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Projeto persistentProjeto = em.find(Projeto.class, projeto.getId());
            List<Objetivodemedicao> objetivodemedicaoListOld = persistentProjeto.getObjetivodemedicaoList();
            List<Objetivodemedicao> objetivodemedicaoListNew = projeto.getObjetivodemedicaoList();
            List<Relatorios> relatoriosListOld = persistentProjeto.getRelatoriosList();
            List<Relatorios> relatoriosListNew = projeto.getRelatoriosList();
            List<Perfilinteressado> perfilinteressadoListOld = persistentProjeto.getPerfilinteressadoList();
            List<Perfilinteressado> perfilinteressadoListNew = projeto.getPerfilinteressadoList();
            List<Registroprojeto> registroprojetoListOld = persistentProjeto.getRegistroprojetoList();
            List<Registroprojeto> registroprojetoListNew = projeto.getRegistroprojetoList();
            List<Acessa> acessaListOld = persistentProjeto.getAcessaList();
            List<Acessa> acessaListNew = projeto.getAcessaList();
            List<Entidademedida> entidademedidaListOld = persistentProjeto.getEntidademedidaList();
            List<Entidademedida> entidademedidaListNew = projeto.getEntidademedidaList();
            List<Meioscomunicacao> meioscomunicacaoListOld = persistentProjeto.getMeioscomunicacaoList();
            List<Meioscomunicacao> meioscomunicacaoListNew = projeto.getMeioscomunicacaoList();
            List<String> illegalOrphanMessages = null;
            for (Objetivodemedicao objetivodemedicaoListOldObjetivodemedicao : objetivodemedicaoListOld) {
                if (!objetivodemedicaoListNew.contains(objetivodemedicaoListOldObjetivodemedicao)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Objetivodemedicao " + objetivodemedicaoListOldObjetivodemedicao + " since its projetoid field is not nullable.");
                }
            }
            for (Relatorios relatoriosListOldRelatorios : relatoriosListOld) {
                if (!relatoriosListNew.contains(relatoriosListOldRelatorios)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Relatorios " + relatoriosListOldRelatorios + " since its projetoid field is not nullable.");
                }
            }
            for (Registroprojeto registroprojetoListOldRegistroprojeto : registroprojetoListOld) {
                if (!registroprojetoListNew.contains(registroprojetoListOldRegistroprojeto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Registroprojeto " + registroprojetoListOldRegistroprojeto + " since its projetoid field is not nullable.");
                }
            }
            for (Acessa acessaListOldAcessa : acessaListOld) {
                if (!acessaListNew.contains(acessaListOldAcessa)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Acessa " + acessaListOldAcessa + " since its projeto field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Objetivodemedicao> attachedObjetivodemedicaoListNew = new ArrayList<Objetivodemedicao>();
            for (Objetivodemedicao objetivodemedicaoListNewObjetivodemedicaoToAttach : objetivodemedicaoListNew) {
                objetivodemedicaoListNewObjetivodemedicaoToAttach = em.getReference(objetivodemedicaoListNewObjetivodemedicaoToAttach.getClass(), objetivodemedicaoListNewObjetivodemedicaoToAttach.getId());
                attachedObjetivodemedicaoListNew.add(objetivodemedicaoListNewObjetivodemedicaoToAttach);
            }
            objetivodemedicaoListNew = attachedObjetivodemedicaoListNew;
            projeto.setObjetivodemedicaoList(objetivodemedicaoListNew);
            List<Relatorios> attachedRelatoriosListNew = new ArrayList<Relatorios>();
            for (Relatorios relatoriosListNewRelatoriosToAttach : relatoriosListNew) {
                relatoriosListNewRelatoriosToAttach = em.getReference(relatoriosListNewRelatoriosToAttach.getClass(), relatoriosListNewRelatoriosToAttach.getIdRelatorio());
                attachedRelatoriosListNew.add(relatoriosListNewRelatoriosToAttach);
            }
            relatoriosListNew = attachedRelatoriosListNew;
            projeto.setRelatoriosList(relatoriosListNew);
            List<Perfilinteressado> attachedPerfilinteressadoListNew = new ArrayList<Perfilinteressado>();
            for (Perfilinteressado perfilinteressadoListNewPerfilinteressadoToAttach : perfilinteressadoListNew) {
                perfilinteressadoListNewPerfilinteressadoToAttach = em.getReference(perfilinteressadoListNewPerfilinteressadoToAttach.getClass(), perfilinteressadoListNewPerfilinteressadoToAttach.getId());
                attachedPerfilinteressadoListNew.add(perfilinteressadoListNewPerfilinteressadoToAttach);
            }
            perfilinteressadoListNew = attachedPerfilinteressadoListNew;
            projeto.setPerfilinteressadoList(perfilinteressadoListNew);
            List<Registroprojeto> attachedRegistroprojetoListNew = new ArrayList<Registroprojeto>();
            for (Registroprojeto registroprojetoListNewRegistroprojetoToAttach : registroprojetoListNew) {
                registroprojetoListNewRegistroprojetoToAttach = em.getReference(registroprojetoListNewRegistroprojetoToAttach.getClass(), registroprojetoListNewRegistroprojetoToAttach.getId());
                attachedRegistroprojetoListNew.add(registroprojetoListNewRegistroprojetoToAttach);
            }
            registroprojetoListNew = attachedRegistroprojetoListNew;
            projeto.setRegistroprojetoList(registroprojetoListNew);
            List<Acessa> attachedAcessaListNew = new ArrayList<Acessa>();
            for (Acessa acessaListNewAcessaToAttach : acessaListNew) {
                acessaListNewAcessaToAttach = em.getReference(acessaListNewAcessaToAttach.getClass(), acessaListNewAcessaToAttach.getAcessaPK());
                attachedAcessaListNew.add(acessaListNewAcessaToAttach);
            }
            acessaListNew = attachedAcessaListNew;
            projeto.setAcessaList(acessaListNew);
            List<Entidademedida> attachedEntidademedidaListNew = new ArrayList<Entidademedida>();
            for (Entidademedida entidademedidaListNewEntidademedidaToAttach : entidademedidaListNew) {
                entidademedidaListNewEntidademedidaToAttach = em.getReference(entidademedidaListNewEntidademedidaToAttach.getClass(), entidademedidaListNewEntidademedidaToAttach.getId());
                attachedEntidademedidaListNew.add(entidademedidaListNewEntidademedidaToAttach);
            }
            entidademedidaListNew = attachedEntidademedidaListNew;
            projeto.setEntidademedidaList(entidademedidaListNew);
            List<Meioscomunicacao> attachedMeioscomunicacaoListNew = new ArrayList<Meioscomunicacao>();
            for (Meioscomunicacao meioscomunicacaoListNewMeioscomunicacaoToAttach : meioscomunicacaoListNew) {
                meioscomunicacaoListNewMeioscomunicacaoToAttach = em.getReference(meioscomunicacaoListNewMeioscomunicacaoToAttach.getClass(), meioscomunicacaoListNewMeioscomunicacaoToAttach.getId());
                attachedMeioscomunicacaoListNew.add(meioscomunicacaoListNewMeioscomunicacaoToAttach);
            }
            meioscomunicacaoListNew = attachedMeioscomunicacaoListNew;
            projeto.setMeioscomunicacaoList(meioscomunicacaoListNew);
            projeto = em.merge(projeto);
            for (Objetivodemedicao objetivodemedicaoListNewObjetivodemedicao : objetivodemedicaoListNew) {
                if (!objetivodemedicaoListOld.contains(objetivodemedicaoListNewObjetivodemedicao)) {
                    Projeto oldProjetoidOfObjetivodemedicaoListNewObjetivodemedicao = objetivodemedicaoListNewObjetivodemedicao.getProjetoid();
                    objetivodemedicaoListNewObjetivodemedicao.setProjetoid(projeto);
                    objetivodemedicaoListNewObjetivodemedicao = em.merge(objetivodemedicaoListNewObjetivodemedicao);
                    if (oldProjetoidOfObjetivodemedicaoListNewObjetivodemedicao != null && !oldProjetoidOfObjetivodemedicaoListNewObjetivodemedicao.equals(projeto)) {
                        oldProjetoidOfObjetivodemedicaoListNewObjetivodemedicao.getObjetivodemedicaoList().remove(objetivodemedicaoListNewObjetivodemedicao);
                        oldProjetoidOfObjetivodemedicaoListNewObjetivodemedicao = em.merge(oldProjetoidOfObjetivodemedicaoListNewObjetivodemedicao);
                    }
                }
            }
            for (Relatorios relatoriosListNewRelatorios : relatoriosListNew) {
                if (!relatoriosListOld.contains(relatoriosListNewRelatorios)) {
                    Projeto oldProjetoidOfRelatoriosListNewRelatorios = relatoriosListNewRelatorios.getProjetoid();
                    relatoriosListNewRelatorios.setProjetoid(projeto);
                    relatoriosListNewRelatorios = em.merge(relatoriosListNewRelatorios);
                    if (oldProjetoidOfRelatoriosListNewRelatorios != null && !oldProjetoidOfRelatoriosListNewRelatorios.equals(projeto)) {
                        oldProjetoidOfRelatoriosListNewRelatorios.getRelatoriosList().remove(relatoriosListNewRelatorios);
                        oldProjetoidOfRelatoriosListNewRelatorios = em.merge(oldProjetoidOfRelatoriosListNewRelatorios);
                    }
                }
            }
            for (Perfilinteressado perfilinteressadoListOldPerfilinteressado : perfilinteressadoListOld) {
                if (!perfilinteressadoListNew.contains(perfilinteressadoListOldPerfilinteressado)) {
                    perfilinteressadoListOldPerfilinteressado.setProjetoid(null);
                    perfilinteressadoListOldPerfilinteressado = em.merge(perfilinteressadoListOldPerfilinteressado);
                }
            }
            for (Perfilinteressado perfilinteressadoListNewPerfilinteressado : perfilinteressadoListNew) {
                if (!perfilinteressadoListOld.contains(perfilinteressadoListNewPerfilinteressado)) {
                    Projeto oldProjetoidOfPerfilinteressadoListNewPerfilinteressado = perfilinteressadoListNewPerfilinteressado.getProjetoid();
                    perfilinteressadoListNewPerfilinteressado.setProjetoid(projeto);
                    perfilinteressadoListNewPerfilinteressado = em.merge(perfilinteressadoListNewPerfilinteressado);
                    if (oldProjetoidOfPerfilinteressadoListNewPerfilinteressado != null && !oldProjetoidOfPerfilinteressadoListNewPerfilinteressado.equals(projeto)) {
                        oldProjetoidOfPerfilinteressadoListNewPerfilinteressado.getPerfilinteressadoList().remove(perfilinteressadoListNewPerfilinteressado);
                        oldProjetoidOfPerfilinteressadoListNewPerfilinteressado = em.merge(oldProjetoidOfPerfilinteressadoListNewPerfilinteressado);
                    }
                }
            }
            for (Registroprojeto registroprojetoListNewRegistroprojeto : registroprojetoListNew) {
                if (!registroprojetoListOld.contains(registroprojetoListNewRegistroprojeto)) {
                    Projeto oldProjetoidOfRegistroprojetoListNewRegistroprojeto = registroprojetoListNewRegistroprojeto.getProjetoid();
                    registroprojetoListNewRegistroprojeto.setProjetoid(projeto);
                    registroprojetoListNewRegistroprojeto = em.merge(registroprojetoListNewRegistroprojeto);
                    if (oldProjetoidOfRegistroprojetoListNewRegistroprojeto != null && !oldProjetoidOfRegistroprojetoListNewRegistroprojeto.equals(projeto)) {
                        oldProjetoidOfRegistroprojetoListNewRegistroprojeto.getRegistroprojetoList().remove(registroprojetoListNewRegistroprojeto);
                        oldProjetoidOfRegistroprojetoListNewRegistroprojeto = em.merge(oldProjetoidOfRegistroprojetoListNewRegistroprojeto);
                    }
                }
            }
            for (Acessa acessaListNewAcessa : acessaListNew) {
                if (!acessaListOld.contains(acessaListNewAcessa)) {
                    Projeto oldProjetoOfAcessaListNewAcessa = acessaListNewAcessa.getProjeto();
                    acessaListNewAcessa.setProjeto(projeto);
                    acessaListNewAcessa = em.merge(acessaListNewAcessa);
                    if (oldProjetoOfAcessaListNewAcessa != null && !oldProjetoOfAcessaListNewAcessa.equals(projeto)) {
                        oldProjetoOfAcessaListNewAcessa.getAcessaList().remove(acessaListNewAcessa);
                        oldProjetoOfAcessaListNewAcessa = em.merge(oldProjetoOfAcessaListNewAcessa);
                    }
                }
            }
            for (Entidademedida entidademedidaListOldEntidademedida : entidademedidaListOld) {
                if (!entidademedidaListNew.contains(entidademedidaListOldEntidademedida)) {
                    entidademedidaListOldEntidademedida.setProjetoid(null);
                    entidademedidaListOldEntidademedida = em.merge(entidademedidaListOldEntidademedida);
                }
            }
            for (Entidademedida entidademedidaListNewEntidademedida : entidademedidaListNew) {
                if (!entidademedidaListOld.contains(entidademedidaListNewEntidademedida)) {
                    Projeto oldProjetoidOfEntidademedidaListNewEntidademedida = entidademedidaListNewEntidademedida.getProjetoid();
                    entidademedidaListNewEntidademedida.setProjetoid(projeto);
                    entidademedidaListNewEntidademedida = em.merge(entidademedidaListNewEntidademedida);
                    if (oldProjetoidOfEntidademedidaListNewEntidademedida != null && !oldProjetoidOfEntidademedidaListNewEntidademedida.equals(projeto)) {
                        oldProjetoidOfEntidademedidaListNewEntidademedida.getEntidademedidaList().remove(entidademedidaListNewEntidademedida);
                        oldProjetoidOfEntidademedidaListNewEntidademedida = em.merge(oldProjetoidOfEntidademedidaListNewEntidademedida);
                    }
                }
            }
            for (Meioscomunicacao meioscomunicacaoListOldMeioscomunicacao : meioscomunicacaoListOld) {
                if (!meioscomunicacaoListNew.contains(meioscomunicacaoListOldMeioscomunicacao)) {
                    meioscomunicacaoListOldMeioscomunicacao.setProjetoid(null);
                    meioscomunicacaoListOldMeioscomunicacao = em.merge(meioscomunicacaoListOldMeioscomunicacao);
                }
            }
            for (Meioscomunicacao meioscomunicacaoListNewMeioscomunicacao : meioscomunicacaoListNew) {
                if (!meioscomunicacaoListOld.contains(meioscomunicacaoListNewMeioscomunicacao)) {
                    Projeto oldProjetoidOfMeioscomunicacaoListNewMeioscomunicacao = meioscomunicacaoListNewMeioscomunicacao.getProjetoid();
                    meioscomunicacaoListNewMeioscomunicacao.setProjetoid(projeto);
                    meioscomunicacaoListNewMeioscomunicacao = em.merge(meioscomunicacaoListNewMeioscomunicacao);
                    if (oldProjetoidOfMeioscomunicacaoListNewMeioscomunicacao != null && !oldProjetoidOfMeioscomunicacaoListNewMeioscomunicacao.equals(projeto)) {
                        oldProjetoidOfMeioscomunicacaoListNewMeioscomunicacao.getMeioscomunicacaoList().remove(meioscomunicacaoListNewMeioscomunicacao);
                        oldProjetoidOfMeioscomunicacaoListNewMeioscomunicacao = em.merge(oldProjetoidOfMeioscomunicacaoListNewMeioscomunicacao);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = projeto.getId();
                if (findProjeto(id) == null) {
                    throw new NonexistentEntityException("The projeto with id " + id + " no longer exists.");
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
            Projeto projeto;
            try {
                projeto = em.getReference(Projeto.class, id);
                projeto.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The projeto with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Objetivodemedicao> objetivodemedicaoListOrphanCheck = projeto.getObjetivodemedicaoList();
            for (Objetivodemedicao objetivodemedicaoListOrphanCheckObjetivodemedicao : objetivodemedicaoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Projeto (" + projeto + ") cannot be destroyed since the Objetivodemedicao " + objetivodemedicaoListOrphanCheckObjetivodemedicao + " in its objetivodemedicaoList field has a non-nullable projetoid field.");
            }
            List<Relatorios> relatoriosListOrphanCheck = projeto.getRelatoriosList();
            for (Relatorios relatoriosListOrphanCheckRelatorios : relatoriosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Projeto (" + projeto + ") cannot be destroyed since the Relatorios " + relatoriosListOrphanCheckRelatorios + " in its relatoriosList field has a non-nullable projetoid field.");
            }
            List<Registroprojeto> registroprojetoListOrphanCheck = projeto.getRegistroprojetoList();
            for (Registroprojeto registroprojetoListOrphanCheckRegistroprojeto : registroprojetoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Projeto (" + projeto + ") cannot be destroyed since the Registroprojeto " + registroprojetoListOrphanCheckRegistroprojeto + " in its registroprojetoList field has a non-nullable projetoid field.");
            }
            List<Acessa> acessaListOrphanCheck = projeto.getAcessaList();
            for (Acessa acessaListOrphanCheckAcessa : acessaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Projeto (" + projeto + ") cannot be destroyed since the Acessa " + acessaListOrphanCheckAcessa + " in its acessaList field has a non-nullable projeto field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Perfilinteressado> perfilinteressadoList = projeto.getPerfilinteressadoList();
            for (Perfilinteressado perfilinteressadoListPerfilinteressado : perfilinteressadoList) {
                perfilinteressadoListPerfilinteressado.setProjetoid(null);
                perfilinteressadoListPerfilinteressado = em.merge(perfilinteressadoListPerfilinteressado);
            }
            List<Entidademedida> entidademedidaList = projeto.getEntidademedidaList();
            for (Entidademedida entidademedidaListEntidademedida : entidademedidaList) {
                entidademedidaListEntidademedida.setProjetoid(null);
                entidademedidaListEntidademedida = em.merge(entidademedidaListEntidademedida);
            }
            List<Meioscomunicacao> meioscomunicacaoList = projeto.getMeioscomunicacaoList();
            for (Meioscomunicacao meioscomunicacaoListMeioscomunicacao : meioscomunicacaoList) {
                meioscomunicacaoListMeioscomunicacao.setProjetoid(null);
                meioscomunicacaoListMeioscomunicacao = em.merge(meioscomunicacaoListMeioscomunicacao);
            }
            em.remove(projeto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Projeto> findProjetoEntities() {
        return findProjetoEntities(true, -1, -1);
    }

    public List<Projeto> findProjetoEntities(int maxResults, int firstResult) {
        return findProjetoEntities(false, maxResults, firstResult);
    }

    private List<Projeto> findProjetoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Projeto.class));
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

    public Projeto findProjeto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Projeto.class, id);
        } finally {
            em.close();
        }
    }

    public int getProjetoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Projeto> rt = cq.from(Projeto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
