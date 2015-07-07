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
@Table(name = "perfilinteressado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Perfilinteressado.findAll", query = "SELECT p FROM Perfilinteressado p"),
    @NamedQuery(name = "Perfilinteressado.findById", query = "SELECT p FROM Perfilinteressado p WHERE p.id = :id"),
    @NamedQuery(name = "Perfilinteressado.findByNome", query = "SELECT p FROM Perfilinteressado p WHERE p.nome = :nome")})
public class Perfilinteressado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    @JoinColumn(name = "Projeto_id", referencedColumnName = "id")
    @ManyToOne
    private Projeto projetoid;

    public Perfilinteressado()
    {
    }

    public Perfilinteressado(Integer id)
    {
        this.id = id;
    }

    public Perfilinteressado(Integer id, String nome)
    {
        this.id = id;
        this.nome = nome;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public Projeto getProjetoid()
    {
        return projetoid;
    }

    public void setProjetoid(Projeto projetoid)
    {
        this.projetoid = projetoid;
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
        if (!(object instanceof Perfilinteressado)) {
            return false;
        }
        Perfilinteressado other = (Perfilinteressado) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "model.Perfilinteressado[ id=" + id + " ]";
    }

}
