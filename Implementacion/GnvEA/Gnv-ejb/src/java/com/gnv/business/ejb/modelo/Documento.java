/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.modelo;

import java.io.Serializable;
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
@Table(name = "DOCUMENTO")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Documento.findAll", query = "SELECT d FROM Documento d"),
    @NamedQuery(name = "Documento.findById", query = "SELECT d FROM Documento d WHERE d.id = :id"),
    @NamedQuery(name = "Documento.findByIdRegistro", query = "SELECT d FROM Documento d WHERE d.idRegistro = :idRegistro"),
    @NamedQuery(name = "Documento.findByNombre", query = "SELECT d FROM Documento d WHERE d.nombre = :nombre"),
    @NamedQuery(name = "Documento.findByTamanio", query = "SELECT d FROM Documento d WHERE d.tamanio = :tamanio"),
    @NamedQuery(name = "Documento.findByUsuarioIns", query = "SELECT d FROM Documento d WHERE d.usuarioIns = :usuarioIns"),
    @NamedQuery(name = "Documento.findByFechaIns", query = "SELECT d FROM Documento d WHERE d.fechaIns = :fechaIns"),
    @NamedQuery(name = "Documento.findByEstatusRow", query = "SELECT d FROM Documento d WHERE d.estatusRow = :estatusRow"),
    @NamedQuery(name = "Documento.findByPublico", query = "SELECT d FROM Documento d WHERE d.publico = :publico")
})
public class Documento implements Serializable {

   

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "TAMANIO")
    private String tamanio;
    @Column(name = "PUBLICO")
    private boolean publico;
    @Column(name = "RUTA")
    private String ruta;
    @Column(name = "ID_REGISTRO")
    private Integer idRegistro;
    @JoinColumn(name = "TIPO_ARCHIVO", referencedColumnName = "ID")
    @ManyToOne
    private DocTipoArchivo tipoArchivo;
    @JoinColumn(name = "OBJETO", referencedColumnName = "ID")
    @ManyToOne
    private Objeto objeto;
    @Column(name = "USUARIO_INS")
    private Integer usuarioIns;
    @Column(name = "FECHA_INS")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIns;
    @Column(name = "ESTATUS_ROW")
    private Integer estatusRow;
    @JoinColumn(name = "COMPANIA", referencedColumnName = "ID")
    @ManyToOne
    private Compania compania;

    public Documento()
    {
    }

    public Documento(Integer id)
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

    public Integer getIdRegistro()
    {
        return idRegistro;
    }

    public void setIdRegistro(Integer idRegistro)
    {
        this.idRegistro = idRegistro;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getTamanio()
    {
        return tamanio;
    }

    public void setTamanio(String tamanio)
    {
        this.tamanio = tamanio;
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

    public Integer getEstatusRow()
    {
        return estatusRow;
    }

    public void setEstatusRow(Integer estatusRow)
    {
        this.estatusRow = estatusRow;
    }

    public boolean isPublico()
    {
        return publico;
    }

    public void setPublico(boolean publico)
    {
        this.publico = publico;
    }


    public DocTipoArchivo getTipoArchivo()
    {
        return tipoArchivo;
    }

    public void setTipoArchivo(DocTipoArchivo tipoArchivo)
    {
        this.tipoArchivo = tipoArchivo;
    }

    public Objeto getObjeto()
    {
        return objeto;
    }

    public void setObjeto(Objeto objeto)
    {
        this.objeto = objeto;
    }
        public Compania getCompania()
    {
        return compania;
    }

    public void setCompania(Compania compania)
    {
        this.compania = compania;
    }

    public String getRuta()
    {
        return ruta;
    }

    public void setRuta(String ruta)
    {
        this.ruta = ruta;
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
        if (!(object instanceof Documento))
        {
            return false;
        }
        Documento other = (Documento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.gnv.business.ejb.modelo.Documento[ id=" + id + " ]";
    }

}
