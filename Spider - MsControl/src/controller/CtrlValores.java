
package controller;

import facade.FacadeJpa;
import java.util.List;
import model.Valormedida;

/**
 *
 * @author Bleno Vale
 */
public class CtrlValores {
    
    FacadeJpa facadeJpa = FacadeJpa.getInstance();
    
    public List<Valormedida> buscarListaValorMedidaPorNomeEIdProjeto (String nome, int IdProjeto){
        try {
            return facadeJpa.getValorMedidaJpa().findListValorMedidaBynameAndProjeto(nome, IdProjeto);
        } catch (Exception error) {
            throw error;
        }
    }
    
    public Valormedida buscarValorMedidaSelecionado(String nome, int IdProjeto){
        try {
            return facadeJpa.getValorMedidaJpa().findValorMedidaBynameAndIdProjeto(nome, IdProjeto);
        } catch (Exception error) {
            throw error;
        }
    }
}
