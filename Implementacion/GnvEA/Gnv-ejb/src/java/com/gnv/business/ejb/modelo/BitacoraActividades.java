/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.modelo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "BITACORA_ACTIVIDADES")
@XmlRootElement
@NamedQueries(
        {
            @NamedQuery(name = "BitacoraActividades.findAll", query = "SELECT b FROM BitacoraActividades b"),
            @NamedQuery(name = "BitacoraActividades.findById", query = "SELECT b FROM BitacoraActividades b WHERE b.id = :id"),
            @NamedQuery(name = "BitacoraActividades.findByUsuario", query = "SELECT b FROM BitacoraActividades b WHERE b.usuario = :usuario"),
            @NamedQuery(name = "BitacoraActividades.findByObjeto", query = "SELECT b FROM BitacoraActividades b WHERE b.objeto = :objeto"),
            @NamedQuery(name = "BitacoraActividades.findByRegistro", query = "SELECT b FROM BitacoraActividades b WHERE b.registro = :registro"),
            @NamedQuery(name = "BitacoraActividades.findByAccion", query = "SELECT b FROM BitacoraActividades b WHERE b.accion = :accion"),
            @NamedQuery(name = "BitacoraActividades.findByFechaActividad", query = "SELECT b FROM BitacoraActividades b WHERE b.fechaActividad = :fechaActividad")
        })
public class BitacoraActividades implements Serializable {

    @JoinColumn(name = "OBJETO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Objeto objeto;
    @JoinColumn(name = "USUARIO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Usuario usuario;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "REGISTRO")
    private int registro;
    @Basic(optional = false)
    @Column(name = "ACCION")
    private String accion;
    @Column(name = "FECHA_ACTIVIDAD")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActividad;
    @Transient
    private String fechaActividadF;

    public BitacoraActividades()
    {
    }

    public BitacoraActividades(Integer id)
    {
        this.id = id;
    }

    public BitacoraActividades(Integer id, Usuario usuario, Objeto objeto, int registro, String accion)
    {
        this.id = id;
        this.usuario = usuario;
        this.objeto = objeto;
        this.registro = registro;
        this.accion = accion;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public int getRegistro()
    {
        return registro;
    }

    public void setRegistro(int registro)
    {
        this.registro = registro;
    }

    public String getAccion()
    {
        return accion;
    }

    public void setAccion(String accion)
    {
        this.accion = accion;
    }

    public Date getFechaActividad()
    {
        return fechaActividad;
    }

    public void setFechaActividad(Date fechaActividad)
    {
        this.fechaActividad = fechaActividad;
    }

    public Objeto getObjeto()
    {
        return objeto;
    }

    public void setObjeto(Objeto objeto)
    {
        this.objeto = objeto;
    }

    public Usuario getUsuario()
    {
        return usuario;
    }

    public void setUsuario(Usuario usuario)
    {
        this.usuario = usuario;
    }

    public String getFechaActividadF()
    {
       if (null != fechaActividad)
        {
            SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy HH:mm");
            fechaActividadF=dateFormat.format(fechaActividad);
        } else
        {
            fechaActividadF = "";
        }
        return fechaActividadF;
    }

    public void setFechaActividadF(String fechaActividadF)
    {
        this.fechaActividadF = fechaActividadF;
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
        if (!(object instanceof BitacoraActividades))
        {
            return false;
        }
        BitacoraActividades other = (BitacoraActividades) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.gnv.business.ejb.modelo.BitacoraActividades[ id=" + id + " ]";
    }

}
