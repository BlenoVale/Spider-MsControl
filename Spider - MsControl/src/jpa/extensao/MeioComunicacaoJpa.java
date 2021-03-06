/* * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.extensao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import jpa.MeioscomunicacaoJpaController;
import model.Meioscomunicacao;

/**
 *
 * @author Paulo
 */
public class MeioComunicacaoJpa extends MeioscomunicacaoJpaController {

    public MeioComunicacaoJpa(EntityManagerFactory emf) {
        super(emf);
    }

    public List<Meioscomunicacao> findAll() {
        try {
            EntityManager entityManager = super.getEntityManager();
            Query query = entityManager.createQuery("SELECT m FROM Meioscomunicacao m ORDER BY m.nome ASC");
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Meioscomunicacao findByName(String nome) {
        try {
            EntityManager entityManager = super.getEntityManager();
            Query query = entityManager.createQuery("SELECT m FROM Meioscomunicacao m WHERE m.nome =:nome");
            query.setParameter("nome", nome);
            return (Meioscomunicacao) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Meioscomunicacao findById(int idMeio) {
        try {
            EntityManager entityManager = super.getEntityManager();
            Query query = entityManager.createQuery("SELECT m FROM Meioscomunicacao m WHERE m.id =:idMeio");
            query.setParameter("idMeio", idMeio);
            return (Meioscomunicacao) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
