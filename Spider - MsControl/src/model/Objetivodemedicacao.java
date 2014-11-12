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
@Table(name = "objetivodemedicacao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Objetivodemedicacao.findAll", query = "SELECT o FROM Objetivodemedicacao o"),
    @NamedQuery(name = "Objetivodemedicacao.findById", query = "SELECT o FROM Objetivodemedicacao o WHERE o.objetivodemedicacaoPK.id = :id"),
    @NamedQuery(name = "Objetivodemedicacao.findByIdProjeto", query = "SELECT o FROM Objetivodemedicacao o WHERE o.objetivodemedicacaoPK.idProjeto = :idProjeto"),
    @NamedQuery(name = "Objetivodemedicacao.findByNome", query = "SELECT o FROM Objetivodemedicacao o WHERE o.nome = :nome"),
    @NamedQuery(name = "Objetivodemedicacao.findByNivelObjetivo", query = "SELECT o FROM Objetivodemedicacao o WHERE o.nivelObjetivo = :nivelObjetivo")})
public class Objetivodemedicacao implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ObjetivodemedicacaoPK objetivodemedicacaoPK;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "objetivodemedicacao")
    private List<Objetivodequestao> objetivodequestaoList;
    @JoinColumn(name = "idProjeto", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Projeto projeto;

    public Objetivodemedicacao() {
    }

    public Objetivodemedicacao(ObjetivodemedicacaoPK objetivodemedicacaoPK) {
        this.objetivodemedicacaoPK = objetivodemedicacaoPK;
    }

    public Objetivodemedicacao(int id, int idProjeto) {
        this.objetivodemedicacaoPK = new ObjetivodemedicacaoPK(id, idProjeto);
    }

    public ObjetivodemedicacaoPK getObjetivodemedicacaoPK() {
        return objetivodemedicacaoPK;
    }

    public void setObjetivodemedicacaoPK(ObjetivodemedicacaoPK objetivodemedicacaoPK) {
        this.objetivodemedicacaoPK = objetivodemedicacaoPK;
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

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (objetivodemedicacaoPK != null ? objetivodemedicacaoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Objetivodemedicacao)) {
            return false;
        }
        Objetivodemedicacao other = (Objetivodemedicacao) object;
        if ((this.objetivodemedicacaoPK == null && other.objetivodemedicacaoPK != null) || (this.objetivodemedicacaoPK != null && !this.objetivodemedicacaoPK.equals(other.objetivodemedicacaoPK)))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "model.Objetivodemedicacao[ objetivodemedicacaoPK=" + objetivodemedicacaoPK + " ]";
    }
    
}
