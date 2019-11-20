/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.view.backing;

import com.gnv.business.ejb.modelo.Objeto;
import com.gnv.business.ejb.modelo.Usuario;
import com.gnv.business.ejb.modelo.VehTipoServicio;
import com.gnv.business.ejb.persistencia.ObjetoFacadeLocal;
import com.gnv.business.ejb.persistencia.VehTipoServicioFacadeLocal;
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
public class NuevoVehTipoServicioManagedBean implements Serializable {

    @ManagedProperty("#{sessionManagedBean.usuario}")
    private Usuario usuarioLogueado;

    @EJB
    private VehTipoServicioFacadeLocal vehTipoServicioFacade;

    @EJB
    private ObjetoFacadeLocal objetoFacade;

    private VehTipoServicio vehTipoServicio;
    private String titulo;
    private Objeto objeto;

    @PostConstruct
    public void init() {
        try {
            String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
            if (id != null) {
                vehTipoServicio = vehTipoServicioFacade.find(Integer.valueOf(id));
                titulo = Constantes.TIPO_SERVICIO_EDITAR_TITULO;
            } else {
                vehTipoServicio = new VehTipoServicio();
                titulo = Constantes.TIPO_SERVICIO_NUEVO_TITULO;
            }

        } catch (Exception e) {
            out.println("E/GVN NuevoVehTipoServicios: " + e.getMessage());
            //Registrar en bitacora de exepciones.
        }

    }

    public void guardar() {

        String mensaje = "", actividad = "";
        try {
            vehTipoServicio.setEstatusRow(1);
            if (vehTipoServicio.getId() != null) {
                //Falta establecer el tipo de activadad pra llenar la bitacora y falta el registro en bitacora.
                mensaje = Propiedades.getMensaje(Constantes.CATALOGO_VEH_TIPO_SERVICIO_NUEVO_EDITO);
                actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_EDITAR;
                vehTipoServicioFacade.edit(vehTipoServicio);
                BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto, vehTipoServicio.getId(), actividad);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
            } else {
                actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_NUEVO;
                vehTipoServicioFacade.create(vehTipoServicio);
                BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto, vehTipoServicio.getId(), actividad);

                if (vehTipoServicio.getId() != null) {
                    mensaje = Propiedades.getMensaje(Constantes.CATALOGO_VEH_TIPO_SERVICIO_NUEVO_GUARDO);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
                    vehTipoServicio = new VehTipoServicio();
                } else {
                    mensaje = Propiedades.getMensaje(Constantes.CATALOGO_VEH_TIPO_SERVICIO_NUEVO_GUARDO_ERROR);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, mensaje));
                }

            }

        } catch (Exception e) {
            out.println("E/GVN NuevoVehTipoServicio: " + e.getMessage());
            //Registrar en bitacora de exepciones.
            mensaje = Propiedades.getMensaje(Constantes.CATALOGO_ERROR_GENERAL);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, mensaje));
        }

    }

    public NuevoVehTipoServicioManagedBean() {
    }

    public VehTipoServicio getVehTipoServicio() {
        return vehTipoServicio;
    }

    public void setVehTipoServicio(VehTipoServicio vehTipoServicio) {
        this.vehTipoServicio = vehTipoServicio;
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
