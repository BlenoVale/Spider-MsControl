/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import facade.FacadeJpa;
import java.util.Date;
import java.util.List;
import model.Procedimentodeanalise;
import model.Registroprocedimentoanalise;
import util.Constantes;
import util.Copia;

/**
 *
 * @author Géssica
 */
public class CtrlProcedimentoDeAnalise {

    private final FacadeJpa facadeJpa = FacadeJpa.getInstance();
    CtrlRegistroDataComunicacao ctrlRegistroDataComunicacao = new CtrlRegistroDataComunicacao();
    CtrlMeioComunicacao ctrlMeioComunicacao = new CtrlMeioComunicacao();
    CtrlPerfilInteressado ctrlPerfilInteressado = new CtrlPerfilInteressado();

    public List<Procedimentodeanalise> getIndicadoresDoProjeto(int idDoProjeto) {
        try {
            return facadeJpa.getProcedimentodeanaliseJpa().findListaIndicadoresByProjeto(idDoProjeto);
        } catch (Exception error) {
            throw error;
        }
    }

    public boolean criarNovoProcedimentoAnalise(Procedimentodeanalise procedimentodeanalise, List<String> listMeioComunicacao, List<String> listPerfilInteressado) {
        try {

            facadeJpa.getProcedimentodeanaliseJpa().create(procedimentodeanalise);
            criarNovoRegistro(procedimentodeanalise, Constantes.CADASTRO);
            ctrlRegistroDataComunicacao.criarNovoRegistroDataComunicacao(procedimentodeanalise, Constantes.CADASTRO);
            ctrlMeioComunicacao.criarVinculoMeioComunicacaoProcedimentoAnalise(procedimentodeanalise, listMeioComunicacao);
            ctrlPerfilInteressado.criarVinculoPerfilInteressadoProcedimentoAnalise(procedimentodeanalise, listPerfilInteressado);
            return true;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return false;
    }

    public void criarNovoRegistro(Procedimentodeanalise procedimentodeanalise, int tipo) {

        Registroprocedimentoanalise registroprocedimentoanalise = new Registroprocedimentoanalise();
        registroprocedimentoanalise.setData(new Date());
        registroprocedimentoanalise.setTipo(tipo);
        registroprocedimentoanalise.setNomeUsuario(Copia.getUsuarioLogado().getNome());
        registroprocedimentoanalise.setProcedimentoDeAnaliseid(procedimentodeanalise);
        try {
            facadeJpa.getRegistroprocedimentoanaliseJpaController().create(registroprocedimentoanalise);
            System.out.println("registro procedimento analise criado");
        } catch (Exception e) {
            System.out.println("Erro no registro procedimento de analise criado");
        }

    }

    public boolean editarProcedimentoAnalise(Procedimentodeanalise procedimentodeanalise, List<String> listMeioComunicacao, List<String> listPerfilInteressado) {

        try {
            facadeJpa.getProcedimentodeanaliseJpa().edit(procedimentodeanalise);
            criarNovoRegistro(procedimentodeanalise, Constantes.EDICAO);
            ctrlRegistroDataComunicacao.criarNovoRegistroDataComunicacao(procedimentodeanalise, Constantes.EDICAO);

            //@TODO verficar a utilizacao do editar
            ctrlMeioComunicacao.editarVinculoMeioComunicacaoProcedimentoAnalise(procedimentodeanalise, listMeioComunicacao);
            ctrlPerfilInteressado.editarVinculoPerfilInteressadoProcedimentoAnalise(procedimentodeanalise, listPerfilInteressado);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
