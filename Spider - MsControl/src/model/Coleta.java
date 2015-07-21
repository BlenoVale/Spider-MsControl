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
@Table(name = "coleta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Coleta.findAll", query = "SELECT c FROM Coleta c"),
    @NamedQuery(name = "Coleta.findById", query = "SELECT c FROM Coleta c WHERE c.id = :id"),
    @NamedQuery(name = "Coleta.findByData", query = "SELECT c FROM Coleta c WHERE c.data = :data"),
    @NamedQuery(name = "Coleta.findByValorDaColeta", query = "SELECT c FROM Coleta c WHERE c.valorDaColeta = :valorDaColeta"),
    @NamedQuery(name = "Coleta.findByUsado", query = "SELECT c FROM Coleta c WHERE c.usado = :usado")})
public class Coleta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "data")
    @Temporal(TemporalType.DATE)
    private Date data;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valorDaColeta")
    private Double valorDaColeta;
    @Basic(optional = false)
    @Column(name = "usado")
    private boolean usado;
    @JoinColumn(name = "Medida_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Medida medidaid;

    public Coleta() {
    }

    public Coleta(Integer id) {
        this.id = id;
    }

    public Coleta(Integer id, Date data, boolean usado) {
        this.id = id;
        this.data = data;
        this.usado = usado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Double getValorDaColeta() {
        return valorDaColeta;
    }

    public void setValorDaColeta(Double valorDaColeta) {
        this.valorDaColeta = valorDaColeta;
    }

    public boolean getUsado() {
        return usado;
    }

    public void setUsado(boolean usado) {
        this.usado = usado;
    }

    public Medida getMedidaid() {
        return medidaid;
    }

    public void setMedidaid(Medida medidaid) {
        this.medidaid = medidaid;
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
        if (!(object instanceof Coleta)) {
            return false;
        }
        Coleta other = (Coleta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Coleta[ id=" + id + " ]";
    }
    
}
