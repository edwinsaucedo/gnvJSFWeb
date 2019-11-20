/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.Conversion;
import com.gnv.business.ejb.modelo.Kit;
import com.gnv.business.ejb.modelo.Legalizacion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author PC2
 */
@Local
public interface KitFacadeLocal {

    void create(Kit kit);

    void edit(Kit kit);

    void remove(Kit kit);

    Kit find(Object id);
    
    public Kit findKitBySerial(String serieKit);
    
    public List<Kit> findByConversion(Conversion conversion);

    List<Kit> findAll();

    public List<Kit> findByEstatusRow(Legalizacion legalizacion);
    

    List<Kit> findRange(int[] range);

    int count();

}
