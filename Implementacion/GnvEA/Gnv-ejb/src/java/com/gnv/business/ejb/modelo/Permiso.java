/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.modelo;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "PERMISO")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Permiso.findAll", query = "SELECT p FROM Permiso p"),
    @NamedQuery(name = "Permiso.findById", query = "SELECT p FROM Permiso p WHERE p.id = :id"),
    @NamedQuery(name = "Permiso.findByEtiqueta", query = "SELECT p FROM Permiso p WHERE p.etiqueta = :etiqueta"),
    @NamedQuery(name = "Permiso.findByPermiso", query = "SELECT p FROM Permiso p WHERE p.permiso = :permiso"),
    @NamedQuery(name = "Permiso.findByOrden", query = "SELECT p FROM Permiso p WHERE p.orden = :orden"),
    @NamedQuery(name = "Permiso.findByDescripcion", query = "SELECT p FROM Permiso p WHERE p.descripcion = :descripcion")
})
public class Permiso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "ETIQUETA")
    private String etiqueta;
    @Basic(optional = false)
    @Column(name = "PERMISO")
    private String permiso;
    @Basic(optional = false)
    @Column(name = "ORDEN")
    private Integer orden;
    @Basic(optional = false)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @JoinColumn(name = "GRUPO_PERMISO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private GrupoPermiso grupoPermiso;

    public Permiso()
    {
    }

    public Permiso(Integer id)
    {
        this.id = id;
    }

    public Permiso(Integer id, String etiqueta, String permiso, int orden, String descripcion)
    {
        this.id = id;
        this.etiqueta = etiqueta;
        this.permiso = permiso;
        this.orden = orden;
        this.descripcion = descripcion;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getEtiqueta()
    {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta)
    {
        this.etiqueta = etiqueta;
    }

    public String getPermiso()
    {
        return permiso;
    }

    public void setPermiso(String permiso)
    {
        this.permiso = permiso;
    }

    public Integer getOrden()
    {
        return orden;
    }

    public void setOrden(Integer orden)
    {
        this.orden = orden;
    }

    public String getDescripcion()
    {
        return descripcion;
    }

    public void setDescripcion(String descripcion)
    {
        this.descripcion = descripcion;
    }

    public GrupoPermiso getGrupoPermiso()
    {
        return grupoPermiso;
    }

    public void setGrupoPermiso(GrupoPermiso grupoPermiso)
    {
        this.grupoPermiso = grupoPermiso;
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
        if (!(object instanceof Permiso))
        {
            return false;
        }
        Permiso other = (Permiso) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.gnv.business.ejb.modelo.Permiso[ id=" + id + " ]";
    }
    
}
