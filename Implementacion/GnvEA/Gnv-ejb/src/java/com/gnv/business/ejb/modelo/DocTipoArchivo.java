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
@Table(name = "DOC_TIPO_ARCHIVO")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "DocTipoArchivo.findAll", query = "SELECT d FROM DocTipoArchivo d"),
    @NamedQuery(name = "DocTipoArchivo.findById", query = "SELECT d FROM DocTipoArchivo d WHERE d.id = :id"),
    @NamedQuery(name = "DocTipoArchivo.findByNombre", query = "SELECT d FROM DocTipoArchivo d WHERE d.nombre = :nombre"),
    @NamedQuery(name = "DocTipoArchivo.findByDescripcion", query = "SELECT d FROM DocTipoArchivo d WHERE d.descripcion = :descripcion"),
    @NamedQuery(name = "DocTipoArchivo.findByExtension", query = "SELECT d FROM DocTipoArchivo d WHERE d.extension = :extension"),
    @NamedQuery(name = "DocTipoArchivo.findByEstatusRow", query = "SELECT d FROM DocTipoArchivo d WHERE d.estatusRow = :estatusRow")
})
public class DocTipoArchivo implements Serializable {

   

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "EXTENSION")
    private String extension;
    @Column(name = "ESTATUS_ROW")
    private Integer estatusRow;
    @Column(name = "MIME_TYPE")
    private String mimeType;
    @OneToMany(mappedBy = "tipoArchivo")
    private List<Documento> documentoList;

    public DocTipoArchivo()
    {
    }

    public DocTipoArchivo(Integer id)
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

    public String getDescripcion()
    {
        return descripcion;
    }

    public void setDescripcion(String descripcion)
    {
        this.descripcion = descripcion;
    }

    public String getExtension()
    {
        return extension;
    }

    public void setExtension(String extension)
    {
        this.extension = extension;
    }
    public String getMimeType()
    {
        return mimeType;
    }

    public void setMimeType(String mimeType)
    {
        this.mimeType = mimeType;
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
    public List<Documento> getDocumentoList()
    {
        return documentoList;
    }

    public void setDocumentoList(List<Documento> documentoList)
    {
        this.documentoList = documentoList;
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
        if (!(object instanceof DocTipoArchivo))
        {
            return false;
        }
        DocTipoArchivo other = (DocTipoArchivo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.gnv.business.ejb.modelo.DocTipoArchivo[ id=" + id + " ]";
    }
  

 
    
}
