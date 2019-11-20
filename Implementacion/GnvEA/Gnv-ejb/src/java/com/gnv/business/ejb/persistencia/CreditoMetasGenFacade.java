/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.CreditoMetasGen;
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
public class CreditoMetasGenFacade extends AbstractFacade<CreditoMetasGen> implements CreditoMetasGenFacadeLocal {

    @PersistenceContext(unitName = "Gnv-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
       @Override
    public CreditoMetasGen findByCredito(Integer credito)
    {
        CreditoMetasGen creditoMetasGen = new CreditoMetasGen();

        try
        {
            Query query = em.createNativeQuery("select cmg.* from  Credito_Metas_Gen cmg where cmg.estatus_Row>0 and cmg.credito = ?", CreditoMetasGen.class);
            query.setParameter(1, credito);
            creditoMetasGen = (CreditoMetasGen) query.getSingleResult();

        } catch (NoResultException nre)
        {
            return null;
        } catch (EJBException ejbe)
        {
            out.println("E/ Busqueda metas generales, facade local: " + ejbe.getMessage());
            throw new EJBException(ejbe);
        } catch (Exception e)
        {
            out.println("E/ Busqueda metas generales, facade local: " + e.getMessage());
        }
        return creditoMetasGen;
    }

    public CreditoMetasGenFacade() {
        super(CreditoMetasGen.class);
    }
    
}
