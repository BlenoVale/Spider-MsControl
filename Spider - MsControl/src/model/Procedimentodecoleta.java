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
    @NamedQuery(name = "Procedimentodecoleta.findByProjetoId", query = "SELECT p FROM Procedimentodecoleta p WHERE p.projetoId = :projetoId"),
    @NamedQuery(name = "Procedimentodecoleta.findByResponsavelPelaColeta", query = "SELECT p FROM Procedimentodecoleta p WHERE p.responsavelPelaColeta = :responsavelPelaColeta"),
    @NamedQuery(name = "Procedimentodecoleta.findByMomento", query = "SELECT p FROM Procedimentodecoleta p WHERE p.momento = :momento"),
    @NamedQuery(name = "Procedimentodecoleta.findByPeriodicidade", query = "SELECT p FROM Procedimentodecoleta p WHERE p.periodicidade = :periodicidade"),
    @NamedQuery(name = "Procedimentodecoleta.findByFrequencia", query = "SELECT p FROM Procedimentodecoleta p WHERE p.frequencia = :frequencia"),
    @NamedQuery(name = "Procedimentodecoleta.findByTipoDeColeta", query = "SELECT p FROM Procedimentodecoleta p WHERE p.tipoDeColeta = :tipoDeColeta"),
    @NamedQuery(name = "Procedimentodecoleta.findByCalculo", query = "SELECT p FROM Procedimentodecoleta p WHERE p.calculo = :calculo"),
    @NamedQuery(name = "Procedimentodecoleta.findByFerramentasUtilizada", query = "SELECT p FROM Procedimentodecoleta p WHERE p.ferramentasUtilizada = :ferramentasUtilizada"),
    @NamedQuery(name = "Procedimentodecoleta.findByData", query = "SELECT p FROM Procedimentodecoleta p WHERE p.data = :data"),
    @NamedQuery(name = "Procedimentodecoleta.findByPorcentagem", query = "SELECT p FROM Procedimentodecoleta p WHERE p.porcentagem = :porcentagem"),
    @NamedQuery(name = "Procedimentodecoleta.findByContadorColeta", query = "SELECT p FROM Procedimentodecoleta p WHERE p.contadorColeta = :contadorColeta")})
public class Procedimentodecoleta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "projeto_id")
    private int projetoId;
    @Basic(optional = false)
    @Column(name = "responsavelPelaColeta")
    private String responsavelPelaColeta;
    @Basic(optional = false)
    @Column(name = "momento")
    private String momento;
    @Basic(optional = false)
    @Column(name = "periodicidade")
    private String periodicidade;
    @Basic(optional = false)
    @Column(name = "frequencia")
    private int frequencia;
    @Basic(optional = false)
    @Lob
    @Column(name = "passosColeta")
    private String passosColeta;
    @Basic(optional = false)
    @Column(name = "tipoDeColeta")
    private String tipoDeColeta;
    @Basic(optional = false)
    @Column(name = "calculo")
    private String calculo;
    @Basic(optional = false)
    @Column(name = "ferramentasUtilizada")
    private String ferramentasUtilizada;
    @Lob
    @Column(name = "observacao")
    private String observacao;
    @Basic(optional = false)
    @Column(name = "data")
    @Temporal(TemporalType.DATE)
    private Date data;
    @Basic(optional = false)
    @Column(name = "porcentagem")
    private double porcentagem;
    @Column(name = "contadorColeta")
    private Integer contadorColeta;
    @JoinColumn(name = "Medida_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Medida medidaid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "procedimentoDeColetaid")
    private List<Registroprocedimentocoleta> registroprocedimentocoletaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "procedimentoDeColetaid")
    private List<Datasprocedimentocoleta> datasprocedimentocoletaList;

    public Procedimentodecoleta() {
    }

    public Procedimentodecoleta(Integer id) {
        this.id = id;
    }

    public Procedimentodecoleta(Integer id, int projetoId, String responsavelPelaColeta, String momento, String periodicidade, int frequencia, String passosColeta, String tipoDeColeta, String calculo, String ferramentasUtilizada, Date data, double porcentagem) {
        this.id = id;
        this.projetoId = projetoId;
        this.responsavelPelaColeta = responsavelPelaColeta;
        this.momento = momento;
        this.periodicidade = periodicidade;
        this.frequencia = frequencia;
        this.passosColeta = passosColeta;
        this.tipoDeColeta = tipoDeColeta;
        this.calculo = calculo;
        this.ferramentasUtilizada = ferramentasUtilizada;
        this.data = data;
        this.porcentagem = porcentagem;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getProjetoId() {
        return projetoId;
    }

    public void setProjetoId(int projetoId) {
        this.projetoId = projetoId;
    }

    public String getResponsavelPelaColeta() {
        return responsavelPelaColeta;
    }

    public void setResponsavelPelaColeta(String responsavelPelaColeta) {
        this.responsavelPelaColeta = responsavelPelaColeta;
    }

    public String getMomento() {
        return momento;
    }

    public void setMomento(String momento) {
        this.momento = momento;
    }

    public String getPeriodicidade() {
        return periodicidade;
    }

    public void setPeriodicidade(String periodicidade) {
        this.periodicidade = periodicidade;
    }

    public int getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(int frequencia) {
        this.frequencia = frequencia;
    }

    public String getPassosColeta() {
        return passosColeta;
    }

    public void setPassosColeta(String passosColeta) {
        this.passosColeta = passosColeta;
    }

    public String getTipoDeColeta() {
        return tipoDeColeta;
    }

    public void setTipoDeColeta(String tipoDeColeta) {
        this.tipoDeColeta = tipoDeColeta;
    }

    public String getCalculo() {
        return calculo;
    }

    public void setCalculo(String calculo) {
        this.calculo = calculo;
    }

    public String getFerramentasUtilizada() {
        return ferramentasUtilizada;
    }

    public void setFerramentasUtilizada(String ferramentasUtilizada) {
        this.ferramentasUtilizada = ferramentasUtilizada;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public double getPorcentagem() {
        return porcentagem;
    }

    public void setPorcentagem(double porcentagem) {
        this.porcentagem = porcentagem;
    }

    public Integer getContadorColeta() {
        return contadorColeta;
    }

    public void setContadorColeta(Integer contadorColeta) {
        this.contadorColeta = contadorColeta;
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

    @XmlTransient
    public List<Datasprocedimentocoleta> getDatasprocedimentocoletaList() {
        return datasprocedimentocoletaList;
    }

    public void setDatasprocedimentocoletaList(List<Datasprocedimentocoleta> datasprocedimentocoletaList) {
        this.datasprocedimentocoletaList = datasprocedimentocoletaList;
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
