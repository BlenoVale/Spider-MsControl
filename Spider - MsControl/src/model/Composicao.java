/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Dan
 */
@Entity
@Table(name = "composicao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Composicao.findAll", query = "SELECT c FROM Composicao c"),
    @NamedQuery(name = "Composicao.findById", query = "SELECT c FROM Composicao c WHERE c.id = :id"),
    @NamedQuery(name = "Composicao.findByTipo", query = "SELECT c FROM Composicao c WHERE c.tipo = :tipo")})
public class Composicao implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "tipo")
    private String tipo;
    @JoinColumn(name = "idDefinicao", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Definicao idDefinicao;

    public Composicao() {
    }

    public Composicao(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Definicao getIdDefinicao() {
        return idDefinicao;
    }

    public void setIdDefinicao(Definicao idDefinicao) {
        this.idDefinicao = idDefinicao;
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
        if (!(object instanceof Composicao)) {
            return false;
        }
        Composicao other = (Composicao) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "model.Composicao[ id=" + id + " ]";
    }
    
}
