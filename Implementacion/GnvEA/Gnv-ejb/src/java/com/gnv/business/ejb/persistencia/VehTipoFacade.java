/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.VehTipo;
import com.gnv.business.ejb.modelo.VehTipoServicio;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author PC2
 */
@Stateless
public class VehTipoFacade extends AbstractFacade<VehTipo> implements VehTipoFacadeLocal {

    @PersistenceContext(unitName = "Gnv-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VehTipoFacade() {
        super(VehTipo.class);
    }
    
    @Override
    public List<VehTipo> findVehTipo()
    {
        List<VehTipo> tipoVehiculo = new ArrayList();
        
        try
        {
            Query query = em.createQuery("Select vt FROM VehTipo vt where vt.estatusRow>0",VehTipo.class);
            tipoVehiculo = query.getResultList();
        
        }
        catch(NoResultException nre)
        {
            return null;
        }
         catch(EJBException ejbe)
        {
            out.println("E/ Busqueda tipo vehiculo, facade local: "+ejbe.getMessage());
            throw new EJBException(ejbe);
        } catch(Exception e)
        {
            out.println("E/ Busqueda tipo vehiculo, facade local: "+e.getMessage());
        }
        return tipoVehiculo;
    }
    
}
