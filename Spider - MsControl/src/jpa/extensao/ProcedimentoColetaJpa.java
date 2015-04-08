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
            Query query = entityManager.createQuery("SELECT p FROM Procedimentodecoleta p WHERE p.projetoId = :idProjeto ");
            query.setParameter("idProjeto", idProjeto);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
        
    }
    
    public Procedimentodecoleta findByProjeto(int idMedida, int idProjeto){
        
        try {
            EntityManager entityManager = super.getEntityManager();
            Query query = entityManager.createQuery("SELECT p FROM Procedimentodecoleta p WHERE p.medidaid.id = :idMedida AND p.projetoId = :idProjeto");
            query.setParameter("idMedida", idMedida);
            query.setParameter("idProjeto", idProjeto);
            return (Procedimentodecoleta) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
        
    }
    
}
