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
import model.Registroprojeto;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;
import model.Objetivodemedicao;
import model.Acessa;
import model.Projeto;

/**
 *
 * @author BlenoVale
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
        if (projeto.getRegistroprojetoList() == null) {
            projeto.setRegistroprojetoList(new ArrayList<Registroprojeto>());
        }
        if (projeto.getObjetivodemedicaoList() == null) {
            projeto.setObjetivodemedicaoList(new ArrayList<Objetivodemedicao>());
        }
        if (projeto.getAcessaList() == null) {
            projeto.setAcessaList(new ArrayList<Acessa>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Registroprojeto> attachedRegistroprojetoList = new ArrayList<Registroprojeto>();
            for (Registroprojeto registroprojetoListRegistroprojetoToAttach : projeto.getRegistroprojetoList()) {
                registroprojetoListRegistroprojetoToAttach = em.getReference(registroprojetoListRegistroprojetoToAttach.getClass(), registroprojetoListRegistroprojetoToAttach.getId());
                attachedRegistroprojetoList.add(registroprojetoListRegistroprojetoToAttach);
            }
            projeto.setRegistroprojetoList(attachedRegistroprojetoList);
            List<Objetivodemedicao> attachedObjetivodemedicaoList = new ArrayList<Objetivodemedicao>();
            for (Objetivodemedicao objetivodemedicaoListObjetivodemedicaoToAttach : projeto.getObjetivodemedicaoList()) {
                objetivodemedicaoListObjetivodemedicaoToAttach = em.getReference(objetivodemedicaoListObjetivodemedicaoToAttach.getClass(), objetivodemedicaoListObjetivodemedicaoToAttach.getId());
                attachedObjetivodemedicaoList.add(objetivodemedicaoListObjetivodemedicaoToAttach);
            }
            projeto.setObjetivodemedicaoList(attachedObjetivodemedicaoList);
            List<Acessa> attachedAcessaList = new ArrayList<Acessa>();
            for (Acessa acessaListAcessaToAttach : projeto.getAcessaList()) {
                acessaListAcessaToAttach = em.getReference(acessaListAcessaToAttach.getClass(), acessaListAcessaToAttach.getAcessaPK());
                attachedAcessaList.add(acessaListAcessaToAttach);
            }
            projeto.setAcessaList(attachedAcessaList);
            em.persist(projeto);
            for (Registroprojeto registroprojetoListRegistroprojeto : projeto.getRegistroprojetoList()) {
                Projeto oldProjetoidOfRegistroprojetoListRegistroprojeto = registroprojetoListRegistroprojeto.getProjetoid();
                registroprojetoListRegistroprojeto.setProjetoid(projeto);
                registroprojetoListRegistroprojeto = em.merge(registroprojetoListRegistroprojeto);
                if (oldProjetoidOfRegistroprojetoListRegistroprojeto != null) {
                    oldProjetoidOfRegistroprojetoListRegistroprojeto.getRegistroprojetoList().remove(registroprojetoListRegistroprojeto);
                    oldProjetoidOfRegistroprojetoListRegistroprojeto = em.merge(oldProjetoidOfRegistroprojetoListRegistroprojeto);
                }
            }
            for (Objetivodemedicao objetivodemedicaoListObjetivodemedicao : projeto.getObjetivodemedicaoList()) {
                Projeto oldProjetoidOfObjetivodemedicaoListObjetivodemedicao = objetivodemedicaoListObjetivodemedicao.getProjetoid();
                objetivodemedicaoListObjetivodemedicao.setProjetoid(projeto);
                objetivodemedicaoListObjetivodemedicao = em.merge(objetivodemedicaoListObjetivodemedicao);
                if (oldProjetoidOfObjetivodemedicaoListObjetivodemedicao != null) {
                    oldProjetoidOfObjetivodemedicaoListObjetivodemedicao.getObjetivodemedicaoList().remove(objetivodemedicaoListObjetivodemedicao);
                    oldProjetoidOfObjetivodemedicaoListObjetivodemedicao = em.merge(oldProjetoidOfObjetivodemedicaoListObjetivodemedicao);
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
            List<Registroprojeto> registroprojetoListOld = persistentProjeto.getRegistroprojetoList();
            List<Registroprojeto> registroprojetoListNew = projeto.getRegistroprojetoList();
            List<Objetivodemedicao> objetivodemedicaoListOld = persistentProjeto.getObjetivodemedicaoList();
            List<Objetivodemedicao> objetivodemedicaoListNew = projeto.getObjetivodemedicaoList();
            List<Acessa> acessaListOld = persistentProjeto.getAcessaList();
            List<Acessa> acessaListNew = projeto.getAcessaList();
            List<String> illegalOrphanMessages = null;
            for (Registroprojeto registroprojetoListOldRegistroprojeto : registroprojetoListOld) {
                if (!registroprojetoListNew.contains(registroprojetoListOldRegistroprojeto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Registroprojeto " + registroprojetoListOldRegistroprojeto + " since its projetoid field is not nullable.");
                }
            }
            for (Objetivodemedicao objetivodemedicaoListOldObjetivodemedicao : objetivodemedicaoListOld) {
                if (!objetivodemedicaoListNew.contains(objetivodemedicaoListOldObjetivodemedicao)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Objetivodemedicao " + objetivodemedicaoListOldObjetivodemedicao + " since its projetoid field is not nullable.");
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
            List<Registroprojeto> attachedRegistroprojetoListNew = new ArrayList<Registroprojeto>();
            for (Registroprojeto registroprojetoListNewRegistroprojetoToAttach : registroprojetoListNew) {
                registroprojetoListNewRegistroprojetoToAttach = em.getReference(registroprojetoListNewRegistroprojetoToAttach.getClass(), registroprojetoListNewRegistroprojetoToAttach.getId());
                attachedRegistroprojetoListNew.add(registroprojetoListNewRegistroprojetoToAttach);
            }
            registroprojetoListNew = attachedRegistroprojetoListNew;
            projeto.setRegistroprojetoList(registroprojetoListNew);
            List<Objetivodemedicao> attachedObjetivodemedicaoListNew = new ArrayList<Objetivodemedicao>();
            for (Objetivodemedicao objetivodemedicaoListNewObjetivodemedicaoToAttach : objetivodemedicaoListNew) {
                objetivodemedicaoListNewObjetivodemedicaoToAttach = em.getReference(objetivodemedicaoListNewObjetivodemedicaoToAttach.getClass(), objetivodemedicaoListNewObjetivodemedicaoToAttach.getId());
                attachedObjetivodemedicaoListNew.add(objetivodemedicaoListNewObjetivodemedicaoToAttach);
            }
            objetivodemedicaoListNew = attachedObjetivodemedicaoListNew;
            projeto.setObjetivodemedicaoList(objetivodemedicaoListNew);
            List<Acessa> attachedAcessaListNew = new ArrayList<Acessa>();
            for (Acessa acessaListNewAcessaToAttach : acessaListNew) {
                acessaListNewAcessaToAttach = em.getReference(acessaListNewAcessaToAttach.getClass(), acessaListNewAcessaToAttach.getAcessaPK());
                attachedAcessaListNew.add(acessaListNewAcessaToAttach);
            }
            acessaListNew = attachedAcessaListNew;
            projeto.setAcessaList(acessaListNew);
            projeto = em.merge(projeto);
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
            List<Registroprojeto> registroprojetoListOrphanCheck = projeto.getRegistroprojetoList();
            for (Registroprojeto registroprojetoListOrphanCheckRegistroprojeto : registroprojetoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Projeto (" + projeto + ") cannot be destroyed since the Registroprojeto " + registroprojetoListOrphanCheckRegistroprojeto + " in its registroprojetoList field has a non-nullable projetoid field.");
            }
            List<Objetivodemedicao> objetivodemedicaoListOrphanCheck = projeto.getObjetivodemedicaoList();
            for (Objetivodemedicao objetivodemedicaoListOrphanCheckObjetivodemedicao : objetivodemedicaoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Projeto (" + projeto + ") cannot be destroyed since the Objetivodemedicao " + objetivodemedicaoListOrphanCheckObjetivodemedicao + " in its objetivodemedicaoList field has a non-nullable projetoid field.");
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
