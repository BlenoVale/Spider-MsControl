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
import javax.persistence.JoinColumns;
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
@Table(name = "registroanalise")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Registroanalise.findAll", query = "SELECT r FROM Registroanalise r"),
    @NamedQuery(name = "Registroanalise.findById", query = "SELECT r FROM Registroanalise r WHERE r.registroanalisePK.id = :id"),
    @NamedQuery(name = "Registroanalise.findByAnaliseid", query = "SELECT r FROM Registroanalise r WHERE r.registroanalisePK.analiseid = :analiseid"),
    @NamedQuery(name = "Registroanalise.findByAnaliseMedidaid", query = "SELECT r FROM Registroanalise r WHERE r.registroanalisePK.analiseMedidaid = :analiseMedidaid"),
    @NamedQuery(name = "Registroanalise.findByAnaliseMedidaProjetoid", query = "SELECT r FROM Registroanalise r WHERE r.registroanalisePK.analiseMedidaProjetoid = :analiseMedidaProjetoid"),
    @NamedQuery(name = "Registroanalise.findByTipo", query = "SELECT r FROM Registroanalise r WHERE r.tipo = :tipo"),
    @NamedQuery(name = "Registroanalise.findByNomeUsuario", query = "SELECT r FROM Registroanalise r WHERE r.nomeUsuario = :nomeUsuario"),
    @NamedQuery(name = "Registroanalise.findByData", query = "SELECT r FROM Registroanalise r WHERE r.data = :data")})
public class Registroanalise implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RegistroanalisePK registroanalisePK;
    @Basic(optional = false)
    @Column(name = "tipo")
    private int tipo;
    @Basic(optional = false)
    @Column(name = "nomeUsuario")
    private String nomeUsuario;
    @Lob
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "data")
    @Temporal(TemporalType.DATE)
    private Date data;
    @JoinColumns({
        @JoinColumn(name = "Analise_id", referencedColumnName = "id", insertable = false, updatable = false),
        @JoinColumn(name = "Analise_Medida_id", referencedColumnName = "Medida_id", insertable = false, updatable = false),
        @JoinColumn(name = "Analise_Medida_Projeto_id", referencedColumnName = "Medida_Projeto_id", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Analise analise;

    public Registroanalise() {
    }

    public Registroanalise(RegistroanalisePK registroanalisePK) {
        this.registroanalisePK = registroanalisePK;
    }

    public Registroanalise(RegistroanalisePK registroanalisePK, int tipo, String nomeUsuario) {
        this.registroanalisePK = registroanalisePK;
        this.tipo = tipo;
        this.nomeUsuario = nomeUsuario;
    }

    public Registroanalise(int id, int analiseid, int analiseMedidaid, int analiseMedidaProjetoid) {
        this.registroanalisePK = new RegistroanalisePK(id, analiseid, analiseMedidaid, analiseMedidaProjetoid);
    }

    public RegistroanalisePK getRegistroanalisePK() {
        return registroanalisePK;
    }

    public void setRegistroanalisePK(RegistroanalisePK registroanalisePK) {
        this.registroanalisePK = registroanalisePK;
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

    public Analise getAnalise() {
        return analise;
    }

    public void setAnalise(Analise analise) {
        this.analise = analise;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (registroanalisePK != null ? registroanalisePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Registroanalise)) {
            return false;
        }
        Registroanalise other = (Registroanalise) object;
        if ((this.registroanalisePK == null && other.registroanalisePK != null) || (this.registroanalisePK != null && !this.registroanalisePK.equals(other.registroanalisePK)))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "model.Registroanalise[ registroanalisePK=" + registroanalisePK + " ]";
    }
    
}
