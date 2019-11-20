/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.modelo;

import java.io.Serializable;
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
@Table(name = "CREDITO")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Credito.findAll", query = "SELECT c FROM Credito c"),
    @NamedQuery(name = "Credito.findById", query = "SELECT c FROM Credito c WHERE c.id = :id"),
    @NamedQuery(name = "Credito.findByNumero", query = "SELECT c FROM Credito c WHERE c.numero = :numero"),
    @NamedQuery(name = "Credito.findByDigitoV", query = "SELECT c FROM Credito c WHERE c.digitoV = :digitoV"),
    @NamedQuery(name = "Credito.findByTipoCredito", query = "SELECT c FROM Credito c WHERE c.tipoCredito = :tipoCredito"),
    @NamedQuery(name = "Credito.findByFechaAut", query = "SELECT c FROM Credito c WHERE c.fechaAut = :fechaAut"),
    @NamedQuery(name = "Credito.findByFechaDes", query = "SELECT c FROM Credito c WHERE c.fechaDes = :fechaDes"),
    @NamedQuery(name = "Credito.findByEstadoCredito", query = "SELECT c FROM Credito c WHERE c.estadoCredito = :estadoCredito"),
    @NamedQuery(name = "Credito.findByTipoRecaudo", query = "SELECT c FROM Credito c WHERE c.tipoRecaudo = :tipoRecaudo"),
    @NamedQuery(name = "Credito.findByBaseRecaudo", query = "SELECT c FROM Credito c WHERE c.baseRecaudo = :baseRecaudo"),
    @NamedQuery(name = "Credito.findByConsumoMinimo", query = "SELECT c FROM Credito c WHERE c.consumoMinimo = :consumoMinimo"),
    @NamedQuery(name = "Credito.findByPlazo", query = "SELECT c FROM Credito c WHERE c.plazo = :plazo"),
    @NamedQuery(name = "Credito.findByFormaPagoRec", query = "SELECT c FROM Credito c WHERE c.formaPagoRec = :formaPagoRec"),
    @NamedQuery(name = "Credito.findByMontoSolicitado", query = "SELECT c FROM Credito c WHERE c.montoSolicitado = :montoSolicitado"),
    @NamedQuery(name = "Credito.findByAnticipo", query = "SELECT c FROM Credito c WHERE c.anticipo = :anticipo"),
    @NamedQuery(name = "Credito.findByInteres", query = "SELECT c FROM Credito c WHERE c.interes = :interes"),
    @NamedQuery(name = "Credito.findByMontoCredito", query = "SELECT c FROM Credito c WHERE c.montoCredito = :montoCredito"),
    @NamedQuery(name = "Credito.findByMetas", query = "SELECT c FROM Credito c WHERE c.metas = :metas"),
    @NamedQuery(name = "Credito.findByEstatusRow", query = "SELECT c FROM Credito c WHERE c.estatusRow = :estatusRow"),
    @NamedQuery(name = "Credito.findByUsuariosIns", query = "SELECT c FROM Credito c WHERE c.usuariosIns = :usuariosIns"),
    @NamedQuery(name = "Credito.findByFechaIns", query = "SELECT c FROM Credito c WHERE c.fechaIns = :fechaIns")
})
public class Credito implements Serializable {

    @JoinColumn(name = "TIPO_CREDITO", referencedColumnName = "ID")
    @ManyToOne
    private TipoCredito tipoCredito;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NUMERO")
    private String numero;
    @Column(name = "DIGITO_V")
    private String digitoV;
    @Column(name = "FECHA_AUT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAut;
    @Column(name = "FECHA_DES")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDes;
    @Column(name = "ESTADO_CREDITO")
    private Integer estadoCredito;
    @Column(name = "TIPO_RECAUDO")
    private Integer tipoRecaudo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "BASE_RECAUDO")
    private Double baseRecaudo;
    @Column(name = "CONSUMO_MINIMO")
    private Double consumoMinimo;
    @Column(name = "PLAZO")
    private Integer plazo;
    @Column(name = "FORMA_PAGO_REC")
    private Integer formaPagoRec;
    @Column(name = "MONTO_SOLICITADO")
    private Double montoSolicitado;
    @Column(name = "ANTICIPO")
    private Double anticipo;
    @Column(name = "INTERES")
    private Double interes;
    @Column(name = "MONTO_CREDITO")
    private Double montoCredito;
    @Column(name = "METAS")
    private boolean metas;
    @Column(name = "ESTATUS_ROW")
    private Integer estatusRow;
    @Column(name = "USUARIOS_INS")
    private Integer usuariosIns;
    @Column(name = "FECHA_INS")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIns;
    @OneToMany(mappedBy = "credito")
    private List<CreditoMetaMes> creditoMetaMesList;
    @OneToMany(mappedBy = "credito")
    private List<CreditoPersona> creditoPersonaList;
    @OneToMany(mappedBy = "credito")
    private List<CreditoMetasGen> creditoMetasGenList;
    @JoinColumn(name = "FINANCIERA", referencedColumnName = "ID")
    @ManyToOne
    private Financiera financiera;
    @JoinColumn(name = "TALLER", referencedColumnName = "ID")
    @ManyToOne
    private Taller taller;
    @JoinColumn(name = "VEHICULO", referencedColumnName = "ID")
    @ManyToOne
    private Vehiculo vehiculo;
    @OneToMany(mappedBy = "credito")
    private List<CreditoEds> creditoEdsList;

