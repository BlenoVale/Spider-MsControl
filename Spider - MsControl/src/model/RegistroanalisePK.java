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
public class RegistroanalisePK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id")
    private int id;
    @Basic(optional = false)
    @Column(name = "Analise_id")
    private int analiseid;
    @Basic(optional = false)
    @Column(name = "Analise_Medida_id")
    private int analiseMedidaid;
    @Basic(optional = false)
    @Column(name = "Analise_Medida_Projeto_id")
    private int analiseMedidaProjetoid;

    public RegistroanalisePK() {
    }

    public RegistroanalisePK(int id, int analiseid, int analiseMedidaid, int analiseMedidaProjetoid) {
        this.id = id;
        this.analiseid = analiseid;
        this.analiseMedidaid = analiseMedidaid;
        this.analiseMedidaProjetoid = analiseMedidaProjetoid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAnaliseid() {
        return analiseid;
    }

    public void setAnaliseid(int analiseid) {
        this.analiseid = analiseid;
    }

    public int getAnaliseMedidaid() {
        return analiseMedidaid;
    }

    public void setAnaliseMedidaid(int analiseMedidaid) {
        this.analiseMedidaid = analiseMedidaid;
    }

    public int getAnaliseMedidaProjetoid() {
        return analiseMedidaProjetoid;
    }

    public void setAnaliseMedidaProjetoid(int analiseMedidaProjetoid) {
        this.analiseMedidaProjetoid = analiseMedidaProjetoid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        hash += (int) analiseid;
        hash += (int) analiseMedidaid;
        hash += (int) analiseMedidaProjetoid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RegistroanalisePK)) {
            return false;
        }
        RegistroanalisePK other = (RegistroanalisePK) object;
        if (this.id != other.id)
            return false;
        if (this.analiseid != other.analiseid)
            return false;
        if (this.analiseMedidaid != other.analiseMedidaid)
            return false;
        if (this.analiseMedidaProjetoid != other.analiseMedidaProjetoid)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "model.RegistroanalisePK[ id=" + id + ", analiseid=" + analiseid + ", analiseMedidaid=" + analiseMedidaid + ", analiseMedidaProjetoid=" + analiseMedidaProjetoid + " ]";
    }
    
}
