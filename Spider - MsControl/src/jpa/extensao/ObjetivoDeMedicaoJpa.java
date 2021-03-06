package jpa.extensao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import jpa.ObjetivodemedicaoJpaController;
import model.Objetivodemedicao;


/**
 *
 * @author BlenoVale
 */
public class ObjetivoDeMedicaoJpa extends ObjetivodemedicaoJpaController {

    public ObjetivoDeMedicaoJpa(EntityManagerFactory emf) {
        super(emf);
    }

    public Objetivodemedicao findByNomeAndIdProjeto(String nomeObjMedicao, int idProjeto) {
        try {
            Objetivodemedicao objetivodemedicao = null;
            EntityManager entityManager = super.getEntityManager();
            entityManager.getTransaction().begin();

            Query query = entityManager.createQuery("SELECT o FROM Objetivodemedicao o WHERE o.nome = :nome AND o.projetoid.id = :idProjeto");
            query.setParameter("nome", nomeObjMedicao);
            query.setParameter("idProjeto", idProjeto);
            objetivodemedicao = (Objetivodemedicao) query.getSingleResult();

            entityManager.getTransaction().commit();
            entityManager.close();

            return objetivodemedicao;
        } catch (Exception error) {
            return null;
        }
    }

    public List<Objetivodemedicao> findObjetivoMedicaoByIdProjeto(int idProjeto) {
        List<Objetivodemedicao> listObjetivoMedicao = null;

        EntityManager entityManager = super.getEntityManager();
        entityManager.getTransaction().begin();

        Query query = entityManager.createQuery("SELECT u FROM Objetivodemedicao u WHERE u.projetoid.id = :idProjeto ORDER BY u.nome ASC");
        query.setParameter("idProjeto", idProjeto);
        listObjetivoMedicao = query.getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();

        return listObjetivoMedicao;

    }

    public List<Objetivodemedicao> findObjetivoMedicaoByPartNome(String nome, int idProjeto) {
        List<Objetivodemedicao> listobObjetivoMedicao = null;

        EntityManager entityManager = super.getEntityManager();
        entityManager.getTransaction().begin();

        Query query = entityManager.createQuery("SELECT u FROM Objetivodemedicao u WHERE u.nome LIKE :nome AND u.projetoid.id = :idProjeto ORDER BY u.nome ASC");
        query.setParameter("nome", nome + "%");
        query.setParameter("idProjeto", idProjeto);

        listobObjetivoMedicao = query.getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();

        return listobObjetivoMedicao;
    }

    public Objetivodemedicao findObjetivo(Integer id) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        try {
            return em.find(Objetivodemedicao.class, id);
        } finally {
            em.getTransaction().commit();
            em.close();
        }
    }

    public void excluirObjetivo(String nomeObjetivo, int idProjeto) {

        EntityManager entityManager = super.getEntityManager();
        Query query = entityManager.createQuery("DELETE FROM Objetivodemedicao u WHERE u.nome = :nome AND u.projetoid.id = :idProjeto");
        query.setParameter("nome", nomeObjetivo);
        query.setParameter("idProjeto", idProjeto);
        query.getSingleResult();

    }

}
