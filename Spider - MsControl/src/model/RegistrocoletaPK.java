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
public class RegistrocoletaPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id")
    private int id;
    @Basic(optional = false)
    @Column(name = "Coleta_id")
    private int coletaid;
    @Basic(optional = false)
    @Column(name = "Coleta_Medida_id")
    private int coletaMedidaid;
    @Basic(optional = false)
    @Column(name = "Coleta_Medida_Projeto_id")
    private int coletaMedidaProjetoid;

    public RegistrocoletaPK() {
    }

    public RegistrocoletaPK(int id, int coletaid, int coletaMedidaid, int coletaMedidaProjetoid) {
        this.id = id;
        this.coletaid = coletaid;
        this.coletaMedidaid = coletaMedidaid;
        this.coletaMedidaProjetoid = coletaMedidaProjetoid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getColetaid() {
        return coletaid;
    }

    public void setColetaid(int coletaid) {
        this.coletaid = coletaid;
    }

    public int getColetaMedidaid() {
        return coletaMedidaid;
    }

    public void setColetaMedidaid(int coletaMedidaid) {
        this.coletaMedidaid = coletaMedidaid;
    }

    public int getColetaMedidaProjetoid() {
        return coletaMedidaProjetoid;
    }

    public void setColetaMedidaProjetoid(int coletaMedidaProjetoid) {
        this.coletaMedidaProjetoid = coletaMedidaProjetoid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        hash += (int) coletaid;
        hash += (int) coletaMedidaid;
        hash += (int) coletaMedidaProjetoid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RegistrocoletaPK)) {
            return false;
        }
        RegistrocoletaPK other = (RegistrocoletaPK) object;
        if (this.id != other.id)
            return false;
        if (this.coletaid != other.coletaid)
            return false;
        if (this.coletaMedidaid != other.coletaMedidaid)
            return false;
        if (this.coletaMedidaProjetoid != other.coletaMedidaProjetoid)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "model.RegistrocoletaPK[ id=" + id + ", coletaid=" + coletaid + ", coletaMedidaid=" + coletaMedidaid + ", coletaMedidaProjetoid=" + coletaMedidaProjetoid + " ]";
    }
    
}
