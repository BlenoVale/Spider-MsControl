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
 * @author Paulo
 */
@Entity
@Table(name = "meiosprocedimentoanalise")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Meiosprocedimentoanalise.findAll", query = "SELECT m FROM Meiosprocedimentoanalise m"),
    @NamedQuery(name = "Meiosprocedimentoanalise.findById", query = "SELECT m FROM Meiosprocedimentoanalise m WHERE m.id = :id"),
    @NamedQuery(name = "Meiosprocedimentoanalise.findByIdmeioComunicacao", query = "SELECT m FROM Meiosprocedimentoanalise m WHERE m.idmeioComunicacao = :idmeioComunicacao")})
public class Meiosprocedimentoanalise implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "id_meioComunicacao")
    private int idmeioComunicacao;
    @JoinColumn(name = "ProcedimentoDeAnalise_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Procedimentodeanalise procedimentoDeAnaliseid;

    public Meiosprocedimentoanalise()
    {
    }

    public Meiosprocedimentoanalise(Integer id)
    {
        this.id = id;
    }

    public Meiosprocedimentoanalise(Integer id, int idmeioComunicacao)
    {
        this.id = id;
        this.idmeioComunicacao = idmeioComunicacao;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public int getIdmeioComunicacao()
    {
        return idmeioComunicacao;
    }

    public void setIdmeioComunicacao(int idmeioComunicacao)
    {
        this.idmeioComunicacao = idmeioComunicacao;
    }

    public Procedimentodeanalise getProcedimentoDeAnaliseid()
    {
        return procedimentoDeAnaliseid;
    }

    public void setProcedimentoDeAnaliseid(Procedimentodeanalise procedimentoDeAnaliseid)
    {
        this.procedimentoDeAnaliseid = procedimentoDeAnaliseid;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Meiosprocedimentoanalise)) {
            return false;
        }
        Meiosprocedimentoanalise other = (Meiosprocedimentoanalise) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "model.Meiosprocedimentoanalise[ id=" + id + " ]";
    }

}
