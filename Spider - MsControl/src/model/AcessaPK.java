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
public class AcessaPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "idPerfil")
    private int idPerfil;
    @Basic(optional = false)
    @Column(name = "idUsuario")
    private int idUsuario;
    @Basic(optional = false)
    @Column(name = "idProjeto")
    private int idProjeto;

    public AcessaPK() {
    }

    public AcessaPK(int idPerfil, int idUsuario, int idProjeto) {
        this.idPerfil = idPerfil;
        this.idUsuario = idUsuario;
        this.idProjeto = idProjeto;
    }

    public int getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(int idPerfil) {
        this.idPerfil = idPerfil;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(int idProjeto) {
        this.idProjeto = idProjeto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idPerfil;
        hash += (int) idUsuario;
        hash += (int) idProjeto;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AcessaPK)) {
            return false;
        }
        AcessaPK other = (AcessaPK) object;
        if (this.idPerfil != other.idPerfil)
            return false;
        if (this.idUsuario != other.idUsuario)
            return false;
        if (this.idProjeto != other.idProjeto)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "model.AcessaPK[ idPerfil=" + idPerfil + ", idUsuario=" + idUsuario + ", idProjeto=" + idProjeto + " ]";
    }
    
}
