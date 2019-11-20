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
@Table(name = "FLOTA_VEHICULO")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "FlotaVehiculo.findAll", query = "SELECT f FROM FlotaVehiculo f"),
    @NamedQuery(name = "FlotaVehiculo.findById", query = "SELECT f FROM FlotaVehiculo f WHERE f.id = :id"),
    @NamedQuery(name = "FlotaVehiculo.findByUsuarioIns", query = "SELECT f FROM FlotaVehiculo f WHERE f.usuarioIns = :usuarioIns"),
    @NamedQuery(name = "FlotaVehiculo.findByFechaIns", query = "SELECT f FROM FlotaVehiculo f WHERE f.fechaIns = :fechaIns"),
    @NamedQuery(name = "FlotaVehiculo.findByEstatusRow", query = "SELECT f FROM FlotaVehiculo f WHERE f.estatusRow = :estatusRow"),
    @NamedQuery(name = "FlotaVehiculo.findByCnciasidn", query = "SELECT f FROM FlotaVehiculo f WHERE f.cnciasidn = :cnciasidn")
})
public class FlotaVehiculo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "USUARIO_INS")
    private Integer usuarioIns;
    @Column(name = "FECHA_INS")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIns;
    @Column(name = "ESTATUS_ROW")
    private Integer estatusRow;
    @Column(name = "CNCIASIDN")
    private Long cnciasidn;
    @JoinColumn(name = "FLOTA", referencedColumnName = "ID")
    @ManyToOne
    private Flota flota;
    @JoinColumn(name = "VEHICULO", referencedColumnName = "ID")
    @ManyToOne
    private Vehiculo vehiculo;

    public FlotaVehiculo()
    {
    }

    public FlotaVehiculo(Integer id)
    {
        this.id = id;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getUsuarioIns()
    {
        return usuarioIns;
    }

    public void setUsuarioIns(Integer usuarioIns)
    {
        this.usuarioIns = usuarioIns;
    }

    public Date getFechaIns()
    {
        return fechaIns;
    }

    public void setFechaIns(Date fechaIns)
    {
        this.fechaIns = fechaIns;
    }

    public Integer getEstatusRow()
    {
        return estatusRow;
    }

    public void setEstatusRow(Integer estatusRow)
    {
        this.estatusRow = estatusRow;
    }

    public Long getCnciasidn()
    {
        return cnciasidn;
    }

    public void setCnciasidn(Long cnciasidn)
    {
        this.cnciasidn = cnciasidn;
    }

    public Flota getFlota()
    {
        return flota;
    }

    public void setFlota(Flota flota)
    {
        this.flota = flota;
    }

    public Vehiculo getVehiculo()
    {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo)
    {
        this.vehiculo = vehiculo;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FlotaVehiculo))
        {
            return false;
        }
        FlotaVehiculo other = (FlotaVehiculo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.gnv.business.ejb.modelo.FlotaVehiculo[ id=" + id + " ]";
    }
    
}
