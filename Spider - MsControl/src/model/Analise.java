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
    @NamedQuery(name = "Analise.findByDataCria\u00e7\u00e3o", query = "SELECT a FROM Analise a WHERE a.dataCria\u00e7\u00e3o = :dataCria\u00e7\u00e3o"),
    @NamedQuery(name = "Analise.findByNomeUsuario", query = "SELECT a FROM Analise a WHERE a.nomeUsuario = :nomeUsuario"),
    @NamedQuery(name = "Analise.findByAnaliseDE", query = "SELECT a FROM Analise a WHERE a.analiseDE = :analiseDE"),
    @NamedQuery(name = "Analise.findByAnaliseATE", query = "SELECT a FROM Analise a WHERE a.analiseATE = :analiseATE")})
public class Analise implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Lob
    @Column(name = "criterioDeAnalise")
    private String criterioDeAnalise;
    @Basic(optional = false)
    @Column(name = "DataCria\u00e7\u00e3o")
    @Temporal(TemporalType.DATE)
    private Date dataCriação;
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

    public Analise(Integer id, String criterioDeAnalise, Date dataCriação, String nomeUsuario, Date analiseDE, Date analiseATE) {
        this.id = id;
        this.criterioDeAnalise = criterioDeAnalise;
        this.dataCriação = dataCriação;
        this.nomeUsuario = nomeUsuario;
        this.analiseDE = analiseDE;
        this.analiseATE = analiseATE;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCriterioDeAnalise() {
        return criterioDeAnalise;
    }

    public void setCriterioDeAnalise(String criterioDeAnalise) {
        this.criterioDeAnalise = criterioDeAnalise;
    }

    public Date getDataCriação() {
        return dataCriação;
    }

    public void setDataCriação(Date dataCriação) {
        this.dataCriação = dataCriação;
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
