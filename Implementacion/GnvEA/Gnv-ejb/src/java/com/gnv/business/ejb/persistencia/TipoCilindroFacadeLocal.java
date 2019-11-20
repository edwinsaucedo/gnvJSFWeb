/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.TipoCilindro;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Administrador
 */
@Local
public interface TipoCilindroFacadeLocal {

    void create(TipoCilindro tipoCilindro);

    void edit(TipoCilindro tipoCilindro);

    void remove(TipoCilindro tipoCilindro);

    TipoCilindro find(Object id);

    List<TipoCilindro> findAll();
    
    public List<TipoCilindro> findByEstatusRow(); 
   
    List<TipoCilindro> findRange(int[] range);

    int count();
    
}
