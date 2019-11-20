/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.DocTipoArchivo;
import com.gnv.business.ejb.modelo.Estatus;
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
public class DocTipoArchivoFacade extends AbstractFacade<DocTipoArchivo> implements DocTipoArchivoFacadeLocal {

    @PersistenceContext(unitName = "Gnv-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DocTipoArchivoFacade() {
        super(DocTipoArchivo.class);
    }
    
    @Override
    public DocTipoArchivo findByExtension(String extension)
    {
        DocTipoArchivo dta =  new DocTipoArchivo();
        try{
            if(extension != null && !extension.isEmpty())
            {
                 Query query = em.createQuery("SELECT dta FROM DocTipoArchivo dta WHERE dta.extension = :extension and dta.estatusRow>0 ", DocTipoArchivo.class);
                    query.setParameter("extension", extension);
                     dta = (DocTipoArchivo) query.getSingleResult();
            }
        }catch (NoResultException nre) {
            return null;
        } catch (EJBException ejbe) {
            throw new EJBException(ejbe);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dta;
        
          
        
    }
    
}
