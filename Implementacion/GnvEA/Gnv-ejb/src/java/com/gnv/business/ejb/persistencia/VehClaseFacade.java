/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.VehClase;
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
public class VehClaseFacade extends AbstractFacade<VehClase> implements VehClaseFacadeLocal {

    @PersistenceContext(unitName = "Gnv-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VehClaseFacade() {
        super(VehClase.class);
    }

    @Override
    public List<VehClase> findVehClase() {
        List<VehClase> vehiculoClase = new ArrayList();

        try {
            Query query = em.createQuery("Select vc FROM VehClase vc where vc.estatusRow>0", VehClase.class);
            vehiculoClase = query.getResultList();

        } catch (NoResultException nre) {
            return null;
        } catch (EJBException ejbe) {
            out.println("E/ Busqueda vehiculo Clase, facade local: "+ejbe.getMessage());
            throw new EJBException(ejbe);
        } catch (Exception e) {
            out.println("E/ Busqueda vehiculo Clase, facade local: " + e.getMessage());
        }
        return vehiculoClase;
    }

}
