/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.Funcionalidade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.Acessa;
import model.Perfil;

/**
 *
 * @author Spider
 */
public class PerfilJpaController implements Serializable {

    public PerfilJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Perfil perfil) {
        if (perfil.getFuncionalidadeList() == null) {
            perfil.setFuncionalidadeList(new ArrayList<Funcionalidade>());
        }
        if (perfil.getAcessaList() == null) {
            perfil.setAcessaList(new ArrayList<Acessa>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Funcionalidade> attachedFuncionalidadeList = new ArrayList<Funcionalidade>();
            for (Funcionalidade funcionalidadeListFuncionalidadeToAttach : perfil.getFuncionalidadeList()) {
                funcionalidadeListFuncionalidadeToAttach = em.getReference(funcionalidadeListFuncionalidadeToAttach.getClass(), funcionalidadeListFuncionalidadeToAttach.getId());
                attachedFuncionalidadeList.add(funcionalidadeListFuncionalidadeToAttach);
            }
            perfil.setFuncionalidadeList(attachedFuncionalidadeList);
            List<Acessa> attachedAcessaList = new ArrayList<Acessa>();
            for (Acessa acessaListAcessaToAttach : perfil.getAcessaList()) {
                acessaListAcessaToAttach = em.getReference(acessaListAcessaToAttach.getClass(), acessaListAcessaToAttach.getAcessaPK());
                attachedAcessaList.add(acessaListAcessaToAttach);
            }
            perfil.setAcessaList(attachedAcessaList);
            em.persist(perfil);
            for (Funcionalidade funcionalidadeListFuncionalidade : perfil.getFuncionalidadeList()) {
                funcionalidadeListFuncionalidade.getPerfilList().add(perfil);
                funcionalidadeListFuncionalidade = em.merge(funcionalidadeListFuncionalidade);
            }
            for (Acessa acessaListAcessa : perfil.getAcessaList()) {
                Perfil oldPerfilOfAcessaListAcessa = acessaListAcessa.getPerfil();
                acessaListAcessa.setPerfil(perfil);
                acessaListAcessa = em.merge(acessaListAcessa);
                if (oldPerfilOfAcessaListAcessa != null) {
                    oldPerfilOfAcessaListAcessa.getAcessaList().remove(acessaListAcessa);
                    oldPerfilOfAcessaListAcessa = em.merge(oldPerfilOfAcessaListAcessa);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Perfil perfil) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Perfil persistentPerfil = em.find(Perfil.class, perfil.getId());
            List<Funcionalidade> funcionalidadeListOld = persistentPerfil.getFuncionalidadeList();
            List<Funcionalidade> funcionalidadeListNew = perfil.getFuncionalidadeList();
            List<Acessa> acessaListOld = persistentPerfil.getAcessaList();
            List<Acessa> acessaListNew = perfil.getAcessaList();
            List<String> illegalOrphanMessages = null;
            for (Acessa acessaListOldAcessa : acessaListOld) {
                if (!acessaListNew.contains(acessaListOldAcessa)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Acessa " + acessaListOldAcessa + " since its perfil field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Funcionalidade> attachedFuncionalidadeListNew = new ArrayList<Funcionalidade>();
            for (Funcionalidade funcionalidadeListNewFuncionalidadeToAttach : funcionalidadeListNew) {
                funcionalidadeListNewFuncionalidadeToAttach = em.getReference(funcionalidadeListNewFuncionalidadeToAttach.getClass(), funcionalidadeListNewFuncionalidadeToAttach.getId());
                attachedFuncionalidadeListNew.add(funcionalidadeListNewFuncionalidadeToAttach);
            }
            funcionalidadeListNew = attachedFuncionalidadeListNew;
            perfil.setFuncionalidadeList(funcionalidadeListNew);
            List<Acessa> attachedAcessaListNew = new ArrayList<Acessa>();
            for (Acessa acessaListNewAcessaToAttach : acessaListNew) {
                acessaListNewAcessaToAttach = em.getReference(acessaListNewAcessaToAttach.getClass(), acessaListNewAcessaToAttach.getAcessaPK());
                attachedAcessaListNew.add(acessaListNewAcessaToAttach);
            }
            acessaListNew = attachedAcessaListNew;
            perfil.setAcessaList(acessaListNew);
            perfil = em.merge(perfil);
            for (Funcionalidade funcionalidadeListOldFuncionalidade : funcionalidadeListOld) {
                if (!funcionalidadeListNew.contains(funcionalidadeListOldFuncionalidade)) {
                    funcionalidadeListOldFuncionalidade.getPerfilList().remove(perfil);
                    funcionalidadeListOldFuncionalidade = em.merge(funcionalidadeListOldFuncionalidade);
                }
            }
            for (Funcionalidade funcionalidadeListNewFuncionalidade : funcionalidadeListNew) {
                if (!funcionalidadeListOld.contains(funcionalidadeListNewFuncionalidade)) {
                    funcionalidadeListNewFuncionalidade.getPerfilList().add(perfil);
                    funcionalidadeListNewFuncionalidade = em.merge(funcionalidadeListNewFuncionalidade);
                }
            }
            for (Acessa acessaListNewAcessa : acessaListNew) {
                if (!acessaListOld.contains(acessaListNewAcessa)) {
                    Perfil oldPerfilOfAcessaListNewAcessa = acessaListNewAcessa.getPerfil();
                    acessaListNewAcessa.setPerfil(perfil);
                    acessaListNewAcessa = em.merge(acessaListNewAcessa);
                    if (oldPerfilOfAcessaListNewAcessa != null && !oldPerfilOfAcessaListNewAcessa.equals(perfil)) {
                        oldPerfilOfAcessaListNewAcessa.getAcessaList().remove(acessaListNewAcessa);
                        oldPerfilOfAcessaListNewAcessa = em.merge(oldPerfilOfAcessaListNewAcessa);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = perfil.getId();
                if (findPerfil(id) == null) {
                    throw new NonexistentEntityException("The perfil with id " + id + " no longer exists.");
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
            Perfil perfil;
            try {
                perfil = em.getReference(Perfil.class, id);
                perfil.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The perfil with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Acessa> acessaListOrphanCheck = perfil.getAcessaList();
            for (Acessa acessaListOrphanCheckAcessa : acessaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Perfil (" + perfil + ") cannot be destroyed since the Acessa " + acessaListOrphanCheckAcessa + " in its acessaList field has a non-nullable perfil field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Funcionalidade> funcionalidadeList = perfil.getFuncionalidadeList();
            for (Funcionalidade funcionalidadeListFuncionalidade : funcionalidadeList) {
                funcionalidadeListFuncionalidade.getPerfilList().remove(perfil);
                funcionalidadeListFuncionalidade = em.merge(funcionalidadeListFuncionalidade);
            }
            em.remove(perfil);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Perfil> findPerfilEntities() {
        return findPerfilEntities(true, -1, -1);
    }

    public List<Perfil> findPerfilEntities(int maxResults, int firstResult) {
        return findPerfilEntities(false, maxResults, firstResult);
    }

    private List<Perfil> findPerfilEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Perfil.class));
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

    public Perfil findPerfil(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Perfil.class, id);
        } finally {
            em.close();
        }
    }

    public int getPerfilCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Perfil> rt = cq.from(Perfil.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
