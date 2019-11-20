/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.Ciudad;
import com.gnv.business.ejb.modelo.Ciudad;
import com.gnv.business.ejb.modelo.Estado;
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
 * @author Administrador
 */
@Stateless
public class CiudadFacade extends AbstractFacade<Ciudad> implements CiudadFacadeLocal {

    @PersistenceContext(unitName = "Gnv-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager()
    {
        return em;
    }

    public CiudadFacade()
    {
        super(Ciudad.class);
    }

    @Override
    public List<Ciudad> findByEstado(Estado estado)
    {
        List<Ciudad> ciudad= new ArrayList<>();
        
        try
        {
            Query query = em.createQuery("SELECT c FROM Ciudad c where c.estado=:estado", Ciudad.class);
            query.setParameter("estado",estado);
            ciudad = query.getResultList();
            
        } catch(NoResultException nre)
        {
            return null;
        } catch(EJBException ejbe)
        {
            out.println("E/ Busqueda Ciudades, facade local: "+ejbe.getMessage());
            throw new EJBException(ejbe);
        } catch(Exception e)
        {
            out.println("E/ Busqueda Ciudades, facade local: "+e.getMessage());
        }
        return ciudad;
    }
    
}
