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
@Table(name = "registrodefinicao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Registrodefinicao.findAll", query = "SELECT r FROM Registrodefinicao r"),
    @NamedQuery(name = "Registrodefinicao.findById", query = "SELECT r FROM Registrodefinicao r WHERE r.registrodefinicaoPK.id = :id"),
    @NamedQuery(name = "Registrodefinicao.findByDefinicaoid", query = "SELECT r FROM Registrodefinicao r WHERE r.registrodefinicaoPK.definicaoid = :definicaoid"),
    @NamedQuery(name = "Registrodefinicao.findByDefinicaoMedidaid", query = "SELECT r FROM Registrodefinicao r WHERE r.registrodefinicaoPK.definicaoMedidaid = :definicaoMedidaid"),
    @NamedQuery(name = "Registrodefinicao.findByDefinicaoMedidaProjetoid", query = "SELECT r FROM Registrodefinicao r WHERE r.registrodefinicaoPK.definicaoMedidaProjetoid = :definicaoMedidaProjetoid"),
    @NamedQuery(name = "Registrodefinicao.findByTipo", query = "SELECT r FROM Registrodefinicao r WHERE r.tipo = :tipo"),
    @NamedQuery(name = "Registrodefinicao.findByNomeUsuario", query = "SELECT r FROM Registrodefinicao r WHERE r.nomeUsuario = :nomeUsuario"),
    @NamedQuery(name = "Registrodefinicao.findByData", query = "SELECT r FROM Registrodefinicao r WHERE r.data = :data")})
public class Registrodefinicao implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RegistrodefinicaoPK registrodefinicaoPK;
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
        @JoinColumn(name = "Definicao_id", referencedColumnName = "id", insertable = false, updatable = false),
        @JoinColumn(name = "Definicao_Medida_id", referencedColumnName = "Medida_id", insertable = false, updatable = false),
        @JoinColumn(name = "Definicao_Medida_Projeto_id", referencedColumnName = "Medida_Projeto_id", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Definicao definicao;

    public Registrodefinicao() {
    }

    public Registrodefinicao(RegistrodefinicaoPK registrodefinicaoPK) {
        this.registrodefinicaoPK = registrodefinicaoPK;
    }

    public Registrodefinicao(RegistrodefinicaoPK registrodefinicaoPK, int tipo, String nomeUsuario, Date data) {
        this.registrodefinicaoPK = registrodefinicaoPK;
        this.tipo = tipo;
        this.nomeUsuario = nomeUsuario;
        this.data = data;
    }

    public Registrodefinicao(int id, int definicaoid, int definicaoMedidaid, int definicaoMedidaProjetoid) {
        this.registrodefinicaoPK = new RegistrodefinicaoPK(id, definicaoid, definicaoMedidaid, definicaoMedidaProjetoid);
    }

    public RegistrodefinicaoPK getRegistrodefinicaoPK() {
        return registrodefinicaoPK;
    }

    public void setRegistrodefinicaoPK(RegistrodefinicaoPK registrodefinicaoPK) {
        this.registrodefinicaoPK = registrodefinicaoPK;
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

    public Definicao getDefinicao() {
        return definicao;
    }

    public void setDefinicao(Definicao definicao) {
        this.definicao = definicao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (registrodefinicaoPK != null ? registrodefinicaoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Registrodefinicao)) {
            return false;
        }
        Registrodefinicao other = (Registrodefinicao) object;
        if ((this.registrodefinicaoPK == null && other.registrodefinicaoPK != null) || (this.registrodefinicaoPK != null && !this.registrodefinicaoPK.equals(other.registrodefinicaoPK)))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "model.Registrodefinicao[ registrodefinicaoPK=" + registrodefinicaoPK + " ]";
    }
    
}
