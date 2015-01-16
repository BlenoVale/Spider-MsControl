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
@Table(name = "registrocomposicao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Registrocomposicao.findAll", query = "SELECT r FROM Registrocomposicao r"),
    @NamedQuery(name = "Registrocomposicao.findById", query = "SELECT r FROM Registrocomposicao r WHERE r.registrocomposicaoPK.id = :id"),
    @NamedQuery(name = "Registrocomposicao.findByComposicaoid", query = "SELECT r FROM Registrocomposicao r WHERE r.registrocomposicaoPK.composicaoid = :composicaoid"),
    @NamedQuery(name = "Registrocomposicao.findByTipo", query = "SELECT r FROM Registrocomposicao r WHERE r.tipo = :tipo"),
    @NamedQuery(name = "Registrocomposicao.findByNomeUsuario", query = "SELECT r FROM Registrocomposicao r WHERE r.nomeUsuario = :nomeUsuario"),
    @NamedQuery(name = "Registrocomposicao.findByData", query = "SELECT r FROM Registrocomposicao r WHERE r.data = :data")})
public class Registrocomposicao implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RegistrocomposicaoPK registrocomposicaoPK;
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
    @JoinColumn(name = "Composicao_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Composicao composicao;

    public Registrocomposicao() {
    }

    public Registrocomposicao(RegistrocomposicaoPK registrocomposicaoPK) {
        this.registrocomposicaoPK = registrocomposicaoPK;
    }

    public Registrocomposicao(RegistrocomposicaoPK registrocomposicaoPK, int tipo, String nomeUsuario) {
        this.registrocomposicaoPK = registrocomposicaoPK;
        this.tipo = tipo;
        this.nomeUsuario = nomeUsuario;
    }

    public Registrocomposicao(int id, int composicaoid) {
        this.registrocomposicaoPK = new RegistrocomposicaoPK(id, composicaoid);
    }

    public RegistrocomposicaoPK getRegistrocomposicaoPK() {
        return registrocomposicaoPK;
    }

    public void setRegistrocomposicaoPK(RegistrocomposicaoPK registrocomposicaoPK) {
        this.registrocomposicaoPK = registrocomposicaoPK;
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

    public Composicao getComposicao() {
        return composicao;
    }

    public void setComposicao(Composicao composicao) {
        this.composicao = composicao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (registrocomposicaoPK != null ? registrocomposicaoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Registrocomposicao)) {
            return false;
        }
        Registrocomposicao other = (Registrocomposicao) object;
        if ((this.registrocomposicaoPK == null && other.registrocomposicaoPK != null) || (this.registrocomposicaoPK != null && !this.registrocomposicaoPK.equals(other.registrocomposicaoPK)))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "model.Registrocomposicao[ registrocomposicaoPK=" + registrocomposicaoPK + " ]";
    }
    
}
