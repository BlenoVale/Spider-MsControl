package controller;

import facade.FacadeJpa;
import java.util.List;
import model.Relatorios;

/**
 *
 * @author Gessica
 */
public class CtrlRelatorios {
    
    private final FacadeJpa facadeJpa = FacadeJpa.getInstance();
    
    public List<Relatorios> getPlanosDoProjeto(int idDoProjeto) {
        try {
            return facadeJpa.getRelatoriosJpa().findListaPlanosByProjeto(idDoProjeto);
        } catch (Exception error) {
            throw error;
        }
    }    
    
    public boolean cadastrarRelatorio(Relatorios relatorios) {
        try {
            facadeJpa.getRelatoriosJpa().create(relatorios);
            return true;
        } catch (Exception error) {
            error.printStackTrace();
            return false;
        }
    }
    
}
