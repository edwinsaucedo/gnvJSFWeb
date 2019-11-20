/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "CILINDRO")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Cilindro.findAll", query = "SELECT c FROM Cilindro c"),
    @NamedQuery(name = "Cilindro.findById", query = "SELECT c FROM Cilindro c WHERE c.id = :id"),
    @NamedQuery(name = "Cilindro.findBySerial", query = "SELECT c FROM Cilindro c WHERE c.serial = :serial"),
    @NamedQuery(name = "Cilindro.findByEstatusRow", query = "SELECT c FROM Cilindro c WHERE c.estatusRow = :estatusRow"),
    @NamedQuery(name = "Cilindro.findByEstatus", query = "SELECT c FROM Cilindro c WHERE c.estatus = :estatus"),
    @NamedQuery(name = "Cilindro.findByCapacidad", query = "SELECT c FROM Cilindro c WHERE c.capacidad = :capacidad"),
    @NamedQuery(name = "Cilindro.findByFechaFabricacion", query = "SELECT c FROM Cilindro c WHERE c.fechaFabricacion = :fechaFabricacion"),
    @NamedQuery(name = "Cilindro.findByClase", query = "SELECT c FROM Cilindro c WHERE c.clase = :clase"),
    @NamedQuery(name = "Cilindro.findByLote", query = "SELECT c FROM Cilindro c WHERE c.lote = :lote"),
    @NamedQuery(name = "Cilindro.findByUsuarioIns", query = "SELECT c FROM Cilindro c WHERE c.usuarioIns = :usuarioIns"),
    @NamedQuery(name = "Cilindro.findByFechaIns", query = "SELECT c FROM Cilindro c WHERE c.fechaIns = :fechaIns")
})
public class Cilindro implements Serializable {

    @JoinColumn(name = "ESTATUS", referencedColumnName = "ID")
    @ManyToOne
    private Estatus estatus;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "SERIAL")
    private String serial;
    @Column(name = "ESTATUS_ROW")
    private Integer estatusRow;
   
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "CAPACIDAD")
    private BigDecimal capacidad;
    @Column(name = "FECHA_FABRICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFabricacion;
    @Column(name = "CLASE")
    private String clase;
    @Column(name = "LOTE")
    private String lote;
    @Column(name = "USUARIO_INS")
    private Integer usuarioIns;
    @Column(name = "FECHA_INS")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIns;
    @JoinColumn(name = "FABRICANTE", referencedColumnName = "ID")
    @ManyToOne
    private Fabricante fabricante;
    @JoinColumn(name = "LEGALIZACION", referencedColumnName = "ID")
    @ManyToOne
    private Legalizacion legalizacion;
    @JoinColumn(name = "LINEA", referencedColumnName = "ID")
    @ManyToOne
    private Linea linea;
    @JoinColumn(name = "MARCA", referencedColumnName = "ID")
    @ManyToOne
    private Marca marca;
    @JoinColumn(name = "TIPO_CILINDRO", referencedColumnName = "ID")
    @ManyToOne
    private TipoCilindro tipoCilindro;

    public Cilindro()
    {
    }

    public Cilindro(Integer id)
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

    public String getSerial()
    {
        return serial;
    }

    public void setSerial(String serial)
    {
        this.serial = serial;
    }

    public Integer getEstatusRow()
    {
        return estatusRow;
    }

    public void setEstatusRow(Integer estatusRow)
    {
        this.estatusRow = estatusRow;
    }

  

    public BigDecimal getCapacidad()
    {
        return capacidad;
    }

    public void setCapacidad(BigDecimal capacidad)
    {
        this.capacidad = capacidad;
    }

    public Date getFechaFabricacion()
    {
        return fechaFabricacion;
    }

    public void setFechaFabricacion(Date fechaFabricacion)
    {
        this.fechaFabricacion = fechaFabricacion;
    }

    public String getClase()
    {
        return clase;
    }

    public void setClase(String clase)
    {
        this.clase = clase;
    }

    public String getLote()
    {
        return lote;
    }

    public void setLote(String lote)
    {
        this.lote = lote;
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

    public Fabricante getFabricante()
    {
        return fabricante;
    }

    public void setFabricante(Fabricante fabricante)
    {
        this.fabricante = fabricante;
    }

    public Legalizacion getLegalizacion()
    {
        return legalizacion;
    }

    public void setLegalizacion(Legalizacion legalizacion)
    {
        this.legalizacion = legalizacion;
    }

    public Linea getLinea()
    {
        return linea;
    }

    public void setLinea(Linea linea)
    {
        this.linea = linea;
    }

    public Marca getMarca()
    {
        return marca;
    }

    public void setMarca(Marca marca)
    {
        this.marca = marca;
    }

    public TipoCilindro getTipoCilindro()
    {
        return tipoCilindro;
    }

    public void setTipoCilindro(TipoCilindro tipoCilindro)
    {
        this.tipoCilindro = tipoCilindro;
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
        if (!(object instanceof Cilindro))
        {
            return false;
        }
        Cilindro other = (Cilindro) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.gnv.business.ejb.modelo.Cilindro[ id=" + id + " ]";
    }

    public Estatus getEstatus()
    {
        return estatus;
    }

    public void setEstatus(Estatus estatus)
    {
        this.estatus = estatus;
    }
    
}
