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

    public List<Coleta> getColetaDaMedidaNaoUsadas(int idMedida) {
        try {
            return facadeJpa.getColetaJpa().findListaColetaByMedida(idMedida);
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

    public boolean editarColeta(Coleta coleta) {
        try {
            coleta.setUsado(true);
            facadeJpa.getColetaJpa().edit(coleta);
            System.out.println("Editado Com Sucesso.");
            return true;
        } catch (Exception error) {
            error.printStackTrace();
            return false;
        }
    }

}
