package jpa.extensao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import jpa.IndicadorJpaController;
import model.Indicador;
import model.Registroindicador;
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
        Query q = emf.createQuery("SELECT i "
                + "FROM Indicador i "
                + "WHERE i.objetivoDeQuestaoid.objetivoDeMedicaoid.projetoid.id = :idProjeto "
                + "AND i.nome LIKE :nome "
                + "ORDER By i.nome ASC");

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

    public Indicador findByNomeAndMnemonico(String nome, String mnemonico, int idProjeto) {

        Indicador indicador = null;

        EntityManager emf = super.getEntityManager();

        Query q = emf.createQuery("SELECT i "
                + "FROM Indicador i "
                + "WHERE i.objetivoDeQuestaoid.objetivoDeMedicaoid.projetoid.id = :idProjeto "
                + "AND i.mnemonico = :mnemonico "
                + "AND i.nome = :nome "
                + "ORDER By i.nome ASC");

        q.setParameter("nome", nome);
        q.setParameter("mnemonico", mnemonico);
        q.setParameter("idProjeto", idProjeto);

        indicador = (Indicador) q.getSingleResult();
        return indicador;
    }

    public List<Registroindicador> findRegistrosDoIndicadorByTipo(int idIndicador, int tipo) {

        List<Registroindicador> registroIndicador = new ArrayList<>();

        EntityManager emf = super.getEntityManager();

        Query q = emf.createQuery("SELECT r FROM Registroindicador r WHERE r.indicadorid.id = :idIndicador and r.tipo = :tipo ORDER BY r.id DESC");

        q.setParameter("idIndicador", idIndicador);
        q.setParameter("tipo", tipo);
        registroIndicador = q.getResultList();
        return registroIndicador;
    }

}
