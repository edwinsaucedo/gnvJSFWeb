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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "SERVIDOR_ARCHIVOS")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "ServidorArchivos.findAll", query = "SELECT s FROM ServidorArchivos s"),
    @NamedQuery(name = "ServidorArchivos.findById", query = "SELECT s FROM ServidorArchivos s WHERE s.id = :id"),
    @NamedQuery(name = "ServidorArchivos.findByNombre", query = "SELECT s FROM ServidorArchivos s WHERE s.nombre = :nombre"),
    @NamedQuery(name = "ServidorArchivos.findByDireccion", query = "SELECT s FROM ServidorArchivos s WHERE s.direccion = :direccion"),
    @NamedQuery(name = "ServidorArchivos.findByUsuario", query = "SELECT s FROM ServidorArchivos s WHERE s.usuario = :usuario"),
    @NamedQuery(name = "ServidorArchivos.findByPassword", query = "SELECT s FROM ServidorArchivos s WHERE s.password = :password"),
    @NamedQuery(name = "ServidorArchivos.findByPuerto", query = "SELECT s FROM ServidorArchivos s WHERE s.puerto = :puerto"),
    @NamedQuery(name = "ServidorArchivos.findByEsSftp", query = "SELECT s FROM ServidorArchivos s WHERE s.esSftp = :esSftp"),
    @NamedQuery(name = "ServidorArchivos.findByDirectorioBase", query = "SELECT s FROM ServidorArchivos s WHERE s.directorioBase = :directorioBase")
})
public class ServidorArchivos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "DIRECCION")
    private String direccion;
    @Basic(optional = false)
    @Column(name = "USUARIO")
    private String usuario;
    @Basic(optional = false)
    @Column(name = "PASSWORD")
    private String password;
    @Basic(optional = false)
    @Column(name = "PUERTO")
    private int puerto;
    @Basic(optional = false)
    @Column(name = "ES_SFTP")
    private boolean esSftp;
    @Column(name = "DIRECTORIO_BASE")
    private String directorioBase;

    public ServidorArchivos()
    {
    }

    public ServidorArchivos(Integer id)
    {
        this.id = id;
    }

    public ServidorArchivos(Integer id, String nombre, String direccion, String usuario, String password, int puerto, boolean esSftp)
    {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.usuario = usuario;
        this.password = password;
        this.puerto = puerto;
        this.esSftp = esSftp;
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

    public String getDireccion()
    {
        return direccion;
    }

    public void setDireccion(String direccion)
    {
        this.direccion = direccion;
    }

    public String getUsuario()
    {
        return usuario;
    }

    public void setUsuario(String usuario)
    {
        this.usuario = usuario;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public int getPuerto()
    {
        return puerto;
    }

    public void setPuerto(int puerto)
    {
        this.puerto = puerto;
    }

    public boolean getEsSftp()
    {
        return esSftp;
    }

    public void setEsSftp(boolean esSftp)
    {
        this.esSftp = esSftp;
    }

    public String getDirectorioBase()
    {
        return directorioBase;
    }

    public void setDirectorioBase(String directorioBase)
    {
        this.directorioBase = directorioBase;
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
        if (!(object instanceof ServidorArchivos))
        {
            return false;
        }
        ServidorArchivos other = (ServidorArchivos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.gnv.business.ejb.modelo.ServidorArchivos[ id=" + id + " ]";
    }
    
}
