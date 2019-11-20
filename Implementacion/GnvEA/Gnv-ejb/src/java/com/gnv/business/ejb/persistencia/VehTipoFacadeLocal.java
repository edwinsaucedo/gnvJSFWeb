/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.VehTipo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author PC2
 */
@Local
public interface VehTipoFacadeLocal {

    void create(VehTipo vehTipo);

    void edit(VehTipo vehTipo);

    void remove(VehTipo vehTipo);

    VehTipo find(Object id);

    List<VehTipo> findAll();

    List<VehTipo> findVehTipo();

    List<VehTipo> findRange(int[] range);

    int count();

}
