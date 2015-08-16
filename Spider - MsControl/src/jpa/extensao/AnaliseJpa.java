package jpa.extensao;

import java.util.List;
import javax.persistence.EntityManager;
import jpa.AnaliseJpaController;
import model.Analise;
import util.Conexao;

/**
 *
 * @author Bleno Vale
 */
public class AnaliseJpa extends AnaliseJpaController {

    public AnaliseJpa() {
        super(Conexao.conectar());
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

}
