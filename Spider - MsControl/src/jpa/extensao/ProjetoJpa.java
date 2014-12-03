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
    
    public boolean saveProjeto(String  nomeProjeto, String descricao){
        
        Projeto projeto = new Projeto();
        ProjetoJpa projetoJpa = new ProjetoJpa();
        
        
        projeto.setNome(nomeProjeto);
        projeto.setDescricao(descricao);
        projeto.setStatus(projeto.ATIVO);
        projeto.setDataInicio(new Date());
        
        try {
        
            projetoJpa.create(projeto);    
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
            return true;
            
        } catch (RollbackException error){
            JOptionPane.showMessageDialog(null, "Esse nome de projeto já existe! Por favor, escolha outro nome.", "", JOptionPane.ERROR_MESSAGE);
            return false;
        }catch (Exception er) {
            JOptionPane.showMessageDialog(null, "Error ao salvar!", "", JOptionPane.ERROR_MESSAGE);
            return false;
        } 
        
    }
    
}
