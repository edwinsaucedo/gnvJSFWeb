/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.view.backing;

import com.gnv.business.ejb.modelo.Objeto;
import com.gnv.business.ejb.modelo.Usuario;
import com.gnv.business.ejb.modelo.VehTipo;
import com.gnv.business.ejb.persistencia.ObjetoFacadeLocal;
import com.gnv.business.ejb.persistencia.VehTipoFacadeLocal;
import com.gnv.views.factories.BitacoraActividadesFactory;

import com.gnv.views.utils.Constantes;
import com.gnv.views.utils.Propiedades;
import java.io.Serializable;
import static java.lang.System.out;
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
public class NuevoVehTipoManagedBean implements Serializable {

    @ManagedProperty("#{sessionManagedBean.usuario}")
    private Usuario usuarioLogueado;

    @EJB
    private VehTipoFacadeLocal vehTipoFacade;

    @EJB
    private ObjetoFacadeLocal objetoFacade;

    private VehTipo vehTipo;
    private String titulo;
    private Objeto objeto;

    @PostConstruct
    public void init() {
        try {
            String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
            if (id != null) {
                vehTipo = vehTipoFacade.find(Integer.valueOf(id));
                titulo = Constantes.TIPO_VEHICULO_EDITAR_TITULO;
            } else {
                vehTipo = new VehTipo();
                titulo = Constantes.TIPO_VEHICULO_NUEVO_TITULO;
            }

        } catch (Exception e) {
            out.println("E/GVN NuevoVehTipo s: " + e.getMessage());
            //Registrar en bitacora de exepciones.
        }

    }

    public void guardar() {

        String mensaje = "", actividad = "";
        try {
            objeto = objetoFacade.findByNombre(Constantes.OBJETO_TIPO_VEHICULO);
            vehTipo.setEstatusRow(1);
            if (vehTipo.getId() != null) {
                //Falta establecer el tipo de activadad pra llenar la bitacora y falta el registro en bitacora.
                mensaje = Propiedades.getMensaje(Constantes.CATALOGO_TIPO_VEHICULO_NUEVO_EDITO);
                actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_EDITAR;
                vehTipoFacade.edit(vehTipo);
                BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto, vehTipo.getId(), actividad);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
            } else {
                actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_NUEVO;
                vehTipoFacade.create(vehTipo);
                BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto, vehTipo.getId(), actividad);

                if (vehTipo.getId() != null) {
                    mensaje = Propiedades.getMensaje(Constantes.CATALOGO_TIPO_VEHICULO_NUEVO_GUARDO);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
                    vehTipo = new VehTipo();
                } else {
                    mensaje = Propiedades.getMensaje(Constantes.CATALOGO_TIPO_VEHICULO_NUEVO_GUARDO_ERROR);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, mensaje));
                }

            }

        } catch (Exception e) {
            out.println("E/GVN NuevoVehTipo : " + e.getMessage());
            //Registrar en bitacora de exepciones.
            mensaje = Propiedades.getMensaje(Constantes.CATALOGO_ERROR_GENERAL);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, mensaje));
        }

    }

    public NuevoVehTipoManagedBean() {
    }

    public VehTipo getVehTipo() {
        return vehTipo;
    }

    public void setVehTipo(VehTipo vehTipo) {
        this.vehTipo = vehTipo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Usuario getUsuarioLogueado() {
        return usuarioLogueado;
    }

    public void setUsuarioLogueado(Usuario usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
    }

    
}
