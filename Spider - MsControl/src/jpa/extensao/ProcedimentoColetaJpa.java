/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.extensao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import util.Conexao;
import jpa.ProcedimentodecoletaJpaController;
import model.Procedimentodecoleta;
import model.Medida;

/**
 *
 * @author Paulo
 */
public class ProcedimentoColetaJpa extends ProcedimentodecoletaJpaController{
    
    public ProcedimentoColetaJpa(){
        super(Conexao.conectar());
    }
    
    public List<Procedimentodecoleta> getListByProjeto(int idProjeto){
        
        try {
            EntityManager entityManager = super.getEntityManager();
            Query query = entityManager.createQuery("SELECT p FROM Procedimentodecoleta p, Medida m WHERE m.projetoId = :idProjeto ");
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
        
    }
    
}
