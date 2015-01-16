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
@Table(name = "registrocoleta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Registrocoleta.findAll", query = "SELECT r FROM Registrocoleta r"),
    @NamedQuery(name = "Registrocoleta.findById", query = "SELECT r FROM Registrocoleta r WHERE r.registrocoletaPK.id = :id"),
    @NamedQuery(name = "Registrocoleta.findByColetaid", query = "SELECT r FROM Registrocoleta r WHERE r.registrocoletaPK.coletaid = :coletaid"),
    @NamedQuery(name = "Registrocoleta.findByColetaMedidaid", query = "SELECT r FROM Registrocoleta r WHERE r.registrocoletaPK.coletaMedidaid = :coletaMedidaid"),
    @NamedQuery(name = "Registrocoleta.findByColetaMedidaProjetoid", query = "SELECT r FROM Registrocoleta r WHERE r.registrocoletaPK.coletaMedidaProjetoid = :coletaMedidaProjetoid"),
    @NamedQuery(name = "Registrocoleta.findByTipo", query = "SELECT r FROM Registrocoleta r WHERE r.tipo = :tipo"),
    @NamedQuery(name = "Registrocoleta.findByNomeUsuario", query = "SELECT r FROM Registrocoleta r WHERE r.nomeUsuario = :nomeUsuario"),
    @NamedQuery(name = "Registrocoleta.findByData", query = "SELECT r FROM Registrocoleta r WHERE r.data = :data")})
public class Registrocoleta implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RegistrocoletaPK registrocoletaPK;
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
    @JoinColumns({
        @JoinColumn(name = "Coleta_id", referencedColumnName = "id", insertable = false, updatable = false),
        @JoinColumn(name = "Coleta_Medida_id", referencedColumnName = "Medida_id", insertable = false, updatable = false),
        @JoinColumn(name = "Coleta_Medida_Projeto_id", referencedColumnName = "Medida_Projeto_id", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Coleta coleta;

    public Registrocoleta() {
    }

    public Registrocoleta(RegistrocoletaPK registrocoletaPK) {
        this.registrocoletaPK = registrocoletaPK;
    }

    public Registrocoleta(RegistrocoletaPK registrocoletaPK, int tipo, String nomeUsuario, Date data) {
        this.registrocoletaPK = registrocoletaPK;
        this.tipo = tipo;
        this.nomeUsuario = nomeUsuario;
        this.data = data;
    }

    public Registrocoleta(int id, int coletaid, int coletaMedidaid, int coletaMedidaProjetoid) {
        this.registrocoletaPK = new RegistrocoletaPK(id, coletaid, coletaMedidaid, coletaMedidaProjetoid);
    }

    public RegistrocoletaPK getRegistrocoletaPK() {
        return registrocoletaPK;
    }

    public void setRegistrocoletaPK(RegistrocoletaPK registrocoletaPK) {
        this.registrocoletaPK = registrocoletaPK;
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

    public Coleta getColeta() {
        return coleta;
    }

    public void setColeta(Coleta coleta) {
        this.coleta = coleta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (registrocoletaPK != null ? registrocoletaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Registrocoleta)) {
            return false;
        }
        Registrocoleta other = (Registrocoleta) object;
        if ((this.registrocoletaPK == null && other.registrocoletaPK != null) || (this.registrocoletaPK != null && !this.registrocoletaPK.equals(other.registrocoletaPK)))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "model.Registrocoleta[ registrocoletaPK=" + registrocoletaPK + " ]";
    }
    
}
