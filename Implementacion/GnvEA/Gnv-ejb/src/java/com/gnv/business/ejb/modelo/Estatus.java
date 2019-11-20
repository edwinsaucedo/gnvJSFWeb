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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "ESTATUS")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Estatus.findAll", query = "SELECT e FROM Estatus e"),
    @NamedQuery(name = "Estatus.findById", query = "SELECT e FROM Estatus e WHERE e.id = :id"),
    @NamedQuery(name = "Estatus.findByNombre", query = "SELECT e FROM Estatus e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "Estatus.findByNombreClase", query = "SELECT e FROM Estatus e WHERE e.nombreClase = :nombreClase")
})
public class Estatus implements Serializable {

    @OneToMany(mappedBy = "estatus")
    private List<Fabricante> fabricanteList;
    @OneToMany(mappedBy = "estatus")
    private List<Vehiculo> vehiculoList;
    @OneToMany(mappedBy = "estatus")
    private List<Conversion> conversionList;
    @OneToMany(mappedBy = "estatus")
    private List<Kit> kitList;
    @OneToMany(mappedBy = "estatus")
    private List<Cilindro> cilindroList;
    @OneToMany(mappedBy = "estatus")
    private List<Usuario> usuarioList;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "NOMBRE_CLASE")
    private String nombreClase;
    @JoinColumn(name = "OBJETO", referencedColumnName = "ID")
    @ManyToOne
    private Objeto objeto;

    public Estatus()
    {
    }

    public Estatus(Integer id)
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

    public String getNombreClase()
    {
        return nombreClase;
    }

    public void setNombreClase(String nombreClase)
    {
        this.nombreClase = nombreClase;
    }

    public Objeto getObjeto()
    {
        return objeto;
    }

    public void setObjeto(Objeto objeto)
    {
        this.objeto = objeto;
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
        if (!(object instanceof Estatus))
        {
            return false;
        }
        Estatus other = (Estatus) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.gnv.business.ejb.modelo.Estatus[ id=" + id + " ]";
    }

   

    @XmlTransient
    public List<Usuario> getUsuarioList()
    {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList)
    {
        this.usuarioList = usuarioList;
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
    public List<Conversion> getConversionList()
    {
        return conversionList;
    }

    public void setConversionList(List<Conversion> conversionList)
    {
        this.conversionList = conversionList;
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

    @XmlTransient
    public List<Fabricante> getFabricanteList()
    {
        return fabricanteList;
    }

    public void setFabricanteList(List<Fabricante> fabricanteList)
    {
        this.fabricanteList = fabricanteList;
    }
    
}
