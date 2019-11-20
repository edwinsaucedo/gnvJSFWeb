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
 * @author Edwin
 */
@ManagedBean
@ViewScoped
public class ListaTipoMarcaManagedBean implements Serializable {

    @ManagedProperty("#{sessionManagedBean.usuario}")
    private Usuario usuarioLogueado;

    private List<TipoMarca> tipoMarcaList;
    private TipoMarca tipoMarca;

    @EJB
    private TipoMarcaFacadeLocal tipoMarcaFacade;

    @EJB
    private ObjetoFacadeLocal objetoFacade;

    @PostConstruct
    public void init() {
        try {
            tipoMarcaList = tipoMarcaFacade.findAll();
        } catch (Exception e) {
            out.println("E/GVN ListaTipoMarcas: " + e.getMessage());
            //Registrar en bitacora de exepciones.
        }
    }

    public ListaTipoMarcaManagedBean() {
    }

    public void eliminar() {

        String mensaje, actividad;
        try {
            String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id_del");
            mensaje = Propiedades.getMensaje(Constantes.CATALOGO_TIPO_MARCA_LISTA_ELIMINO);
            Objeto objeto = objetoFacade.findByNombre(Constantes.OBJETO_TIPO_MARCA);
            actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_ELIMINAR;
            tipoMarca = tipoMarcaFacade.find(Integer.valueOf(id));
            tipoMarcaFacade.remove(tipoMarca);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
            init();
            BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto, Integer.valueOf(id), actividad);

        } catch (Exception e) {

            out.println("E/GVN ListaTipoMarcas: " + e.getMessage());
            //Registrar en bitacora de exepciones.
            mensaje = Propiedades.getMensaje(Constantes.CATALOGO_ERROR_GENERAL);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, mensaje));
        }

    }

    public List<TipoMarca> getTipoMarcaList() {
        return tipoMarcaList;
    }

    public void setTipoMarcaList(List<TipoMarca> tipoMarcaList) {
        this.tipoMarcaList = tipoMarcaList;
    }

    public TipoMarca getTipoMarca() {
        return tipoMarca;
    }

    public void setTipoMarca(TipoMarca tipoMarca) {
        this.tipoMarca = tipoMarca;
    }

    public Usuario getUsuarioLogueado() {
        return usuarioLogueado;
    }

    public void setUsuarioLogueado(Usuario usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
    }

}
