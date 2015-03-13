package controller;

import facade.FacadeJpa;
import java.util.List;
import model.Coleta;

/**
 *
 * @author Géssica
 */
public class CtrlColeta {
    private final FacadeJpa facadeJpa = FacadeJpa.getInstance();
    
    public List<Coleta> getColetaDoProjeto(int idDoProjeto) {
        try {
            return facadeJpa.getColetaJpa().findListaColetaByProjeto(idDoProjeto);
        } catch (Exception error) {
            throw error;
        }
    }   
}
