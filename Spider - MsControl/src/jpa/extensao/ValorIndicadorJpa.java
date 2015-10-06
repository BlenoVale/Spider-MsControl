package jpa.extensao;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.ValorindicadorJpaController;
import model.Valorindicador;

/**
 *
 * @author Bleno Vale
 */
public class ValorIndicadorJpa extends ValorindicadorJpaController {

    public ValorIndicadorJpa(EntityManagerFactory emf) {
        super(emf);
    }

    public Valorindicador findLastValorIndicador(String nome, int idProjeto) {
        try {
            EntityManager entityManager = super.getEntityManager();
            List<Valorindicador> lista = entityManager.createQuery("SELECT v From Valorindicador v WHERE v.indicadorid.nome = :nome AND v.indicadorid.objetivoDeQuestaoid.objetivoDeMedicaoid.projetoid.id = :idProjeto")
                    .setParameter("nome", nome).setParameter("idProjeto", idProjeto).getResultList();

            return lista.get(lista.size() - 1);
        } catch (Exception error) {
            throw error;
        }
    }

    public List<Valorindicador> findValorIndicadorByDatasAndProjeto(Date dataInicio, Date dataFim, int idIndicador, int idProjeto) {
        try {
            EntityManager entityManager = super.getEntityManager();
            return entityManager.createQuery("SELECT v FROM Valorindicador v WHERE v.data >= :dataInicio AND "
                    + "v.data <= :dataFim AND v.indicadorid.id = :idIndicador AND "
                    + "v.indicadorid.objetivoDeQuestaoid.objetivoDeMedicaoid.projetoid.id = :idProjeto "
                    + "ORDER BY v.id ASC")
                    .setParameter("dataInicio", dataInicio).setParameter("dataFim", dataFim)
                    .setParameter("idIndicador", idIndicador).setParameter("idProjeto", idProjeto)
                    .getResultList();
        } catch (Exception error) {
            throw error;
        }
    }
}
