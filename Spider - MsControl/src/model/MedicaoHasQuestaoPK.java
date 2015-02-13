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
public class MedicaoHasQuestaoPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "ObjetivoDeQuestao_id")
    private int objetivoDeQuestaoid;
    @Basic(optional = false)
    @Column(name = "ObjetivoDeMedicao_id")
    private int objetivoDeMedicaoid;

    public MedicaoHasQuestaoPK() {
    }

    public MedicaoHasQuestaoPK(int objetivoDeQuestaoid, int objetivoDeMedicaoid) {
        this.objetivoDeQuestaoid = objetivoDeQuestaoid;
        this.objetivoDeMedicaoid = objetivoDeMedicaoid;
    }

    public int getObjetivoDeQuestaoid() {
        return objetivoDeQuestaoid;
    }

    public void setObjetivoDeQuestaoid(int objetivoDeQuestaoid) {
        this.objetivoDeQuestaoid = objetivoDeQuestaoid;
    }

    public int getObjetivoDeMedicaoid() {
        return objetivoDeMedicaoid;
    }

    public void setObjetivoDeMedicaoid(int objetivoDeMedicaoid) {
        this.objetivoDeMedicaoid = objetivoDeMedicaoid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) objetivoDeQuestaoid;
        hash += (int) objetivoDeMedicaoid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MedicaoHasQuestaoPK)) {
            return false;
        }
        MedicaoHasQuestaoPK other = (MedicaoHasQuestaoPK) object;
        if (this.objetivoDeQuestaoid != other.objetivoDeQuestaoid)
            return false;
        if (this.objetivoDeMedicaoid != other.objetivoDeMedicaoid)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "model.MedicaoHasQuestaoPK[ objetivoDeQuestaoid=" + objetivoDeQuestaoid + ", objetivoDeMedicaoid=" + objetivoDeMedicaoid + " ]";
    }
    
}
