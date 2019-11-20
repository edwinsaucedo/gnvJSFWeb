/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.Legalizacion;
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
public class LegalizacionFacade extends AbstractFacade<Legalizacion> implements LegalizacionFacadeLocal {

    @PersistenceContext(unitName = "Gnv-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LegalizacionFacade() {
        super(Legalizacion.class);
    }
     @Override
    public List<Legalizacion> findByEstatusRow()
    {
         List<Legalizacion> legalizacion = new ArrayList();
        
        try
        {
            Query query = em.createQuery("SELECT l FROM Legalizacion l where l.estatusRow>0", Legalizacion.class);
            legalizacion = query.getResultList();
            
        } catch(NoResultException nre)
        {
            return null;
        } catch(EJBException ejbe)
        {
            throw new EJBException(ejbe);
        } catch(Exception e)
        {
            out.println("E/ Busqueda legalizacion, facade local: "+e.getMessage());
        }
        return legalizacion;
    }
    
    
}
