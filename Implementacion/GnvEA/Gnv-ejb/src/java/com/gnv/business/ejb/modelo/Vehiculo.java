/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.modelo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "VEHICULO")
@XmlRootElement
@NamedQueries(
        {
            @NamedQuery(name = "Vehiculo.findAll", query = "SELECT v FROM Vehiculo v"),
            @NamedQuery(name = "Vehiculo.findById", query = "SELECT v FROM Vehiculo v WHERE v.id = :id"),
            @NamedQuery(name = "Vehiculo.findByPlaca", query = "SELECT v FROM Vehiculo v WHERE v.placa = :placa"),
            @NamedQuery(name = "Vehiculo.findByVin", query = "SELECT v FROM Vehiculo v WHERE v.vin = :vin"),
            @NamedQuery(name = "Vehiculo.findByModelo", query = "SELECT v FROM Vehiculo v WHERE v.modelo = :modelo"),
            @NamedQuery(name = "Vehiculo.findByFechaProxRev", query = "SELECT v FROM Vehiculo v WHERE v.fechaProxRev = :fechaProxRev"),
            @NamedQuery(name = "Vehiculo.findByEngomadoPatrimonial", query = "SELECT v FROM Vehiculo v WHERE v.engomadoPatrimonial = :engomadoPatrimonial"),
            @NamedQuery(name = "Vehiculo.findByUsuarioIns", query = "SELECT v FROM Vehiculo v WHERE v.usuarioIns = :usuarioIns"),
            @NamedQuery(name = "Vehiculo.findByFechains", query = "SELECT v FROM Vehiculo v WHERE v.fechaIns = :fechaIns"),
            @NamedQuery(name = "Vehiculo.findByEstatusRow", query = "SELECT v FROM Vehiculo v WHERE v.estatusRow = :estatusRow"),
            @NamedQuery(name = "Vehiculo.findByEstatus", query = "SELECT v FROM Vehiculo v WHERE v.estatus = :estatus")
        })
public class Vehiculo implements Serializable {

