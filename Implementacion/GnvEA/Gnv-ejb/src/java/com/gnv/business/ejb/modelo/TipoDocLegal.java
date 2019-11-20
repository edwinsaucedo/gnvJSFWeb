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
@Table(name = "TIPO_DOC_LEGAL")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "TipoDocLegal.findAll", query = "SELECT t FROM TipoDocLegal t"),
    @NamedQuery(name = "TipoDocLegal.findById", query = "SELECT t FROM TipoDocLegal t WHERE t.id = :id"),
    @NamedQuery(name = "TipoDocLegal.findByNombre", query = "SELECT t FROM TipoDocLegal t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TipoDocLegal.findByEstatusRow", query = "SELECT t FROM TipoDocLegal t WHERE t.estatusRow = :estatusRow")
})
public class TipoDocLegal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "ESTATUS_ROW")
    private Integer estatusRow;
    @OneToMany(mappedBy = "tipoDocumento")
    private List<Legalizacion> legalizacionList;

    public TipoDocLegal()
    {
    }

    public TipoDocLegal(Integer id)
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
        if (!(object instanceof TipoDocLegal))
        {
            return false;
        }
        TipoDocLegal other = (TipoDocLegal) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.gnv.business.ejb.modelo.TipoDocLegal[ id=" + id + " ]";
    }
    
}
