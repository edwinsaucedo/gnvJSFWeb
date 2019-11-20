/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.TipoLegalizacion;
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
public class TipoLegalizacionFacade extends AbstractFacade<TipoLegalizacion> implements TipoLegalizacionFacadeLocal {
    @PersistenceContext(unitName = "Gnv-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoLegalizacionFacade() {
        super(TipoLegalizacion.class);
    }

    @Override
    public List<TipoLegalizacion> findByEstatusRow() {
                 List<TipoLegalizacion> tipoLegalizacion = new ArrayList();
        try{
        
            Query query  = em.createQuery("SELECT obj from TipoLegalizacion obj where obj.estatusRow>0",TipoLegalizacion.class);
            tipoLegalizacion = query.getResultList();
            
        }catch(NoResultException nre){
            return null;
        }catch(EJBException ejbe){
            out.println("E/ Busqueda Tipo Legalizacion, facade local: " + ejbe.getMessage());
            throw new EJBException(ejbe);
        }catch(Exception e){
            out.println("E/ Busqueda Tipo Legalizacion, facade local: " + e.getMessage());
            
        }
        
        return tipoLegalizacion;
    }
    
    
}
