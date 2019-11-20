/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.modelo;

import java.io.Serializable;
import java.util.List;
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
@Table(name = "TIPO_CILINDRO")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "TipoCilindro.findAll", query = "SELECT t FROM TipoCilindro t"),
    @NamedQuery(name = "TipoCilindro.findById", query = "SELECT t FROM TipoCilindro t WHERE t.id = :id"),
    @NamedQuery(name = "TipoCilindro.findByNombre", query = "SELECT t FROM TipoCilindro t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TipoCilindro.findByEstatusRow", query = "SELECT t FROM TipoCilindro t WHERE t.estatusRow = :estatusRow")
})
public class TipoCilindro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "ESTATUS_ROW")
    private Integer estatusRow;
    @OneToMany(mappedBy = "tipoCilindro")
    private List<Cilindro> cilindroList;

    public TipoCilindro()
    {
    }

    public TipoCilindro(Integer id)
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
    public List<Cilindro> getCilindroList()
    {
        return cilindroList;
    }

    public void setCilindroList(List<Cilindro> cilindroList)
    {
        this.cilindroList = cilindroList;
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
        if (!(object instanceof TipoCilindro))
        {
            return false;
        }
        TipoCilindro other = (TipoCilindro) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.gnv.business.ejb.modelo.TipoCilindro[ id=" + id + " ]";
    }
    
}
