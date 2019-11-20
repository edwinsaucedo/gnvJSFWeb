/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.view.backing;

import com.gnv.business.ejb.modelo.CreditoCotizacion;
import com.gnv.business.ejb.modelo.CreditoCotizacionAmortiza;
import com.gnv.business.ejb.modelo.Objeto;
import com.gnv.business.ejb.modelo.Usuario;
import com.gnv.business.ejb.persistencia.CreditoCotizacionAmortizaFacadeLocal;
import com.gnv.business.ejb.persistencia.CreditoCotizacionFacadeLocal;
import com.gnv.business.ejb.persistencia.ObjetoFacadeLocal;
import com.gnv.views.factories.BitacoraActividadesFactory;
import com.gnv.views.utils.Constantes;
import com.gnv.views.utils.Propiedades;
import java.io.Serializable;
import static java.lang.System.out;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
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
public class ListaCotizacionManagedBean implements Serializable {

    @ManagedProperty("#{sessionManagedBean.usuario}")
    private Usuario usuarioLogueado;

    private List<CreditoCotizacion> cotizacionesList;
    private List<CreditoCotizacionAmortiza> cotizacionesAmortizaList;
    private CreditoCotizacion creditoCotizacion;
    private CreditoCotizacionAmortiza creditoCotizacionAmortiza;
    private Objeto objeto;

    @EJB
    private CreditoCotizacionFacadeLocal cotizacionFacade;

    @EJB
    private ObjetoFacadeLocal objetoFacade;

    @EJB
    private CreditoCotizacionAmortizaFacadeLocal cotizacionAmortizaFacade;

    public ListaCotizacionManagedBean() {
    }

    @PostConstruct
    public void init() {
        creditoCotizacion = new CreditoCotizacion();
        try {
            cotizacionesList = cotizacionFacade.findByEstatusRow();

        } catch (Exception e) {
            out.println("E/GVN ListaCotizaciones: " + e.getMessage());
        }
    }

    public void eliminar() {
        String mensaje, actividad;
        try {
            actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_ELIMINAR;
            objeto = objetoFacade.findByNombre(Constantes.OBJETO_CREDITO_COTIZACION);
            String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id_e");
            creditoCotizacion = cotizacionFacade.find(Integer.valueOf(id));
            creditoCotizacion.setEstatusRow(-1);
            cotizacionFacade.edit(creditoCotizacion);
            BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto, creditoCotizacion.getId(), actividad);
            mensaje = Propiedades.getMensaje(Constantes.PROCESO_COTIZACION_LISTA_ELIMINO);
            if (creditoCotizacion.getId() != null) {

                cotizacionesAmortizaList = cotizacionAmortizaFacade.findByEstatusRow(creditoCotizacion);

                    if (cotizacionesAmortizaList.size() > 0) {
                    objeto = objetoFacade.findByNombre(Constantes.OBJETO_CREDITO_COTIZACION_AMORTIZA);
                    for (int i = 0; i < cotizacionesAmortizaList.size(); i++) {
                        creditoCotizacionAmortiza = cotizacionAmortizaFacade.find(cotizacionesAmortizaList.get(i).getId());
                        creditoCotizacion.setEstatusRow(-1);
                        cotizacionAmortizaFacade.edit(creditoCotizacionAmortiza);
                        BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto, creditoCotizacionAmortiza.getId(), actividad);
                    }
                }
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
            cotizacionesList = cotizacionFacade.findByEstatusRow();

            // BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto, Integer.valueOf(id), actividad);
        } catch (Exception e) {
            out.println("E/GVN ListaLinea: " + e.getMessage());
            //Registrar en bitacora de exepciones.
            mensaje = Propiedades.getMensaje(Constantes.CATALOGO_ERROR_GENERAL);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, mensaje));

        }
    }

    public Usuario getUsuarioLogueado() {
        return usuarioLogueado;
    }

    public void setUsuarioLogueado(Usuario usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
    }

    public List<CreditoCotizacion> getCotizacionesList() {
        return cotizacionesList;
    }

    public void setCotizacionesList(List<CreditoCotizacion> cotizacionesList) {
        this.cotizacionesList = cotizacionesList;
    }

    public List<CreditoCotizacionAmortiza> getCotizacionesAmortizaList() {
        return cotizacionesAmortizaList;
    }

    public void setCotizacionesAmortizaList(List<CreditoCotizacionAmortiza> cotizacionesAmortizaList) {
        this.cotizacionesAmortizaList = cotizacionesAmortizaList;
    }

    public CreditoCotizacion getCreditoCotizacion() {
        return creditoCotizacion;
    }

    public void setCreditoCotizacion(CreditoCotizacion creditoCotizacion) {
        this.creditoCotizacion = creditoCotizacion;
    }

    public CreditoCotizacionAmortiza getCreditoCotizacionAmortiza() {
        return creditoCotizacionAmortiza;
    }

    public void setCreditoCotizacionAmortiza(CreditoCotizacionAmortiza creditoCotizacionAmortiza) {
        this.creditoCotizacionAmortiza = creditoCotizacionAmortiza;
    }

}
