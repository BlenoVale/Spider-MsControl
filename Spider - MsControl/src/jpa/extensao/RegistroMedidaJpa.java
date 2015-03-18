/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.extensao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import jpa.RegistromedidaJpaController;
import util.Conexao;
import model.Registromedida;

/**
 *
 * @author Paulo
 */
    public class RegistroMedidaJpa extends RegistromedidaJpaController {
    
        public RegistroMedidaJpa(){
            super(Conexao.conectar());
        }
    public List<Registromedida> findRegistroByIdMedida(int tipo, int idMedida){
        
        
        try {
            EntityManager entityManager = super.getEntityManager();
            Query query;
            query = entityManager.createQuery("SELECT r FROM Registromedida r WHERE r.tipo = :tipo AND r.medidaid.id = :idMedida");
            query.setParameter("tipo", tipo);
            query.setParameter("idMedida", idMedida);
            return query.getResultList();
            
        } catch (Exception error) {
            throw (error);
        }
    }
}
