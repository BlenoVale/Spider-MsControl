/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
 * @author Spider
 */
@Entity
@Table(name = "medida")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Medida.findAll", query = "SELECT m FROM Medida m"),
    @NamedQuery(name = "Medida.findById", query = "SELECT m FROM Medida m WHERE m.medidaPK.id = :id"),
    @NamedQuery(name = "Medida.findByNome", query = "SELECT m FROM Medida m WHERE m.nome = :nome"),
    @NamedQuery(name = "Medida.findByData", query = "SELECT m FROM Medida m WHERE m.data = :data"),
    @NamedQuery(name = "Medida.findByProjetoid", query = "SELECT m FROM Medida m WHERE m.medidaPK.projetoid = :projetoid")})
public class Medida implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MedidaPK medidaPK;
    @Column(name = "nome")
    private String nome;
    @Column(name = "data")
    @Temporal(TemporalType.DATE)
    private Date data;
    @JoinColumn(name = "Projeto_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Projeto projeto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "medida")
    private List<Definicao> definicaoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "medida")
    private List<Coleta> coletaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "medida")
    private List<Analise> analiseList;

    public Medida() {
    }

    public Medida(MedidaPK medidaPK) {
        this.medidaPK = medidaPK;
    }

    public Medida(int id, int projetoid) {
        this.medidaPK = new MedidaPK(id, projetoid);
    }

    public MedidaPK getMedidaPK() {
        return medidaPK;
    }

    public void setMedidaPK(MedidaPK medidaPK) {
        this.medidaPK = medidaPK;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    @XmlTransient
    public List<Definicao> getDefinicaoList() {
        return definicaoList;
    }

    public void setDefinicaoList(List<Definicao> definicaoList) {
        this.definicaoList = definicaoList;
    }

    @XmlTransient
    public List<Coleta> getColetaList() {
        return coletaList;
    }

    public void setColetaList(List<Coleta> coletaList) {
        this.coletaList = coletaList;
    }

    @XmlTransient
    public List<Analise> getAnaliseList() {
        return analiseList;
    }

    public void setAnaliseList(List<Analise> analiseList) {
        this.analiseList = analiseList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (medidaPK != null ? medidaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Medida)) {
            return false;
        }
        Medida other = (Medida) object;
        if ((this.medidaPK == null && other.medidaPK != null) || (this.medidaPK != null && !this.medidaPK.equals(other.medidaPK)))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "model.Medida[ medidaPK=" + medidaPK + " ]";
    }
    
}
