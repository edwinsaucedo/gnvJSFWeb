/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.Cilindro;
import com.gnv.business.ejb.modelo.ConversionEquipo;
import com.gnv.business.ejb.modelo.Kit;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Administrador
 */
@Local
public interface ConversionEquipoFacadeLocal {

    void create(ConversionEquipo conversionEquipo);

    void edit(ConversionEquipo conversionEquipo);

    void remove(ConversionEquipo conversionEquipo);
    
    public int deleteByKit(Kit kit);
    
    public int deleteByCilindro(Cilindro cilindro);

    ConversionEquipo find(Object id);

    List<ConversionEquipo> findAll();

    List<ConversionEquipo> findRange(int[] range);

    int count();
    
}
