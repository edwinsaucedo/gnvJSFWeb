/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.business.ejb.persistencia;

import com.gnv.business.ejb.modelo.Vehiculo;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Administrador
 */
@Stateless
public class VehiculoFacade extends AbstractFacade<Vehiculo> implements VehiculoFacadeLocal {

    @PersistenceContext(unitName = "Gnv-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VehiculoFacade() {
        super(Vehiculo.class);
    }

    @Override
    public List<Vehiculo> findByStatus() {
        List<Vehiculo> vehiculo = new ArrayList();

        try {
            Query query = em.createQuery("SELECT v FROM Vehiculo v where v.estatusRow>0", Vehiculo.class);
            vehiculo = query.getResultList();

        } catch (NoResultException nre) {
            return null;
        } catch (EJBException ejbe) {
            out.println("E/ Busqueda vehiculos, facade local: " + ejbe.getMessage());
            throw new EJBException(ejbe);
        } catch (Exception e) {
            out.println("E/ Busqueda vehiculos, facade local: " + e.getMessage());
        }
        return vehiculo;
    }

    @Override
    public List<Vehiculo> findByDate() {
        List<Vehiculo> vehiculo = new ArrayList();

        try {
            Query query = em.createNativeQuery("SELECT v.* FROM Vehiculo v where v.estatus_Row>0 and GETDATE() >= DATEADD(M,-12, v.FECHA_PROX_REV) and v.FECHA_PROX_REV IS NOT NULL", Vehiculo.class);
            //query.setParameter("proximaRevision", proximaRevision);
            vehiculo = query.getResultList();

        } catch (NoResultException nre) {
            return null;
        } catch (EJBException ejbe) {
            out.println("E/ Busqueda vehiculos, facade local: " + ejbe.getMessage());
            throw new EJBException(ejbe);
        } catch (Exception e) {
            out.println("E/ Busqueda vehiculos, facade local: " + e.getMessage());
        }
        return vehiculo;
    }

    @Override
    public Vehiculo findbyPlaca(String placa) {
        Vehiculo vehiculo = new Vehiculo();

        try {
            Query query = em.createQuery("SELECT v FROM Vehiculo v where v.placa=:placa and v.estatusRow>0", Vehiculo.class);
            query.setParameter("placa", placa);
            vehiculo = (Vehiculo) query.getSingleResult();

        } catch (NoResultException nre) {
            return null;
        } catch (EJBException ejbe) {
            out.println("E/ Busqueda vehiculo, facade local: " + ejbe.getMessage());
            throw new EJBException(ejbe);
        } catch (Exception e) {
            out.println("E/ Busqueda tipo vehiculo, facade local: " + e.getMessage());
        }
        return vehiculo;
    }

    @Override
    public Vehiculo findbyPlacaVin(String placa, String vin) {
        Vehiculo vehiculo = new Vehiculo();

        try {
            Query query = em.createQuery("SELECT v FROM Vehiculo v where v.vin=:vin and v.placa=:placa and v.estatusRow>0", Vehiculo.class);
            query.setParameter("placa", placa);
            query.setParameter("vin", vin);
            vehiculo = (Vehiculo) query.getSingleResult();

        } catch (NoResultException nre) {
            return null;
        } catch (EJBException ejbe) {
            out.println("E/ Busqueda vehiculo, facade local: " + ejbe.getMessage());
            throw new EJBException(ejbe);
        } catch (Exception e) {
            out.println("E/ Busqueda tipo vehiculo, facade local: " + e.getMessage());
        }
        return vehiculo;
    }

    @Override
    public Vehiculo findbyVin(String vin) {
        Vehiculo vehiculo = new Vehiculo();

        try {
            Query query = em.createQuery("SELECT v FROM Vehiculo v where v.vin=:vin and v.estatusRow>0", Vehiculo.class);
            query.setParameter("vin", vin);
            vehiculo = (Vehiculo) query.getSingleResult();

        } catch (NoResultException nre) {
            return null;
        } catch (EJBException ejbe) {
            out.println("E/ Busqueda vehiculo, facade local: " + ejbe.getMessage());
            throw new EJBException(ejbe);
        } catch (Exception e) {
            out.println("E/ Busqueda vehiculo, facade local: " + e.getMessage());
        }
        return vehiculo;
    }

