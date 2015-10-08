package jpa.extensao;

import jpa.PerfilJpaController;
import jpa.exceptions.NonexistentEntityException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import model.Perfil;

/**
 *
 * @author Bleno Vale
 */
public class PerfilJpa extends PerfilJpaController {

    public PerfilJpa(EntityManagerFactory emf) {
        super(emf);
    }

    public Perfil findByNome(String nomeProjeto) {
        Perfil perfil = null;
        EntityManager emf = super.getEntityManager();
        Query q = emf.createQuery("Select p From Perfil p WHERE p.nome = :nome").setParameter("nome", nomeProjeto);
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
