/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.extensao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.Medida;
import util.Conexao;
import jpa.MedidaJpaController;
import model.Medida_;

/**
 *
 * @author Paulo
 */
public class MedidaJpa extends MedidaJpaController {

    public MedidaJpa() {
        super(Conexao.conectar());
    }

    public List<Medida> findByNome(String nomeMedicao, int idProjeto) {
       
        List<Medida> listMedida = null;
        try {
            EntityManager entityManager = super.getEntityManager();
            Query query = entityManager.createQuery("SELECT u FROM Medida u WHERE u.projetoId = :idProjeto AND U.nome LIKE :nome ORDER BY u.nome ASC");
            query.setParameter("nome", nomeMedicao + "%");
            query.setParameter("idProjeto", idProjeto);
            listMedida = query.getResultList();
            return listMedida;
        } catch (Exception e) {
            return null;
        }

    }
    public Medida findByNomeSingle(String nomeMedida){
        Medida medida = null;
        try {
            EntityManager entityManager = super.getEntityManager();
            Query query = entityManager.createQuery("SELECT m FROM Medida m WHERE m.nome LIKE :nome");
            query.setParameter("nome", nomeMedida + "%");
            medida = (Medida) query.getSingleResult();
            return medida;
        } catch (Exception e) {
            return null;
                    
        }
    }
    public List<Medida> findMedidaByProjeto (int idProjeto){
        List<Medida> listMedida = null;
        try {
            EntityManager entityManager = super.getEntityManager();
            Query query = entityManager.createQuery("SELECT m FROM Medida m WHERE m.projetoId = :idProjeto ORDER BY m.nome ASC");
            query.setParameter("idProjeto", idProjeto);
            listMedida = query.getResultList();
            return listMedida;
        } catch (Exception e) {
            return  null;
        }
    }

}
