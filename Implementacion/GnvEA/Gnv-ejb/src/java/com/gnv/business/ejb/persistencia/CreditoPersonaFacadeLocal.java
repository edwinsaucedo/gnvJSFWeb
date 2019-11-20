/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.CreditoPersona;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author PC2
 */
@Local
public interface CreditoPersonaFacadeLocal {

    void create(CreditoPersona creditoPersona);

    void edit(CreditoPersona creditoPersona);

    void remove(CreditoPersona creditoPersona);

    CreditoPersona find(Object id);
    
     CreditoPersona findByCredito(Integer credito);

    List<CreditoPersona> findAll();

    List<CreditoPersona> findRange(int[] range);

    int count();
    
}
