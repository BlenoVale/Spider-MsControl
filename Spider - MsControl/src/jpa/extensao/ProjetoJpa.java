package jpa.extensao;

import jpa.ProjetoJpaController;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.Projeto;
import util.Conexao;

/**
 *
 * @author Dan
 */
public class ProjetoJpa extends ProjetoJpaController {

    public ProjetoJpa() {
        super(Conexao.conectar());
    }

    public Projeto findByNome(String nomeProjeto) {
        Projeto projeto = null;
        EntityManager emf = super.getEntityManager();
        Query q = emf.createQuery("SELECT p FROM Projeto p WHERE p.nome = :nome");
        q.setParameter("nome", nomeProjeto);
        projeto = (Projeto) q.getSingleResult();
        return projeto;
    }

    public List<Projeto> findTodosProjetosAtivosOrderByNome() {
        List<Projeto> projetoList = null;
        EntityManager emf = super.getEntityManager();
        Query q = emf.createQuery("SELECT p FROM Projeto p WHERE p.status = :status ORDER BY p.nome ASC");
        q.setParameter("status", Projeto.ATIVO);
        projetoList = q.getResultList();
        return projetoList;
    }

    public List<Projeto> findTodosProjetosAtivosOrderByData() {
        List<Projeto> projetoList = null;
        EntityManager emf = super.getEntityManager();
        Query q = emf.createQuery("SELECT p FROM Projeto p WHERE p.status = :status ORDER BY p.dataInicio DESC");
        q.setParameter("status", Projeto.ATIVO);
        projetoList = q.getResultList();
        return projetoList;
    }

    public List<Projeto> findTodosProjetosInativos() {
        List<Projeto> projetoList = null;
        EntityManager emf = super.getEntityManager();
        Query q = emf.createQuery("SELECT p FROM Projeto p WHERE p.status = :status");
        q.setParameter("status", Projeto.INATIVO);
        projetoList = q.getResultList();
        return projetoList;
    }

    public List<Projeto> findTodosProjetosFinalizados() {
        List<Projeto> projetoList = null;
        EntityManager emf = super.getEntityManager();
        Query q = emf.createQuery("SELECT p FROM Projeto p WHERE p.status = :status");
        q.setParameter("status", Projeto.FINALIZADO);
        projetoList = q.getResultList();
        return projetoList;
    }

    public List<String> findTodosProjetosDistintosByUsuario(int id_usuario, int status) {
        try {
            EntityManager entityManager = super.getEntityManager();
            return entityManager.createQuery("SElECT DISTINCT a.projeto.nome From Acessa a  WHERE a.usuario.id =:id AND a.projeto.status =:status")
                    .setParameter("id", id_usuario).setParameter("status", status).getResultList();

        } catch (Exception error) {
            throw error;
        }
    }

}
