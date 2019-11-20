/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "TIPO_CONVERSION")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "TipoConversion.findAll", query = "SELECT t FROM TipoConversion t"),
    @NamedQuery(name = "TipoConversion.findById", query = "SELECT t FROM TipoConversion t WHERE t.id = :id"),
    @NamedQuery(name = "TipoConversion.findByNombre", query = "SELECT t FROM TipoConversion t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TipoConversion.findByEstatusRow", query = "SELECT t FROM TipoConversion t WHERE t.estatusRow = :estatusRow")
})
public class TipoConversion implements Serializable {

  
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "ESTATUS_ROW")
    private Integer estatusRow;
    @OneToMany(mappedBy = "tipoConversion")
    private List<Conversion> conversionList;

    public TipoConversion()
    {
    }

    public TipoConversion(Integer id)
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

    @XmlTransient
    public List<Conversion> getConversionList()
    {
        return conversionList;
    }

    public void setConversionList(List<Conversion> conversionList)
    {
        this.conversionList = conversionList;
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
        if (!(object instanceof TipoConversion))
        {
            return false;
        }
        TipoConversion other = (TipoConversion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.gnv.business.ejb.modelo.TipoConversion[ id=" + id + " ]";
    }

    
  
    
}
