/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.Credito;
import com.gnv.business.ejb.modelo.CreditoEds;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author PC2
 */
@Local
public interface CreditoEdsFacadeLocal {

    void create(CreditoEds creditoEds);

    void edit(CreditoEds creditoEds);

    void remove(CreditoEds creditoEds);

    CreditoEds find(Object id);

    List<CreditoEds> findAll();
    
     List<CreditoEds> findByCredito(Credito credito);

    CreditoEds findByCreditoEds(Integer credito, Integer eds);

    List<CreditoEds> findRange(int[] range

    );

    int count();

}
