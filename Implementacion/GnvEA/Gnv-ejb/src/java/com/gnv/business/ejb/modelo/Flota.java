/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "FLOTA")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Flota.findAll", query = "SELECT f FROM Flota f"),
    @NamedQuery(name = "Flota.findById", query = "SELECT f FROM Flota f WHERE f.id = :id"),
    @NamedQuery(name = "Flota.findByNombre", query = "SELECT f FROM Flota f WHERE f.nombre = :nombre"),
    @NamedQuery(name = "Flota.findByUsuarioIns", query = "SELECT f FROM Flota f WHERE f.usuarioIns = :usuarioIns"),
    @NamedQuery(name = "Flota.findByFechaIns", query = "SELECT f FROM Flota f WHERE f.fechaIns = :fechaIns"),
    @NamedQuery(name = "Flota.findByEstatusRow", query = "SELECT f FROM Flota f WHERE f.estatusRow = :estatusRow"),
    @NamedQuery(name = "Flota.findByCncdiridn", query = "SELECT f FROM Flota f WHERE f.cncdiridn = :cncdiridn"),
    @NamedQuery(name = "Flota.findByCnciasidn", query = "SELECT f FROM Flota f WHERE f.cnciasidn = :cnciasidn")
})
public class Flota implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "USUARIO_INS")
    private Integer usuarioIns;
    @Column(name = "FECHA_INS")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIns;
    @Column(name = "ESTATUS_ROW")
    private Integer estatusRow;
    @Column(name = "CNCDIRIDN")
    private Long cncdiridn;
    @Column(name = "CNCIASIDN")
    private Long cnciasidn;
    @OneToMany(mappedBy = "flota")
    private List<FlotaVehiculo> flotaVehiculoList;

    public Flota()
    {
    }

    public Flota(Integer id)
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

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
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

    public Long getCncdiridn()
    {
        return cncdiridn;
    }

    public void setCncdiridn(Long cncdiridn)
    {
        this.cncdiridn = cncdiridn;
    }

    public Long getCnciasidn()
    {
        return cnciasidn;
    }

    public void setCnciasidn(Long cnciasidn)
    {
        this.cnciasidn = cnciasidn;
    }

    @XmlTransient
    public List<FlotaVehiculo> getFlotaVehiculoList()
    {
        return flotaVehiculoList;
    }

    public void setFlotaVehiculoList(List<FlotaVehiculo> flotaVehiculoList)
    {
        this.flotaVehiculoList = flotaVehiculoList;
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
        if (!(object instanceof Flota))
        {
            return false;
        }
        Flota other = (Flota) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.gnv.business.ejb.modelo.Flota[ id=" + id + " ]";
    }
    
}
