/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.Propietario;
import com.gnv.business.ejb.modelo.VehPropietario;
import com.gnv.business.ejb.modelo.Vehiculo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Administrador
 */
@Local
public interface VehPropietarioFacadeLocal {

    void create(VehPropietario vehPropietario);

    void edit(VehPropietario vehPropietario);

    void remove(VehPropietario vehPropietario);

    VehPropietario find(Object id);

    VehPropietario findByPropietarioVehiculo(Propietario propietario, Vehiculo vehiculo);

    public List<VehPropietario> findByVehiculo(Vehiculo vehiculo);

    List<VehPropietario> findAll();

    List<VehPropietario> findRange(int[] range);

    List<VehPropietario> findByPropietario(Integer vehiculo, String nombreCompleto);

    VehPropietario findByPropietarioVehiculo(Integer vehiculo, String nombre, String apellido);

    int count();

}
