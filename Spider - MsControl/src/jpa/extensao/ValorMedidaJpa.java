package jpa.extensao;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.ValormedidaJpaController;
import model.Valormedida;

/**
 *
 * @author Bleno Vale
 */
public class ValorMedidaJpa extends ValormedidaJpaController {

    public ValorMedidaJpa(EntityManagerFactory emf) {
        super(emf);
    }

    public List<Valormedida> findListValorMedidaBynameAndProjeto(String nome, int idProjeto) {
        try {
            EntityManager entityManager = super.getEntityManager();
            return entityManager.createQuery("SELECT v FROM Valormedida v WHERE v.medidaid.mnemonico = :nome AND v.medidaid.projetoId = :idProjeto ORDER BY v.medidaid.mnemonico ASC")
                    .setParameter("nome", nome).setParameter("idProjeto", idProjeto)
                    .getResultList();
        } catch (Exception error) {
            throw error;
        }
    }

    public Valormedida findValorMedidaBynameAndIdProjeto(String nome, int idProjeto) {
        try {
            EntityManager entityManager = super.getEntityManager();
            return (Valormedida) entityManager.createQuery("SELECT v FROM Valormedida v WHERE v.medidaid.mnemonico = :nome AND v.medidaid.projetoId = :idProjeto")
                    .setParameter("nome", nome).setParameter("idProjeto", idProjeto)
                    .getResultList().get(0);
        } catch (Exception error) {
            throw error;
        }
    }

    public List<Valormedida> findValorMedidaByDataAndIdProjeto(String mnemonico, Date dataInicio, Date dataFim, int idProjeto) {
        try {
            EntityManager entityManager = super.getEntityManager();
            return entityManager.createQuery("SELECT v FROM Valormedida v WHERE v.medidaid.mnemonico = :mnemonico AND v.data >= :dataInicio AND v.data <= :dataFim AND  v.medidaid.projetoId = :idProjeto") 
                    .setParameter("mnemonico", mnemonico).setParameter("dataInicio", dataInicio)
                    .setParameter("dataFim", dataFim).setParameter("idProjeto", idProjeto)
                    .getResultList();
        } catch (Exception error) {
            throw error;
        }
    }
}
