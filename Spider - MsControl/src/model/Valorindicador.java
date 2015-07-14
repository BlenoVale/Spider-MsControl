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
 * @author paulosouza
 */
@Entity
@Table(name = "valorindicador")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Valorindicador.findAll", query = "SELECT v FROM Valorindicador v"),
    @NamedQuery(name = "Valorindicador.findById", query = "SELECT v FROM Valorindicador v WHERE v.id = :id"),
    @NamedQuery(name = "Valorindicador.findByValor", query = "SELECT v FROM Valorindicador v WHERE v.valor = :valor"),
    @NamedQuery(name = "Valorindicador.findByData", query = "SELECT v FROM Valorindicador v WHERE v.data = :data")})
public class Valorindicador implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "valor")
    private double valor;
    @Column(name = "data")
    @Temporal(TemporalType.DATE)
    private Date data;
    @JoinColumn(name = "Indicador_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Indicador indicadorid;

    public Valorindicador() {
    }

    public Valorindicador(Integer id) {
        this.id = id;
    }

    public Valorindicador(Integer id, double valor) {
        this.id = id;
        this.valor = valor;
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

    public Indicador getIndicadorid() {
        return indicadorid;
    }

    public void setIndicadorid(Indicador indicadorid) {
        this.indicadorid = indicadorid;
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
        if (!(object instanceof Valorindicador)) {
            return false;
        }
        Valorindicador other = (Valorindicador) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "model.Valorindicador[ id=" + id + " ]";
    }

}
