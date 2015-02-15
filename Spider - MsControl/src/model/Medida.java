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
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author BlenoVale
 */
@Entity
@Table(name = "medida")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Medida.findAll", query = "SELECT m FROM Medida m"),
    @NamedQuery(name = "Medida.findById", query = "SELECT m FROM Medida m WHERE m.id = :id"),
    @NamedQuery(name = "Medida.findByNome", query = "SELECT m FROM Medida m WHERE m.nome = :nome"),
    @NamedQuery(name = "Medida.findByPontoDeVista", query = "SELECT m FROM Medida m WHERE m.pontoDeVista = :pontoDeVista"),
    @NamedQuery(name = "Medida.findByMnemonico", query = "SELECT m FROM Medida m WHERE m.mnemonico = :mnemonico"),
    @NamedQuery(name = "Medida.findByUnidadeMedida", query = "SELECT m FROM Medida m WHERE m.unidadeMedida = :unidadeMedida"),
    @NamedQuery(name = "Medida.findByEscala", query = "SELECT m FROM Medida m WHERE m.escala = :escala"),
    @NamedQuery(name = "Medida.findByFaixa", query = "SELECT m FROM Medida m WHERE m.faixa = :faixa")})
public class Medida implements Serializable {
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
    @Column(name = "definicao")
    private String definicao;
    @Basic(optional = false)
    @Column(name = "pontoDeVista")
    private String pontoDeVista;
    @Basic(optional = false)
    @Column(name = "mnemonico")
    private String mnemonico;
    @Column(name = "unidadeMedida")
    private String unidadeMedida;
    @Basic(optional = false)
    @Column(name = "escala")
    private String escala;
    @Basic(optional = false)
    @Column(name = "faixa")
    private String faixa;
    @Lob
    @Column(name = "observacao")
    private String observacao;
    @ManyToMany(mappedBy = "medidaList")
    private List<Indicador> indicadorList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "medidaid")
    private List<Registromedida> registromedidaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "medidaid")
    private List<Procedimentodecoleta> procedimentodecoletaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "medidaid")
    private List<Coleta> coletaList;

    public Medida() {
    }

    public Medida(Integer id) {
        this.id = id;
    }

    public Medida(Integer id, String nome, String definicao, String pontoDeVista, String mnemonico, String escala, String faixa) {
        this.id = id;
        this.nome = nome;
        this.definicao = definicao;
        this.pontoDeVista = pontoDeVista;
        this.mnemonico = mnemonico;
        this.escala = escala;
        this.faixa = faixa;
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

    public String getDefinicao() {
        return definicao;
    }

    public void setDefinicao(String definicao) {
        this.definicao = definicao;
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

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    @XmlTransient
    public List<Indicador> getIndicadorList() {
        return indicadorList;
    }

    public void setIndicadorList(List<Indicador> indicadorList) {
        this.indicadorList = indicadorList;
    }

    @XmlTransient
    public List<Registromedida> getRegistromedidaList() {
        return registromedidaList;
    }

    public void setRegistromedidaList(List<Registromedida> registromedidaList) {
        this.registromedidaList = registromedidaList;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Medida)) {
            return false;
        }
        Medida other = (Medida) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Medida[ id=" + id + " ]";
    }
    
}
