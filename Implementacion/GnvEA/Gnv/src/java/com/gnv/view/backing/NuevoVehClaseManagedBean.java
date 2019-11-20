/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.view.backing;

import com.gnv.business.ejb.modelo.Objeto;
import com.gnv.business.ejb.modelo.Usuario;
import com.gnv.business.ejb.modelo.VehClase;
import com.gnv.business.ejb.modelo.VehClase;
import com.gnv.business.ejb.persistencia.ObjetoFacadeLocal;
import com.gnv.business.ejb.persistencia.VehClaseFacadeLocal;
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
public class NuevoVehClaseManagedBean implements Serializable {

    @ManagedProperty("#{sessionManagedBean.usuario}")
    private Usuario usuarioLogueado;

    @EJB
    private VehClaseFacadeLocal vehClaseFacade;
    @EJB
    private ObjetoFacadeLocal objetoFacade;

    private VehClase vehClase;
    private String titulo;
    private Objeto objeto;

    @PostConstruct
    public void init() {
        try {
            String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
            if (id != null) {
                vehClase = vehClaseFacade.find(Integer.valueOf(id));
                titulo = Constantes.VEH_CLASE_EDITAR_TITULO;
            } else {
                vehClase = new VehClase();
                titulo = Constantes.VEH_CLASE_NUEVO_TITULO;
            }

        } catch (Exception e) {
            out.println("E/GVN NuevoVehClases: " + e.getMessage());
            //Registrar en bitacora de exepciones.
        }

    }

    public void guardar() {

        String mensaje = "", actividad = "";
        try {
            objeto = objetoFacade.findByNombre(Constantes.OBJETO_CLASE_VEHICULO);
            vehClase.setEstatusRow(1);
            if (vehClase.getId() != null) {
                //Falta establecer el tipo de activadad pra llenar la bitacora y falta el registro en bitacora.
                mensaje = Propiedades.getMensaje(Constantes.CATALOGO_CLASE_VEHICULO_NUEVO_EDITO);
                actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_EDITAR;
                vehClaseFacade.edit(vehClase);
                BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto, vehClase.getId(), actividad);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
            } else {
                actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_NUEVO;
                vehClaseFacade.create(vehClase);
                BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto, vehClase.getId(), actividad);

                if (vehClase.getId() != null) {
                    mensaje = Propiedades.getMensaje(Constantes.CATALOGO_CLASE_VEHICULO_NUEVO_GUARDO);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
                    vehClase = new VehClase();
                } else {
                    mensaje = Propiedades.getMensaje(Constantes.CATALOGO_CLASE_VEHICULO_NUEVO_GUARDO_ERROR);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, mensaje));
                }

            }

        } catch (Exception e) {
            out.println("E/GVN NuevoVehClase : " + e.getMessage());
            //Registrar en bitacora de exepciones.
            mensaje = Propiedades.getMensaje(Constantes.CATALOGO_ERROR_GENERAL);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, mensaje));
        }

    }

    public NuevoVehClaseManagedBean() {
    }

    public VehClase getVehClase() {
        return vehClase;
    }

    public void setVehClase(VehClase vehClase) {
        this.vehClase = vehClase;
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
