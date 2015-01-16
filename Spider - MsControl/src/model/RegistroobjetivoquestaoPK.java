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
public class RegistroobjetivoquestaoPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id")
    private int id;
    @Basic(optional = false)
    @Column(name = "ObjetivoDeQuestao_id")
    private int objetivoDeQuestaoid;
    @Basic(optional = false)
    @Column(name = "ObjetivoDeQuestao_ObjetivoDeMedicao_id")
    private int objetivoDeQuestaoObjetivoDeMedicaoid;
    @Basic(optional = false)
    @Column(name = "ObjetivoDeQuestao_ObjetivoDeMedicao_Projeto_id")
    private int objetivoDeQuestaoObjetivoDeMedicaoProjetoid;

    public RegistroobjetivoquestaoPK() {
    }

    public RegistroobjetivoquestaoPK(int id, int objetivoDeQuestaoid, int objetivoDeQuestaoObjetivoDeMedicaoid, int objetivoDeQuestaoObjetivoDeMedicaoProjetoid) {
        this.id = id;
        this.objetivoDeQuestaoid = objetivoDeQuestaoid;
        this.objetivoDeQuestaoObjetivoDeMedicaoid = objetivoDeQuestaoObjetivoDeMedicaoid;
        this.objetivoDeQuestaoObjetivoDeMedicaoProjetoid = objetivoDeQuestaoObjetivoDeMedicaoProjetoid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getObjetivoDeQuestaoid() {
        return objetivoDeQuestaoid;
    }

    public void setObjetivoDeQuestaoid(int objetivoDeQuestaoid) {
        this.objetivoDeQuestaoid = objetivoDeQuestaoid;
    }

    public int getObjetivoDeQuestaoObjetivoDeMedicaoid() {
        return objetivoDeQuestaoObjetivoDeMedicaoid;
    }

    public void setObjetivoDeQuestaoObjetivoDeMedicaoid(int objetivoDeQuestaoObjetivoDeMedicaoid) {
        this.objetivoDeQuestaoObjetivoDeMedicaoid = objetivoDeQuestaoObjetivoDeMedicaoid;
    }

    public int getObjetivoDeQuestaoObjetivoDeMedicaoProjetoid() {
        return objetivoDeQuestaoObjetivoDeMedicaoProjetoid;
    }

    public void setObjetivoDeQuestaoObjetivoDeMedicaoProjetoid(int objetivoDeQuestaoObjetivoDeMedicaoProjetoid) {
        this.objetivoDeQuestaoObjetivoDeMedicaoProjetoid = objetivoDeQuestaoObjetivoDeMedicaoProjetoid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        hash += (int) objetivoDeQuestaoid;
        hash += (int) objetivoDeQuestaoObjetivoDeMedicaoid;
        hash += (int) objetivoDeQuestaoObjetivoDeMedicaoProjetoid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RegistroobjetivoquestaoPK)) {
            return false;
        }
        RegistroobjetivoquestaoPK other = (RegistroobjetivoquestaoPK) object;
        if (this.id != other.id)
            return false;
        if (this.objetivoDeQuestaoid != other.objetivoDeQuestaoid)
            return false;
        if (this.objetivoDeQuestaoObjetivoDeMedicaoid != other.objetivoDeQuestaoObjetivoDeMedicaoid)
            return false;
        if (this.objetivoDeQuestaoObjetivoDeMedicaoProjetoid != other.objetivoDeQuestaoObjetivoDeMedicaoProjetoid)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "model.RegistroobjetivoquestaoPK[ id=" + id + ", objetivoDeQuestaoid=" + objetivoDeQuestaoid + ", objetivoDeQuestaoObjetivoDeMedicaoid=" + objetivoDeQuestaoObjetivoDeMedicaoid + ", objetivoDeQuestaoObjetivoDeMedicaoProjetoid=" + objetivoDeQuestaoObjetivoDeMedicaoProjetoid + " ]";
    }
    
}
