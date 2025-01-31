/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.modelo;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "VEH_TIPO")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "VehTipo.findAll", query = "SELECT v FROM VehTipo v"),
    @NamedQuery(name = "VehTipo.findById", query = "SELECT v FROM VehTipo v WHERE v.id = :id"),
    @NamedQuery(name = "VehTipo.findByNombre", query = "SELECT v FROM VehTipo v WHERE v.nombre = :nombre"),
    @NamedQuery(name = "VehTipo.findByEstatusRow", query = "SELECT v FROM VehTipo v WHERE v.estatusRow = :estatusRow")
})
public class VehTipo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "ESTATUS_ROW")
    private Integer estatusRow;
    @OneToMany(mappedBy = "tipo")
    private List<Vehiculo> vehiculoList;

    public VehTipo()
    {
    }

    public VehTipo(Integer id)
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
        if (!(object instanceof VehTipo))
        {
            return false;
        }
        VehTipo other = (VehTipo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.gnv.business.ejb.modelo.VehTipo[ id=" + id + " ]";
    }
    
}
