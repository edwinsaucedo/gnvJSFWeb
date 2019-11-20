/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.Linea;
import com.gnv.business.ejb.modelo.Marca;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Administrador
 */
@Local
public interface LineaFacadeLocal {

    void create(Linea linea);

    void edit(Linea linea);

    void remove(Linea linea);

    Linea find(Object id);

    List<Linea> findAll();
    
    public List<Linea> findLineasByMarca(Marca marca);

    List<Linea> findRange(int[] range);

    int count();
    
}
