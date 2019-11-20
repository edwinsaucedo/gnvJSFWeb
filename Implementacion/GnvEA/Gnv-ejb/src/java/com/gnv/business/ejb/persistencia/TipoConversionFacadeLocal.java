/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.TipoConversion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Administrador
 */
@Local
public interface TipoConversionFacadeLocal {

    void create(TipoConversion tipoConversion);

    void edit(TipoConversion tipoConversion);

    void remove(TipoConversion tipoConversion);

    TipoConversion find(Object id);

    List<TipoConversion> findAll();

    List<TipoConversion> findRange(int[] range);
    
    public List<TipoConversion> findByEstatusRow();

    int count();
    
}
