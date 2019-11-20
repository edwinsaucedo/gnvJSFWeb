/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "COMPANIA")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Compania.findAll", query = "SELECT c FROM Compania c"),
    @NamedQuery(name = "Compania.findById", query = "SELECT c FROM Compania c WHERE c.id = :id"),
    @NamedQuery(name = "Compania.findByRazonsocial", query = "SELECT c FROM Compania c WHERE c.razonSocial = :razonSocial"),
    @NamedQuery(name = "Compania.findByNombre", query = "SELECT c FROM Compania c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Compania.findByCnciasidn", query = "SELECT c FROM Compania c WHERE c.cnciasidn = :cnciasidn")
})
public class Compania implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "compania")
    private List<Financiera> financieraList;

    @OneToMany(mappedBy = "compania")
    private List<Taller> tallerList;

    @OneToMany(mappedBy = "compania")
    private List<Usuario> usuarioList;

    @OneToMany(mappedBy = "compania")
    private List<Documento> documentoList;

    @Column(name = "RAZON_SOCIAL")
    private String razonSocial;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "CNCIASIDN")
    private Long cnciasidn;
    @OneToMany(mappedBy = "compania")
    private List<VehiculoInfoA> vehiculoInfoAList;

    public Compania()
    {
    }

    public Compania(Integer id)
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

    public Long getCnciasidn()
    {
        return cnciasidn;
    }

    public void setCnciasidn(Long cnciasidn)
    {
        this.cnciasidn = cnciasidn;
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
        if (!(object instanceof Compania))
        {
            return false;
        }
        Compania other = (Compania) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.gnv.business.ejb.modelo.Compania[ id=" + id + " ]";
    }

    public String getRazonSocial()
    {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial)
    {
        this.razonSocial = razonSocial;
    }

    @XmlTransient
    public List<Documento> getDocumentoList()
    {
        return documentoList;
    }

    public void setDocumentoList(List<Documento> documentoList)
    {
        this.documentoList = documentoList;
    }

    @XmlTransient
    public List<Usuario> getUsuarioList()
    {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList)
    {
        this.usuarioList = usuarioList;
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
    public List<Financiera> getFinancieraList()
    {
        return financieraList;
    }

    public void setFinancieraList(List<Financiera> financieraList)
    {
        this.financieraList = financieraList;
    }
    
}
