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
public class ObjetivodequestaoPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id")
    private int id;
    @Basic(optional = false)
    @Column(name = "idObjetivoDeMedicacao")
    private int idObjetivoDeMedicacao;

    public ObjetivodequestaoPK() {
    }

    public ObjetivodequestaoPK(int id, int idObjetivoDeMedicacao) {
        this.id = id;
        this.idObjetivoDeMedicacao = idObjetivoDeMedicacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdObjetivoDeMedicacao() {
        return idObjetivoDeMedicacao;
    }

    public void setIdObjetivoDeMedicacao(int idObjetivoDeMedicacao) {
        this.idObjetivoDeMedicacao = idObjetivoDeMedicacao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        hash += (int) idObjetivoDeMedicacao;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ObjetivodequestaoPK)) {
            return false;
        }
        ObjetivodequestaoPK other = (ObjetivodequestaoPK) object;
        if (this.id != other.id)
            return false;
        if (this.idObjetivoDeMedicacao != other.idObjetivoDeMedicacao)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "model.ObjetivodequestaoPK[ id=" + id + ", idObjetivoDeMedicacao=" + idObjetivoDeMedicacao + " ]";
    }
    
}
