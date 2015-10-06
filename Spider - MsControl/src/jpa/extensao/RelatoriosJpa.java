package jpa.extensao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import jpa.RelatoriosJpaController;
import model.Relatorios;

/**
 *
 * @author Gessica
 */
public class RelatoriosJpa extends RelatoriosJpaController {
    public RelatoriosJpa(EntityManagerFactory emf){
        super(emf);
    }
    
    public List<Relatorios> getListByProjeto(int idProjeto){
        
        try {
            EntityManager entityManager = super.getEntityManager();
            Query query = entityManager.createQuery("SELECT r FROM Relatorios r WHERE r.projetoid.id = :idProjeto");
            query.setParameter("idProjeto", idProjeto);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<Relatorios> findListaRelatoriosByProjeto(int idProjeto) {
        try {
            List<Relatorios> lista = null;
            EntityManager emf = super.getEntityManager();
            Query q = emf.createQuery("SELECT r FROM Relatorios r WHERE r.projetoid.id = :idProjeto")
                    .setParameter("idProjeto", idProjeto);

            lista = q.getResultList();
            return lista;
        } catch (Exception error) {
            throw error;
        }
    }
}