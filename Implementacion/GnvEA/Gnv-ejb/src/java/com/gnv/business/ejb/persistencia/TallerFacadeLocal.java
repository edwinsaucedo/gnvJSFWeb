/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.Compania;
import com.gnv.business.ejb.modelo.Taller;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author PC2
 */
@Local
public interface TallerFacadeLocal {

    void create(Taller taller);

    void edit(Taller taller);

    void remove(Taller taller);

    Taller find(Object id);

    List<Taller> findAll();

    List<Taller> findRange(int[] range);

    public List<Taller> findByEstatusRow();
    
     public List<Taller> findByCompania(Compania compania);
    

    int count();

}
