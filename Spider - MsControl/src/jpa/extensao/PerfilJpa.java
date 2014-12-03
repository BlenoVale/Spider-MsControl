package jpa.extensao;

import jpa.PerfilJpaController;
import jpa.exceptions.NonexistentEntityException;
import java.awt.JobAttributes;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import model.Perfil;
import util.Conexao;

/**
 *
 * @author Dan
 */
public class PerfilJpa extends PerfilJpaController {

    public PerfilJpa() {
        super(Conexao.conectar());
    }

    public Perfil findByNome(String nomeProjeto) {
        Perfil perfil = null;
        EntityManager emf = super.getEntityManager();
        Query q = emf.createQuery("SELECT p FROM Perfil p WHERE p.nome = :nome");
        q.setParameter("nome", nomeProjeto);
        perfil = (Perfil) q.getSingleResult();
        return perfil;
    }

    public void inserirFuncionalidadesNoPerfil(Perfil perfil) {
        try {
            this.edit(perfil);
            JOptionPane.showMessageDialog(null, "Salvo com sucesso.");
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(PerfilJpa.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Não foi possivel salvar.");
        } catch (Exception ex) {
            Logger.getLogger(PerfilJpa.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Não foi possivel salvar.");
        } 
    }
}
