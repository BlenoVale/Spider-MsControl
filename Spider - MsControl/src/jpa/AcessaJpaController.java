/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import jpa.exceptions.NonexistentEntityException;
import jpa.exceptions.PreexistingEntityException;
import model.Acessa;
import model.AcessaPK;
import model.Usuario;
import model.Projeto;
import model.Perfil;

/**
 *
 * @author BlenoVale
 */
public class AcessaJpaController implements Serializable {

    public AcessaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Acessa acessa) throws PreexistingEntityException, Exception {
        if (acessa.getAcessaPK() == null) {
            acessa.setAcessaPK(new AcessaPK());
        }
        acessa.getAcessaPK().setPerfilid(acessa.getPerfil().getId());
        acessa.getAcessaPK().setProjetoid(acessa.getProjeto().getId());
        acessa.getAcessaPK().setUsuarioid(acessa.getUsuario().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuario = acessa.getUsuario();
            if (usuario != null) {
                usuario = em.getReference(usuario.getClass(), usuario.getId());
                acessa.setUsuario(usuario);
            }
            Projeto projeto = acessa.getProjeto();
            if (projeto != null) {
                projeto = em.getReference(projeto.getClass(), projeto.getId());
                acessa.setProjeto(projeto);
            }
            Perfil perfil = acessa.getPerfil();
            if (perfil != null) {
                perfil = em.getReference(perfil.getClass(), perfil.getId());
                acessa.setPerfil(perfil);
            }
            em.persist(acessa);
            if (usuario != null) {
                usuario.getAcessaList().add(acessa);
                usuario = em.merge(usuario);
            }
            if (projeto != null) {
                projeto.getAcessaList().add(acessa);
                projeto = em.merge(projeto);
            }
            if (perfil != null) {
                perfil.getAcessaList().add(acessa);
                perfil = em.merge(perfil);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAcessa(acessa.getAcessaPK()) != null) {
                throw new PreexistingEntityException("Acessa " + acessa + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Acessa acessa) throws NonexistentEntityException, Exception {
        acessa.getAcessaPK().setPerfilid(acessa.getPerfil().getId());
        acessa.getAcessaPK().setProjetoid(acessa.getProjeto().getId());
        acessa.getAcessaPK().setUsuarioid(acessa.getUsuario().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Acessa persistentAcessa = em.find(Acessa.class, acessa.getAcessaPK());
            Usuario usuarioOld = persistentAcessa.getUsuario();
            Usuario usuarioNew = acessa.getUsuario();
            Projeto projetoOld = persistentAcessa.getProjeto();
            Projeto projetoNew = acessa.getProjeto();
            Perfil perfilOld = persistentAcessa.getPerfil();
            Perfil perfilNew = acessa.getPerfil();
            if (usuarioNew != null) {
                usuarioNew = em.getReference(usuarioNew.getClass(), usuarioNew.getId());
                acessa.setUsuario(usuarioNew);
            }
            if (projetoNew != null) {
                projetoNew = em.getReference(projetoNew.getClass(), projetoNew.getId());
                acessa.setProjeto(projetoNew);
            }
            if (perfilNew != null) {
                perfilNew = em.getReference(perfilNew.getClass(), perfilNew.getId());
                acessa.setPerfil(perfilNew);
            }
            acessa = em.merge(acessa);
            if (usuarioOld != null && !usuarioOld.equals(usuarioNew)) {
                usuarioOld.getAcessaList().remove(acessa);
                usuarioOld = em.merge(usuarioOld);
            }
            if (usuarioNew != null && !usuarioNew.equals(usuarioOld)) {
                usuarioNew.getAcessaList().add(acessa);
                usuarioNew = em.merge(usuarioNew);
            }
            if (projetoOld != null && !projetoOld.equals(projetoNew)) {
                projetoOld.getAcessaList().remove(acessa);
                projetoOld = em.merge(projetoOld);
            }
            if (projetoNew != null && !projetoNew.equals(projetoOld)) {
                projetoNew.getAcessaList().add(acessa);
                projetoNew = em.merge(projetoNew);
            }
            if (perfilOld != null && !perfilOld.equals(perfilNew)) {
                perfilOld.getAcessaList().remove(acessa);
                perfilOld = em.merge(perfilOld);
            }
            if (perfilNew != null && !perfilNew.equals(perfilOld)) {
                perfilNew.getAcessaList().add(acessa);
                perfilNew = em.merge(perfilNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                AcessaPK id = acessa.getAcessaPK();
                if (findAcessa(id) == null) {
                    throw new NonexistentEntityException("The acessa with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(AcessaPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Acessa acessa;
            try {
                acessa = em.getReference(Acessa.class, id);
                acessa.getAcessaPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The acessa with id " + id + " no longer exists.", enfe);
            }
            Usuario usuario = acessa.getUsuario();
            if (usuario != null) {
                usuario.getAcessaList().remove(acessa);
                usuario = em.merge(usuario);
            }
            Projeto projeto = acessa.getProjeto();
            if (projeto != null) {
                projeto.getAcessaList().remove(acessa);
                projeto = em.merge(projeto);
            }
            Perfil perfil = acessa.getPerfil();
            if (perfil != null) {
                perfil.getAcessaList().remove(acessa);
                perfil = em.merge(perfil);
            }
            em.remove(acessa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Acessa> findAcessaEntities() {
        return findAcessaEntities(true, -1, -1);
    }

    public List<Acessa> findAcessaEntities(int maxResults, int firstResult) {
        return findAcessaEntities(false, maxResults, firstResult);
    }

    private List<Acessa> findAcessaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Acessa.class));
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

    public Acessa findAcessa(AcessaPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Acessa.class, id);
        } finally {
            em.close();
        }
    }

    public int getAcessaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Acessa> rt = cq.from(Acessa.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
