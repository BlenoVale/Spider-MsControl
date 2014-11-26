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
import javax.persistence.JoinColumns;
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
 * @author GEDAE
 */
@Entity
@Table(name = "definicao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Definicao.findAll", query = "SELECT d FROM Definicao d"),
    @NamedQuery(name = "Definicao.findById", query = "SELECT d FROM Definicao d WHERE d.definicaoPK.id = :id"),
    @NamedQuery(name = "Definicao.findByVersao", query = "SELECT d FROM Definicao d WHERE d.versao = :versao"),
    @NamedQuery(name = "Definicao.findByMnemonico", query = "SELECT d FROM Definicao d WHERE d.mnemonico = :mnemonico"),
    @NamedQuery(name = "Definicao.findByPropriedadeMedida", query = "SELECT d FROM Definicao d WHERE d.propriedadeMedida = :propriedadeMedida"),
    @NamedQuery(name = "Definicao.findByEntidadeMedida", query = "SELECT d FROM Definicao d WHERE d.entidadeMedida = :entidadeMedida"),
    @NamedQuery(name = "Definicao.findByUnidadeMedida", query = "SELECT d FROM Definicao d WHERE d.unidadeMedida = :unidadeMedida"),
    @NamedQuery(name = "Definicao.findByEscala", query = "SELECT d FROM Definicao d WHERE d.escala = :escala"),
    @NamedQuery(name = "Definicao.findByFaixa", query = "SELECT d FROM Definicao d WHERE d.faixa = :faixa"),
    @NamedQuery(name = "Definicao.findByFormula", query = "SELECT d FROM Definicao d WHERE d.formula = :formula"),
    @NamedQuery(name = "Definicao.findByMedidaid", query = "SELECT d FROM Definicao d WHERE d.definicaoPK.medidaid = :medidaid"),
    @NamedQuery(name = "Definicao.findByMedidaProjetoid", query = "SELECT d FROM Definicao d WHERE d.definicaoPK.medidaProjetoid = :medidaProjetoid")})
public class Definicao implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DefinicaoPK definicaoPK;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "definicao")
    private List<Composicao> composicaoList;
    @JoinColumns({
        @JoinColumn(name = "Medida_id", referencedColumnName = "id", insertable = false, updatable = false),
        @JoinColumn(name = "Medida_Projeto_id", referencedColumnName = "Projeto_id", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Medida medida;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "definicao")
    private List<Aprovacao> aprovacaoList;

    public Definicao() {
    }

    public Definicao(DefinicaoPK definicaoPK) {
        this.definicaoPK = definicaoPK;
    }

    public Definicao(int id, int medidaid, int medidaProjetoid) {
        this.definicaoPK = new DefinicaoPK(id, medidaid, medidaProjetoid);
    }

    public DefinicaoPK getDefinicaoPK() {
        return definicaoPK;
    }

    public void setDefinicaoPK(DefinicaoPK definicaoPK) {
        this.definicaoPK = definicaoPK;
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
    public List<Composicao> getComposicaoList() {
        return composicaoList;
    }

    public void setComposicaoList(List<Composicao> composicaoList) {
        this.composicaoList = composicaoList;
    }

    public Medida getMedida() {
        return medida;
    }

    public void setMedida(Medida medida) {
        this.medida = medida;
    }

    @XmlTransient
    public List<Aprovacao> getAprovacaoList() {
        return aprovacaoList;
    }

    public void setAprovacaoList(List<Aprovacao> aprovacaoList) {
        this.aprovacaoList = aprovacaoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (definicaoPK != null ? definicaoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Definicao)) {
            return false;
        }
        Definicao other = (Definicao) object;
        if ((this.definicaoPK == null && other.definicaoPK != null) || (this.definicaoPK != null && !this.definicaoPK.equals(other.definicaoPK)))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "model.Definicao[ definicaoPK=" + definicaoPK + " ]";
    }
    
}
