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
@Table(name = "TALLER")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Taller.findAll", query = "SELECT t FROM Taller t"),
    @NamedQuery(name = "Taller.findById", query = "SELECT t FROM Taller t WHERE t.id = :id"),
    @NamedQuery(name = "Taller.findByNombre", query = "SELECT t FROM Taller t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "Taller.findByUsuarioIns", query = "SELECT t FROM Taller t WHERE t.usuarioIns = :usuarioIns"),
    @NamedQuery(name = "Taller.findByFechaIns", query = "SELECT t FROM Taller t WHERE t.fechaIns = :fechaIns"),
    @NamedQuery(name = "Taller.findByEstatusRow", query = "SELECT t FROM Taller t WHERE t.estatusRow = :estatusRow")
})
public class Taller implements Serializable {

   
    @Column(name = "ESTATUS_ROW")
    private Integer estatusRow;
    @OneToMany(mappedBy = "taller")
    private List<Credito> creditoList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "USUARIO_INS")
    private Integer usuarioIns;
    @Column(name = "FECHA_INS")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIns;
    @OneToMany(mappedBy = "taller")
    private List<Revision> revisionList;
    @JoinColumn(name = "EMPRESA", referencedColumnName = "ID")
    @ManyToOne
    private Empresa empresa;
    @JoinColumn(name = "COMPANIA", referencedColumnName = "ID")
    @ManyToOne
    private Compania compania;
    @OneToMany(mappedBy = "taller")
    private List<Conversion> conversionList;
    @OneToMany(mappedBy = "taller")
    private List<Valoracion> valoracionList;

    public Taller()
    {
    }

    public Taller(Integer id)
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

    @XmlTransient
    public List<Revision> getRevisionList()
    {
        return revisionList;
    }

    public void setRevisionList(List<Revision> revisionList)
    {
        this.revisionList = revisionList;
    }

    public Empresa getEmpresa()
    {
        return empresa;
    }

    public void setEmpresa(Empresa empresa)
    {
        this.empresa = empresa;
    }

    @XmlTransient
    public List<Conversion> getConversionList()
    {
        return conversionList;
    }

    public void setConversionList(List<Conversion> conversionList)
    {
        this.conversionList = conversionList;
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
    public List<Valoracion> getValoracionList()
    {
        return valoracionList;
    }

    public void setValoracionList(List<Valoracion> valoracionList)
    {
        this.valoracionList = valoracionList;
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
        if (!(object instanceof Taller))
        {
            return false;
        }
        Taller other = (Taller) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.gnv.business.ejb.modelo.Taller[ id=" + id + " ]";
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

    
   
    
}
