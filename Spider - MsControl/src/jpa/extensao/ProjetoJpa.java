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

    public List<Projeto> findTodosProjetosAtivos() {
        List<Projeto> projetoList = null;
        EntityManager emf = super.getEntityManager();
        Query q = emf.createQuery("SELECT p FROM Projeto p WHERE p.status = :status");
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

}
