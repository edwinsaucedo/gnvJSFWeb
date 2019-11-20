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
@Table(name = "TIPO_MARCA")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "TipoMarca.findAll", query = "SELECT t FROM TipoMarca t"),
    @NamedQuery(name = "TipoMarca.findById", query = "SELECT t FROM TipoMarca t WHERE t.id = :id"),
    @NamedQuery(name = "TipoMarca.findByNombre", query = "SELECT t FROM TipoMarca t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TipoMarca.findByDescripcion", query = "SELECT t FROM TipoMarca t WHERE t.descripcion = :descripcion")
})
public class TipoMarca implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @OneToMany(mappedBy = "tipoMarca")
    private List<Marca> marcaList;

    public TipoMarca()
    {
    }

    public TipoMarca(Integer id)
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
    public List<Marca> getMarcaList()
    {
        return marcaList;
    }

    public void setMarcaList(List<Marca> marcaList)
    {
        this.marcaList = marcaList;
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
        if (!(object instanceof TipoMarca))
        {
            return false;
        }
        TipoMarca other = (TipoMarca) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.gnv.business.ejb.modelo.TipoMarca[ id=" + id + " ]";
    }
    
}
