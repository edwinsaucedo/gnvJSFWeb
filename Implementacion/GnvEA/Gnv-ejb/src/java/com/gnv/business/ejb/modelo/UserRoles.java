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
@Table(name = "USER_ROLES")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "UserRoles.findAll", query = "SELECT u FROM UserRoles u"),
    @NamedQuery(name = "UserRoles.findByUserRoles", query = "SELECT u FROM UserRoles u WHERE u.userRoles = :userRoles"),
    @NamedQuery(name = "UserRoles.findByUsername", query = "SELECT u FROM UserRoles u WHERE u.username = :username"),
    @NamedQuery(name = "UserRoles.findByRoleName", query = "SELECT u FROM UserRoles u WHERE u.roleName = :roleName")
})
public class UserRoles implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ROLES")
    private Integer userRoles;
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "ROLE_NAME")
    private String roleName;

    public UserRoles()
    {
    }

    public UserRoles(Integer userRoles)
    {
        this.userRoles = userRoles;
    }

    public Integer getUserRoles()
    {
        return userRoles;
    }

    public void setUserRoles(Integer userRoles)
    {
        this.userRoles = userRoles;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getRoleName()
    {
        return roleName;
    }

    public void setRoleName(String roleName)
    {
        this.roleName = roleName;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (userRoles != null ? userRoles.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserRoles))
        {
            return false;
        }
        UserRoles other = (UserRoles) object;
        if ((this.userRoles == null && other.userRoles != null) || (this.userRoles != null && !this.userRoles.equals(other.userRoles)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.gnv.business.ejb.modelo.UserRoles[ userRoles=" + userRoles + " ]";
    }
    
}
