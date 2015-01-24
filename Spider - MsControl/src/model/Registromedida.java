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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Spider
 */
@Entity
@Table(name = "registromedida")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Registromedida.findAll", query = "SELECT r FROM Registromedida r"),
    @NamedQuery(name = "Registromedida.findById", query = "SELECT r FROM Registromedida r WHERE r.id = :id"),
    @NamedQuery(name = "Registromedida.findByTipo", query = "SELECT r FROM Registromedida r WHERE r.tipo = :tipo"),
    @NamedQuery(name = "Registromedida.findByNomeUsuario", query = "SELECT r FROM Registromedida r WHERE r.nomeUsuario = :nomeUsuario"),
    @NamedQuery(name = "Registromedida.findByData", query = "SELECT r FROM Registromedida r WHERE r.data = :data")})
public class Registromedida implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
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
        @JoinColumn(name = "Medida_id", referencedColumnName = "id"),
        @JoinColumn(name = "Medida_Projeto_id", referencedColumnName = "Projeto_id")})
    @ManyToOne(optional = false)
    private Medida medida;

    public Registromedida() {
    }

    public Registromedida(Integer id) {
        this.id = id;
    }

    public Registromedida(Integer id, int tipo, String nomeUsuario, Date data) {
        this.id = id;
        this.tipo = tipo;
        this.nomeUsuario = nomeUsuario;
        this.data = data;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Registromedida)) {
            return false;
        }
        Registromedida other = (Registromedida) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "model.Registromedida[ id=" + id + " ]";
    }
    
}
