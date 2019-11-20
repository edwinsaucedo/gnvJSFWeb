/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "GRUPO_PERMISO")
@XmlRootElement
@NamedQueries(
        {
            @NamedQuery(name = "GrupoPermiso.findAll", query = "SELECT g FROM GrupoPermiso g"),
            @NamedQuery(name = "GrupoPermiso.findById", query = "SELECT g FROM GrupoPermiso g WHERE g.id = :id"),
            @NamedQuery(name = "GrupoPermiso.findByNombre", query = "SELECT g FROM GrupoPermiso g WHERE g.nombre = :nombre"),
            @NamedQuery(name = "GrupoPermiso.findByOrden", query = "SELECT g FROM GrupoPermiso g WHERE g.orden = :orden"),
            @NamedQuery(name = "GrupoPermiso.findByExpandido", query = "SELECT g FROM GrupoPermiso g WHERE g.expandido = :expandido"),
            @NamedQuery(name = "GrupoPermiso.findByDescripcion", query = "SELECT g FROM GrupoPermiso g WHERE g.descripcion = :descripcion")
        })
public class GrupoPermiso implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "grupoPermiso")
    private List<Permiso> permisoList;

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "ORDEN")
    private Integer orden;
    @Basic(optional = false)
    @Column(name = "EXPANDIDO")
    private boolean expandido;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    
    @OneToMany(mappedBy = "grupoPadre")
    private List<GrupoPermiso> grupoPermisoList;
    
    @JoinColumn(name = "GRUPO_PADRE", referencedColumnName = "ID")
    @ManyToOne
    private GrupoPermiso grupoPadre;
    
    @Transient
    private String nombreFull;

    public String getNombreFull()
    {
        nombreFull = nombre;

        if (this.grupoPadre != null)
        {
            nombreFull = grupoPadre.getNombreFull() + "->" + this.nombreFull;
        }

        return nombreFull;
    }

    public void setNombreFull(String nombreFull)
    {
        this.nombreFull = nombreFull;
    }

    public GrupoPermiso()
    {
    }

    public GrupoPermiso(Integer id)
    {
        this.id = id;
    }

    public GrupoPermiso(Integer id, String nombre, int orden, boolean expandido)
    {
        this.id = id;
        this.nombre = nombre;
        this.orden = orden;
        this.expandido = expandido;
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

    public Integer getOrden()
    {
        return orden;
    }

    public void setOrden(Integer orden)
    {
        this.orden = orden;
    }

    public boolean getExpandido()
    {
        return expandido;
    }

    public void setExpandido(boolean expandido)
    {
        this.expandido = expandido;
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
    public List<GrupoPermiso> getGrupoPermisoList()
    {
        return grupoPermisoList;
    }

    public void setGrupoPermisoList(List<GrupoPermiso> grupoPermisoList)
    {
        this.grupoPermisoList = grupoPermisoList;
    }

    public GrupoPermiso getGrupoPadre()
    {
        return grupoPadre;
    }

    public void setGrupoPadre(GrupoPermiso grupoPadre)
    {
        this.grupoPadre = grupoPadre;
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
        if (!(object instanceof GrupoPermiso))
        {
            return false;
        }
        GrupoPermiso other = (GrupoPermiso) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.gnv.business.ejb.modelo.GrupoPermiso[ id=" + id + " ]";
    }

    @XmlTransient
    public List<Permiso> getPermisoList()
    {
        return permisoList;
    }

    public void setPermisoList(List<Permiso> permisoList)
    {
        this.permisoList = permisoList;
    }

}
