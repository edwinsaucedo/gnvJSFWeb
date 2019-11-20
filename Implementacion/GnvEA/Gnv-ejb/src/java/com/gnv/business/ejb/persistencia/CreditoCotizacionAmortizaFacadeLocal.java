/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.CreditoCotizacion;
import com.gnv.business.ejb.modelo.CreditoCotizacionAmortiza;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author PC2
 */
@Local
public interface CreditoCotizacionAmortizaFacadeLocal {

    void create(CreditoCotizacionAmortiza creditoCotizacionAmortiza);

    void edit(CreditoCotizacionAmortiza creditoCotizacionAmortiza);

    void remove(CreditoCotizacionAmortiza creditoCotizacionAmortiza);

    CreditoCotizacionAmortiza find(Object id);

    List<CreditoCotizacionAmortiza> findAll();

    List<CreditoCotizacionAmortiza> findByEstatusRow(CreditoCotizacion creditoCotizacion);

    List<CreditoCotizacionAmortiza> findRange(int[] range);

    int count();

}