    @Override
    public List<Vehiculo> findByPlacaList(String placa) {
        List<Vehiculo> vehiculo = new ArrayList();

        try {
            Query query = em.createQuery("SELECT v FROM Vehiculo v where v.placa like :placa  and v.estatusRow>0", Vehiculo.class);
            query.setParameter("placa", placa + "%");
            vehiculo = query.getResultList();

        } catch (NoResultException nre) {
            return null;
        } catch (EJBException ejbe) {
            out.println("E/ Busqueda vehiculos, facade local: " + ejbe.getMessage());
            throw new EJBException(ejbe);
        } catch (Exception e) {
            out.println("E/ Busqueda vehiculos, facade local: " + e.getMessage());
        }
        return vehiculo;
    }

    @Override
    public List<Vehiculo> findByVinList(String vin) {
        List<Vehiculo> vehiculo = new ArrayList();

        try {
            Query query = em.createQuery("SELECT v FROM Vehiculo v where v.vin like :vin and v.estatusRow>0", Vehiculo.class);
            query.setParameter("vin", vin + "%");
            vehiculo = query.getResultList();

        } catch (NoResultException nre) {
            return null;
        } catch (EJBException ejbe) {
            out.println("E/ Busqueda vehiculos, facade local: " + ejbe.getMessage());
            throw new EJBException(ejbe);
        } catch (Exception e) {
            out.println("E/ Busqueda vehiculos, facade local: " + e.getMessage());
        }
        return vehiculo;
    }

    @Override
    public List<Vehiculo> findByVinPlacaList(String vin, String placa) {
        List<Vehiculo> vehiculo = new ArrayList();

        try {
            Query query = em.createQuery("SELECT v FROM Vehiculo v where v.vin like :vin and  v.placa like :placa and v.estatusRow>0", Vehiculo.class);
            query.setParameter("vin", vin + "%");
            query.setParameter("placa", placa + "%");
            vehiculo = query.getResultList();

        } catch (NoResultException nre) {
            return null;
        } catch (EJBException ejbe) {
            out.println("E/ Busqueda vehiculos, facade local: " + ejbe.getMessage());
            throw new EJBException(ejbe);
        } catch (Exception e) {
            out.println("E/ Busqueda vehiculos, facade local: " + e.getMessage());
        }
        return vehiculo;
    }

    @Override
    public List<Vehiculo> findByVehiculo(Integer vehiculo) {
        List<Vehiculo> vh = new ArrayList();
        try {
            Query query = em.createQuery("SELECT v FROM Vehiculo v where  v.id = :vehiculo and v.estatusRow>0", Vehiculo.class);
            query.setParameter("vehiculo", vehiculo);
            vh = query.getResultList();
        } catch (NoResultException nre) {
            return null;
        } catch (EJBException ejbe) {
            out.println("E/ Busqueda propietario, facade local: " + ejbe.getMessage());
            throw new EJBException(ejbe);
        } catch (Exception e) {
            out.println("E/ Busqueda  propietario, facade local: " + e.getMessage());
        }

        return vh;
    }

    @Override
    public List<Vehiculo> findByCredit(String vin, String placa) {
        List<Vehiculo> vehiculo = new ArrayList();

        String nQuery = "select vh.* from Vehiculo vh inner join Valoracion v on v.Vehiculo = vh.ID"
                + " where v.solicitar_Credito = 1 and vh.estatus_Row>0 and v.estatus_Row>0 and  "
                + "(vh.placa like ? or vh.vin like ?) "
                + "and not exists (Select null from Credito c where c.Vehiculo = vh.ID and estatus_row >0)  ";
        try {
            Query query = em.createNativeQuery(nQuery, Vehiculo.class);
            query.setParameter(1, placa + "%");
            query.setParameter(2, vin + "%");
            vehiculo = query.getResultList();

        } catch (NoResultException nre) {
            return null;
        } catch (EJBException ejbe) {
            out.println("E/ Busqueda vehiculos, facade local: " + ejbe.getMessage());
            throw new EJBException(ejbe);
        } catch (Exception e) {
            out.println("E/ Busqueda vehiculos, facade local: " + e.getMessage());
        }
        return vehiculo;
    }
    
      @Override
    public List<Vehiculo> findByCreditPlaca(String placa) {
        List<Vehiculo> vehiculo = new ArrayList();

        String nQuery = "select vh.* from Vehiculo vh inner join Valoracion v on v.Vehiculo = vh.ID"
                + " where v.solicitar_Credito = 1 and vh.estatus_Row>0 and v.estatus_Row>0 and  "
                + "(vh.placa like ?) "
                + "and not exists (Select null from Credito c where c.Vehiculo = vh.ID and estatus_row >0)  ";
        try {
            Query query = em.createNativeQuery(nQuery, Vehiculo.class);
            query.setParameter(1, placa + "%");
           
            vehiculo = query.getResultList();

        } catch (NoResultException nre) {
            return null;
        } catch (EJBException ejbe) {
            out.println("E/ Busqueda vehiculos, facade local: " + ejbe.getMessage());
            throw new EJBException(ejbe);
        } catch (Exception e) {
            out.println("E/ Busqueda vehiculos, facade local: " + e.getMessage());
        }
        return vehiculo;
    }
    
