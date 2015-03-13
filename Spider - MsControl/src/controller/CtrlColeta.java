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

    public boolean cadastrarColeta(Coleta coleta) {
        try {
            facadeJpa.getColetaJpa().create(coleta);
            return true;
        } catch (Exception error) {
            error.printStackTrace();
            return false;
        }
    }
}
