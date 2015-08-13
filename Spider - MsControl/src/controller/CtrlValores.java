package controller;

import facade.FacadeJpa;
import java.util.Date;
import java.util.List;
import model.Valorindicador;
import model.Valormedida;

/**
 *
 * @author Bleno Vale
 */
public class CtrlValores {

    FacadeJpa facadeJpa = FacadeJpa.getInstance();

    public List<Valormedida> buscarListaValorMedidaPorNomeEIdProjeto(String nome, int IdProjeto) {
        try {
            return facadeJpa.getValorMedidaJpa().findListValorMedidaBynameAndProjeto(nome, IdProjeto);
        } catch (Exception error) {
            throw error;
        }
    }

    public Valormedida buscarValorMedidaSelecionado(String nome, int IdProjeto) {
        try {
            return facadeJpa.getValorMedidaJpa().findValorMedidaBynameAndIdProjeto(nome, IdProjeto);
        } catch (Exception error) {
            throw error;
        }
    }

    public List<Valormedida> buscaValorMedidaPorDatas(String mnemonico, Date dataInicio, Date dataFim, int idProjeto) {
        try {
            return facadeJpa.getValorMedidaJpa().findValorMedidaByDataAndIdProjeto(mnemonico, dataInicio, dataFim, idProjeto);
        } catch (Exception error) {
            throw error;
        }
    }

    public boolean cadastraValorIndicador(Valorindicador valorindicador) {
        try {
            facadeJpa.getValorIndicadorJpa().create(valorindicador);
            return true;
        } catch (Exception error) {
            return false;
        }
    }

    public Valorindicador buscaUltimoValorIndicadorDoProjeto(String nomeIndicador, int idProjeto) {
        try {
            return facadeJpa.getValorIndicadorJpa().findLastValorIndicador(nomeIndicador, idProjeto);
        } catch (Exception error) {
            throw error;
        }
    }

    public List<Valorindicador> buscarValorIndicadorPorDatas(Date dataInicio, Date dataFim, int idIndicador, int idProjeto) {
        try {
            return facadeJpa.getValorIndicadorJpa().findValorIndicadorByDatasAndProjeto(dataInicio, dataFim, idIndicador, idProjeto);
        } catch (Exception error) {
            throw error;
        }
    }
}
