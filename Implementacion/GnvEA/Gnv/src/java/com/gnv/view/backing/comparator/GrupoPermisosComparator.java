/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.view.backing.comparator;

import com.gnv.business.ejb.modelo.GrupoPermiso;
import java.util.Comparator;

/**
 *
 * @author Administrador
 */
public class GrupoPermisosComparator implements Comparator<GrupoPermiso> {
   
    @Override
    public int compare(GrupoPermiso o1, GrupoPermiso o2) {
        try {
            return o1.getOrden().compareTo(o2.getOrden());
        } catch (NullPointerException npe) {
            if (o1.getOrden() == null && o2.getOrden() != null) {
                return 1;
            }
            if (o1.getOrden() != null && o2.getOrden() == null) {
                return -1;
            }
            if (o1.getOrden() == null && o2.getOrden() == null) {
                return 0;
            }
            return 0;
        }
    }
    
}