    @Column(name = "MODELO")
    private Integer modelo;
    @Column(name = "ESTATUS_ROW")
    private Integer estatusRow;
    @OneToMany(mappedBy = "vehiculo")
    private List<Credito> creditoList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "PLACA")
    private String placa;
    @Column(name = "VIN")
    private String vin;
    @Column(name = "FECHA_PROX_REV")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaProxRev;
    @Column(name = "ENGOMADO_PATRIMONIAL")
    private String engomadoPatrimonial;

    @JoinColumn(name = "VEHICULO_INFO_A", referencedColumnName = "ID")
    @ManyToOne
    private VehiculoInfoA vehiculoInfoA;
    @JoinColumn(name = "LINEA", referencedColumnName = "ID")
    @ManyToOne
    private Linea linea;
    @JoinColumn(name = "MARCA", referencedColumnName = "ID")
    @ManyToOne
    private Marca marca;
    @JoinColumn(name = "CLASE", referencedColumnName = "ID")
    @ManyToOne
    private VehClase clase;
    @JoinColumn(name = "TIPO", referencedColumnName = "ID")
    @ManyToOne
    private VehTipo tipo;
    @JoinColumn(name = "TIPO_SERVICIO", referencedColumnName = "ID")
    @ManyToOne
    private VehTipoServicio tipoServicio;
    @JoinColumn(name = "ESTATUS", referencedColumnName = "ID")
    @ManyToOne
    private Estatus estatus;

    @Column(name = "USUARIO_INS")
    private Integer usuarioIns;
    @Column(name = "FECHA_INS")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIns;
    @Transient
    private String fechafechaProxString;

    @OneToMany(mappedBy = "vehiculo")
    private List<VehPropietario> vehPropietarioList;
    @OneToMany(mappedBy = "vehiculo")
    private List<VehEmpresa> vehEmpresaList;
    @OneToMany(mappedBy = "vehiculo")
    private List<Revision> revisionList;
    @OneToMany(mappedBy = "vehiculo")
    private List<FlotaVehiculo> flotaVehiculoList;
    @OneToMany(mappedBy = "vehiculo")
    private List<Valoracion> valoracionList;
    @OneToMany(mappedBy = "vehiculo")
    private List<VehEquipo> vehEquipoList;

    public Vehiculo() {
    }

    public Vehiculo(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public Date getFechaProxRev() {
        return fechaProxRev;
    }

    public void setFechaProxRev(Date fechaProxRev) {
        this.fechaProxRev = fechaProxRev;
    }

    public String getEngomadoPatrimonial() {
        return engomadoPatrimonial;
    }

    public void setEngomadoPatrimonial(String engomadoPatrimonial) {
        this.engomadoPatrimonial = engomadoPatrimonial;
    }

    public Integer getUsuarioIns() {
        return usuarioIns;
    }

    public void setUsuarioIns(Integer usuarioIns) {
        this.usuarioIns = usuarioIns;
    }

    public Integer getEstatusRow() {
        return estatusRow;
    }

    public void setEstatusRow(Integer estatusRow) {
        this.estatusRow = estatusRow;
    }

    @XmlTransient
    public List<Revision> getRevisionList() {
        return revisionList;
    }

    public void setRevisionList(List<Revision> revisionList) {
        this.revisionList = revisionList;
    }

    public Linea getLinea() {
        return linea;
    }

    public void setLinea(Linea linea) {
        this.linea = linea;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public VehClase getClase() {
        return clase;
    }

    public void setClase(VehClase clase) {
        this.clase = clase;
    }

    public VehTipo getTipo() {
        return tipo;
    }

    public void setTipo(VehTipo tipo) {
        this.tipo = tipo;
    }

    public VehTipoServicio getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(VehTipoServicio tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    @XmlTransient
    public List<FlotaVehiculo> getFlotaVehiculoList() {
        return flotaVehiculoList;
    }

    public void setFlotaVehiculoList(List<FlotaVehiculo> flotaVehiculoList) {
        this.flotaVehiculoList = flotaVehiculoList;
    }

    @XmlTransient
    public List<Valoracion> getValoracionList() {
        return valoracionList;
    }

    public void setValoracionList(List<Valoracion> valoracionList) {
        this.valoracionList = valoracionList;
    }

    @XmlTransient
    public List<VehEquipo> getVehEquipoList() {
        return vehEquipoList;
    }

    public void setVehEquipoList(List<VehEquipo> vehEquipoList) {
        this.vehEquipoList = vehEquipoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vehiculo)) {
            return false;
        }
        Vehiculo other = (Vehiculo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gnv.business.ejb.modelo.Vehiculo[ id=" + id + " ]";
    }

    public Estatus getEstatus() {
        return estatus;
    }

    public void setEstatus(Estatus estatus) {
        this.estatus = estatus;
    }

    @XmlTransient
    public List<VehPropietario> getVehPropietarioList() {
        return vehPropietarioList;
    }

    public void setVehPropietarioList(List<VehPropietario> vehPropietarioList) {
        this.vehPropietarioList = vehPropietarioList;
    }

    @XmlTransient
    public List<VehEmpresa> getVehEmpresaList() {
        return vehEmpresaList;
    }

    public void setVehEmpresaList(List<VehEmpresa> vehEmpresaList) {
        this.vehEmpresaList = vehEmpresaList;
    }

    public Date getFechaIns() {
        return fechaIns;
    }

    public void setFechaIns(Date fechaIns) {
        this.fechaIns = fechaIns;
    }

    public VehiculoInfoA getVehiculoInfoA() {
        return vehiculoInfoA;
    }

    public void setVehiculoInfoA(VehiculoInfoA vehiculoInfoA) {
        this.vehiculoInfoA = vehiculoInfoA;
    }


    public String getFechafechaProxString() {
        if (fechaProxRev != null) {
            SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yy");
            String fechaFormato;
            fechaFormato = formateador.format(fechaProxRev);
            return fechaFormato;
        }
        return "";
    }

    public void setFechafechaProxString(String fechafechaProxString) {
        this.fechafechaProxString = fechafechaProxString;
    }

   
    @XmlTransient
    public List<Credito> getCreditoList()
    {
        return creditoList;
    }

    public void setCreditoList(List<Credito> creditoList)
    {
        this.creditoList = creditoList;
    }

    public Integer getModelo()
    {
        return modelo;
    }

    public void setModelo(Integer modelo)
    {
        this.modelo = modelo;
    }
    
    

}
