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
}
