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
@Table(name = "valormedida")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Valormedida.findAll", query = "SELECT v FROM Valormedida v"),
    @NamedQuery(name = "Valormedida.findById", query = "SELECT v FROM Valormedida v WHERE v.id = :id"),
    @NamedQuery(name = "Valormedida.findByValor", query = "SELECT v FROM Valormedida v WHERE v.valor = :valor"),
    @NamedQuery(name = "Valormedida.findByData", query = "SELECT v FROM Valormedida v WHERE v.data = :data"),
    @NamedQuery(name = "Valormedida.findByUsado", query = "SELECT v FROM Valormedida v WHERE v.usado = :usado")})
public class Valormedida implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "valor")
    private double valor;
    @Basic(optional = false)
    @Column(name = "data")
    @Temporal(TemporalType.DATE)
    private Date data;
    @Basic(optional = false)
    @Column(name = "usado")
    private boolean usado;
    @JoinColumn(name = "Medida_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Medida medidaid;

    public Valormedida() {
    }

    public Valormedida(Integer id) {
        this.id = id;
    }

    public Valormedida(Integer id, double valor, Date data, boolean usado) {
        this.id = id;
        this.valor = valor;
        this.data = data;
        this.usado = usado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
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
        if (!(object instanceof Valormedida)) {
            return false;
        }
        Valormedida other = (Valormedida) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Valormedida[ id=" + id + " ]";
    }
    
}
