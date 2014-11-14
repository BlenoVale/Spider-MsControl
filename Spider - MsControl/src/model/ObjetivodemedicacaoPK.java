/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Dan
 */
@Embeddable
public class ObjetivodemedicacaoPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id")
    private int id;
    @Basic(optional = false)
    @Column(name = "Projeto_id")
    private int projetoid;

    public ObjetivodemedicacaoPK() {
    }

    public ObjetivodemedicacaoPK(int id, int projetoid) {
        this.id = id;
        this.projetoid = projetoid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProjetoid() {
        return projetoid;
    }

    public void setProjetoid(int projetoid) {
        this.projetoid = projetoid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        hash += (int) projetoid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ObjetivodemedicacaoPK)) {
            return false;
        }
        ObjetivodemedicacaoPK other = (ObjetivodemedicacaoPK) object;
        if (this.id != other.id)
            return false;
        if (this.projetoid != other.projetoid)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "model.ObjetivodemedicacaoPK[ id=" + id + ", projetoid=" + projetoid + " ]";
    }
    
}
