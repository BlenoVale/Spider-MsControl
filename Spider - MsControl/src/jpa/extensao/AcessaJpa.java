/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.extensao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import jpa.AcessaJpaController;
import model.Acessa;
import util.Conexao;

/**
 *
 * @author Spider-02
 */
public class AcessaJpa extends AcessaJpaController {

    public AcessaJpa() {
        super(Conexao.conectar());
    }

    public List<Acessa> findAcessaByIdUsuario(int idUsuario) {
        List<Acessa> lista = null;
        EntityManager emf = super.getEntityManager();
        Query q = emf.createQuery("SELECT a FROM Acessa a WHERE a.acessaPK.usuarioid = :idUsuario")
                .setParameter("idUsuario", idUsuario);

        lista = q.getResultList();
        return lista;
    }

}
