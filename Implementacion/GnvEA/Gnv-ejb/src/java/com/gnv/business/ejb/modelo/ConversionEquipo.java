/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.modelo;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "CONVERSION_EQUIPO")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "ConversionEquipo.findAll", query = "SELECT c FROM ConversionEquipo c"),
    @NamedQuery(name = "ConversionEquipo.findById", query = "SELECT c FROM ConversionEquipo c WHERE c.id = :id"),
    @NamedQuery(name = "ConversionEquipo.findByTipo", query = "SELECT c FROM ConversionEquipo c WHERE c.tipo = :tipo"),
    @NamedQuery(name = "ConversionEquipo.findByIdRegistro", query = "SELECT c FROM ConversionEquipo c WHERE c.idRegistro = :idRegistro"),
    @NamedQuery(name = "ConversionEquipo.findByEstatusRow", query = "SELECT c FROM ConversionEquipo c WHERE c.estatusRow = :estatusRow"),
    @NamedQuery(name = "ConversionEquipo.findByUsuarioIns", query = "SELECT c FROM ConversionEquipo c WHERE c.usuarioIns = :usuarioIns"),
    @NamedQuery(name = "ConversionEquipo.findByFechaIns", query = "SELECT c FROM ConversionEquipo c WHERE c.fechaIns = :fechaIns")
})
public class ConversionEquipo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "TIPO")
    private Boolean tipo;
    @Column(name = "ID_REGISTRO")
    private Integer idRegistro;
    @Column(name = "ESTATUS_ROW")
    private Integer estatusRow;
    @Column(name = "USUARIO_INS")
    private Integer usuarioIns;
    @Column(name = "FECHA_INS")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIns;
    @JoinColumn(name = "CONVERSION", referencedColumnName = "ID")
    @ManyToOne
    private Conversion conversion;

    public ConversionEquipo()
    {
    }

    public ConversionEquipo(Integer id)
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

    public Boolean getTipo()
    {
        return tipo;
    }

    public void setTipo(Boolean tipo)
    {
        this.tipo = tipo;
    }

    public Integer getIdRegistro()
    {
        return idRegistro;
    }

    public void setIdRegistro(Integer idRegistro)
    {
        this.idRegistro = idRegistro;
    }

    public Integer getEstatusRow()
    {
        return estatusRow;
    }

    public void setEstatusRow(Integer estatusRow)
    {
        this.estatusRow = estatusRow;
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

    public Conversion getConversion()
    {
        return conversion;
    }

    public void setConversion(Conversion conversion)
    {
        this.conversion = conversion;
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
        if (!(object instanceof ConversionEquipo))
        {
            return false;
        }
        ConversionEquipo other = (ConversionEquipo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.gnv.business.ejb.modelo.ConversionEquipo[ id=" + id + " ]";
    }
    
}
