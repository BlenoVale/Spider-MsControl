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
@Table(name = "coleta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Coleta.findAll", query = "SELECT c FROM Coleta c"),
    @NamedQuery(name = "Coleta.findById", query = "SELECT c FROM Coleta c WHERE c.id = :id"),
    @NamedQuery(name = "Coleta.findByData", query = "SELECT c FROM Coleta c WHERE c.data = :data"),
    @NamedQuery(name = "Coleta.findByComposicao", query = "SELECT c FROM Coleta c WHERE c.composicao = :composicao"),
    @NamedQuery(name = "Coleta.findByTipoDeColeta", query = "SELECT c FROM Coleta c WHERE c.tipoDeColeta = :tipoDeColeta"),
    @NamedQuery(name = "Coleta.findByTipoComposicao", query = "SELECT c FROM Coleta c WHERE c.tipoComposicao = :tipoComposicao"),
    @NamedQuery(name = "Coleta.findByObservacao", query = "SELECT c FROM Coleta c WHERE c.observacao = :observacao")})
public class Coleta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "data")
    @Temporal(TemporalType.DATE)
    private Date data;
    @Basic(optional = false)
    @Column(name = "composicao")
    private String composicao;
    @Column(name = "tipoDeColeta")
    private String tipoDeColeta;
    @Basic(optional = false)
    @Column(name = "tipoComposicao")
    private String tipoComposicao;
    @Column(name = "observacao")
    private String observacao;
    @JoinColumn(name = "Medida_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Medida medidaid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "coletaid")
    private List<Registrocoleta> registrocoletaList;

    public Coleta() {
    }

    public Coleta(Integer id) {
        this.id = id;
    }

    public Coleta(Integer id, Date data, String composicao, String tipoComposicao) {
        this.id = id;
        this.data = data;
        this.composicao = composicao;
        this.tipoComposicao = tipoComposicao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getComposicao() {
        return composicao;
    }

    public void setComposicao(String composicao) {
        this.composicao = composicao;
    }

    public String getTipoDeColeta() {
        return tipoDeColeta;
    }

    public void setTipoDeColeta(String tipoDeColeta) {
        this.tipoDeColeta = tipoDeColeta;
    }

    public String getTipoComposicao() {
        return tipoComposicao;
    }

    public void setTipoComposicao(String tipoComposicao) {
        this.tipoComposicao = tipoComposicao;
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
    public List<Registrocoleta> getRegistrocoletaList() {
        return registrocoletaList;
    }

    public void setRegistrocoletaList(List<Registrocoleta> registrocoletaList) {
        this.registrocoletaList = registrocoletaList;
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
        if (!(object instanceof Coleta)) {
            return false;
        }
        Coleta other = (Coleta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Coleta[ id=" + id + " ]";
    }
    
}
