package controller;

import facade.FacadeJpa;
import java.util.List;
import model.Indicador;

/**
 *
 * @author Dan
 */
public class CtrlIndicador {

    private final FacadeJpa facadeJpa = FacadeJpa.getInstance();
    
    public List<Indicador> findByParteNome(String nome){
        return facadeJpa.getIndicadorJpa().findByParteNome(nome);
    }
    
    public List<Indicador> getIndicadoresDoProjeto(int idDoProjeto){
        try{
            return facadeJpa.getIndicadorJpa().findListaIndicadoresByProjeto(idDoProjeto);
        }catch(Exception error){
            throw error;
        }
    }    
}
