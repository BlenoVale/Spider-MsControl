package jpa.extensao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import jpa.RegistroobjetivomedicaoJpaController;
import model.Registroobjetivomedicao;
import util.Conexao;

/**
 *
 * @author DAN JHONATAN
 */
public class RegistroobjetivomedicaoJpa extends RegistroobjetivomedicaoJpaController {

    public RegistroobjetivomedicaoJpa() {
        super(Conexao.conectar());
    }

    public List<Registroobjetivomedicao> findRegistroMedicaoByTipo(int tipo, int projetoid, int idObjetoMedicao) {
        List<Registroobjetivomedicao> listRegistroMedicao = null;

        EntityManager entityManager = super.getEntityManager();
        Query query;// = entityManager.createQuery("SELECT r FROM Registroobjetivomedicao r WHERE r.tipo = :tipo AND r.registroobjetivomedicaoPK = :idProjeto AND r.registroobjetivomedicaoPK.objetivoDeMedicaoid = :idObjetoMedicao");
        query = entityManager.createQuery("SELECT r FROM Registroobjetivomedicao r WHERE r.tipo = :tipo AND r.objetivodemedicao.objetivodemedicaoPK.projetoid = :projetoid AND r.objetivodemedicao.objetivodemedicaoPK.id = :id");
        query.setParameter("tipo", tipo);
        query.setParameter("projetoid", projetoid);
        query.setParameter("id", idObjetoMedicao);
        listRegistroMedicao = query.getResultList();

        return listRegistroMedicao;

    }

}
