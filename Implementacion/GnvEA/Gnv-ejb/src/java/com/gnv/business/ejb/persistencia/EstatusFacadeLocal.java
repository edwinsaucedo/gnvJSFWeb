/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.Estatus;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Administrador
 */
@Local
public interface EstatusFacadeLocal {

    void create(Estatus estatus);

    void edit(Estatus estatus);

    void remove(Estatus estatus);

    Estatus find(Object id);

    List<Estatus> findAll();

    List<Estatus> findRange(int[] range);

    List<Estatus> findByClaseList(String nombreClase);

    int count();

    Estatus findByClase(String nombreClase, String nombreEstatus);
}
