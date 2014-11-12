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
@Table(name = "acessa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Acessa.findAll", query = "SELECT a FROM Acessa a"),
    @NamedQuery(name = "Acessa.findByIdPerfil", query = "SELECT a FROM Acessa a WHERE a.acessaPK.idPerfil = :idPerfil"),
    @NamedQuery(name = "Acessa.findByIdUsuario", query = "SELECT a FROM Acessa a WHERE a.acessaPK.idUsuario = :idUsuario"),
    @NamedQuery(name = "Acessa.findByIdProjeto", query = "SELECT a FROM Acessa a WHERE a.acessaPK.idProjeto = :idProjeto")})
public class Acessa implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AcessaPK acessaPK;
    @JoinColumn(name = "idPerfil", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Perfil perfil;
    @JoinColumn(name = "idProjeto", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Projeto projeto;
    @JoinColumn(name = "idUsuario", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario;

    public Acessa() {
    }

    public Acessa(AcessaPK acessaPK) {
        this.acessaPK = acessaPK;
    }

    public Acessa(int idPerfil, int idUsuario, int idProjeto) {
        this.acessaPK = new AcessaPK(idPerfil, idUsuario, idProjeto);
    }

    public AcessaPK getAcessaPK() {
        return acessaPK;
    }

    public void setAcessaPK(AcessaPK acessaPK) {
        this.acessaPK = acessaPK;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (acessaPK != null ? acessaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Acessa)) {
            return false;
        }
        Acessa other = (Acessa) object;
        if ((this.acessaPK == null && other.acessaPK != null) || (this.acessaPK != null && !this.acessaPK.equals(other.acessaPK)))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "model.Acessa[ acessaPK=" + acessaPK + " ]";
    }
    
}
