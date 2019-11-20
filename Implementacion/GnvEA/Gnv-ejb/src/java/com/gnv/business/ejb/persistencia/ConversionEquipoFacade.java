/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.Cilindro;
import com.gnv.business.ejb.modelo.ConversionEquipo;
import com.gnv.business.ejb.modelo.Kit;
import static java.lang.System.out;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Administrador
 */
@Stateless
public class ConversionEquipoFacade extends AbstractFacade<ConversionEquipo> implements ConversionEquipoFacadeLocal {

    @PersistenceContext(unitName = "Gnv-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager()
    {
        return em;
    }

    public ConversionEquipoFacade()
    {
        super(ConversionEquipo.class);
    }

    @Override
    public int deleteByKit(Kit kit)
    {
        String nQuery="Update CONVERSION_EQUIPO set ESTATUS_ROW=-1 WHERE ID_REGISTRO=? AND TIPO=1 AND ESTATUS_ROW>0";
        int success=0;
        try {
            Query query = em.createNativeQuery(nQuery);
            query.setParameter(1,kit.getId());
            success=query.executeUpdate();
           
        } catch (EJBException ejbe) {
            throw new EJBException(ejbe);
        } catch (Exception e) {
            out.println("E/ Busqueda KIT, facade local: " + e.getMessage());
        }
        return success;
    }

    @Override
    public int deleteByCilindro(Cilindro cilindro)
    {
        String nQuery="Update CONVERSION_EQUIPO set ESTATUS_ROW=-1 WHERE ID_REGISTRO=? AND TIPO=0 AND ESTATUS_ROW>0";
        int success=0;
        try {
            Query query = em.createNativeQuery(nQuery);
            query.setParameter(1,cilindro.getId());
            success=query.executeUpdate();
           
        } catch (EJBException ejbe) {
            throw new EJBException(ejbe);
        } catch (Exception e) {
            out.println("E/ Busqueda Cilindro, facade local: " + e.getMessage());
        }
        return success;
    }
    
}
