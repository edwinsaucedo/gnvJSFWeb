/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.Estatus;
import com.gnv.business.ejb.modelo.Fabricante;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author PC2
 */
@Local
public interface FabricanteFacadeLocal {

    void create(Fabricante fabricante);

    void edit(Fabricante fabricante);

    void remove(Fabricante fabricante);

    Fabricante find(Object id);

    List<Fabricante> findAll();

    List<Fabricante> findByEstatusRow(Estatus estatus);

    List<Fabricante> findRange(int[] range);

    int count();

}
