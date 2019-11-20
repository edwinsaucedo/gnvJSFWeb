/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.view.backing;

import com.gnv.business.ejb.modelo.Valoracion;
import com.gnv.business.ejb.persistencia.ValoracionFacadeLocal;
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
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author PC2
 */
@ManagedBean
@ViewScoped
public class ListaValoracionManagedBean implements Serializable {

    private List<Valoracion> listaValoracion;
    private Valoracion valoracion;

    @EJB
    private ValoracionFacadeLocal valoracionFacade;

    public ListaValoracionManagedBean() {
    }

    @PostConstruct
    public void init() {
        try {
            listaValoracion = valoracionFacade.findValoracion();
        } catch (Exception e) {
            out.println("E/GVN ListaValoracion: " + e.getMessage());
            //Registrar en bitacora de exepciones.
        }
    }

    public void eliminar() {
        String mensaje;
        try {
            String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id_e");
            mensaje = Propiedades.getMensaje(Constantes.PROCESO_VALORACION_LISTA_ELIMINO);
            valoracion = valoracionFacade.find(Integer.valueOf(id));
            valoracion.setEstatusRow(-1);
            valoracionFacade.edit(valoracion);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
            init();

        } catch (Exception e) {
            out.println("E/GVN ListaLegalizacion: " + e.getMessage());
            //Registrar en bitacora de exepciones.
            mensaje = Propiedades.getMensaje(Constantes.CATALOGO_ERROR_GENERAL);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, mensaje));
        }
    }

    public List<Valoracion> getListaValoracion() {
        return listaValoracion;
    }

    public void setListaValoracion(List<Valoracion> listaValoracion) {
        this.listaValoracion = listaValoracion;
    }

    public Valoracion getValoracion() {
        return valoracion;
    }

    public void setValoracion(Valoracion valoracion) {
        this.valoracion = valoracion;
    }
    
}
