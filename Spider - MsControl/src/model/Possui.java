/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "possui")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Possui.findAll", query = "SELECT p FROM Possui p"),
    @NamedQuery(name = "Possui.findById", query = "SELECT p FROM Possui p WHERE p.possuiPK.id = :id"),
    @NamedQuery(name = "Possui.findByIdFuncionalidade", query = "SELECT p FROM Possui p WHERE p.possuiPK.idFuncionalidade = :idFuncionalidade"),
    @NamedQuery(name = "Possui.findByIdPerfil", query = "SELECT p FROM Possui p WHERE p.possuiPK.idPerfil = :idPerfil")})
public class Possui implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PossuiPK possuiPK;
    @JoinColumn(name = "idFuncionalidade", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Funcionalidade funcionalidade;
    @JoinColumn(name = "idPerfil", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Perfil perfil;

    public Possui() {
    }

    public Possui(PossuiPK possuiPK) {
        this.possuiPK = possuiPK;
    }

    public Possui(int id, int idFuncionalidade, int idPerfil) {
        this.possuiPK = new PossuiPK(id, idFuncionalidade, idPerfil);
    }

    public PossuiPK getPossuiPK() {
        return possuiPK;
    }

    public void setPossuiPK(PossuiPK possuiPK) {
        this.possuiPK = possuiPK;
    }

    public Funcionalidade getFuncionalidade() {
        return funcionalidade;
    }

    public void setFuncionalidade(Funcionalidade funcionalidade) {
        this.funcionalidade = funcionalidade;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (possuiPK != null ? possuiPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Possui)) {
            return false;
        }
        Possui other = (Possui) object;
        if ((this.possuiPK == null && other.possuiPK != null) || (this.possuiPK != null && !this.possuiPK.equals(other.possuiPK)))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "model.Possui[ possuiPK=" + possuiPK + " ]";
    }
    
}
