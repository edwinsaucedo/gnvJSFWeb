/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.CreditoMetaMes;
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
public class CreditoMetaMesFacade extends AbstractFacade<CreditoMetaMes> implements CreditoMetaMesFacadeLocal {

    @PersistenceContext(unitName = "Gnv-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CreditoMetaMesFacade() {
        super(CreditoMetaMes.class);
    }

    @Override
    public CreditoMetaMes findByCredito(Integer credito) {
        CreditoMetaMes creditoMetaMes = new CreditoMetaMes();

        try {
            Query query = em.createNativeQuery("select cmm.* from  Credito_Meta_Mes cmm where cmm.estatus_Row>0 and cmm.credito = ?", CreditoMetaMes.class);
            query.setParameter(1, credito);
            creditoMetaMes = (CreditoMetaMes) query.getSingleResult();

        } catch (NoResultException nre) {
            return null;
        } catch (EJBException ejbe) {
            out.println("E/ Busqueda metas mensuales, facade local: " + ejbe.getMessage());
            throw new EJBException(ejbe);
        } catch (Exception e) {
            out.println("E/ Busqueda metas mensuales, facade local: " + e.getMessage());
        }
        return creditoMetaMes;
    }

}
