package controller.extensao;

import controller.PerfilJpaController;
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
}
