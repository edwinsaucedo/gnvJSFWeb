/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.GrupoPermiso;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Administrador
 */
@Local
public interface GrupoPermisoFacadeLocal {

    void create(GrupoPermiso grupoPermiso);

    void edit(GrupoPermiso grupoPermiso);

    void remove(GrupoPermiso grupoPermiso);

    GrupoPermiso find(Object id);

    List<GrupoPermiso> findAll();

    List<GrupoPermiso> findRange(int[] range);
    
    public List<GrupoPermiso> findPadres();
    
    public List<GrupoPermiso> findHijos(GrupoPermiso grupoPermisoPadre);

    int count();
    
}
