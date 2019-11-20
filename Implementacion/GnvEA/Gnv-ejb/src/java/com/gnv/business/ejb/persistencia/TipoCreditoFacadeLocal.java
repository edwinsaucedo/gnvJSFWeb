/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.TipoCredito;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author PC2
 */
@Local
public interface TipoCreditoFacadeLocal {

    void create(TipoCredito tipoCredito);

    void edit(TipoCredito tipoCredito);

    void remove(TipoCredito tipoCredito);

    TipoCredito find(Object id);

    List<TipoCredito> findAll();
    
    List<TipoCredito> findByEstatusRow();

    List<TipoCredito> findRange(int[] range);

    int count();
    
}
