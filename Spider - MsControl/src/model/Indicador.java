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
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Spider-02
 */
@Entity
@Table(name = "indicador")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Indicador.findAll", query = "SELECT i FROM Indicador i"),
    @NamedQuery(name = "Indicador.findById", query = "SELECT i FROM Indicador i WHERE i.id = :id"),
    @NamedQuery(name = "Indicador.findByNome", query = "SELECT i FROM Indicador i WHERE i.nome = :nome"),
    @NamedQuery(name = "Indicador.findByPontoDeVista", query = "SELECT i FROM Indicador i WHERE i.pontoDeVista = :pontoDeVista"),
    @NamedQuery(name = "Indicador.findByMnemonico", query = "SELECT i FROM Indicador i WHERE i.mnemonico = :mnemonico"),
    @NamedQuery(name = "Indicador.findByEntidadeDeMedida", query = "SELECT i FROM Indicador i WHERE i.entidadeDeMedida = :entidadeDeMedida"),
    @NamedQuery(name = "Indicador.findByPropriedadeDeMedidade", query = "SELECT i FROM Indicador i WHERE i.propriedadeDeMedidade = :propriedadeDeMedidade"),
    @NamedQuery(name = "Indicador.findByAprovacao", query = "SELECT i FROM Indicador i WHERE i.aprovacao = :aprovacao"),
    @NamedQuery(name = "Indicador.findByPrioridade", query = "SELECT i FROM Indicador i WHERE i.prioridade = :prioridade")})
public class Indicador implements Serializable {
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
    @Lob
    @Column(name = "descricao")
    private String descricao;
    @Basic(optional = false)
    @Column(name = "pontoDeVista")
    private String pontoDeVista;
    @Basic(optional = false)
    @Column(name = "mnemonico")
    private String mnemonico;
    @Basic(optional = false)
    @Column(name = "entidadeDeMedida")
    private String entidadeDeMedida;
    @Column(name = "propriedadeDeMedidade")
    private String propriedadeDeMedidade;
    @Basic(optional = false)
    @Column(name = "aprovacao")
    private int aprovacao;
    @Lob
    @Column(name = "observacao")
    private String observacao;
    @Basic(optional = false)
    @Column(name = "prioridade")
    private int prioridade;
    @JoinTable(name = "indicador_has_medida", joinColumns = {
        @JoinColumn(name = "Indicador_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "Medida_id", referencedColumnName = "id")})
    @ManyToMany
    private List<Medida> medidaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "indicadorid")
    private List<Procedimentodeanalise> procedimentodeanaliseList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "indicadorid")
    private List<Registroindicador> registroindicadorList;
    @JoinColumn(name = "ObjetivoDeQuestao_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Objetivodequestao objetivoDeQuestaoid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "indicadorid")
    private List<Analise> analiseList;

    public Indicador() {
    }

    public Indicador(Integer id) {
        this.id = id;
    }

    public Indicador(Integer id, String nome, String descricao, String pontoDeVista, String mnemonico, String entidadeDeMedida, int aprovacao, int prioridade) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.pontoDeVista = pontoDeVista;
        this.mnemonico = mnemonico;
        this.entidadeDeMedida = entidadeDeMedida;
        this.aprovacao = aprovacao;
        this.prioridade = prioridade;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPontoDeVista() {
        return pontoDeVista;
    }

    public void setPontoDeVista(String pontoDeVista) {
        this.pontoDeVista = pontoDeVista;
    }

    public String getMnemonico() {
        return mnemonico;
    }

    public void setMnemonico(String mnemonico) {
        this.mnemonico = mnemonico;
    }

    public String getEntidadeDeMedida() {
        return entidadeDeMedida;
    }

    public void setEntidadeDeMedida(String entidadeDeMedida) {
        this.entidadeDeMedida = entidadeDeMedida;
    }

    public String getPropriedadeDeMedidade() {
        return propriedadeDeMedidade;
    }

    public void setPropriedadeDeMedidade(String propriedadeDeMedidade) {
        this.propriedadeDeMedidade = propriedadeDeMedidade;
    }

    public int getAprovacao() {
        return aprovacao;
    }

    public void setAprovacao(int aprovacao) {
        this.aprovacao = aprovacao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }

    @XmlTransient
    public List<Medida> getMedidaList() {
        return medidaList;
    }

    public void setMedidaList(List<Medida> medidaList) {
        this.medidaList = medidaList;
    }

    @XmlTransient
    public List<Procedimentodeanalise> getProcedimentodeanaliseList() {
        return procedimentodeanaliseList;
    }

    public void setProcedimentodeanaliseList(List<Procedimentodeanalise> procedimentodeanaliseList) {
        this.procedimentodeanaliseList = procedimentodeanaliseList;
    }

    @XmlTransient
    public List<Registroindicador> getRegistroindicadorList() {
        return registroindicadorList;
    }

    public void setRegistroindicadorList(List<Registroindicador> registroindicadorList) {
        this.registroindicadorList = registroindicadorList;
    }

    public Objetivodequestao getObjetivoDeQuestaoid() {
        return objetivoDeQuestaoid;
    }

    public void setObjetivoDeQuestaoid(Objetivodequestao objetivoDeQuestaoid) {
        this.objetivoDeQuestaoid = objetivoDeQuestaoid;
    }

    @XmlTransient
    public List<Analise> getAnaliseList() {
        return analiseList;
    }

    public void setAnaliseList(List<Analise> analiseList) {
        this.analiseList = analiseList;
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
        if (!(object instanceof Indicador)) {
            return false;
        }
        Indicador other = (Indicador) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "model.Indicador[ id=" + id + " ]";
    }
    
}
