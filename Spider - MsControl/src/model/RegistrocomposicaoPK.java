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
public class RegistrocomposicaoPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id")
    private int id;
    @Basic(optional = false)
    @Column(name = "Composicao_id")
    private int composicaoid;

    public RegistrocomposicaoPK() {
    }

    public RegistrocomposicaoPK(int id, int composicaoid) {
        this.id = id;
        this.composicaoid = composicaoid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getComposicaoid() {
        return composicaoid;
    }

    public void setComposicaoid(int composicaoid) {
        this.composicaoid = composicaoid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        hash += (int) composicaoid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RegistrocomposicaoPK)) {
            return false;
        }
        RegistrocomposicaoPK other = (RegistrocomposicaoPK) object;
        if (this.id != other.id)
            return false;
        if (this.composicaoid != other.composicaoid)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "model.RegistrocomposicaoPK[ id=" + id + ", composicaoid=" + composicaoid + " ]";
    }
    
}
