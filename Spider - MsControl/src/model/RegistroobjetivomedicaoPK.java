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
public class RegistroobjetivomedicaoPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id")
    private int id;
    @Basic(optional = false)
    @Column(name = "ObjetivoDeMedicao_id")
    private int objetivoDeMedicaoid;
    @Basic(optional = false)
    @Column(name = "ObjetivoDeMedicao_Projeto_id")
    private int objetivoDeMedicaoProjetoid;

    public RegistroobjetivomedicaoPK() {
    }

    public RegistroobjetivomedicaoPK(int id, int objetivoDeMedicaoid, int objetivoDeMedicaoProjetoid) {
        this.id = id;
        this.objetivoDeMedicaoid = objetivoDeMedicaoid;
        this.objetivoDeMedicaoProjetoid = objetivoDeMedicaoProjetoid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getObjetivoDeMedicaoid() {
        return objetivoDeMedicaoid;
    }

    public void setObjetivoDeMedicaoid(int objetivoDeMedicaoid) {
        this.objetivoDeMedicaoid = objetivoDeMedicaoid;
    }

    public int getObjetivoDeMedicaoProjetoid() {
        return objetivoDeMedicaoProjetoid;
    }

    public void setObjetivoDeMedicaoProjetoid(int objetivoDeMedicaoProjetoid) {
        this.objetivoDeMedicaoProjetoid = objetivoDeMedicaoProjetoid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        hash += (int) objetivoDeMedicaoid;
        hash += (int) objetivoDeMedicaoProjetoid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RegistroobjetivomedicaoPK)) {
            return false;
        }
        RegistroobjetivomedicaoPK other = (RegistroobjetivomedicaoPK) object;
        if (this.id != other.id)
            return false;
        if (this.objetivoDeMedicaoid != other.objetivoDeMedicaoid)
            return false;
        if (this.objetivoDeMedicaoProjetoid != other.objetivoDeMedicaoProjetoid)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "model.RegistroobjetivomedicaoPK[ id=" + id + ", objetivoDeMedicaoid=" + objetivoDeMedicaoid + ", objetivoDeMedicaoProjetoid=" + objetivoDeMedicaoProjetoid + " ]";
    }
    
}
