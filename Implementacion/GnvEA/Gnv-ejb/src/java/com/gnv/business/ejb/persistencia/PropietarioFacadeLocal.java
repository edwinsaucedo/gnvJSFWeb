/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.Propietario;
import com.gnv.business.ejb.modelo.Vehiculo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Administrador
 */
@Local
public interface PropietarioFacadeLocal {

    void create(Propietario propietario);

    void edit(Propietario propietario);

    void remove(Propietario propietario);

    Propietario find(Object id);

    List<Propietario> findAll();

    List<Propietario> findRange(int[] range);
    
    List<Propietario> findByEstatusRow();
    
    int count();
    
}
