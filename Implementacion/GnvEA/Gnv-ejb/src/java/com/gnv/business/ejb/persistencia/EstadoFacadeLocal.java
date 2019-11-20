/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.Estado;
import com.gnv.business.ejb.modelo.Pais;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Administrador
 */
@Local
public interface EstadoFacadeLocal {

    void create(Estado estado);

    void edit(Estado estado);

    void remove(Estado estado);

    Estado find(Object id);

    List<Estado> findAll();
    
    public List<Estado> findByPais(Pais pais);

    List<Estado> findRange(int[] range);

    int count();
    
}
