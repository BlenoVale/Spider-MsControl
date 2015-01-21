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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
 * @author Dan
 */
@Entity
@Table(name = "objetivodequestao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Objetivodequestao.findAll", query = "SELECT o FROM Objetivodequestao o"),
    @NamedQuery(name = "Objetivodequestao.findById", query = "SELECT o FROM Objetivodequestao o WHERE o.objetivodequestaoPK.id = :id"),
    @NamedQuery(name = "Objetivodequestao.findByObjetivoDeMedicaoid", query = "SELECT o FROM Objetivodequestao o WHERE o.objetivodequestaoPK.objetivoDeMedicaoid = :objetivoDeMedicaoid"),
    @NamedQuery(name = "Objetivodequestao.findByObjetivoDeMedicaoProjetoid", query = "SELECT o FROM Objetivodequestao o WHERE o.objetivodequestaoPK.objetivoDeMedicaoProjetoid = :objetivoDeMedicaoProjetoid"),
    @NamedQuery(name = "Objetivodequestao.findByNome", query = "SELECT o FROM Objetivodequestao o WHERE o.nome = :nome"),
    @NamedQuery(name = "Objetivodequestao.findByIndicador", query = "SELECT o FROM Objetivodequestao o WHERE o.indicador = :indicador"),
    @NamedQuery(name = "Objetivodequestao.findByPrioridade", query = "SELECT o FROM Objetivodequestao o WHERE o.prioridade = :prioridade"),
    @NamedQuery(name = "Objetivodequestao.findByTipoDeDerivacao", query = "SELECT o FROM Objetivodequestao o WHERE o.tipoDeDerivacao = :tipoDeDerivacao"),
    @NamedQuery(name = "Objetivodequestao.findByDataLevantamento", query = "SELECT o FROM Objetivodequestao o WHERE o.dataLevantamento = :dataLevantamento")})
public class Objetivodequestao implements Serializable {
    @Basic(optional = false)
    @Column(name = "pontoDevista")
    private String pontoDevista;
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ObjetivodequestaoPK objetivodequestaoPK;
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @Column(name = "indicador")
    private String indicador;
    @Basic(optional = false)
    @Lob
    @Column(name = "descricaoIndicador")
    private String descricaoIndicador;
    @Basic(optional = false)
    @Column(name = "prioridade")
    private int prioridade;
    @Basic(optional = false)
    @Column(name = "tipoDeDerivacao")
    private String tipoDeDerivacao;
    @Basic(optional = false)
    @Column(name = "dataLevantamento")
    @Temporal(TemporalType.DATE)
    private Date dataLevantamento;
    @Lob
    @Column(name = "observacao")
    private String observacao;
    @JoinColumns({
        @JoinColumn(name = "ObjetivoDeMedicao_id", referencedColumnName = "id", insertable = false, updatable = false),
        @JoinColumn(name = "ObjetivoDeMedicao_Projeto_id", referencedColumnName = "Projeto_id", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Objetivodemedicao objetivodemedicao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "objetivodequestao")
    private List<Registroobjetivoquestao> registroobjetivoquestaoList;

    public Objetivodequestao() {
    }

    public Objetivodequestao(ObjetivodequestaoPK objetivodequestaoPK) {
        this.objetivodequestaoPK = objetivodequestaoPK;
    }

    public Objetivodequestao(ObjetivodequestaoPK objetivodequestaoPK, String nome, String indicador, String descricaoIndicador, int prioridade, String tipoDeDerivacao, Date dataLevantamento) {
        this.objetivodequestaoPK = objetivodequestaoPK;
        this.nome = nome;
        this.indicador = indicador;
        this.descricaoIndicador = descricaoIndicador;
        this.prioridade = prioridade;
        this.tipoDeDerivacao = tipoDeDerivacao;
        this.dataLevantamento = dataLevantamento;
    }

    public Objetivodequestao(int id, int objetivoDeMedicaoid, int objetivoDeMedicaoProjetoid) {
        this.objetivodequestaoPK = new ObjetivodequestaoPK(id, objetivoDeMedicaoid, objetivoDeMedicaoProjetoid);
    }

    public ObjetivodequestaoPK getObjetivodequestaoPK() {
        return objetivodequestaoPK;
    }

    public void setObjetivodequestaoPK(ObjetivodequestaoPK objetivodequestaoPK) {
        this.objetivodequestaoPK = objetivodequestaoPK;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIndicador() {
        return indicador;
    }

    public void setIndicador(String indicador) {
        this.indicador = indicador;
    }

    public String getDescricaoIndicador() {
        return descricaoIndicador;
    }

    public void setDescricaoIndicador(String descricaoIndicador) {
        this.descricaoIndicador = descricaoIndicador;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }

    public String getTipoDeDerivacao() {
        return tipoDeDerivacao;
    }

    public void setTipoDeDerivacao(String tipoDeDerivacao) {
        this.tipoDeDerivacao = tipoDeDerivacao;
    }

    public Date getDataLevantamento() {
        return dataLevantamento;
    }

    public void setDataLevantamento(Date dataLevantamento) {
        this.dataLevantamento = dataLevantamento;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Objetivodemedicao getObjetivodemedicao() {
        return objetivodemedicao;
    }

    public void setObjetivodemedicao(Objetivodemedicao objetivodemedicao) {
        this.objetivodemedicao = objetivodemedicao;
    }

    @XmlTransient
    public List<Registroobjetivoquestao> getRegistroobjetivoquestaoList() {
        return registroobjetivoquestaoList;
    }

    public void setRegistroobjetivoquestaoList(List<Registroobjetivoquestao> registroobjetivoquestaoList) {
        this.registroobjetivoquestaoList = registroobjetivoquestaoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (objetivodequestaoPK != null ? objetivodequestaoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Objetivodequestao)) {
            return false;
        }
        Objetivodequestao other = (Objetivodequestao) object;
        if ((this.objetivodequestaoPK == null && other.objetivodequestaoPK != null) || (this.objetivodequestaoPK != null && !this.objetivodequestaoPK.equals(other.objetivodequestaoPK)))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "model.Objetivodequestao[ objetivodequestaoPK=" + objetivodequestaoPK + " ]";
    }

    public String getPontoDevista() {
        return pontoDevista;
    }

    public void setPontoDevista(String pontoDevista) {
        this.pontoDevista = pontoDevista;
    }
    
}
