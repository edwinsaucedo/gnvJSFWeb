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
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "LEGALIZACION")
@XmlRootElement
@NamedQueries(
        {
            @NamedQuery(name = "Legalizacion.findAll", query = "SELECT l FROM Legalizacion l"),
            @NamedQuery(name = "Legalizacion.findById", query = "SELECT l FROM Legalizacion l WHERE l.id = :id"),
            @NamedQuery(name = "Legalizacion.findByNumeroLegal", query = "SELECT l FROM Legalizacion l WHERE l.numeroLegal = :numeroLegal"),
            @NamedQuery(name = "Legalizacion.findByNumeroDoc", query = "SELECT l FROM Legalizacion l WHERE l.numeroDoc = :numeroDoc"),
            @NamedQuery(name = "Legalizacion.findByNumeroMotor", query = "SELECT l FROM Legalizacion l WHERE l.numeroMotor = :numeroMotor"),
            @NamedQuery(name = "Legalizacion.findByInformacionRegulador", query = "SELECT l FROM Legalizacion l WHERE l.informacionRegulador = :informacionRegulador"),
            @NamedQuery(name = "Legalizacion.findByCncdiridn", query = "SELECT l FROM Legalizacion l WHERE l.cncdiridn = :cncdiridn"),
            @NamedQuery(name = "Legalizacion.findByUsuarioIns", query = "SELECT l FROM Legalizacion l WHERE l.usuarioIns = :usuarioIns"),
            @NamedQuery(name = "Legalizacion.findByFechaIns", query = "SELECT l FROM Legalizacion l WHERE l.fechaIns = :fechaIns"),
            @NamedQuery(name = "Legalizacion.findByEstatusRow", query = "SELECT l FROM Legalizacion l WHERE l.estatusRow = :estatusRow")
        })
public class Legalizacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NUMERO_LEGAL")
    private String numeroLegal;
    @Column(name = "NUMERO_DOC")
    private String numeroDoc;
    @Column(name = "NUMERO_MOTOR")
    private String numeroMotor;
    @Column(name = "INFORMACION_REGULADOR")
    private String informacionRegulador;
    @Column(name = "CNCDIRIDN")
    private Long cncdiridn;
    @Column(name = "USUARIO_INS")
    private Integer usuarioIns;
    @Column(name = "FECHA_INS")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIns;
    @Column(name = "ESTATUS_ROW")
    private Integer estatusRow;
    @JoinColumn(name = "TIPO_DOCUMENTO", referencedColumnName = "ID")
    @ManyToOne
    private TipoDocLegal tipoDocumento;
    @JoinColumn(name = "TIPO_LEGALIZACION", referencedColumnName = "ID")
    @ManyToOne
    private TipoLegalizacion tipoLegalizacion;
    @OneToMany(mappedBy = "legalizacion")
    private List<Kit> kitList;
    @OneToMany(mappedBy = "legalizacion")
    private List<Cilindro> cilindroList;
    @Transient
    private String dateString;

    public Legalizacion() {
    }

    public Legalizacion(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumeroLegal() {
        return numeroLegal;
    }

    public void setNumeroLegal(String numeroLegal) {
        this.numeroLegal = numeroLegal;
    }

    public String getNumeroDoc() {
        return numeroDoc;
    }

    public void setNumeroDoc(String numeroDoc) {
        this.numeroDoc = numeroDoc;
    }

    public String getNumeroMotor() {
        return numeroMotor;
    }

    public void setNumeroMotor(String numeroMotor) {
        this.numeroMotor = numeroMotor;
    }

    public String getInformacionRegulador() {
        return informacionRegulador;
    }

    public void setInformacionRegulador(String informacionRegulador) {
        this.informacionRegulador = informacionRegulador;
    }

    public Long getCncdiridn() {
        return cncdiridn;
    }

    public void setCncdiridn(Long cncdiridn) {
        this.cncdiridn = cncdiridn;
    }

    public Integer getUsuarioIns() {
        return usuarioIns;
    }

    public void setUsuarioIns(Integer usuarioIns) {
        this.usuarioIns = usuarioIns;
    }

    public Date getFechaIns() {
        return fechaIns;
    }

    public void setFechaIns(Date fechaIns) {
        this.fechaIns = fechaIns;
    }

    public Integer getEstatusRow() {
        return estatusRow;
    }

    public void setEstatusRow(Integer estatusRow) {
        this.estatusRow = estatusRow;
    }

    public TipoDocLegal getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocLegal tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public TipoLegalizacion getTipoLegalizacion() {
        return tipoLegalizacion;
    }

    public void setTipoLegalizacion(TipoLegalizacion tipoLegalizacion) {
        this.tipoLegalizacion = tipoLegalizacion;
    }

    @XmlTransient
    public List<Kit> getKitList() {
        return kitList;
    }

    public void setKitList(List<Kit> kitList) {
        this.kitList = kitList;
    }

    @XmlTransient
    public List<Cilindro> getCilindroList() {
        return cilindroList;
    }

    public void setCilindroList(List<Cilindro> cilindroList) {
        this.cilindroList = cilindroList;
    }

    public String getDateString() {
        if (fechaIns != null) {
            SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yy HH:mm");
            String fechaFormato;
            fechaFormato = formateador.format(fechaIns);
            return fechaFormato;
        }
        return "";
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
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
        if (!(object instanceof Legalizacion)) {
            return false;
        }
        Legalizacion other = (Legalizacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gnv.business.ejb.modelo.Legalizacion[ id=" + id + " ]";
    }

}
