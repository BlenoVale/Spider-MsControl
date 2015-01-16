/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Dan
 */
@Entity
@Table(name = "registroobjetivoquestao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Registroobjetivoquestao.findAll", query = "SELECT r FROM Registroobjetivoquestao r"),
    @NamedQuery(name = "Registroobjetivoquestao.findById", query = "SELECT r FROM Registroobjetivoquestao r WHERE r.registroobjetivoquestaoPK.id = :id"),
    @NamedQuery(name = "Registroobjetivoquestao.findByObjetivoDeQuestaoid", query = "SELECT r FROM Registroobjetivoquestao r WHERE r.registroobjetivoquestaoPK.objetivoDeQuestaoid = :objetivoDeQuestaoid"),
    @NamedQuery(name = "Registroobjetivoquestao.findByObjetivoDeQuestaoObjetivoDeMedicaoid", query = "SELECT r FROM Registroobjetivoquestao r WHERE r.registroobjetivoquestaoPK.objetivoDeQuestaoObjetivoDeMedicaoid = :objetivoDeQuestaoObjetivoDeMedicaoid"),
    @NamedQuery(name = "Registroobjetivoquestao.findByObjetivoDeQuestaoObjetivoDeMedicaoProjetoid", query = "SELECT r FROM Registroobjetivoquestao r WHERE r.registroobjetivoquestaoPK.objetivoDeQuestaoObjetivoDeMedicaoProjetoid = :objetivoDeQuestaoObjetivoDeMedicaoProjetoid"),
    @NamedQuery(name = "Registroobjetivoquestao.findByTipo", query = "SELECT r FROM Registroobjetivoquestao r WHERE r.tipo = :tipo"),
    @NamedQuery(name = "Registroobjetivoquestao.findByNomeUsuario", query = "SELECT r FROM Registroobjetivoquestao r WHERE r.nomeUsuario = :nomeUsuario"),
    @NamedQuery(name = "Registroobjetivoquestao.findByData", query = "SELECT r FROM Registroobjetivoquestao r WHERE r.data = :data")})
public class Registroobjetivoquestao implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RegistroobjetivoquestaoPK registroobjetivoquestaoPK;
    @Basic(optional = false)
    @Column(name = "tipo")
    private int tipo;
    @Basic(optional = false)
    @Column(name = "nomeUsuario")
    private String nomeUsuario;
    @Lob
    @Column(name = "descricao")
    private String descricao;
    @Basic(optional = false)
    @Column(name = "data")
    @Temporal(TemporalType.DATE)
    private Date data;
    @JoinColumns({
        @JoinColumn(name = "ObjetivoDeQuestao_id", referencedColumnName = "id", insertable = false, updatable = false),
        @JoinColumn(name = "ObjetivoDeQuestao_ObjetivoDeMedicao_id", referencedColumnName = "ObjetivoDeMedicao_id", insertable = false, updatable = false),
        @JoinColumn(name = "ObjetivoDeQuestao_ObjetivoDeMedicao_Projeto_id", referencedColumnName = "ObjetivoDeMedicao_Projeto_id", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Objetivodequestao objetivodequestao;

    public Registroobjetivoquestao() {
    }

    public Registroobjetivoquestao(RegistroobjetivoquestaoPK registroobjetivoquestaoPK) {
        this.registroobjetivoquestaoPK = registroobjetivoquestaoPK;
    }

    public Registroobjetivoquestao(RegistroobjetivoquestaoPK registroobjetivoquestaoPK, int tipo, String nomeUsuario, Date data) {
        this.registroobjetivoquestaoPK = registroobjetivoquestaoPK;
        this.tipo = tipo;
        this.nomeUsuario = nomeUsuario;
        this.data = data;
    }

    public Registroobjetivoquestao(int id, int objetivoDeQuestaoid, int objetivoDeQuestaoObjetivoDeMedicaoid, int objetivoDeQuestaoObjetivoDeMedicaoProjetoid) {
        this.registroobjetivoquestaoPK = new RegistroobjetivoquestaoPK(id, objetivoDeQuestaoid, objetivoDeQuestaoObjetivoDeMedicaoid, objetivoDeQuestaoObjetivoDeMedicaoProjetoid);
    }

    public RegistroobjetivoquestaoPK getRegistroobjetivoquestaoPK() {
        return registroobjetivoquestaoPK;
    }

    public void setRegistroobjetivoquestaoPK(RegistroobjetivoquestaoPK registroobjetivoquestaoPK) {
        this.registroobjetivoquestaoPK = registroobjetivoquestaoPK;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Objetivodequestao getObjetivodequestao() {
        return objetivodequestao;
    }

    public void setObjetivodequestao(Objetivodequestao objetivodequestao) {
        this.objetivodequestao = objetivodequestao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (registroobjetivoquestaoPK != null ? registroobjetivoquestaoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Registroobjetivoquestao)) {
            return false;
        }
        Registroobjetivoquestao other = (Registroobjetivoquestao) object;
        if ((this.registroobjetivoquestaoPK == null && other.registroobjetivoquestaoPK != null) || (this.registroobjetivoquestaoPK != null && !this.registroobjetivoquestaoPK.equals(other.registroobjetivoquestaoPK)))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "model.Registroobjetivoquestao[ registroobjetivoquestaoPK=" + registroobjetivoquestaoPK + " ]";
    }
    
}
