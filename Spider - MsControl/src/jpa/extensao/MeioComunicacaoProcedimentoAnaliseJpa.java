/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.extensao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import jpa.MeiosprocedimentoanaliseJpaController;
import model.Meiosprocedimentoanalise;
import util.Conexao;

/**
 *
 * @author paulosouza
 */
public class MeioComunicacaoProcedimentoAnaliseJpa extends MeiosprocedimentoanaliseJpaController {

    public MeioComunicacaoProcedimentoAnaliseJpa() {
        super(Conexao.conectar());
    }

    public List<Meiosprocedimentoanalise> findByProcedimentoAnalise(int idProcedimentoAnalise) {
        try {
            EntityManager entityManager = getEntityManager();
            Query query = entityManager.createQuery("SELECT m FROM Meiosprocedimentoanalise m WHERE m.procedimentoDeAnaliseid.id =:idProcedimentoAnalise");
            query.setParameter("idProcedimentoAnalise", idProcedimentoAnalise);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
