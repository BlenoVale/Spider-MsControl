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
    @NamedQuery(name = "Registroanalise.findById", query = "SELECT r FROM Registroanalise r WHERE r.id = :id"),
    @NamedQuery(name = "Registroanalise.findByTipo", query = "SELECT r FROM Registroanalise r WHERE r.tipo = :tipo"),
    @NamedQuery(name = "Registroanalise.findByNomeUsuario", query = "SELECT r FROM Registroanalise r WHERE r.nomeUsuario = :nomeUsuario"),
    @NamedQuery(name = "Registroanalise.findByData", query = "SELECT r FROM Registroanalise r WHERE r.data = :data")})
public class Registroanalise implements Serializable {
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
    @Column(name = "data")
    @Temporal(TemporalType.DATE)
    private Date data;
    @JoinColumn(name = "Analise_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Analise analiseid;

    public Registroanalise() {
    }

    public Registroanalise(Integer id) {
        this.id = id;
    }

    public Registroanalise(Integer id, int tipo, String nomeUsuario) {
        this.id = id;
        this.tipo = tipo;
        this.nomeUsuario = nomeUsuario;
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

    public Analise getAnaliseid() {
        return analiseid;
    }

    public void setAnaliseid(Analise analiseid) {
        this.analiseid = analiseid;
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
        if (!(object instanceof Registroanalise)) {
            return false;
        }
        Registroanalise other = (Registroanalise) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "model.Registroanalise[ id=" + id + " ]";
    }
    
}
