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
public class ObjetivodequestaoPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id")
    private int id;
    @Basic(optional = false)
    @Column(name = "ObjetivoDeMedicacao_id")
    private int objetivoDeMedicacaoid;
    @Basic(optional = false)
    @Column(name = "ObjetivoDeMedicacao_Projeto_id")
    private int objetivoDeMedicacaoProjetoid;

    public ObjetivodequestaoPK() {
    }

    public ObjetivodequestaoPK(int id, int objetivoDeMedicacaoid, int objetivoDeMedicacaoProjetoid) {
        this.id = id;
        this.objetivoDeMedicacaoid = objetivoDeMedicacaoid;
        this.objetivoDeMedicacaoProjetoid = objetivoDeMedicacaoProjetoid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getObjetivoDeMedicacaoid() {
        return objetivoDeMedicacaoid;
    }

    public void setObjetivoDeMedicacaoid(int objetivoDeMedicacaoid) {
        this.objetivoDeMedicacaoid = objetivoDeMedicacaoid;
    }

    public int getObjetivoDeMedicacaoProjetoid() {
        return objetivoDeMedicacaoProjetoid;
    }

    public void setObjetivoDeMedicacaoProjetoid(int objetivoDeMedicacaoProjetoid) {
        this.objetivoDeMedicacaoProjetoid = objetivoDeMedicacaoProjetoid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        hash += (int) objetivoDeMedicacaoid;
        hash += (int) objetivoDeMedicacaoProjetoid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ObjetivodequestaoPK)) {
            return false;
        }
        ObjetivodequestaoPK other = (ObjetivodequestaoPK) object;
        if (this.id != other.id) {
            return false;
        }
        if (this.objetivoDeMedicacaoid != other.objetivoDeMedicacaoid) {
            return false;
        }
        if (this.objetivoDeMedicacaoProjetoid != other.objetivoDeMedicacaoProjetoid) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.ObjetivodequestaoPK[ id=" + id + ", objetivoDeMedicacaoid=" + objetivoDeMedicacaoid + ", objetivoDeMedicacaoProjetoid=" + objetivoDeMedicacaoProjetoid + " ]";
    }
    
}
