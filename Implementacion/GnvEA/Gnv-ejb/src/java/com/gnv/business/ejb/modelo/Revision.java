/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.modelo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
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
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "REVISION")
@XmlRootElement
@NamedQueries(
        {
            @NamedQuery(name = "Revision.findAll", query = "SELECT r FROM Revision r"),
            @NamedQuery(name = "Revision.findById", query = "SELECT r FROM Revision r WHERE r.id = :id"),
            @NamedQuery(name = "Revision.findByFechaRevision", query = "SELECT r FROM Revision r WHERE r.fechaRevision = :fechaRevision"),
            @NamedQuery(name = "Revision.findByFechaProxRevision", query = "SELECT r FROM Revision r WHERE r.fechaProxRevision = :fechaProxRevision")
        })
public class Revision implements Serializable {

    @Column(name = "OBSERVACION")
    private String observacion;
    @Column(name = "ESTATUS")
    private Boolean estatus;
    @Column(name = "ESTATUS_ROW")
    private Integer estatusRow;
    @Transient
    private String fechaRevString;
    @Transient
    private String fechaProxString;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "FECHA_REVISION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRevision;
    @Column(name = "FECHA_PROX_REVISION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaProxRevision;
    @JoinColumn(name = "TALLER", referencedColumnName = "ID")
    @ManyToOne
    private Taller taller;
    @JoinColumn(name = "VEHICULO", referencedColumnName = "ID")
    @ManyToOne
    private Vehiculo vehiculo;

    public Revision() {
    }

    public Revision(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaRevision() {
        return fechaRevision;
    }

    public void setFechaRevision(Date fechaRevision) {
        this.fechaRevision = fechaRevision;
    }

    public Date getFechaProxRevision() {
        return fechaProxRevision;
    }

    public void setFechaProxRevision(Date fechaProxRevision) {
        this.fechaProxRevision = fechaProxRevision;
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
        if (!(object instanceof Revision)) {
            return false;
        }
        Revision other = (Revision) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gnv.business.ejb.modelo.Revision[ id=" + id + " ]";
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Boolean getEstatus() {
        return estatus;
    }

    public void setEstatus(Boolean estatus) {
        this.estatus = estatus;
    }

    public Integer getEstatusRow() {
        return estatusRow;
    }

    public void setEstatusRow(Integer estatusRow) {
        this.estatusRow = estatusRow;
    }

    public String getFechaRevString() {
    if (fechaRevision != null) {
            SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yy");
            String fechaFormato;
            fechaFormato = formateador.format(fechaRevision);
            return fechaFormato;
        }
        return "";
    }

    public void setFechaRevString(String fechaRevString) {
        this.fechaRevString = fechaRevString;
    }

    public String getFechaProxString() {
    if (fechaProxRevision != null) {
            SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yy");
            String fechaFormato;
            fechaFormato = formateador.format(fechaProxRevision);
            return fechaFormato;
        }
        return "";
    }

    public void setFechaProxString(String fechaProxString) {
        this.fechaProxString = fechaProxString;
    }
    
    

}
