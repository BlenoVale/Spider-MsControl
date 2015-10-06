
package jpa.extensao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.DatasprocedimentocoletaJpaController;
import model.Datasprocedimentocoleta;

/**
 *
 * @author Bleno Vale
 */
public class DatasProcedimentoColetaJpa extends DatasprocedimentocoletaJpaController {

    public DatasProcedimentoColetaJpa(EntityManagerFactory emf) {
        super(emf);
    }
    
    public List<Datasprocedimentocoleta> findDatasEmUso(int idProcedimento){
        try {
            EntityManager entityManager = super.getEntityManager();
            return entityManager.createQuery("SELECT d FROM Datasprocedimentocoleta d WHERE d.procedimentoDeColetaid.id = :idprocedimento AND d.emUso =:em_Uso")
                    .setParameter("idprocedimento", idProcedimento).setParameter("em_Uso", 1)
                    .getResultList();
        } catch (Exception error) {
            throw error; 
        }
    }
    
}
