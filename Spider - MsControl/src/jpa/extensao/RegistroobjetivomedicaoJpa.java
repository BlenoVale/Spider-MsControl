package jpa.extensao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import jpa.RegistroobjetivomedicaoJpaController;
import model.Registroobjetivomedicao;

/**
 *
 * @author DAN JHONATAN
 */
public class RegistroobjetivomedicaoJpa extends RegistroobjetivomedicaoJpaController {

    public RegistroobjetivomedicaoJpa(EntityManagerFactory emf) {
        super(emf);
    }

    public List<Registroobjetivomedicao> findRegistroMedicaoByTipo(int tipo, int projetoid, int idObjetoMedicao) {
        List<Registroobjetivomedicao> listRegistroMedicao = null;

        EntityManager entityManager = super.getEntityManager();
        Query query;// = entityManager.createQuery("SELECT r FROM Registroobjetivomedicao r WHERE r.tipo = :tipo AND r.registroobjetivomedicaoPK = :idProjeto AND r.registroobjetivomedicaoPK.objetivoDeMedicaoid = :idObjetoMedicao");
        query = entityManager.createQuery("SELECT r FROM Registroobjetivomedicao r WHERE r.tipo = :tipo AND r.objetivoDeMedicaoid.projetoid.id = :projetoid AND r.objetivoDeMedicaoid.id = :id");
        query.setParameter("tipo", tipo);
        query.setParameter("projetoid", projetoid);
        query.setParameter("id", idObjetoMedicao);
        listRegistroMedicao = query.getResultList();

        return listRegistroMedicao;

    }

}
