/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.Conversion;
import com.gnv.business.ejb.modelo.Estatus;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Administrador
 */
@Local
public interface ConversionFacadeLocal {

    void create(Conversion conversion);

    void edit(Conversion conversion);

    void remove(Conversion conversion);

    Conversion find(Object id);

    Conversion findByVehiculo(Integer vehiculo);

    List<Conversion> findAll();

    List<Conversion> findByDate();

    List<Conversion> findRange(int[] range);

    List<Conversion> findByEstatus(Estatus estatus);
    
    HashMap<String,Integer> findCoversionesMes();
    
    int findKitInstaladosAnio(Estatus estatus);

    int count();

}
