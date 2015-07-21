/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
 * @author Bleno Vale
 */
@Entity
@Table(name = "registrodatacomunicacao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Registrodatacomunicacao.findAll", query = "SELECT r FROM Registrodatacomunicacao r"),
    @NamedQuery(name = "Registrodatacomunicacao.findById", query = "SELECT r FROM Registrodatacomunicacao r WHERE r.id = :id"),
    @NamedQuery(name = "Registrodatacomunicacao.findByDataComunicacao", query = "SELECT r FROM Registrodatacomunicacao r WHERE r.dataComunicacao = :dataComunicacao"),
    @NamedQuery(name = "Registrodatacomunicacao.findByTipo", query = "SELECT r FROM Registrodatacomunicacao r WHERE r.tipo = :tipo")})
public class Registrodatacomunicacao implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "DataComunicacao")
    @Temporal(TemporalType.DATE)
    private Date dataComunicacao;
    @Column(name = "Tipo")
    private Integer tipo;
    @JoinColumn(name = "ProcedimentoDeAnalise_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Procedimentodeanalise procedimentoDeAnaliseid;

    public Registrodatacomunicacao() {
    }

    public Registrodatacomunicacao(Integer id) {
        this.id = id;
    }

    public Registrodatacomunicacao(Integer id, Date dataComunicacao) {
        this.id = id;
        this.dataComunicacao = dataComunicacao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataComunicacao() {
        return dataComunicacao;
    }

    public void setDataComunicacao(Date dataComunicacao) {
        this.dataComunicacao = dataComunicacao;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public Procedimentodeanalise getProcedimentoDeAnaliseid() {
        return procedimentoDeAnaliseid;
    }

    public void setProcedimentoDeAnaliseid(Procedimentodeanalise procedimentoDeAnaliseid) {
        this.procedimentoDeAnaliseid = procedimentoDeAnaliseid;
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
        if (!(object instanceof Registrodatacomunicacao)) {
            return false;
        }
        Registrodatacomunicacao other = (Registrodatacomunicacao) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Registrodatacomunicacao[ id=" + id + " ]";
    }
    
}
