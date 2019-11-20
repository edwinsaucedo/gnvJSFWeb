/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.TipoCilindro;
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
public class TipoCilindroFacade extends AbstractFacade<TipoCilindro> implements TipoCilindroFacadeLocal {

    @PersistenceContext(unitName = "Gnv-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager()
    {
        return em;
    }

    public TipoCilindroFacade()
    {
        super(TipoCilindro.class);
    }

    @Override
    public List<TipoCilindro> findByEstatusRow()
    {
        List<TipoCilindro> tipoCilindros= new ArrayList();
        
        try
        {
            Query query = em.createQuery("SELECT tp FROM TipoCilindro tp where tp.estatusRow>0", TipoCilindro.class);
            tipoCilindros = query.getResultList();
            
        } catch(NoResultException nre)
        {
            return null;
        } catch(EJBException ejbe)
        {
            out.println("E/ Busqueda Tipo de cilindro, facade local: " + ejbe.getMessage());
            throw new EJBException(ejbe);
        } catch(Exception e)
        {
            out.println("E/ Busqueda tipo cilindros, facade local: "+e.getMessage());
        }
        return tipoCilindros;
    }
    
}
