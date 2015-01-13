package jpa.extensao;

import java.util.List;
import javax.persistence.EntityManager;
import jpa.ObjetivodequestaoJpaController;
import model.Objetivodequestao;
import util.Conexao;

/**
 *
 * @author BlenoVale
 */
public class ObjetivoDeQuestaoJpa extends ObjetivodequestaoJpaController {

    public ObjetivoDeQuestaoJpa() {
        super(Conexao.conectar());
    }

    public String countQuantidadeDeQuestoes() {
        try {
            EntityManager entityManager = super.getEntityManager();
            return (String) entityManager.createQuery("SELECT COUNT(q.objetivodequestaoPK.id) FROM Objetivodequestao q")
                    .getSingleResult();
        } catch (Exception error) {
            throw error;
        }
    }

    public List<Objetivodequestao> ListQuestoesByProjeto(int id_projeto) {
        try {
            EntityManager entityManager = super.getEntityManager();
            return entityManager.createQuery("SELECT q FROM Objetivodequestao q WHERE q.objetivodequestaoPK.objetivoDeMedicacaoProjetoid = :id_projeto")
                    .setParameter("id_projeto", id_projeto).getResultList();
        } catch (Exception error) {
            throw error;
        }
    }

    public Objetivodequestao findQuestaoByNome(String nome_questao) {
        try {
            EntityManager entityManager = super.getEntityManager();
            return (Objetivodequestao) entityManager.createQuery("SELECT q FROM Objetivodequestao q WHERE q.nome = :nome_questao")
                    .setParameter("nome_questao", nome_questao).getSingleResult();
        } catch (Exception error) {
            throw error;
        }
    }

}
