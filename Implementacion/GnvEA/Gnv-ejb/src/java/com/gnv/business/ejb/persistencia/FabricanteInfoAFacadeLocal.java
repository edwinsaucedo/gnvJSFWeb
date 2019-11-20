/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.FabricanteInfoA;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author PC2
 */
@Local
public interface FabricanteInfoAFacadeLocal {

    void create(FabricanteInfoA fabricanteInfoA);

    void edit(FabricanteInfoA fabricanteInfoA);

    void remove(FabricanteInfoA fabricanteInfoA);

    FabricanteInfoA find(Object id);

    List<FabricanteInfoA> findAll();

    List<FabricanteInfoA> findRange(int[] range);

    int count();
    
}
