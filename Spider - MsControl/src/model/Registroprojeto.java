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
@Table(name = "registroprojeto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Registroprojeto.findAll", query = "SELECT r FROM Registroprojeto r"),
    @NamedQuery(name = "Registroprojeto.findById", query = "SELECT r FROM Registroprojeto r WHERE r.registroprojetoPK.id = :id"),
    @NamedQuery(name = "Registroprojeto.findByProjetoid", query = "SELECT r FROM Registroprojeto r WHERE r.registroprojetoPK.projetoid = :projetoid"),
    @NamedQuery(name = "Registroprojeto.findByTipo", query = "SELECT r FROM Registroprojeto r WHERE r.tipo = :tipo"),
    @NamedQuery(name = "Registroprojeto.findByNomeUsuario", query = "SELECT r FROM Registroprojeto r WHERE r.nomeUsuario = :nomeUsuario"),
    @NamedQuery(name = "Registroprojeto.findByData", query = "SELECT r FROM Registroprojeto r WHERE r.data = :data")})
public class Registroprojeto implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RegistroprojetoPK registroprojetoPK;
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
    @JoinColumn(name = "Projeto_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Projeto projeto;

    public Registroprojeto() {
    }

    public Registroprojeto(RegistroprojetoPK registroprojetoPK) {
        this.registroprojetoPK = registroprojetoPK;
    }

    public Registroprojeto(RegistroprojetoPK registroprojetoPK, int tipo, String nomeUsuario, Date data) {
        this.registroprojetoPK = registroprojetoPK;
        this.tipo = tipo;
        this.nomeUsuario = nomeUsuario;
        this.data = data;
    }

    public Registroprojeto(int id, int projetoid) {
        this.registroprojetoPK = new RegistroprojetoPK(id, projetoid);
    }

    public RegistroprojetoPK getRegistroprojetoPK() {
        return registroprojetoPK;
    }

    public void setRegistroprojetoPK(RegistroprojetoPK registroprojetoPK) {
        this.registroprojetoPK = registroprojetoPK;
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

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (registroprojetoPK != null ? registroprojetoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Registroprojeto)) {
            return false;
        }
        Registroprojeto other = (Registroprojeto) object;
        if ((this.registroprojetoPK == null && other.registroprojetoPK != null) || (this.registroprojetoPK != null && !this.registroprojetoPK.equals(other.registroprojetoPK)))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "model.Registroprojeto[ registroprojetoPK=" + registroprojetoPK + " ]";
    }
    
}
