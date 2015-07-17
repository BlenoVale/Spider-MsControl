/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import facade.FacadeJpa;
import java.util.List;
import javax.persistence.RollbackException;
import javax.swing.JOptionPane;
import model.Meioscomunicacao;
import model.Meiosprocedimentoanalise;
import model.Procedimentodeanalise;

/**
 *
 * @author Paulo
 */
public class CtrlMeioComunicacao {

    private final FacadeJpa facadeJpa = FacadeJpa.getInstance();

    public List<Meioscomunicacao> buscarMeiosComunicacao() {
        try {
            return facadeJpa.getMeioComunicacaoJpa().findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean criarNovoMeioComunicacao(Meioscomunicacao meioscomunicacao) {
        try {
            facadeJpa.getMeioComunicacaoJpa().create(meioscomunicacao);
            return true;

        } catch (RollbackException e) {
            JOptionPane.showMessageDialog(null, "Já existe um meio de comunicação com esse nome, escolha outro.", "", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Meioscomunicacao buscarMeioComunicacao(String nome) {
        return (Meioscomunicacao) facadeJpa.getMeioComunicacaoJpa().findByName(nome);
    }

    public void vincularMeioComunicacaoProcedimentoAnalise(Procedimentodeanalise procedimentodeanalise, Meioscomunicacao meioscomunicacao) {
        try {
            Meiosprocedimentoanalise meiosprocedimentoanalise = new Meiosprocedimentoanalise();
            meiosprocedimentoanalise.setIdmeioComunicacao(meioscomunicacao.getId());
            meiosprocedimentoanalise.setProcedimentoDeAnaliseid(procedimentodeanalise);
            facadeJpa.getMeioComunicacaoProcedimentoAnaliseJpa().create(meiosprocedimentoanalise);
            System.out.println("criado vinculo meio comunicação com procedimento analise");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void criarVinculoMeioComunicacaoProcedimentoAnalise(Procedimentodeanalise procedimentodeanalise, List<String> meioComunicacao) {
        try {
            for (int i = 0; i < meioComunicacao.size(); i++) {
                Meioscomunicacao meioscomunicacao = facadeJpa.getMeioComunicacaoJpa().findByName(meioComunicacao.get(i));
                vincularMeioComunicacaoProcedimentoAnalise(procedimentodeanalise, meioscomunicacao);
            }
        } catch (Exception e) {
        }
    }

    public void editarMeioComunicacaoProcedimentoAnalise(Procedimentodeanalise procedimentodeanalise, Meioscomunicacao meioscomunicacao) {
        try {
            Meiosprocedimentoanalise meiosprocedimentoanalise = new Meiosprocedimentoanalise();
            meiosprocedimentoanalise.setIdmeioComunicacao(meioscomunicacao.getId());
            meiosprocedimentoanalise.setProcedimentoDeAnaliseid(procedimentodeanalise);
            facadeJpa.getMeioComunicacaoProcedimentoAnaliseJpa().edit(meiosprocedimentoanalise);
            System.out.println("criado vinculo meio comunicação com procedimento analise");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void editarVinculoMeioComunicacaoProcedimentoAnalise(Procedimentodeanalise procedimentodeanalise, List<String> meioComunicacao) {
        try {
            for (int i = 0; i < meioComunicacao.size(); i++) {
                Meioscomunicacao meioscomunicacao = facadeJpa.getMeioComunicacaoJpa().findByName(meioComunicacao.get(i));
                vincularMeioComunicacaoProcedimentoAnalise(procedimentodeanalise, meioscomunicacao);
            }
        } catch (Exception e) {
        }
    }

}
