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
import javax.persistence.Lob;
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
@Table(name = "relatorios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Relatorios.findAll", query = "SELECT r FROM Relatorios r"),
    @NamedQuery(name = "Relatorios.findByIdRelatorio", query = "SELECT r FROM Relatorios r WHERE r.idRelatorio = :idRelatorio"),
    @NamedQuery(name = "Relatorios.findByTipoRelatorio", query = "SELECT r FROM Relatorios r WHERE r.tipoRelatorio = :tipoRelatorio"),
    @NamedQuery(name = "Relatorios.findByData", query = "SELECT r FROM Relatorios r WHERE r.data = :data"),
    @NamedQuery(name = "Relatorios.findByAutor", query = "SELECT r FROM Relatorios r WHERE r.autor = :autor")})
public class Relatorios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idRelatorio")
    private Integer idRelatorio;
    @Basic(optional = false)
    @Column(name = "tipoRelatorio")
    private String tipoRelatorio;
    @Basic(optional = false)
    @Column(name = "data")
    @Temporal(TemporalType.DATE)
    private Date data;
    @Basic(optional = false)
    @Column(name = "autor")
    private String autor;
    @Lob
    @Column(name = "observacao")
    private String observacao;
    @JoinColumn(name = "Projeto_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Projeto projetoid;

    public Relatorios() {
    }

    public Relatorios(Integer idRelatorio) {
        this.idRelatorio = idRelatorio;
    }

    public Relatorios(Integer idRelatorio, String tipoRelatorio, Date data, String autor) {
        this.idRelatorio = idRelatorio;
        this.tipoRelatorio = tipoRelatorio;
        this.data = data;
        this.autor = autor;
    }

    public Integer getIdRelatorio() {
        return idRelatorio;
    }

    public void setIdRelatorio(Integer idRelatorio) {
        this.idRelatorio = idRelatorio;
    }

    public String getTipoRelatorio() {
        return tipoRelatorio;
    }

    public void setTipoRelatorio(String tipoRelatorio) {
        this.tipoRelatorio = tipoRelatorio;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Projeto getProjetoid() {
        return projetoid;
    }

    public void setProjetoid(Projeto projetoid) {
        this.projetoid = projetoid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRelatorio != null ? idRelatorio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Relatorios)) {
            return false;
        }
        Relatorios other = (Relatorios) object;
        if ((this.idRelatorio == null && other.idRelatorio != null) || (this.idRelatorio != null && !this.idRelatorio.equals(other.idRelatorio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Relatorios[ idRelatorio=" + idRelatorio + " ]";
    }
    
}
