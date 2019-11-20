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
@Table(name = "LINEA")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Linea.findAll", query = "SELECT l FROM Linea l"),
    @NamedQuery(name = "Linea.findById", query = "SELECT l FROM Linea l WHERE l.id = :id"),
    @NamedQuery(name = "Linea.findByNombre", query = "SELECT l FROM Linea l WHERE l.nombre = :nombre"),
    @NamedQuery(name = "Linea.findByDescripcion", query = "SELECT l FROM Linea l WHERE l.descripcion = :descripcion")
})
public class Linea implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @OneToMany(mappedBy = "linea")
    private List<Vehiculo> vehiculoList;
    @JoinColumn(name = "MARCA", referencedColumnName = "ID")
    @ManyToOne
    private Marca marca;
    @OneToMany(mappedBy = "linea")
    private List<Kit> kitList;
    @OneToMany(mappedBy = "linea")
    private List<Cilindro> cilindroList;

    public Linea()
    {
    }

    public Linea(Integer id)
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

    @XmlTransient
    public List<Vehiculo> getVehiculoList()
    {
        return vehiculoList;
    }

    public void setVehiculoList(List<Vehiculo> vehiculoList)
    {
        this.vehiculoList = vehiculoList;
    }

    public Marca getMarca()
    {
        return marca;
    }

    public void setMarca(Marca marca)
    {
        this.marca = marca;
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
        if (!(object instanceof Linea))
        {
            return false;
        }
        Linea other = (Linea) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.gnv.business.ejb.modelo.Linea[ id=" + id + " ]";
    }
    
}
