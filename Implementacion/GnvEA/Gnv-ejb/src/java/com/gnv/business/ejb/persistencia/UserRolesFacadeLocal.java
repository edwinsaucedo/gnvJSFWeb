/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.UserRoles;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Administrador
 */
@Local
public interface UserRolesFacadeLocal {

    void create(UserRoles userRoles);

    void edit(UserRoles userRoles);

    void remove(UserRoles userRoles);

    UserRoles find(Object id);

    List<UserRoles> findAll();
    
    public List<String> findRolesNamesByUser(String nickName);
    
    public UserRoles findByUserName(String nickName);

    List<UserRoles> findRange(int[] range);

    int count();
    
}
