/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.extensao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import jpa.RegistroprocedimentoanaliseJpaController;
import model.Registroprocedimentoanalise;
import util.Conexao;

/**
 *
 * @author Paulo
 */
public class RegistroProcedimentoAnalise extends RegistroprocedimentoanaliseJpaController {

    public RegistroProcedimentoAnalise() {
        super(Conexao.conectar());
    }

    public List<Registroprocedimentoanalise> findAllRegistros(int idProcedimento, int tipo) {

        try {
            EntityManager entityManager = getEntityManager();
            Query query = entityManager.createQuery("SELECT r FROM Registroprocedimentoanalise r WHERE r.procedimentoDeAnaliseid.id =:idProcedimento AND r.tipo =:tipo");
            query.setParameter("idProcedimento", idProcedimento);
            query.setParameter("tipo", tipo);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
