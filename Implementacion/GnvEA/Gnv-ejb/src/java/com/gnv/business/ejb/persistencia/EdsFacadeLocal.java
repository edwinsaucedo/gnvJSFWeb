/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.Eds;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author PC2
 */
@Local
public interface EdsFacadeLocal {

    void create(Eds eds);

    void edit(Eds eds);

    void remove(Eds eds);

    Eds find(Object id);

    List<Eds> findAll();

    List<Eds> findByEstatusRow();

    List<Eds> findRange(int[] range);

    int count();

}
