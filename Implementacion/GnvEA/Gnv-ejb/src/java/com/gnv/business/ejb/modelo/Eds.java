/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.modelo;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "EDS")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Eds.findAll", query = "SELECT e FROM Eds e"),
    @NamedQuery(name = "Eds.findById", query = "SELECT e FROM Eds e WHERE e.id = :id"),
    @NamedQuery(name = "Eds.findByNombre", query = "SELECT e FROM Eds e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "Eds.findByEstatusRow", query = "SELECT e FROM Eds e WHERE e.estatusRow = :estatusRow"),
    @NamedQuery(name = "Eds.findByDireccion", query = "SELECT e FROM Eds e WHERE e.direccion = :direccion"),
    @NamedQuery(name = "Eds.findByCodigoPostal", query = "SELECT e FROM Eds e WHERE e.codigoPostal = :codigoPostal"),
    @NamedQuery(name = "Eds.findByLatitud", query = "SELECT e FROM Eds e WHERE e.latitud = :latitud"),
    @NamedQuery(name = "Eds.findByLongitud", query = "SELECT e FROM Eds e WHERE e.longitud = :longitud")
})
public class Eds implements Serializable {

    
    @OneToMany(mappedBy = "eds")
    private List<CreditoEds> creditoEdsList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "ESTATUS_ROW")
    private Integer estatusRow;
    @Column(name = "DIRECCION")
    private String direccion;
    @Column(name = "CODIGO_POSTAL")
    private String codigoPostal;
    @Column(name = "LATITUD")
    private String latitud;
    @Column(name = "LONGITUD")
    private String longitud;
    @JoinColumn(name = "CIUDAD", referencedColumnName = "ID")
    @ManyToOne
    private Ciudad ciudad;
    @JoinColumn(name = "EMPRESA", referencedColumnName = "ID")
    @ManyToOne
    private Empresa empresa;

    public Eds()
    {
    }

    public Eds(Integer id)
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

    public String getDireccion()
    {
        return direccion;
    }

    public void setDireccion(String direccion)
    {
        this.direccion = direccion;
    }

    public String getCodigoPostal()
    {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal)
    {
        this.codigoPostal = codigoPostal;
    }

    public String getLatitud()
    {
        return latitud;
    }

    public void setLatitud(String latitud)
    {
        this.latitud = latitud;
    }

    public String getLongitud()
    {
        return longitud;
    }

    public void setLongitud(String longitud)
    {
        this.longitud = longitud;
    }

    public Ciudad getCiudad()
    {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad)
    {
        this.ciudad = ciudad;
    }

    public Empresa getEmpresa()
    {
        return empresa;
    }

    public void setEmpresa(Empresa empresa)
    {
        this.empresa = empresa;
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
        if (!(object instanceof Eds))
        {
            return false;
        }
        Eds other = (Eds) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.gnv.business.ejb.modelo.Eds[ id=" + id + " ]";
    }

   

    @XmlTransient
    public List<CreditoEds> getCreditoEdsList()
    {
        return creditoEdsList;
    }

    public void setCreditoEdsList(List<CreditoEds> creditoEdsList)
    {
        this.creditoEdsList = creditoEdsList;
    }
    
}
