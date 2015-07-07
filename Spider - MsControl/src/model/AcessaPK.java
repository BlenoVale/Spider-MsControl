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
 * @author BlenoVale
 */
@Embeddable
public class AcessaPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "Projeto_id")
    private int projetoid;
    @Basic(optional = false)
    @Column(name = "Usuario_id")
    private int usuarioid;
    @Basic(optional = false)
    @Column(name = "Perfil_id")
    private int perfilid;

    public AcessaPK() {
    }

    public AcessaPK(int projetoid, int usuarioid, int perfilid) {
        this.projetoid = projetoid;
        this.usuarioid = usuarioid;
        this.perfilid = perfilid;
    }

    public int getProjetoid() {
        return projetoid;
    }

    public void setProjetoid(int projetoid) {
        this.projetoid = projetoid;
    }

    public int getUsuarioid() {
        return usuarioid;
    }

    public void setUsuarioid(int usuarioid) {
        this.usuarioid = usuarioid;
    }

    public int getPerfilid() {
        return perfilid;
    }

    public void setPerfilid(int perfilid) {
        this.perfilid = perfilid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) projetoid;
        hash += (int) usuarioid;
        hash += (int) perfilid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AcessaPK)) {
            return false;
        }
        AcessaPK other = (AcessaPK) object;
        if (this.projetoid != other.projetoid) {
            return false;
        }
        if (this.usuarioid != other.usuarioid) {
            return false;
        }
        if (this.perfilid != other.perfilid) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.AcessaPK[ projetoid=" + projetoid + ", usuarioid=" + usuarioid + ", perfilid=" + perfilid + " ]";
    }
    
}
