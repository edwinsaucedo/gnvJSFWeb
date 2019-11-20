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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "VEHICULO_INFO_A")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "VehiculoInfoA.findAll", query = "SELECT v FROM VehiculoInfoA v"),
    @NamedQuery(name = "VehiculoInfoA.findById", query = "SELECT v FROM VehiculoInfoA v WHERE v.id = :id"),
    @NamedQuery(name = "VehiculoInfoA.findByTarjetacirculacion", query = "SELECT v FROM VehiculoInfoA v WHERE v.tarjetaCirculacion = :tarjetaCirculacion"),
    @NamedQuery(name = "VehiculoInfoA.findByFechaexpedicion", query = "SELECT v FROM VehiculoInfoA v WHERE v.fechaExpedicion = :fechaExpedicion"),
    @NamedQuery(name = "VehiculoInfoA.findByOrganismo", query = "SELECT v FROM VehiculoInfoA v WHERE v.organismo = :organismo"),
    @NamedQuery(name = "VehiculoInfoA.findByUsuarioins", query = "SELECT v FROM VehiculoInfoA v WHERE v.usuarioIns = :usuarioIns"),
    @NamedQuery(name = "VehiculoInfoA.findByFechains", query = "SELECT v FROM VehiculoInfoA v WHERE v.fechaIns = :fechaIns"),
    @NamedQuery(name = "VehiculoInfoA.findByEstatusrow", query = "SELECT v FROM VehiculoInfoA v WHERE v.estatusRow = :estatusRow")
})
public class VehiculoInfoA implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional=true) 
    @Column(name = "ORGANISMO")
    private String organismo;
    @Basic(optional=true) 
    @Column(name = "TARJETA_CIRCULACION")
    private String tarjetaCirculacion;
    @Basic(optional=true) 
    @Column(name = "FECHA_EXPEDICION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaExpedicion;
    
    
    @JoinColumn(name = "CIUDAD", referencedColumnName = "ID")
    @ManyToOne
    private Ciudad ciudad;
    @JoinColumn(name = "COMPANIA", referencedColumnName = "ID")
    @ManyToOne
    private Compania compania;
    
    @Column(name = "USUARIO_INS")
    private Integer usuarioIns;
    @Column(name = "FECHA_INS")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIns;
    @Column(name = "ESTATUS_ROW")
    private Integer estatusRow;
    
    @OneToMany(mappedBy = "vehiculoInfoA")
    private List<Vehiculo> vehiculoList;

    public VehiculoInfoA()
    {
    }

    public VehiculoInfoA(Integer id)
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

    public String getOrganismo()
    {
        return organismo;
    }

    public void setOrganismo(String organismo)
    {
        this.organismo = organismo;
    }

    public Ciudad getCiudad()
    {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad)
    {
        this.ciudad = ciudad;
    }

    public Compania getCompania()
    {
        return compania;
    }

    public void setCompania(Compania compania)
    {
        this.compania = compania;
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
        if (!(object instanceof VehiculoInfoA))
        {
            return false;
        }
        VehiculoInfoA other = (VehiculoInfoA) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.gnv.business.ejb.modelo.VehiculoInfoA[ id=" + id + " ]";
    }

    public String getTarjetaCirculacion()
    {
        return tarjetaCirculacion;
    }

    public void setTarjetaCirculacion(String tarjetaCirculacion)
    {
        this.tarjetaCirculacion = tarjetaCirculacion;
    }

    public Date getFechaExpedicion()
    {
        return fechaExpedicion;
    }

    public void setFechaExpedicion(Date fechaExpedicion)
    {
        this.fechaExpedicion = fechaExpedicion;
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

    @XmlTransient
    public List<Vehiculo> getVehiculoList()
    {
        return vehiculoList;
    }

    public void setVehiculoList(List<Vehiculo> vehiculoList)
    {
        this.vehiculoList = vehiculoList;
    }

    
    
}
