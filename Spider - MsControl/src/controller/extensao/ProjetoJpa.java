package controller.extensao;

import controller.ProjetoJpaController;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.Projeto;
import util.Conexao;

/**
 *
 * @author Dan
 */
public class ProjetoJpa extends  ProjetoJpaController {

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
    
}
