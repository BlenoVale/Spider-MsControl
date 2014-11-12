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
 * @author Spider
 */
@Embeddable
public class PossuiPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id")
    private int id;
    @Basic(optional = false)
    @Column(name = "Perfil_id")
    private int perfilid;
    @Basic(optional = false)
    @Column(name = "Funcionalidade_id")
    private int funcionalidadeid;

    public PossuiPK() {
    }

    public PossuiPK(int id, int perfilid, int funcionalidadeid) {
        this.id = id;
        this.perfilid = perfilid;
        this.funcionalidadeid = funcionalidadeid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPerfilid() {
        return perfilid;
    }

    public void setPerfilid(int perfilid) {
        this.perfilid = perfilid;
    }

    public int getFuncionalidadeid() {
        return funcionalidadeid;
    }

    public void setFuncionalidadeid(int funcionalidadeid) {
        this.funcionalidadeid = funcionalidadeid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        hash += (int) perfilid;
        hash += (int) funcionalidadeid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PossuiPK)) {
            return false;
        }
        PossuiPK other = (PossuiPK) object;
        if (this.id != other.id) {
            return false;
        }
        if (this.perfilid != other.perfilid) {
            return false;
        }
        if (this.funcionalidadeid != other.funcionalidadeid) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.PossuiPK[ id=" + id + ", perfilid=" + perfilid + ", funcionalidadeid=" + funcionalidadeid + " ]";
    }
    
}
