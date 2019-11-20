/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.modelo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
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
@Table(name = "CONVERSION")
@XmlRootElement
@NamedQueries(
        {
            @NamedQuery(name = "Conversion.findAll", query = "SELECT c FROM Conversion c"),
            @NamedQuery(name = "Conversion.findById", query = "SELECT c FROM Conversion c WHERE c.id = :id"),
            @NamedQuery(name = "Conversion.findByFechaConversion", query = "SELECT c FROM Conversion c WHERE c.fechaConversion = :fechaConversion"),
            @NamedQuery(name = "Conversion.findByObservacion", query = "SELECT c FROM Conversion c WHERE c.observacion = :observacion"),
            @NamedQuery(name = "Conversion.findByEstatusRow", query = "SELECT c FROM Conversion c WHERE c.estatusRow = :estatusRow"),
            @NamedQuery(name = "Conversion.findByUsuarioIns", query = "SELECT c FROM Conversion c WHERE c.usuarioIns = :usuarioIns"),
            @NamedQuery(name = "Conversion.findByFechaIns", query = "SELECT c FROM Conversion c WHERE c.fechaIns = :fechaIns")
        })
public class Conversion implements Serializable {

    @JoinColumn(name = "ESTATUS", referencedColumnName = "ID")
    @ManyToOne
    private Estatus estatus;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "FECHA_CONVERSION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaConversion;
    @Column(name = "OBSERVACION")
    private String observacion;
    @Column(name = "ESTATUS_ROW")
    private Integer estatusRow;
    @Column(name = "USUARIO_INS")
    private Integer usuarioIns;
    @Column(name = "FECHA_INS")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIns;
    @OneToMany(mappedBy = "conversion")
    private List<ConversionEquipo> conversionEquipoList;
    @JoinColumn(name = "TALLER", referencedColumnName = "ID")
    @ManyToOne
    private Taller taller;
    @JoinColumn(name = "TIPO_CONVENIO", referencedColumnName = "ID")
    @ManyToOne
    private TipoConvenio tipoConvenio;
    @JoinColumn(name = "TIPO_CONVERSION", referencedColumnName = "ID")
    @ManyToOne
    private TipoConversion tipoConversion;
    @JoinColumn(name = "VALORACION", referencedColumnName = "ID")
    @ManyToOne
    private Valoracion valoracion;
    @Transient
    private String fechaConversionF;

    public Conversion()
    {
    }

    public Conversion(Integer id)
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

    public Date getFechaConversion()
    {
        return fechaConversion;
    }

    public void setFechaConversion(Date fechaConversion)
    {
        this.fechaConversion = fechaConversion;
    }

    public String getObservacion()
    {
        return observacion;
    }

    public void setObservacion(String observacion)
    {
        this.observacion = observacion;
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

    @XmlTransient
    public List<ConversionEquipo> getConversionEquipoList()
    {
        return conversionEquipoList;
    }

    public void setConversionEquipoList(List<ConversionEquipo> conversionEquipoList)
    {
        this.conversionEquipoList = conversionEquipoList;
    }

    public Taller getTaller()
    {
        return taller;
    }

    public void setTaller(Taller taller)
    {
        this.taller = taller;
    }

    public TipoConvenio getTipoConvenio()
    {
        return tipoConvenio;
    }

    public void setTipoConvenio(TipoConvenio tipoConvenio)
    {
        this.tipoConvenio = tipoConvenio;
    }

    public TipoConversion getTipoConversion()
    {
        return tipoConversion;
    }

    public void setTipoConversion(TipoConversion tipoConversion)
    {
        this.tipoConversion = tipoConversion;
    }

    public Valoracion getValoracion()
    {
        return valoracion;
    }

    public void setValoracion(Valoracion valoracion)
    {
        this.valoracion = valoracion;
    }

    public Estatus getEstatus()
    {
        return estatus;
    }

    public void setEstatus(Estatus estatus)
    {
        this.estatus = estatus;
    }

    public String getFechaConversionF()
    {
        if (null != fechaConversion)
        {
            SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
            fechaConversionF=dateFormat.format(fechaConversion);
        } else
        {
            fechaConversionF = "";
        }
        return fechaConversionF;
    }

    public void setFechaConversionF(String fechaConversionF)
    {
        this.fechaConversionF = fechaConversionF;
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
        if (!(object instanceof Conversion))
        {
            return false;
        }
        Conversion other = (Conversion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.gnv.business.ejb.modelo.Conversion[ id=" + id + " ]";
    }

}
