/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.VehiculoInfoA;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Administrador
 */
@Local
public interface VehiculoInfoAFacadeLocal {

    void create(VehiculoInfoA vehiculoInfoA);

    void edit(VehiculoInfoA vehiculoInfoA);

    void remove(VehiculoInfoA vehiculoInfoA);

    VehiculoInfoA find(Object id);

    List<VehiculoInfoA> findAll();

    List<VehiculoInfoA> findRange(int[] range);

    int count();
    
}
