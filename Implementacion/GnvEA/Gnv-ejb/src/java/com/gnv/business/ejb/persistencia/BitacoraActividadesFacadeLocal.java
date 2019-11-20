/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.BitacoraActividades;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Administrador
 */
@Local
public interface BitacoraActividadesFacadeLocal {

    void create(BitacoraActividades bitacoraActividades);

    void edit(BitacoraActividades bitacoraActividades);

    void remove(BitacoraActividades bitacoraActividades);

    BitacoraActividades find(Object id);

    List<BitacoraActividades> findAll();
    
    List<BitacoraActividades> findByFecha(String fechaInicio, String fechaFin);

    List<BitacoraActividades> findRange(int[] range);

    int count();
    
}
