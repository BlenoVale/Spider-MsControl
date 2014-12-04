/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller.extensao;

import controller.FuncionalidadeJpaController;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.Funcionalidade;
import util.Conexao;

/**
 *
 * @author BlenoVale
 */
public class FuncionalidadeJpa extends FuncionalidadeJpaController{
    
    public FuncionalidadeJpa (){
        super(Conexao.conectar());
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