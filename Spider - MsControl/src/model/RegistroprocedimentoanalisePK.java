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
public class RegistroprocedimentoanalisePK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id")
    private int id;
    @Basic(optional = false)
    @Column(name = "ProcedimentoDeAnalise_id")
    private int procedimentoDeAnaliseid;

    public RegistroprocedimentoanalisePK() {
    }

    public RegistroprocedimentoanalisePK(int id, int procedimentoDeAnaliseid) {
        this.id = id;
        this.procedimentoDeAnaliseid = procedimentoDeAnaliseid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProcedimentoDeAnaliseid() {
        return procedimentoDeAnaliseid;
    }

    public void setProcedimentoDeAnaliseid(int procedimentoDeAnaliseid) {
        this.procedimentoDeAnaliseid = procedimentoDeAnaliseid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        hash += (int) procedimentoDeAnaliseid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RegistroprocedimentoanalisePK)) {
            return false;
        }
        RegistroprocedimentoanalisePK other = (RegistroprocedimentoanalisePK) object;
        if (this.id != other.id)
            return false;
        if (this.procedimentoDeAnaliseid != other.procedimentoDeAnaliseid)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "model.RegistroprocedimentoanalisePK[ id=" + id + ", procedimentoDeAnaliseid=" + procedimentoDeAnaliseid + " ]";
    }
    
}
