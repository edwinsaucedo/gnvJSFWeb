/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.Financiera;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author PC2
 */
@Local
public interface FinancieraFacadeLocal {

    void create(Financiera financiera);

    void edit(Financiera financiera);

    void remove(Financiera financiera);

    Financiera find(Object id);

    List<Financiera> findAll();

    List<Financiera> findByEstatusRow();

    List<Financiera> findRange(int[] range);

    int count();

}
