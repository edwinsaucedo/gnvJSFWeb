/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.modelo;

import java.io.Serializable;
import java.util.Date;
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
@Table(name = "PROPIETARIO")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Propietario.findAll", query = "SELECT p FROM Propietario p"),
    @NamedQuery(name = "Propietario.findById", query = "SELECT p FROM Propietario p WHERE p.id = :id"),
    @NamedQuery(name = "Propietario.findByNumeroIdentificacion", query = "SELECT p FROM Propietario p WHERE p.numeroIdentificacion = :numeroIdentificacion"),
    @NamedQuery(name = "Propietario.findByCncdiridn", query = "SELECT p FROM Propietario p WHERE p.cncdiridn = :cncdiridn"),
    @NamedQuery(name = "Propietario.findByCnciasidn", query = "SELECT p FROM Propietario p WHERE p.cnciasidn = :cnciasidn")
})
public class Propietario implements Serializable {

    @Column(name = "CNCDIRIDN")
    private Integer cncdiridn;
    @Column(name = "CNCIASIDN")
    private Integer cnciasidn;
    @Column(name = "ESTATUS_ROW")
    private Integer estatusRow;
    @OneToMany(mappedBy = "propietario")
    private List<CreditoPersona> creditoPersonaList;

    @OneToMany(mappedBy = "propietario")
    private List<VehPropietario> vehPropietarioList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NUMERO_IDENTIFICACION")
    private String numeroIdentificacion;
    @Column(name = "APELLIDO")
    private String apellido;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "TELEFONO")
    private String telefono;
    @Column(name = "USUARIO_INS")
    private Integer usuarioIns;
    @Column(name = "FECHA_INS")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIns;
    @Transient
    private String nombreCompleto;
   
    @JoinColumn(name = "TIPO_DOCUMENTACION", referencedColumnName = "ID")
    @ManyToOne
    private TipoDocumentacion tipoDocumentacion;

    public Propietario()
    {
    }

    public Propietario(Integer id)
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

    public String getNumeroIdentificacion()
    {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion)
    {
        this.numeroIdentificacion = numeroIdentificacion;
    }


    public TipoDocumentacion getTipoDocumentacion()
    {
        return tipoDocumentacion;
    }

    public void setTipoDocumentacion(TipoDocumentacion tipoDocumentacion)
    {
        this.tipoDocumentacion = tipoDocumentacion;
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
        if (!(object instanceof Propietario))
        {
            return false;
        }
        Propietario other = (Propietario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.gnv.business.ejb.modelo.Propietario[ id=" + id + " ]";
    }

    public String getTelefono()
    {
        return telefono;
    }

    public void setTelefono(String telefono)
    {
        this.telefono = telefono;
    }

    @XmlTransient
    public List<VehPropietario> getVehPropietarioList()
    {
        return vehPropietarioList;
    }

    public void setVehPropietarioList(List<VehPropietario> vehPropietarioList)
    {
        this.vehPropietarioList = vehPropietarioList;
    }

    public Integer getUsuarioIns()
    {
        return usuarioIns;
    }

    public void setUsuarioIns(Integer usuarioIns)
    {
        this.usuarioIns = usuarioIns;
    }

    public Date getFechaIns()
    {
        return fechaIns;
    }

    public void setFechaIns(Date fechaIns)
    {
        this.fechaIns = fechaIns;
    }

    public Integer getEstatusRow()
    {
        return estatusRow;
    }

    public void setEstatusRow(Integer estatusRow)
    {
        this.estatusRow = estatusRow;
    }


    public String getApellido()
    {
        return apellido;
    }

    public void setApellido(String apellido)
    {
        this.apellido = apellido;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }
    public String getNombreCompleto()
    {
        if (null != nombre && !nombre.isEmpty())
        {
            nombreCompleto = nombre;
            if (null != apellido && !apellido.isEmpty())
            {
                nombreCompleto += " ".concat(apellido);
            }
        }
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto)
    {
        this.nombreCompleto = nombreCompleto;
    }

    public Integer getCncdiridn()
    {
        return cncdiridn;
    }

    public void setCncdiridn(Integer cncdiridn)
    {
        this.cncdiridn = cncdiridn;
    }

    public Integer getCnciasidn()
    {
        return cnciasidn;
    }

    public void setCnciasidn(Integer cnciasidn)
    {
        this.cnciasidn = cnciasidn;
    }

   
    @XmlTransient
    public List<CreditoPersona> getCreditoPersonaList()
    {
        return creditoPersonaList;
    }

    public void setCreditoPersonaList(List<CreditoPersona> creditoPersonaList)
    {
        this.creditoPersonaList = creditoPersonaList;
    }
}
