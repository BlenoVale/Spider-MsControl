package jpa.extensao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import jpa.ColetaJpaController;
import model.Coleta;
import model.Medida;
import util.Conexao;

/**
 *
 * @author Géssica
 */
public class ColetaJpa extends ColetaJpaController {
    
    public ColetaJpa() {
        super(Conexao.conectar());
    }
    
    public List<Coleta> findListaColetaByProjeto(int idDoProjeto) {
        try {
            List<Coleta> lista = null;
            EntityManager emf = super.getEntityManager();
            Query q = emf.createQuery("SELECT i FROM Coleta i WHERE i.objetivoDeQuestaoid.objetivoDeMedicaoid.projetoid.id = :idDoProjeto ORDER By i.nome ASC")
                    .setParameter("idDoProjeto", idDoProjeto);

            lista = q.getResultList();
            return lista;
        } catch (Exception error) {
            throw error;
        }
    }
    
    public Medida findByNomeAndProjeto (String nome, int idProjeto){
        try {
            EntityManager entityManager = super.getEntityManager();
            return (Medida) entityManager.createQuery("SELECT i FROM Medida i WHERE i.nome = :nome AND i.indicadorid.objetivoDeQuestaoid.objetivoDeMedicaoid.projetoid.id = :idProjeto")
                    .setParameter("nome", nome)
                    .setParameter("idProjeto", idProjeto)
                    .getSingleResult();
        } catch (Exception error) {
            return null;
        }
    }
}
