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
            entityManager.getTransaction().begin();

            String cont = (String) entityManager.createQuery("SELECT COUNT(q.id) FROM Objetivodequestao q")
                    .getSingleResult();

            entityManager.getTransaction().commit();
            entityManager.close();

            return cont;
        } catch (Exception error) {
            throw error;
        }
    }

    public List<Objetivodequestao> ListQuestoesByProjeto(int idProjeto) {
        try {
            EntityManager entityManager = super.getEntityManager();
            entityManager.getTransaction().begin();

            List<Objetivodequestao> lista = entityManager.createQuery("SELECT q FROM Objetivodequestao q WHERE q.objetivoDeMedicaoid.projetoid.id = :idProjeto ORDER BY q.objetivoDeMedicaoid.nome ASC")
                    .setParameter("idProjeto", idProjeto).getResultList();

            entityManager.getTransaction().commit();
            entityManager.close();

            return lista;
        } catch (Exception error) {
            throw error;
        }
    }

    public Objetivodequestao findQuestaoByNomeAndIdProjeto(String nome_questao, int id_projeto) {
        try {
            EntityManager entityManager = super.getEntityManager();
            entityManager.getTransaction().begin();

            Objetivodequestao objetivodequestao = (Objetivodequestao) entityManager.createQuery("SELECT q FROM Objetivodequestao q WHERE q.nome = :nome_questao AND q.objetivoDeMedicaoid.projetoid.id = :id_projeto")
                    .setParameter("nome_questao", nome_questao)
                    .setParameter("id_projeto", id_projeto).getSingleResult();

            entityManager.getTransaction().commit();
            entityManager.close();

            return objetivodequestao;
        } catch (Exception error) {
            return null;
        }
    }

    public List findLastQuestao(String nome_questao) {
        try {
            EntityManager entityManager = super.getEntityManager();
            entityManager.getTransaction().begin();

            String query = "SELECT q FROM Objetivodequestao q WHERE q.nome = :nome_questao";
            List lista = entityManager.createQuery(query)
                    .setParameter("nome_questao", nome_questao).getResultList();

            entityManager.getTransaction().commit();
            entityManager.close();

            return lista;
        } catch (Exception error) {
            throw error;
        }
    }

    public List<Objetivodequestao> findParteNomeQuestao(String nome_questao, int id_projeto) {
        try {
            EntityManager entityManager = super.getEntityManager();
            entityManager.getTransaction().begin();

            List<Objetivodequestao> lista = entityManager.createQuery("SELECT q FROM Objetivodequestao q WHERE q.objetivoDeMedicaoid.projetoid.id = :id_projeto AND q.nome LIKE :nome_questao ORDER by q.id  ASC")
                    .setParameter("nome_questao", nome_questao + "%")
                    .setParameter("id_projeto", id_projeto).getResultList();

            entityManager.getTransaction().commit();
            entityManager.close();

            return lista;
        } catch (Exception error) {
            throw error;
        }
    }

    public List<Objetivodequestao> findListQuestaoByNomeAndIdProejto(String nome_questao, int id_projeto) {
        try {
            EntityManager entityManager = super.getEntityManager();
            entityManager.getTransaction().begin();

            List<Objetivodequestao> lista = entityManager.createQuery("SELECT q FROM Objetivodequestao q WHERE q.nome = :nome_questao AND q.objetivoDeMedicaoid.projetoid.id = :id_projeto")
                    .setParameter("nome_questao", nome_questao)
                    .setParameter("id_projeto", id_projeto).getResultList();

            entityManager.getTransaction().commit();
            entityManager.close();
            
            return lista;
        } catch (Exception error) {
            throw error;
        }
    }

}
