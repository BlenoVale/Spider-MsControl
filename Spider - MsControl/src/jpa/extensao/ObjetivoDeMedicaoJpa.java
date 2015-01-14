package jpa.extensao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import jpa.ObjetivodemedicacaoJpaController;
import model.Objetivodemedicacao;
import util.Conexao;

/**
 *
 * @author BlenoVale
 */
public class ObjetivoDeMedicaoJpa extends ObjetivodemedicacaoJpaController {

    public ObjetivoDeMedicaoJpa() {
        super(Conexao.conectar());
    }

    public Objetivodemedicacao findByNome(String nomeObjMedicao) {
        try {
            EntityManager entityManager = super.getEntityManager();
            return (Objetivodemedicacao) entityManager.createQuery("SELECT o FROM Objetivodemedicacao o WHERE o.nome = :nome")
                    .setParameter("nome", nomeObjMedicao).getSingleResult();
        } catch (Exception error) {
            throw error;
        }
    }

    public List<Objetivodemedicacao> findObjetivoMedicaoByIdProjeto(int idProjeto) {
        List<Objetivodemedicacao> listObjetivoMedicao = null;

        
            EntityManager entityManager = super.getEntityManager();
            Query query = entityManager.createQuery("SELECT u FROM Objetivodemedicacao u WHERE u.objetivodemedicacaoPK.projetoid = :idProjeto ORDER BY u.nome ASC");
            query.setParameter("idProjeto", idProjeto);
            listObjetivoMedicao = query.getResultList();

            return listObjetivoMedicao;
        
    }

    public List<Objetivodemedicacao> findObjetivoMedicaoByPartNome(String nome) {
        List<Objetivodemedicacao> listobObjetivoMedicao = null;

        EntityManager entityManager = super.getEntityManager();
        Query query = entityManager.createQuery("SELECT u FROM Objetivodemedicacao u WHERE u.nome LIKE :nome ORDER BY u.nome ASC");
        query.setParameter("nome", nome + "%");

        listobObjetivoMedicao = query.getResultList();

        return listobObjetivoMedicao;
    }
    public Objetivodemedicacao findObjetivo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Objetivodemedicacao.class, id);
        } finally {
            em.close();
        }
    } 

}
