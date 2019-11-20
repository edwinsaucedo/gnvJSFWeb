/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.Cilindro;
import com.gnv.business.ejb.modelo.Conversion;
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
public class CilindroFacade extends AbstractFacade<Cilindro> implements CilindroFacadeLocal {

    @PersistenceContext(unitName = "Gnv-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CilindroFacade() {
        super(Cilindro.class);
    }
         @Override
    public List<Cilindro> findByEstatusRow(Legalizacion legalizacion)
    {
        List<Cilindro> cilindro = new ArrayList();
        
        try{
            Query query = em.createQuery("SELECT c FROM Cilindro c where c.estatusRow>0 and c.legalizacion = :legalizacion", Cilindro.class);
            query.setParameter("legalizacion", legalizacion);
            cilindro = query.getResultList();
        } catch(NoResultException nre)
        {
            return null;
        } catch(EJBException ejbe)
        {
            throw new EJBException(ejbe);
        } catch(Exception e)
        {
            out.println("E/ Busqueda cilindro, facade local: "+e.getMessage());
        }
        return cilindro;
    }

    @Override
    public Cilindro findCilindroBySerial(String serieCilindro)
    {
         Cilindro cilindro = new Cilindro();
        try {
            Query query = em.createQuery("SELECT c FROM Cilindro c  where c.estatusRow>0 and c.legalizacion.estatusRow>0 and c.serial=:serieCilindro", Cilindro.class);
            query.setParameter("serieCilindro",serieCilindro);
            cilindro = (Cilindro)query.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        } catch (EJBException ejbe) {
            throw new EJBException(ejbe);
        } catch (Exception e) {
            out.println("E/ Busqueda Cilindro, facade local: " + e.getMessage());
        }
        return cilindro;
    }

    @Override
    public List<Cilindro> findByConversion(Conversion conversion)
    {
        List<Cilindro> cilindro = new ArrayList<>();
        String nQuery="SELECT c.* FROM Cilindro c inner join Conversion_Equipo ce ON c.id=ce.id_registro and tipo=0 where ce.estatus_row>0 and ce.conversion=?";
        try {
            Query query = em.createNativeQuery(nQuery, Cilindro.class);
            query.setParameter(1,conversion.getId());
            cilindro = query.getResultList();
        } catch (NoResultException nre) {
            return null;
        } catch (EJBException ejbe) {
            throw new EJBException(ejbe);
        } catch (Exception e) {
            out.println("E/ Busqueda KIT, facade local: " + e.getMessage());
        }
        return cilindro;
    }
    
}
