/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "CREDITO_METAS_GEN")
@XmlRootElement
@NamedQueries(
        {
            @NamedQuery(name = "CreditoMetasGen.findAll", query = "SELECT c FROM CreditoMetasGen c"),
            @NamedQuery(name = "CreditoMetasGen.findById", query = "SELECT c FROM CreditoMetasGen c WHERE c.id = :id"),
            @NamedQuery(name = "CreditoMetasGen.findByTipoMeta", query = "SELECT c FROM CreditoMetasGen c WHERE c.tipoMeta = :tipoMeta"),
            @NamedQuery(name = "CreditoMetasGen.findByTipoPagos", query = "SELECT c FROM CreditoMetasGen c WHERE c.tipoPagos = :tipoPagos"),
            @NamedQuery(name = "CreditoMetasGen.findByValorMeta", query = "SELECT c FROM CreditoMetasGen c WHERE c.valorMeta = :valorMeta"),
            @NamedQuery(name = "CreditoMetasGen.findByDiasAviso", query = "SELECT c FROM CreditoMetasGen c WHERE c.diasAviso = :diasAviso"),
            @NamedQuery(name = "CreditoMetasGen.findByBloqueo", query = "SELECT c FROM CreditoMetasGen c WHERE c.bloqueo = :bloqueo"),
            @NamedQuery(name = "CreditoMetasGen.findByProporcionPrimerMes", query = "SELECT c FROM CreditoMetasGen c WHERE c.proporcionPrimerMes = :proporcionPrimerMes"),
            @NamedQuery(name = "CreditoMetasGen.findByEstatusRow", query = "SELECT c FROM CreditoMetasGen c WHERE c.estatusRow = :estatusRow"),
            @NamedQuery(name = "CreditoMetasGen.findByUsuariosIns", query = "SELECT c FROM CreditoMetasGen c WHERE c.usuariosIns = :usuariosIns"),
            @NamedQuery(name = "CreditoMetasGen.findByFechaIns", query = "SELECT c FROM CreditoMetasGen c WHERE c.fechaIns = :fechaIns")
        })
public class CreditoMetasGen implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "TIPO_META")
    private Integer tipoMeta;
    @Column(name = "TIPO_PAGOS")
    private Integer tipoPagos;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "VALOR_META")
    private BigDecimal valorMeta;
    @Column(name = "DIAS_AVISO")
    private Integer diasAviso;
    @Column(name = "BLOQUEO")
    private Boolean bloqueo;
    @Column(name = "PROPORCION_PRIMER_MES")
    private Boolean proporcionPrimerMes;
    @Column(name = "ESTATUS_ROW")
    private Integer estatusRow;
    @Column(name = "USUARIOS_INS")
    private Integer usuariosIns;
    @Column(name = "FECHA_INS")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIns;
    @JoinColumn(name = "CREDITO", referencedColumnName = "ID")
    @ManyToOne
    private Credito credito;

    public CreditoMetasGen() {
    }

    public CreditoMetasGen(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTipoMeta() {
        return tipoMeta;
    }

    public void setTipoMeta(Integer tipoMeta) {
        this.tipoMeta = tipoMeta;
    }

    public Integer getTipoPagos() {
        return tipoPagos;
    }

    public void setTipoPagos(Integer tipoPagos) {
        this.tipoPagos = tipoPagos;
    }

    public BigDecimal getValorMeta() {
        return valorMeta;
    }

    public void setValorMeta(BigDecimal valorMeta) {
        this.valorMeta = valorMeta;
    }

    public Integer getDiasAviso() {
        return diasAviso;
    }

    public void setDiasAviso(Integer diasAviso) {
        this.diasAviso = diasAviso;
    }

    public Boolean getBloqueo() {
        return bloqueo;
    }

    public void setBloqueo(Boolean bloqueo) {
        this.bloqueo = bloqueo;
    }

    public Boolean getProporcionPrimerMes() {
        return proporcionPrimerMes;
    }

    public void setProporcionPrimerMes(Boolean proporcionPrimerMes) {
        this.proporcionPrimerMes = proporcionPrimerMes;
    }

    public Integer getEstatusRow() {
        return estatusRow;
    }

    public void setEstatusRow(Integer estatusRow) {
        this.estatusRow = estatusRow;
    }

    public Integer getUsuariosIns() {
        return usuariosIns;
    }

    public void setUsuariosIns(Integer usuariosIns) {
        this.usuariosIns = usuariosIns;
    }

    public Date getFechaIns() {
        return fechaIns;
    }

    public void setFechaIns(Date fechaIns) {
        this.fechaIns = fechaIns;
    }

    public Credito getCredito() {
        return credito;
    }

    public void setCredito(Credito credito) {
        this.credito = credito;
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
        if (!(object instanceof CreditoMetasGen)) {
            return false;
        }
        CreditoMetasGen other = (CreditoMetasGen) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gnv.business.ejb.modelo.CreditoMetasGen[ id=" + id + " ]";
    }

}
