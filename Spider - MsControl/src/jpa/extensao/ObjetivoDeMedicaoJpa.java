package jpa.extensao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import jpa.ObjetivodemedicaoJpaController;
import model.Objetivodemedicao;

import util.Conexao;

/**
 *
 * @author BlenoVale
 */
public class ObjetivoDeMedicaoJpa extends ObjetivodemedicaoJpaController {

    public ObjetivoDeMedicaoJpa() {
        super(Conexao.conectar());
    }
    
    public Objetivodemedicao findByNomeAndIdProjeto(String nomeObjMedicao, int idProjeto) {
        try {
            Objetivodemedicao objetivodemedicao = null;
            EntityManager entityManager = super.getEntityManager();
            Query query = entityManager.createQuery("SELECT o FROM Objetivodemedicao o WHERE o.nome = :nome AND o.projetoid.id = :idProjeto");
            query.setParameter("nome", nomeObjMedicao);
            query.setParameter("idProjeto", idProjeto);
            objetivodemedicao = (Objetivodemedicao) query.getSingleResult();
            return objetivodemedicao;
        } catch (Exception error) {
            return null;
        }
    }

    public List<Objetivodemedicao> findObjetivoMedicaoByIdProjeto(int idProjeto) {
        List<Objetivodemedicao> listObjetivoMedicao = null;

        
            EntityManager entityManager = super.getEntityManager();
            Query query = entityManager.createQuery("SELECT u FROM Objetivodemedicao u WHERE u.projetoid.id = :idProjeto ORDER BY u.nome ASC");
            query.setParameter("idProjeto", idProjeto);
            listObjetivoMedicao = query.getResultList();

            return listObjetivoMedicao;
        
    }

    public List<Objetivodemedicao> findObjetivoMedicaoByPartNome(String nome, int idProjeto) {
        List<Objetivodemedicao> listobObjetivoMedicao = null;

        EntityManager entityManager = super.getEntityManager();
        Query query = entityManager.createQuery("SELECT u FROM Objetivodemedicao u WHERE u.nome LIKE :nome AND u.projetoid.id = :idProjeto ORDER BY u.nome ASC");
        query.setParameter("nome", nome + "%");
        query.setParameter("idProjeto", idProjeto);

        listobObjetivoMedicao = query.getResultList();

        return listobObjetivoMedicao;
    }
    public Objetivodemedicao findObjetivo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Objetivodemedicao.class, id);
        } finally {
            em.close();
        }
    } 
    public void excluirObjetivo(String nomeObjetivo, int idProjeto){
       
        EntityManager entityManager = super.getEntityManager();
        Query query = entityManager.createQuery("DELETE FROM Objetivodemedicao u WHERE u.nome = :nome AND u.projetoid.id = :idProjeto");
        query.setParameter("nome", nomeObjetivo);
        query.setParameter("idProjeto", idProjeto);
        query.getSingleResult();
        
    }

}
