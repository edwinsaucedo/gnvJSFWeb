/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.Estatus;
import com.gnv.business.ejb.modelo.Revision;
import com.gnv.business.ejb.modelo.Vehiculo;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author PC2
 */
@Local
public interface RevisionFacadeLocal {

    void create(Revision revision);

    void edit(Revision revision);

    void remove(Revision revision);

    Revision find(Object id);
    
     Revision findByVehiculo(Vehiculo vehiculo);

    List<Revision> findAll();

    List<Revision> findByRevision();

    List<Revision> findRange(int[] range);
    
     HashMap<String,Integer> findRevisionesMes();

    int count();

}
