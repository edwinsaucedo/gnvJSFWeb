/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

 
import com.gnv.business.ejb.modelo.TipoConvenio;
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
public class TipoConvenioFacade extends AbstractFacade<TipoConvenio> implements TipoConvenioFacadeLocal {
    @PersistenceContext(unitName = "Gnv-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoConvenioFacade() {
        super(TipoConvenio.class);
    }

    @Override
    public List<TipoConvenio> findByEstatusRow() {

        List<TipoConvenio> tipoConvenios= new ArrayList();
        
        try
        {
            Query query = em.createQuery("SELECT obj FROM TipoConvenio obj where obj.estatusRow>0", TipoConvenio.class);
            tipoConvenios = query.getResultList();
            
        } catch(NoResultException nre)
        {
            return null;
        } catch(EJBException ejbe)
        {
            out.println("E/ Busqueda Tipo convenios, facade local: " + ejbe.getMessage());
            throw new EJBException(ejbe);
        } catch(Exception e)
        {
            out.println("E/ Busqueda tipo convenio, facade local: "+e.getMessage());
        }
        return tipoConvenios;
    }
    
}
