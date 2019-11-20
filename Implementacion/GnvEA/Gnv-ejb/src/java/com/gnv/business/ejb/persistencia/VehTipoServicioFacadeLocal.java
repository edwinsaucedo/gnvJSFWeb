/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.VehTipoServicio;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author PC2
 */
@Local
public interface VehTipoServicioFacadeLocal {

    void create(VehTipoServicio vehTipoServicio);

    void edit(VehTipoServicio vehTipoServicio);

    void remove(VehTipoServicio vehTipoServicio);

    VehTipoServicio find(Object id);
    
     public List<VehTipoServicio> findVehTipoServicio(); 

    List<VehTipoServicio> findAll();

    List<VehTipoServicio> findRange(int[] range);

    int count();
    
}
