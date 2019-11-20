/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.TipoConversion;
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
public class TipoConversionFacade extends AbstractFacade<TipoConversion> implements TipoConversionFacadeLocal {

    @PersistenceContext(unitName = "Gnv-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager()
    {
        return em;
    }

    public TipoConversionFacade()
    {
        super(TipoConversion.class);
    }

    @Override
    public List<TipoConversion> findByEstatusRow()
    {
        List<TipoConversion> tipoConversiones= new ArrayList();
        
        try
        {
            Query query = em.createQuery("SELECT tc FROM TipoConversion tc where tc.estatusRow>0", TipoConversion.class);
            tipoConversiones = query.getResultList();
            
        } catch(NoResultException nre)
        {
            return null;
        } catch(EJBException ejbe)
        {
            out.println("E/ Busqueda Tipo conversiones, facade local: " + ejbe.getMessage());
            throw new EJBException(ejbe);
        } catch(Exception e)
        {
            out.println("E/ Busqueda tipo conversiones, facade local: "+e.getMessage());
        }
        return tipoConversiones;
    }
    
}
