/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.VehClase;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author PC2
 */
@Local
public interface VehClaseFacadeLocal {

    void create(VehClase vehClase);

    void edit(VehClase vehClase);

    void remove(VehClase vehClase);

    VehClase find(Object id);

    List<VehClase> findAll();

    List<VehClase> findVehClase();

    List<VehClase> findRange(int[] range);

    int count();

}
