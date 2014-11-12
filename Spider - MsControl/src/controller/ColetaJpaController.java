/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.Coleta;
import model.ColetaPK;
import model.Medida;
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
        coleta.getColetaPK().setIdMedida(coleta.getMedida().getMedidaPK().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Medida medida = coleta.getMedida();
            if (medida != null) {
                medida = em.getReference(medida.getClass(), medida.getMedidaPK());
                coleta.setMedida(medida);
            }
            Procedimentodeanalise idProcedimentoDeAnalise = coleta.getIdProcedimentoDeAnalise();
            if (idProcedimentoDeAnalise != null) {
                idProcedimentoDeAnalise = em.getReference(idProcedimentoDeAnalise.getClass(), idProcedimentoDeAnalise.getId());
                coleta.setIdProcedimentoDeAnalise(idProcedimentoDeAnalise);
            }
            Procedimentodecoleta idProcedimentoDeColeta = coleta.getIdProcedimentoDeColeta();
            if (idProcedimentoDeColeta != null) {
                idProcedimentoDeColeta = em.getReference(idProcedimentoDeColeta.getClass(), idProcedimentoDeColeta.getId());
                coleta.setIdProcedimentoDeColeta(idProcedimentoDeColeta);
            }
            em.persist(coleta);
            if (medida != null) {
                medida.getColetaList().add(coleta);
                medida = em.merge(medida);
            }
            if (idProcedimentoDeAnalise != null) {
                idProcedimentoDeAnalise.getColetaList().add(coleta);
                idProcedimentoDeAnalise = em.merge(idProcedimentoDeAnalise);
            }
            if (idProcedimentoDeColeta != null) {
                idProcedimentoDeColeta.getColetaList().add(coleta);
                idProcedimentoDeColeta = em.merge(idProcedimentoDeColeta);
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

    public void edit(Coleta coleta) throws NonexistentEntityException, Exception {
        coleta.getColetaPK().setIdMedida(coleta.getMedida().getMedidaPK().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Coleta persistentColeta = em.find(Coleta.class, coleta.getColetaPK());
            Medida medidaOld = persistentColeta.getMedida();
            Medida medidaNew = coleta.getMedida();
            Procedimentodeanalise idProcedimentoDeAnaliseOld = persistentColeta.getIdProcedimentoDeAnalise();
            Procedimentodeanalise idProcedimentoDeAnaliseNew = coleta.getIdProcedimentoDeAnalise();
            Procedimentodecoleta idProcedimentoDeColetaOld = persistentColeta.getIdProcedimentoDeColeta();
            Procedimentodecoleta idProcedimentoDeColetaNew = coleta.getIdProcedimentoDeColeta();
            if (medidaNew != null) {
                medidaNew = em.getReference(medidaNew.getClass(), medidaNew.getMedidaPK());
                coleta.setMedida(medidaNew);
            }
            if (idProcedimentoDeAnaliseNew != null) {
                idProcedimentoDeAnaliseNew = em.getReference(idProcedimentoDeAnaliseNew.getClass(), idProcedimentoDeAnaliseNew.getId());
                coleta.setIdProcedimentoDeAnalise(idProcedimentoDeAnaliseNew);
            }
            if (idProcedimentoDeColetaNew != null) {
                idProcedimentoDeColetaNew = em.getReference(idProcedimentoDeColetaNew.getClass(), idProcedimentoDeColetaNew.getId());
                coleta.setIdProcedimentoDeColeta(idProcedimentoDeColetaNew);
            }
            coleta = em.merge(coleta);
            if (medidaOld != null && !medidaOld.equals(medidaNew)) {
                medidaOld.getColetaList().remove(coleta);
                medidaOld = em.merge(medidaOld);
            }
            if (medidaNew != null && !medidaNew.equals(medidaOld)) {
                medidaNew.getColetaList().add(coleta);
                medidaNew = em.merge(medidaNew);
            }
            if (idProcedimentoDeAnaliseOld != null && !idProcedimentoDeAnaliseOld.equals(idProcedimentoDeAnaliseNew)) {
                idProcedimentoDeAnaliseOld.getColetaList().remove(coleta);
                idProcedimentoDeAnaliseOld = em.merge(idProcedimentoDeAnaliseOld);
            }
            if (idProcedimentoDeAnaliseNew != null && !idProcedimentoDeAnaliseNew.equals(idProcedimentoDeAnaliseOld)) {
                idProcedimentoDeAnaliseNew.getColetaList().add(coleta);
                idProcedimentoDeAnaliseNew = em.merge(idProcedimentoDeAnaliseNew);
            }
            if (idProcedimentoDeColetaOld != null && !idProcedimentoDeColetaOld.equals(idProcedimentoDeColetaNew)) {
                idProcedimentoDeColetaOld.getColetaList().remove(coleta);
                idProcedimentoDeColetaOld = em.merge(idProcedimentoDeColetaOld);
            }
            if (idProcedimentoDeColetaNew != null && !idProcedimentoDeColetaNew.equals(idProcedimentoDeColetaOld)) {
                idProcedimentoDeColetaNew.getColetaList().add(coleta);
                idProcedimentoDeColetaNew = em.merge(idProcedimentoDeColetaNew);
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

    public void destroy(ColetaPK id) throws NonexistentEntityException {
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
            Medida medida = coleta.getMedida();
            if (medida != null) {
                medida.getColetaList().remove(coleta);
                medida = em.merge(medida);
            }
            Procedimentodeanalise idProcedimentoDeAnalise = coleta.getIdProcedimentoDeAnalise();
            if (idProcedimentoDeAnalise != null) {
                idProcedimentoDeAnalise.getColetaList().remove(coleta);
                idProcedimentoDeAnalise = em.merge(idProcedimentoDeAnalise);
            }
            Procedimentodecoleta idProcedimentoDeColeta = coleta.getIdProcedimentoDeColeta();
            if (idProcedimentoDeColeta != null) {
                idProcedimentoDeColeta.getColetaList().remove(coleta);
                idProcedimentoDeColeta = em.merge(idProcedimentoDeColeta);
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
