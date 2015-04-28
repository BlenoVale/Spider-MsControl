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
@Table(name = "objetivodequestao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Objetivodequestao.findAll", query = "SELECT o FROM Objetivodequestao o"),
    @NamedQuery(name = "Objetivodequestao.findById", query = "SELECT o FROM Objetivodequestao o WHERE o.id = :id"),
    @NamedQuery(name = "Objetivodequestao.findByNome", query = "SELECT o FROM Objetivodequestao o WHERE o.nome = :nome"),
    @NamedQuery(name = "Objetivodequestao.findByPontoDeVista", query = "SELECT o FROM Objetivodequestao o WHERE o.pontoDeVista = :pontoDeVista"),
    @NamedQuery(name = "Objetivodequestao.findByTipoDeDerivacao", query = "SELECT o FROM Objetivodequestao o WHERE o.tipoDeDerivacao = :tipoDeDerivacao"),
    @NamedQuery(name = "Objetivodequestao.findByDataLevantamento", query = "SELECT o FROM Objetivodequestao o WHERE o.dataLevantamento = :dataLevantamento")})
public class Objetivodequestao implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @Column(name = "pontoDeVista")
    private String pontoDeVista;
    @Basic(optional = false)
    @Column(name = "tipoDeDerivacao")
    private String tipoDeDerivacao;
    @Basic(optional = false)
    @Column(name = "dataLevantamento")
    @Temporal(TemporalType.DATE)
    private Date dataLevantamento;
    @Lob
    @Column(name = "observacao")
    private String observacao;
    @JoinColumn(name = "ObjetivoDeMedicao_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Objetivodemedicao objetivoDeMedicaoid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "objetivoDeQuestaoid")
    private List<Registroobjetivoquestao> registroobjetivoquestaoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "objetivoDeQuestaoid")
    private List<Indicador> indicadorList;

    public Objetivodequestao() {
    }

    public Objetivodequestao(Integer id) {
        this.id = id;
    }

    public Objetivodequestao(Integer id, String nome, String pontoDeVista, String tipoDeDerivacao, Date dataLevantamento) {
        this.id = id;
        this.nome = nome;
        this.pontoDeVista = pontoDeVista;
        this.tipoDeDerivacao = tipoDeDerivacao;
        this.dataLevantamento = dataLevantamento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPontoDeVista() {
        return pontoDeVista;
    }

    public void setPontoDeVista(String pontoDeVista) {
        this.pontoDeVista = pontoDeVista;
    }

    public String getTipoDeDerivacao() {
        return tipoDeDerivacao;
    }

    public void setTipoDeDerivacao(String tipoDeDerivacao) {
        this.tipoDeDerivacao = tipoDeDerivacao;
    }

    public Date getDataLevantamento() {
        return dataLevantamento;
    }

    public void setDataLevantamento(Date dataLevantamento) {
        this.dataLevantamento = dataLevantamento;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Objetivodemedicao getObjetivoDeMedicaoid() {
        return objetivoDeMedicaoid;
    }

    public void setObjetivoDeMedicaoid(Objetivodemedicao objetivoDeMedicaoid) {
        this.objetivoDeMedicaoid = objetivoDeMedicaoid;
    }

    @XmlTransient
    public List<Registroobjetivoquestao> getRegistroobjetivoquestaoList() {
        return registroobjetivoquestaoList;
    }

    public void setRegistroobjetivoquestaoList(List<Registroobjetivoquestao> registroobjetivoquestaoList) {
        this.registroobjetivoquestaoList = registroobjetivoquestaoList;
    }

    @XmlTransient
    public List<Indicador> getIndicadorList() {
        return indicadorList;
    }

    public void setIndicadorList(List<Indicador> indicadorList) {
        this.indicadorList = indicadorList;
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
        if (!(object instanceof Objetivodequestao)) {
            return false;
        }
        Objetivodequestao other = (Objetivodequestao) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "model.Objetivodequestao[ id=" + id + " ]";
    }
    
}
