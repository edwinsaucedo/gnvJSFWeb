/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.view.backing;

import com.gnv.business.ejb.modelo.Objeto;
import com.gnv.business.ejb.modelo.TipoConversion;
import com.gnv.business.ejb.modelo.Usuario;
import com.gnv.business.ejb.persistencia.ObjetoFacadeLocal;
import com.gnv.business.ejb.persistencia.TipoConversionFacadeLocal;
import com.gnv.views.factories.BitacoraActividadesFactory;
import com.gnv.views.utils.Constantes;
import com.gnv.views.utils.Propiedades;
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
public class NuevoTipoConversionManagedBean {

    @ManagedProperty("#{sessionManagedBean.usuario}")
    private Usuario usuarioLogueado;

    @EJB
    private TipoConversionFacadeLocal tipoConversionFacade;

    @EJB
    private ObjetoFacadeLocal objetoFacade;

    private TipoConversion tipoConversion;
    private String titulo;
    private Objeto objeto;

    public NuevoTipoConversionManagedBean() {
    }

    @PostConstruct
    public void init() {
        try {
            String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
            if (id != null) {
                tipoConversion = tipoConversionFacade.find(Integer.valueOf(id));
                titulo = Constantes.TIPO_CONVERSION_EDITAR_TITULO;
            } else {
                tipoConversion = new TipoConversion();
                titulo = Constantes.TIPO_CONVERSION_NUEVO_TITULO;
            }
        } catch (Exception e) {
            out.println("E/GVN NuevoTipoConversiones: " + e.getMessage());
            //Registrar en bitacora de exepciones.
        }
    }

    public void guardar() {
        String mensaje = "", actividad = "";
        try {
            objeto = objetoFacade.findByNombre(Constantes.OBJETO_TIPO_CONVERSION);
            tipoConversion.setEstatusRow(1);
            if (tipoConversion.getId() != null) {
                //Falta establecer el tipo de activadad pra llenar la bitacora y falta el registro en bitacora.
                mensaje = Propiedades.getMensaje(Constantes.CATALOGO_TIPO_CONVERSION_NUEVO_EDITO);
                actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_EDITAR;
                tipoConversionFacade.edit(tipoConversion);
                BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto, tipoConversion.getId(), actividad);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
            } else {
                actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_NUEVO;
                tipoConversionFacade.create(tipoConversion);
                if (tipoConversion.getId() != null) {
                    mensaje = Propiedades.getMensaje(Constantes.CATALOGO_TIPO_CONVERSION_NUEVO_GUARDO);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
                    BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto, tipoConversion.getId(), actividad);
                    tipoConversion = new TipoConversion();
                } else {
                    mensaje = Propiedades.getMensaje(Constantes.CATALOGO_TIPO_CONVERSION_NUEVO_GUARDO_ERROR);

                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, mensaje));
                }

            }
        } catch (Exception e) {
            out.println("E/GVN NuevoTipoConversiones: " + e.getMessage());
            //Registrar en bitacora de exepciones.
            mensaje = Propiedades.getMensaje(Constantes.CATALOGO_ERROR_GENERAL);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, mensaje));
        }

    }

    public TipoConversion getTipoConversion() {
        return tipoConversion;
    }

    public void setTipoConversion(TipoConversion tipoConversion) {
        this.tipoConversion = tipoConversion;
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
