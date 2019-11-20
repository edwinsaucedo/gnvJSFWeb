/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.view.backing.comparator;

import com.gnv.business.ejb.modelo.Permiso;
import java.util.Comparator;

/**
 *
 * @author Administrador
 */
public class PermisoComparator implements Comparator<Permiso> {

    @Override
    public int compare(Permiso t, Permiso t2)
    {
        try {
            return t.getOrden().compareTo(t2.getOrden());
        } catch (NullPointerException npe){
            if(t.getOrden() == null && t2.getOrden()!= null)
                return 1;
            if(t.getOrden()!= null && t2.getOrden() == null)
                return -1;
            if(t.getOrden() == null && t2.getOrden() == null)
                return 0;
            return 0;
        }
    }
    
}
