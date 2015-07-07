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
 * @author BlenoVale
 */
@Entity
@Table(name = "datasprocedimentocoleta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Datasprocedimentocoleta.findAll", query = "SELECT d FROM Datasprocedimentocoleta d"),
    @NamedQuery(name = "Datasprocedimentocoleta.findById", query = "SELECT d FROM Datasprocedimentocoleta d WHERE d.id = :id"),
    @NamedQuery(name = "Datasprocedimentocoleta.findByDia", query = "SELECT d FROM Datasprocedimentocoleta d WHERE d.dia = :dia"),
    @NamedQuery(name = "Datasprocedimentocoleta.findByDataInicio", query = "SELECT d FROM Datasprocedimentocoleta d WHERE d.dataInicio = :dataInicio"),
    @NamedQuery(name = "Datasprocedimentocoleta.findByDataFim", query = "SELECT d FROM Datasprocedimentocoleta d WHERE d.dataFim = :dataFim")})
public class Datasprocedimentocoleta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "dia")
    private String dia;
    @Column(name = "dataInicio")
    @Temporal(TemporalType.DATE)
    private Date dataInicio;
    @Column(name = "dataFim")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataFim;
    @JoinColumn(name = "ProcedimentoDeColeta_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Procedimentodecoleta procedimentoDeColetaid;

    public Datasprocedimentocoleta() {
    }

    public Datasprocedimentocoleta(Integer id) {
        this.id = id;
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

    public Procedimentodecoleta getProcedimentoDeColetaid() {
        return procedimentoDeColetaid;
    }

    public void setProcedimentoDeColetaid(Procedimentodecoleta procedimentoDeColetaid) {
        this.procedimentoDeColetaid = procedimentoDeColetaid;
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
        if (!(object instanceof Datasprocedimentocoleta)) {
            return false;
        }
        Datasprocedimentocoleta other = (Datasprocedimentocoleta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Datasprocedimentocoleta[ id=" + id + " ]";
    }
    
}
