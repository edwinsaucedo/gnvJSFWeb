/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.view.backing;

import com.gnv.business.ejb.modelo.Objeto;
import com.gnv.business.ejb.modelo.TipoCilindro;
import com.gnv.business.ejb.modelo.Usuario;
import com.gnv.business.ejb.persistencia.ObjetoFacadeLocal;
import com.gnv.business.ejb.persistencia.TipoCilindroFacadeLocal;
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
 * @author Rene Vl
 */
@ManagedBean
@ViewScoped
public class NuevoTipoCilindroManagedBean implements Serializable {

    @ManagedProperty("#{sessionManagedBean.usuario}")
    private Usuario usuarioLogueado;

    @EJB
    private TipoCilindroFacadeLocal tipoCilindroFacade;

    @EJB
    private ObjetoFacadeLocal objetoFacade;

    private TipoCilindro tipoCilindro;
    private String titulo;

    @PostConstruct
    public void init() {
        try {
            String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
            if (id != null) {
                tipoCilindro = tipoCilindroFacade.find(Integer.valueOf(id));
                titulo = Constantes.TIPO_CILINDRO_EDITAR_TITULO;
            } else {
                tipoCilindro = new TipoCilindro();
                titulo = Constantes.TIPO_CILINDRO_NUEVO_TITULO;
            }
        } catch (Exception e) {
            out.println("E/GVN NuevoTipoCilindros: " + e.getMessage());
            //Registrar en bitacora de exepciones.
        }
    }

    public void guardar() {
        String mensaje = "", actividad = "";
        try {
            tipoCilindro.setEstatusRow(1);
            if (tipoCilindro.getId() != null) {
                //Falta establecer el tipo de activadad pra llenar la bitacora y falta el registro en bitacora.
                mensaje = Propiedades.getMensaje(Constantes.CATALOGO_TIPO_CILINDRO_NUEVO_EDITO);
                actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_EDITAR;
                tipoCilindroFacade.edit(tipoCilindro);
                Objeto objeto = objetoFacade.findByNombre(Constantes.OBJETO_TIPO_CILINDRO);
                BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto, tipoCilindro.getId(), actividad);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
            } else {
                tipoCilindroFacade.create(tipoCilindro);
                if (tipoCilindro.getId() != null) {
                    actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_NUEVO;
                    mensaje = Propiedades.getMensaje(Constantes.CATALOGO_TIPO_CILINDRO_NUEVO_GUARDO);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
                    Objeto objeto = objetoFacade.findByNombre(Constantes.OBJETO_TIPO_CILINDRO);
                    BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto, tipoCilindro.getId(), actividad);
                    tipoCilindro = new TipoCilindro();
                } else {
                    mensaje = Propiedades.getMensaje(Constantes.CATALOGO_TIPO_CILINDRO_NUEVO_GUARDO_ERROR);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, mensaje));
                }

            }
        } catch (Exception e) {
            out.println("E/GVN NuevoTipoCilindros: " + e.getMessage());
            //Registrar en bitacora de exepciones.
            mensaje = Propiedades.getMensaje(Constantes.CATALOGO_ERROR_GENERAL);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, mensaje));
        }

    }

    public NuevoTipoCilindroManagedBean() {
    }

    public TipoCilindro getTipoCilindro() {
        return tipoCilindro;
    }

    public void setTipoCilindro(TipoCilindro ttipoCilindro) {
        this.tipoCilindro = ttipoCilindro;
    }

    public String getTitulo() {
        return titulo;
    }

    public Usuario getUsuarioLogueado() {
        return usuarioLogueado;
    }

    public void setUsuarioLogueado(Usuario usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
    }

}
