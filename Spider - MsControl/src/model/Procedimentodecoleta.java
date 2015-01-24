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
 * @author Spider
 */
@Entity
@Table(name = "procedimentodecoleta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Procedimentodecoleta.findAll", query = "SELECT p FROM Procedimentodecoleta p"),
    @NamedQuery(name = "Procedimentodecoleta.findById", query = "SELECT p FROM Procedimentodecoleta p WHERE p.id = :id"),
    @NamedQuery(name = "Procedimentodecoleta.findByMomento", query = "SELECT p FROM Procedimentodecoleta p WHERE p.momento = :momento"),
    @NamedQuery(name = "Procedimentodecoleta.findByPeriodicidadeInicio", query = "SELECT p FROM Procedimentodecoleta p WHERE p.periodicidadeInicio = :periodicidadeInicio"),
    @NamedQuery(name = "Procedimentodecoleta.findByProcedimentoFim", query = "SELECT p FROM Procedimentodecoleta p WHERE p.procedimentoFim = :procedimentoFim"),
    @NamedQuery(name = "Procedimentodecoleta.findByColetaTip", query = "SELECT p FROM Procedimentodecoleta p WHERE p.coletaTip = :coletaTip"),
    @NamedQuery(name = "Procedimentodecoleta.findByFerramentasUtilizada", query = "SELECT p FROM Procedimentodecoleta p WHERE p.ferramentasUtilizada = :ferramentasUtilizada")})
public class Procedimentodecoleta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "momento")
    private String momento;
    @Column(name = "periodicidade_inicio")
    @Temporal(TemporalType.DATE)
    private Date periodicidadeInicio;
    @Column(name = "procedimento_fim")
    @Temporal(TemporalType.DATE)
    private Date procedimentoFim;
    @Lob
    @Column(name = "frequencia")
    private String frequencia;
    @Lob
    @Column(name = "procedimento_coleta_descricao")
    private String procedimentoColetaDescricao;
    @Column(name = "coleta_tip")
    private String coletaTip;
    @Column(name = " ferramentas_utilizada")
    private String ferramentasUtilizada;
    @Lob
    @Column(name = "observacao")
    private String observacao;
    @JoinColumns({
        @JoinColumn(name = "Medida_id", referencedColumnName = "id"),
        @JoinColumn(name = "Medida_Projeto_id", referencedColumnName = "Projeto_id")})
    @ManyToOne(optional = false)
    private Medida medida;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "procedimentoDeColetaid")
    private List<Registroprocedimentocoleta> registroprocedimentocoletaList;

    public Procedimentodecoleta() {
    }

    public Procedimentodecoleta(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMomento() {
        return momento;
    }

    public void setMomento(String momento) {
        this.momento = momento;
    }

    public Date getPeriodicidadeInicio() {
        return periodicidadeInicio;
    }

    public void setPeriodicidadeInicio(Date periodicidadeInicio) {
        this.periodicidadeInicio = periodicidadeInicio;
    }

    public Date getProcedimentoFim() {
        return procedimentoFim;
    }

    public void setProcedimentoFim(Date procedimentoFim) {
        this.procedimentoFim = procedimentoFim;
    }

    public String getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(String frequencia) {
        this.frequencia = frequencia;
    }

    public String getProcedimentoColetaDescricao() {
        return procedimentoColetaDescricao;
    }

    public void setProcedimentoColetaDescricao(String procedimentoColetaDescricao) {
        this.procedimentoColetaDescricao = procedimentoColetaDescricao;
    }

    public String getColetaTip() {
        return coletaTip;
    }

    public void setColetaTip(String coletaTip) {
        this.coletaTip = coletaTip;
    }

    public String getFerramentasUtilizada() {
        return ferramentasUtilizada;
    }

    public void setFerramentasUtilizada(String ferramentasUtilizada) {
        this.ferramentasUtilizada = ferramentasUtilizada;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Medida getMedida() {
        return medida;
    }

    public void setMedida(Medida medida) {
        this.medida = medida;
    }

    @XmlTransient
    public List<Registroprocedimentocoleta> getRegistroprocedimentocoletaList() {
        return registroprocedimentocoletaList;
    }

    public void setRegistroprocedimentocoletaList(List<Registroprocedimentocoleta> registroprocedimentocoletaList) {
        this.registroprocedimentocoletaList = registroprocedimentocoletaList;
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
        if (!(object instanceof Procedimentodecoleta)) {
            return false;
        }
        Procedimentodecoleta other = (Procedimentodecoleta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "model.Procedimentodecoleta[ id=" + id + " ]";
    }
    
}
