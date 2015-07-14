/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author paulosouza
 */
@Entity
@Table(name = "objetivodemedicao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Objetivodemedicao.findAll", query = "SELECT o FROM Objetivodemedicao o"),
    @NamedQuery(name = "Objetivodemedicao.findById", query = "SELECT o FROM Objetivodemedicao o WHERE o.id = :id"),
    @NamedQuery(name = "Objetivodemedicao.findByNome", query = "SELECT o FROM Objetivodemedicao o WHERE o.nome = :nome"),
    @NamedQuery(name = "Objetivodemedicao.findByNivelObjetivo", query = "SELECT o FROM Objetivodemedicao o WHERE o.nivelObjetivo = :nivelObjetivo")})
public class Objetivodemedicao implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "nivelObjetivo")
    private String nivelObjetivo;
    @Lob
    @Column(name = "proposito")
    private String proposito;
    @Lob
    @Column(name = "foco")
    private String foco;
    @Lob
    @Column(name = "ambiente")
    private String ambiente;
    @Lob
    @Column(name = "pontoDeVista")
    private String pontoDeVista;
    @Lob
    @Column(name = "observacao")
    private String observacao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "objetivoDeMedicaoid")
    private List<Objetivodequestao> objetivodequestaoList;
    @JoinColumn(name = "Projeto_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Projeto projetoid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "objetivoDeMedicaoid")
    private List<Registroobjetivomedicao> registroobjetivomedicaoList;

    public Objetivodemedicao() {
    }

    public Objetivodemedicao(Integer id) {
        this.id = id;
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

    public String getNivelObjetivo() {
        return nivelObjetivo;
    }

    public void setNivelObjetivo(String nivelObjetivo) {
        this.nivelObjetivo = nivelObjetivo;
    }

    public String getProposito() {
        return proposito;
    }

    public void setProposito(String proposito) {
        this.proposito = proposito;
    }

    public String getFoco() {
        return foco;
    }

    public void setFoco(String foco) {
        this.foco = foco;
    }

    public String getAmbiente() {
        return ambiente;
    }

    public void setAmbiente(String ambiente) {
        this.ambiente = ambiente;
    }

    public String getPontoDeVista() {
        return pontoDeVista;
    }

    public void setPontoDeVista(String pontoDeVista) {
        this.pontoDeVista = pontoDeVista;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    @XmlTransient
    public List<Objetivodequestao> getObjetivodequestaoList() {
        return objetivodequestaoList;
    }

    public void setObjetivodequestaoList(List<Objetivodequestao> objetivodequestaoList) {
        this.objetivodequestaoList = objetivodequestaoList;
    }

    public Projeto getProjetoid() {
        return projetoid;
    }

    public void setProjetoid(Projeto projetoid) {
        this.projetoid = projetoid;
    }

    @XmlTransient
    public List<Registroobjetivomedicao> getRegistroobjetivomedicaoList() {
        return registroobjetivomedicaoList;
    }

    public void setRegistroobjetivomedicaoList(List<Registroobjetivomedicao> registroobjetivomedicaoList) {
        this.registroobjetivomedicaoList = registroobjetivomedicaoList;
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
        if (!(object instanceof Objetivodemedicao)) {
            return false;
        }
        Objetivodemedicao other = (Objetivodemedicao) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "model.Objetivodemedicao[ id=" + id + " ]";
    }

}
