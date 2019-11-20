/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.Documento;
import com.gnv.business.ejb.modelo.Objeto;
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
public class DocumentoFacade extends AbstractFacade<Documento> implements DocumentoFacadeLocal {

    @PersistenceContext(unitName = "Gnv-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DocumentoFacade() {
        super(Documento.class);
    }

    @Override
    public List<Documento> findByLegalizacion(Integer legalizacion) {

        List<Documento> documento = new ArrayList();
        try {
            Query query = em.createQuery("SELECT d FROM Documento d where d.estatusRow > 0 and d.idRegistro = :idLegalizacion", Documento.class);
            query.setParameter("idLegalizacion", legalizacion);
            documento = query.getResultList();

        } catch (NoResultException nre) {
            return null;
        } catch (EJBException ejbe) {
            out.println("E/ Busqueda doc a, facade local: " + ejbe.getMessage());
            throw new EJBException(ejbe);
        } catch (Exception e) {
            out.println("E/ Busqueda doc , facade local: " + e.getMessage());
        }
        return documento;
    }

    @Override
    public List<Documento> findByObjeto(Objeto objeto, Integer idRegistro)
    {
        List<Documento> documento = new ArrayList();
        try {
            Query query = em.createQuery("SELECT d FROM Documento d where d.objeto=:objeto and d.idRegistro=:idRegistro and d.estatusRow>0", Documento.class);
            query.setParameter("objeto", objeto);
            query.setParameter("idRegistro", idRegistro);
            documento = query.getResultList();
        } catch (NoResultException nre) {
            return null;
        } catch (EJBException ejbe) {
            out.println("E/ Busqueda doc, facade local: " + ejbe.getMessage());
            throw new EJBException(ejbe);
        } catch (Exception e) {
            out.println("E/ Busqueda doc , facade local: " + e.getMessage());
        }
        return documento;
    }

    @Override
    public List<Documento> findByRegistros(Objeto objeto, List<Integer> idRegistros)
    {
        List<Documento> documento = new ArrayList();
        try {
            Query query = em.createQuery("SELECT d FROM Documento d where d.objeto=:objeto and d.idRegistro in :idRegistros and d.estatusRow>0", Documento.class);
            query.setParameter("objeto", objeto);
            query.setParameter("idRegistros", idRegistros);
            documento = query.getResultList();
        } catch (NoResultException nre) {
            return null;
        } catch (EJBException ejbe) {
            out.println("E/ Busqueda doc, facade local: " + ejbe.getMessage());
            throw new EJBException(ejbe);
        } catch (Exception e) {
            out.println("E/ Busqueda doc , facade local: " + e.getMessage());
        }
        return documento;
    }
    
    

}
