/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.RolesPermissions;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Administrador
 */
@Local
public interface RolesPermissionsFacadeLocal {

    void create(RolesPermissions rolesPermissions);

    void edit(RolesPermissions rolesPermissions);

    void remove(RolesPermissions rolesPermissions);

    RolesPermissions find(Object id);
    
    public List<RolesPermissions> findByRol(String nombreRol);
    
    public Boolean existePermisoRol(String nombreRol, String permiso);     

    List<RolesPermissions> findAll();

    List<RolesPermissions> findRange(int[] range);

    int count();
    
}
