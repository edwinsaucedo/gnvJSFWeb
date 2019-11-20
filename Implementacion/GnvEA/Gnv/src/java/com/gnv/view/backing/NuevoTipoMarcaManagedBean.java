/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.view.backing;

import com.gnv.business.ejb.modelo.Objeto;
import com.gnv.business.ejb.modelo.TipoMarca;
import com.gnv.business.ejb.modelo.Usuario;
import com.gnv.business.ejb.persistencia.ObjetoFacadeLocal;
import com.gnv.business.ejb.persistencia.TipoMarcaFacadeLocal;
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
 * @author Edwin
 */
@ManagedBean
@ViewScoped
public class NuevoTipoMarcaManagedBean implements Serializable {

    @ManagedProperty("#{sessionManagedBean.usuario}")
    private Usuario usuarioLogueado;

    @EJB
    private TipoMarcaFacadeLocal tipoMarcaFacade;

    @EJB
    private ObjetoFacadeLocal objetoFacade;

    private Objeto objeto;
    private TipoMarca tipoMarca;
    private String titulo;

    @PostConstruct
    public void init() {
        try {
            String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
            if (id != null) {
                tipoMarca = tipoMarcaFacade.find(Integer.valueOf(id));
                titulo = Constantes.TIPO_MARCA_EDITAR_TITULO;
            } else {
                tipoMarca = new TipoMarca();
                titulo = Constantes.TIPO_MARCA_NUEVO_TITULO;

            }

        } catch (Exception e) {
            out.println("E/GVN NuevoTipoMarca: " + e.getMessage());
            //Registrar en bitacora de exepciones.
        }
    }

    public void guardar() {
        String mensaje = "", actividad = "";
        objeto = objetoFacade.findByNombre(Constantes.OBJETO_TIPO_MARCA);
        try {
            if (tipoMarca.getId() != null) {
                mensaje = Propiedades.getMensaje(Constantes.CATALOGO_TIPO_MARCA_NUEVO_EDITO);
                actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_EDITAR;

                tipoMarcaFacade.edit(tipoMarca);
                BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto, tipoMarca.getId(), actividad);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
            } else {
                actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_NUEVO;
                tipoMarcaFacade.create(tipoMarca);
                if (tipoMarca.getId() != null) {

                    mensaje = Propiedades.getMensaje(Constantes.CATALOGO_TIPO_MARCA_NUEVO_GUARDO);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
                    BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto, tipoMarca.getId(), actividad);
                    tipoMarca = new TipoMarca();

                } else {
                    mensaje = Propiedades.getMensaje(Constantes.CATALOGO_TIPO_MARCA_NUEVO_GUARDO_ERROR);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, mensaje));

                }

            }

        } catch (Exception e) {

            out.println("E/GVN NuevoTipoMarca: " + e.getMessage());
            //Registrar en bitacora de exepciones.
            mensaje = Propiedades.getMensaje(Constantes.CATALOGO_ERROR_GENERAL);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, mensaje));
        }

    }

    public NuevoTipoMarcaManagedBean() {
    }

    public TipoMarca getTipoMarca() {
        return tipoMarca;
    }

    public void setTipoMarca(TipoMarca tipoMarca) {
        this.tipoMarca = tipoMarca;
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
