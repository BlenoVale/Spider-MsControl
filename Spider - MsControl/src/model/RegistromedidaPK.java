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
public class RegistromedidaPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id")
    private int id;
    @Basic(optional = false)
    @Column(name = "Medida_id")
    private int medidaid;
    @Basic(optional = false)
    @Column(name = "Medida_Projeto_id")
    private int medidaProjetoid;

    public RegistromedidaPK() {
    }

    public RegistromedidaPK(int id, int medidaid, int medidaProjetoid) {
        this.id = id;
        this.medidaid = medidaid;
        this.medidaProjetoid = medidaProjetoid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMedidaid() {
        return medidaid;
    }

    public void setMedidaid(int medidaid) {
        this.medidaid = medidaid;
    }

    public int getMedidaProjetoid() {
        return medidaProjetoid;
    }

    public void setMedidaProjetoid(int medidaProjetoid) {
        this.medidaProjetoid = medidaProjetoid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        hash += (int) medidaid;
        hash += (int) medidaProjetoid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RegistromedidaPK)) {
            return false;
        }
        RegistromedidaPK other = (RegistromedidaPK) object;
        if (this.id != other.id)
            return false;
        if (this.medidaid != other.medidaid)
            return false;
        if (this.medidaProjetoid != other.medidaProjetoid)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "model.RegistromedidaPK[ id=" + id + ", medidaid=" + medidaid + ", medidaProjetoid=" + medidaProjetoid + " ]";
    }
    
}