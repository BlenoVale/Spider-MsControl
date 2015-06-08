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
 * @author Spider
 */
@Entity
@Table(name = "datasprocedimentoanalise")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Datasprocedimentoanalise.findAll", query = "SELECT d FROM Datasprocedimentoanalise d"),
    @NamedQuery(name = "Datasprocedimentoanalise.findById", query = "SELECT d FROM Datasprocedimentoanalise d WHERE d.id = :id"),
    @NamedQuery(name = "Datasprocedimentoanalise.findByDia", query = "SELECT d FROM Datasprocedimentoanalise d WHERE d.dia = :dia"),
    @NamedQuery(name = "Datasprocedimentoanalise.findByDataInicio", query = "SELECT d FROM Datasprocedimentoanalise d WHERE d.dataInicio = :dataInicio"),
    @NamedQuery(name = "Datasprocedimentoanalise.findByDataFim", query = "SELECT d FROM Datasprocedimentoanalise d WHERE d.dataFim = :dataFim"),
    @NamedQuery(name = "Datasprocedimentoanalise.findByTipo", query = "SELECT d FROM Datasprocedimentoanalise d WHERE d.tipo = :tipo")})
public class Datasprocedimentoanalise implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "Dia")
    private String dia;
    @Column(name = "dataInicio")
    @Temporal(TemporalType.DATE)
    private Date dataInicio;
    @Column(name = "dataFim")
    @Temporal(TemporalType.DATE)
    private Date dataFim;
    @Basic(optional = false)
    @Column(name = "tipo")
    private String tipo;
    @JoinColumn(name = "ProcedimentoDeAnalise_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Procedimentodeanalise procedimentoDeAnaliseid;

    public Datasprocedimentoanalise() {
    }

    public Datasprocedimentoanalise(Integer id) {
        this.id = id;
    }

    public Datasprocedimentoanalise(Integer id, String tipo) {
        this.id = id;
        this.tipo = tipo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
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
        if (!(object instanceof Datasprocedimentoanalise)) {
            return false;
        }
        Datasprocedimentoanalise other = (Datasprocedimentoanalise) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "model.Datasprocedimentoanalise[ id=" + id + " ]";
    }
    
}
