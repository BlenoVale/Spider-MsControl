/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import controller.ProjetoJpaController;
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
        projeto.setDataFim(new Date(2014, 11, 13));
        projeto.setDataInicio(new Date(2014, 11, 13));
        projeto.setNome("Teste");
        projeto.setStatus(0);
        
        try {
            new ProjetoJpaController(Conexao.conectar()).create(projeto);
            System.out.println("Funfou");
        } catch (Exception e) {
            System.out.println("Não funfou");
        }
    }
}
