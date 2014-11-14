/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.extensao;

import controller.UsuarioJpaController;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.Usuario;
import util.Conexao;

/**
 *
 * @author Dan
 */
public class UsuarioJpa extends UsuarioJpaController {

    public UsuarioJpa() {
        super(Conexao.conectar());
    }

    public Usuario findByNome(String nomeUsuario) {
        Usuario usuario = null;
        EntityManager emf = super.getEntityManager();
        Query q = emf.createQuery("SELECT u FROM Usuario u WHERE u.nome = :nome");
        q.setParameter("nome", nomeUsuario);
        usuario = (Usuario) q.getSingleResult();
        return usuario;
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

}
