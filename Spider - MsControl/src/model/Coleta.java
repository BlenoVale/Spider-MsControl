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
import javax.persistence.JoinColumns;
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
@Table(name = "coleta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Coleta.findAll", query = "SELECT c FROM Coleta c"),
    @NamedQuery(name = "Coleta.findById", query = "SELECT c FROM Coleta c WHERE c.coletaPK.id = :id"),
    @NamedQuery(name = "Coleta.findByVersao", query = "SELECT c FROM Coleta c WHERE c.versao = :versao"),
    @NamedQuery(name = "Coleta.findByData", query = "SELECT c FROM Coleta c WHERE c.data = :data"),
    @NamedQuery(name = "Coleta.findByComposicao", query = "SELECT c FROM Coleta c WHERE c.composicao = :composicao"),
    @NamedQuery(name = "Coleta.findByTipoDeColeta", query = "SELECT c FROM Coleta c WHERE c.tipoDeColeta = :tipoDeColeta"),
    @NamedQuery(name = "Coleta.findByObservacao", query = "SELECT c FROM Coleta c WHERE c.observacao = :observacao"),
    @NamedQuery(name = "Coleta.findByMedidaid", query = "SELECT c FROM Coleta c WHERE c.coletaPK.medidaid = :medidaid"),
    @NamedQuery(name = "Coleta.findByMedidaProjetoid", query = "SELECT c FROM Coleta c WHERE c.coletaPK.medidaProjetoid = :medidaProjetoid")})
public class Coleta implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ColetaPK coletaPK;
    @Column(name = "versao")
    private String versao;
    @Column(name = "data")
    @Temporal(TemporalType.DATE)
    private Date data;
    @Column(name = "composicao")
    private String composicao;
    @Column(name = "tipoDeColeta")
    private String tipoDeColeta;
    @Column(name = "observacao")
    private String observacao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "coleta")
    private List<Procedimentodeanalise> procedimentodeanaliseList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "coleta")
    private List<Procedimentodecoleta> procedimentodecoletaList;
    @JoinColumns({
        @JoinColumn(name = "Medida_id", referencedColumnName = "id", insertable = false, updatable = false),
        @JoinColumn(name = "Medida_Projeto_id", referencedColumnName = "Projeto_id", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Medida medida;

    public Coleta() {
    }

    public Coleta(ColetaPK coletaPK) {
        this.coletaPK = coletaPK;
    }

    public Coleta(int id, int medidaid, int medidaProjetoid) {
        this.coletaPK = new ColetaPK(id, medidaid, medidaProjetoid);
    }

    public ColetaPK getColetaPK() {
        return coletaPK;
    }

    public void setColetaPK(ColetaPK coletaPK) {
        this.coletaPK = coletaPK;
    }

    public String getVersao() {
        return versao;
    }

    public void setVersao(String versao) {
        this.versao = versao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getComposicao() {
        return composicao;
    }

    public void setComposicao(String composicao) {
        this.composicao = composicao;
    }

    public String getTipoDeColeta() {
        return tipoDeColeta;
    }

    public void setTipoDeColeta(String tipoDeColeta) {
        this.tipoDeColeta = tipoDeColeta;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
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

    public Medida getMedida() {
        return medida;
    }

    public void setMedida(Medida medida) {
        this.medida = medida;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (coletaPK != null ? coletaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Coleta)) {
            return false;
        }
        Coleta other = (Coleta) object;
        if ((this.coletaPK == null && other.coletaPK != null) || (this.coletaPK != null && !this.coletaPK.equals(other.coletaPK)))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "model.Coleta[ coletaPK=" + coletaPK + " ]";
    }
    
}
