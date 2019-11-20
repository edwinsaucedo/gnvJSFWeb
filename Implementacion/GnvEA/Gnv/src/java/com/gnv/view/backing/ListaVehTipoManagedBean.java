/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.view.backing;

import com.gnv.business.ejb.modelo.Objeto;
import com.gnv.business.ejb.modelo.Usuario;
import com.gnv.business.ejb.modelo.VehTipo;
import com.gnv.business.ejb.persistencia.ObjetoFacadeLocal;
import com.gnv.business.ejb.persistencia.VehTipoFacadeLocal;
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
public class ListaVehTipoManagedBean implements Serializable {

    @ManagedProperty("#{sessionManagedBean.usuario}")
    private Usuario usuarioLogueado;

    private List<VehTipo> vehTipoList;
    private VehTipo vehTipo;

    @EJB
    private VehTipoFacadeLocal vehTipoFacade;

    @EJB
    private ObjetoFacadeLocal objetoFacade;

    @PostConstruct
    public void init() {
        try {
            vehTipoList = vehTipoFacade.findVehTipo();
        } catch (Exception e) {
            out.println("E/GVN ListaVehTipo: " + e.getMessage());
            //Registrar en bitacora de exepciones.
        }

    }

    public ListaVehTipoManagedBean() {
    }

    public void eliminar() {
        String mensaje, actividad;

        try {
            String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id_e");
            Objeto objeto = objetoFacade.findByNombre(Constantes.OBJETO_TIPO_VEHICULO);
            actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_ELIMINAR;
            mensaje = Propiedades.getMensaje(Constantes.CATALOGO_TIPO_VEHICULO_LISTA_ELIMINO);
            vehTipo = vehTipoFacade.find(Integer.valueOf(id));
            vehTipo.setEstatusRow(-1);
            vehTipoFacade.edit(vehTipo);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
                 BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto,Integer.valueOf(id),actividad);
            init();

        } catch (Exception e) {
            out.println("E/GVN ListaVehTipo: " + e.getMessage());
            //Registrar en bitacora de exepciones.
            mensaje = Propiedades.getMensaje(Constantes.CATALOGO_ERROR_GENERAL);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, mensaje));

        }
    }

    public List<VehTipo> getVehTipoList() {
        return vehTipoList;
    }

    public void setVehTipoList(List<VehTipo> vehTipoList) {
        this.vehTipoList = vehTipoList;
    }

    public VehTipo getVehTipo() {
        return vehTipo;
    }

    /**
     * Creates a new instance of ListaVehTipoServicioManagedBean
     */
    //Agregar control de sesion;
    public void setVehTipo(VehTipo vehTipo) {
        this.vehTipo = vehTipo;
    }

    public Usuario getUsuarioLogueado() {
        return usuarioLogueado;
    }

    public void setUsuarioLogueado(Usuario usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
    }
    
    
}
