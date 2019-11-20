/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.view.backing;

import com.gnv.business.ejb.modelo.Legalizacion;
import com.gnv.business.ejb.modelo.Objeto;
import com.gnv.business.ejb.modelo.Usuario;
import com.gnv.business.ejb.persistencia.LegalizacionFacadeLocal;
import com.gnv.business.ejb.persistencia.ObjetoFacadeLocal;
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
 * @author PC2
 */
@ManagedBean
@ViewScoped
public class ListaLegalizacionManagedBean implements Serializable{

    private List<Legalizacion> listaLegalizacion;
    private Legalizacion legalizacion;

    @EJB
    private LegalizacionFacadeLocal legalizacionFacade;
    @EJB
    private ObjetoFacadeLocal objetoFacade;

    @ManagedProperty("#{sessionManagedBean.usuario}")
    private Usuario usuarioLogueado;

    private Objeto objeto;

    public ListaLegalizacionManagedBean() {
    }

    @PostConstruct
    public void init() {
        try {
            listaLegalizacion = legalizacionFacade.findByEstatusRow();

        } catch (Exception e) {
            out.println("E/GVN ListaLegalizacion: " + e.getMessage());
            //Registrar en bitacora de exepciones.
        }
    }

    public void eliminar() {
        String mensaje,actividad;
        try {
            String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id_e");
            mensaje = Propiedades.getMensaje(Constantes.CATALOGO_LEGALIZACION_LISTA_ELIMINO);
            actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_ELIMINAR;
            objeto = objetoFacade.findByNombre(Constantes.OBJETO_LEGALIZACION);
            legalizacion = legalizacionFacade.find(Integer.valueOf(id));
            legalizacion.setEstatusRow(-1);
            legalizacionFacade.edit(legalizacion);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
            BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto,Integer.valueOf(id), actividad);
            init();

        } catch (Exception e) {
            out.println("E/GVN ListaLegalizacion: " + e.getMessage());
            //Registrar en bitacora de exepciones.
            mensaje = Propiedades.getMensaje(Constantes.CATALOGO_ERROR_GENERAL);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, mensaje));
        }

    }

    public List<Legalizacion> getListaLegalizacion() {
        return listaLegalizacion;
    }

    public void setListaLegalizacion(List<Legalizacion> listaLegalizacion) {
        this.listaLegalizacion = listaLegalizacion;
    }

    public Legalizacion getLegalizacion() {
        return legalizacion;
    }

    public void setLegalizacion(Legalizacion legalizacion) {
        this.legalizacion = legalizacion;
    }

    public Usuario getUsuarioLogueado()
    {
        return usuarioLogueado;
    }

    public void setUsuarioLogueado(Usuario usuarioLogueado)
    {
        this.usuarioLogueado = usuarioLogueado;
    }
    
    

}
