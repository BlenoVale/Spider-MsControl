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
import model.Definicao;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;
import jpa.exceptions.PreexistingEntityException;
import model.Analise;
import model.Registromedida;
import model.Coleta;
import model.Medida;
import model.MedidaPK;

/**
 *
 * @author Dan
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
        if (medida.getDefinicaoList() == null) {
            medida.setDefinicaoList(new ArrayList<Definicao>());
        }
        if (medida.getAnaliseList() == null) {
            medida.setAnaliseList(new ArrayList<Analise>());
        }
        if (medida.getRegistromedidaList() == null) {
            medida.setRegistromedidaList(new ArrayList<Registromedida>());
        }
        if (medida.getColetaList() == null) {
            medida.setColetaList(new ArrayList<Coleta>());
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
            List<Definicao> attachedDefinicaoList = new ArrayList<Definicao>();
            for (Definicao definicaoListDefinicaoToAttach : medida.getDefinicaoList()) {
                definicaoListDefinicaoToAttach = em.getReference(definicaoListDefinicaoToAttach.getClass(), definicaoListDefinicaoToAttach.getDefinicaoPK());
                attachedDefinicaoList.add(definicaoListDefinicaoToAttach);
            }
            medida.setDefinicaoList(attachedDefinicaoList);
            List<Analise> attachedAnaliseList = new ArrayList<Analise>();
            for (Analise analiseListAnaliseToAttach : medida.getAnaliseList()) {
                analiseListAnaliseToAttach = em.getReference(analiseListAnaliseToAttach.getClass(), analiseListAnaliseToAttach.getAnalisePK());
                attachedAnaliseList.add(analiseListAnaliseToAttach);
            }
            medida.setAnaliseList(attachedAnaliseList);
            List<Registromedida> attachedRegistromedidaList = new ArrayList<Registromedida>();
            for (Registromedida registromedidaListRegistromedidaToAttach : medida.getRegistromedidaList()) {
                registromedidaListRegistromedidaToAttach = em.getReference(registromedidaListRegistromedidaToAttach.getClass(), registromedidaListRegistromedidaToAttach.getRegistromedidaPK());
                attachedRegistromedidaList.add(registromedidaListRegistromedidaToAttach);
            }
            medida.setRegistromedidaList(attachedRegistromedidaList);
            List<Coleta> attachedColetaList = new ArrayList<Coleta>();
            for (Coleta coletaListColetaToAttach : medida.getColetaList()) {
                coletaListColetaToAttach = em.getReference(coletaListColetaToAttach.getClass(), coletaListColetaToAttach.getColetaPK());
                attachedColetaList.add(coletaListColetaToAttach);
            }
            medida.setColetaList(attachedColetaList);
            em.persist(medida);
            if (projeto != null) {
                projeto.getMedidaList().add(medida);
                projeto = em.merge(projeto);
            }
            for (Definicao definicaoListDefinicao : medida.getDefinicaoList()) {
                Medida oldMedidaOfDefinicaoListDefinicao = definicaoListDefinicao.getMedida();
                definicaoListDefinicao.setMedida(medida);
                definicaoListDefinicao = em.merge(definicaoListDefinicao);
                if (oldMedidaOfDefinicaoListDefinicao != null) {
                    oldMedidaOfDefinicaoListDefinicao.getDefinicaoList().remove(definicaoListDefinicao);
                    oldMedidaOfDefinicaoListDefinicao = em.merge(oldMedidaOfDefinicaoListDefinicao);
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
            for (Coleta coletaListColeta : medida.getColetaList()) {
                Medida oldMedidaOfColetaListColeta = coletaListColeta.getMedida();
                coletaListColeta.setMedida(medida);
                coletaListColeta = em.merge(coletaListColeta);
                if (oldMedidaOfColetaListColeta != null) {
                    oldMedidaOfColetaListColeta.getColetaList().remove(coletaListColeta);
                    oldMedidaOfColetaListColeta = em.merge(oldMedidaOfColetaListColeta);
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
            List<Definicao> definicaoListOld = persistentMedida.getDefinicaoList();
            List<Definicao> definicaoListNew = medida.getDefinicaoList();
            List<Analise> analiseListOld = persistentMedida.getAnaliseList();
            List<Analise> analiseListNew = medida.getAnaliseList();
            List<Registromedida> registromedidaListOld = persistentMedida.getRegistromedidaList();
            List<Registromedida> registromedidaListNew = medida.getRegistromedidaList();
            List<Coleta> coletaListOld = persistentMedida.getColetaList();
            List<Coleta> coletaListNew = medida.getColetaList();
            List<String> illegalOrphanMessages = null;
            for (Definicao definicaoListOldDefinicao : definicaoListOld) {
                if (!definicaoListNew.contains(definicaoListOldDefinicao)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Definicao " + definicaoListOldDefinicao + " since its medida field is not nullable.");
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
            for (Coleta coletaListOldColeta : coletaListOld) {
                if (!coletaListNew.contains(coletaListOldColeta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Coleta " + coletaListOldColeta + " since its medida field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (projetoNew != null) {
                projetoNew = em.getReference(projetoNew.getClass(), projetoNew.getId());
                medida.setProjeto(projetoNew);
            }
            List<Definicao> attachedDefinicaoListNew = new ArrayList<Definicao>();
            for (Definicao definicaoListNewDefinicaoToAttach : definicaoListNew) {
                definicaoListNewDefinicaoToAttach = em.getReference(definicaoListNewDefinicaoToAttach.getClass(), definicaoListNewDefinicaoToAttach.getDefinicaoPK());
                attachedDefinicaoListNew.add(definicaoListNewDefinicaoToAttach);
            }
            definicaoListNew = attachedDefinicaoListNew;
            medida.setDefinicaoList(definicaoListNew);
            List<Analise> attachedAnaliseListNew = new ArrayList<Analise>();
            for (Analise analiseListNewAnaliseToAttach : analiseListNew) {
                analiseListNewAnaliseToAttach = em.getReference(analiseListNewAnaliseToAttach.getClass(), analiseListNewAnaliseToAttach.getAnalisePK());
                attachedAnaliseListNew.add(analiseListNewAnaliseToAttach);
            }
            analiseListNew = attachedAnaliseListNew;
            medida.setAnaliseList(analiseListNew);
            List<Registromedida> attachedRegistromedidaListNew = new ArrayList<Registromedida>();
            for (Registromedida registromedidaListNewRegistromedidaToAttach : registromedidaListNew) {
                registromedidaListNewRegistromedidaToAttach = em.getReference(registromedidaListNewRegistromedidaToAttach.getClass(), registromedidaListNewRegistromedidaToAttach.getRegistromedidaPK());
                attachedRegistromedidaListNew.add(registromedidaListNewRegistromedidaToAttach);
            }
            registromedidaListNew = attachedRegistromedidaListNew;
            medida.setRegistromedidaList(registromedidaListNew);
            List<Coleta> attachedColetaListNew = new ArrayList<Coleta>();
            for (Coleta coletaListNewColetaToAttach : coletaListNew) {
                coletaListNewColetaToAttach = em.getReference(coletaListNewColetaToAttach.getClass(), coletaListNewColetaToAttach.getColetaPK());
                attachedColetaListNew.add(coletaListNewColetaToAttach);
            }
            coletaListNew = attachedColetaListNew;
            medida.setColetaList(coletaListNew);
            medida = em.merge(medida);
            if (projetoOld != null && !projetoOld.equals(projetoNew)) {
                projetoOld.getMedidaList().remove(medida);
                projetoOld = em.merge(projetoOld);
            }
            if (projetoNew != null && !projetoNew.equals(projetoOld)) {
                projetoNew.getMedidaList().add(medida);
                projetoNew = em.merge(projetoNew);
            }
            for (Definicao definicaoListNewDefinicao : definicaoListNew) {
                if (!definicaoListOld.contains(definicaoListNewDefinicao)) {
                    Medida oldMedidaOfDefinicaoListNewDefinicao = definicaoListNewDefinicao.getMedida();
                    definicaoListNewDefinicao.setMedida(medida);
                    definicaoListNewDefinicao = em.merge(definicaoListNewDefinicao);
                    if (oldMedidaOfDefinicaoListNewDefinicao != null && !oldMedidaOfDefinicaoListNewDefinicao.equals(medida)) {
                        oldMedidaOfDefinicaoListNewDefinicao.getDefinicaoList().remove(definicaoListNewDefinicao);
                        oldMedidaOfDefinicaoListNewDefinicao = em.merge(oldMedidaOfDefinicaoListNewDefinicao);
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
            List<Definicao> definicaoListOrphanCheck = medida.getDefinicaoList();
            for (Definicao definicaoListOrphanCheckDefinicao : definicaoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Medida (" + medida + ") cannot be destroyed since the Definicao " + definicaoListOrphanCheckDefinicao + " in its definicaoList field has a non-nullable medida field.");
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
            List<Coleta> coletaListOrphanCheck = medida.getColetaList();
            for (Coleta coletaListOrphanCheckColeta : coletaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Medida (" + medida + ") cannot be destroyed since the Coleta " + coletaListOrphanCheckColeta + " in its coletaList field has a non-nullable medida field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Projeto projeto = medida.getProjeto();
            if (projeto != null) {
                projeto.getMedidaList().remove(medida);
                projeto = em.merge(projeto);
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
