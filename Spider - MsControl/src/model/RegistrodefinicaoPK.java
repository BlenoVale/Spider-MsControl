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
public class RegistrodefinicaoPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id")
    private int id;
    @Basic(optional = false)
    @Column(name = "Definicao_id")
    private int definicaoid;
    @Basic(optional = false)
    @Column(name = "Definicao_Medida_id")
    private int definicaoMedidaid;
    @Basic(optional = false)
    @Column(name = "Definicao_Medida_Projeto_id")
    private int definicaoMedidaProjetoid;

    public RegistrodefinicaoPK() {
    }

    public RegistrodefinicaoPK(int id, int definicaoid, int definicaoMedidaid, int definicaoMedidaProjetoid) {
        this.id = id;
        this.definicaoid = definicaoid;
        this.definicaoMedidaid = definicaoMedidaid;
        this.definicaoMedidaProjetoid = definicaoMedidaProjetoid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDefinicaoid() {
        return definicaoid;
    }

    public void setDefinicaoid(int definicaoid) {
        this.definicaoid = definicaoid;
    }

    public int getDefinicaoMedidaid() {
        return definicaoMedidaid;
    }

    public void setDefinicaoMedidaid(int definicaoMedidaid) {
        this.definicaoMedidaid = definicaoMedidaid;
    }

    public int getDefinicaoMedidaProjetoid() {
        return definicaoMedidaProjetoid;
    }

    public void setDefinicaoMedidaProjetoid(int definicaoMedidaProjetoid) {
        this.definicaoMedidaProjetoid = definicaoMedidaProjetoid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        hash += (int) definicaoid;
        hash += (int) definicaoMedidaid;
        hash += (int) definicaoMedidaProjetoid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RegistrodefinicaoPK)) {
            return false;
        }
        RegistrodefinicaoPK other = (RegistrodefinicaoPK) object;
        if (this.id != other.id)
            return false;
        if (this.definicaoid != other.definicaoid)
            return false;
        if (this.definicaoMedidaid != other.definicaoMedidaid)
            return false;
        if (this.definicaoMedidaProjetoid != other.definicaoMedidaProjetoid)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "model.RegistrodefinicaoPK[ id=" + id + ", definicaoid=" + definicaoid + ", definicaoMedidaid=" + definicaoMedidaid + ", definicaoMedidaProjetoid=" + definicaoMedidaProjetoid + " ]";
    }
    
}
