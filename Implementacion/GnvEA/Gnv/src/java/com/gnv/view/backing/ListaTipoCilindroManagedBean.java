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
 * @author ReneVl
 */
@ManagedBean
@ViewScoped
public class ListaTipoCilindroManagedBean implements Serializable {

    @ManagedProperty("#{sessionManagedBean.usuario}")
    private Usuario usuarioLogueado;

    private List<TipoCilindro> tipoCilindroList;
    private TipoCilindro tipoCilindro;

    @EJB
    private TipoCilindroFacadeLocal tipoCilindroFacade;

    @EJB
    private ObjetoFacadeLocal objetoFacade;

    @PostConstruct
    public void init() {
        try {
            tipoCilindroList = tipoCilindroFacade.findByEstatusRow();
        } catch (Exception e) {
            out.println("E/GVN ListaTipoCilindros: " + e.getMessage());
            //Registrar en bitacora de exepciones.
        }
    }

    public ListaTipoCilindroManagedBean() {
    }

    public void eliminar() {
        //String actividad;
        String mensaje, actividad;
        try {
            String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id_e");
            Objeto objeto = objetoFacade.findByNombre(Constantes.OBJETO_TIPO_CILINDRO);
            actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_ELIMINAR;
            //actividad = Propiedades.getMensaje(Constantes.BITACORA_ACTIVIDAD_ELIMINO_USUARIO); Falta registar en la bitacora de actividades.
            mensaje = Propiedades.getMensaje(Constantes.CATALOGO_TIPO_CILINDRO_LISTA_ELIMINO);
            tipoCilindro = tipoCilindroFacade.find(Integer.valueOf(id));
            tipoCilindro.setEstatusRow(-1);
            tipoCilindroFacade.edit(tipoCilindro);
            BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto, Integer.valueOf(id), actividad);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
            init();
        } catch (Exception e) {
            out.println("E/GVN ListaTipoCilindros: " + e.getMessage());
            //Registrar en bitacora de exepciones.
            mensaje = Propiedades.getMensaje(Constantes.CATALOGO_ERROR_GENERAL);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, mensaje));

        }
    }

    public TipoCilindro getTipoCilindro() {
        return tipoCilindro;
    }

    public void setTipoCilindro(TipoCilindro tipoCilindro) {
        this.tipoCilindro = tipoCilindro;
    }

    public List<TipoCilindro> getTipoCilindroList() {
        return tipoCilindroList;
    }

    public void setTipoCilindroList(List<TipoCilindro> tipoCilindroList) {
        this.tipoCilindroList = tipoCilindroList;
    }

    public Usuario getUsuarioLogueado() {
        return usuarioLogueado;
    }

    public void setUsuarioLogueado(Usuario usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
    }

    
}
