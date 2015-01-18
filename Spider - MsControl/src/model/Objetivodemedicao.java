/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
 * @author Dan
 */
@Entity
@Table(name = "objetivodemedicao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Objetivodemedicao.findAll", query = "SELECT o FROM Objetivodemedicao o"),
    @NamedQuery(name = "Objetivodemedicao.findById", query = "SELECT o FROM Objetivodemedicao o WHERE o.objetivodemedicaoPK.id = :id"),
    @NamedQuery(name = "Objetivodemedicao.findByNome", query = "SELECT o FROM Objetivodemedicao o WHERE o.nome = :nome"),
    @NamedQuery(name = "Objetivodemedicao.findByNivelObjetivo", query = "SELECT o FROM Objetivodemedicao o WHERE o.nivelObjetivo = :nivelObjetivo"),
    @NamedQuery(name = "Objetivodemedicao.findByProjetoid", query = "SELECT o FROM Objetivodemedicao o WHERE o.objetivodemedicaoPK.projetoid = :projetoid")})
public class Objetivodemedicao implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ObjetivodemedicaoPK objetivodemedicaoPK;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "objetivodemedicao")
    private List<Objetivodequestao> objetivodequestaoList;
    @JoinColumn(name = "Projeto_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Projeto projeto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "objetivodemedicao")
    private List<Registroobjetivomedicao> registroobjetivomedicaoList;

    public Objetivodemedicao() {
    }

    public Objetivodemedicao(ObjetivodemedicaoPK objetivodemedicaoPK) {
        this.objetivodemedicaoPK = objetivodemedicaoPK;
    }

    public Objetivodemedicao(int id, int projetoid) {
        this.objetivodemedicaoPK = new ObjetivodemedicaoPK(id, projetoid);
    }

    public ObjetivodemedicaoPK getObjetivodemedicaoPK() {
        return objetivodemedicaoPK;
    }

    public void setObjetivodemedicaoPK(ObjetivodemedicaoPK objetivodemedicaoPK) {
        this.objetivodemedicaoPK = objetivodemedicaoPK;
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
    
    public void addObjetivodequestaoList(Objetivodequestao objetivodequestaoList) {
        this.objetivodequestaoList.add(objetivodequestaoList);
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    @XmlTransient
    public List<Registroobjetivomedicao> getRegistroobjetivomedicaoList() {
        return registroobjetivomedicaoList;
    }

    public void setRegistroobjetivomedicaoList(List<Registroobjetivomedicao> registroobjetivomedicaoList) {
        this.registroobjetivomedicaoList = registroobjetivomedicaoList;
    }
    
    public void setRegistroobjetivomedicao(Registroobjetivomedicao registro) {
        this.registroobjetivomedicaoList.add(registro);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (objetivodemedicaoPK != null ? objetivodemedicaoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Objetivodemedicao)) {
            return false;
        }
        Objetivodemedicao other = (Objetivodemedicao) object;
        if ((this.objetivodemedicaoPK == null && other.objetivodemedicaoPK != null) || (this.objetivodemedicaoPK != null && !this.objetivodemedicaoPK.equals(other.objetivodemedicaoPK)))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "model.Objetivodemedicao[ objetivodemedicaoPK=" + objetivodemedicaoPK + " ]";
    }
    
}
