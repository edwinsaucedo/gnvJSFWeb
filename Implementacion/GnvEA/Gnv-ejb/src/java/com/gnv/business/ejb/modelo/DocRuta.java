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
@Table(name = "DOC_RUTA")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "DocRuta.findAll", query = "SELECT d FROM DocRuta d"),
    @NamedQuery(name = "DocRuta.findById", query = "SELECT d FROM DocRuta d WHERE d.id = :id"),
    @NamedQuery(name = "DocRuta.findByRuta", query = "SELECT d FROM DocRuta d WHERE d.ruta = :ruta"),
    @NamedQuery(name = "DocRuta.findByEstatusRow", query = "SELECT d FROM DocRuta d WHERE d.estatusRow = :estatusRow")
})
public class DocRuta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "RUTA")
    private String ruta;
    @Column(name = "ESTATUS_ROW")
    private Integer estatusRow;


    public DocRuta()
    {
    }

    public DocRuta(Integer id)
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

    public String getRuta()
    {
        return ruta;
    }

    public void setRuta(String ruta)
    {
        this.ruta = ruta;
    }

    public Integer getEstatusRow()
    {
        return estatusRow;
    }

    public void setEstatusRow(Integer estatusRow)
    {
        this.estatusRow = estatusRow;
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
        if (!(object instanceof DocRuta))
        {
            return false;
        }
        DocRuta other = (DocRuta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.gnv.business.ejb.modelo.DocRuta[ id=" + id + " ]";
    }
    
}
