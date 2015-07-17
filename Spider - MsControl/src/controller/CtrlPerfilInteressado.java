/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import facade.FacadeJpa;
import java.util.List;
import model.Perfilinteressado;
import model.Perfisinteressadosprocedimentoanalise;
import model.Procedimentodeanalise;

/**
 *
 * @author paulosouza
 */
public class CtrlPerfilInteressado {

    FacadeJpa facadeJpa = FacadeJpa.getInstance();

    public void criarVinculoPerfilInteressadoProcedimentoAnalise(Procedimentodeanalise procedimentodeanalise, List<String> listPerfilInteressado) {

        try {
            for (int i = 0; i < listPerfilInteressado.size(); i++) {

                Perfilinteressado perfilinteressado = facadeJpa.getPerfilInteressadoJpa().findByName(listPerfilInteressado.get(i));
                criarPerfilInteressadoProcedimentoAnalise(procedimentodeanalise, perfilinteressado);
                System.out.println("Salvo Perfil Interessado Procedimento Analise");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void criarPerfilInteressadoProcedimentoAnalise(Procedimentodeanalise procedimentodeanalise, Perfilinteressado perfilinteressado) {

        try {
            Perfisinteressadosprocedimentoanalise perfisinteressadosprocedimentoanalise = new Perfisinteressadosprocedimentoanalise();
            perfisinteressadosprocedimentoanalise.setIdperfilInteressado(perfilinteressado.getId());
            perfisinteressadosprocedimentoanalise.setProcedimentoDeAnaliseid(procedimentodeanalise);
            facadeJpa.getPerfislInteressadoProcedimentoAnaliseJpa().create(perfisinteressadosprocedimentoanalise);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void editarPerfilInteressadoProcedimentoAnalise(Procedimentodeanalise procedimentodeanalise, Perfilinteressado perfilinteressado) {

        try {
            Perfisinteressadosprocedimentoanalise perfisinteressadosprocedimentoanalise = facadeJpa.getPerfislInteressadoProcedimentoAnaliseJpa().findByPerfilInteressado(perfilinteressado.getId());
            perfisinteressadosprocedimentoanalise.setIdperfilInteressado(perfilinteressado.getId());
            perfisinteressadosprocedimentoanalise.setProcedimentoDeAnaliseid(procedimentodeanalise);

            facadeJpa.getPerfislInteressadoProcedimentoAnaliseJpa().destroy(perfisinteressadosprocedimentoanalise.getId());
            facadeJpa.getPerfislInteressadoProcedimentoAnaliseJpa().create(perfisinteressadosprocedimentoanalise);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void editarVinculoPerfilInteressadoProcedimentoAnalise(Procedimentodeanalise procedimentodeanalise, List<String> listPerfilInteressado) {

        limparPerfisInteressadosProcedimentoAnalise(procedimentodeanalise);

        try {
            for (int i = 0; i < listPerfilInteressado.size(); i++) {

                Perfilinteressado perfilinteressado = facadeJpa.getPerfilInteressadoJpa().findByName(listPerfilInteressado.get(i));
                criarPerfilInteressadoProcedimentoAnalise(procedimentodeanalise, perfilinteressado);
                System.out.println("Editado Perfil Interessado Procedimento Analise");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void limparPerfisInteressadosProcedimentoAnalise(Procedimentodeanalise procedimentodeanalise) {
        List<Perfisinteressadosprocedimentoanalise> perfisinteressadosprocedimentoanalises = facadeJpa.getPerfislInteressadoProcedimentoAnaliseJpa().findByIdProcedimentoAnalise(procedimentodeanalise.getId());
        int TAM = perfisinteressadosprocedimentoanalises.size();

        for (int i = 0; i < TAM; i++) {
            try {
                facadeJpa.getPerfislInteressadoProcedimentoAnaliseJpa().destroy(perfisinteressadosprocedimentoanalises.get(i).getId());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

}
