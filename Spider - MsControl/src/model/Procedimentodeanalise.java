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
 * @author Bleno Vale
 */
@Entity
@Table(name = "procedimentodeanalise")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Procedimentodeanalise.findAll", query = "SELECT p FROM Procedimentodeanalise p"),
    @NamedQuery(name = "Procedimentodeanalise.findById", query = "SELECT p FROM Procedimentodeanalise p WHERE p.id = :id"),
    @NamedQuery(name = "Procedimentodeanalise.findByResponsavel", query = "SELECT p FROM Procedimentodeanalise p WHERE p.responsavel = :responsavel"),
    @NamedQuery(name = "Procedimentodeanalise.findByComposicao", query = "SELECT p FROM Procedimentodeanalise p WHERE p.composicao = :composicao"),
    @NamedQuery(name = "Procedimentodeanalise.findByPeriodicidade", query = "SELECT p FROM Procedimentodeanalise p WHERE p.periodicidade = :periodicidade"),
    @NamedQuery(name = "Procedimentodeanalise.findByGraficoNome", query = "SELECT p FROM Procedimentodeanalise p WHERE p.graficoNome = :graficoNome"),
    @NamedQuery(name = "Procedimentodeanalise.findByMetaOk", query = "SELECT p FROM Procedimentodeanalise p WHERE p.metaOk = :metaOk"),
    @NamedQuery(name = "Procedimentodeanalise.findByMetaAlerta", query = "SELECT p FROM Procedimentodeanalise p WHERE p.metaAlerta = :metaAlerta"),
    @NamedQuery(name = "Procedimentodeanalise.findByMetaCritico", query = "SELECT p FROM Procedimentodeanalise p WHERE p.metaCritico = :metaCritico"),
    @NamedQuery(name = "Procedimentodeanalise.findByDataComunicacao", query = "SELECT p FROM Procedimentodeanalise p WHERE p.dataComunicacao = :dataComunicacao"),
    @NamedQuery(name = "Procedimentodeanalise.findByProjetoId", query = "SELECT p FROM Procedimentodeanalise p WHERE p.projetoId = :projetoId")})
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
    @Column(name = "periodicidade")
    private String periodicidade;
    @Basic(optional = false)
    @Lob
    @Column(name = "frequencia")
    private String frequencia;
    @Basic(optional = false)
    @Column(name = "graficoNome")
    private String graficoNome;
    @Basic(optional = false)
    @Column(name = "metaOk")
    private double metaOk;
    @Basic(optional = false)
    @Column(name = "metaAlerta")
    private double metaAlerta;
    @Basic(optional = false)
    @Column(name = "metaCritico")
    private double metaCritico;
    @Basic(optional = false)
    @Lob
    @Column(name = "criterioOk")
    private String criterioOk;
    @Basic(optional = false)
    @Lob
    @Column(name = "criterioAlerta")
    private String criterioAlerta;
    @Basic(optional = false)
    @Lob
    @Column(name = "criterioCritico")
    private String criterioCritico;
    @Basic(optional = false)
    @Lob
    @Column(name = "acoesOk")
    private String acoesOk;
    @Basic(optional = false)
    @Lob
    @Column(name = "acoesAlerta")
    private String acoesAlerta;
    @Basic(optional = false)
    @Lob
    @Column(name = "acoesCritico")
    private String acoesCritico;
    @Basic(optional = false)
    @Column(name = "dataComunicacao")
    @Temporal(TemporalType.DATE)
    private Date dataComunicacao;
    @Lob
    @Column(name = "observacao")
    private String observacao;
    @Basic(optional = false)
    @Column(name = "projeto_id")
    private int projetoId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "procedimentoDeAnaliseid")
    private List<Perfisinteressadosprocedimentoanalise> perfisinteressadosprocedimentoanaliseList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "procedimentoDeAnaliseid")
    private List<Registrodatacomunicacao> registrodatacomunicacaoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "procedimentoDeAnaliseid")
    private List<Registroprocedimentoanalise> registroprocedimentoanaliseList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "procedimentoDeAnaliseid")
    private List<Meiosprocedimentoanalise> meiosprocedimentoanaliseList;
    @JoinColumn(name = "Indicador_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Indicador indicadorid;

    public Procedimentodeanalise() {
    }

    public Procedimentodeanalise(Integer id) {
        this.id = id;
    }

    public Procedimentodeanalise(Integer id, String responsavel, String composicao, String formula, String periodicidade, String frequencia, String graficoNome, double metaOk, double metaAlerta, double metaCritico, String criterioOk, String criterioAlerta, String criterioCritico, String acoesOk, String acoesAlerta, String acoesCritico, Date dataComunicacao, int projetoId) {
        this.id = id;
        this.responsavel = responsavel;
        this.composicao = composicao;
        this.formula = formula;
        this.periodicidade = periodicidade;
        this.frequencia = frequencia;
        this.graficoNome = graficoNome;
        this.metaOk = metaOk;
        this.metaAlerta = metaAlerta;
        this.metaCritico = metaCritico;
        this.criterioOk = criterioOk;
        this.criterioAlerta = criterioAlerta;
        this.criterioCritico = criterioCritico;
        this.acoesOk = acoesOk;
        this.acoesAlerta = acoesAlerta;
        this.acoesCritico = acoesCritico;
        this.dataComunicacao = dataComunicacao;
        this.projetoId = projetoId;
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

    public String getPeriodicidade() {
        return periodicidade;
    }

    public void setPeriodicidade(String periodicidade) {
        this.periodicidade = periodicidade;
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

    public double getMetaOk() {
        return metaOk;
    }

    public void setMetaOk(double metaOk) {
        this.metaOk = metaOk;
    }

    public double getMetaAlerta() {
        return metaAlerta;
    }

    public void setMetaAlerta(double metaAlerta) {
        this.metaAlerta = metaAlerta;
    }

    public double getMetaCritico() {
        return metaCritico;
    }

    public void setMetaCritico(double metaCritico) {
        this.metaCritico = metaCritico;
    }

    public String getCriterioOk() {
        return criterioOk;
    }

    public void setCriterioOk(String criterioOk) {
        this.criterioOk = criterioOk;
    }

    public String getCriterioAlerta() {
        return criterioAlerta;
    }

    public void setCriterioAlerta(String criterioAlerta) {
        this.criterioAlerta = criterioAlerta;
    }

    public String getCriterioCritico() {
        return criterioCritico;
    }

    public void setCriterioCritico(String criterioCritico) {
        this.criterioCritico = criterioCritico;
    }

    public String getAcoesOk() {
        return acoesOk;
    }

    public void setAcoesOk(String acoesOk) {
        this.acoesOk = acoesOk;
    }

    public String getAcoesAlerta() {
        return acoesAlerta;
    }

    public void setAcoesAlerta(String acoesAlerta) {
        this.acoesAlerta = acoesAlerta;
    }

    public String getAcoesCritico() {
        return acoesCritico;
    }

    public void setAcoesCritico(String acoesCritico) {
        this.acoesCritico = acoesCritico;
    }

    public Date getDataComunicacao() {
        return dataComunicacao;
    }

    public void setDataComunicacao(Date dataComunicacao) {
        this.dataComunicacao = dataComunicacao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public int getProjetoId() {
        return projetoId;
    }

    public void setProjetoId(int projetoId) {
        this.projetoId = projetoId;
    }

    @XmlTransient
    public List<Perfisinteressadosprocedimentoanalise> getPerfisinteressadosprocedimentoanaliseList() {
        return perfisinteressadosprocedimentoanaliseList;
    }

    public void setPerfisinteressadosprocedimentoanaliseList(List<Perfisinteressadosprocedimentoanalise> perfisinteressadosprocedimentoanaliseList) {
        this.perfisinteressadosprocedimentoanaliseList = perfisinteressadosprocedimentoanaliseList;
    }

    @XmlTransient
    public List<Registrodatacomunicacao> getRegistrodatacomunicacaoList() {
        return registrodatacomunicacaoList;
    }

    public void setRegistrodatacomunicacaoList(List<Registrodatacomunicacao> registrodatacomunicacaoList) {
        this.registrodatacomunicacaoList = registrodatacomunicacaoList;
    }

    @XmlTransient
    public List<Registroprocedimentoanalise> getRegistroprocedimentoanaliseList() {
        return registroprocedimentoanaliseList;
    }

    public void setRegistroprocedimentoanaliseList(List<Registroprocedimentoanalise> registroprocedimentoanaliseList) {
        this.registroprocedimentoanaliseList = registroprocedimentoanaliseList;
    }

    @XmlTransient
    public List<Meiosprocedimentoanalise> getMeiosprocedimentoanaliseList() {
        return meiosprocedimentoanaliseList;
    }

    public void setMeiosprocedimentoanaliseList(List<Meiosprocedimentoanalise> meiosprocedimentoanaliseList) {
        this.meiosprocedimentoanaliseList = meiosprocedimentoanaliseList;
    }

    public Indicador getIndicadorid() {
        return indicadorid;
    }

    public void setIndicadorid(Indicador indicadorid) {
        this.indicadorid = indicadorid;
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
