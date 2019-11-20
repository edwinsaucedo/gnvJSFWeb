/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.TipoDocLegal;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author US5
 */
@Local
public interface TipoDocLegalFacadeLocal {

    void create(TipoDocLegal tipoDocLegal);

    void edit(TipoDocLegal tipoDocLegal);

    void remove(TipoDocLegal tipoDocLegal);

    TipoDocLegal find(Object id);

    List<TipoDocLegal> findAll();
    
    public List<TipoDocLegal> findByEstatusRow();
    
    List<TipoDocLegal> findRange(int[] range);

    int count();
    
}
