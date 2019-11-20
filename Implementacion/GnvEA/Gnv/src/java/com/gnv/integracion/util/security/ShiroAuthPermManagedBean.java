/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.integracion.util.security;

import java.io.Serializable;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 *
 * @author _GEscalante
 */
@ApplicationScoped
@ManagedBean
public class ShiroAuthPermManagedBean implements Serializable {

    /**
     * Creates a new instance of ShiroAuthPermManagedBean
     */
    public ShiroAuthPermManagedBean()
    {
    }

    public boolean isPermitted(String permiso)
    {
        try
        {
            Subject subject = SecurityUtils.getSubject();
            boolean flag = subject.isPermitted(permiso);
            return flag;
        } catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

}
