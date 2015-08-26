/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Bleno Vale
 */
@Entity
@Table(name = "resultados")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Resultados.findAll", query = "SELECT r FROM Resultados r"),
    @NamedQuery(name = "Resultados.findById", query = "SELECT r FROM Resultados r WHERE r.id = :id"),
    @NamedQuery(name = "Resultados.findByTitulo", query = "SELECT r FROM Resultados r WHERE r.titulo = :titulo"),
    @NamedQuery(name = "Resultados.findByData", query = "SELECT r FROM Resultados r WHERE r.data = :data"),
    @NamedQuery(name = "Resultados.findByNomeUsuario", query = "SELECT r FROM Resultados r WHERE r.nomeUsuario = :nomeUsuario")})
public class Resultados implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "titulo")
    private String titulo;
    @Basic(optional = false)
    @Column(name = "data")
    @Temporal(TemporalType.DATE)
    private Date data;
    @Basic(optional = false)
    @Column(name = "nomeUsuario")
    private String nomeUsuario;
    @Basic(optional = false)
    @Lob
    @Column(name = "interpretacao")
    private String interpretacao;
    @Basic(optional = false)
    @Lob
    @Column(name = "tomadaDeDecisao")
    private String tomadaDeDecisao;
    @ManyToMany(mappedBy = "resultadosList")
    private List<Analise> analiseList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "resultadosid")
    private List<ParticipanteseInteressados> participanteseInteressadosList;

    public Resultados() {
    }

    public Resultados(Integer id) {
        this.id = id;
    }

    public Resultados(Integer id, String titulo, Date data, String nomeUsuario, String interpretacao, String tomadaDeDecisao) {
        this.id = id;
        this.titulo = titulo;
        this.data = data;
        this.nomeUsuario = nomeUsuario;
        this.interpretacao = interpretacao;
        this.tomadaDeDecisao = tomadaDeDecisao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getInterpretacao() {
        return interpretacao;
    }

    public void setInterpretacao(String interpretacao) {
        this.interpretacao = interpretacao;
    }

    public String getTomadaDeDecisao() {
        return tomadaDeDecisao;
    }

    public void setTomadaDeDecisao(String tomadaDeDecisao) {
        this.tomadaDeDecisao = tomadaDeDecisao;
    }

    @XmlTransient
    public List<Analise> getAnaliseList() {
        return analiseList;
    }

    public void setAnaliseList(List<Analise> analiseList) {
        this.analiseList = analiseList;
    }

    @XmlTransient
    public List<ParticipanteseInteressados> getParticipanteseInteressadosList() {
        return participanteseInteressadosList;
    }

    public void setParticipanteseInteressadosList(List<ParticipanteseInteressados> participanteseInteressadosList) {
        this.participanteseInteressadosList = participanteseInteressadosList;
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
        if (!(object instanceof Resultados)) {
            return false;
        }
        Resultados other = (Resultados) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Resultados[ id=" + id + " ]";
    }
    
}
