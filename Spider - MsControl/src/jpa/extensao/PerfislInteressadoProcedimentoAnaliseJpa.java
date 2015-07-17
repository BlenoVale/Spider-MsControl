/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.extensao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import jpa.PerfisinteressadosprocedimentoanaliseJpaController;
import model.Perfisinteressadosprocedimentoanalise;
import util.Conexao;

/**
 *
 * @author paulosouza
 */
public class PerfislInteressadoProcedimentoAnaliseJpa extends PerfisinteressadosprocedimentoanaliseJpaController {

    public PerfislInteressadoProcedimentoAnaliseJpa() {
        super(Conexao.conectar());
    }

    public List<Perfisinteressadosprocedimentoanalise> findByIdProcedimentoAnalise(int idProcedimentoAnalise) {
        try {
            EntityManager entityManager = getEntityManager();
            Query query = entityManager.createQuery("SELECT p FROM Perfisinteressadosprocedimentoanalise p WHERE p.procedimentoDeAnaliseid.id =:idProcedimentoAnalise");
            query.setParameter("idProcedimentoAnalise", idProcedimentoAnalise);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Perfisinteressadosprocedimentoanalise findByPerfilInteressado(int idPerfilInteressado) {
        try {
            EntityManager entityManager = getEntityManager();
            Query query = entityManager.createQuery("SELECT p FROM Perfisinteressadosprocedimentoanalise p WHERE p.idperfilInteressado =:idPerfilInteressado");
            query.setParameter("idPerfilInteressado", idPerfilInteressado);
            return (Perfisinteressadosprocedimentoanalise) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
