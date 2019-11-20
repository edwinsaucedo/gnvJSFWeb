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
@Table(name = "FABRICANTE")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Fabricante.findAll", query = "SELECT f FROM Fabricante f"),
    @NamedQuery(name = "Fabricante.findById", query = "SELECT f FROM Fabricante f WHERE f.id = :id"),
    @NamedQuery(name = "Fabricante.findByNombre", query = "SELECT f FROM Fabricante f WHERE f.nombre = :nombre"),
    @NamedQuery(name = "Fabricante.findByNumeroDocumento", query = "SELECT f FROM Fabricante f WHERE f.numeroDocumento = :numeroDocumento"),
    @NamedQuery(name = "Fabricante.findByCodigo", query = "SELECT f FROM Fabricante f WHERE f.codigo = :codigo"),
    @NamedQuery(name = "Fabricante.findByDireccion", query = "SELECT f FROM Fabricante f WHERE f.direccion = :direccion"),
    @NamedQuery(name = "Fabricante.findByContacto", query = "SELECT f FROM Fabricante f WHERE f.contacto = :contacto"),
    @NamedQuery(name = "Fabricante.findByTelefono", query = "SELECT f FROM Fabricante f WHERE f.telefono = :telefono"),
    @NamedQuery(name = "Fabricante.findByEmail", query = "SELECT f FROM Fabricante f WHERE f.email = :email"),
    @NamedQuery(name = "Fabricante.findByEstatus", query = "SELECT f FROM Fabricante f WHERE f.estatus = :estatus")
})
public class Fabricante implements Serializable {

    @JoinColumn(name = "CIUDAD", referencedColumnName = "ID")
    @ManyToOne
    private Ciudad ciudad;
    @JoinColumn(name = "FABRICANTE_INFO_A", referencedColumnName = "ID")
    @ManyToOne
    private FabricanteInfoA fabricanteInfoA;

    @JoinColumn(name = "ESTATUS", referencedColumnName = "ID")
    @ManyToOne
    private Estatus estatus;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "NUMERO_DOCUMENTO")
    private String numeroDocumento;
    @Column(name = "CODIGO")
    private String codigo;
    @Column(name = "DIRECCION")
    private String direccion;
    @Column(name = "CONTACTO")
    private String contacto;
    @Column(name = "TELEFONO")
    private String telefono;
    @Column(name = "EMAIL")
    private String email;
    @JoinColumn(name = "EMPRESA", referencedColumnName = "ID")
    @ManyToOne
    private Empresa empresa;
    @JoinColumn(name = "TIPO_DOCUMENTO", referencedColumnName = "ID")
    @ManyToOne
    private TipoDocumentacion tipoDocumento;
    @OneToMany(mappedBy = "fabricante")
    private List<Kit> kitList;
    @OneToMany(mappedBy = "fabricante")
    private List<Cilindro> cilindroList;

    public Fabricante()
    {
    }

    public Fabricante(Integer id)
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

    public String getNumeroDocumento()
    {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento)
    {
        this.numeroDocumento = numeroDocumento;
    }

    public String getCodigo()
    {
        return codigo;
    }

    public void setCodigo(String codigo)
    {
        this.codigo = codigo;
    }

    public String getDireccion()
    {
        return direccion;
    }

    public void setDireccion(String direccion)
    {
        this.direccion = direccion;
    }

    public String getContacto()
    {
        return contacto;
    }

    public void setContacto(String contacto)
    {
        this.contacto = contacto;
    }

    public String getTelefono()
    {
        return telefono;
    }

    public void setTelefono(String telefono)
    {
        this.telefono = telefono;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public Empresa getEmpresa()
    {
        return empresa;
    }

    public void setEmpresa(Empresa empresa)
    {
        this.empresa = empresa;
    }

    public TipoDocumentacion getTipoDocumento()
    {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumentacion tipoDocumento)
    {
        this.tipoDocumento = tipoDocumento;
    }

    @XmlTransient
    public List<Kit> getKitList()
    {
        return kitList;
    }

    public void setKitList(List<Kit> kitList)
    {
        this.kitList = kitList;
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
        if (!(object instanceof Fabricante))
        {
            return false;
        }
        Fabricante other = (Fabricante) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.gnv.business.ejb.modelo.Fabricante[ id=" + id + " ]";
    }

    public Estatus getEstatus()
    {
        return estatus;
    }

    public void setEstatus(Estatus estatus)
    {
        this.estatus = estatus;
    }

    public Ciudad getCiudad()
    {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad)
    {
        this.ciudad = ciudad;
    }

    public FabricanteInfoA getFabricanteInfoA()
    {
        return fabricanteInfoA;
    }

    public void setFabricanteInfoA(FabricanteInfoA fabricanteInfoA)
    {
        this.fabricanteInfoA = fabricanteInfoA;
    }
    
}
