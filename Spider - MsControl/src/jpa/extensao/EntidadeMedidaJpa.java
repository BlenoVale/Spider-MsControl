package jpa.extensao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.EntidademedidaJpaController;
import model.Entidademedida;

/**
 *
 * @author BlenoVale
 */
public class EntidadeMedidaJpa extends EntidademedidaJpaController {

    public EntidadeMedidaJpa(EntityManagerFactory emf) {
        super(emf);
    }

    public List<Entidademedida> findListaEntidadeMedida(int idProjeto) {
        try {
            EntityManager entityManager = super.getEntityManager();
            return entityManager.createQuery("SELECT e FROM Entidademedida e WHERE e.projetoid IS NULL OR e.projetoid.id = :idProjeto ORDER BY e.id ASC")
                    .setParameter("idProjeto", idProjeto)
                    .getResultList();
        } catch (Exception error) {
            throw error;
        }
    }

    public List<Entidademedida> findListaEntidadesMedidasCadastradas(int idProjeto) {
        try {
            EntityManager entityManager = super.getEntityManager();
            return entityManager.createQuery("SELECT e FROM Entidademedida e WHERE e.projetoid.id = :idProjeto ORDER BY e.id ASC")
                    .setParameter("idProjeto", idProjeto)
                    .getResultList();
        } catch (Exception error) {
            throw error;
        }
    }

    public Entidademedida findEntidadeMedidaByNomeAndProjeto(String nome, int idProjeto) {

        try {
            EntityManager entityManager = super.getEntityManager();
            return (Entidademedida) entityManager.createQuery("SELECT e FROM Entidademedida e WHERE e.nome = :nome AND e.projetoid.id = :idProjeto")
                    .setParameter("nome", nome)
                    .setParameter("idProjeto", idProjeto)
                    .getSingleResult();
        } catch (Exception error) {
            return null;
        }
    }
}
