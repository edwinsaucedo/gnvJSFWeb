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
@Table(name = "KIT")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Kit.findAll", query = "SELECT k FROM Kit k"),
    @NamedQuery(name = "Kit.findById", query = "SELECT k FROM Kit k WHERE k.id = :id"),
    @NamedQuery(name = "Kit.findBySerial", query = "SELECT k FROM Kit k WHERE k.serial = :serial"),
    @NamedQuery(name = "Kit.findByFechaFabricacion", query = "SELECT k FROM Kit k WHERE k.fechaFabricacion = :fechaFabricacion"),
    @NamedQuery(name = "Kit.findByEstatus", query = "SELECT k FROM Kit k WHERE k.estatus = :estatus"),
    @NamedQuery(name = "Kit.findByEstatusRow", query = "SELECT k FROM Kit k WHERE k.estatusRow = :estatusRow"),
    @NamedQuery(name = "Kit.findByUsuarioIns", query = "SELECT k FROM Kit k WHERE k.usuarioIns = :usuarioIns"),
    @NamedQuery(name = "Kit.findByFechaIns", query = "SELECT k FROM Kit k WHERE k.fechaIns = :fechaIns")
})
public class Kit implements Serializable {

    @JoinColumn(name = "ESTATUS", referencedColumnName = "ID")
    @ManyToOne
    private Estatus estatus;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "SERIAL")
    private String serial;
    @Column(name = "FECHA_FABRICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFabricacion;
    @Column(name = "ESTATUS_ROW")
    private Integer estatusRow;
    @Column(name = "USUARIO_INS")
    private Integer usuarioIns;
    @Column(name = "FECHA_INS")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIns;
    @JoinColumn(name = "FABRICANTE", referencedColumnName = "ID")
    @ManyToOne
    private Fabricante fabricante;
    @JoinColumn(name = "LEGALIZACION", referencedColumnName = "ID")
    @ManyToOne
    private Legalizacion legalizacion;
    @JoinColumn(name = "LINEA", referencedColumnName = "ID")
    @ManyToOne
    private Linea linea;
    @JoinColumn(name = "MARCA", referencedColumnName = "ID")
    @ManyToOne
    private Marca marca;

    public Kit()
    {
    }

    public Kit(Integer id)
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

    public String getSerial()
    {
        return serial;
    }

    public void setSerial(String serial)
    {
        this.serial = serial;
    }

    public Date getFechaFabricacion()
    {
        return fechaFabricacion;
    }

    public void setFechaFabricacion(Date fechaFabricacion)
    {
        this.fechaFabricacion = fechaFabricacion;
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

    public Fabricante getFabricante()
    {
        return fabricante;
    }

    public void setFabricante(Fabricante fabricante)
    {
        this.fabricante = fabricante;
    }

    public Legalizacion getLegalizacion()
    {
        return legalizacion;
    }

    public void setLegalizacion(Legalizacion legalizacion)
    {
        this.legalizacion = legalizacion;
    }

    public Linea getLinea()
    {
        return linea;
    }

    public void setLinea(Linea linea)
    {
        this.linea = linea;
    }

    public Marca getMarca()
    {
        return marca;
    }

    public void setMarca(Marca marca)
    {
        this.marca = marca;
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
        if (!(object instanceof Kit))
        {
            return false;
        }
        Kit other = (Kit) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.gnv.business.ejb.modelo.Kit[ id=" + id + " ]";
    }

    public Estatus getEstatus()
    {
        return estatus;
    }

    public void setEstatus(Estatus estatus)
    {
        this.estatus = estatus;
    }
    
}
