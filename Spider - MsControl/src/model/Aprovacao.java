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
import javax.persistence.JoinColumns;
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
 * @author Dan
 */
@Entity
@Table(name = "aprovacao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Aprovacao.findAll", query = "SELECT a FROM Aprovacao a"),
    @NamedQuery(name = "Aprovacao.findById", query = "SELECT a FROM Aprovacao a WHERE a.id = :id"),
    @NamedQuery(name = "Aprovacao.findByData", query = "SELECT a FROM Aprovacao a WHERE a.data = :data"),
    @NamedQuery(name = "Aprovacao.findByStatus", query = "SELECT a FROM Aprovacao a WHERE a.status = :status")})
public class Aprovacao implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "data")
    @Temporal(TemporalType.DATE)
    private Date data;
    @Lob
    @Column(name = "observacao")
    private String observacao;
    @Column(name = "status")
    private Boolean status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "aprovacao")
    private List<Registroaprovacao> registroaprovacaoList;
    @JoinColumns({
        @JoinColumn(name = "Definicao_id", referencedColumnName = "id"),
        @JoinColumn(name = "Definicao_Medida_id", referencedColumnName = "Medida_id"),
        @JoinColumn(name = "Definicao_Medida_Projeto_id", referencedColumnName = "Medida_Projeto_id")})
    @ManyToOne(optional = false)
    private Definicao definicao;

    public Aprovacao() {
    }

    public Aprovacao(Integer id) {
        this.id = id;
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

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @XmlTransient
    public List<Registroaprovacao> getRegistroaprovacaoList() {
        return registroaprovacaoList;
    }

    public void setRegistroaprovacaoList(List<Registroaprovacao> registroaprovacaoList) {
        this.registroaprovacaoList = registroaprovacaoList;
    }

    public Definicao getDefinicao() {
        return definicao;
    }

    public void setDefinicao(Definicao definicao) {
        this.definicao = definicao;
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
        if (!(object instanceof Aprovacao)) {
            return false;
        }
        Aprovacao other = (Aprovacao) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "model.Aprovacao[ id=" + id + " ]";
    }
    
}
