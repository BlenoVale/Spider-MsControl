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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
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
 * @author Dan
 */
@Entity
@Table(name = "registroaprovacao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Registroaprovacao.findAll", query = "SELECT r FROM Registroaprovacao r"),
    @NamedQuery(name = "Registroaprovacao.findById", query = "SELECT r FROM Registroaprovacao r WHERE r.registroaprovacaoPK.id = :id"),
    @NamedQuery(name = "Registroaprovacao.findByAprovacaoid", query = "SELECT r FROM Registroaprovacao r WHERE r.registroaprovacaoPK.aprovacaoid = :aprovacaoid"),
    @NamedQuery(name = "Registroaprovacao.findByTipo", query = "SELECT r FROM Registroaprovacao r WHERE r.tipo = :tipo"),
    @NamedQuery(name = "Registroaprovacao.findByNomeUsuario", query = "SELECT r FROM Registroaprovacao r WHERE r.nomeUsuario = :nomeUsuario"),
    @NamedQuery(name = "Registroaprovacao.findByData", query = "SELECT r FROM Registroaprovacao r WHERE r.data = :data")})
public class Registroaprovacao implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RegistroaprovacaoPK registroaprovacaoPK;
    @Basic(optional = false)
    @Column(name = "tipo")
    private int tipo;
    @Basic(optional = false)
    @Column(name = "nomeUsuario")
    private String nomeUsuario;
    @Lob
    @Column(name = "descricao")
    private String descricao;
    @Basic(optional = false)
    @Column(name = "data")
    @Temporal(TemporalType.DATE)
    private Date data;
    @JoinColumn(name = "Aprovacao_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Aprovacao aprovacao;

    public Registroaprovacao() {
    }

    public Registroaprovacao(RegistroaprovacaoPK registroaprovacaoPK) {
        this.registroaprovacaoPK = registroaprovacaoPK;
    }

    public Registroaprovacao(RegistroaprovacaoPK registroaprovacaoPK, int tipo, String nomeUsuario, Date data) {
        this.registroaprovacaoPK = registroaprovacaoPK;
        this.tipo = tipo;
        this.nomeUsuario = nomeUsuario;
        this.data = data;
    }

    public Registroaprovacao(int id, int aprovacaoid) {
        this.registroaprovacaoPK = new RegistroaprovacaoPK(id, aprovacaoid);
    }

    public RegistroaprovacaoPK getRegistroaprovacaoPK() {
        return registroaprovacaoPK;
    }

    public void setRegistroaprovacaoPK(RegistroaprovacaoPK registroaprovacaoPK) {
        this.registroaprovacaoPK = registroaprovacaoPK;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Aprovacao getAprovacao() {
        return aprovacao;
    }

    public void setAprovacao(Aprovacao aprovacao) {
        this.aprovacao = aprovacao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (registroaprovacaoPK != null ? registroaprovacaoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Registroaprovacao)) {
            return false;
        }
        Registroaprovacao other = (Registroaprovacao) object;
        if ((this.registroaprovacaoPK == null && other.registroaprovacaoPK != null) || (this.registroaprovacaoPK != null && !this.registroaprovacaoPK.equals(other.registroaprovacaoPK)))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "model.Registroaprovacao[ registroaprovacaoPK=" + registroaprovacaoPK + " ]";
    }
    
}
