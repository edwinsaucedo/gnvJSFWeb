/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
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
 * @author Administrador
 */
@Entity 
@Table(name = "VALORACION")
@XmlRootElement
@NamedQueries(
        {
            @NamedQuery(name = "Valoracion.findAll", query = "SELECT v FROM Valoracion v"),
            @NamedQuery(name = "Valoracion.findById", query = "SELECT v FROM Valoracion v WHERE v.id = :id"),
            @NamedQuery(name = "Valoracion.findByFecha", query = "SELECT v FROM Valoracion v WHERE v.fecha = :fecha"),
            @NamedQuery(name = "Valoracion.findByPresupuesto", query = "SELECT v FROM Valoracion v WHERE v.presupuesto = :presupuesto"),
            @NamedQuery(name = "Valoracion.findByObservacion", query = "SELECT v FROM Valoracion v WHERE v.observacion = :observacion"),
            @NamedQuery(name = "Valoracion.findBySolicitarCredito", query = "SELECT v FROM Valoracion v WHERE v.solicitarCredito = :solicitarCredito"),
            @NamedQuery(name = "Valoracion.findByEstatusRow", query = "SELECT v FROM Valoracion v WHERE v.estatusRow = :estatusRow"),
            @NamedQuery(name = "Valoracion.findByEstado", query = "SELECT v FROM Valoracion v WHERE v.estado = :estado")
        })
public class Valoracion implements Serializable {

    @Column(name = "ESTATUS_ROW")
    private Integer estatusRow;
    @Column(name = "USUARIO_INS")
    private Integer usuarioIns;
    @Column(name = "FECHA_INS")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIns;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PRESUPUESTO")
    private BigDecimal presupuesto;
    @Column(name = "OBSERVACION")
    private String observacion;
    @Column(name = "SOLICITAR_CREDITO")
    private Boolean solicitarCredito;
    @Column(name = "ESTADO")
    private Boolean estado;
    @OneToMany(mappedBy = "valoracion") 
    private List<Conversion> conversionList;
    @JoinColumn(name = "TALLER", referencedColumnName = "ID")
    @ManyToOne
    private Taller taller;
    @JoinColumn(name = "VEHICULO", referencedColumnName = "ID")
    @ManyToOne
    private Vehiculo vehiculo;
    @Transient
    private String fechaString;
    @Transient
    private String fechaInsString;
    @Transient
    private String estadoValoracionString;

    public Valoracion() {
    }

    public Valoracion(Integer id) {
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

    public String getFechaString() {
        if (fecha != null) {
            SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yy");
            String fechaFormato;
            fechaFormato = formateador.format(fecha);
            return fechaFormato;
        }
        return "";
    }

    public String getFechaInsString() {
       if (fechaIns != null) {
            SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yy");
            String fechaFormato;
            fechaFormato = formateador.format(fechaIns);
            return fechaFormato;
        }
        return "";
    }

    public void setFechaInsString(String fechaInsString) {
        this.fechaInsString = fechaInsString;
    }

    public void setFechaString(String fechaString) {
        this.fechaString = fechaString;
    }

    public BigDecimal getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(BigDecimal presupuesto) {
        this.presupuesto = presupuesto;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Boolean getSolicitarCredito() {
        return solicitarCredito;
    }

    public void setSolicitarCredito(Boolean solicitarCredito) {
        this.solicitarCredito = solicitarCredito;
    }

    public Integer getEstatusRow() {
        return estatusRow;
    }

    public void setEstatusRow(Integer estatusRow) {
        this.estatusRow = estatusRow;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    @XmlTransient
    public List<Conversion> getConversionList() {
        return conversionList;
    }

    public void setConversionList(List<Conversion> conversionList) {
        this.conversionList = conversionList;
    }

    public Taller getTaller() {
        return taller;
    }

    public void setTaller(Taller taller) {
        this.taller = taller;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
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
        if (!(object instanceof Valoracion)) {
            return false;
        }
        Valoracion other = (Valoracion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gnv.business.ejb.modelo.Valoracion[ id=" + id + " ]";
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

    public String getEstadoValoracionString() {
        if (estado != null) {
            if (estado) {
                estadoValoracionString = "Apto para conversión";
            } else {
                estadoValoracionString = "No apto para conversión";
            }
        }
        return estadoValoracionString;
    }

    public void setEstadoValoracionString(String estadoValoracionString) {
        this.estadoValoracionString = estadoValoracionString;
    }

}
