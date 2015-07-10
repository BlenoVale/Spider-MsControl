/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import facade.FacadeJpa;
import java.util.List;
import model.Meioscomunicacao;

/**
 *
 * @author Paulo
 */
public class CtrlMeioComunicacao {

    private final FacadeJpa facadeJpa = FacadeJpa.getInstance();


    public List<Meioscomunicacao> buscarMeiosComunicacao()
    {
        try {
            return facadeJpa.getMeioComunicacaoJpa().findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean criarNovoMeioComunicacao(Meioscomunicacao meioscomunicacao)
    {
        try {
            facadeJpa.getMeioComunicacaoJpa().create(meioscomunicacao);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Meioscomunicacao buscarMeioComunicacao(String nome)
    {
        return (Meioscomunicacao) facadeJpa.getMeioComunicacaoJpa().findByName(nome);
    }

    

}
