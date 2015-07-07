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
import model.Acessa;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;
import model.Usuario;

/**
 *
 * @author Paulo
 */
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(EntityManagerFactory emf)
    {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager()
    {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario)
    {
        if (usuario.getAcessaList() == null) {
            usuario.setAcessaList(new ArrayList<Acessa>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Acessa> attachedAcessaList = new ArrayList<Acessa>();
            for (Acessa acessaListAcessaToAttach : usuario.getAcessaList()) {
                acessaListAcessaToAttach = em.getReference(acessaListAcessaToAttach.getClass(), acessaListAcessaToAttach.getAcessaPK());
                attachedAcessaList.add(acessaListAcessaToAttach);
            }
            usuario.setAcessaList(attachedAcessaList);
            em.persist(usuario);
            for (Acessa acessaListAcessa : usuario.getAcessaList()) {
                Usuario oldUsuarioOfAcessaListAcessa = acessaListAcessa.getUsuario();
                acessaListAcessa.setUsuario(usuario);
                acessaListAcessa = em.merge(acessaListAcessa);
                if (oldUsuarioOfAcessaListAcessa != null) {
                    oldUsuarioOfAcessaListAcessa.getAcessaList().remove(acessaListAcessa);
                    oldUsuarioOfAcessaListAcessa = em.merge(oldUsuarioOfAcessaListAcessa);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws IllegalOrphanException, NonexistentEntityException, Exception
    {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getId());
            List<Acessa> acessaListOld = persistentUsuario.getAcessaList();
            List<Acessa> acessaListNew = usuario.getAcessaList();
            List<String> illegalOrphanMessages = null;
            for (Acessa acessaListOldAcessa : acessaListOld) {
                if (!acessaListNew.contains(acessaListOldAcessa)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Acessa " + acessaListOldAcessa + " since its usuario field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Acessa> attachedAcessaListNew = new ArrayList<Acessa>();
            for (Acessa acessaListNewAcessaToAttach : acessaListNew) {
                acessaListNewAcessaToAttach = em.getReference(acessaListNewAcessaToAttach.getClass(), acessaListNewAcessaToAttach.getAcessaPK());
                attachedAcessaListNew.add(acessaListNewAcessaToAttach);
            }
            acessaListNew = attachedAcessaListNew;
            usuario.setAcessaList(acessaListNew);
            usuario = em.merge(usuario);
            for (Acessa acessaListNewAcessa : acessaListNew) {
                if (!acessaListOld.contains(acessaListNewAcessa)) {
                    Usuario oldUsuarioOfAcessaListNewAcessa = acessaListNewAcessa.getUsuario();
                    acessaListNewAcessa.setUsuario(usuario);
                    acessaListNewAcessa = em.merge(acessaListNewAcessa);
                    if (oldUsuarioOfAcessaListNewAcessa != null && !oldUsuarioOfAcessaListNewAcessa.equals(usuario)) {
                        oldUsuarioOfAcessaListNewAcessa.getAcessaList().remove(acessaListNewAcessa);
                        oldUsuarioOfAcessaListNewAcessa = em.merge(oldUsuarioOfAcessaListNewAcessa);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = usuario.getId();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException
    {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Acessa> acessaListOrphanCheck = usuario.getAcessaList();
            for (Acessa acessaListOrphanCheckAcessa : acessaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Acessa " + acessaListOrphanCheckAcessa + " in its acessaList field has a non-nullable usuario field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities()
    {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult)
    {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult)
    {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
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

    public Usuario findUsuario(Integer id)
    {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount()
    {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
