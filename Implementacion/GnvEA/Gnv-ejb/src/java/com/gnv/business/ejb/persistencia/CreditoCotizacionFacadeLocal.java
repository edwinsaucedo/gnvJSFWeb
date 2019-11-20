/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.CreditoCotizacion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author PC2
 */
@Local
public interface CreditoCotizacionFacadeLocal {

    void create(CreditoCotizacion creditoCotizacion);

    void edit(CreditoCotizacion creditoCotizacion);

    void remove(CreditoCotizacion creditoCotizacion);

    CreditoCotizacion find(Object id);

    List<CreditoCotizacion> findAll();

    List<CreditoCotizacion> findByEstatusRow();

    List<CreditoCotizacion> findRange(int[] range);

    int count();

}
