/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.Medida;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.Objetivodemedicacao;
import model.Acessa;
import model.Projeto;

/**
 *
 * @author Dan
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
        if (projeto.getMedidaList() == null) {
            projeto.setMedidaList(new ArrayList<Medida>());
        }
        if (projeto.getObjetivodemedicacaoList() == null) {
            projeto.setObjetivodemedicacaoList(new ArrayList<Objetivodemedicacao>());
        }
        if (projeto.getAcessaList() == null) {
            projeto.setAcessaList(new ArrayList<Acessa>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Medida> attachedMedidaList = new ArrayList<Medida>();
            for (Medida medidaListMedidaToAttach : projeto.getMedidaList()) {
                medidaListMedidaToAttach = em.getReference(medidaListMedidaToAttach.getClass(), medidaListMedidaToAttach.getMedidaPK());
                attachedMedidaList.add(medidaListMedidaToAttach);
            }
            projeto.setMedidaList(attachedMedidaList);
            List<Objetivodemedicacao> attachedObjetivodemedicacaoList = new ArrayList<Objetivodemedicacao>();
            for (Objetivodemedicacao objetivodemedicacaoListObjetivodemedicacaoToAttach : projeto.getObjetivodemedicacaoList()) {
                objetivodemedicacaoListObjetivodemedicacaoToAttach = em.getReference(objetivodemedicacaoListObjetivodemedicacaoToAttach.getClass(), objetivodemedicacaoListObjetivodemedicacaoToAttach.getObjetivodemedicacaoPK());
                attachedObjetivodemedicacaoList.add(objetivodemedicacaoListObjetivodemedicacaoToAttach);
            }
            projeto.setObjetivodemedicacaoList(attachedObjetivodemedicacaoList);
            List<Acessa> attachedAcessaList = new ArrayList<Acessa>();
            for (Acessa acessaListAcessaToAttach : projeto.getAcessaList()) {
                acessaListAcessaToAttach = em.getReference(acessaListAcessaToAttach.getClass(), acessaListAcessaToAttach.getAcessaPK());
                attachedAcessaList.add(acessaListAcessaToAttach);
            }
            projeto.setAcessaList(attachedAcessaList);
            em.persist(projeto);
            for (Medida medidaListMedida : projeto.getMedidaList()) {
                Projeto oldProjetoOfMedidaListMedida = medidaListMedida.getProjeto();
                medidaListMedida.setProjeto(projeto);
                medidaListMedida = em.merge(medidaListMedida);
                if (oldProjetoOfMedidaListMedida != null) {
                    oldProjetoOfMedidaListMedida.getMedidaList().remove(medidaListMedida);
                    oldProjetoOfMedidaListMedida = em.merge(oldProjetoOfMedidaListMedida);
                }
            }
            for (Objetivodemedicacao objetivodemedicacaoListObjetivodemedicacao : projeto.getObjetivodemedicacaoList()) {
                Projeto oldProjetoOfObjetivodemedicacaoListObjetivodemedicacao = objetivodemedicacaoListObjetivodemedicacao.getProjeto();
                objetivodemedicacaoListObjetivodemedicacao.setProjeto(projeto);
                objetivodemedicacaoListObjetivodemedicacao = em.merge(objetivodemedicacaoListObjetivodemedicacao);
                if (oldProjetoOfObjetivodemedicacaoListObjetivodemedicacao != null) {
                    oldProjetoOfObjetivodemedicacaoListObjetivodemedicacao.getObjetivodemedicacaoList().remove(objetivodemedicacaoListObjetivodemedicacao);
                    oldProjetoOfObjetivodemedicacaoListObjetivodemedicacao = em.merge(oldProjetoOfObjetivodemedicacaoListObjetivodemedicacao);
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
            List<Medida> medidaListOld = persistentProjeto.getMedidaList();
            List<Medida> medidaListNew = projeto.getMedidaList();
            List<Objetivodemedicacao> objetivodemedicacaoListOld = persistentProjeto.getObjetivodemedicacaoList();
            List<Objetivodemedicacao> objetivodemedicacaoListNew = projeto.getObjetivodemedicacaoList();
            List<Acessa> acessaListOld = persistentProjeto.getAcessaList();
            List<Acessa> acessaListNew = projeto.getAcessaList();
            List<String> illegalOrphanMessages = null;
            for (Medida medidaListOldMedida : medidaListOld) {
                if (!medidaListNew.contains(medidaListOldMedida)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Medida " + medidaListOldMedida + " since its projeto field is not nullable.");
                }
            }
            for (Objetivodemedicacao objetivodemedicacaoListOldObjetivodemedicacao : objetivodemedicacaoListOld) {
                if (!objetivodemedicacaoListNew.contains(objetivodemedicacaoListOldObjetivodemedicacao)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Objetivodemedicacao " + objetivodemedicacaoListOldObjetivodemedicacao + " since its projeto field is not nullable.");
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
            List<Medida> attachedMedidaListNew = new ArrayList<Medida>();
            for (Medida medidaListNewMedidaToAttach : medidaListNew) {
                medidaListNewMedidaToAttach = em.getReference(medidaListNewMedidaToAttach.getClass(), medidaListNewMedidaToAttach.getMedidaPK());
                attachedMedidaListNew.add(medidaListNewMedidaToAttach);
            }
            medidaListNew = attachedMedidaListNew;
            projeto.setMedidaList(medidaListNew);
            List<Objetivodemedicacao> attachedObjetivodemedicacaoListNew = new ArrayList<Objetivodemedicacao>();
            for (Objetivodemedicacao objetivodemedicacaoListNewObjetivodemedicacaoToAttach : objetivodemedicacaoListNew) {
                objetivodemedicacaoListNewObjetivodemedicacaoToAttach = em.getReference(objetivodemedicacaoListNewObjetivodemedicacaoToAttach.getClass(), objetivodemedicacaoListNewObjetivodemedicacaoToAttach.getObjetivodemedicacaoPK());
                attachedObjetivodemedicacaoListNew.add(objetivodemedicacaoListNewObjetivodemedicacaoToAttach);
            }
            objetivodemedicacaoListNew = attachedObjetivodemedicacaoListNew;
            projeto.setObjetivodemedicacaoList(objetivodemedicacaoListNew);
            List<Acessa> attachedAcessaListNew = new ArrayList<Acessa>();
            for (Acessa acessaListNewAcessaToAttach : acessaListNew) {
                acessaListNewAcessaToAttach = em.getReference(acessaListNewAcessaToAttach.getClass(), acessaListNewAcessaToAttach.getAcessaPK());
                attachedAcessaListNew.add(acessaListNewAcessaToAttach);
            }
            acessaListNew = attachedAcessaListNew;
            projeto.setAcessaList(acessaListNew);
            projeto = em.merge(projeto);
            for (Medida medidaListNewMedida : medidaListNew) {
                if (!medidaListOld.contains(medidaListNewMedida)) {
                    Projeto oldProjetoOfMedidaListNewMedida = medidaListNewMedida.getProjeto();
                    medidaListNewMedida.setProjeto(projeto);
                    medidaListNewMedida = em.merge(medidaListNewMedida);
                    if (oldProjetoOfMedidaListNewMedida != null && !oldProjetoOfMedidaListNewMedida.equals(projeto)) {
                        oldProjetoOfMedidaListNewMedida.getMedidaList().remove(medidaListNewMedida);
                        oldProjetoOfMedidaListNewMedida = em.merge(oldProjetoOfMedidaListNewMedida);
                    }
                }
            }
            for (Objetivodemedicacao objetivodemedicacaoListNewObjetivodemedicacao : objetivodemedicacaoListNew) {
                if (!objetivodemedicacaoListOld.contains(objetivodemedicacaoListNewObjetivodemedicacao)) {
                    Projeto oldProjetoOfObjetivodemedicacaoListNewObjetivodemedicacao = objetivodemedicacaoListNewObjetivodemedicacao.getProjeto();
                    objetivodemedicacaoListNewObjetivodemedicacao.setProjeto(projeto);
                    objetivodemedicacaoListNewObjetivodemedicacao = em.merge(objetivodemedicacaoListNewObjetivodemedicacao);
                    if (oldProjetoOfObjetivodemedicacaoListNewObjetivodemedicacao != null && !oldProjetoOfObjetivodemedicacaoListNewObjetivodemedicacao.equals(projeto)) {
                        oldProjetoOfObjetivodemedicacaoListNewObjetivodemedicacao.getObjetivodemedicacaoList().remove(objetivodemedicacaoListNewObjetivodemedicacao);
                        oldProjetoOfObjetivodemedicacaoListNewObjetivodemedicacao = em.merge(oldProjetoOfObjetivodemedicacaoListNewObjetivodemedicacao);
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
            List<Medida> medidaListOrphanCheck = projeto.getMedidaList();
            for (Medida medidaListOrphanCheckMedida : medidaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Projeto (" + projeto + ") cannot be destroyed since the Medida " + medidaListOrphanCheckMedida + " in its medidaList field has a non-nullable projeto field.");
            }
            List<Objetivodemedicacao> objetivodemedicacaoListOrphanCheck = projeto.getObjetivodemedicacaoList();
            for (Objetivodemedicacao objetivodemedicacaoListOrphanCheckObjetivodemedicacao : objetivodemedicacaoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Projeto (" + projeto + ") cannot be destroyed since the Objetivodemedicacao " + objetivodemedicacaoListOrphanCheckObjetivodemedicacao + " in its objetivodemedicacaoList field has a non-nullable projeto field.");
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
