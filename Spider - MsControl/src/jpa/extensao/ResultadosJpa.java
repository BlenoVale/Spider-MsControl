package jpa.extensao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import jpa.ResultadosJpaController;
import model.Registroresultados;
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
            Query q = emf.createQuery("SELECT r FROM Resultados r WHERE r.projetoid.id = :idDoProjeto ORDER By r.titulo ASC")
                    .setParameter("idDoProjeto", idDoProjeto);

            lista = q.getResultList();
            return lista;
        } catch (Exception error) {
            throw error;
        }
    } 
    
    public Resultados findBYTituloAndProjeto (String titulo, int idProjeto){
        try {
            EntityManager entityManager = super.getEntityManager();
            return (Resultados) entityManager.createQuery("SELECT r FROM Resultados r WHERE r.titulo = :titulo AND r.projetoid.id = :idProjeto")
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
                + "WHERE r.projetoid.id = :idProjeto "
                + "AND r.titulo = :titulo "
                + "ORDER By r.titulo ASC");

        q.setParameter("titulo", titulo);
        q.setParameter("idProjeto", idProjeto);

        resultados = (Resultados) q.getSingleResult();
        return resultados;
    }
    
    public long countResultadosByProjeto(int idProjeto){
        try {
            EntityManager entityManager = super.getEntityManager();
            return (long) entityManager.createQuery("SELECT COUNT(r.id) FROM Resultados r WHERE r.projetoid.id = :idProjeto")
                    .setParameter("idProjeto", idProjeto)
                    .getSingleResult();
        } catch (Exception error) {
            throw error;
        }
    }
    
    public List<Registroresultados> findRegistrosDoResultadoByTipo(int idResultados, int tipo) {

        List<Registroresultados> registroresultados = new ArrayList<>();

        EntityManager emf = super.getEntityManager();

        Query q = emf.createQuery("SELECT r FROM Registroresultados r WHERE r.resultadosid.id = :idResultados and r.tipo = :tipo ORDER BY r.id DESC");

        q.setParameter("idResultados", idResultados);
        q.setParameter("tipo", tipo);
        registroresultados = q.getResultList();
        return registroresultados;
    }
}
