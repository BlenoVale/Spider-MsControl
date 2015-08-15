package jpa.extensao;

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
            return (Analise) entityManager.createQuery("SELECT a FROM Analise a WHERE a.indicadorid.objetivoDeQuestaoid.objetivoDeMedicaoid.projetoid.id = :idProjeto")
                    .setParameter("idProjeto", idProjeto).getResultList().get(0);
        } catch (Exception error) {
            throw error;
        }

    }

}
