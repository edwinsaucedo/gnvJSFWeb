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
@Table(name = "TIPO_LEGALIZACION")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "TipoLegalizacion.findAll", query = "SELECT t FROM TipoLegalizacion t"),
    @NamedQuery(name = "TipoLegalizacion.findById", query = "SELECT t FROM TipoLegalizacion t WHERE t.id = :id"),
    @NamedQuery(name = "TipoLegalizacion.findByNombre", query = "SELECT t FROM TipoLegalizacion t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TipoLegalizacion.findByEstatusRow", query = "SELECT t FROM TipoLegalizacion t WHERE t.estatusRow = :estatusRow")
})
public class TipoLegalizacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "ESTATUS_ROW")
    private Integer estatusRow;
    @OneToMany(mappedBy = "tipoLegalizacion")
    private List<Legalizacion> legalizacionList;

    public TipoLegalizacion()
    {
    }

    public TipoLegalizacion(Integer id)
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
    public List<Legalizacion> getLegalizacionList()
    {
        return legalizacionList;
    }

    public void setLegalizacionList(List<Legalizacion> legalizacionList)
    {
        this.legalizacionList = legalizacionList;
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
        if (!(object instanceof TipoLegalizacion))
        {
            return false;
        }
        TipoLegalizacion other = (TipoLegalizacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.gnv.business.ejb.modelo.TipoLegalizacion[ id=" + id + " ]";
    }
    
}
