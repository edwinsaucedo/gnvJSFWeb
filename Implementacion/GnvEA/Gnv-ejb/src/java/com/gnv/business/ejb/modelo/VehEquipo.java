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
@Table(name = "VEH_EQUIPO")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "VehEquipo.findAll", query = "SELECT v FROM VehEquipo v"),
    @NamedQuery(name = "VehEquipo.findById", query = "SELECT v FROM VehEquipo v WHERE v.id = :id"),
    @NamedQuery(name = "VehEquipo.findByTipo", query = "SELECT v FROM VehEquipo v WHERE v.tipo = :tipo"),
    @NamedQuery(name = "VehEquipo.findByIdRegistro", query = "SELECT v FROM VehEquipo v WHERE v.idRegistro = :idRegistro"),
    @NamedQuery(name = "VehEquipo.findByAccion", query = "SELECT v FROM VehEquipo v WHERE v.accion = :accion"),
    @NamedQuery(name = "VehEquipo.findByFechaAct", query = "SELECT v FROM VehEquipo v WHERE v.fechaAct = :fechaAct")
})
public class VehEquipo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "TIPO")
    private Boolean tipo;
    @Column(name = "ID_REGISTRO")
    private Integer idRegistro;
    @Column(name = "ACCION")
    private String accion;
    @Column(name = "FECHA_ACT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAct;
    @JoinColumn(name = "VEHICULO", referencedColumnName = "ID")
    @ManyToOne
    private Vehiculo vehiculo;

    public VehEquipo()
    {
    }

    public VehEquipo(Integer id)
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

    public Boolean getTipo()
    {
        return tipo;
    }

    public void setTipo(Boolean tipo)
    {
        this.tipo = tipo;
    }

    public Integer getIdRegistro()
    {
        return idRegistro;
    }

    public void setIdRegistro(Integer idRegistro)
    {
        this.idRegistro = idRegistro;
    }

    public String getAccion()
    {
        return accion;
    }

    public void setAccion(String accion)
    {
        this.accion = accion;
    }

    public Date getFechaAct()
    {
        return fechaAct;
    }

    public void setFechaAct(Date fechaAct)
    {
        this.fechaAct = fechaAct;
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
        if (!(object instanceof VehEquipo))
        {
            return false;
        }
        VehEquipo other = (VehEquipo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.gnv.business.ejb.modelo.VehEquipo[ id=" + id + " ]";
    }
    
}
