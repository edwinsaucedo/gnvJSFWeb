/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.modelo;

import java.io.Serializable;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author PC2
 */
@Entity
@Table(name = "CREDITO_COTIZACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CreditoCotizacion.findAll", query = "SELECT c FROM CreditoCotizacion c"),
    @NamedQuery(name = "CreditoCotizacion.findById", query = "SELECT c FROM CreditoCotizacion c WHERE c.id = :id"),
    @NamedQuery(name = "CreditoCotizacion.findByFecha", query = "SELECT c FROM CreditoCotizacion c WHERE c.fecha = :fecha"),
    @NamedQuery(name = "CreditoCotizacion.findByNombre", query = "SELECT c FROM CreditoCotizacion c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CreditoCotizacion.findByMonto", query = "SELECT c FROM CreditoCotizacion c WHERE c.monto = :monto"),
    @NamedQuery(name = "CreditoCotizacion.findByAnticipo", query = "SELECT c FROM CreditoCotizacion c WHERE c.anticipo = :anticipo"),
    @NamedQuery(name = "CreditoCotizacion.findByMontoAFinanciar", query = "SELECT c FROM CreditoCotizacion c WHERE c.montoAFinanciar = :montoAFinanciar"),
    @NamedQuery(name = "CreditoCotizacion.findByPlazoCalculo", query = "SELECT c FROM CreditoCotizacion c WHERE c.plazoCalculo = :plazoCalculo"),
    @NamedQuery(name = "CreditoCotizacion.findByFrecuenciaTipoPlazo", query = "SELECT c FROM CreditoCotizacion c WHERE c.frecuenciaTipoPlazo = :frecuenciaTipoPlazo"),
    @NamedQuery(name = "CreditoCotizacion.findByTasaOCuota", query = "SELECT c FROM CreditoCotizacion c WHERE c.tasaOCuota = :tasaOCuota"),
    @NamedQuery(name = "CreditoCotizacion.findByFrecuenciaPago", query = "SELECT c FROM CreditoCotizacion c WHERE c.frecuenciaPago = :frecuenciaPago"),
    @NamedQuery(name = "CreditoCotizacion.findByMontoInteres", query = "SELECT c FROM CreditoCotizacion c WHERE c.montoInteres = :montoInteres"),
    @NamedQuery(name = "CreditoCotizacion.findByMontoAPagar", query = "SELECT c FROM CreditoCotizacion c WHERE c.montoAPagar = :montoAPagar"),
    @NamedQuery(name = "CreditoCotizacion.findByEstatusRow", query = "SELECT c FROM CreditoCotizacion c WHERE c.estatusRow = :estatusRow"),
    @NamedQuery(name = "CreditoCotizacion.findByUsuariosIns", query = "SELECT c FROM CreditoCotizacion c WHERE c.usuariosIns = :usuariosIns"),
    @NamedQuery(name = "CreditoCotizacion.findByFechaIns", query = "SELECT c FROM CreditoCotizacion c WHERE c.fechaIns = :fechaIns")})
public class CreditoCotizacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Column(name = "NOMBRE")
    private String nombre;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "MONTO")
    private double monto;
    @Column(name = "ANTICIPO")
    private double anticipo;
    @Column(name = "MONTO_A_FINANCIAR")
    private double montoAFinanciar;
    @Column(name = "PLAZO_CALCULO")
    private int plazoCalculo;
    @Column(name = "FRECUENCIA_TIPO_PLAZO")
    private Character frecuenciaTipoPlazo;
    @Column(name = "TASA_O_CUOTA")
    private double tasaOCuota;
    @Column(name = "FRECUENCIA_PAGO")
    private Character frecuenciaPago;
    @Column(name = "MONTO_INTERES")
    private double montoInteres;
    @Column(name = "MONTO_A_PAGAR")
    private double montoAPagar;
    @Column(name = "ESTATUS_ROW")
    private int estatusRow;
    @Column(name = "USUARIOS_INS")
    private Integer usuariosIns;
    @Column(name = "FECHA_INS")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIns;
    @JoinColumn(name = "FINANCIERA", referencedColumnName = "ID")
    @ManyToOne
    private Financiera financiera;
    @OneToMany(mappedBy = "creditoCotizacion")
    private List<CreditoCotizacionAmortiza> creditoCotizacionAmortizaList;
    @Transient
    private String dateString;

    public CreditoCotizacion() {
    }

    public CreditoCotizacion(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public double getAnticipo() {
        return anticipo;
    }

    public void setAnticipo(double anticipo) {
        this.anticipo = anticipo;
    }

    public double getMontoAFinanciar() {
        return montoAFinanciar;
    }

    public void setMontoAFinanciar(double montoAFinanciar) {
        this.montoAFinanciar = montoAFinanciar;
    }

    public int getPlazoCalculo() {
        return plazoCalculo;
    }

    public void setPlazoCalculo(int plazoCalculo) {
        this.plazoCalculo = plazoCalculo;
    }

    public Character getFrecuenciaTipoPlazo() {
        return frecuenciaTipoPlazo;
    }

    public void setFrecuenciaTipoPlazo(Character frecuenciaTipoPlazo) {
        this.frecuenciaTipoPlazo = frecuenciaTipoPlazo;
    }

    public double getTasaOCuota() {
        return tasaOCuota;
    }

    public void setTasaOCuota(double tasaOCuota) {
        this.tasaOCuota = tasaOCuota;
    }

    public Character getFrecuenciaPago() {
        return frecuenciaPago;
    }

    public void setFrecuenciaPago(Character frecuenciaPago) {
        this.frecuenciaPago = frecuenciaPago;
    }

    public double getMontoInteres() {
        return montoInteres;
    }

    public void setMontoInteres(double montoInteres) {
        this.montoInteres = montoInteres;
    }

    public double getMontoAPagar() {
        return montoAPagar;
    }

    public void setMontoAPagar(double montoAPagar) {
        this.montoAPagar = montoAPagar;
    }

    public int getEstatusRow() {
        return estatusRow;
    }

    public void setEstatusRow(int estatusRow) {
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

    public Financiera getFinanciera() {
        return financiera;
    }

    public void setFinanciera(Financiera financiera) {
        this.financiera = financiera;
    }

    @XmlTransient
    public List<CreditoCotizacionAmortiza> getCreditoCotizacionAmortizaList() {
        return creditoCotizacionAmortizaList;
    }

    public void setCreditoCotizacionAmortizaList(List<CreditoCotizacionAmortiza> creditoCotizacionAmortizaList) {
        this.creditoCotizacionAmortizaList = creditoCotizacionAmortizaList;
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
        if (!(object instanceof CreditoCotizacion)) {
            return false;
        }
        CreditoCotizacion other = (CreditoCotizacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
        public String getDateString() {
        if (fecha != null) {
            SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yy HH:mm");
            String fechaFormato;
            fechaFormato = formateador.format(fechaIns);
            return fechaFormato;
        }
        return "";
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    @Override
    public String toString() {
        return "com.gnv.business.ejb.modelo.CreditoCotizacion[ id=" + id + " ]";
    }
    
}
