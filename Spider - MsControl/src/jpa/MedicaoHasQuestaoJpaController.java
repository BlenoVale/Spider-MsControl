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
import model.MedicaoHasQuestao;
import model.MedicaoHasQuestaoPK;
import model.Objetivodemedicao;
import model.Objetivodequestao;

/**
 *
 * @author Dan
 */
public class MedicaoHasQuestaoJpaController implements Serializable {

    public MedicaoHasQuestaoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MedicaoHasQuestao medicaoHasQuestao) throws PreexistingEntityException, Exception {
        if (medicaoHasQuestao.getMedicaoHasQuestaoPK() == null) {
            medicaoHasQuestao.setMedicaoHasQuestaoPK(new MedicaoHasQuestaoPK());
        }
        medicaoHasQuestao.getMedicaoHasQuestaoPK().setObjetivoDeMedicaoid(medicaoHasQuestao.getObjetivodemedicao().getId());
        medicaoHasQuestao.getMedicaoHasQuestaoPK().setObjetivoDeQuestaoid(medicaoHasQuestao.getObjetivodequestao().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Objetivodemedicao objetivodemedicao = medicaoHasQuestao.getObjetivodemedicao();
            if (objetivodemedicao != null) {
                objetivodemedicao = em.getReference(objetivodemedicao.getClass(), objetivodemedicao.getId());
                medicaoHasQuestao.setObjetivodemedicao(objetivodemedicao);
            }
            Objetivodequestao objetivodequestao = medicaoHasQuestao.getObjetivodequestao();
            if (objetivodequestao != null) {
                objetivodequestao = em.getReference(objetivodequestao.getClass(), objetivodequestao.getId());
                medicaoHasQuestao.setObjetivodequestao(objetivodequestao);
            }
            em.persist(medicaoHasQuestao);
            if (objetivodemedicao != null) {
                objetivodemedicao.getMedicaoHasQuestaoList().add(medicaoHasQuestao);
                objetivodemedicao = em.merge(objetivodemedicao);
            }
            if (objetivodequestao != null) {
                objetivodequestao.getMedicaoHasQuestaoList().add(medicaoHasQuestao);
                objetivodequestao = em.merge(objetivodequestao);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMedicaoHasQuestao(medicaoHasQuestao.getMedicaoHasQuestaoPK()) != null) {
                throw new PreexistingEntityException("MedicaoHasQuestao " + medicaoHasQuestao + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MedicaoHasQuestao medicaoHasQuestao) throws NonexistentEntityException, Exception {
        medicaoHasQuestao.getMedicaoHasQuestaoPK().setObjetivoDeMedicaoid(medicaoHasQuestao.getObjetivodemedicao().getId());
        medicaoHasQuestao.getMedicaoHasQuestaoPK().setObjetivoDeQuestaoid(medicaoHasQuestao.getObjetivodequestao().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MedicaoHasQuestao persistentMedicaoHasQuestao = em.find(MedicaoHasQuestao.class, medicaoHasQuestao.getMedicaoHasQuestaoPK());
            Objetivodemedicao objetivodemedicaoOld = persistentMedicaoHasQuestao.getObjetivodemedicao();
            Objetivodemedicao objetivodemedicaoNew = medicaoHasQuestao.getObjetivodemedicao();
            Objetivodequestao objetivodequestaoOld = persistentMedicaoHasQuestao.getObjetivodequestao();
            Objetivodequestao objetivodequestaoNew = medicaoHasQuestao.getObjetivodequestao();
            if (objetivodemedicaoNew != null) {
                objetivodemedicaoNew = em.getReference(objetivodemedicaoNew.getClass(), objetivodemedicaoNew.getId());
                medicaoHasQuestao.setObjetivodemedicao(objetivodemedicaoNew);
            }
            if (objetivodequestaoNew != null) {
                objetivodequestaoNew = em.getReference(objetivodequestaoNew.getClass(), objetivodequestaoNew.getId());
                medicaoHasQuestao.setObjetivodequestao(objetivodequestaoNew);
            }
            medicaoHasQuestao = em.merge(medicaoHasQuestao);
            if (objetivodemedicaoOld != null && !objetivodemedicaoOld.equals(objetivodemedicaoNew)) {
                objetivodemedicaoOld.getMedicaoHasQuestaoList().remove(medicaoHasQuestao);
                objetivodemedicaoOld = em.merge(objetivodemedicaoOld);
            }
            if (objetivodemedicaoNew != null && !objetivodemedicaoNew.equals(objetivodemedicaoOld)) {
                objetivodemedicaoNew.getMedicaoHasQuestaoList().add(medicaoHasQuestao);
                objetivodemedicaoNew = em.merge(objetivodemedicaoNew);
            }
            if (objetivodequestaoOld != null && !objetivodequestaoOld.equals(objetivodequestaoNew)) {
                objetivodequestaoOld.getMedicaoHasQuestaoList().remove(medicaoHasQuestao);
                objetivodequestaoOld = em.merge(objetivodequestaoOld);
            }
            if (objetivodequestaoNew != null && !objetivodequestaoNew.equals(objetivodequestaoOld)) {
                objetivodequestaoNew.getMedicaoHasQuestaoList().add(medicaoHasQuestao);
                objetivodequestaoNew = em.merge(objetivodequestaoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                MedicaoHasQuestaoPK id = medicaoHasQuestao.getMedicaoHasQuestaoPK();
                if (findMedicaoHasQuestao(id) == null) {
                    throw new NonexistentEntityException("The medicaoHasQuestao with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(MedicaoHasQuestaoPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MedicaoHasQuestao medicaoHasQuestao;
            try {
                medicaoHasQuestao = em.getReference(MedicaoHasQuestao.class, id);
                medicaoHasQuestao.getMedicaoHasQuestaoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The medicaoHasQuestao with id " + id + " no longer exists.", enfe);
            }
            Objetivodemedicao objetivodemedicao = medicaoHasQuestao.getObjetivodemedicao();
            if (objetivodemedicao != null) {
                objetivodemedicao.getMedicaoHasQuestaoList().remove(medicaoHasQuestao);
                objetivodemedicao = em.merge(objetivodemedicao);
            }
            Objetivodequestao objetivodequestao = medicaoHasQuestao.getObjetivodequestao();
            if (objetivodequestao != null) {
                objetivodequestao.getMedicaoHasQuestaoList().remove(medicaoHasQuestao);
                objetivodequestao = em.merge(objetivodequestao);
            }
            em.remove(medicaoHasQuestao);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<MedicaoHasQuestao> findMedicaoHasQuestaoEntities() {
        return findMedicaoHasQuestaoEntities(true, -1, -1);
    }

    public List<MedicaoHasQuestao> findMedicaoHasQuestaoEntities(int maxResults, int firstResult) {
        return findMedicaoHasQuestaoEntities(false, maxResults, firstResult);
    }

    private List<MedicaoHasQuestao> findMedicaoHasQuestaoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MedicaoHasQuestao.class));
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

    public MedicaoHasQuestao findMedicaoHasQuestao(MedicaoHasQuestaoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MedicaoHasQuestao.class, id);
        } finally {
            em.close();
        }
    }

    public int getMedicaoHasQuestaoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<MedicaoHasQuestao> rt = cq.from(MedicaoHasQuestao.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
