package jpa.extensao;

import java.util.List;
import jpa.UsuarioJpaController;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import model.Usuario;

/**
 *
 * @author Dan, BlenoVale
 */
public class UsuarioJpa extends UsuarioJpaController {

    public UsuarioJpa(EntityManagerFactory emf) {
        super(emf);
    }

    public Usuario findByNome(String nomeUsuario) {
        Usuario usuario = null;
        EntityManager emf = super.getEntityManager();
        Query q = emf.createQuery("SELECT u FROM Usuario u WHERE u.nome = :nome");
        q.setParameter("nome", nomeUsuario);
        usuario = (Usuario) q.getSingleResult();
        return usuario;
    }

    public String findCountProjetosByIdUsuario(int idUsuario) {
        EntityManager emf = super.getEntityManager();
        Query q = emf.createQuery("SELECT count(DISTINCT a.acessaPK.projetoid) FROM Acessa a WHERE a.acessaPK.usuarioid = :id");
        q.setParameter("id", idUsuario);
        return q.getSingleResult().toString();
    }

    public String findCountPerfilByIdUsuario(int idUsuario) {
        EntityManager emf = super.getEntityManager();
        Query q = emf.createQuery("SELECT count(a.acessaPK.perfilid) FROM Acessa a WHERE a.acessaPK.usuarioid = :id");
        q.setParameter("id", idUsuario);
        return q.getSingleResult().toString();
    }

    public List<Usuario> findByParteNome(String nomeUsuario) {
        List<Usuario> usuarioList = null;
        EntityManager emf = super.getEntityManager();
        Query q = emf.createQuery("SELECT u FROM Usuario u WHERE u.nome LIKE :nome ORDER By u.nome ASC");
        q.setParameter("nome", nomeUsuario + "%");
        usuarioList = q.getResultList();
        return usuarioList;
    }

    public List<Usuario> findAllUsuarios() {
        List<Usuario> usuarioList = null;
        EntityManager emf = super.getEntityManager();
        Query q = emf.createQuery("SELECT u FROM Usuario u ORDER By u.nome ASC");
        usuarioList = q.getResultList();
        return usuarioList;
    }

    public Usuario findByLogin(String login) {
        EntityManager emf = super.getEntityManager();
        Query q = emf.createQuery("SELECT u FROM Usuario u WHERE u.login = :login");
        q.setParameter("login", login);
        try {
            return (Usuario) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public Usuario findByNomeELogin(String nome, String login) {
        Usuario usuario = null;
        EntityManager emf = super.getEntityManager();
        Query q = emf.createQuery("SELECT u FROM Usuario u WHERE u.nome = :nome AND u.login = :login");
        q.setParameter("nome", nome);
        q.setParameter("login", login);
        usuario = (Usuario) q.getSingleResult();
        return usuario;
    }

    public List<Usuario> selectNomeLoginUser() {

        List<Usuario> listUsuario = null;
        EntityManager emf = super.getEntityManager();
        Query q = emf.createQuery("SELECT u FROM Usuario u");
        listUsuario = q.getResultList();
        return listUsuario;
    }

    public List<Usuario> findUsuarioByEmail(String email) {
        try {
            EntityManager entityManager = super.getEntityManager();
            return entityManager.createQuery("SELECT u FROM Usuario u WHERE u.email = :email").setParameter("email", email).getResultList();
        } catch (Exception error) {
            throw error;
        }
    }
}
