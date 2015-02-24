package jpa.extensao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import jpa.IndicadorJpaController;
import model.Indicador;
import util.Conexao;

/**
 *
 * @author Dan
 */
public class IndicadorJpa extends IndicadorJpaController {

    public IndicadorJpa() {
        super(Conexao.conectar());
    }

    public List<Indicador> findByParteNome(String nome, int idProjeto) {

        List<Indicador> lista = null;

        EntityManager emf = super.getEntityManager();
        Query q = emf.createQuery("SELECT i FROM Indicador i WHERE "
                + "i.objetivoDeQuestaoid.objetivoDeMedicaoid.projetoid.id = :idProjeto AND "
                + "i.nome LIKE :nome ORDER By i.nome ASC");
        q.setParameter("nome", nome + "%");
        q.setParameter("idProjeto", idProjeto);

        lista = q.getResultList();
        return lista;
    }

    public List<Indicador> findIndicadorByParteNome(String nome, int id_projeto) {
        try {
            List<Indicador> lista = null;
            EntityManager emf = super.getEntityManager();
            Query q = emf.createQuery("SELECT i FROM Indicador i WHERE i.objetivoDeQuestaoid.objetivoDeMedicaoid.projetoid.id = :idDoProjeto AND i.nome LIKE :nome ORDER By i.prioridade ASC")
                    .setParameter("idDoProjeto", id_projeto).setParameter("nome", nome + "%");

            lista = q.getResultList();
            return lista;
        } catch (Exception error) {
            throw error;
        }
    }

    public List<Indicador> findListaIndicadoresByProjeto(int idDoProjeto) {
        try {
            List<Indicador> lista = null;
            EntityManager emf = super.getEntityManager();
            Query q = emf.createQuery("SELECT i FROM Indicador i WHERE i.objetivoDeQuestaoid.objetivoDeMedicaoid.projetoid.id = :idDoProjeto ORDER By i.prioridade ASC")
                    .setParameter("idDoProjeto", idDoProjeto);

            lista = q.getResultList();
            return lista;
        } catch (Exception error) {
            throw error;
        }
    }

}
