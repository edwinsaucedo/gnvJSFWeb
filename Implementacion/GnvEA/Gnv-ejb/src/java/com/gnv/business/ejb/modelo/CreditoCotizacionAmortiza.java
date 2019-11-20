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
 * @author PC2
 */
@Entity
@Table(name = "CREDITO_COTIZACION_AMORTIZA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CreditoCotizacionAmortiza.findAll", query = "SELECT c FROM CreditoCotizacionAmortiza c"),
    @NamedQuery(name = "CreditoCotizacionAmortiza.findById", query = "SELECT c FROM CreditoCotizacionAmortiza c WHERE c.id = :id"),
    @NamedQuery(name = "CreditoCotizacionAmortiza.findByPartida", query = "SELECT c FROM CreditoCotizacionAmortiza c WHERE c.partida = :partida"),
    @NamedQuery(name = "CreditoCotizacionAmortiza.findByFecha", query = "SELECT c FROM CreditoCotizacionAmortiza c WHERE c.fecha = :fecha"),
    @NamedQuery(name = "CreditoCotizacionAmortiza.findByCapital", query = "SELECT c FROM CreditoCotizacionAmortiza c WHERE c.capital = :capital"),
    @NamedQuery(name = "CreditoCotizacionAmortiza.findByIntereses", query = "SELECT c FROM CreditoCotizacionAmortiza c WHERE c.intereses = :intereses"),
    @NamedQuery(name = "CreditoCotizacionAmortiza.findByIvaIntereses", query = "SELECT c FROM CreditoCotizacionAmortiza c WHERE c.ivaIntereses = :ivaIntereses"),
    @NamedQuery(name = "CreditoCotizacionAmortiza.findByPago", query = "SELECT c FROM CreditoCotizacionAmortiza c WHERE c.pago = :pago"),
    @NamedQuery(name = "CreditoCotizacionAmortiza.findByEstatusRow", query = "SELECT c FROM CreditoCotizacionAmortiza c WHERE c.estatusRow = :estatusRow")})
public class CreditoCotizacionAmortiza implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "PARTIDA")
    private int partida;
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Column(name = "CAPITAL")
    private double capital;
    @Column(name = "INTERESES")
    private double intereses;
    @Column(name = "IVA_INTERESES")
    private double ivaIntereses;
    @Column(name = "PAGO")
    private double pago;
    @Column(name = "ESTATUS_ROW")
    private int estatusRow;
    @JoinColumn(name = "CREDITO_COTIZACION", referencedColumnName = "ID")
    @ManyToOne
    private CreditoCotizacion creditoCotizacion;

    public CreditoCotizacionAmortiza() {
    }

    public CreditoCotizacionAmortiza(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getPartida() {
        return partida;
    }

    public void setPartida(int partida) {
        this.partida = partida;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getCapital() {
        return capital;
    }

    public void setCapital(double capital) {
        this.capital = capital;
    }

    public double getIntereses() {
        return intereses;
    }

    public void setIntereses(double intereses) {
        this.intereses = intereses;
    }

    public double getIvaIntereses() {
        return ivaIntereses;
    }

    public void setIvaIntereses(double ivaIntereses) {
        this.ivaIntereses = ivaIntereses;
    }

    public double getPago() {
        return pago;
    }

    public void setPago(double pago) {
        this.pago = pago;
    }

    public int getEstatusRow() {
        return estatusRow;
    }

    public void setEstatusRow(int estatusRow) {
        this.estatusRow = estatusRow;
    }

    public CreditoCotizacion getCreditoCotizacion() {
        return creditoCotizacion;
    }

    public void setCreditoCotizacion(CreditoCotizacion creditoCotizacion) {
        this.creditoCotizacion = creditoCotizacion;
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
        if (!(object instanceof CreditoCotizacionAmortiza)) {
            return false;
        }
        CreditoCotizacionAmortiza other = (CreditoCotizacionAmortiza) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gnv.business.ejb.modelo.CreditoCotizacionAmortiza[ id=" + id + " ]";
    }
    
}
