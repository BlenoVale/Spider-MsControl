package jpa.extensao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import jpa.ProcedimentodeanaliseJpaController;
import model.Procedimentodeanalise;

/**
 *
 * @author Géssica
 */
public class ProcedimentoDeAnaliseJpa extends ProcedimentodeanaliseJpaController {

    public ProcedimentoDeAnaliseJpa(EntityManagerFactory emf) {
        super(emf);
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

    public Procedimentodeanalise findAllByIndicador(int indicador) {
        try {
            EntityManager entityManager = getEntityManager();
            Query query = entityManager.createQuery("SELECT p FROM Procedimentodeanalise p WHERE p.indicadorid.id =:indicador ORDER BY p.dataComunicacao ASC");
            query.setParameter("indicador", indicador);
            return (Procedimentodeanalise) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
