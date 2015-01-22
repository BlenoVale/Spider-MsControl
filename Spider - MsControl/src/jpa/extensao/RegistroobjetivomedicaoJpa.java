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

    public List<Registroobjetivomedicao> findRegistroMedicaoByTipo(int tipo, int idProjeto, int idObjetoMedicao) {
        List<Registroobjetivomedicao> listRegistroMedicao = null;

        EntityManager entityManager = super.getEntityManager();
        Query query = entityManager.createQuery("SELECT r FROM Registroobjetivomedicao r WHERE r.tipo = :tipo AND r.registroobjetivomedicaoPK.objetivoDeMedicaoProjetoid = :idProjeto AND r.registroobjetivomedicaoPK.objetivoDeMedicaoid = :idObjetoMedicao");
        query.setParameter("tipo", tipo);
        query.setParameter("idProjeto", idProjeto);
        query.setParameter("idObjetoMedicao", idObjetoMedicao);
        listRegistroMedicao = query.getResultList();

        return listRegistroMedicao;

    }

}
