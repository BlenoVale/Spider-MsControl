/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Dan
 */
@Entity
@Table(name = "medicao_has_questao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MedicaoHasQuestao.findAll", query = "SELECT m FROM MedicaoHasQuestao m"),
    @NamedQuery(name = "MedicaoHasQuestao.findByObjetivoDeQuestaoid", query = "SELECT m FROM MedicaoHasQuestao m WHERE m.medicaoHasQuestaoPK.objetivoDeQuestaoid = :objetivoDeQuestaoid"),
    @NamedQuery(name = "MedicaoHasQuestao.findByObjetivoDeMedicaoid", query = "SELECT m FROM MedicaoHasQuestao m WHERE m.medicaoHasQuestaoPK.objetivoDeMedicaoid = :objetivoDeMedicaoid"),
    @NamedQuery(name = "MedicaoHasQuestao.findByPrioridade", query = "SELECT m FROM MedicaoHasQuestao m WHERE m.prioridade = :prioridade")})
public class MedicaoHasQuestao implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MedicaoHasQuestaoPK medicaoHasQuestaoPK;
    @Column(name = "prioridade")
    private Integer prioridade;
    @JoinColumn(name = "ObjetivoDeMedicao_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Objetivodemedicao objetivodemedicao;
    @JoinColumn(name = "ObjetivoDeQuestao_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Objetivodequestao objetivodequestao;

    public MedicaoHasQuestao() {
    }

    public MedicaoHasQuestao(MedicaoHasQuestaoPK medicaoHasQuestaoPK) {
        this.medicaoHasQuestaoPK = medicaoHasQuestaoPK;
    }

    public MedicaoHasQuestao(int objetivoDeQuestaoid, int objetivoDeMedicaoid) {
        this.medicaoHasQuestaoPK = new MedicaoHasQuestaoPK(objetivoDeQuestaoid, objetivoDeMedicaoid);
    }

    public MedicaoHasQuestaoPK getMedicaoHasQuestaoPK() {
        return medicaoHasQuestaoPK;
    }

    public void setMedicaoHasQuestaoPK(MedicaoHasQuestaoPK medicaoHasQuestaoPK) {
        this.medicaoHasQuestaoPK = medicaoHasQuestaoPK;
    }

    public Integer getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Integer prioridade) {
        this.prioridade = prioridade;
    }

    public Objetivodemedicao getObjetivodemedicao() {
        return objetivodemedicao;
    }

    public void setObjetivodemedicao(Objetivodemedicao objetivodemedicao) {
        this.objetivodemedicao = objetivodemedicao;
    }

    public Objetivodequestao getObjetivodequestao() {
        return objetivodequestao;
    }

    public void setObjetivodequestao(Objetivodequestao objetivodequestao) {
        this.objetivodequestao = objetivodequestao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (medicaoHasQuestaoPK != null ? medicaoHasQuestaoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MedicaoHasQuestao)) {
            return false;
        }
        MedicaoHasQuestao other = (MedicaoHasQuestao) object;
        if ((this.medicaoHasQuestaoPK == null && other.medicaoHasQuestaoPK != null) || (this.medicaoHasQuestaoPK != null && !this.medicaoHasQuestaoPK.equals(other.medicaoHasQuestaoPK)))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "model.MedicaoHasQuestao[ medicaoHasQuestaoPK=" + medicaoHasQuestaoPK + " ]";
    }
    
}
