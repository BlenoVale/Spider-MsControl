/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Procedimentodeanalise;
import model.Registrodatacomunicacao;
import facade.FacadeJpa;

/**
 *
 * @author paulosouza
 */
public class CtrlRegistroDataComunicacao {

    FacadeJpa facadeJpa = FacadeJpa.getInstance();

    public void criarNovoRegistroDataComunicacao(Procedimentodeanalise procedimentodeanalise, int tipo) {
        try {

            Registrodatacomunicacao registrodatacomunicacao = new Registrodatacomunicacao();
            registrodatacomunicacao.setDataComunicacao(procedimentodeanalise.getDataComunicacao());
            registrodatacomunicacao.setProcedimentoDeAnaliseid(procedimentodeanalise);
            registrodatacomunicacao.setTipo(tipo);

            facadeJpa.getRegistroDataComunicacaoJpa().create(registrodatacomunicacao);
            System.out.println("registro data comunicação criado");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
