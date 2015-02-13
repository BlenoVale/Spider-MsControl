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
 * @author Spider
 */
@Entity
@Table(name = "registroobjetivomedicao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Registroobjetivomedicao.findAll", query = "SELECT r FROM Registroobjetivomedicao r"),
    @NamedQuery(name = "Registroobjetivomedicao.findById", query = "SELECT r FROM Registroobjetivomedicao r WHERE r.id = :id"),
    @NamedQuery(name = "Registroobjetivomedicao.findByTipo", query = "SELECT r FROM Registroobjetivomedicao r WHERE r.tipo = :tipo"),
    @NamedQuery(name = "Registroobjetivomedicao.findByNomeUsuario", query = "SELECT r FROM Registroobjetivomedicao r WHERE r.nomeUsuario = :nomeUsuario"),
    @NamedQuery(name = "Registroobjetivomedicao.findByData", query = "SELECT r FROM Registroobjetivomedicao r WHERE r.data = :data"),
    @NamedQuery(name = "Registroobjetivomedicao.findByObjetivoDeMedicaoid", query = "SELECT r FROM Registroobjetivomedicao r WHERE r.objetivoDeMedicaoid = :objetivoDeMedicaoid"),
    @NamedQuery(name = "Registroobjetivomedicao.findByObjetivoDeMedicaoProjetoid", query = "SELECT r FROM Registroobjetivomedicao r WHERE r.objetivoDeMedicaoProjetoid = :objetivoDeMedicaoProjetoid")})
public class Registroobjetivomedicao implements Serializable {
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
    @Basic(optional = false)
    @Column(name = "ObjetivoDeMedicao_id")
    private int objetivoDeMedicaoid;
    @Basic(optional = false)
    @Column(name = "ObjetivoDeMedicao_Projeto_id")
    private int objetivoDeMedicaoProjetoid;
    @JoinColumn(name = "ObjetivoDeMedicao_id1", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Objetivodemedicao objetivoDeMedicaoid1;

    public Registroobjetivomedicao() {
    }

    public Registroobjetivomedicao(Integer id) {
        this.id = id;
    }

    public Registroobjetivomedicao(Integer id, int tipo, String nomeUsuario, Date data, int objetivoDeMedicaoid, int objetivoDeMedicaoProjetoid) {
        this.id = id;
        this.tipo = tipo;
        this.nomeUsuario = nomeUsuario;
        this.data = data;
        this.objetivoDeMedicaoid = objetivoDeMedicaoid;
        this.objetivoDeMedicaoProjetoid = objetivoDeMedicaoProjetoid;
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

    public int getObjetivoDeMedicaoid() {
        return objetivoDeMedicaoid;
    }

    public void setObjetivoDeMedicaoid(int objetivoDeMedicaoid) {
        this.objetivoDeMedicaoid = objetivoDeMedicaoid;
    }

    public int getObjetivoDeMedicaoProjetoid() {
        return objetivoDeMedicaoProjetoid;
    }

    public void setObjetivoDeMedicaoProjetoid(int objetivoDeMedicaoProjetoid) {
        this.objetivoDeMedicaoProjetoid = objetivoDeMedicaoProjetoid;
    }

    public Objetivodemedicao getObjetivoDeMedicaoid1() {
        return objetivoDeMedicaoid1;
    }

    public void setObjetivoDeMedicaoid1(Objetivodemedicao objetivoDeMedicaoid1) {
        this.objetivoDeMedicaoid1 = objetivoDeMedicaoid1;
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
        if (!(object instanceof Registroobjetivomedicao)) {
            return false;
        }
        Registroobjetivomedicao other = (Registroobjetivomedicao) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Registroobjetivomedicao[ id=" + id + " ]";
    }
    
}
