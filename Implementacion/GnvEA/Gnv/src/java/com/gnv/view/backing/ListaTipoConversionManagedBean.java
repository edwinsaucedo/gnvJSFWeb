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
 * @author Rene Vl
 */
@ManagedBean
@ViewScoped
public class ListaTipoConversionManagedBean {

    @ManagedProperty("#{sessionManagedBean.usuario}")
    private Usuario usuarioLogueado;

    private List<TipoConversion> tipoConversionList;
    private TipoConversion tipoConversion;

    @EJB
    private TipoConversionFacadeLocal tipoConversionFacade;

    @EJB
    private ObjetoFacadeLocal objetoFacade;

    @PostConstruct
    public void init() {
        try {
            tipoConversionList = tipoConversionFacade.findByEstatusRow();
        } catch (Exception e) {
            out.println("E/GVN ListaTipoConversions: " + e.getMessage());
            //Registrar en bitacora de exepciones.
        }
    }

    public void eliminar() {
        //String actividad;
        String mensaje, actividad;
        try {
            String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id_e");
            Objeto objeto = objetoFacade.findByNombre(Constantes.OBJETO_TIPO_CONVERSION);
            actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_ELIMINAR;
            //actividad = Propiedades.getMensaje(Constantes.BITACORA_ACTIVIDAD_ELIMINO_USUARIO); Falta registar en la bitacora de actividades.
            mensaje = Propiedades.getMensaje(Constantes.CATALOGO_TIPO_CONVERSION_LISTA_ELIMINO);
            tipoConversion = tipoConversionFacade.find(Integer.valueOf(id));
            tipoConversion.setEstatusRow(-1);
            tipoConversionFacade.edit(tipoConversion);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
            init();
            BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto, Integer.valueOf(id), actividad);
        } catch (Exception e) {
            out.println("E/GVN ListaTipoConversions: " + e.getMessage());
            //Registrar en bitacora de exepciones.
            mensaje = Propiedades.getMensaje(Constantes.CATALOGO_ERROR_GENERAL);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, mensaje));

        }
    }

    public ListaTipoConversionManagedBean() {
    }

    public List<TipoConversion> getTipoConversionList() {
        return tipoConversionList;
    }

    public void setTipoConversionList(List<TipoConversion> tipoConversionList) {
        this.tipoConversionList = tipoConversionList;
    }

    public TipoConversion getTipoConversion() {
        return tipoConversion;
    }

    public void setTipoConversion(TipoConversion tipoConversion) {
        this.tipoConversion = tipoConversion;
    }

    public Usuario getUsuarioLogueado() {
        return usuarioLogueado;
    }

    public void setUsuarioLogueado(Usuario usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
    }
    
    

}
