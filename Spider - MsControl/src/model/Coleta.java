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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Dan
 */
@Entity
@Table(name = "coleta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Coleta.findAll", query = "SELECT c FROM Coleta c"),
    @NamedQuery(name = "Coleta.findById", query = "SELECT c FROM Coleta c WHERE c.coletaPK.id = :id"),
    @NamedQuery(name = "Coleta.findByIdMedida", query = "SELECT c FROM Coleta c WHERE c.coletaPK.idMedida = :idMedida"),
    @NamedQuery(name = "Coleta.findByVersao", query = "SELECT c FROM Coleta c WHERE c.versao = :versao"),
    @NamedQuery(name = "Coleta.findByData", query = "SELECT c FROM Coleta c WHERE c.data = :data"),
    @NamedQuery(name = "Coleta.findByComposicao", query = "SELECT c FROM Coleta c WHERE c.composicao = :composicao"),
    @NamedQuery(name = "Coleta.findByTipoDeColeta", query = "SELECT c FROM Coleta c WHERE c.tipoDeColeta = :tipoDeColeta"),
    @NamedQuery(name = "Coleta.findByObservacao", query = "SELECT c FROM Coleta c WHERE c.observacao = :observacao")})
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
    @JoinColumn(name = "idMedida", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Medida medida;
    @JoinColumn(name = "idProcedimentoDeAnalise", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Procedimentodeanalise idProcedimentoDeAnalise;
    @JoinColumn(name = "idProcedimentoDeColeta", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Procedimentodecoleta idProcedimentoDeColeta;

    public Coleta() {
    }

    public Coleta(ColetaPK coletaPK) {
        this.coletaPK = coletaPK;
    }

    public Coleta(int id, int idMedida) {
        this.coletaPK = new ColetaPK(id, idMedida);
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

    public Medida getMedida() {
        return medida;
    }

    public void setMedida(Medida medida) {
        this.medida = medida;
    }

    public Procedimentodeanalise getIdProcedimentoDeAnalise() {
        return idProcedimentoDeAnalise;
    }

    public void setIdProcedimentoDeAnalise(Procedimentodeanalise idProcedimentoDeAnalise) {
        this.idProcedimentoDeAnalise = idProcedimentoDeAnalise;
    }

    public Procedimentodecoleta getIdProcedimentoDeColeta() {
        return idProcedimentoDeColeta;
    }

    public void setIdProcedimentoDeColeta(Procedimentodecoleta idProcedimentoDeColeta) {
        this.idProcedimentoDeColeta = idProcedimentoDeColeta;
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
