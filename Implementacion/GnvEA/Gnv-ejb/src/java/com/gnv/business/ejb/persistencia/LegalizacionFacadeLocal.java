/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.Legalizacion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author PC2
 */
@Local
public interface LegalizacionFacadeLocal {

    void create(Legalizacion legalizacion);

    void edit(Legalizacion legalizacion);

    void remove(Legalizacion legalizacion);

    Legalizacion find(Object id);

    List<Legalizacion> findAll();

    List<Legalizacion> findByEstatusRow();

    List<Legalizacion> findRange(int[] range);

    int count();

}
