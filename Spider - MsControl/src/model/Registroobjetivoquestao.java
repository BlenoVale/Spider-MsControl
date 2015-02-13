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
@Table(name = "registroobjetivoquestao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Registroobjetivoquestao.findAll", query = "SELECT r FROM Registroobjetivoquestao r"),
    @NamedQuery(name = "Registroobjetivoquestao.findById", query = "SELECT r FROM Registroobjetivoquestao r WHERE r.id = :id"),
    @NamedQuery(name = "Registroobjetivoquestao.findByTipo", query = "SELECT r FROM Registroobjetivoquestao r WHERE r.tipo = :tipo"),
    @NamedQuery(name = "Registroobjetivoquestao.findByNomeUsuario", query = "SELECT r FROM Registroobjetivoquestao r WHERE r.nomeUsuario = :nomeUsuario"),
    @NamedQuery(name = "Registroobjetivoquestao.findByData", query = "SELECT r FROM Registroobjetivoquestao r WHERE r.data = :data"),
    @NamedQuery(name = "Registroobjetivoquestao.findByObjetivoDeQuestaoid", query = "SELECT r FROM Registroobjetivoquestao r WHERE r.objetivoDeQuestaoid = :objetivoDeQuestaoid")})
public class Registroobjetivoquestao implements Serializable {
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
    @Column(name = "ObjetivoDeQuestao_id")
    private int objetivoDeQuestaoid;
    @JoinColumn(name = "ObjetivoDeQuestao_id1", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Objetivodequestao objetivoDeQuestaoid1;

    public Registroobjetivoquestao() {
    }

    public Registroobjetivoquestao(Integer id) {
        this.id = id;
    }

    public Registroobjetivoquestao(Integer id, int tipo, String nomeUsuario, Date data, int objetivoDeQuestaoid) {
        this.id = id;
        this.tipo = tipo;
        this.nomeUsuario = nomeUsuario;
        this.data = data;
        this.objetivoDeQuestaoid = objetivoDeQuestaoid;
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

    public int getObjetivoDeQuestaoid() {
        return objetivoDeQuestaoid;
    }

    public void setObjetivoDeQuestaoid(int objetivoDeQuestaoid) {
        this.objetivoDeQuestaoid = objetivoDeQuestaoid;
    }

    public Objetivodequestao getObjetivoDeQuestaoid1() {
        return objetivoDeQuestaoid1;
    }

    public void setObjetivoDeQuestaoid1(Objetivodequestao objetivoDeQuestaoid1) {
        this.objetivoDeQuestaoid1 = objetivoDeQuestaoid1;
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
        if (!(object instanceof Registroobjetivoquestao)) {
            return false;
        }
        Registroobjetivoquestao other = (Registroobjetivoquestao) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "model.Registroobjetivoquestao[ id=" + id + " ]";
    }
    
}
