package controller.extensao;

import controller.PerfilJpaController;
import controller.exceptions.NonexistentEntityException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;
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
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(PerfilJpa.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(PerfilJpa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
