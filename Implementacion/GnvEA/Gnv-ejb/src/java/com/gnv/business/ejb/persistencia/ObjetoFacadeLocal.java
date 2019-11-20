/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.Objeto;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Administrador
 */
@Local
public interface ObjetoFacadeLocal {

    void create(Objeto objeto);

    void edit(Objeto objeto);

    void remove(Objeto objeto);

    Objeto find(Object id);
    
    public Objeto findByNombre(String nombre);

    List<Objeto> findAll();

    List<Objeto> findRange(int[] range);

    int count();
    
}
