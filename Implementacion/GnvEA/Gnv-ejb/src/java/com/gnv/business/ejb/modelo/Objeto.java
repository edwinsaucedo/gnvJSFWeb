/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
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
@Table(name = "OBJETO")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Objeto.findAll", query = "SELECT o FROM Objeto o"),
    @NamedQuery(name = "Objeto.findById", query = "SELECT o FROM Objeto o WHERE o.id = :id"),
    @NamedQuery(name = "Objeto.findByNombre", query = "SELECT o FROM Objeto o WHERE o.nombre = :nombre"),
    @NamedQuery(name = "Objeto.findByDescripcion", query = "SELECT o FROM Objeto o WHERE o.descripcion = :descripcion")
})
public class Objeto implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "objeto")
    private List<BitacoraActividades> bitacoraActividadesList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @OneToMany(mappedBy = "objeto")
    private List<Documento> documentoList;
    @OneToMany(mappedBy = "objeto")
    private List<Estatus> estatusList;

    public Objeto()
    {
    }

    public Objeto(Integer id)
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
    public List<Documento> getDocumentoList()
    {
        return documentoList;
    }

    public void setDocumentoList(List<Documento> documentoList)
    {
        this.documentoList = documentoList;
    }

    @XmlTransient
    public List<Estatus> getEstatusList()
    {
        return estatusList;
    }

    public void setEstatusList(List<Estatus> estatusList)
    {
        this.estatusList = estatusList;
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
        if (!(object instanceof Objeto))
        {
            return false;
        }
        Objeto other = (Objeto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.gnv.business.ejb.modelo.Objeto[ id=" + id + " ]";
    }

    @XmlTransient
    public List<BitacoraActividades> getBitacoraActividadesList()
    {
        return bitacoraActividadesList;
    }

    public void setBitacoraActividadesList(List<BitacoraActividades> bitacoraActividadesList)
    {
        this.bitacoraActividadesList = bitacoraActividadesList;
    }
    
}
