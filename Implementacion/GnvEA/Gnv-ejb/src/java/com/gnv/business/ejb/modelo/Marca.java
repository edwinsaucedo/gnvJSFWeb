/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
@Table(name = "MARCA")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Marca.findAll", query = "SELECT m FROM Marca m"),
    @NamedQuery(name = "Marca.findById", query = "SELECT m FROM Marca m WHERE m.id = :id"),
    @NamedQuery(name = "Marca.findByNombre", query = "SELECT m FROM Marca m WHERE m.nombre = :nombre"),
    @NamedQuery(name = "Marca.findByDescripcion", query = "SELECT m FROM Marca m WHERE m.descripcion = :descripcion"),
    @NamedQuery(name = "Marca.findByUsuarioIns", query = "SELECT m FROM Marca m WHERE m.usuarioIns = :usuarioIns"),
    @NamedQuery(name = "Marca.findByFechaIns", query = "SELECT m FROM Marca m WHERE m.fechaIns = :fechaIns"),
    @NamedQuery(name = "Marca.findByEstatusRow", query = "SELECT m FROM Marca m WHERE m.estatusRow = :estatusRow")
})
public class Marca implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "USUARIO_INS")
    private Integer usuarioIns;
    @Column(name = "FECHA_INS")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIns;
    @Column(name = "ESTATUS_ROW")
    private Integer estatusRow;
    @JoinColumn(name = "TIPO_MARCA", referencedColumnName = "ID")
    @ManyToOne
    private TipoMarca tipoMarca;
    @OneToMany(mappedBy = "marca")
    private List<Vehiculo> vehiculoList;
    @OneToMany(mappedBy = "marca")
    private List<Linea> lineaList;
    @OneToMany(mappedBy = "marca")
    private List<Kit> kitList;
    @OneToMany(mappedBy = "marca")
    private List<Cilindro> cilindroList;

    public Marca()
    {
    }

    public Marca(Integer id)
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

    public String getDescripcion()
    {
        return descripcion;
    }

    public void setDescripcion(String descripcion)
    {
        this.descripcion = descripcion;
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

    public TipoMarca getTipoMarca()
    {
        return tipoMarca;
    }

    public void setTipoMarca(TipoMarca tipoMarca)
    {
        this.tipoMarca = tipoMarca;
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

    @XmlTransient
    public List<Linea> getLineaList()
    {
        return lineaList;
    }

    public void setLineaList(List<Linea> lineaList)
    {
        this.lineaList = lineaList;
    }

    @XmlTransient
    public List<Kit> getKitList()
    {
        return kitList;
    }

    public void setKitList(List<Kit> kitList)
    {
        this.kitList = kitList;
    }

    @XmlTransient
    public List<Cilindro> getCilindroList()
    {
        return cilindroList;
    }

    public void setCilindroList(List<Cilindro> cilindroList)
    {
        this.cilindroList = cilindroList;
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
        if (!(object instanceof Marca))
        {
            return false;
        }
        Marca other = (Marca) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.gnv.business.ejb.modelo.Marca[ id=" + id + " ]";
    }
    
}
