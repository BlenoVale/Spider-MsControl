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
public class DefinicaoPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id")
    private int id;
    @Basic(optional = false)
    @Column(name = "idMedida")
    private int idMedida;

    public DefinicaoPK() {
    }

    public DefinicaoPK(int id, int idMedida) {
        this.id = id;
        this.idMedida = idMedida;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdMedida() {
        return idMedida;
    }

    public void setIdMedida(int idMedida) {
        this.idMedida = idMedida;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        hash += (int) idMedida;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DefinicaoPK)) {
            return false;
        }
        DefinicaoPK other = (DefinicaoPK) object;
        if (this.id != other.id)
            return false;
        if (this.idMedida != other.idMedida)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "model.DefinicaoPK[ id=" + id + ", idMedida=" + idMedida + " ]";
    }
    
}
