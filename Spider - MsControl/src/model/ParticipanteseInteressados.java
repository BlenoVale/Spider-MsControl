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
 * @author Bleno Vale
 */
@Entity
@Table(name = "participantese_interessados")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ParticipanteseInteressados.findAll", query = "SELECT p FROM ParticipanteseInteressados p"),
    @NamedQuery(name = "ParticipanteseInteressados.findById", query = "SELECT p FROM ParticipanteseInteressados p WHERE p.id = :id"),
    @NamedQuery(name = "ParticipanteseInteressados.findByTipo", query = "SELECT p FROM ParticipanteseInteressados p WHERE p.tipo = :tipo"),
    @NamedQuery(name = "ParticipanteseInteressados.findByParticipanteEInteressado", query = "SELECT p FROM ParticipanteseInteressados p WHERE p.participanteEInteressado = :participanteEInteressado")})
public class ParticipanteseInteressados implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "tipo")
    private String tipo;
    @Basic(optional = false)
    @Column(name = "ParticipanteEInteressado")
    private String participanteEInteressado;
    @JoinColumn(name = "Resultados_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Resultados resultadosid;

    public ParticipanteseInteressados() {
    }

    public ParticipanteseInteressados(Integer id) {
        this.id = id;
    }

    public ParticipanteseInteressados(Integer id, String tipo, String participanteEInteressado) {
        this.id = id;
        this.tipo = tipo;
        this.participanteEInteressado = participanteEInteressado;
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

    public String getParticipanteEInteressado() {
        return participanteEInteressado;
    }

    public void setParticipanteEInteressado(String participanteEInteressado) {
        this.participanteEInteressado = participanteEInteressado;
    }

    public Resultados getResultadosid() {
        return resultadosid;
    }

    public void setResultadosid(Resultados resultadosid) {
        this.resultadosid = resultadosid;
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
        if (!(object instanceof ParticipanteseInteressados)) {
            return false;
        }
        ParticipanteseInteressados other = (ParticipanteseInteressados) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.ParticipanteseInteressados[ id=" + id + " ]";
    }
    
}
