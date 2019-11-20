/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.view.backing.wraper;

import com.gnv.business.ejb.modelo.GrupoPermiso;
import com.gnv.business.ejb.modelo.Permiso;

/**
 *
 * @author Administrador
 */
public class GrupoPermisoWraper {
    private GrupoPermiso gp;
    private Permiso p;
    public GrupoPermisoWraper(){
    }

    public GrupoPermisoWraper(GrupoPermiso g, Permiso permiso) 
    {
        this.gp = g;
        if(null != (Object) permiso)
        {
            this.p = permiso;
        }
    }

    public GrupoPermiso getGp() {
        return gp;
    }

    public void setGp(GrupoPermiso gp) {
        this.gp = gp;
    }

    public Permiso getP() {
        return p;
    }

    public void setP(Permiso p) {
        this.p = p;
    }
}
