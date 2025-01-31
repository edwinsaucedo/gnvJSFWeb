/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.Estatus;
import com.gnv.business.ejb.modelo.Usuario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Administrador
 */
@Local
public interface UsuarioFacadeLocal {

    void create(Usuario usuario);

    void edit(Usuario usuario);

    void remove(Usuario usuario);

    Usuario find(Object id);
    
    public Usuario findByClave(String clave,Estatus estatus);

    List<Usuario> findAll();

    List<Usuario> findRange(int[] range);
    
    public List<Usuario> findByEstatus(Estatus estatus,Estatus estatus2); 

    int count();
    
}
