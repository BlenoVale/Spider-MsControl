package jpa.extensao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import jpa.ProcedimentodeanaliseJpaController;
import model.Procedimentodeanalise;
import util.Conexao;

/**
 *
 * @author Géssica
 */
public class ProcedimentoDeAnaliseJpa extends ProcedimentodeanaliseJpaController {

    public ProcedimentoDeAnaliseJpa() {
        super(Conexao.conectar());
    }

    public List<Procedimentodeanalise> findListaIndicadoresByProjeto(int idDoProjeto) {
        try {
            List<Procedimentodeanalise> lista = null;
            EntityManager emf = super.getEntityManager();
            Query q = emf.createQuery("SELECT i FROM Procedimentodeanalise i WHERE i.indicadorid.objetivoDeQuestaoid.objetivoDeMedicaoid.projetoid.id = :idDoProjeto ORDER By i.prioridade ASC")
                    .setParameter("idDoProjeto", idDoProjeto);

            lista = q.getResultList();
            return lista;
        } catch (Exception error) {
            throw error;
        }
    }

    public List<Procedimentodeanalise> findAllByProjeto(int idProjeto) {
        try {
            EntityManager entityManager = getEntityManager();
            Query query = entityManager.createQuery("SELECT p FROM Procedimentodeanalise p WHERE p.projetoId =:idProjeto ORDER BY p.dataComunicacao ASC");
            query.setParameter("idProjeto", idProjeto);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
