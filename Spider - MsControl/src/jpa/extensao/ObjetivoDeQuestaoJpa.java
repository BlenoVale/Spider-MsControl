package jpa.extensao;

import java.util.List;
import javax.persistence.EntityManager;
import jpa.ObjetivodequestaoJpaController;
import model.Objetivodemedicao;
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
            return entityManager.createQuery("SELECT q FROM Objetivodequestao q WHERE q.objetivodequestaoPK.objetivoDeMedicaoProjetoid = :id_projeto ORDER by q.prioridade ASC")
                    .setParameter("id_projeto", id_projeto).getResultList();
        } catch (Exception error) {
            throw error;
        }
    }

    public Objetivodequestao findQuestaoByNomeAndIdProjeto(String nome_questao, int id_projeto) {
        try {
            EntityManager entityManager = super.getEntityManager();
            String query = "SELECT q FROM Objetivodequestao q WHERE q.nome = :nome_questao AND q.objetivodequestaoPK.objetivoDeMedicaoProjetoid = :id_projeto";
            return (Objetivodequestao) entityManager.createQuery(query)
                    .setParameter("nome_questao", nome_questao)
                    .setParameter("id_projeto", id_projeto).getSingleResult();
        } catch (Exception error) {
            throw error;
        }
    }

    public List<Objetivodequestao> findParteNomeQuestao(String nome_questao, int id_projeto) {
        try {
            EntityManager entityManager = super.getEntityManager();
            String query = "SELECT q FROM Objetivodequestao q WHERE q.objetivodequestaoPK.objetivoDeMedicaoProjetoid = :id_projeto AND q.nome LIKE :nome_questao ORDER by q.prioridade ASC";
            return entityManager.createQuery(query)
                    .setParameter("nome_questao", nome_questao + "%")
                    .setParameter("id_projeto", id_projeto).getResultList();
        } catch (Exception error) {
            throw error;
        }
    }

    public List<Objetivodequestao> findListQuestaoByNomeAndIdProejto(String nome_questao, int id_projeto, int prioridade) {
        try {
            EntityManager entityManager = super.getEntityManager();
            String query = "SELECT q FROM Objetivodequestao q WHERE q.nome = :nome_questao AND q.objetivodequestaoPK.objetivoDeMedicaoProjetoid = :id_projeto And q.prioridade <> :prioridade";
            return entityManager.createQuery(query)
                    .setParameter("nome_questao", nome_questao)
                    .setParameter("id_projeto", id_projeto)
                    .setParameter("prioridade", prioridade).getResultList();
        } catch (Exception error) {
            throw error;
        }
    }

    public void myEdit(Objetivodequestao objetivodequestao, Objetivodemedicao objetivodemedicao) {
//        objetivodequestao.getObjetivodequestaoPK().setObjetivoDeMedicaoid(objetivodequestao.getObjetivodemedicao().getObjetivodemedicaoPK().getId());
//        objetivodequestao.getObjetivodequestaoPK().setObjetivoDeMedicaoProjetoid(objetivodequestao.getObjetivodemedicao().getObjetivodemedicaoPK().getProjetoid());
//        objetivodequestao.getObjetivodequestaoPK().setId(objetivodequestao.getObjetivodequestaoPK().getId());

        EntityManager entityManager = super.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            Objetivodequestao questaoNew = entityManager.find(Objetivodequestao.class, objetivodequestao.getObjetivodequestaoPK());
           
            Objetivodemedicao objetivodemedicaoOld = findQuestaoByNomeAndIdProjeto(objetivodequestao.getNome(), objetivodequestao.getObjetivodequestaoPK().getObjetivoDeMedicaoProjetoid()).getObjetivodemedicao();
            if (objetivodemedicaoOld != null && !objetivodemedicaoOld.equals(objetivodequestao.getObjetivodemedicao())) {
                objetivodemedicaoOld.getObjetivodequestaoList().remove(objetivodequestao);
                entityManager.merge(objetivodemedicaoOld);
            }
            if (objetivodequestao.getObjetivodemedicao() != null && !objetivodequestao.getObjetivodemedicao().equals(objetivodemedicaoOld)) {
                objetivodequestao.getObjetivodemedicao().getObjetivodequestaoList().add(objetivodequestao);
                entityManager.merge(objetivodequestao.getObjetivodemedicao());
            }
            
            questaoNew.setObjetivodemedicao(objetivodemedicao);
            entityManager.merge(questaoNew);
            entityManager.getTransaction().commit();
        } catch (Exception error) {
            System.out.println("Error: " + error.getMessage());
            error.printStackTrace();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }
}
