/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Spider
 */
@Entity
@Table(name = "projeto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Projeto.findAll", query = "SELECT p FROM Projeto p"),
    @NamedQuery(name = "Projeto.findById", query = "SELECT p FROM Projeto p WHERE p.id = :id"),
    @NamedQuery(name = "Projeto.findByNome", query = "SELECT p FROM Projeto p WHERE p.nome = :nome"),
    @NamedQuery(name = "Projeto.findByStatus", query = "SELECT p FROM Projeto p WHERE p.status = :status"),
    @NamedQuery(name = "Projeto.findByDataInicio", query = "SELECT p FROM Projeto p WHERE p.dataInicio = :dataInicio"),
    @NamedQuery(name = "Projeto.findByDataInatividade", query = "SELECT p FROM Projeto p WHERE p.dataInatividade = :dataInatividade"),
    @NamedQuery(name = "Projeto.findByDataFim", query = "SELECT p FROM Projeto p WHERE p.dataFim = :dataFim")})
public class Projeto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @Column(name = "status")
    private int status;
    @Basic(optional = false)
    @Column(name = "dataInicio")
    @Temporal(TemporalType.DATE)
    private Date dataInicio;
    @Column(name = "dataInatividade")
    @Temporal(TemporalType.DATE)
    private Date dataInatividade;
    @Column(name = "dataFim")
    @Temporal(TemporalType.DATE)
    private Date dataFim;
    @Lob
    @Column(name = "descricao")
    private String descricao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "projetoid")
    private List<Registroprojeto> registroprojetoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "projetoid")
    private List<Objetivodemedicao> objetivodemedicaoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "projeto")
    private List<Acessa> acessaList;
    @OneToMany(mappedBy = "projetoid")
    private List<Entidademedida> entidademedidaList;

    public Projeto() {
    }

    public Projeto(Integer id) {
        this.id = id;
    }

    public Projeto(Integer id, String nome, int status, Date dataInicio) {
        this.id = id;
        this.nome = nome;
        this.status = status;
        this.dataInicio = dataInicio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataInatividade() {
        return dataInatividade;
    }

    public void setDataInatividade(Date dataInatividade) {
        this.dataInatividade = dataInatividade;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @XmlTransient
    public List<Registroprojeto> getRegistroprojetoList() {
        return registroprojetoList;
    }

    public void setRegistroprojetoList(List<Registroprojeto> registroprojetoList) {
        this.registroprojetoList = registroprojetoList;
    }

    @XmlTransient
    public List<Objetivodemedicao> getObjetivodemedicaoList() {
        return objetivodemedicaoList;
    }

    public void setObjetivodemedicaoList(List<Objetivodemedicao> objetivodemedicaoList) {
        this.objetivodemedicaoList = objetivodemedicaoList;
    }

    @XmlTransient
    public List<Acessa> getAcessaList() {
        return acessaList;
    }

    public void setAcessaList(List<Acessa> acessaList) {
        this.acessaList = acessaList;
    }

    @XmlTransient
    public List<Entidademedida> getEntidademedidaList() {
        return entidademedidaList;
    }

    public void setEntidademedidaList(List<Entidademedida> entidademedidaList) {
        this.entidademedidaList = entidademedidaList;
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
        if (!(object instanceof Projeto)) {
            return false;
        }
        Projeto other = (Projeto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "model.Projeto[ id=" + id + " ]";
    }
    
}
