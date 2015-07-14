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
 * @author paulosouza
 */
@Entity
@Table(name = "perfisinteressadosprocedimentoanalise")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Perfisinteressadosprocedimentoanalise.findAll", query = "SELECT p FROM Perfisinteressadosprocedimentoanalise p"),
    @NamedQuery(name = "Perfisinteressadosprocedimentoanalise.findById", query = "SELECT p FROM Perfisinteressadosprocedimentoanalise p WHERE p.id = :id"),
    @NamedQuery(name = "Perfisinteressadosprocedimentoanalise.findByIdperfilInteressado", query = "SELECT p FROM Perfisinteressadosprocedimentoanalise p WHERE p.idperfilInteressado = :idperfilInteressado")})
public class Perfisinteressadosprocedimentoanalise implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "id_perfilInteressado")
    private int idperfilInteressado;
    @JoinColumn(name = "ProcedimentoDeAnalise_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Procedimentodeanalise procedimentoDeAnaliseid;

    public Perfisinteressadosprocedimentoanalise() {
    }

    public Perfisinteressadosprocedimentoanalise(Integer id) {
        this.id = id;
    }

    public Perfisinteressadosprocedimentoanalise(Integer id, int idperfilInteressado) {
        this.id = id;
        this.idperfilInteressado = idperfilInteressado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdperfilInteressado() {
        return idperfilInteressado;
    }

    public void setIdperfilInteressado(int idperfilInteressado) {
        this.idperfilInteressado = idperfilInteressado;
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
        if (!(object instanceof Perfisinteressadosprocedimentoanalise)) {
            return false;
        }
        Perfisinteressadosprocedimentoanalise other = (Perfisinteressadosprocedimentoanalise) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "model.Perfisinteressadosprocedimentoanalise[ id=" + id + " ]";
    }

}
