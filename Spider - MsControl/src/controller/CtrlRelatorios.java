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
    
    public List<Relatorios> getRelatoriosDoProjeto(int idDoProjeto) {
        try {
            return facadeJpa.getRelatoriosJpa().findListaRelatoriosByProjeto(idDoProjeto);
        } catch (Exception error) {
            throw error;
        }
    }
    
}
