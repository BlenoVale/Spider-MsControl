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
    public  List<Medida> getListMedidaProjeto(int idProjeto){
        try {
            EntityManager entityManager = super.getEntityManager();
            Query query = entityManager.createQuery("SELECT m FROM Medida m WHERE m.projetoId = :idProjeto ORDER BY ASC");
            query.setParameter("idProjeto", idProjeto);
            return query.getResultList();
        } catch (Exception e) {
            return  null;
        }
    }
    
    
    public Medida findByNomeAndProjeto(String nomeMedida, int idProjeto){
        Medida medida = null;
        try {
            EntityManager entityManager = super.getEntityManager();
            Query query = entityManager.createQuery("SELECT m FROM Medida m WHERE m.nome = :nomeMedida AND m.projetoId = :idProjeto");
            query.setParameter("nomeMedida", nomeMedida);
            query.setParameter("idProjeto", idProjeto);
            medida = (Medida) query.getSingleResult();
            return medida;
        } catch (Exception e) {
            return null;
        }
    }
     public Medida findByNomeAndProjetoDiferente(String nomeMedida, int idProjeto, int id){
        Medida medida = null;
        try {
            EntityManager entityManager = super.getEntityManager();
            Query query = entityManager.createQuery("SELECT m FROM Medida m WHERE m.nome = :nome AND m.projetoId = :idProjeto AND m.id != :id");
            query.setParameter("nome", nomeMedida);
            query.setParameter("idProjeto", idProjeto);
            query.setParameter("id", id);
            medida = (Medida) query.getSingleResult();
            return medida;
        } catch (Exception e) {
            return null;
        }
    }
    public Medida findByMnemonicoAndProjetoDiferente(String mnemonico, int idProjeto, int id){
        Medida medida = null;
        try {
            EntityManager entityManager = super.getEntityManager();
            Query query = entityManager.createQuery("SELECT m FROM Medida m WHERE m.mnemonico = :mnemonico AND m.projetoId = :idProjeto AND m.id != :id");
            query.setParameter("mnemonico", mnemonico);
            query.setParameter("idProjeto", idProjeto);
            query.setParameter("id", id);
            medida = (Medida) query.getSingleResult();
            return medida;
        } catch (Exception e) {
            return null;
        }
    }
    public Medida findByMnemonicoAndProjeto(String nomeMnemonico, int idProjeto){
        Medida medida = null;
        try {
            EntityManager entityManager = super.getEntityManager();
            Query query = entityManager.createQuery("SELECT m FROM Medida m WHERE m.mnemonico = :nomeMnemonico AND m.projetoId = :idProjeto");
            query.setParameter("nomeMnemonico", nomeMnemonico);
            query.setParameter("idProjeto", idProjeto);
            medida = (Medida) query.getSingleResult();
            return medida;
        } catch (Exception e) {
            return null;
        }
    }
    public List<Medida> findByProjeto (int idProjeto){
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
    public String findNomeByProjeto(int idMedida, int idProjeto){
        try {
            EntityManager  entityManager = super.getEntityManager();
            Query query = entityManager.createQuery("SELECT m.nome FROM Medida m WHERE m.id = :idMedida AND m.projetoId = :idProjeto");
            query.setParameter("idMedida", idMedida);
            query.setParameter("idProjeto", idProjeto);
            return (String) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return  null;
        }
    }
    
      public List<Medida> findNomeByProjetoAndNomeMedida(String nomeMedida, int idProjeto){
        try {
            EntityManager  entityManager = super.getEntityManager();
            Query query = entityManager.createQuery("SELECT m.nome,m.id FROM Medida m WHERE m.nome = :nomeMedida AND m.projetoId = :idProjeto");
            query.setParameter("nomeMedida", nomeMedida);
            query.setParameter("idProjeto", idProjeto);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return  null;
        }
    }
    
    

}
