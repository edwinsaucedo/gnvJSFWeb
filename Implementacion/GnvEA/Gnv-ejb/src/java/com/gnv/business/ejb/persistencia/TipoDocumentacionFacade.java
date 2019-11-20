/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.TipoDocumentacion;
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
 * @author US5
 */
@Stateless
public class TipoDocumentacionFacade extends AbstractFacade<TipoDocumentacion> implements TipoDocumentacionFacadeLocal {
    @PersistenceContext(unitName = "Gnv-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoDocumentacionFacade() {
        super(TipoDocumentacion.class);
    }

    @Override
    public List<TipoDocumentacion> findByEstatusRow() {
        List<TipoDocumentacion> tipoDocumentacion = new ArrayList();
        try{
        
            Query query  = em.createQuery("SELECT obj from TipoDocumentacion obj where obj.estatusRow>0",TipoDocumentacion.class);
            tipoDocumentacion = query.getResultList();
            
        }catch(NoResultException nre){
            return null;
        }catch(EJBException ejbe){
            out.println("E/ Busqueda Tipo Documentacion, facade local: " + ejbe.getMessage());
            throw new EJBException(ejbe);
        }catch(Exception e){
            System.out.println("E/ Busqueda Tipo Documentacion, facade local: " + e.getMessage());
            
        }
        
        return tipoDocumentacion;
    }
    
}
