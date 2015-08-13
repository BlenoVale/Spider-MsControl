/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Bleno Vale
 */
@Entity
@Table(name = "analise")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Analise.findAll", query = "SELECT a FROM Analise a"),
    @NamedQuery(name = "Analise.findById", query = "SELECT a FROM Analise a WHERE a.id = :id")})
public class Analise implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Lob
    @Column(name = "criterioDeAnalise")
    private String criterioDeAnalise;
    @Lob
    @Column(name = "observacao")
    private String observacao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "analiseid")
    private List<Resultados> resultadosList;
    @JoinColumn(name = "Indicador_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Indicador indicadorid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "analiseid")
    private List<Registroanalise> registroanaliseList;

    public Analise() {
    }

    public Analise(Integer id) {
        this.id = id;
    }

    public Analise(Integer id, String criterioDeAnalise) {
        this.id = id;
        this.criterioDeAnalise = criterioDeAnalise;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCriterioDeAnalise() {
        return criterioDeAnalise;
    }

    public void setCriterioDeAnalise(String criterioDeAnalise) {
        this.criterioDeAnalise = criterioDeAnalise;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    @XmlTransient
    public List<Resultados> getResultadosList() {
        return resultadosList;
    }

    public void setResultadosList(List<Resultados> resultadosList) {
        this.resultadosList = resultadosList;
    }

    public Indicador getIndicadorid() {
        return indicadorid;
    }

    public void setIndicadorid(Indicador indicadorid) {
        this.indicadorid = indicadorid;
    }

    @XmlTransient
    public List<Registroanalise> getRegistroanaliseList() {
        return registroanaliseList;
    }

    public void setRegistroanaliseList(List<Registroanalise> registroanaliseList) {
        this.registroanaliseList = registroanaliseList;
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
        if (!(object instanceof Analise)) {
            return false;
        }
        Analise other = (Analise) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Analise[ id=" + id + " ]";
    }
    
}
