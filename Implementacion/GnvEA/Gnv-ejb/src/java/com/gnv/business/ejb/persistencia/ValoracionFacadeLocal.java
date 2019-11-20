/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.Valoracion;
import com.gnv.business.ejb.modelo.Vehiculo;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author PC2
 */
@Local
public interface ValoracionFacadeLocal {

    void create(Valoracion valoracion);

    void edit(Valoracion valoracion);

    void remove(Valoracion valoracion);

    Valoracion find(Object id);
    
    public Valoracion findByPlacaVehiculo(String placa);

    List<Valoracion> findAll();

    List<Valoracion> findValoracion();
    
    Valoracion findByVehiculo(Vehiculo vehiculo);

    List<Valoracion> findRange(int[] range);
    
    HashMap<String,Integer> findValoracionesMes();
    
    public List<Valoracion> findByPlacaVehiculo(Vehiculo vehiculo);

    int count();

}
