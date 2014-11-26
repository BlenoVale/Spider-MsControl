/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
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
 * @author GEDAE
 */
@Entity
@Table(name = "objetivodequestao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Objetivodequestao.findAll", query = "SELECT o FROM Objetivodequestao o"),
    @NamedQuery(name = "Objetivodequestao.findById", query = "SELECT o FROM Objetivodequestao o WHERE o.objetivodequestaoPK.id = :id"),
    @NamedQuery(name = "Objetivodequestao.findByNome", query = "SELECT o FROM Objetivodequestao o WHERE o.nome = :nome"),
    @NamedQuery(name = "Objetivodequestao.findByNomeDoUsuario", query = "SELECT o FROM Objetivodequestao o WHERE o.nomeDoUsuario = :nomeDoUsuario"),
    @NamedQuery(name = "Objetivodequestao.findByIndicador", query = "SELECT o FROM Objetivodequestao o WHERE o.indicador = :indicador"),
    @NamedQuery(name = "Objetivodequestao.findByPrioridade", query = "SELECT o FROM Objetivodequestao o WHERE o.prioridade = :prioridade"),
    @NamedQuery(name = "Objetivodequestao.findByTipoDeDerivacao", query = "SELECT o FROM Objetivodequestao o WHERE o.tipoDeDerivacao = :tipoDeDerivacao"),
    @NamedQuery(name = "Objetivodequestao.findByDataLevantamento", query = "SELECT o FROM Objetivodequestao o WHERE o.dataLevantamento = :dataLevantamento"),
    @NamedQuery(name = "Objetivodequestao.findByObjetivoDeMedicacaoid", query = "SELECT o FROM Objetivodequestao o WHERE o.objetivodequestaoPK.objetivoDeMedicacaoid = :objetivoDeMedicacaoid"),
    @NamedQuery(name = "Objetivodequestao.findByObjetivoDeMedicacaoProjetoid", query = "SELECT o FROM Objetivodequestao o WHERE o.objetivodequestaoPK.objetivoDeMedicacaoProjetoid = :objetivoDeMedicacaoProjetoid")})
public class Objetivodequestao implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ObjetivodequestaoPK objetivodequestaoPK;
    @Column(name = "nome")
    private String nome;
    @Column(name = "nomeDoUsuario")
    private String nomeDoUsuario;
    @Column(name = "indicador")
    private String indicador;
    @Lob
    @Column(name = "descricaoIndicador")
    private String descricaoIndicador;
    @Column(name = "prioridade")
    private String prioridade;
    @Column(name = "tipoDeDerivacao")
    private String tipoDeDerivacao;
    @Column(name = "dataLevantamento")
    @Temporal(TemporalType.DATE)
    private Date dataLevantamento;
    @Lob
    @Column(name = "observacao")
    private String observacao;
    @JoinColumns({
        @JoinColumn(name = "ObjetivoDeMedicacao_id", referencedColumnName = "id", insertable = false, updatable = false),
        @JoinColumn(name = "ObjetivoDeMedicacao_Projeto_id", referencedColumnName = "Projeto_id", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Objetivodemedicacao objetivodemedicacao;

    public Objetivodequestao() {
    }

    public Objetivodequestao(ObjetivodequestaoPK objetivodequestaoPK) {
        this.objetivodequestaoPK = objetivodequestaoPK;
    }

    public Objetivodequestao(int id, int objetivoDeMedicacaoid, int objetivoDeMedicacaoProjetoid) {
        this.objetivodequestaoPK = new ObjetivodequestaoPK(id, objetivoDeMedicacaoid, objetivoDeMedicacaoProjetoid);
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

    public String getNomeDoUsuario() {
        return nomeDoUsuario;
    }

    public void setNomeDoUsuario(String nomeDoUsuario) {
        this.nomeDoUsuario = nomeDoUsuario;
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

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
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

    public Objetivodemedicacao getObjetivodemedicacao() {
        return objetivodemedicacao;
    }

    public void setObjetivodemedicacao(Objetivodemedicacao objetivodemedicacao) {
        this.objetivodemedicacao = objetivodemedicacao;
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
    
}
