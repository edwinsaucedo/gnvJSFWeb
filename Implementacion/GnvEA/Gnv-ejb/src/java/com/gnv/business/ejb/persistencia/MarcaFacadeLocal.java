/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.Marca;
import com.gnv.business.ejb.modelo.TipoMarca;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Administrador
 */
@Local
public interface MarcaFacadeLocal {

    void create(Marca marca);

    void edit(Marca marca);

    void remove(Marca marca);

    Marca find(Object id);

    List<Marca> findAll();

    List<Marca> findRange(int[] range);
    
    public List<Marca> findByEstatusRow();
    
    public List<Marca> findByTipoMarca(TipoMarca tipoMarca);

    int count();
    
}
