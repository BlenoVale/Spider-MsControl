/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import facade.FacadeJpa;
import java.util.ArrayList;
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

    public List<Procedimentodeanalise> getIndicadoresDoProjeto(int idDoProjeto) {
        try {
            return facadeJpa.getProcedimentodeanaliseJpa().findListaIndicadoresByProjeto(idDoProjeto);
        } catch (Exception error) {
            throw error;
        }
    }

    public boolean criarNovoProcedimentoAnalise(Procedimentodeanalise procedimentodeanalise) {
        try {

            facadeJpa.getProcedimentodeanaliseJpa().create(procedimentodeanalise);
            criarNovoRegistro(procedimentodeanalise, Constantes.CADASTRO);
            ctrlRegistroDataComunicacao.criarNovoRegistroDataComunicacao(procedimentodeanalise, Constantes.CADASTRO);
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

    public void criarNovoMeioComunicacao(ArrayList<Integer> integers) {

        for (int i = 0; i < integers.size(); i++) {

            facadeJpa.getMeioComunicacaoJpa().create(null);
        }
    }

    public boolean editarProcedimentoAnalise(Procedimentodeanalise procedimentodeanalise) {

        try {
            facadeJpa.getProcedimentodeanaliseJpa().edit(procedimentodeanalise);
            criarNovoRegistro(procedimentodeanalise, Constantes.EDICAO);
            ctrlRegistroDataComunicacao.criarNovoRegistroDataComunicacao(procedimentodeanalise, Constantes.EDICAO);
            System.out.println("Editado com sucesso.");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
