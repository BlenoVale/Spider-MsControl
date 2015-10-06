package jpa.extensao;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.AnaliseJpaController;
import model.Analise;

/**
 *
 * @author Bleno Vale
 */
public class AnaliseJpa extends AnaliseJpaController {

    public AnaliseJpa(EntityManagerFactory emf) {
        super(emf);
    }

    public Analise findAnaliseByIDProjeto(int idProjeto) {
        try {
            EntityManager entityManager = super.getEntityManager();
            List<Analise> lista = entityManager.createQuery("SELECT a FROM Analise a WHERE a.indicadorid.objetivoDeQuestaoid.objetivoDeMedicaoid.projetoid.id = :idProjeto")
                    .setParameter("idProjeto", idProjeto).getResultList();
            return (Analise) lista.get(lista.size() - 1);
        } catch (Exception error) {
            throw error;
        }

    }

    public List<Analise> findListAnaliseByIDProjeto(int idProjeto) {
        try {
            EntityManager entityManager = super.getEntityManager();
            return entityManager.createQuery("SELECT a FROM Analise a WHERE a.indicadorid.objetivoDeQuestaoid.objetivoDeMedicaoid.projetoid.id = :idProjeto")
                    .setParameter("idProjeto", idProjeto).getResultList();
        } catch (Exception error) {
            throw error;
        }

    }

    public List<Analise> findAnaliseByDatasAndProjeto(Date dataInicio, Date dataFim, int idProjeto) {
        try {
            EntityManager entityManager = super.getEntityManager();
            return entityManager.createQuery("SELECT a FROM Analise a WHERE  a.dataCriação >= :dataInicio "
                    + "AND a.dataCriação <= :dataFim AND  "
                    + "a.indicadorid.objetivoDeQuestaoid.objetivoDeMedicaoid.projetoid.id = :idProjeto "
                    + "ORDER BY a.dataCriação")
                    .setParameter("dataInicio", dataInicio).setParameter("dataFim", dataFim)
                    .setParameter("idProjeto", idProjeto).getResultList();
        } catch (Exception error) {
            throw error;
        }
    }

}
