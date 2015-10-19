/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.extensao;

import java.util.List;
import jpa.FuncionalidadeJpaController;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import model.Funcionalidade;

/**
 *
 * @author BlenoVale
 */
public class FuncionalidadeJpa extends FuncionalidadeJpaController {

    public FuncionalidadeJpa(EntityManagerFactory emf) {
        super(emf);
    }

    public Funcionalidade findByNome(String nomeFuncionalidade) {
        Funcionalidade funcionalidade = null;
        EntityManager emf = super.getEntityManager();
        Query q = emf.createQuery("SELECT f FROM Funcionalidade f WHERE f.nome = :nome");
        q.setParameter("nome", nomeFuncionalidade);
        funcionalidade = (Funcionalidade) q.getSingleResult();
        return funcionalidade;
    }
}
