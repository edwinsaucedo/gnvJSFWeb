/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.TipoDocLegal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import static java.lang.System.out;

/**
 *
 * @author US5
 */
@Stateless
public class TipoDocLegalFacade extends AbstractFacade<TipoDocLegal> implements TipoDocLegalFacadeLocal {
    @PersistenceContext(unitName = "Gnv-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoDocLegalFacade() {
        super(TipoDocLegal.class);
    }

    @Override
    public List<TipoDocLegal> findByEstatusRow() {
        List<TipoDocLegal> tipoLegalizacion = new ArrayList();
        try{
        
            Query query  = em.createQuery("SELECT obj from TipoDocLegal obj where obj.estatusRow>0",TipoDocLegal.class);
            tipoLegalizacion = query.getResultList();
            
        }catch(NoResultException nre){
            return null;
        }catch(EJBException ejbe){
            out.println("E/ Busqueda Tipo Documentacion, facade local: " + ejbe.getMessage());
            throw new EJBException(ejbe);
        }catch(Exception e){
            out.println("E/ Busqueda Tipo Documentacion, facade local: " + e.getMessage());
            
        }
        
        return tipoLegalizacion;
     
    }
    
}
