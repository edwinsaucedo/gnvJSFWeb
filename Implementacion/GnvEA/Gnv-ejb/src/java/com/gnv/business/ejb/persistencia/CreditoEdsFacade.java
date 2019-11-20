/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.Credito;
import com.gnv.business.ejb.modelo.CreditoEds;
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
public class CreditoEdsFacade extends AbstractFacade<CreditoEds> implements CreditoEdsFacadeLocal {

    @PersistenceContext(unitName = "Gnv-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CreditoEdsFacade() {
        super(CreditoEds.class);
    }

    @Override
    public CreditoEds findByCreditoEds(Integer credito, Integer eds) {
        CreditoEds creditoEds = new CreditoEds();

        try {
            Query query = em.createNativeQuery("select ceds.* from  Credito_Eds ceds where ceds.estatus_Row>0 and ceds.credito = ? and ceds.eds = ?", CreditoEds.class);
            query.setParameter(1, credito);
            query.setParameter(2, eds);
            creditoEds = (CreditoEds) query.getSingleResult();

        } catch (NoResultException nre) {
            return null;
        } catch (EJBException ejbe) {
            out.println("E/ Busqueda credito eds, facade local: " + ejbe.getMessage());
            throw new EJBException(ejbe);
        } catch (Exception e) {
            out.println("E/ Busqueda credito eds, facade local: " + e.getMessage());
        }
        return creditoEds;
    }

    @Override
    public List<CreditoEds> findByCredito(Credito credito) {
        List<CreditoEds> creditoEds = new ArrayList();

        try {
            Query query = em.createQuery("SELECT c FROM CreditoEds c where c.estatusRow > 0 and c.credito = :credito", CreditoEds.class);
            query.setParameter("credito", credito);
            creditoEds = query.getResultList();

        } catch (NoResultException nre) {
            return null;
        } catch (EJBException ejbe) {
            throw new EJBException(ejbe);
        } catch (Exception e) {
            out.println("E/ Busqueda creditoeds, facade local: " + e.getMessage());
        }
        return creditoEds;
    }
}
