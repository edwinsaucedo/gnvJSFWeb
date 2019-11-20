/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "FABRICANTE_INFO_A")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "FabricanteInfoA.findAll", query = "SELECT f FROM FabricanteInfoA f"),
    @NamedQuery(name = "FabricanteInfoA.findById", query = "SELECT f FROM FabricanteInfoA f WHERE f.id = :id"),
    @NamedQuery(name = "FabricanteInfoA.findByTipoValorComision", query = "SELECT f FROM FabricanteInfoA f WHERE f.tipoValorComision = :tipoValorComision"),
    @NamedQuery(name = "FabricanteInfoA.findByComisionControlCarga", query = "SELECT f FROM FabricanteInfoA f WHERE f.comisionControlCarga = :comisionControlCarga"),
    @NamedQuery(name = "FabricanteInfoA.findByNumeroAutorizacion", query = "SELECT f FROM FabricanteInfoA f WHERE f.numeroAutorizacion = :numeroAutorizacion"),
    @NamedQuery(name = "FabricanteInfoA.findByFechaAutorizacion", query = "SELECT f FROM FabricanteInfoA f WHERE f.fechaAutorizacion = :fechaAutorizacion"),
    @NamedQuery(name = "FabricanteInfoA.findByFechaVencimiento", query = "SELECT f FROM FabricanteInfoA f WHERE f.fechaVencimiento = :fechaVencimiento"),
    @NamedQuery(name = "FabricanteInfoA.findByObservacion", query = "SELECT f FROM FabricanteInfoA f WHERE f.observacion = :observacion")
})
public class FabricanteInfoA implements Serializable {

    @OneToMany(mappedBy = "fabricanteInfoA")
    private List<Fabricante> fabricanteList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "TIPO_VALOR_COMISION")
    private Boolean tipoValorComision;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "COMISION_CONTROL_CARGA")
    private BigDecimal comisionControlCarga;
    @Column(name = "NUMERO_AUTORIZACION")
    private String numeroAutorizacion;
    @Column(name = "FECHA_AUTORIZACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAutorizacion;
    @Column(name = "FECHA_VENCIMIENTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaVencimiento;
    @Column(name = "OBSERVACION")
    private String observacion;
  

    public FabricanteInfoA()
    {
    }

    public FabricanteInfoA(Integer id)
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

    public Boolean getTipoValorComision()
    {
        return tipoValorComision;
    }

    public void setTipoValorComision(Boolean tipoValorComision)
    {
        this.tipoValorComision = tipoValorComision;
    }

    public BigDecimal getComisionControlCarga()
    {
        return comisionControlCarga;
    }

    public void setComisionControlCarga(BigDecimal comisionControlCarga)
    {
        this.comisionControlCarga = comisionControlCarga;
    }

    public String getNumeroAutorizacion()
    {
        return numeroAutorizacion;
    }

    public void setNumeroAutorizacion(String numeroAutorizacion)
    {
        this.numeroAutorizacion = numeroAutorizacion;
    }

    public Date getFechaAutorizacion()
    {
        return fechaAutorizacion;
    }

    public void setFechaAutorizacion(Date fechaAutorizacion)
    {
        this.fechaAutorizacion = fechaAutorizacion;
    }

    public Date getFechaVencimiento()
    {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento)
    {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getObservacion()
    {
        return observacion;
    }

    public void setObservacion(String observacion)
    {
        this.observacion = observacion;
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
        if (!(object instanceof FabricanteInfoA))
        {
            return false;
        }
        FabricanteInfoA other = (FabricanteInfoA) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.gnv.business.ejb.modelo.FabricanteInfoA[ id=" + id + " ]";
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
    
}
