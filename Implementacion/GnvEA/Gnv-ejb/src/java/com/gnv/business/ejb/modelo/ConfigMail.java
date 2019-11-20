/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "CONFIG_MAIL")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "ConfigMail.findAll", query = "SELECT c FROM ConfigMail c"),
    @NamedQuery(name = "ConfigMail.findById", query = "SELECT c FROM ConfigMail c WHERE c.id = :id"),
    @NamedQuery(name = "ConfigMail.findByNombre", query = "SELECT c FROM ConfigMail c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "ConfigMail.findByUrlImagen", query = "SELECT c FROM ConfigMail c WHERE c.urlImagen = :urlImagen"),
    @NamedQuery(name = "ConfigMail.findByCadNot", query = "SELECT c FROM ConfigMail c WHERE c.cadNot = :cadNot"),
    @NamedQuery(name = "ConfigMail.findByMailSmtpHost", query = "SELECT c FROM ConfigMail c WHERE c.mailSmtpHost = :mailSmtpHost"),
    @NamedQuery(name = "ConfigMail.findByMailSmtpStarttlsEnable", query = "SELECT c FROM ConfigMail c WHERE c.mailSmtpStarttlsEnable = :mailSmtpStarttlsEnable"),
    @NamedQuery(name = "ConfigMail.findByMailSmtpPort", query = "SELECT c FROM ConfigMail c WHERE c.mailSmtpPort = :mailSmtpPort"),
    @NamedQuery(name = "ConfigMail.findByMailSmtpPuser", query = "SELECT c FROM ConfigMail c WHERE c.mailSmtpPuser = :mailSmtpPuser"),
    @NamedQuery(name = "ConfigMail.findByMailSmtpAuth", query = "SELECT c FROM ConfigMail c WHERE c.mailSmtpAuth = :mailSmtpAuth"),
    @NamedQuery(name = "ConfigMail.findByMailSmtpStarttlsReq", query = "SELECT c FROM ConfigMail c WHERE c.mailSmtpStarttlsReq = :mailSmtpStarttlsReq"),
    @NamedQuery(name = "ConfigMail.findByMailSmtpSslTrust", query = "SELECT c FROM ConfigMail c WHERE c.mailSmtpSslTrust = :mailSmtpSslTrust"),
    @NamedQuery(name = "ConfigMail.findByMailSmtpPassword", query = "SELECT c FROM ConfigMail c WHERE c.mailSmtpPassword = :mailSmtpPassword"),
    @NamedQuery(name = "ConfigMail.findByMailSmtpFrom", query = "SELECT c FROM ConfigMail c WHERE c.mailSmtpFrom = :mailSmtpFrom"),
    @NamedQuery(name = "ConfigMail.findByMailSmtpTimeout", query = "SELECT c FROM ConfigMail c WHERE c.mailSmtpTimeout = :mailSmtpTimeout"),
    @NamedQuery(name = "ConfigMail.findByMailSmtpConntimeout", query = "SELECT c FROM ConfigMail c WHERE c.mailSmtpConntimeout = :mailSmtpConntimeout"),
    @NamedQuery(name = "ConfigMail.findByActiva", query = "SELECT c FROM ConfigMail c WHERE c.activa = :activa")
})
public class ConfigMail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "URL_IMAGEN")
    private String urlImagen;
    @Column(name = "CAD_NOT")
    private String cadNot;
    @Column(name = "MAIL_SMTP_HOST")
    private String mailSmtpHost;
    @Column(name = "MAIL_SMTP_STARTTLS_ENABLE")
    private String mailSmtpStarttlsEnable;
    @Column(name = "MAIL_SMTP_PORT")
    private Integer mailSmtpPort;
    @Column(name = "MAIL_SMTP_PUSER")
    private String mailSmtpPuser;
    @Column(name = "MAIL_SMTP_AUTH")
    private String mailSmtpAuth;
    @Column(name = "MAIL_SMTP_STARTTLS_REQ")
    private String mailSmtpStarttlsReq;
    @Column(name = "MAIL_SMTP_SSL_TRUST")
    private String mailSmtpSslTrust;
    @Column(name = "MAIL_SMTP_PASSWORD")
    private String mailSmtpPassword;
    @Column(name = "MAIL_SMTP_FROM")
    private String mailSmtpFrom;
    @Column(name = "MAIL_SMTP_TIMEOUT")
    private String mailSmtpTimeout;
    @Column(name = "MAIL_SMTP_CONNTIMEOUT")
    private String mailSmtpConntimeout;
    @Column(name = "ACTIVA")
    private Boolean activa;

    public ConfigMail()
    {
    }

    public ConfigMail(Integer id)
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

    public String getUrlImagen()
    {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen)
    {
        this.urlImagen = urlImagen;
    }

    public String getCadNot()
    {
        return cadNot;
    }

    public void setCadNot(String cadNot)
    {
        this.cadNot = cadNot;
    }

    public String getMailSmtpHost()
    {
        return mailSmtpHost;
    }

    public void setMailSmtpHost(String mailSmtpHost)
    {
        this.mailSmtpHost = mailSmtpHost;
    }

    public String getMailSmtpStarttlsEnable()
    {
        return mailSmtpStarttlsEnable;
    }

    public void setMailSmtpStarttlsEnable(String mailSmtpStarttlsEnable)
    {
        this.mailSmtpStarttlsEnable = mailSmtpStarttlsEnable;
    }

    public Integer getMailSmtpPort()
    {
        return mailSmtpPort;
    }

    public void setMailSmtpPort(Integer mailSmtpPort)
    {
        this.mailSmtpPort = mailSmtpPort;
    }

    public String getMailSmtpPuser()
    {
        return mailSmtpPuser;
    }

    public void setMailSmtpPuser(String mailSmtpPuser)
    {
        this.mailSmtpPuser = mailSmtpPuser;
    }

    public String getMailSmtpAuth()
    {
        return mailSmtpAuth;
    }

    public void setMailSmtpAuth(String mailSmtpAuth)
    {
        this.mailSmtpAuth = mailSmtpAuth;
    }

    public String getMailSmtpStarttlsReq()
    {
        return mailSmtpStarttlsReq;
    }

    public void setMailSmtpStarttlsReq(String mailSmtpStarttlsReq)
    {
        this.mailSmtpStarttlsReq = mailSmtpStarttlsReq;
    }

    public String getMailSmtpSslTrust()
    {
        return mailSmtpSslTrust;
    }

    public void setMailSmtpSslTrust(String mailSmtpSslTrust)
    {
        this.mailSmtpSslTrust = mailSmtpSslTrust;
    }

    public String getMailSmtpPassword()
    {
        return mailSmtpPassword;
    }

    public void setMailSmtpPassword(String mailSmtpPassword)
    {
        this.mailSmtpPassword = mailSmtpPassword;
    }

    public String getMailSmtpFrom()
    {
        return mailSmtpFrom;
    }

    public void setMailSmtpFrom(String mailSmtpFrom)
    {
        this.mailSmtpFrom = mailSmtpFrom;
    }

    public String getMailSmtpTimeout()
    {
        return mailSmtpTimeout;
    }

    public void setMailSmtpTimeout(String mailSmtpTimeout)
    {
        this.mailSmtpTimeout = mailSmtpTimeout;
    }

    public String getMailSmtpConntimeout()
    {
        return mailSmtpConntimeout;
    }

    public void setMailSmtpConntimeout(String mailSmtpConntimeout)
    {
        this.mailSmtpConntimeout = mailSmtpConntimeout;
    }

    public Boolean getActiva()
    {
        return activa;
    }

    public void setActiva(Boolean activa)
    {
        this.activa = activa;
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
        if (!(object instanceof ConfigMail))
        {
            return false;
        }
        ConfigMail other = (ConfigMail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.gnv.business.ejb.modelo.ConfigMail[ id=" + id + " ]";
    }
    
}
