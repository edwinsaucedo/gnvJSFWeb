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
@Table(name = "ROLES_PERMISSIONS")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "RolesPermissions.findAll", query = "SELECT r FROM RolesPermissions r"),
    @NamedQuery(name = "RolesPermissions.findByRolespermissionsid", query = "SELECT r FROM RolesPermissions r WHERE r.rolespermissionsid = :rolespermissionsid"),
    @NamedQuery(name = "RolesPermissions.findByRoleName", query = "SELECT r FROM RolesPermissions r WHERE r.roleName = :roleName"),
    @NamedQuery(name = "RolesPermissions.findByPermission", query = "SELECT r FROM RolesPermissions r WHERE r.permission = :permission")
})
public class RolesPermissions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROLESPERMISSIONSID")
    private Integer rolespermissionsid;
    @Column(name = "ROLE_NAME")
    private String roleName;
    @Column(name = "PERMISSION")
    private String permission;

    public RolesPermissions()
    {
    }

    public RolesPermissions(Integer rolespermissionsid)
    {
        this.rolespermissionsid = rolespermissionsid;
    }

    public Integer getRolespermissionsid()
    {
        return rolespermissionsid;
    }

    public void setRolespermissionsid(Integer rolespermissionsid)
    {
        this.rolespermissionsid = rolespermissionsid;
    }

    public String getRoleName()
    {
        return roleName;
    }

    public void setRoleName(String roleName)
    {
        this.roleName = roleName;
    }

    public String getPermission()
    {
        return permission;
    }

    public void setPermission(String permission)
    {
        this.permission = permission;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (rolespermissionsid != null ? rolespermissionsid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RolesPermissions))
        {
            return false;
        }
        RolesPermissions other = (RolesPermissions) object;
        if ((this.rolespermissionsid == null && other.rolespermissionsid != null) || (this.rolespermissionsid != null && !this.rolespermissionsid.equals(other.rolespermissionsid)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.gnv.business.ejb.modelo.RolesPermissions[ rolespermissionsid=" + rolespermissionsid + " ]";
    }
    
}
