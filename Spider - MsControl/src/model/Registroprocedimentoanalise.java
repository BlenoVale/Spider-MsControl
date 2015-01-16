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
@Table(name = "registroprocedimentoanalise")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Registroprocedimentoanalise.findAll", query = "SELECT r FROM Registroprocedimentoanalise r"),
    @NamedQuery(name = "Registroprocedimentoanalise.findById", query = "SELECT r FROM Registroprocedimentoanalise r WHERE r.registroprocedimentoanalisePK.id = :id"),
    @NamedQuery(name = "Registroprocedimentoanalise.findByProcedimentoDeAnaliseid", query = "SELECT r FROM Registroprocedimentoanalise r WHERE r.registroprocedimentoanalisePK.procedimentoDeAnaliseid = :procedimentoDeAnaliseid"),
    @NamedQuery(name = "Registroprocedimentoanalise.findByTipo", query = "SELECT r FROM Registroprocedimentoanalise r WHERE r.tipo = :tipo"),
    @NamedQuery(name = "Registroprocedimentoanalise.findByNomeUsuario", query = "SELECT r FROM Registroprocedimentoanalise r WHERE r.nomeUsuario = :nomeUsuario"),
    @NamedQuery(name = "Registroprocedimentoanalise.findByData", query = "SELECT r FROM Registroprocedimentoanalise r WHERE r.data = :data")})
public class Registroprocedimentoanalise implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RegistroprocedimentoanalisePK registroprocedimentoanalisePK;
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
    @JoinColumn(name = "ProcedimentoDeAnalise_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Procedimentodeanalise procedimentodeanalise;

    public Registroprocedimentoanalise() {
    }

    public Registroprocedimentoanalise(RegistroprocedimentoanalisePK registroprocedimentoanalisePK) {
        this.registroprocedimentoanalisePK = registroprocedimentoanalisePK;
    }

    public Registroprocedimentoanalise(RegistroprocedimentoanalisePK registroprocedimentoanalisePK, int tipo, String nomeUsuario, Date data) {
        this.registroprocedimentoanalisePK = registroprocedimentoanalisePK;
        this.tipo = tipo;
        this.nomeUsuario = nomeUsuario;
        this.data = data;
    }

    public Registroprocedimentoanalise(int id, int procedimentoDeAnaliseid) {
        this.registroprocedimentoanalisePK = new RegistroprocedimentoanalisePK(id, procedimentoDeAnaliseid);
    }

    public RegistroprocedimentoanalisePK getRegistroprocedimentoanalisePK() {
        return registroprocedimentoanalisePK;
    }

    public void setRegistroprocedimentoanalisePK(RegistroprocedimentoanalisePK registroprocedimentoanalisePK) {
        this.registroprocedimentoanalisePK = registroprocedimentoanalisePK;
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

    public Procedimentodeanalise getProcedimentodeanalise() {
        return procedimentodeanalise;
    }

    public void setProcedimentodeanalise(Procedimentodeanalise procedimentodeanalise) {
        this.procedimentodeanalise = procedimentodeanalise;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (registroprocedimentoanalisePK != null ? registroprocedimentoanalisePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Registroprocedimentoanalise)) {
            return false;
        }
        Registroprocedimentoanalise other = (Registroprocedimentoanalise) object;
        if ((this.registroprocedimentoanalisePK == null && other.registroprocedimentoanalisePK != null) || (this.registroprocedimentoanalisePK != null && !this.registroprocedimentoanalisePK.equals(other.registroprocedimentoanalisePK)))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "model.Registroprocedimentoanalise[ registroprocedimentoanalisePK=" + registroprocedimentoanalisePK + " ]";
    }
    
}
