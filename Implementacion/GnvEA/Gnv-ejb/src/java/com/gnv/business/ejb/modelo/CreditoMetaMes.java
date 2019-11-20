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
@Table(name = "CREDITO_META_MES")
@XmlRootElement
@NamedQueries(
        {
            @NamedQuery(name = "CreditoMetaMes.findAll", query = "SELECT c FROM CreditoMetaMes c"),
            @NamedQuery(name = "CreditoMetaMes.findById", query = "SELECT c FROM CreditoMetaMes c WHERE c.id = :id"),
            @NamedQuery(name = "CreditoMetaMes.findByMes", query = "SELECT c FROM CreditoMetaMes c WHERE c.mes = :mes"),
            @NamedQuery(name = "CreditoMetaMes.findByValor", query = "SELECT c FROM CreditoMetaMes c WHERE c.valor = :valor"),
            @NamedQuery(name = "CreditoMetaMes.findByEstatusRow", query = "SELECT c FROM CreditoMetaMes c WHERE c.estatusRow = :estatusRow"),
            @NamedQuery(name = "CreditoMetaMes.findByUsuariosIns", query = "SELECT c FROM CreditoMetaMes c WHERE c.usuariosIns = :usuariosIns"),
            @NamedQuery(name = "CreditoMetaMes.findByFechaIns", query = "SELECT c FROM CreditoMetaMes c WHERE c.fechaIns = :fechaIns")
        })
public class CreditoMetaMes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "MES")
    private Integer mes;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "VALOR")
    private BigDecimal valor;
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

    public CreditoMetaMes() {
    }

    public CreditoMetaMes(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
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
        if (!(object instanceof CreditoMetaMes)) {
            return false;
        }
        CreditoMetaMes other = (CreditoMetaMes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gnv.business.ejb.modelo.CreditoMetaMes[ id=" + id + " ]";
    }

}
