/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

import facade.FacadeJpa;

/**
 *
 * @author Dan
 */
public class TesteDoEmail {
    public static void main(String[] args) {
        System.out.println(FacadeJpa.getInstance().getIndicadorJpa().findRegistrosDoIndicadorByTipo(1, 1));
    }
}
