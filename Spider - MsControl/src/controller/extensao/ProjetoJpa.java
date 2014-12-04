package controller.extensao;

import controller.ProjetoJpaController;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.Query;
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
    
    public boolean saveProjeto(JTextField nomeProjeto, JTextArea descricao){
        
        Projeto projeto = new Projeto();
        ProjetoJpa projetoJpa = new ProjetoJpa();
        
        
        projeto.setNome(nomeProjeto.getText());
        projeto.setDescricao(descricao.getText());
        projeto.setStatus(1);
        projeto.setDataInicio(new Date());
        
        try {
        
            projetoJpa.create(projeto);    
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
            return true;
            
        } catch (Exception er) {
            JOptionPane.showMessageDialog(null, "Error ao salvar!" + er);
            return false;
        }
        
    }
    
}
