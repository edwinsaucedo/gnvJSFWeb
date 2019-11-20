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
@Table(name = "EMPRESA")
@XmlRootElement
@NamedQueries(
        {
            @NamedQuery(name = "Empresa.findAll", query = "SELECT e FROM Empresa e"),
            @NamedQuery(name = "Empresa.findById", query = "SELECT e FROM Empresa e WHERE e.id = :id"),
            @NamedQuery(name = "Empresa.findByNombre", query = "SELECT e FROM Empresa e WHERE e.nombre = :nombre"),
            @NamedQuery(name = "Empresa.findByRfc", query = "SELECT e FROM Empresa e WHERE e.rfc = :rfc")
        })
public class Empresa implements Serializable {

    
    @OneToMany(mappedBy = "empresa")
    private List<Eds> edsList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "RFC")
    private String rfc;
    @Column(name = "ESTATUS_ROW")
    private Integer estatusRow;

    @OneToMany(mappedBy = "empresa")
    private List<Taller> tallerList;
    @OneToMany(mappedBy = "empresa")
    private List<Fabricante> fabricanteList;

    public Empresa()
    {
    }

    public Empresa(Integer id)
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

    public String getRfc()
    {
        return rfc;
    }

    public void setRfc(String rfc)
    {
        this.rfc = rfc;
    }

    @XmlTransient
    public List<Taller> getTallerList()
    {
        return tallerList;
    }

    public void setTallerList(List<Taller> tallerList)
    {
        this.tallerList = tallerList;
    }

    @XmlTransient
    public List<Fabricante> getFabricanteList()
    {
        return fabricanteList;
    }

    public void setFabricanteList(List<Fabricante> fabricanteList)
    {
        this.fabricanteList = fabricanteList;
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
        if (!(object instanceof Empresa))
        {
            return false;
        }
        Empresa other = (Empresa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.gnv.business.ejb.modelo.Empresa[ id=" + id + " ]";
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
    public List<Eds> getEdsList()
    {
        return edsList;
    }

    public void setEdsList(List<Eds> edsList)
    {
        this.edsList = edsList;
    }

   

}
