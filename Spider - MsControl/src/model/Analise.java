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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "analise")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Analise.findAll", query = "SELECT a FROM Analise a"),
    @NamedQuery(name = "Analise.findById", query = "SELECT a FROM Analise a WHERE a.id = :id"),
    @NamedQuery(name = "Analise.findByDataCriacao", query = "SELECT a FROM Analise a WHERE a.dataCriacao = :dataCriacao"),
    @NamedQuery(name = "Analise.findByNomeUsuario", query = "SELECT a FROM Analise a WHERE a.nomeUsuario = :nomeUsuario"),
    @NamedQuery(name = "Analise.findByAnaliseDE", query = "SELECT a FROM Analise a WHERE a.analiseDE = :analiseDE"),
    @NamedQuery(name = "Analise.findByAnaliseATE", query = "SELECT a FROM Analise a WHERE a.analiseATE = :analiseATE"),
    @NamedQuery(name = "Analise.findByValorAtualdoIndicador", query = "SELECT a FROM Analise a WHERE a.valorAtualdoIndicador = :valorAtualdoIndicador")})
public class Analise implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Lob
    @Column(name = "analise")
    private String analise;
    @Basic(optional = false)
    @Column(name = "DataCriacao")
    @Temporal(TemporalType.DATE)
    private Date dataCriacao;
    @Basic(optional = false)
    @Column(name = "nomeUsuario")
    private String nomeUsuario;
    @Lob
    @Column(name = "observacao")
    private String observacao;
    @Basic(optional = false)
    @Column(name = "analiseDE")
    @Temporal(TemporalType.DATE)
    private Date analiseDE;
    @Basic(optional = false)
    @Column(name = "analiseATE")
    @Temporal(TemporalType.DATE)
    private Date analiseATE;
    @Basic(optional = false)
    @Column(name = "valorAtualdoIndicador")
    private String valorAtualdoIndicador;
    @JoinTable(name = "analise_has_resultados", joinColumns = {
        @JoinColumn(name = "Analise_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "Resultados_id", referencedColumnName = "id")})
    @ManyToMany
    private List<Resultados> resultadosList;
    @JoinColumn(name = "Indicador_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Indicador indicadorid;

    public Analise() {
    }

    public Analise(Integer id) {
        this.id = id;
    }

    public Analise(Integer id, String analise, Date dataCriacao, String nomeUsuario, Date analiseDE, Date analiseATE, String valorAtualdoIndicador) {
        this.id = id;
        this.analise = analise;
        this.dataCriacao = dataCriacao;
        this.nomeUsuario = nomeUsuario;
        this.analiseDE = analiseDE;
        this.analiseATE = analiseATE;
        this.valorAtualdoIndicador = valorAtualdoIndicador;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAnalise() {
        return analise;
    }

    public void setAnalise(String analise) {
        this.analise = analise;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Date getAnaliseDE() {
        return analiseDE;
    }

    public void setAnaliseDE(Date analiseDE) {
        this.analiseDE = analiseDE;
    }

    public Date getAnaliseATE() {
        return analiseATE;
    }

    public void setAnaliseATE(Date analiseATE) {
        this.analiseATE = analiseATE;
    }

    public String getValorAtualdoIndicador() {
        return valorAtualdoIndicador;
    }

    public void setValorAtualdoIndicador(String valorAtualdoIndicador) {
        this.valorAtualdoIndicador = valorAtualdoIndicador;
    }

    @XmlTransient
    public List<Resultados> getResultadosList() {
        return resultadosList;
    }

    public void setResultadosList(List<Resultados> resultadosList) {
        this.resultadosList = resultadosList;
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
        if (!(object instanceof Analise)) {
            return false;
        }
        Analise other = (Analise) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Analise[ id=" + id + " ]";
    }
    
}
