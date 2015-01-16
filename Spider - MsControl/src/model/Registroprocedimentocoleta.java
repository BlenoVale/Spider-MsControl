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
@Table(name = "registroprocedimentocoleta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Registroprocedimentocoleta.findAll", query = "SELECT r FROM Registroprocedimentocoleta r"),
    @NamedQuery(name = "Registroprocedimentocoleta.findById", query = "SELECT r FROM Registroprocedimentocoleta r WHERE r.registroprocedimentocoletaPK.id = :id"),
    @NamedQuery(name = "Registroprocedimentocoleta.findByProcedimentoDeColetaid", query = "SELECT r FROM Registroprocedimentocoleta r WHERE r.registroprocedimentocoletaPK.procedimentoDeColetaid = :procedimentoDeColetaid"),
    @NamedQuery(name = "Registroprocedimentocoleta.findByTipo", query = "SELECT r FROM Registroprocedimentocoleta r WHERE r.tipo = :tipo"),
    @NamedQuery(name = "Registroprocedimentocoleta.findByNomeUsuario", query = "SELECT r FROM Registroprocedimentocoleta r WHERE r.nomeUsuario = :nomeUsuario"),
    @NamedQuery(name = "Registroprocedimentocoleta.findByData", query = "SELECT r FROM Registroprocedimentocoleta r WHERE r.data = :data")})
public class Registroprocedimentocoleta implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RegistroprocedimentocoletaPK registroprocedimentocoletaPK;
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
    @JoinColumn(name = "ProcedimentoDeColeta_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Procedimentodecoleta procedimentodecoleta;

    public Registroprocedimentocoleta() {
    }

    public Registroprocedimentocoleta(RegistroprocedimentocoletaPK registroprocedimentocoletaPK) {
        this.registroprocedimentocoletaPK = registroprocedimentocoletaPK;
    }

    public Registroprocedimentocoleta(RegistroprocedimentocoletaPK registroprocedimentocoletaPK, int tipo, String nomeUsuario, Date data) {
        this.registroprocedimentocoletaPK = registroprocedimentocoletaPK;
        this.tipo = tipo;
        this.nomeUsuario = nomeUsuario;
        this.data = data;
    }

    public Registroprocedimentocoleta(int id, int procedimentoDeColetaid) {
        this.registroprocedimentocoletaPK = new RegistroprocedimentocoletaPK(id, procedimentoDeColetaid);
    }

    public RegistroprocedimentocoletaPK getRegistroprocedimentocoletaPK() {
        return registroprocedimentocoletaPK;
    }

    public void setRegistroprocedimentocoletaPK(RegistroprocedimentocoletaPK registroprocedimentocoletaPK) {
        this.registroprocedimentocoletaPK = registroprocedimentocoletaPK;
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

    public Procedimentodecoleta getProcedimentodecoleta() {
        return procedimentodecoleta;
    }

    public void setProcedimentodecoleta(Procedimentodecoleta procedimentodecoleta) {
        this.procedimentodecoleta = procedimentodecoleta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (registroprocedimentocoletaPK != null ? registroprocedimentocoletaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Registroprocedimentocoleta)) {
            return false;
        }
        Registroprocedimentocoleta other = (Registroprocedimentocoleta) object;
        if ((this.registroprocedimentocoletaPK == null && other.registroprocedimentocoletaPK != null) || (this.registroprocedimentocoletaPK != null && !this.registroprocedimentocoletaPK.equals(other.registroprocedimentocoletaPK)))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "model.Registroprocedimentocoleta[ registroprocedimentocoletaPK=" + registroprocedimentocoletaPK + " ]";
    }
    
}
