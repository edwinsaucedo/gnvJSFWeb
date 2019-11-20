/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.view.backing;

import com.gnv.business.ejb.modelo.Conversion;
import com.gnv.business.ejb.modelo.Estatus;
import com.gnv.business.ejb.modelo.Objeto;
import com.gnv.business.ejb.modelo.Revision;
import com.gnv.business.ejb.modelo.Usuario;
import com.gnv.business.ejb.persistencia.ConversionFacadeLocal;
import com.gnv.business.ejb.persistencia.EstatusFacadeLocal;
import com.gnv.business.ejb.persistencia.RevisionFacadeLocal;
import com.gnv.business.ejb.modelo.Valoracion;
import com.gnv.business.ejb.modelo.Vehiculo;
import com.gnv.business.ejb.persistencia.ObjetoFacadeLocal;
import com.gnv.business.ejb.persistencia.ValoracionFacadeLocal;
import com.gnv.business.ejb.persistencia.VehiculoFacadeLocal;
import com.gnv.views.factories.BitacoraActividadesFactory;
import com.gnv.views.utils.Constantes;
import com.gnv.views.utils.Propiedades;
import java.io.Serializable;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author PC2
 */
@ManagedBean
@ViewScoped
public class ListaRevisionManagedBean implements Serializable {

    //Facade
    @EJB
    private ConversionFacadeLocal conversionFacade;

    @EJB
    private EstatusFacadeLocal estatusFacade;

    @EJB
    private RevisionFacadeLocal revisionFacade;

    @EJB
    private ValoracionFacadeLocal valoracionFacade;

    @EJB
    private VehiculoFacadeLocal vehiculoFacade;
    @EJB
    private ObjetoFacadeLocal objetoFacade;

    @ManagedProperty("#{sessionManagedBean.usuario}")
    private Usuario usuarioLogueado;

   

    //Listas
    private List<Conversion> listaConversion;
    private List<Vehiculo> listaVehiculo;
    private List<Revision> listaRevision;
    private List<Valoracion> listaValoracion;

    //Objetos
    private Objeto objeto;
    private Estatus estatus;
    private Revision revision;
    private Conversion conversion;

    public ListaRevisionManagedBean() {
    }

    @PostConstruct
    public void init() {
        try {
            revision = new Revision();
            estatus = estatusFacade.findByClase(Conversion.class.getName(), Constantes.ESTATUS_CONVERTIDO);

            listaRevision = revisionFacade.findByRevision();
            listaVehiculo = vehiculoFacade.findByDate();
            listaConversion = conversionFacade.findByDate();
            listaValoracion = new ArrayList();

            //   construirData();
            //listaRevision.get(0).getVehiculo().getValoracionList().get(0).getConversionList().get(0).getTaller();
        } catch (Exception e) {
            out.println("E/GVN CargarDatos: " + e.getMessage());
            //Registrar en bitacora de exepciones

        }
    }

    public void eliminar() {
        String mensaje,actividad;
        try {
            String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id_e");
            actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_ELIMINAR;
            mensaje = Propiedades.getMensaje(Constantes.CATALOGO_TALLER_LISTA_ELIMINO);
            revision = revisionFacade.find(Integer.valueOf(id));
            revision.setEstatusRow(-1);
            revisionFacade.edit(revision);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
            objeto = objetoFacade.findByNombre(Constantes.OBJETO_REVISION);
            BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto, Integer.valueOf(id), actividad);
            init();

        } catch (Exception e) {
            out.println("E/GVN ListaRevision: " + e.getMessage());
            //Registrar en bitacora de exepciones.
            mensaje = Propiedades.getMensaje(Constantes.CATALOGO_ERROR_GENERAL);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, mensaje));
        }
    }


    public List<Conversion> getListaConversion() {
        return listaConversion;
    }

    public void setListaConversion(List<Conversion> listaConversion) {
        this.listaConversion = listaConversion;
    }

    public List<Revision> getListaRevision() {
        return listaRevision;
    }

    public void setListaRevision(List<Revision> listaRevision) {
        this.listaRevision = listaRevision;
    }

    public Conversion getConversion() {
        return conversion;
    }

    public void setConversion(Conversion conversion) {
        this.conversion = conversion;
    }
    public List<Vehiculo> getListaVehiculo() {
        return listaVehiculo;
    }

    public void setListaVehiculo(List<Vehiculo> listaVehiculo) {
        this.listaVehiculo = listaVehiculo;
    }

    public Usuario getUsuarioLogueado()
    {
        return usuarioLogueado;
    }

    public void setUsuarioLogueado(Usuario usuarioLogueado)
    {
        this.usuarioLogueado = usuarioLogueado;
    }
    
    
}
