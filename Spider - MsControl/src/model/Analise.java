/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Dan
 */
@Entity
@Table(name = "analise")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Analise.findAll", query = "SELECT a FROM Analise a"),
    @NamedQuery(name = "Analise.findById", query = "SELECT a FROM Analise a WHERE a.analisePK.id = :id"),
    @NamedQuery(name = "Analise.findByMedidaid", query = "SELECT a FROM Analise a WHERE a.analisePK.medidaid = :medidaid"),
    @NamedQuery(name = "Analise.findByMedidaProjetoid", query = "SELECT a FROM Analise a WHERE a.analisePK.medidaProjetoid = :medidaProjetoid"),
    @NamedQuery(name = "Analise.findByVersao", query = "SELECT a FROM Analise a WHERE a.versao = :versao"),
    @NamedQuery(name = "Analise.findByIndicador", query = "SELECT a FROM Analise a WHERE a.indicador = :indicador"),
    @NamedQuery(name = "Analise.findByCriterioDeAnalise", query = "SELECT a FROM Analise a WHERE a.criterioDeAnalise = :criterioDeAnalise")})
public class Analise implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AnalisePK analisePK;
    @Column(name = "versao")
    private String versao;
    @Column(name = "indicador")
    private String indicador;
    @Column(name = "criterioDeAnalise")
    private String criterioDeAnalise;
    @JoinColumns({
        @JoinColumn(name = "Medida_id", referencedColumnName = "id", insertable = false, updatable = false),
        @JoinColumn(name = "Medida_Projeto_id", referencedColumnName = "Projeto_id", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Medida medida;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "analise")
    private List<Registroanalise> registroanaliseList;

    public Analise() {
    }

    public Analise(AnalisePK analisePK) {
        this.analisePK = analisePK;
    }

    public Analise(int id, int medidaid, int medidaProjetoid) {
        this.analisePK = new AnalisePK(id, medidaid, medidaProjetoid);
    }

    public AnalisePK getAnalisePK() {
        return analisePK;
    }

    public void setAnalisePK(AnalisePK analisePK) {
        this.analisePK = analisePK;
    }

    public String getVersao() {
        return versao;
    }

    public void setVersao(String versao) {
        this.versao = versao;
    }

    public String getIndicador() {
        return indicador;
    }

    public void setIndicador(String indicador) {
        this.indicador = indicador;
    }

    public String getCriterioDeAnalise() {
        return criterioDeAnalise;
    }

    public void setCriterioDeAnalise(String criterioDeAnalise) {
        this.criterioDeAnalise = criterioDeAnalise;
    }

    public Medida getMedida() {
        return medida;
    }

    public void setMedida(Medida medida) {
        this.medida = medida;
    }

    @XmlTransient
    public List<Registroanalise> getRegistroanaliseList() {
        return registroanaliseList;
    }

    public void setRegistroanaliseList(List<Registroanalise> registroanaliseList) {
        this.registroanaliseList = registroanaliseList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (analisePK != null ? analisePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Analise)) {
            return false;
        }
        Analise other = (Analise) object;
        if ((this.analisePK == null && other.analisePK != null) || (this.analisePK != null && !this.analisePK.equals(other.analisePK)))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "model.Analise[ analisePK=" + analisePK + " ]";
    }
    
}
