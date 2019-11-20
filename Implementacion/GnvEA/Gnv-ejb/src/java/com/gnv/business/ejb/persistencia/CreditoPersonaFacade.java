/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.CreditoPersona;
import static java.lang.System.out;
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
public class CreditoPersonaFacade extends AbstractFacade<CreditoPersona> implements CreditoPersonaFacadeLocal {

    @PersistenceContext(unitName = "Gnv-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CreditoPersonaFacade() {
        super(CreditoPersona.class);
    }
    
      @Override
    public CreditoPersona findByCredito(Integer credito)
    {
        CreditoPersona creditoPersona = new CreditoPersona();

        try
        {
            Query query = em.createNativeQuery("select cp.* from  Credito_Persona cp where cp.estatus_Row>0 and cp.credito = ?", CreditoPersona.class);
            query.setParameter(1, credito);
            creditoPersona = (CreditoPersona) query.getSingleResult();

        } catch (NoResultException nre)
        {
            return null;
        } catch (EJBException ejbe)
        {
            out.println("E/ Busqueda vehiculos, facade local: " + ejbe.getMessage());
            throw new EJBException(ejbe);
        } catch (Exception e)
        {
            out.println("E/ Busqueda vehiculos, facade local: " + e.getMessage());
        }
        return creditoPersona;
    }

    
}
