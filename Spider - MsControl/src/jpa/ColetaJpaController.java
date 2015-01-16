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
import jpa.exceptions.PreexistingEntityException;
import model.Coleta;
import model.ColetaPK;
import model.Procedimentodeanalise;
import model.Procedimentodecoleta;

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

    public void create(Coleta coleta) throws PreexistingEntityException, Exception {
        if (coleta.getColetaPK() == null) {
            coleta.setColetaPK(new ColetaPK());
        }
        if (coleta.getRegistrocoletaList() == null) {
            coleta.setRegistrocoletaList(new ArrayList<Registrocoleta>());
        }
        if (coleta.getProcedimentodeanaliseList() == null) {
            coleta.setProcedimentodeanaliseList(new ArrayList<Procedimentodeanalise>());
        }
        if (coleta.getProcedimentodecoletaList() == null) {
            coleta.setProcedimentodecoletaList(new ArrayList<Procedimentodecoleta>());
        }
        coleta.getColetaPK().setMedidaid(coleta.getMedida().getMedidaPK().getId());
        coleta.getColetaPK().setMedidaProjetoid(coleta.getMedida().getMedidaPK().getProjetoid());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Medida medida = coleta.getMedida();
            if (medida != null) {
                medida = em.getReference(medida.getClass(), medida.getMedidaPK());
                coleta.setMedida(medida);
            }
            List<Registrocoleta> attachedRegistrocoletaList = new ArrayList<Registrocoleta>();
            for (Registrocoleta registrocoletaListRegistrocoletaToAttach : coleta.getRegistrocoletaList()) {
                registrocoletaListRegistrocoletaToAttach = em.getReference(registrocoletaListRegistrocoletaToAttach.getClass(), registrocoletaListRegistrocoletaToAttach.getRegistrocoletaPK());
                attachedRegistrocoletaList.add(registrocoletaListRegistrocoletaToAttach);
            }
            coleta.setRegistrocoletaList(attachedRegistrocoletaList);
            List<Procedimentodeanalise> attachedProcedimentodeanaliseList = new ArrayList<Procedimentodeanalise>();
            for (Procedimentodeanalise procedimentodeanaliseListProcedimentodeanaliseToAttach : coleta.getProcedimentodeanaliseList()) {
                procedimentodeanaliseListProcedimentodeanaliseToAttach = em.getReference(procedimentodeanaliseListProcedimentodeanaliseToAttach.getClass(), procedimentodeanaliseListProcedimentodeanaliseToAttach.getId());
                attachedProcedimentodeanaliseList.add(procedimentodeanaliseListProcedimentodeanaliseToAttach);
            }
            coleta.setProcedimentodeanaliseList(attachedProcedimentodeanaliseList);
            List<Procedimentodecoleta> attachedProcedimentodecoletaList = new ArrayList<Procedimentodecoleta>();
            for (Procedimentodecoleta procedimentodecoletaListProcedimentodecoletaToAttach : coleta.getProcedimentodecoletaList()) {
                procedimentodecoletaListProcedimentodecoletaToAttach = em.getReference(procedimentodecoletaListProcedimentodecoletaToAttach.getClass(), procedimentodecoletaListProcedimentodecoletaToAttach.getId());
                attachedProcedimentodecoletaList.add(procedimentodecoletaListProcedimentodecoletaToAttach);
            }
            coleta.setProcedimentodecoletaList(attachedProcedimentodecoletaList);
            em.persist(coleta);
            if (medida != null) {
                medida.getColetaList().add(coleta);
                medida = em.merge(medida);
            }
            for (Registrocoleta registrocoletaListRegistrocoleta : coleta.getRegistrocoletaList()) {
                Coleta oldColetaOfRegistrocoletaListRegistrocoleta = registrocoletaListRegistrocoleta.getColeta();
                registrocoletaListRegistrocoleta.setColeta(coleta);
                registrocoletaListRegistrocoleta = em.merge(registrocoletaListRegistrocoleta);
                if (oldColetaOfRegistrocoletaListRegistrocoleta != null) {
                    oldColetaOfRegistrocoletaListRegistrocoleta.getRegistrocoletaList().remove(registrocoletaListRegistrocoleta);
                    oldColetaOfRegistrocoletaListRegistrocoleta = em.merge(oldColetaOfRegistrocoletaListRegistrocoleta);
                }
            }
            for (Procedimentodeanalise procedimentodeanaliseListProcedimentodeanalise : coleta.getProcedimentodeanaliseList()) {
                Coleta oldColetaOfProcedimentodeanaliseListProcedimentodeanalise = procedimentodeanaliseListProcedimentodeanalise.getColeta();
                procedimentodeanaliseListProcedimentodeanalise.setColeta(coleta);
                procedimentodeanaliseListProcedimentodeanalise = em.merge(procedimentodeanaliseListProcedimentodeanalise);
                if (oldColetaOfProcedimentodeanaliseListProcedimentodeanalise != null) {
                    oldColetaOfProcedimentodeanaliseListProcedimentodeanalise.getProcedimentodeanaliseList().remove(procedimentodeanaliseListProcedimentodeanalise);
                    oldColetaOfProcedimentodeanaliseListProcedimentodeanalise = em.merge(oldColetaOfProcedimentodeanaliseListProcedimentodeanalise);
                }
            }
            for (Procedimentodecoleta procedimentodecoletaListProcedimentodecoleta : coleta.getProcedimentodecoletaList()) {
                Coleta oldColetaOfProcedimentodecoletaListProcedimentodecoleta = procedimentodecoletaListProcedimentodecoleta.getColeta();
                procedimentodecoletaListProcedimentodecoleta.setColeta(coleta);
                procedimentodecoletaListProcedimentodecoleta = em.merge(procedimentodecoletaListProcedimentodecoleta);
                if (oldColetaOfProcedimentodecoletaListProcedimentodecoleta != null) {
                    oldColetaOfProcedimentodecoletaListProcedimentodecoleta.getProcedimentodecoletaList().remove(procedimentodecoletaListProcedimentodecoleta);
                    oldColetaOfProcedimentodecoletaListProcedimentodecoleta = em.merge(oldColetaOfProcedimentodecoletaListProcedimentodecoleta);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findColeta(coleta.getColetaPK()) != null) {
                throw new PreexistingEntityException("Coleta " + coleta + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Coleta coleta) throws IllegalOrphanException, NonexistentEntityException, Exception {
        coleta.getColetaPK().setMedidaid(coleta.getMedida().getMedidaPK().getId());
        coleta.getColetaPK().setMedidaProjetoid(coleta.getMedida().getMedidaPK().getProjetoid());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Coleta persistentColeta = em.find(Coleta.class, coleta.getColetaPK());
            Medida medidaOld = persistentColeta.getMedida();
            Medida medidaNew = coleta.getMedida();
            List<Registrocoleta> registrocoletaListOld = persistentColeta.getRegistrocoletaList();
            List<Registrocoleta> registrocoletaListNew = coleta.getRegistrocoletaList();
            List<Procedimentodeanalise> procedimentodeanaliseListOld = persistentColeta.getProcedimentodeanaliseList();
            List<Procedimentodeanalise> procedimentodeanaliseListNew = coleta.getProcedimentodeanaliseList();
            List<Procedimentodecoleta> procedimentodecoletaListOld = persistentColeta.getProcedimentodecoletaList();
            List<Procedimentodecoleta> procedimentodecoletaListNew = coleta.getProcedimentodecoletaList();
            List<String> illegalOrphanMessages = null;
            for (Registrocoleta registrocoletaListOldRegistrocoleta : registrocoletaListOld) {
                if (!registrocoletaListNew.contains(registrocoletaListOldRegistrocoleta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Registrocoleta " + registrocoletaListOldRegistrocoleta + " since its coleta field is not nullable.");
                }
            }
            for (Procedimentodeanalise procedimentodeanaliseListOldProcedimentodeanalise : procedimentodeanaliseListOld) {
                if (!procedimentodeanaliseListNew.contains(procedimentodeanaliseListOldProcedimentodeanalise)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Procedimentodeanalise " + procedimentodeanaliseListOldProcedimentodeanalise + " since its coleta field is not nullable.");
                }
            }
            for (Procedimentodecoleta procedimentodecoletaListOldProcedimentodecoleta : procedimentodecoletaListOld) {
                if (!procedimentodecoletaListNew.contains(procedimentodecoletaListOldProcedimentodecoleta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Procedimentodecoleta " + procedimentodecoletaListOldProcedimentodecoleta + " since its coleta field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (medidaNew != null) {
                medidaNew = em.getReference(medidaNew.getClass(), medidaNew.getMedidaPK());
                coleta.setMedida(medidaNew);
            }
            List<Registrocoleta> attachedRegistrocoletaListNew = new ArrayList<Registrocoleta>();
            for (Registrocoleta registrocoletaListNewRegistrocoletaToAttach : registrocoletaListNew) {
                registrocoletaListNewRegistrocoletaToAttach = em.getReference(registrocoletaListNewRegistrocoletaToAttach.getClass(), registrocoletaListNewRegistrocoletaToAttach.getRegistrocoletaPK());
                attachedRegistrocoletaListNew.add(registrocoletaListNewRegistrocoletaToAttach);
            }
            registrocoletaListNew = attachedRegistrocoletaListNew;
            coleta.setRegistrocoletaList(registrocoletaListNew);
            List<Procedimentodeanalise> attachedProcedimentodeanaliseListNew = new ArrayList<Procedimentodeanalise>();
            for (Procedimentodeanalise procedimentodeanaliseListNewProcedimentodeanaliseToAttach : procedimentodeanaliseListNew) {
                procedimentodeanaliseListNewProcedimentodeanaliseToAttach = em.getReference(procedimentodeanaliseListNewProcedimentodeanaliseToAttach.getClass(), procedimentodeanaliseListNewProcedimentodeanaliseToAttach.getId());
                attachedProcedimentodeanaliseListNew.add(procedimentodeanaliseListNewProcedimentodeanaliseToAttach);
            }
            procedimentodeanaliseListNew = attachedProcedimentodeanaliseListNew;
            coleta.setProcedimentodeanaliseList(procedimentodeanaliseListNew);
            List<Procedimentodecoleta> attachedProcedimentodecoletaListNew = new ArrayList<Procedimentodecoleta>();
            for (Procedimentodecoleta procedimentodecoletaListNewProcedimentodecoletaToAttach : procedimentodecoletaListNew) {
                procedimentodecoletaListNewProcedimentodecoletaToAttach = em.getReference(procedimentodecoletaListNewProcedimentodecoletaToAttach.getClass(), procedimentodecoletaListNewProcedimentodecoletaToAttach.getId());
                attachedProcedimentodecoletaListNew.add(procedimentodecoletaListNewProcedimentodecoletaToAttach);
            }
            procedimentodecoletaListNew = attachedProcedimentodecoletaListNew;
            coleta.setProcedimentodecoletaList(procedimentodecoletaListNew);
            coleta = em.merge(coleta);
            if (medidaOld != null && !medidaOld.equals(medidaNew)) {
                medidaOld.getColetaList().remove(coleta);
                medidaOld = em.merge(medidaOld);
            }
            if (medidaNew != null && !medidaNew.equals(medidaOld)) {
                medidaNew.getColetaList().add(coleta);
                medidaNew = em.merge(medidaNew);
            }
            for (Registrocoleta registrocoletaListNewRegistrocoleta : registrocoletaListNew) {
                if (!registrocoletaListOld.contains(registrocoletaListNewRegistrocoleta)) {
                    Coleta oldColetaOfRegistrocoletaListNewRegistrocoleta = registrocoletaListNewRegistrocoleta.getColeta();
                    registrocoletaListNewRegistrocoleta.setColeta(coleta);
                    registrocoletaListNewRegistrocoleta = em.merge(registrocoletaListNewRegistrocoleta);
                    if (oldColetaOfRegistrocoletaListNewRegistrocoleta != null && !oldColetaOfRegistrocoletaListNewRegistrocoleta.equals(coleta)) {
                        oldColetaOfRegistrocoletaListNewRegistrocoleta.getRegistrocoletaList().remove(registrocoletaListNewRegistrocoleta);
                        oldColetaOfRegistrocoletaListNewRegistrocoleta = em.merge(oldColetaOfRegistrocoletaListNewRegistrocoleta);
                    }
                }
            }
            for (Procedimentodeanalise procedimentodeanaliseListNewProcedimentodeanalise : procedimentodeanaliseListNew) {
                if (!procedimentodeanaliseListOld.contains(procedimentodeanaliseListNewProcedimentodeanalise)) {
                    Coleta oldColetaOfProcedimentodeanaliseListNewProcedimentodeanalise = procedimentodeanaliseListNewProcedimentodeanalise.getColeta();
                    procedimentodeanaliseListNewProcedimentodeanalise.setColeta(coleta);
                    procedimentodeanaliseListNewProcedimentodeanalise = em.merge(procedimentodeanaliseListNewProcedimentodeanalise);
                    if (oldColetaOfProcedimentodeanaliseListNewProcedimentodeanalise != null && !oldColetaOfProcedimentodeanaliseListNewProcedimentodeanalise.equals(coleta)) {
                        oldColetaOfProcedimentodeanaliseListNewProcedimentodeanalise.getProcedimentodeanaliseList().remove(procedimentodeanaliseListNewProcedimentodeanalise);
                        oldColetaOfProcedimentodeanaliseListNewProcedimentodeanalise = em.merge(oldColetaOfProcedimentodeanaliseListNewProcedimentodeanalise);
                    }
                }
            }
            for (Procedimentodecoleta procedimentodecoletaListNewProcedimentodecoleta : procedimentodecoletaListNew) {
                if (!procedimentodecoletaListOld.contains(procedimentodecoletaListNewProcedimentodecoleta)) {
                    Coleta oldColetaOfProcedimentodecoletaListNewProcedimentodecoleta = procedimentodecoletaListNewProcedimentodecoleta.getColeta();
                    procedimentodecoletaListNewProcedimentodecoleta.setColeta(coleta);
                    procedimentodecoletaListNewProcedimentodecoleta = em.merge(procedimentodecoletaListNewProcedimentodecoleta);
                    if (oldColetaOfProcedimentodecoletaListNewProcedimentodecoleta != null && !oldColetaOfProcedimentodecoletaListNewProcedimentodecoleta.equals(coleta)) {
                        oldColetaOfProcedimentodecoletaListNewProcedimentodecoleta.getProcedimentodecoletaList().remove(procedimentodecoletaListNewProcedimentodecoleta);
                        oldColetaOfProcedimentodecoletaListNewProcedimentodecoleta = em.merge(oldColetaOfProcedimentodecoletaListNewProcedimentodecoleta);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                ColetaPK id = coleta.getColetaPK();
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

    public void destroy(ColetaPK id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Coleta coleta;
            try {
                coleta = em.getReference(Coleta.class, id);
                coleta.getColetaPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The coleta with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Registrocoleta> registrocoletaListOrphanCheck = coleta.getRegistrocoletaList();
            for (Registrocoleta registrocoletaListOrphanCheckRegistrocoleta : registrocoletaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Coleta (" + coleta + ") cannot be destroyed since the Registrocoleta " + registrocoletaListOrphanCheckRegistrocoleta + " in its registrocoletaList field has a non-nullable coleta field.");
            }
            List<Procedimentodeanalise> procedimentodeanaliseListOrphanCheck = coleta.getProcedimentodeanaliseList();
            for (Procedimentodeanalise procedimentodeanaliseListOrphanCheckProcedimentodeanalise : procedimentodeanaliseListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Coleta (" + coleta + ") cannot be destroyed since the Procedimentodeanalise " + procedimentodeanaliseListOrphanCheckProcedimentodeanalise + " in its procedimentodeanaliseList field has a non-nullable coleta field.");
            }
            List<Procedimentodecoleta> procedimentodecoletaListOrphanCheck = coleta.getProcedimentodecoletaList();
            for (Procedimentodecoleta procedimentodecoletaListOrphanCheckProcedimentodecoleta : procedimentodecoletaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Coleta (" + coleta + ") cannot be destroyed since the Procedimentodecoleta " + procedimentodecoletaListOrphanCheckProcedimentodecoleta + " in its procedimentodecoletaList field has a non-nullable coleta field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Medida medida = coleta.getMedida();
            if (medida != null) {
                medida.getColetaList().remove(coleta);
                medida = em.merge(medida);
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

    public Coleta findColeta(ColetaPK id) {
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
    
}
