/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.modelo;

import java.io.Serializable;
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
@Table(name = "CREDITO_EDS")
@XmlRootElement
@NamedQueries(
        {
            @NamedQuery(name = "CreditoEds.findAll", query = "SELECT c FROM CreditoEds c"),
            @NamedQuery(name = "CreditoEds.findById", query = "SELECT c FROM CreditoEds c WHERE c.id = :id"),
            @NamedQuery(name = "CreditoEds.findByEstatusRow", query = "SELECT c FROM CreditoEds c WHERE c.estatusRow = :estatusRow"),
            @NamedQuery(name = "CreditoEds.findByUsuarioIns", query = "SELECT c FROM CreditoEds c WHERE c.usuarioIns = :usuarioIns"),
            @NamedQuery(name = "CreditoEds.findByFechaIns", query = "SELECT c FROM CreditoEds c WHERE c.fechaIns = :fechaIns")
        })
public class CreditoEds implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "ESTATUS_ROW")
    private Integer estatusRow;
    @Column(name = "USUARIO_INS")
    private Integer usuarioIns;
    @Column(name = "FECHA_INS")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIns;
    @JoinColumn(name = "CREDITO", referencedColumnName = "ID")
    @ManyToOne
    private Credito credito;
    @JoinColumn(name = "EDS", referencedColumnName = "ID")
    @ManyToOne
    private Eds eds;

    public CreditoEds() {
    }

    public CreditoEds(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getEstatusRow() {
        return estatusRow;
    }

    public void setEstatusRow(Integer estatusRow) {
        this.estatusRow = estatusRow;
    }

    public Integer getUsuarioIns() {
        return usuarioIns;
    }

    public void setUsuarioIns(Integer usuarioIns) {
        this.usuarioIns = usuarioIns;
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

    public Eds getEds() {
        return eds;
    }

    public void setEds(Eds eds) {
        this.eds = eds;
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
        if (!(object instanceof CreditoEds)) {
            return false;
        }
        CreditoEds other = (CreditoEds) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gnv.business.ejb.modelo.CreditoEds[ id=" + id + " ]";
    }

}
