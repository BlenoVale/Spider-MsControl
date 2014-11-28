/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import controller.ProjetoJpaController;
import java.text.SimpleDateFormat;
import java.util.Date;
import model.Projeto;
import util.Conexao;

/**
 *
 * @author Dan
 */
public class Teste {

    public static void main(String[] args) {

 
        Projeto projeto = new Projeto();
        projeto.setNome("PROJETO 1");

        projeto.setDataFim(new Date());
        projeto.setDataInicio(new Date());
        projeto.setStatus(0);

        try {
            new ProjetoJpaController(Conexao.conectar()).create(projeto);
            System.out.println("Funfou");
        } catch (Exception e) {
            System.out.println("Não funfou");
        }
    }

    public static  String getData() {
        SimpleDateFormat formatador = new SimpleDateFormat("yy/MM/dd");
        Date data = new Date();

        String dataFormatada = formatador.format(data);
        return dataFormatada;
    }
}
