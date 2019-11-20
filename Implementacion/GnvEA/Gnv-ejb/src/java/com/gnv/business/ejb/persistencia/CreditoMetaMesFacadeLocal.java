/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.CreditoMetaMes;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author PC2
 */
@Local
public interface CreditoMetaMesFacadeLocal {

    void create(CreditoMetaMes creditoMetaMes);

    void edit(CreditoMetaMes creditoMetaMes);

    void remove(CreditoMetaMes creditoMetaMes);

    CreditoMetaMes find(Object id);
    
    CreditoMetaMes findByCredito(Integer credito);

    List<CreditoMetaMes> findAll();

    List<CreditoMetaMes> findRange(int[] range);

    int count();
    
}
