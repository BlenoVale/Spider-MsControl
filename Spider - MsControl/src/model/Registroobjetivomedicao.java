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
@Table(name = "registroobjetivomedicao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Registroobjetivomedicao.findAll", query = "SELECT r FROM Registroobjetivomedicao r"),
    @NamedQuery(name = "Registroobjetivomedicao.findById", query = "SELECT r FROM Registroobjetivomedicao r WHERE r.registroobjetivomedicaoPK.id = :id"),
    @NamedQuery(name = "Registroobjetivomedicao.findByObjetivoDeMedicaoid", query = "SELECT r FROM Registroobjetivomedicao r WHERE r.registroobjetivomedicaoPK.objetivoDeMedicaoid = :objetivoDeMedicaoid"),
    @NamedQuery(name = "Registroobjetivomedicao.findByObjetivoDeMedicaoProjetoid", query = "SELECT r FROM Registroobjetivomedicao r WHERE r.registroobjetivomedicaoPK.objetivoDeMedicaoProjetoid = :objetivoDeMedicaoProjetoid"),
    @NamedQuery(name = "Registroobjetivomedicao.findByTipo", query = "SELECT r FROM Registroobjetivomedicao r WHERE r.tipo = :tipo"),
    @NamedQuery(name = "Registroobjetivomedicao.findByNomeUsuario", query = "SELECT r FROM Registroobjetivomedicao r WHERE r.nomeUsuario = :nomeUsuario"),
    @NamedQuery(name = "Registroobjetivomedicao.findByData", query = "SELECT r FROM Registroobjetivomedicao r WHERE r.data = :data")})
public class Registroobjetivomedicao implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RegistroobjetivomedicaoPK registroobjetivomedicaoPK;
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
        @JoinColumn(name = "ObjetivoDeMedicao_id", referencedColumnName = "id", insertable = false, updatable = false),
        @JoinColumn(name = "ObjetivoDeMedicao_Projeto_id", referencedColumnName = "Projeto_id", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Objetivodemedicao objetivodemedicao;

    public Registroobjetivomedicao() {
    }

    public Registroobjetivomedicao(RegistroobjetivomedicaoPK registroobjetivomedicaoPK) {
        this.registroobjetivomedicaoPK = registroobjetivomedicaoPK;
    }

    public Registroobjetivomedicao(RegistroobjetivomedicaoPK registroobjetivomedicaoPK, int tipo, String nomeUsuario, Date data) {
        this.registroobjetivomedicaoPK = registroobjetivomedicaoPK;
        this.tipo = tipo;
        this.nomeUsuario = nomeUsuario;
        this.data = data;
    }

    public Registroobjetivomedicao(int id, int objetivoDeMedicaoid, int objetivoDeMedicaoProjetoid) {
        this.registroobjetivomedicaoPK = new RegistroobjetivomedicaoPK(id, objetivoDeMedicaoid, objetivoDeMedicaoProjetoid);
    }

    public RegistroobjetivomedicaoPK getRegistroobjetivomedicaoPK() {
        return registroobjetivomedicaoPK;
    }

    public void setRegistroobjetivomedicaoPK(RegistroobjetivomedicaoPK registroobjetivomedicaoPK) {
        this.registroobjetivomedicaoPK = registroobjetivomedicaoPK;
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

    public Objetivodemedicao getObjetivodemedicao() {
        return objetivodemedicao;
    }

    public void setObjetivodemedicao(Objetivodemedicao objetivodemedicao) {
        this.objetivodemedicao = objetivodemedicao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (registroobjetivomedicaoPK != null ? registroobjetivomedicaoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Registroobjetivomedicao)) {
            return false;
        }
        Registroobjetivomedicao other = (Registroobjetivomedicao) object;
        if ((this.registroobjetivomedicaoPK == null && other.registroobjetivomedicaoPK != null) || (this.registroobjetivomedicaoPK != null && !this.registroobjetivomedicaoPK.equals(other.registroobjetivomedicaoPK)))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "model.Registroobjetivomedicao[ registroobjetivomedicaoPK=" + registroobjetivomedicaoPK + " ]";
    }
    
}
