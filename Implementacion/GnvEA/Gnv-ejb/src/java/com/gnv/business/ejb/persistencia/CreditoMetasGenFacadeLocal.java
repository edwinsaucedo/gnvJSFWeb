/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.CreditoMetasGen;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author PC2
 */
@Local
public interface CreditoMetasGenFacadeLocal {

    void create(CreditoMetasGen creditoMetasGen);

    void edit(CreditoMetasGen creditoMetasGen);

    void remove(CreditoMetasGen creditoMetasGen);

    CreditoMetasGen find(Object id);

    CreditoMetasGen findByCredito(Integer credito);

    List<CreditoMetasGen> findAll();

    List<CreditoMetasGen> findRange(int[] range);

    int count();

}
