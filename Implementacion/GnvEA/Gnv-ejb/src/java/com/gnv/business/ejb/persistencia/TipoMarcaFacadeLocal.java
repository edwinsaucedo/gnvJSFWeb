/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;
import com.gnv.business.ejb.modelo.TipoMarca;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author PC2
 */
@Local
public interface TipoMarcaFacadeLocal {

    void create(TipoMarca tipoMarca);

    void edit(TipoMarca tipoMarca);

    void remove(TipoMarca tipoMarca);

    TipoMarca find(Object id);

    List<TipoMarca> findAll();
    
    List<TipoMarca> findRange(int[] range);
    
    public TipoMarca findByTipoMarcaNombre(String nombre);
    
    int count();
    
}
