/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.extensao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import jpa.PerfilinteressadoJpaController;
import model.Perfilinteressado;
import util.Conexao;

/**
 *
 * @author Paulo
 */
public class PerfilInteressadoJpa extends PerfilinteressadoJpaController {

    public PerfilInteressadoJpa() {
        super(Conexao.conectar());
    }

    public List<Perfilinteressado> findAll() {
        try {
            EntityManager entityManager = super.getEntityManager();
            Query query = entityManager.createQuery("SELECT p FROM Perfilinteressado p ORDER BY p.nome ASC");
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Perfilinteressado findByName(String nome) {
        try {
            EntityManager entityManager = getEntityManager();
            Query query = entityManager.createQuery("SELECT p FROM Perfilinteressado p WHERE p.nome =:nome");
            query.setParameter("nome", nome);
            return (Perfilinteressado) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
