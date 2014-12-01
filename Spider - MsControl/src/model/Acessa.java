/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
 * @author Spider
 */
@Entity
@Table(name = "acessa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Acessa.findAll", query = "SELECT a FROM Acessa a"),
    @NamedQuery(name = "Acessa.findByProjetoid", query = "SELECT a FROM Acessa a WHERE a.acessaPK.projetoid = :projetoid"),
    @NamedQuery(name = "Acessa.findByPerfilid", query = "SELECT a FROM Acessa a WHERE a.acessaPK.perfilid = :perfilid"),
    @NamedQuery(name = "Acessa.findByUsuarioid", query = "SELECT a FROM Acessa a WHERE a.acessaPK.usuarioid = :usuarioid"),
    @NamedQuery(name = "Acessa.findByDataDeInicio", query = "SELECT a FROM Acessa a WHERE a.dataDeInicio = :dataDeInicio")})
public class Acessa implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AcessaPK acessaPK;
    @Column(name = "dataDeInicio")
    @Temporal(TemporalType.DATE)
    private Date dataDeInicio;
    @JoinColumn(name = "Perfil_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Perfil perfil;
    @JoinColumn(name = "Projeto_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Projeto projeto;
    @JoinColumn(name = "Usuario_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario;

    public Acessa() {
    }

    public Acessa(AcessaPK acessaPK) {
        this.acessaPK = acessaPK;
    }

    public Acessa(int projetoid, int perfilid, int usuarioid) {
        this.acessaPK = new AcessaPK(projetoid, perfilid, usuarioid);
    }

    public AcessaPK getAcessaPK() {
        return acessaPK;
    }

    public void setAcessaPK(AcessaPK acessaPK) {
        this.acessaPK = acessaPK;
    }

    public Date getDataDeInicio() {
        return dataDeInicio;
    }

    public void setDataDeInicio(Date dataDeInicio) {
        this.dataDeInicio = dataDeInicio;
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
