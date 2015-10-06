/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.extensao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import jpa.RegistroprojetoJpaController;
import model.Registroprocedimentocoleta;

/**
 *
 * @author Paulo
 */
public class RegistroProcedimentoColetaJpa extends RegistroprojetoJpaController {

    public RegistroProcedimentoColetaJpa(EntityManagerFactory emf) {
        super(emf);
    }

    public List<Registroprocedimentocoleta> findByIdProcedimento(int idProcedimento, int tipo){
        try {
        EntityManager entityManager = super.getEntityManager();
            Query query = entityManager.createQuery("SELECT r FROM Registroprocedimentocoleta r WHERE r.procedimentoDeColetaid.id = :idProcedimento AND r.tipo = :tipo");
            query.setParameter("idProcedimento", idProcedimento);
            query.setParameter("tipo", tipo);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
    }
}
