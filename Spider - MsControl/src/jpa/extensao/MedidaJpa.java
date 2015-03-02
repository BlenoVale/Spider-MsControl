/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.extensao;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.Medida;
import util.Conexao;
import jpa.MedidaJpaController;

/**
 *
 * @author Paulo
 */
public class MedidaJpa extends MedidaJpaController {

    public MedidaJpa() {
        super(Conexao.conectar());
    }

    public Medida findByNome(String nomeMedicao) {
        Medida medida = null;
        try {
            EntityManager entityManager = super.getEntityManager();
            Query query = entityManager.createQuery("SELECT u FROM Medida u WHERE u.nome = :nomeMedicao");
            query.setParameter("nomoMedicao", nomeMedicao);
            medida = (Medida) query.getResultList();
            return medida;
        } catch (Exception e) {
            return null;
        }

    }

}
