/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.Marca;
import com.gnv.business.ejb.modelo.TipoCredito;
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
public class TipoCreditoFacade extends AbstractFacade<TipoCredito> implements TipoCreditoFacadeLocal {

    @PersistenceContext(unitName = "Gnv-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoCreditoFacade() {
        super(TipoCredito.class);
    }
    
       @Override
    public List<TipoCredito> findByEstatusRow()
    {
        List<TipoCredito> tipoCredito = new ArrayList();

        try
        {
            Query query = em.createQuery("SELECT tc FROM TipoCredito tc where tc.estatusRow > 0", TipoCredito.class);
            tipoCredito = query.getResultList();

        } catch (NoResultException nre)
        {
            return null;
        } catch (EJBException ejbe)
        {
            out.println("E/ Busqueda tipo credito, facade local: " + ejbe.getMessage());
            throw new EJBException(ejbe);
        } catch (Exception e)
        {
            out.println("E/ Busqueda tipo credito, facade local: " + e.getMessage());
        }
        return tipoCredito;
    }
    
}
