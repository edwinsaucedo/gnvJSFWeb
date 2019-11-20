/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.Vehiculo;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Administrador
 */
@Local
public interface VehiculoFacadeLocal {

    void create(Vehiculo vehiculo);

    void edit(Vehiculo vehiculo);

    void remove(Vehiculo vehiculo);

    Vehiculo find(Object id);

    public Vehiculo findbyPlaca(String placa);

    public Vehiculo findbyVin(String vin);

    public Vehiculo findbyPlacaVin(String placa, String vin);

    List<Vehiculo> findAll();

    List<Vehiculo> findByPlacaList(String placa);

    List<Vehiculo> findByVehiculo(Integer vehiculo);

    List<Vehiculo> findByVinList(String vin);

    List<Vehiculo> findByVinPlacaList(String vin, String placa);

    List<Vehiculo> findRange(int[] range);

    public List<Vehiculo> findByStatus();

    List<Vehiculo> findByCredit(String vin, String placa);

    List<Vehiculo> findByCreditPlaca(String placa);

    List<Vehiculo> findByCreditVin(String vin);

    Vehiculo findByCreditoValoracionVin(String vin);
    
        Vehiculo findByCreditoValoracionVinPlaca(String vin,String placa);
    
    Vehiculo findByCreditoValoracionPlaca(String placa);

    List<Vehiculo> findByDate();

    int count();

}
