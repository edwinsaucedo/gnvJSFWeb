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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "TIPO_CREDITO")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "TipoCredito.findAll", query = "SELECT t FROM TipoCredito t"),
    @NamedQuery(name = "TipoCredito.findById", query = "SELECT t FROM TipoCredito t WHERE t.id = :id"),
    @NamedQuery(name = "TipoCredito.findByNombre", query = "SELECT t FROM TipoCredito t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TipoCredito.findByEstatusRow", query = "SELECT t FROM TipoCredito t WHERE t.estatusRow = :estatusRow"),
    @NamedQuery(name = "TipoCredito.findByUsuariosIns", query = "SELECT t FROM TipoCredito t WHERE t.usuariosIns = :usuariosIns"),
    @NamedQuery(name = "TipoCredito.findByFechaIns", query = "SELECT t FROM TipoCredito t WHERE t.fechaIns = :fechaIns")
})
public class TipoCredito implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "ESTATUS_ROW")
    private Integer estatusRow;
    @Column(name = "USUARIOS_INS")
    private Integer usuariosIns;
    @Column(name = "FECHA_INS")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIns;
    @OneToMany(mappedBy = "tipoCredito")
    private List<Credito> creditoList;

    public TipoCredito()
    {
    }

    public TipoCredito(Integer id)
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

    public Integer getEstatusRow()
    {
        return estatusRow;
    }

    public void setEstatusRow(Integer estatusRow)
    {
        this.estatusRow = estatusRow;
    }

    public Integer getUsuariosIns()
    {
        return usuariosIns;
    }

    public void setUsuariosIns(Integer usuariosIns)
    {
        this.usuariosIns = usuariosIns;
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
    public List<Credito> getCreditoList()
    {
        return creditoList;
    }

    public void setCreditoList(List<Credito> creditoList)
    {
        this.creditoList = creditoList;
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
        if (!(object instanceof TipoCredito))
        {
            return false;
        }
        TipoCredito other = (TipoCredito) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.gnv.business.ejb.modelo.TipoCredito[ id=" + id + " ]";
    }
    
}
