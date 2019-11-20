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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "CIUDAD")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Ciudad.findAll", query = "SELECT c FROM Ciudad c"),
    @NamedQuery(name = "Ciudad.findById", query = "SELECT c FROM Ciudad c WHERE c.id = :id"),
    @NamedQuery(name = "Ciudad.findByNombre", query = "SELECT c FROM Ciudad c WHERE c.nombre = :nombre")
})
public class Ciudad implements Serializable {

    @OneToMany(mappedBy = "ciudad")
    private List<Eds> edsList;

    @OneToMany(mappedBy = "ciudad")
    private List<Fabricante> fabricanteList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NOMBRE")
    private String nombre;
    @OneToMany(mappedBy = "ciudad")
    private List<VehiculoInfoA> vehiculoInfoAList;
    @JoinColumn(name = "ESTADO", referencedColumnName = "ID")
    @ManyToOne
    private Estado estado;

    public Ciudad()
    {
    }

    public Ciudad(Integer id)
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

    @XmlTransient
    public List<VehiculoInfoA> getVehiculoInfoAList()
    {
        return vehiculoInfoAList;
    }

    public void setVehiculoInfoAList(List<VehiculoInfoA> vehiculoInfoAList)
    {
        this.vehiculoInfoAList = vehiculoInfoAList;
    }

    public Estado getEstado()
    {
        return estado;
    }

    public void setEstado(Estado estado)
    {
        this.estado = estado;
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
        if (!(object instanceof Ciudad))
        {
            return false;
        }
        Ciudad other = (Ciudad) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.gnv.business.ejb.modelo.Ciudad[ id=" + id + " ]";
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
