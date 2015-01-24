/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
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
 * @author Spider
 */
@Entity
@Table(name = "medida")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Medida.findAll", query = "SELECT m FROM Medida m"),
    @NamedQuery(name = "Medida.findById", query = "SELECT m FROM Medida m WHERE m.medidaPK.id = :id"),
    @NamedQuery(name = "Medida.findByNome", query = "SELECT m FROM Medida m WHERE m.nome = :nome"),
    @NamedQuery(name = "Medida.findByData", query = "SELECT m FROM Medida m WHERE m.data = :data"),
    @NamedQuery(name = "Medida.findByVersao", query = "SELECT m FROM Medida m WHERE m.versao = :versao"),
    @NamedQuery(name = "Medida.findByMnemonico", query = "SELECT m FROM Medida m WHERE m.mnemonico = :mnemonico"),
    @NamedQuery(name = "Medida.findByPropriedadeMedida", query = "SELECT m FROM Medida m WHERE m.propriedadeMedida = :propriedadeMedida"),
    @NamedQuery(name = "Medida.findByEntidadeMedida", query = "SELECT m FROM Medida m WHERE m.entidadeMedida = :entidadeMedida"),
    @NamedQuery(name = "Medida.findByUnidadeMedida", query = "SELECT m FROM Medida m WHERE m.unidadeMedida = :unidadeMedida"),
    @NamedQuery(name = "Medida.findByEscala", query = "SELECT m FROM Medida m WHERE m.escala = :escala"),
    @NamedQuery(name = "Medida.findByFaixa", query = "SELECT m FROM Medida m WHERE m.faixa = :faixa"),
    @NamedQuery(name = "Medida.findByFormula", query = "SELECT m FROM Medida m WHERE m.formula = :formula"),
    @NamedQuery(name = "Medida.findByProjetoid", query = "SELECT m FROM Medida m WHERE m.medidaPK.projetoid = :projetoid")})
public class Medida implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MedidaPK medidaPK;
    @Column(name = "nome")
    private String nome;
    @Column(name = "data")
    @Temporal(TemporalType.DATE)
    private Date data;
    @Lob
    @Column(name = "definicao")
    private String definicao;
    @Column(name = "versao")
    private String versao;
    @Column(name = "mnemonico")
    private String mnemonico;
    @Column(name = "propriedadeMedida")
    private String propriedadeMedida;
    @Column(name = "entidadeMedida")
    private String entidadeMedida;
    @Column(name = "unidadeMedida")
    private String unidadeMedida;
    @Column(name = "escala")
    private String escala;
    @Column(name = "faixa")
    private String faixa;
    @Column(name = "formula")
    private String formula;
    @Lob
    @Column(name = "observacao")
    private String observacao;
    @JoinTable(name = "relacionado", joinColumns = {
        @JoinColumn(name = "Medida_id", referencedColumnName = "id"),
        @JoinColumn(name = "Medida_Projeto_id", referencedColumnName = "Projeto_id")}, inverseJoinColumns = {
        @JoinColumn(name = "ObjetivoDeQuestao_id", referencedColumnName = "id")})
    @ManyToMany
    private List<Objetivodequestao> objetivodequestaoList;
    @JoinColumn(name = "Projeto_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Projeto projeto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "medida")
    private List<Procedimentodeanalise> procedimentodeanaliseList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "medida")
    private List<Procedimentodecoleta> procedimentodecoletaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "medida")
    private List<Coleta> coletaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "medida")
    private List<Aprovacao> aprovacaoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "medida")
    private List<Analise> analiseList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "medida")
    private List<Registromedida> registromedidaList;

    public Medida() {
    }

    public Medida(MedidaPK medidaPK) {
        this.medidaPK = medidaPK;
    }

    public Medida(int id, int projetoid) {
        this.medidaPK = new MedidaPK(id, projetoid);
    }

    public MedidaPK getMedidaPK() {
        return medidaPK;
    }

    public void setMedidaPK(MedidaPK medidaPK) {
        this.medidaPK = medidaPK;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getDefinicao() {
        return definicao;
    }

    public void setDefinicao(String definicao) {
        this.definicao = definicao;
    }

    public String getVersao() {
        return versao;
    }

    public void setVersao(String versao) {
        this.versao = versao;
    }

    public String getMnemonico() {
        return mnemonico;
    }

    public void setMnemonico(String mnemonico) {
        this.mnemonico = mnemonico;
    }

    public String getPropriedadeMedida() {
        return propriedadeMedida;
    }

    public void setPropriedadeMedida(String propriedadeMedida) {
        this.propriedadeMedida = propriedadeMedida;
    }

    public String getEntidadeMedida() {
        return entidadeMedida;
    }

    public void setEntidadeMedida(String entidadeMedida) {
        this.entidadeMedida = entidadeMedida;
    }

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    public String getEscala() {
        return escala;
    }

    public void setEscala(String escala) {
        this.escala = escala;
    }

    public String getFaixa() {
        return faixa;
    }

    public void setFaixa(String faixa) {
        this.faixa = faixa;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
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

    @XmlTransient
    public List<Procedimentodeanalise> getProcedimentodeanaliseList() {
        return procedimentodeanaliseList;
    }

    public void setProcedimentodeanaliseList(List<Procedimentodeanalise> procedimentodeanaliseList) {
        this.procedimentodeanaliseList = procedimentodeanaliseList;
    }

    @XmlTransient
    public List<Procedimentodecoleta> getProcedimentodecoletaList() {
        return procedimentodecoletaList;
    }

    public void setProcedimentodecoletaList(List<Procedimentodecoleta> procedimentodecoletaList) {
        this.procedimentodecoletaList = procedimentodecoletaList;
    }

    @XmlTransient
    public List<Coleta> getColetaList() {
        return coletaList;
    }

    public void setColetaList(List<Coleta> coletaList) {
        this.coletaList = coletaList;
    }

    @XmlTransient
    public List<Aprovacao> getAprovacaoList() {
        return aprovacaoList;
    }

    public void setAprovacaoList(List<Aprovacao> aprovacaoList) {
        this.aprovacaoList = aprovacaoList;
    }

    @XmlTransient
    public List<Analise> getAnaliseList() {
        return analiseList;
    }

    public void setAnaliseList(List<Analise> analiseList) {
        this.analiseList = analiseList;
    }

    @XmlTransient
    public List<Registromedida> getRegistromedidaList() {
        return registromedidaList;
    }

    public void setRegistromedidaList(List<Registromedida> registromedidaList) {
        this.registromedidaList = registromedidaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (medidaPK != null ? medidaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Medida)) {
            return false;
        }
        Medida other = (Medida) object;
        if ((this.medidaPK == null && other.medidaPK != null) || (this.medidaPK != null && !this.medidaPK.equals(other.medidaPK)))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "model.Medida[ medidaPK=" + medidaPK + " ]";
    }
    
}
