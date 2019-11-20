/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.TipoLegalizacion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author US5
 */
@Local
public interface TipoLegalizacionFacadeLocal {

    void create(TipoLegalizacion tipoLegalizacion);

    void edit(TipoLegalizacion tipoLegalizacion);

    void remove(TipoLegalizacion tipoLegalizacion);

    TipoLegalizacion find(Object id);

    List<TipoLegalizacion> findAll();
    
    public List<TipoLegalizacion> findByEstatusRow();
    
    List<TipoLegalizacion> findRange(int[] range);

    int count();
    
}
