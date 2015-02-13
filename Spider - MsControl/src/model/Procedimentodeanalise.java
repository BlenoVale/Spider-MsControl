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
 * @author Spider
 */
@Entity
@Table(name = "procedimentodeanalise")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Procedimentodeanalise.findAll", query = "SELECT p FROM Procedimentodeanalise p"),
    @NamedQuery(name = "Procedimentodeanalise.findById", query = "SELECT p FROM Procedimentodeanalise p WHERE p.id = :id"),
    @NamedQuery(name = "Procedimentodeanalise.findByResponsavel", query = "SELECT p FROM Procedimentodeanalise p WHERE p.responsavel = :responsavel"),
    @NamedQuery(name = "Procedimentodeanalise.findByComposicao", query = "SELECT p FROM Procedimentodeanalise p WHERE p.composicao = :composicao"),
    @NamedQuery(name = "Procedimentodeanalise.findByPeriodicidadeInicio", query = "SELECT p FROM Procedimentodeanalise p WHERE p.periodicidadeInicio = :periodicidadeInicio"),
    @NamedQuery(name = "Procedimentodeanalise.findByPeriodicidadeFim", query = "SELECT p FROM Procedimentodeanalise p WHERE p.periodicidadeFim = :periodicidadeFim"),
    @NamedQuery(name = "Procedimentodeanalise.findByGraficoNome", query = "SELECT p FROM Procedimentodeanalise p WHERE p.graficoNome = :graficoNome"),
    @NamedQuery(name = "Procedimentodeanalise.findByMeta", query = "SELECT p FROM Procedimentodeanalise p WHERE p.meta = :meta"),
    @NamedQuery(name = "Procedimentodeanalise.findByCriterioDeAnalise", query = "SELECT p FROM Procedimentodeanalise p WHERE p.criterioDeAnalise = :criterioDeAnalise"),
    @NamedQuery(name = "Procedimentodeanalise.findByAcoes", query = "SELECT p FROM Procedimentodeanalise p WHERE p.acoes = :acoes"),
    @NamedQuery(name = "Procedimentodeanalise.findByComunicacao", query = "SELECT p FROM Procedimentodeanalise p WHERE p.comunicacao = :comunicacao")})
public class Procedimentodeanalise implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "responsavel")
    private String responsavel;
    @Basic(optional = false)
    @Column(name = "composicao")
    private String composicao;
    @Basic(optional = false)
    @Lob
    @Column(name = "formula")
    private String formula;
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
    @Column(name = "graficoNome")
    private String graficoNome;
    @Basic(optional = false)
    @Column(name = "meta")
    private String meta;
    @Basic(optional = false)
    @Column(name = "criterioDeAnalise")
    private String criterioDeAnalise;
    @Basic(optional = false)
    @Column(name = "acoes")
    private String acoes;
    @Basic(optional = false)
    @Column(name = "comunicacao")
    private String comunicacao;
    @Lob
    @Column(name = "observacao")
    private String observacao;
    @JoinColumn(name = "Indicador_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Indicador indicadorid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "procedimentoDeAnaliseid")
    private List<Registroprocedimentoanalise> registroprocedimentoanaliseList;

    public Procedimentodeanalise() {
    }

    public Procedimentodeanalise(Integer id) {
        this.id = id;
    }

    public Procedimentodeanalise(Integer id, String responsavel, String composicao, String formula, Date periodicidadeInicio, Date periodicidadeFim, String frequencia, String graficoNome, String meta, String criterioDeAnalise, String acoes, String comunicacao) {
        this.id = id;
        this.responsavel = responsavel;
        this.composicao = composicao;
        this.formula = formula;
        this.periodicidadeInicio = periodicidadeInicio;
        this.periodicidadeFim = periodicidadeFim;
        this.frequencia = frequencia;
        this.graficoNome = graficoNome;
        this.meta = meta;
        this.criterioDeAnalise = criterioDeAnalise;
        this.acoes = acoes;
        this.comunicacao = comunicacao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getComposicao() {
        return composicao;
    }

    public void setComposicao(String composicao) {
        this.composicao = composicao;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
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

    public String getGraficoNome() {
        return graficoNome;
    }

    public void setGraficoNome(String graficoNome) {
        this.graficoNome = graficoNome;
    }

    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }

    public String getCriterioDeAnalise() {
        return criterioDeAnalise;
    }

    public void setCriterioDeAnalise(String criterioDeAnalise) {
        this.criterioDeAnalise = criterioDeAnalise;
    }

    public String getAcoes() {
        return acoes;
    }

    public void setAcoes(String acoes) {
        this.acoes = acoes;
    }

    public String getComunicacao() {
        return comunicacao;
    }

    public void setComunicacao(String comunicacao) {
        this.comunicacao = comunicacao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Indicador getIndicadorid() {
        return indicadorid;
    }

    public void setIndicadorid(Indicador indicadorid) {
        this.indicadorid = indicadorid;
    }

    @XmlTransient
    public List<Registroprocedimentoanalise> getRegistroprocedimentoanaliseList() {
        return registroprocedimentoanaliseList;
    }

    public void setRegistroprocedimentoanaliseList(List<Registroprocedimentoanalise> registroprocedimentoanaliseList) {
        this.registroprocedimentoanaliseList = registroprocedimentoanaliseList;
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
        if (!(object instanceof Procedimentodeanalise)) {
            return false;
        }
        Procedimentodeanalise other = (Procedimentodeanalise) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Procedimentodeanalise[ id=" + id + " ]";
    }
    
}