       @Override
    public List<Vehiculo> findByCreditVin(String vin) {
        List<Vehiculo> vehiculo = new ArrayList();

        String nQuery = "select vh.* from Vehiculo vh inner join Valoracion v on v.Vehiculo = vh.ID"
                + " where v.solicitar_Credito = 1 and vh.estatus_Row>0 and v.estatus_Row>0 and  "
                + "(vh.vin like ?) "
                + "and not exists (Select null from Credito c where c.Vehiculo = vh.ID and estatus_row >0)  ";
        try {
            Query query = em.createNativeQuery(nQuery, Vehiculo.class);
            query.setParameter(1, vin + "%");
           
            vehiculo = query.getResultList();

        } catch (NoResultException nre) {
            return null;
        } catch (EJBException ejbe) {
            out.println("E/ Busqueda vehiculos, facade local: " + ejbe.getMessage());
            throw new EJBException(ejbe);
        } catch (Exception e) {
            out.println("E/ Busqueda vehiculos, facade local: " + e.getMessage());
        }
        return vehiculo;
    }
    
          @Override
    public Vehiculo findByCreditoValoracionPlaca(String placa) {
        Vehiculo vehiculo = new Vehiculo();

        String nQuery = "select vh.* from Vehiculo vh inner join Valoracion v on v.Vehiculo = vh.ID"
                + " where v.solicitar_Credito = 1 and vh.estatus_Row>0 and v.estatus_Row>0 and  "
                + "(vh.placa = ?) "
                + "and not exists (Select null from Credito c where c.Vehiculo = vh.ID and estatus_row >0)  ";
        try {
            Query query = em.createNativeQuery(nQuery, Vehiculo.class);
            query.setParameter(1, placa);
           
            vehiculo = (Vehiculo) query.getSingleResult();

        } catch (NoResultException nre) {
            return null;
        } catch (EJBException ejbe) {
            out.println("E/ Busqueda vehiculos, facade local: " + ejbe.getMessage());
            throw new EJBException(ejbe);
        } catch (Exception e) {
            out.println("E/ Busqueda vehiculos, facade local: " + e.getMessage());
        }
        return vehiculo;
    }
    
       @Override
    public Vehiculo findByCreditoValoracionVin(String vin) {
        Vehiculo vehiculo = new Vehiculo();

        String nQuery = "select vh.* from Vehiculo vh inner join Valoracion v on v.Vehiculo = vh.ID"
                + " where v.solicitar_Credito = 1 and vh.estatus_Row>0 and v.estatus_Row>0 and  "
                + "(vh.vin = ?) "
                + "and not exists (Select null from Credito c where c.Vehiculo = vh.ID and estatus_row >0)  ";
        try {
            Query query = em.createNativeQuery(nQuery, Vehiculo.class);
            query.setParameter(1, vin );
           
            vehiculo = (Vehiculo) query.getSingleResult();

        } catch (NoResultException nre) {
            return null;
        } catch (EJBException ejbe) {
            out.println("E/ Busqueda vehiculos, facade local: " + ejbe.getMessage());
            throw new EJBException(ejbe);
        } catch (Exception e) {
            out.println("E/ Busqueda vehiculos, facade local: " + e.getMessage());
        }
        return vehiculo;
    }
    
     @Override
    public Vehiculo findByCreditoValoracionVinPlaca(String vin,String placa) {
        Vehiculo vehiculo = new Vehiculo();

        String nQuery = "select vh.* from Vehiculo vh inner join Valoracion v on v.Vehiculo = vh.ID"
                + " where v.solicitar_Credito = 1 and vh.estatus_Row>0 and v.estatus_Row>0 and  "
                + "(vh.vin = ? or vh.placa = ?) "
                + "and not exists (Select null from Credito c where c.Vehiculo = vh.ID and estatus_row >0)  ";
        try {
            Query query = em.createNativeQuery(nQuery, Vehiculo.class);
            query.setParameter(1, vin );
            query.setParameter(2, placa );
           
            vehiculo = (Vehiculo) query.getSingleResult();

        } catch (NoResultException nre) {
            return null;
        } catch (EJBException ejbe) {
            out.println("E/ Busqueda vehiculos, facade local: " + ejbe.getMessage());
            throw new EJBException(ejbe);
        } catch (Exception e) {
            out.println("E/ Busqueda vehiculos, facade local: " + e.getMessage());
        }
        return vehiculo;
    }
}
