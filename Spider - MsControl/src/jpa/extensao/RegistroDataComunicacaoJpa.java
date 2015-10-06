/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.extensao;

import javax.persistence.EntityManagerFactory;
import jpa.RegistrodatacomunicacaoJpaController;

/**
 *
 * @author paulosouza
 */
public class RegistroDataComunicacaoJpa extends RegistrodatacomunicacaoJpaController {

    public RegistroDataComunicacaoJpa(EntityManagerFactory emf) {
        super(emf);
    }

}
