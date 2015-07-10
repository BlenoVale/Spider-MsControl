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
@Table(name = "valorindicador")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Valorindicador.findAll", query = "SELECT v FROM Valorindicador v"),
    @NamedQuery(name = "Valorindicador.findByValorIndicadorcol", query = "SELECT v FROM Valorindicador v WHERE v.valorIndicadorcol = :valorIndicadorcol"),
    @NamedQuery(name = "Valorindicador.findByValor", query = "SELECT v FROM Valorindicador v WHERE v.valor = :valor"),
    @NamedQuery(name = "Valorindicador.findByData", query = "SELECT v FROM Valorindicador v WHERE v.data = :data"),
    @NamedQuery(name = "Valorindicador.findById", query = "SELECT v FROM Valorindicador v WHERE v.id = :id")})
public class Valorindicador implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ValorIndicadorcol")
    private Integer valorIndicadorcol;
    @Basic(optional = false)
    @Column(name = "valor")
    private double valor;
    @Column(name = "data")
    @Temporal(TemporalType.DATE)
    private Date data;
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "Indicador_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Indicador indicadorid;

    public Valorindicador() {
    }

    public Valorindicador(Integer valorIndicadorcol) {
        this.valorIndicadorcol = valorIndicadorcol;
    }

    public Valorindicador(Integer valorIndicadorcol, double valor) {
        this.valorIndicadorcol = valorIndicadorcol;
        this.valor = valor;
    }

    public Integer getValorIndicadorcol() {
        return valorIndicadorcol;
    }

    public void setValorIndicadorcol(Integer valorIndicadorcol) {
        this.valorIndicadorcol = valorIndicadorcol;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        hash += (valorIndicadorcol != null ? valorIndicadorcol.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Valorindicador)) {
            return false;
        }
        Valorindicador other = (Valorindicador) object;
        if ((this.valorIndicadorcol == null && other.valorIndicadorcol != null) || (this.valorIndicadorcol != null && !this.valorIndicadorcol.equals(other.valorIndicadorcol))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Valorindicador[ valorIndicadorcol=" + valorIndicadorcol + " ]";
    }
    
}
