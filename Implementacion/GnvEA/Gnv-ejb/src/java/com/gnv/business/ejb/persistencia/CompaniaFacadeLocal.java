/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.Compania;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Administrador
 */
@Local
public interface CompaniaFacadeLocal {

    void create(Compania compania);

    void edit(Compania compania);

    void remove(Compania compania);

    Compania find(Object id);

    List<Compania> findAll();

    List<Compania> findRange(int[] range);

    int count();
    
}
