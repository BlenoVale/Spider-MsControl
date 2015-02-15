/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author BlenoVale
 */
@Entity
@Table(name = "procedimentodecoleta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Procedimentodecoleta.findAll", query = "SELECT p FROM Procedimentodecoleta p"),
    @NamedQuery(name = "Procedimentodecoleta.findById", query = "SELECT p FROM Procedimentodecoleta p WHERE p.id = :id"),
    @NamedQuery(name = "Procedimentodecoleta.findByMomento", query = "SELECT p FROM Procedimentodecoleta p WHERE p.momento = :momento"),
    @NamedQuery(name = "Procedimentodecoleta.findByPeriodicidadeInicio", query = "SELECT p FROM Procedimentodecoleta p WHERE p.periodicidadeInicio = :periodicidadeInicio"),
    @NamedQuery(name = "Procedimentodecoleta.findByPeriodicidadeFim", query = "SELECT p FROM Procedimentodecoleta p WHERE p.periodicidadeFim = :periodicidadeFim"),
    @NamedQuery(name = "Procedimentodecoleta.findByTipoDeColeta", query = "SELECT p FROM Procedimentodecoleta p WHERE p.tipoDeColeta = :tipoDeColeta"),
    @NamedQuery(name = "Procedimentodecoleta.findByFerramentasUtilizada", query = "SELECT p FROM Procedimentodecoleta p WHERE p.ferramentasUtilizada = :ferramentasUtilizada"),
    @NamedQuery(name = "Procedimentodecoleta.findByResponsavelPelaPreservacao", query = "SELECT p FROM Procedimentodecoleta p WHERE p.responsavelPelaPreservacao = :responsavelPelaPreservacao")})
public class Procedimentodecoleta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "momento")
    private String momento;
    @Basic(optional = false)
    @Column(name = "periodicidadeInicio")
    @Temporal(TemporalType.DATE)
    private Date periodicidadeInicio;
    @Basic(optional = false)
    @Column(name = "periodicidadeFim")
    @Temporal(TemporalType.DATE)
    private Date periodicidadeFim;
    @Basic(optional = false)
    @Lob
    @Column(name = "frequencia")
    private String frequencia;
    @Basic(optional = false)
    @Lob
    @Column(name = "descricao")
    private String descricao;
    @Basic(optional = false)
    @Column(name = "tipoDeColeta")
    private String tipoDeColeta;
    @Basic(optional = false)
    @Column(name = "ferramentasUtilizada")
    private String ferramentasUtilizada;
    @Basic(optional = false)
    @Column(name = "responsavelPelaPreservacao")
    private String responsavelPelaPreservacao;
    @Basic(optional = false)
    @Lob
    @Column(name = "formula")
    private String formula;
    @Lob
    @Column(name = "observacao")
    private String observacao;
    @JoinColumn(name = "Medida_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Medida medidaid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "procedimentoDeColetaid")
    private List<Registroprocedimentocoleta> registroprocedimentocoletaList;

    public Procedimentodecoleta() {
    }

    public Procedimentodecoleta(Integer id) {
        this.id = id;
    }

    public Procedimentodecoleta(Integer id, String momento, Date periodicidadeInicio, Date periodicidadeFim, String frequencia, String descricao, String tipoDeColeta, String ferramentasUtilizada, String responsavelPelaPreservacao, String formula) {
        this.id = id;
        this.momento = momento;
        this.periodicidadeInicio = periodicidadeInicio;
        this.periodicidadeFim = periodicidadeFim;
        this.frequencia = frequencia;
        this.descricao = descricao;
        this.tipoDeColeta = tipoDeColeta;
        this.ferramentasUtilizada = ferramentasUtilizada;
        this.responsavelPelaPreservacao = responsavelPelaPreservacao;
        this.formula = formula;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMomento() {
        return momento;
    }

    public void setMomento(String momento) {
        this.momento = momento;
    }

    public Date getPeriodicidadeInicio() {
        return periodicidadeInicio;
    }

    public void setPeriodicidadeInicio(Date periodicidadeInicio) {
        this.periodicidadeInicio = periodicidadeInicio;
    }

    public Date getPeriodicidadeFim() {
        return periodicidadeFim;
    }

    public void setPeriodicidadeFim(Date periodicidadeFim) {
        this.periodicidadeFim = periodicidadeFim;
    }

    public String getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(String frequencia) {
        this.frequencia = frequencia;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipoDeColeta() {
        return tipoDeColeta;
    }

    public void setTipoDeColeta(String tipoDeColeta) {
        this.tipoDeColeta = tipoDeColeta;
    }

    public String getFerramentasUtilizada() {
        return ferramentasUtilizada;
    }

    public void setFerramentasUtilizada(String ferramentasUtilizada) {
        this.ferramentasUtilizada = ferramentasUtilizada;
    }

    public String getResponsavelPelaPreservacao() {
        return responsavelPelaPreservacao;
    }

    public void setResponsavelPelaPreservacao(String responsavelPelaPreservacao) {
        this.responsavelPelaPreservacao = responsavelPelaPreservacao;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Medida getMedidaid() {
        return medidaid;
    }

    public void setMedidaid(Medida medidaid) {
        this.medidaid = medidaid;
    }

    @XmlTransient
    public List<Registroprocedimentocoleta> getRegistroprocedimentocoletaList() {
        return registroprocedimentocoletaList;
    }

    public void setRegistroprocedimentocoletaList(List<Registroprocedimentocoleta> registroprocedimentocoletaList) {
        this.registroprocedimentocoletaList = registroprocedimentocoletaList;
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
        if (!(object instanceof Procedimentodecoleta)) {
            return false;
        }
        Procedimentodecoleta other = (Procedimentodecoleta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Procedimentodecoleta[ id=" + id + " ]";
    }
    
}
