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

    public List<Indicador> findByParteNome(String nome, int idProjeto) {
        return facadeJpa.getIndicadorJpa().findByParteNome(nome, idProjeto);
    }

    public List<Indicador> buscarParteDoNomeIndicador(String nome, int id_projeto) {
        try {
            return facadeJpa.getIndicadorJpa().findIndicadorByParteNome(nome, id_projeto);
        } catch (Exception error) {
            throw error;
        }
    }

    public List<Indicador> getIndicadoresDoProjeto(int idDoProjeto) {
        try {
            return facadeJpa.getIndicadorJpa().findListaIndicadoresByProjeto(idDoProjeto);
        } catch (Exception error) {
            throw error;
        }
    }
}
