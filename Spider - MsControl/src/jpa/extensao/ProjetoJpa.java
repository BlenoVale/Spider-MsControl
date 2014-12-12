package jpa.extensao;

import jpa.ProjetoJpaController;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.RollbackException;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
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
