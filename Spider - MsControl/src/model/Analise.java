/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
 * @author Spider
 */
@Entity
@Table(name = "analise")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Analise.findAll", query = "SELECT a FROM Analise a"),
    @NamedQuery(name = "Analise.findById", query = "SELECT a FROM Analise a WHERE a.id = :id"),
    @NamedQuery(name = "Analise.findByVersao", query = "SELECT a FROM Analise a WHERE a.versao = :versao"),
    @NamedQuery(name = "Analise.findByIndicador", query = "SELECT a FROM Analise a WHERE a.indicador = :indicador"),
    @NamedQuery(name = "Analise.findByCriterioDeAnalise", query = "SELECT a FROM Analise a WHERE a.criterioDeAnalise = :criterioDeAnalise")})
public class Analise implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "versao")
    private String versao;
    @Column(name = "indicador")
    private String indicador;
    @Column(name = "criterioDeAnalise")
    private String criterioDeAnalise;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "analiseid")
    private List<Registroanalise> registroanaliseList;
    @JoinColumns({
        @JoinColumn(name = "Medida_id", referencedColumnName = "id"),
        @JoinColumn(name = "Medida_Projeto_id", referencedColumnName = "Projeto_id")})
    @ManyToOne(optional = false)
    private Medida medida;

    public Analise() {
    }

    public Analise(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @XmlTransient
    public List<Registroanalise> getRegistroanaliseList() {
        return registroanaliseList;
    }

    public void setRegistroanaliseList(List<Registroanalise> registroanaliseList) {
        this.registroanaliseList = registroanaliseList;
    }

    public Medida getMedida() {
        return medida;
    }

    public void setMedida(Medida medida) {
        this.medida = medida;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Analise)) {
            return false;
        }
        Analise other = (Analise) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "model.Analise[ id=" + id + " ]";
    }
    
}
