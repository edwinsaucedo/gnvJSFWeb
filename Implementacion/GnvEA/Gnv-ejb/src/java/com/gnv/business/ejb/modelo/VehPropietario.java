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
@Table(name = "VEH_PROPIETARIO")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "VehPropietario.findAll", query = "SELECT v FROM VehPropietario v"),
    @NamedQuery(name = "VehPropietario.findById", query = "SELECT v FROM VehPropietario v WHERE v.id = :id"),
    @NamedQuery(name = "VehPropietario.findByUsuarioIns", query = "SELECT v FROM VehPropietario v WHERE v.usuarioIns = :usuarioIns"),
    @NamedQuery(name = "VehPropietario.findByFechains", query = "SELECT v FROM VehPropietario v WHERE v.fechaIns = :fechaIns"),
    @NamedQuery(name = "VehPropietario.findByEstatusRow", query = "SELECT v FROM VehPropietario v WHERE v.estatusRow = :estatusRow")
})
public class VehPropietario implements Serializable {

    @Column(name = "FECHA_INS")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIns;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "USUARIO_INS")
    private Integer usuarioIns;
    @Column(name = "ESTATUS_ROW")
    private Integer estatusRow;
    @JoinColumn(name = "PROPIETARIO", referencedColumnName = "ID")
    @ManyToOne
    private Propietario propietario;
    @JoinColumn(name = "VEHICULO", referencedColumnName = "ID")
    @ManyToOne
    private Vehiculo vehiculo;

    public VehPropietario()
    {
    }

    public VehPropietario(Integer id)
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
    public Integer getEstatusRow()
    {
        return estatusRow;
    }

    public void setEstatusRow(Integer estatusRow)
    {
        this.estatusRow = estatusRow;
    }

    public Propietario getPropietario()
    {
        return propietario;
    }

    public void setPropietario(Propietario propietario)
    {
        this.propietario = propietario;
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
        if (!(object instanceof VehPropietario))
        {
            return false;
        }
        VehPropietario other = (VehPropietario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.gnv.business.ejb.modelo.VehPropietario[ id=" + id + " ]";
    }

    public Date getFechaIns()
    {
        return fechaIns;
    }

    public void setFechaIns(Date fechaIns)
    {
        this.fechaIns = fechaIns;
    }
    
}
