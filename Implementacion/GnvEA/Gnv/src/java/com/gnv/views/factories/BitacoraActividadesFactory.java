/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.views.factories;

import com.gnv.business.ejb.modelo.BitacoraActividades;
import com.gnv.business.ejb.modelo.Objeto;
import com.gnv.business.ejb.modelo.Usuario;
import com.gnv.business.ejb.persistencia.BitacoraActividadesFacadeLocal;
import static java.lang.System.out;
import java.util.Date;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Administrador
 */
public class BitacoraActividadesFactory {

    private final static BitacoraActividadesFacadeLocal bitacoraActividadesFacade = lookupBitacoraActividadFacadeLocal();

    private static BitacoraActividadesFacadeLocal lookupBitacoraActividadFacadeLocal()
    {
        try
        {
            Context c = new InitialContext();
            return (BitacoraActividadesFacadeLocal) c.lookup("java:global/GnvEA/Gnv-ejb/BitacoraActividadesFacade!com.gnv.business.ejb.persistencia.BitacoraActividadesFacadeLocal");
        } catch (NamingException ne)
        {
            throw new RuntimeException(ne);
        }
    }

    public static BitacoraActividades ckeckIn(Usuario usuario, Objeto objeto, int registro, String accion)
    {
        BitacoraActividades bitacoraActividades = new BitacoraActividades();
        try
        {
            bitacoraActividades.setAccion(accion);
            bitacoraActividades.setObjeto(objeto);
            bitacoraActividades.setRegistro(registro);
            bitacoraActividades.setUsuario(usuario);
            bitacoraActividades.setFechaActividad(new Date());
            bitacoraActividadesFacade.create(bitacoraActividades);
        } catch (Exception e)
        {
            out.println("E/GVN BitacoraActividadesFactory: " + e.getMessage());
        }
        return bitacoraActividades;
    }
}
