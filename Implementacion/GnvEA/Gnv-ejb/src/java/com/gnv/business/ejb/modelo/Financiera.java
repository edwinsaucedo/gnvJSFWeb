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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "FINANCIERA")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Financiera.findAll", query = "SELECT f FROM Financiera f"),
    @NamedQuery(name = "Financiera.findById", query = "SELECT f FROM Financiera f WHERE f.id = :id"),
    @NamedQuery(name = "Financiera.findByNombre", query = "SELECT f FROM Financiera f WHERE f.nombre = :nombre"),
    @NamedQuery(name = "Financiera.findByLogotipo", query = "SELECT f FROM Financiera f WHERE f.logotipo = :logotipo"),
    @NamedQuery(name = "Financiera.findByRfc", query = "SELECT f FROM Financiera f WHERE f.rfc = :rfc"),
    @NamedQuery(name = "Financiera.findByEstatusRow", query = "SELECT f FROM Financiera f WHERE f.estatusRow = :estatusRow"),
    @NamedQuery(name = "Financiera.findByUsuarioIns", query = "SELECT f FROM Financiera f WHERE f.usuarioIns = :usuarioIns"),
    @NamedQuery(name = "Financiera.findByFechaIns", query = "SELECT f FROM Financiera f WHERE f.fechaIns = :fechaIns")
})
public class Financiera implements Serializable {

    @Basic(optional = false)
    @Column(name = "ESTATUS_ROW")
    private Integer estatusRow;
    @OneToMany(mappedBy = "financiera")
    private List<CreditoCotizacion> creditoCotizacionList;

    private static final long serialVersionUID = 1L;
   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "LOGOTIPO")
    private String logotipo;
    @Basic(optional = false)
    @Column(name = "RFC")
    private String rfc;
    @Basic(optional = false)
    @Column(name = "USUARIO_INS")
    private int usuarioIns;
    @Column(name = "FECHA_INS")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIns;
    @JoinColumn(name = "COMPANIA", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Compania compania;
    @OneToMany(mappedBy = "financiera")
    private List<Credito> creditoList;

    public Financiera()
    {
    }

    public Financiera(Integer id)
    {
        this.id = id;
    }

    public Financiera(Integer id, String nombre, String rfc, Integer estatusRow, int usuarioIns)
    {
        this.id = id;
        this.nombre = nombre;
        this.rfc = rfc;
        this.estatusRow = estatusRow;
        this.usuarioIns = usuarioIns;
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

    public String getLogotipo()
    {
        return logotipo;
    }

    public void setLogotipo(String logotipo)
    {
        this.logotipo = logotipo;
    }

    public String getRfc()
    {
        return rfc;
    }

    public void setRfc(String rfc)
    {
        this.rfc = rfc;
    }

    public Integer getEstatusRow()
    {
        return estatusRow;
    }

    public void setEstatusRow(Integer estatusRow)
    {
        this.estatusRow = estatusRow;
    }

    public int getUsuarioIns()
    {
        return usuarioIns;
    }

    public void setUsuarioIns(int usuarioIns)
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

    public Compania getCompania()
    {
        return compania;
    }

    public void setCompania(Compania compania)
    {
        this.compania = compania;
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
        if (!(object instanceof Financiera))
        {
            return false;
        }
        Financiera other = (Financiera) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.gnv.business.ejb.modelo.Financiera[ id=" + id + " ]";
    }

    @XmlTransient
    public List<CreditoCotizacion> getCreditoCotizacionList() {
        return creditoCotizacionList;
    }

    public void setCreditoCotizacionList(List<CreditoCotizacion> creditoCotizacionList) {
        this.creditoCotizacionList = creditoCotizacionList;
    }
    
}
