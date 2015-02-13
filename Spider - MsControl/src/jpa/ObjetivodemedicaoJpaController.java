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
import model.Registroobjetivomedicao;
import model.MedicaoHasQuestao;
import model.Objetivodemedicao;

/**
 *
 * @author Dan
 */
public class ObjetivodemedicaoJpaController implements Serializable {

    public ObjetivodemedicaoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Objetivodemedicao objetivodemedicao) {
        if (objetivodemedicao.getObjetivodequestaoList() == null) {
            objetivodemedicao.setObjetivodequestaoList(new ArrayList<Objetivodequestao>());
        }
        if (objetivodemedicao.getRegistroobjetivomedicaoList() == null) {
            objetivodemedicao.setRegistroobjetivomedicaoList(new ArrayList<Registroobjetivomedicao>());
        }
        if (objetivodemedicao.getMedicaoHasQuestaoList() == null) {
            objetivodemedicao.setMedicaoHasQuestaoList(new ArrayList<MedicaoHasQuestao>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Projeto projetoid = objetivodemedicao.getProjetoid();
            if (projetoid != null) {
                projetoid = em.getReference(projetoid.getClass(), projetoid.getId());
                objetivodemedicao.setProjetoid(projetoid);
            }
            List<Objetivodequestao> attachedObjetivodequestaoList = new ArrayList<Objetivodequestao>();
            for (Objetivodequestao objetivodequestaoListObjetivodequestaoToAttach : objetivodemedicao.getObjetivodequestaoList()) {
                objetivodequestaoListObjetivodequestaoToAttach = em.getReference(objetivodequestaoListObjetivodequestaoToAttach.getClass(), objetivodequestaoListObjetivodequestaoToAttach.getId());
                attachedObjetivodequestaoList.add(objetivodequestaoListObjetivodequestaoToAttach);
            }
            objetivodemedicao.setObjetivodequestaoList(attachedObjetivodequestaoList);
            List<Registroobjetivomedicao> attachedRegistroobjetivomedicaoList = new ArrayList<Registroobjetivomedicao>();
            for (Registroobjetivomedicao registroobjetivomedicaoListRegistroobjetivomedicaoToAttach : objetivodemedicao.getRegistroobjetivomedicaoList()) {
                registroobjetivomedicaoListRegistroobjetivomedicaoToAttach = em.getReference(registroobjetivomedicaoListRegistroobjetivomedicaoToAttach.getClass(), registroobjetivomedicaoListRegistroobjetivomedicaoToAttach.getId());
                attachedRegistroobjetivomedicaoList.add(registroobjetivomedicaoListRegistroobjetivomedicaoToAttach);
            }
            objetivodemedicao.setRegistroobjetivomedicaoList(attachedRegistroobjetivomedicaoList);
            List<MedicaoHasQuestao> attachedMedicaoHasQuestaoList = new ArrayList<MedicaoHasQuestao>();
            for (MedicaoHasQuestao medicaoHasQuestaoListMedicaoHasQuestaoToAttach : objetivodemedicao.getMedicaoHasQuestaoList()) {
                medicaoHasQuestaoListMedicaoHasQuestaoToAttach = em.getReference(medicaoHasQuestaoListMedicaoHasQuestaoToAttach.getClass(), medicaoHasQuestaoListMedicaoHasQuestaoToAttach.getMedicaoHasQuestaoPK());
                attachedMedicaoHasQuestaoList.add(medicaoHasQuestaoListMedicaoHasQuestaoToAttach);
            }
            objetivodemedicao.setMedicaoHasQuestaoList(attachedMedicaoHasQuestaoList);
            em.persist(objetivodemedicao);
            if (projetoid != null) {
                projetoid.getObjetivodemedicaoList().add(objetivodemedicao);
                projetoid = em.merge(projetoid);
            }
            for (Objetivodequestao objetivodequestaoListObjetivodequestao : objetivodemedicao.getObjetivodequestaoList()) {
                Objetivodemedicao oldObjetivoDeMedicaoidOfObjetivodequestaoListObjetivodequestao = objetivodequestaoListObjetivodequestao.getObjetivoDeMedicaoid();
                objetivodequestaoListObjetivodequestao.setObjetivoDeMedicaoid(objetivodemedicao);
                objetivodequestaoListObjetivodequestao = em.merge(objetivodequestaoListObjetivodequestao);
                if (oldObjetivoDeMedicaoidOfObjetivodequestaoListObjetivodequestao != null) {
                    oldObjetivoDeMedicaoidOfObjetivodequestaoListObjetivodequestao.getObjetivodequestaoList().remove(objetivodequestaoListObjetivodequestao);
                    oldObjetivoDeMedicaoidOfObjetivodequestaoListObjetivodequestao = em.merge(oldObjetivoDeMedicaoidOfObjetivodequestaoListObjetivodequestao);
                }
            }
            for (Registroobjetivomedicao registroobjetivomedicaoListRegistroobjetivomedicao : objetivodemedicao.getRegistroobjetivomedicaoList()) {
                Objetivodemedicao oldObjetivoDeMedicaoid1OfRegistroobjetivomedicaoListRegistroobjetivomedicao = registroobjetivomedicaoListRegistroobjetivomedicao.getObjetivoDeMedicaoid1();
                registroobjetivomedicaoListRegistroobjetivomedicao.setObjetivoDeMedicaoid1(objetivodemedicao);
                registroobjetivomedicaoListRegistroobjetivomedicao = em.merge(registroobjetivomedicaoListRegistroobjetivomedicao);
                if (oldObjetivoDeMedicaoid1OfRegistroobjetivomedicaoListRegistroobjetivomedicao != null) {
                    oldObjetivoDeMedicaoid1OfRegistroobjetivomedicaoListRegistroobjetivomedicao.getRegistroobjetivomedicaoList().remove(registroobjetivomedicaoListRegistroobjetivomedicao);
                    oldObjetivoDeMedicaoid1OfRegistroobjetivomedicaoListRegistroobjetivomedicao = em.merge(oldObjetivoDeMedicaoid1OfRegistroobjetivomedicaoListRegistroobjetivomedicao);
                }
            }
            for (MedicaoHasQuestao medicaoHasQuestaoListMedicaoHasQuestao : objetivodemedicao.getMedicaoHasQuestaoList()) {
                Objetivodemedicao oldObjetivodemedicaoOfMedicaoHasQuestaoListMedicaoHasQuestao = medicaoHasQuestaoListMedicaoHasQuestao.getObjetivodemedicao();
                medicaoHasQuestaoListMedicaoHasQuestao.setObjetivodemedicao(objetivodemedicao);
                medicaoHasQuestaoListMedicaoHasQuestao = em.merge(medicaoHasQuestaoListMedicaoHasQuestao);
                if (oldObjetivodemedicaoOfMedicaoHasQuestaoListMedicaoHasQuestao != null) {
                    oldObjetivodemedicaoOfMedicaoHasQuestaoListMedicaoHasQuestao.getMedicaoHasQuestaoList().remove(medicaoHasQuestaoListMedicaoHasQuestao);
                    oldObjetivodemedicaoOfMedicaoHasQuestaoListMedicaoHasQuestao = em.merge(oldObjetivodemedicaoOfMedicaoHasQuestaoListMedicaoHasQuestao);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Objetivodemedicao objetivodemedicao) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Objetivodemedicao persistentObjetivodemedicao = em.find(Objetivodemedicao.class, objetivodemedicao.getId());
            Projeto projetoidOld = persistentObjetivodemedicao.getProjetoid();
            Projeto projetoidNew = objetivodemedicao.getProjetoid();
            List<Objetivodequestao> objetivodequestaoListOld = persistentObjetivodemedicao.getObjetivodequestaoList();
            List<Objetivodequestao> objetivodequestaoListNew = objetivodemedicao.getObjetivodequestaoList();
            List<Registroobjetivomedicao> registroobjetivomedicaoListOld = persistentObjetivodemedicao.getRegistroobjetivomedicaoList();
            List<Registroobjetivomedicao> registroobjetivomedicaoListNew = objetivodemedicao.getRegistroobjetivomedicaoList();
            List<MedicaoHasQuestao> medicaoHasQuestaoListOld = persistentObjetivodemedicao.getMedicaoHasQuestaoList();
            List<MedicaoHasQuestao> medicaoHasQuestaoListNew = objetivodemedicao.getMedicaoHasQuestaoList();
            List<String> illegalOrphanMessages = null;
            for (Objetivodequestao objetivodequestaoListOldObjetivodequestao : objetivodequestaoListOld) {
                if (!objetivodequestaoListNew.contains(objetivodequestaoListOldObjetivodequestao)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Objetivodequestao " + objetivodequestaoListOldObjetivodequestao + " since its objetivoDeMedicaoid field is not nullable.");
                }
            }
            for (Registroobjetivomedicao registroobjetivomedicaoListOldRegistroobjetivomedicao : registroobjetivomedicaoListOld) {
                if (!registroobjetivomedicaoListNew.contains(registroobjetivomedicaoListOldRegistroobjetivomedicao)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Registroobjetivomedicao " + registroobjetivomedicaoListOldRegistroobjetivomedicao + " since its objetivoDeMedicaoid1 field is not nullable.");
                }
            }
            for (MedicaoHasQuestao medicaoHasQuestaoListOldMedicaoHasQuestao : medicaoHasQuestaoListOld) {
                if (!medicaoHasQuestaoListNew.contains(medicaoHasQuestaoListOldMedicaoHasQuestao)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain MedicaoHasQuestao " + medicaoHasQuestaoListOldMedicaoHasQuestao + " since its objetivodemedicao field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (projetoidNew != null) {
                projetoidNew = em.getReference(projetoidNew.getClass(), projetoidNew.getId());
                objetivodemedicao.setProjetoid(projetoidNew);
            }
            List<Objetivodequestao> attachedObjetivodequestaoListNew = new ArrayList<Objetivodequestao>();
            for (Objetivodequestao objetivodequestaoListNewObjetivodequestaoToAttach : objetivodequestaoListNew) {
                objetivodequestaoListNewObjetivodequestaoToAttach = em.getReference(objetivodequestaoListNewObjetivodequestaoToAttach.getClass(), objetivodequestaoListNewObjetivodequestaoToAttach.getId());
                attachedObjetivodequestaoListNew.add(objetivodequestaoListNewObjetivodequestaoToAttach);
            }
            objetivodequestaoListNew = attachedObjetivodequestaoListNew;
            objetivodemedicao.setObjetivodequestaoList(objetivodequestaoListNew);
            List<Registroobjetivomedicao> attachedRegistroobjetivomedicaoListNew = new ArrayList<Registroobjetivomedicao>();
            for (Registroobjetivomedicao registroobjetivomedicaoListNewRegistroobjetivomedicaoToAttach : registroobjetivomedicaoListNew) {
                registroobjetivomedicaoListNewRegistroobjetivomedicaoToAttach = em.getReference(registroobjetivomedicaoListNewRegistroobjetivomedicaoToAttach.getClass(), registroobjetivomedicaoListNewRegistroobjetivomedicaoToAttach.getId());
                attachedRegistroobjetivomedicaoListNew.add(registroobjetivomedicaoListNewRegistroobjetivomedicaoToAttach);
            }
            registroobjetivomedicaoListNew = attachedRegistroobjetivomedicaoListNew;
            objetivodemedicao.setRegistroobjetivomedicaoList(registroobjetivomedicaoListNew);
            List<MedicaoHasQuestao> attachedMedicaoHasQuestaoListNew = new ArrayList<MedicaoHasQuestao>();
            for (MedicaoHasQuestao medicaoHasQuestaoListNewMedicaoHasQuestaoToAttach : medicaoHasQuestaoListNew) {
                medicaoHasQuestaoListNewMedicaoHasQuestaoToAttach = em.getReference(medicaoHasQuestaoListNewMedicaoHasQuestaoToAttach.getClass(), medicaoHasQuestaoListNewMedicaoHasQuestaoToAttach.getMedicaoHasQuestaoPK());
                attachedMedicaoHasQuestaoListNew.add(medicaoHasQuestaoListNewMedicaoHasQuestaoToAttach);
            }
            medicaoHasQuestaoListNew = attachedMedicaoHasQuestaoListNew;
            objetivodemedicao.setMedicaoHasQuestaoList(medicaoHasQuestaoListNew);
            objetivodemedicao = em.merge(objetivodemedicao);
            if (projetoidOld != null && !projetoidOld.equals(projetoidNew)) {
                projetoidOld.getObjetivodemedicaoList().remove(objetivodemedicao);
                projetoidOld = em.merge(projetoidOld);
            }
            if (projetoidNew != null && !projetoidNew.equals(projetoidOld)) {
                projetoidNew.getObjetivodemedicaoList().add(objetivodemedicao);
                projetoidNew = em.merge(projetoidNew);
            }
            for (Objetivodequestao objetivodequestaoListNewObjetivodequestao : objetivodequestaoListNew) {
                if (!objetivodequestaoListOld.contains(objetivodequestaoListNewObjetivodequestao)) {
                    Objetivodemedicao oldObjetivoDeMedicaoidOfObjetivodequestaoListNewObjetivodequestao = objetivodequestaoListNewObjetivodequestao.getObjetivoDeMedicaoid();
                    objetivodequestaoListNewObjetivodequestao.setObjetivoDeMedicaoid(objetivodemedicao);
                    objetivodequestaoListNewObjetivodequestao = em.merge(objetivodequestaoListNewObjetivodequestao);
                    if (oldObjetivoDeMedicaoidOfObjetivodequestaoListNewObjetivodequestao != null && !oldObjetivoDeMedicaoidOfObjetivodequestaoListNewObjetivodequestao.equals(objetivodemedicao)) {
                        oldObjetivoDeMedicaoidOfObjetivodequestaoListNewObjetivodequestao.getObjetivodequestaoList().remove(objetivodequestaoListNewObjetivodequestao);
                        oldObjetivoDeMedicaoidOfObjetivodequestaoListNewObjetivodequestao = em.merge(oldObjetivoDeMedicaoidOfObjetivodequestaoListNewObjetivodequestao);
                    }
                }
            }
            for (Registroobjetivomedicao registroobjetivomedicaoListNewRegistroobjetivomedicao : registroobjetivomedicaoListNew) {
                if (!registroobjetivomedicaoListOld.contains(registroobjetivomedicaoListNewRegistroobjetivomedicao)) {
                    Objetivodemedicao oldObjetivoDeMedicaoid1OfRegistroobjetivomedicaoListNewRegistroobjetivomedicao = registroobjetivomedicaoListNewRegistroobjetivomedicao.getObjetivoDeMedicaoid1();
                    registroobjetivomedicaoListNewRegistroobjetivomedicao.setObjetivoDeMedicaoid1(objetivodemedicao);
                    registroobjetivomedicaoListNewRegistroobjetivomedicao = em.merge(registroobjetivomedicaoListNewRegistroobjetivomedicao);
                    if (oldObjetivoDeMedicaoid1OfRegistroobjetivomedicaoListNewRegistroobjetivomedicao != null && !oldObjetivoDeMedicaoid1OfRegistroobjetivomedicaoListNewRegistroobjetivomedicao.equals(objetivodemedicao)) {
                        oldObjetivoDeMedicaoid1OfRegistroobjetivomedicaoListNewRegistroobjetivomedicao.getRegistroobjetivomedicaoList().remove(registroobjetivomedicaoListNewRegistroobjetivomedicao);
                        oldObjetivoDeMedicaoid1OfRegistroobjetivomedicaoListNewRegistroobjetivomedicao = em.merge(oldObjetivoDeMedicaoid1OfRegistroobjetivomedicaoListNewRegistroobjetivomedicao);
                    }
                }
            }
            for (MedicaoHasQuestao medicaoHasQuestaoListNewMedicaoHasQuestao : medicaoHasQuestaoListNew) {
                if (!medicaoHasQuestaoListOld.contains(medicaoHasQuestaoListNewMedicaoHasQuestao)) {
                    Objetivodemedicao oldObjetivodemedicaoOfMedicaoHasQuestaoListNewMedicaoHasQuestao = medicaoHasQuestaoListNewMedicaoHasQuestao.getObjetivodemedicao();
                    medicaoHasQuestaoListNewMedicaoHasQuestao.setObjetivodemedicao(objetivodemedicao);
                    medicaoHasQuestaoListNewMedicaoHasQuestao = em.merge(medicaoHasQuestaoListNewMedicaoHasQuestao);
                    if (oldObjetivodemedicaoOfMedicaoHasQuestaoListNewMedicaoHasQuestao != null && !oldObjetivodemedicaoOfMedicaoHasQuestaoListNewMedicaoHasQuestao.equals(objetivodemedicao)) {
                        oldObjetivodemedicaoOfMedicaoHasQuestaoListNewMedicaoHasQuestao.getMedicaoHasQuestaoList().remove(medicaoHasQuestaoListNewMedicaoHasQuestao);
                        oldObjetivodemedicaoOfMedicaoHasQuestaoListNewMedicaoHasQuestao = em.merge(oldObjetivodemedicaoOfMedicaoHasQuestaoListNewMedicaoHasQuestao);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = objetivodemedicao.getId();
                if (findObjetivodemedicao(id) == null) {
                    throw new NonexistentEntityException("The objetivodemedicao with id " + id + " no longer exists.");
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
            Objetivodemedicao objetivodemedicao;
            try {
                objetivodemedicao = em.getReference(Objetivodemedicao.class, id);
                objetivodemedicao.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The objetivodemedicao with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Objetivodequestao> objetivodequestaoListOrphanCheck = objetivodemedicao.getObjetivodequestaoList();
            for (Objetivodequestao objetivodequestaoListOrphanCheckObjetivodequestao : objetivodequestaoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Objetivodemedicao (" + objetivodemedicao + ") cannot be destroyed since the Objetivodequestao " + objetivodequestaoListOrphanCheckObjetivodequestao + " in its objetivodequestaoList field has a non-nullable objetivoDeMedicaoid field.");
            }
            List<Registroobjetivomedicao> registroobjetivomedicaoListOrphanCheck = objetivodemedicao.getRegistroobjetivomedicaoList();
            for (Registroobjetivomedicao registroobjetivomedicaoListOrphanCheckRegistroobjetivomedicao : registroobjetivomedicaoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Objetivodemedicao (" + objetivodemedicao + ") cannot be destroyed since the Registroobjetivomedicao " + registroobjetivomedicaoListOrphanCheckRegistroobjetivomedicao + " in its registroobjetivomedicaoList field has a non-nullable objetivoDeMedicaoid1 field.");
            }
            List<MedicaoHasQuestao> medicaoHasQuestaoListOrphanCheck = objetivodemedicao.getMedicaoHasQuestaoList();
            for (MedicaoHasQuestao medicaoHasQuestaoListOrphanCheckMedicaoHasQuestao : medicaoHasQuestaoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Objetivodemedicao (" + objetivodemedicao + ") cannot be destroyed since the MedicaoHasQuestao " + medicaoHasQuestaoListOrphanCheckMedicaoHasQuestao + " in its medicaoHasQuestaoList field has a non-nullable objetivodemedicao field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Projeto projetoid = objetivodemedicao.getProjetoid();
            if (projetoid != null) {
                projetoid.getObjetivodemedicaoList().remove(objetivodemedicao);
                projetoid = em.merge(projetoid);
            }
            em.remove(objetivodemedicao);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Objetivodemedicao> findObjetivodemedicaoEntities() {
        return findObjetivodemedicaoEntities(true, -1, -1);
    }

    public List<Objetivodemedicao> findObjetivodemedicaoEntities(int maxResults, int firstResult) {
        return findObjetivodemedicaoEntities(false, maxResults, firstResult);
    }

    private List<Objetivodemedicao> findObjetivodemedicaoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Objetivodemedicao.class));
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

    public Objetivodemedicao findObjetivodemedicao(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Objetivodemedicao.class, id);
        } finally {
            em.close();
        }
    }

    public int getObjetivodemedicaoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Objetivodemedicao> rt = cq.from(Objetivodemedicao.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
