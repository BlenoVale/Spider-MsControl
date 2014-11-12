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
public class PossuiPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id")
    private int id;
    @Basic(optional = false)
    @Column(name = "idFuncionalidade")
    private int idFuncionalidade;
    @Basic(optional = false)
    @Column(name = "idPerfil")
    private int idPerfil;

    public PossuiPK() {
    }

    public PossuiPK(int id, int idFuncionalidade, int idPerfil) {
        this.id = id;
        this.idFuncionalidade = idFuncionalidade;
        this.idPerfil = idPerfil;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdFuncionalidade() {
        return idFuncionalidade;
    }

    public void setIdFuncionalidade(int idFuncionalidade) {
        this.idFuncionalidade = idFuncionalidade;
    }

    public int getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(int idPerfil) {
        this.idPerfil = idPerfil;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        hash += (int) idFuncionalidade;
        hash += (int) idPerfil;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PossuiPK)) {
            return false;
        }
        PossuiPK other = (PossuiPK) object;
        if (this.id != other.id)
            return false;
        if (this.idFuncionalidade != other.idFuncionalidade)
            return false;
        if (this.idPerfil != other.idPerfil)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "model.PossuiPK[ id=" + id + ", idFuncionalidade=" + idFuncionalidade + ", idPerfil=" + idPerfil + " ]";
    }
    
}
