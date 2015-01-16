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
@Table(name = "registromedida")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Registromedida.findAll", query = "SELECT r FROM Registromedida r"),
    @NamedQuery(name = "Registromedida.findById", query = "SELECT r FROM Registromedida r WHERE r.registromedidaPK.id = :id"),
    @NamedQuery(name = "Registromedida.findByMedidaid", query = "SELECT r FROM Registromedida r WHERE r.registromedidaPK.medidaid = :medidaid"),
    @NamedQuery(name = "Registromedida.findByMedidaProjetoid", query = "SELECT r FROM Registromedida r WHERE r.registromedidaPK.medidaProjetoid = :medidaProjetoid"),
    @NamedQuery(name = "Registromedida.findByTipo", query = "SELECT r FROM Registromedida r WHERE r.tipo = :tipo"),
    @NamedQuery(name = "Registromedida.findByNomeUsuario", query = "SELECT r FROM Registromedida r WHERE r.nomeUsuario = :nomeUsuario"),
    @NamedQuery(name = "Registromedida.findByData", query = "SELECT r FROM Registromedida r WHERE r.data = :data")})
public class Registromedida implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RegistromedidaPK registromedidaPK;
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
        @JoinColumn(name = "Medida_id", referencedColumnName = "id", insertable = false, updatable = false),
        @JoinColumn(name = "Medida_Projeto_id", referencedColumnName = "Projeto_id", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Medida medida;

    public Registromedida() {
    }

    public Registromedida(RegistromedidaPK registromedidaPK) {
        this.registromedidaPK = registromedidaPK;
    }

    public Registromedida(RegistromedidaPK registromedidaPK, int tipo, String nomeUsuario, Date data) {
        this.registromedidaPK = registromedidaPK;
        this.tipo = tipo;
        this.nomeUsuario = nomeUsuario;
        this.data = data;
    }

    public Registromedida(int id, int medidaid, int medidaProjetoid) {
        this.registromedidaPK = new RegistromedidaPK(id, medidaid, medidaProjetoid);
    }

    public RegistromedidaPK getRegistromedidaPK() {
        return registromedidaPK;
    }

    public void setRegistromedidaPK(RegistromedidaPK registromedidaPK) {
        this.registromedidaPK = registromedidaPK;
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

    public Medida getMedida() {
        return medida;
    }

    public void setMedida(Medida medida) {
        this.medida = medida;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (registromedidaPK != null ? registromedidaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Registromedida)) {
            return false;
        }
        Registromedida other = (Registromedida) object;
        if ((this.registromedidaPK == null && other.registromedidaPK != null) || (this.registromedidaPK != null && !this.registromedidaPK.equals(other.registromedidaPK)))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "model.Registromedida[ registromedidaPK=" + registromedidaPK + " ]";
    }
    
}