    public Credito()
    {
    }

    public Credito(Integer id)
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

    public String getNumero()
    {
        return numero;
    }

    public void setNumero(String numero)
    {
        this.numero = numero;
    }

    public String getDigitoV()
    {
        return digitoV;
    }

    public void setDigitoV(String digitoV)
    {
        this.digitoV = digitoV;
    }

    public Date getFechaAut()
    {
        return fechaAut;
    }

    public void setFechaAut(Date fechaAut)
    {
        this.fechaAut = fechaAut;
    }

    public Date getFechaDes()
    {
        return fechaDes;
    }

    public void setFechaDes(Date fechaDes)
    {
        this.fechaDes = fechaDes;
    }

    public Integer getEstadoCredito()
    {
        return estadoCredito;
    }

    public void setEstadoCredito(Integer estadoCredito)
    {
        this.estadoCredito = estadoCredito;
    }

    public Integer getTipoRecaudo()
    {
        return tipoRecaudo;
    }

    public void setTipoRecaudo(Integer tipoRecaudo)
    {
        this.tipoRecaudo = tipoRecaudo;
    }

    public Double getBaseRecaudo()
    {
        return baseRecaudo;
    }

    public void setBaseRecaudo(Double baseRecaudo)
    {
        this.baseRecaudo = baseRecaudo;
    }

    public Double getConsumoMinimo()
    {
        return consumoMinimo;
    }

    public void setConsumoMinimo(Double consumoMinimo)
    {
        this.consumoMinimo = consumoMinimo;
    }

    public Integer getPlazo()
    {
        return plazo;
    }

    public void setPlazo(Integer plazo)
    {
        this.plazo = plazo;
    }

    public Integer getFormaPagoRec()
    {
        return formaPagoRec;
    }

    public void setFormaPagoRec(Integer formaPagoRec)
    {
        this.formaPagoRec = formaPagoRec;
    }

    public Double getMontoSolicitado()
    {
        return montoSolicitado;
    }

    public void setMontoSolicitado(Double montoSolicitado)
    {
        this.montoSolicitado = montoSolicitado;
    }

    public Double getAnticipo()
    {
        return anticipo;
    }

    public void setAnticipo(Double anticipo)
    {
        this.anticipo = anticipo;
    }

    public Double getInteres()
    {
        return interes;
    }

    public void setInteres(Double interes)
    {
        this.interes = interes;
    }

    public Double getMontoCredito()
    {
        return montoCredito;
    }

    public void setMontoCredito(Double montoCredito)
    {
        this.montoCredito = montoCredito;
    }

    public boolean isMetas()
    {
        return metas;
    }

    public void setMetas(boolean metas)
    {
        this.metas = metas;
    }


    public Integer getEstatusRow()
    {
        return estatusRow;
    }

    public void setEstatusRow(Integer estatusRow)
    {
        this.estatusRow = estatusRow;
    }

    public Integer getUsuariosIns()
    {
        return usuariosIns;
    }

    public void setUsuariosIns(Integer usuariosIns)
    {
        this.usuariosIns = usuariosIns;
    }

    public Date getFechaIns()
    {
        return fechaIns;
    }

    public void setFechaIns(Date fechaIns)
    {
        this.fechaIns = fechaIns;
    }

    @XmlTransient
    public List<CreditoMetaMes> getCreditoMetaMesList()
    {
        return creditoMetaMesList;
    }

    public void setCreditoMetaMesList(List<CreditoMetaMes> creditoMetaMesList)
    {
        this.creditoMetaMesList = creditoMetaMesList;
    }

    @XmlTransient
    public List<CreditoPersona> getCreditoPersonaList()
    {
        return creditoPersonaList;
    }

    public void setCreditoPersonaList(List<CreditoPersona> creditoPersonaList)
    {
        this.creditoPersonaList = creditoPersonaList;
    }

    @XmlTransient
    public List<CreditoMetasGen> getCreditoMetasGenList()
    {
        return creditoMetasGenList;
    }

    public void setCreditoMetasGenList(List<CreditoMetasGen> creditoMetasGenList)
    {
        this.creditoMetasGenList = creditoMetasGenList;
    }

    public Financiera getFinanciera()
    {
        return financiera;
    }

    public void setFinanciera(Financiera financiera)
    {
        this.financiera = financiera;
    }

    public Taller getTaller()
    {
        return taller;
    }

    public void setTaller(Taller taller)
    {
        this.taller = taller;
    }

    public Vehiculo getVehiculo()
    {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo)
    {
        this.vehiculo = vehiculo;
    }

    @XmlTransient
    public List<CreditoEds> getCreditoEdsList()
    {
        return creditoEdsList;
    }

    public void setCreditoEdsList(List<CreditoEds> creditoEdsList)
    {
        this.creditoEdsList = creditoEdsList;
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
        if (!(object instanceof Credito))
        {
            return false;
        }
        Credito other = (Credito) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.gnv.business.ejb.modelo.Credito[ id=" + id + " ]";
    }

    public TipoCredito getTipoCredito()
    {
        return tipoCredito;
    }

    public void setTipoCredito(TipoCredito tipoCredito)
    {
        this.tipoCredito = tipoCredito;
    }
    
}
