/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.ServidorArchivos;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Administrador
 */
@Local
public interface ServidorArchivosFacadeLocal {

    void create(ServidorArchivos servidorArchivos);

    void edit(ServidorArchivos servidorArchivos);

    void remove(ServidorArchivos servidorArchivos);

    ServidorArchivos find(Object id);

    List<ServidorArchivos> findAll();

    List<ServidorArchivos> findRange(int[] range);

    int count();
    
}
