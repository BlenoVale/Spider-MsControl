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
public class RegistroprocedimentocoletaPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id")
    private int id;
    @Basic(optional = false)
    @Column(name = "ProcedimentoDeColeta_id")
    private int procedimentoDeColetaid;

    public RegistroprocedimentocoletaPK() {
    }

    public RegistroprocedimentocoletaPK(int id, int procedimentoDeColetaid) {
        this.id = id;
        this.procedimentoDeColetaid = procedimentoDeColetaid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProcedimentoDeColetaid() {
        return procedimentoDeColetaid;
    }

    public void setProcedimentoDeColetaid(int procedimentoDeColetaid) {
        this.procedimentoDeColetaid = procedimentoDeColetaid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        hash += (int) procedimentoDeColetaid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RegistroprocedimentocoletaPK)) {
            return false;
        }
        RegistroprocedimentocoletaPK other = (RegistroprocedimentocoletaPK) object;
        if (this.id != other.id)
            return false;
        if (this.procedimentoDeColetaid != other.procedimentoDeColetaid)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "model.RegistroprocedimentocoletaPK[ id=" + id + ", procedimentoDeColetaid=" + procedimentoDeColetaid + " ]";
    }
    
}
