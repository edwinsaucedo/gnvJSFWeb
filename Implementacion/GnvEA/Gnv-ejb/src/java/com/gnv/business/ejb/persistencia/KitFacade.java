/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.Conversion;
import com.gnv.business.ejb.modelo.Kit;
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
public class KitFacade extends AbstractFacade<Kit> implements KitFacadeLocal {

    @PersistenceContext(unitName = "Gnv-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public KitFacade() {
        super(Kit.class);
    }

    @Override
    public List<Kit> findByEstatusRow(Legalizacion legalizacion) {
        List<Kit> kit = new ArrayList();

        try {
            Query query = em.createQuery("SELECT k FROM Kit k where k.estatusRow>0 and k.legalizacion = :legalizacion", Kit.class);
            query.setParameter("legalizacion",legalizacion);
            kit = query.getResultList();
        } catch (NoResultException nre) {
            return null;
        } catch (EJBException ejbe) {
            throw new EJBException(ejbe);
        } catch (Exception e) {
            out.println("E/ Busqueda KIT, facade local: " + e.getMessage());
        }
        return kit;
    }


    @Override
    public Kit findKitBySerial(String serieKit)
    {
        Kit kit = new Kit();
        try {
            Query query = em.createQuery("SELECT k FROM Kit k  where k.legalizacion.estatusRow>0 and k.estatusRow>0 and k.serial=:serieKit", Kit.class);
            query.setParameter("serieKit",serieKit);
            kit = (Kit)query.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        } catch (EJBException ejbe) {
            throw new EJBException(ejbe);
        } catch (Exception e) {
            out.println("E/ Busqueda KIT, facade local: " + e.getMessage());
        }
        return kit;
    }

    @Override
    public List<Kit> findByConversion(Conversion conversion)
    {
        List<Kit> kit = new ArrayList<>();
        String nQuery="SELECT k.* FROM Kit k inner join Conversion_Equipo ce ON k.id=ce.id_registro and tipo=1 where ce.estatus_row>0 and ce.conversion=?";
        try {
            Query query = em.createNativeQuery(nQuery, Kit.class);
            query.setParameter(1,conversion.getId());
            kit = query.getResultList();
        } catch (NoResultException nre) {
            return null;
        } catch (EJBException ejbe) {
            throw new EJBException(ejbe);
        } catch (Exception e) {
            out.println("E/ Busqueda KIT, facade local: " + e.getMessage());
        }
        return kit;
        
    }

}
