/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.view.backing;

import com.gnv.business.ejb.modelo.Credito;
import com.gnv.business.ejb.modelo.Objeto;
import com.gnv.business.ejb.modelo.Usuario;
import com.gnv.business.ejb.persistencia.CreditoFacadeLocal;
import com.gnv.business.ejb.persistencia.ObjetoFacadeLocal;
import com.gnv.views.factories.BitacoraActividadesFactory;
import com.gnv.views.utils.Constantes;
import com.gnv.views.utils.Propiedades;
import java.io.Serializable;
import static java.lang.System.out;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
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
public class ListaCreditoManagedBean implements Serializable {

    @ManagedProperty("#{sessionManagedBean.usuario}")
    private Usuario usuarioLogueado;

    private List<Credito> listaCredito;
    private Credito credito;

    @EJB
    private CreditoFacadeLocal creditoFacade;

    @EJB
    private ObjetoFacadeLocal objetoFacade;

    @PostConstruct
    public void init() {
        try {
            credito = new Credito();
            listaCredito = creditoFacade.findByEstatusRow();
        } catch (Exception e) {
            out.println("E/GVN ListaCredito: " + e.getMessage());
            //Registrar en bitacora de exepciones.
        }
    }

    public ListaCreditoManagedBean() {
    }

    public void eliminar() {
        String mensaje, actividad;
        try {
            String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id_e");
            Objeto objeto = objetoFacade.findByNombre(Constantes.OBJETO_LINEA);
            actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_ELIMINAR;
            mensaje = Propiedades.getMensaje(Constantes.CATALOGO_LINEA_LISTA_ELIMINO);
            credito = creditoFacade.find(Integer.valueOf(id));
            credito.setEstatusRow(-1);
                creditoFacade.edit(credito);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
            listaCredito = creditoFacade.findByEstatusRow();
            BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto, Integer.valueOf(id), actividad);
        } catch (Exception e) {
            out.println("E/GVN ListaLinea: " + e.getMessage());
            //Registrar en bitacora de exepciones.
            mensaje = Propiedades.getMensaje(Constantes.CATALOGO_ERROR_GENERAL);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, mensaje));
        }

    }

    public Usuario getUsuarioLogueado() {
        return usuarioLogueado;
    }

    public void setUsuarioLogueado(Usuario usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
    }

    public List<Credito> getListaCredito() {
        return listaCredito;
    }

    public void setListaCredito(List<Credito> listaCredito) {
        this.listaCredito = listaCredito;
    }

    public Credito getCredito() {
        return credito;
    }

    public void setCredito(Credito credito) {
        this.credito = credito;
    }

}
