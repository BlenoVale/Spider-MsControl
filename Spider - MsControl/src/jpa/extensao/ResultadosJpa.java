package jpa.extensao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import jpa.ResultadosJpaController;
import model.Resultados;
import util.Conexao;

/**
 *
 * @author Géssica
 */
public class ResultadosJpa extends ResultadosJpaController {

    public ResultadosJpa() {
        super(Conexao.conectar());
    }

    public List<Resultados> findListaResultadosByProjeto(int idDoProjeto) {
        try {
            List<Resultados> lista = null;
            EntityManager emf = super.getEntityManager();
            Query q = emf.createQuery("SELECT r FROM Resultados r WHERE r.analiseId.indicadorid.objetivoDeQuestaoid.objetivoDeMedicaoid.projetoid.id = :idDoProjeto ORDER By r.titulo ASC")
                    .setParameter("idDoProjeto", idDoProjeto);

            lista = q.getResultList();
            return lista;
        } catch (Exception error) {
            throw error;
        }
    }

    public Resultados findBYTituloAndProjeto(String titulo, int idProjeto) {
        try {
            EntityManager entityManager = super.getEntityManager();
            return (Resultados) entityManager.createQuery("SELECT r FROM Resultados r WHERE r.titulo = :titulo AND r.idProjeto = :idProjeto")
                    .setParameter("titulo", titulo)
                    .setParameter("idProjeto", idProjeto)
                    .getSingleResult();
        } catch (Exception error) {
            return null;
        }
    }

    public Resultados findByTitulo(String titulo, int idProjeto) {

        Resultados resultados = null;

        EntityManager emf = super.getEntityManager();

        Query q = emf.createQuery("SELECT r "
                + "FROM Resultados r "
                + "WHERE r.analiseId.indicadorid.objetivoDeQuestaoid.objetivoDeMedicaoid.projetoid.id = :idDoProjeto "
                + "AND r.titulo = :titulo "
                + "ORDER By r.titulo ASC");

        q.setParameter("titulo", titulo);
        q.setParameter("idProjeto", idProjeto);

        resultados = (Resultados) q.getSingleResult();
        return resultados;
    }

    public List<Resultados> findResultadoByParteTitulo(String titulo, int id_projeto) {
        try {
            List<Resultados> lista = null;
            EntityManager emf = super.getEntityManager();
            Query q = emf.createQuery("SELECT r FROM Resultados r WHERE r.analiseId.indicadorid.objetivoDeQuestaoid.objetivoDeMedicaoid.projetoid.id = :idDoProjeto AND r.titulo LIKE :titulo ORDER BY r.titulo ASC")
                    .setParameter("idDoProjeto", id_projeto).setParameter("titulo", titulo + "%");

            lista = q.getResultList();
            return lista;
        } catch (Exception error) {
            throw error;
        }
    }

    public long countResultadosByProjeto(int idProjeto) {
        try {
            EntityManager entityManager = super.getEntityManager();
            return (long) entityManager.createQuery("SELECT COUNT(r.id) FROM Resultados r WHERE r.analiseId.indicadorid.objetivoDeQuestaoid.objetivoDeMedicaoid.projetoid.id = :idDoProjeto")
                    .setParameter("idProjeto", idProjeto)
                    .getSingleResult();
        } catch (Exception error) {
            throw error;
        }
    }
}
