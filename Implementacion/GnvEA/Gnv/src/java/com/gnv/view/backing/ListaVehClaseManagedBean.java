/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.view.backing;

import com.gnv.business.ejb.modelo.Objeto;
import com.gnv.business.ejb.modelo.Usuario;
import com.gnv.business.ejb.modelo.VehClase;
import com.gnv.business.ejb.persistencia.ObjetoFacadeLocal;
import com.gnv.business.ejb.persistencia.VehClaseFacadeLocal;
import com.gnv.views.factories.BitacoraActividadesFactory;
import com.gnv.views.utils.Constantes;
import com.gnv.views.utils.Propiedades;
import java.io.Serializable;
import static java.lang.System.out;
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
public class ListaVehClaseManagedBean implements Serializable {

    @ManagedProperty("#{sessionManagedBean.usuario}")
    private Usuario usuarioLogueado;

    private List<VehClase> vehClaseList;
    private VehClase vehClase;

    @EJB
    private VehClaseFacadeLocal vehClaseFacade;

    @EJB
    private ObjetoFacadeLocal objetoFacade;

    @PostConstruct
    public void init() {
        try {
            vehClaseList = vehClaseFacade.findVehClase();
        } catch (Exception e) {
            out.println("E/GVN ListaVehClase: " + e.getMessage());
            //Registrar en bitacora de exepciones.
        }

    }

    public ListaVehClaseManagedBean() {
    }

    public void eliminar() {
        String mensaje, actividad;

        try {
            String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id_e");
            Objeto objeto = objetoFacade.findByNombre(Constantes.OBJETO_CLASE_VEHICULO);
            mensaje = Propiedades.getMensaje(Constantes.CATALOGO_CLASE_VEHICULO_LISTA_ELIMINO);
            actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_ELIMINAR;
            vehClase = vehClaseFacade.find(Integer.valueOf(id));
            vehClase.setEstatusRow(-1);
            vehClaseFacade.edit(vehClase);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
            BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto,Integer.valueOf(id),actividad);
            init();

        } catch (Exception e) {
            out.println("E/GVN ListaVehClase: " + e.getMessage());
            //Registrar en bitacora de exepciones.
            mensaje = Propiedades.getMensaje(Constantes.CATALOGO_ERROR_GENERAL);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, mensaje));

        }
    }

    public List<VehClase> getVehClaseList() {
        return vehClaseList;
    }

    public void setVehClaseList(List<VehClase> vehClaseList) {
        this.vehClaseList = vehClaseList;
    }

    public VehClase getVehClase() {
        return vehClase;
    }

    public void setVehClase(VehClase vehClase) {
        this.vehClase = vehClase;
    }

    public Usuario getUsuarioLogueado() {
        return usuarioLogueado;
    }

    public void setUsuarioLogueado(Usuario usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
    }

    
}
