/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

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
    @NamedQuery(name = "Procedimentodeanalise.findByVersao", query = "SELECT p FROM Procedimentodeanalise p WHERE p.versao = :versao"),
    @NamedQuery(name = "Procedimentodeanalise.findByPeriodicidadeInicio", query = "SELECT p FROM Procedimentodeanalise p WHERE p.periodicidadeInicio = :periodicidadeInicio"),
    @NamedQuery(name = "Procedimentodeanalise.findByPeriodicidadeFim", query = "SELECT p FROM Procedimentodeanalise p WHERE p.periodicidadeFim = :periodicidadeFim"),
    @NamedQuery(name = "Procedimentodeanalise.findByGraficoNome", query = "SELECT p FROM Procedimentodeanalise p WHERE p.graficoNome = :graficoNome"),
    @NamedQuery(name = "Procedimentodeanalise.findByLimiteAnalise", query = "SELECT p FROM Procedimentodeanalise p WHERE p.limiteAnalise = :limiteAnalise"),
    @NamedQuery(name = "Procedimentodeanalise.findByCriterioAnalise", query = "SELECT p FROM Procedimentodeanalise p WHERE p.criterioAnalise = :criterioAnalise"),
    @NamedQuery(name = "Procedimentodeanalise.findByAcoesAnalise", query = "SELECT p FROM Procedimentodeanalise p WHERE p.acoesAnalise = :acoesAnalise"),
    @NamedQuery(name = "Procedimentodeanalise.findByComunicacao", query = "SELECT p FROM Procedimentodeanalise p WHERE p.comunicacao = :comunicacao")})
public class Procedimentodeanalise implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "versao")
    private String versao;
    @Column(name = "periodicidade_inicio")
    @Temporal(TemporalType.DATE)
    private Date periodicidadeInicio;
    @Column(name = "periodicidade_fim")
    @Temporal(TemporalType.DATE)
    private Date periodicidadeFim;
    @Lob
    @Column(name = "frequencia")
    private String frequencia;
    @Column(name = "grafico_nome")
    private String graficoNome;
    @Column(name = "limite_analise")
    private String limiteAnalise;
    @Column(name = "criterio_analise")
    private String criterioAnalise;
    @Column(name = "acoes_analise")
    private String acoesAnalise;
    @Column(name = "comunicacao")
    private String comunicacao;
    @JoinColumns({
        @JoinColumn(name = "Coleta_id", referencedColumnName = "id"),
        @JoinColumn(name = "Coleta_Medida_id", referencedColumnName = "Medida_id"),
        @JoinColumn(name = "Coleta_Medida_Projeto_id", referencedColumnName = "Medida_Projeto_id")})
    @ManyToOne(optional = false)
    private Coleta coleta;

    public Procedimentodeanalise() {
    }

    public Procedimentodeanalise(Integer id) {
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

    public String getLimiteAnalise() {
        return limiteAnalise;
    }

    public void setLimiteAnalise(String limiteAnalise) {
        this.limiteAnalise = limiteAnalise;
    }

    public String getCriterioAnalise() {
        return criterioAnalise;
    }

    public void setCriterioAnalise(String criterioAnalise) {
        this.criterioAnalise = criterioAnalise;
    }

    public String getAcoesAnalise() {
        return acoesAnalise;
    }

    public void setAcoesAnalise(String acoesAnalise) {
        this.acoesAnalise = acoesAnalise;
    }

    public String getComunicacao() {
        return comunicacao;
    }

    public void setComunicacao(String comunicacao) {
        this.comunicacao = comunicacao;
    }

    public Coleta getColeta() {
        return coleta;
    }

    public void setColeta(Coleta coleta) {
        this.coleta = coleta;
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
