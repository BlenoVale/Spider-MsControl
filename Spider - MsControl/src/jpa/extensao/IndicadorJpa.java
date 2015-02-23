package jpa.extensao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import jpa.IndicadorJpaController;
import model.Indicador;
import util.Conexao;

/**
 *
 * @author Dan
 */
public class IndicadorJpa extends IndicadorJpaController {

    public IndicadorJpa() {
        super(Conexao.conectar());
    }

    public List<Indicador> findByParteNome(String nome) {

        List<Indicador> lista = null;
        
        EntityManager emf = super.getEntityManager();
        Query q = emf.createQuery("SELECT i FROM Indicador i WHERE i.nome LIKE :nome ORDER By i.nome ASC");
        q.setParameter("nome", nome + "%");

        lista = q.getResultList();
        return lista;
    }
    
    public List<Indicador> findListaIndicadoresByProjeto(int idDoProjeto){
        try{
            List<Indicador> lista = null;
            EntityManager emf = super.getEntityManager();
            Query q = emf.createQuery("SELECT i FROM Indicador i WHERE i.objetivoDeQuestaoid.objetivoDeMedicaoid.projetoid.id = :idDoProjeto ORDER By i.prioridade ASC")
            .setParameter("idDoProjeto", idDoProjeto);
            
            lista = q.getResultList();
            return lista;
        }catch(Exception error){
            throw error;
        }
    }

}
