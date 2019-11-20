/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "USUARIO")
@XmlRootElement
@NamedQueries(
        {
            @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
            @NamedQuery(name = "Usuario.findById", query = "SELECT u FROM Usuario u WHERE u.id = :id"),
            @NamedQuery(name = "Usuario.findByNombre", query = "SELECT u FROM Usuario u WHERE u.nombre = :nombre"),
            @NamedQuery(name = "Usuario.findByApellidoPaterno", query = "SELECT u FROM Usuario u WHERE u.apellidoPaterno = :apellidoPaterno"),
            @NamedQuery(name = "Usuario.findByApellidoMaterno", query = "SELECT u FROM Usuario u WHERE u.apellidoMaterno = :apellidoMaterno"),
            @NamedQuery(name = "Usuario.findByRol", query = "SELECT u FROM Usuario u WHERE u.rol = :rol"),
            @NamedQuery(name = "Usuario.findByCorreo", query = "SELECT u FROM Usuario u WHERE u.correo = :correo"),
            @NamedQuery(name = "Usuario.findByHash", query = "SELECT u FROM Usuario u WHERE u.hash = :hash"),
            @NamedQuery(name = "Usuario.findByClaveAcceso", query = "SELECT u FROM Usuario u WHERE u.claveAcceso = :claveAcceso"),
            @NamedQuery(name = "Usuario.findByEstatus", query = "SELECT u FROM Usuario u WHERE u.estatus = :estatus"),
            @NamedQuery(name = "Usuario.findByActualizarHash", query = "SELECT u FROM Usuario u WHERE u.actualizarHash = :actualizarHash"),
            @NamedQuery(name = "Usuario.findByFechaRegistro", query = "SELECT u FROM Usuario u WHERE u.fechaRegistro = :fechaRegistro"),
            @NamedQuery(name = "Usuario.findByFechaModificacion", query = "SELECT u FROM Usuario u WHERE u.fechaModificacion = :fechaModificacion")
        })
public class Usuario implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private List<BitacoraActividades> bitacoraActividadesList;

    @JoinColumn(name = "COMPANIA", referencedColumnName = "ID")
    @ManyToOne
    private Compania compania;

    @JoinColumn(name = "ROL", referencedColumnName = "ID")
    @ManyToOne
    private Rol rol;

    @JoinColumn(name = "ESTATUS", referencedColumnName = "ID")
    @ManyToOne
    private Estatus estatus;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "APELLIDO_PATERNO")
    private String apellidoPaterno;
    @Column(name = "APELLIDO_MATERNO")
    private String apellidoMaterno;
    @Column(name = "CORREO")
    private String correo;
    @Column(name = "HASH")
    private String hash;
    @Column(name = "CLAVE_ACCESO")
    private String claveAcceso;
    @Column(name = "ACTUALIZAR_HASH")
    private Boolean actualizarHash;
    @Column(name = "FECHA_REGISTRO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "FECHA_MODIFICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Transient
    private String nombreCompleto;

    public Usuario()
    {
    }

    public Usuario(Integer id)
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

    public String getApellidoPaterno()
    {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno)
    {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno()
    {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno)
    {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getCorreo()
    {
        return correo;
    }

    public void setCorreo(String correo)
    {
        this.correo = correo;
    }

    public String getHash()
    {
        return hash;
    }

    public void setHash(String hash)
    {
        this.hash = hash;
    }

    public String getClaveAcceso()
    {
        return claveAcceso;
    }

    public void setClaveAcceso(String claveAcceso)
    {
        this.claveAcceso = claveAcceso;
    }

    public Boolean getActualizarHash()
    {
        return actualizarHash;
    }

    public void setActualizarHash(Boolean actualizarHash)
    {
        this.actualizarHash = actualizarHash;
    }

    public Date getFechaRegistro()
    {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro)
    {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaModificacion()
    {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion)
    {
        this.fechaModificacion = fechaModificacion;
    }

    public String getNombreCompleto()
    {
        if (null != nombre && !nombre.isEmpty())
        {
            nombreCompleto = nombre;
            if (null != apellidoPaterno && !apellidoPaterno.isEmpty())
            {
                nombreCompleto += " ".concat(apellidoPaterno);
                if (null != apellidoMaterno && !apellidoMaterno.isEmpty())
                {
                    nombreCompleto += " ".concat(apellidoMaterno);
                }
            }
        }
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto)
    {
        this.nombreCompleto = nombreCompleto;
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
        if (!(object instanceof Usuario))
        {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.gnv.business.ejb.modelo.Usuario[ id=" + id + " ]";
    }

    public Estatus getEstatus()
    {
        return estatus;
    }

    public void setEstatus(Estatus estatus)
    {
        this.estatus = estatus;
    }

    public Rol getRol()
    {
        return rol;
    }

    public void setRol(Rol rol)
    {
        this.rol = rol;
    }

    public Compania getCompania()
    {
        return compania;
    }

    public void setCompania(Compania compania)
    {
        this.compania = compania;
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
