/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

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
public class VehTipoServicioFacade extends AbstractFacade<VehTipoServicio> implements VehTipoServicioFacadeLocal {

    @PersistenceContext(unitName = "Gnv-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VehTipoServicioFacade() {
        super(VehTipoServicio.class);
    }
    
    @Override
    public List<VehTipoServicio> findVehTipoServicio()
    {
        List<VehTipoServicio> tipoServicio = new ArrayList();
        
        try
        {
            Query query = em.createQuery("Select vts FROM VehTipoServicio vts where vts.estatusRow>0",VehTipoServicio.class);
            tipoServicio = query.getResultList();
        
        }
        catch(NoResultException nre)
        {
            return null;
        }
         catch(EJBException ejbe)
        {
            out.println("E/ Busqueda tipo servicio, facade local: "+ejbe.getMessage());
            throw new EJBException(ejbe);
        } catch(Exception e)
        {
            out.println("E/ Busqueda tipo servicio, facade local: "+e.getMessage());
        }
        return tipoServicio;
    }
    
}
