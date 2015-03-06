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
import model.Medida;
import model.Registrocoleta;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;
import model.Coleta;

/**
 *
 * @author Dan
 */
public class ColetaJpaController implements Serializable {

    public ColetaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Coleta coleta) {
        if (coleta.getRegistrocoletaList() == null) {
            coleta.setRegistrocoletaList(new ArrayList<Registrocoleta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Medida medidaid = coleta.getMedidaid();
            if (medidaid != null) {
                medidaid = em.getReference(medidaid.getClass(), medidaid.getId());
                coleta.setMedidaid(medidaid);
            }
            List<Registrocoleta> attachedRegistrocoletaList = new ArrayList<Registrocoleta>();
            for (Registrocoleta registrocoletaListRegistrocoletaToAttach : coleta.getRegistrocoletaList()) {
                registrocoletaListRegistrocoletaToAttach = em.getReference(registrocoletaListRegistrocoletaToAttach.getClass(), registrocoletaListRegistrocoletaToAttach.getId());
                attachedRegistrocoletaList.add(registrocoletaListRegistrocoletaToAttach);
            }
            coleta.setRegistrocoletaList(attachedRegistrocoletaList);
            em.persist(coleta);
            if (medidaid != null) {
                medidaid.getColetaList().add(coleta);
                medidaid = em.merge(medidaid);
            }
            for (Registrocoleta registrocoletaListRegistrocoleta : coleta.getRegistrocoletaList()) {
                Coleta oldColetaidOfRegistrocoletaListRegistrocoleta = registrocoletaListRegistrocoleta.getColetaid();
                registrocoletaListRegistrocoleta.setColetaid(coleta);
                registrocoletaListRegistrocoleta = em.merge(registrocoletaListRegistrocoleta);
                if (oldColetaidOfRegistrocoletaListRegistrocoleta != null) {
                    oldColetaidOfRegistrocoletaListRegistrocoleta.getRegistrocoletaList().remove(registrocoletaListRegistrocoleta);
                    oldColetaidOfRegistrocoletaListRegistrocoleta = em.merge(oldColetaidOfRegistrocoletaListRegistrocoleta);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Coleta coleta) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Coleta persistentColeta = em.find(Coleta.class, coleta.getId());
            Medida medidaidOld = persistentColeta.getMedidaid();
            Medida medidaidNew = coleta.getMedidaid();
            List<Registrocoleta> registrocoletaListOld = persistentColeta.getRegistrocoletaList();
            List<Registrocoleta> registrocoletaListNew = coleta.getRegistrocoletaList();
            List<String> illegalOrphanMessages = null;
            for (Registrocoleta registrocoletaListOldRegistrocoleta : registrocoletaListOld) {
                if (!registrocoletaListNew.contains(registrocoletaListOldRegistrocoleta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Registrocoleta " + registrocoletaListOldRegistrocoleta + " since its coletaid field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (medidaidNew != null) {
                medidaidNew = em.getReference(medidaidNew.getClass(), medidaidNew.getId());
                coleta.setMedidaid(medidaidNew);
            }
            List<Registrocoleta> attachedRegistrocoletaListNew = new ArrayList<Registrocoleta>();
            for (Registrocoleta registrocoletaListNewRegistrocoletaToAttach : registrocoletaListNew) {
                registrocoletaListNewRegistrocoletaToAttach = em.getReference(registrocoletaListNewRegistrocoletaToAttach.getClass(), registrocoletaListNewRegistrocoletaToAttach.getId());
                attachedRegistrocoletaListNew.add(registrocoletaListNewRegistrocoletaToAttach);
            }
            registrocoletaListNew = attachedRegistrocoletaListNew;
            coleta.setRegistrocoletaList(registrocoletaListNew);
            coleta = em.merge(coleta);
            if (medidaidOld != null && !medidaidOld.equals(medidaidNew)) {
                medidaidOld.getColetaList().remove(coleta);
                medidaidOld = em.merge(medidaidOld);
            }
            if (medidaidNew != null && !medidaidNew.equals(medidaidOld)) {
                medidaidNew.getColetaList().add(coleta);
                medidaidNew = em.merge(medidaidNew);
            }
            for (Registrocoleta registrocoletaListNewRegistrocoleta : registrocoletaListNew) {
                if (!registrocoletaListOld.contains(registrocoletaListNewRegistrocoleta)) {
                    Coleta oldColetaidOfRegistrocoletaListNewRegistrocoleta = registrocoletaListNewRegistrocoleta.getColetaid();
                    registrocoletaListNewRegistrocoleta.setColetaid(coleta);
                    registrocoletaListNewRegistrocoleta = em.merge(registrocoletaListNewRegistrocoleta);
                    if (oldColetaidOfRegistrocoletaListNewRegistrocoleta != null && !oldColetaidOfRegistrocoletaListNewRegistrocoleta.equals(coleta)) {
                        oldColetaidOfRegistrocoletaListNewRegistrocoleta.getRegistrocoletaList().remove(registrocoletaListNewRegistrocoleta);
                        oldColetaidOfRegistrocoletaListNewRegistrocoleta = em.merge(oldColetaidOfRegistrocoletaListNewRegistrocoleta);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = coleta.getId();
                if (findColeta(id) == null) {
                    throw new NonexistentEntityException("The coleta with id " + id + " no longer exists.");
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
            Coleta coleta;
            try {
                coleta = em.getReference(Coleta.class, id);
                coleta.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The coleta with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Registrocoleta> registrocoletaListOrphanCheck = coleta.getRegistrocoletaList();
            for (Registrocoleta registrocoletaListOrphanCheckRegistrocoleta : registrocoletaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Coleta (" + coleta + ") cannot be destroyed since the Registrocoleta " + registrocoletaListOrphanCheckRegistrocoleta + " in its registrocoletaList field has a non-nullable coletaid field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Medida medidaid = coleta.getMedidaid();
            if (medidaid != null) {
                medidaid.getColetaList().remove(coleta);
                medidaid = em.merge(medidaid);
            }
            em.remove(coleta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Coleta> findColetaEntities() {
        return findColetaEntities(true, -1, -1);
    }

    public List<Coleta> findColetaEntities(int maxResults, int firstResult) {
        return findColetaEntities(false, maxResults, firstResult);
    }

    private List<Coleta> findColetaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Coleta.class));
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

    public Coleta findColeta(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Coleta.class, id);
        } finally {
            em.close();
        }
    }

    public int getColetaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Coleta> rt = cq.from(Coleta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<Coleta> findListaColetaByProjeto(int idDoProjeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
