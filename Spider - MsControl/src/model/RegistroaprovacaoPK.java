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
public class RegistroaprovacaoPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id")
    private int id;
    @Basic(optional = false)
    @Column(name = "Aprovacao_id")
    private int aprovacaoid;

    public RegistroaprovacaoPK() {
    }

    public RegistroaprovacaoPK(int id, int aprovacaoid) {
        this.id = id;
        this.aprovacaoid = aprovacaoid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAprovacaoid() {
        return aprovacaoid;
    }

    public void setAprovacaoid(int aprovacaoid) {
        this.aprovacaoid = aprovacaoid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        hash += (int) aprovacaoid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RegistroaprovacaoPK)) {
            return false;
        }
        RegistroaprovacaoPK other = (RegistroaprovacaoPK) object;
        if (this.id != other.id)
            return false;
        if (this.aprovacaoid != other.aprovacaoid)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "model.RegistroaprovacaoPK[ id=" + id + ", aprovacaoid=" + aprovacaoid + " ]";
    }
    
}
