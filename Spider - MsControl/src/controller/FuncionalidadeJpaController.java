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
import model.Possui;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.Funcionalidade;

/**
 *
 * @author Dan
 */
public class FuncionalidadeJpaController implements Serializable {

    public FuncionalidadeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Funcionalidade funcionalidade) {
        if (funcionalidade.getPossuiList() == null) {
            funcionalidade.setPossuiList(new ArrayList<Possui>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Possui> attachedPossuiList = new ArrayList<Possui>();
            for (Possui possuiListPossuiToAttach : funcionalidade.getPossuiList()) {
                possuiListPossuiToAttach = em.getReference(possuiListPossuiToAttach.getClass(), possuiListPossuiToAttach.getPossuiPK());
                attachedPossuiList.add(possuiListPossuiToAttach);
            }
            funcionalidade.setPossuiList(attachedPossuiList);
            em.persist(funcionalidade);
            for (Possui possuiListPossui : funcionalidade.getPossuiList()) {
                Funcionalidade oldFuncionalidadeOfPossuiListPossui = possuiListPossui.getFuncionalidade();
                possuiListPossui.setFuncionalidade(funcionalidade);
                possuiListPossui = em.merge(possuiListPossui);
                if (oldFuncionalidadeOfPossuiListPossui != null) {
                    oldFuncionalidadeOfPossuiListPossui.getPossuiList().remove(possuiListPossui);
                    oldFuncionalidadeOfPossuiListPossui = em.merge(oldFuncionalidadeOfPossuiListPossui);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Funcionalidade funcionalidade) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Funcionalidade persistentFuncionalidade = em.find(Funcionalidade.class, funcionalidade.getId());
            List<Possui> possuiListOld = persistentFuncionalidade.getPossuiList();
            List<Possui> possuiListNew = funcionalidade.getPossuiList();
            List<String> illegalOrphanMessages = null;
            for (Possui possuiListOldPossui : possuiListOld) {
                if (!possuiListNew.contains(possuiListOldPossui)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Possui " + possuiListOldPossui + " since its funcionalidade field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Possui> attachedPossuiListNew = new ArrayList<Possui>();
            for (Possui possuiListNewPossuiToAttach : possuiListNew) {
                possuiListNewPossuiToAttach = em.getReference(possuiListNewPossuiToAttach.getClass(), possuiListNewPossuiToAttach.getPossuiPK());
                attachedPossuiListNew.add(possuiListNewPossuiToAttach);
            }
            possuiListNew = attachedPossuiListNew;
            funcionalidade.setPossuiList(possuiListNew);
            funcionalidade = em.merge(funcionalidade);
            for (Possui possuiListNewPossui : possuiListNew) {
                if (!possuiListOld.contains(possuiListNewPossui)) {
                    Funcionalidade oldFuncionalidadeOfPossuiListNewPossui = possuiListNewPossui.getFuncionalidade();
                    possuiListNewPossui.setFuncionalidade(funcionalidade);
                    possuiListNewPossui = em.merge(possuiListNewPossui);
                    if (oldFuncionalidadeOfPossuiListNewPossui != null && !oldFuncionalidadeOfPossuiListNewPossui.equals(funcionalidade)) {
                        oldFuncionalidadeOfPossuiListNewPossui.getPossuiList().remove(possuiListNewPossui);
                        oldFuncionalidadeOfPossuiListNewPossui = em.merge(oldFuncionalidadeOfPossuiListNewPossui);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = funcionalidade.getId();
                if (findFuncionalidade(id) == null) {
                    throw new NonexistentEntityException("The funcionalidade with id " + id + " no longer exists.");
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
            Funcionalidade funcionalidade;
            try {
                funcionalidade = em.getReference(Funcionalidade.class, id);
                funcionalidade.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The funcionalidade with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Possui> possuiListOrphanCheck = funcionalidade.getPossuiList();
            for (Possui possuiListOrphanCheckPossui : possuiListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Funcionalidade (" + funcionalidade + ") cannot be destroyed since the Possui " + possuiListOrphanCheckPossui + " in its possuiList field has a non-nullable funcionalidade field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(funcionalidade);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Funcionalidade> findFuncionalidadeEntities() {
        return findFuncionalidadeEntities(true, -1, -1);
    }

    public List<Funcionalidade> findFuncionalidadeEntities(int maxResults, int firstResult) {
        return findFuncionalidadeEntities(false, maxResults, firstResult);
    }

    private List<Funcionalidade> findFuncionalidadeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Funcionalidade.class));
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

    public Funcionalidade findFuncionalidade(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Funcionalidade.class, id);
        } finally {
            em.close();
        }
    }

    public int getFuncionalidadeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Funcionalidade> rt = cq.from(Funcionalidade.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
