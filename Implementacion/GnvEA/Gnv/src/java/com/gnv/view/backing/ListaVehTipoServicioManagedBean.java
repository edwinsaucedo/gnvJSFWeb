/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.view.backing;

import com.gnv.business.ejb.modelo.Objeto;
import com.gnv.business.ejb.modelo.Usuario;
import com.gnv.business.ejb.modelo.VehTipoServicio;
import com.gnv.business.ejb.persistencia.ObjetoFacadeLocal;
import com.gnv.business.ejb.persistencia.TipoCilindroFacadeLocal;
import com.gnv.business.ejb.persistencia.VehTipoServicioFacadeLocal;
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
public class ListaVehTipoServicioManagedBean implements Serializable {

    @ManagedProperty("#{sessionManagedBean.usuario}")
    private Usuario usuarioLogueado;
    
    private List<VehTipoServicio> vehTipoServicioList;
    private VehTipoServicio vehTipoServicio;
    private Objeto objeto;

    @EJB
    private VehTipoServicioFacadeLocal vehTipoServicioFacade;
    
    @EJB
    private ObjetoFacadeLocal objetoFacade;

    @PostConstruct
    public void init() {
        try {
            vehTipoServicioList = vehTipoServicioFacade.findVehTipoServicio();
        } catch (Exception e) {
            out.println("E/GVN ListaVehTipoServicios: " + e.getMessage());
            //Registrar en bitacora de exepciones.
        }

    }

    public ListaVehTipoServicioManagedBean() {
    }

    public void eliminar() {
        String mensaje, actividad;

        try {
            objeto = objetoFacade.findByNombre(Constantes.OBJETO_TIPO_SERVICIO);
            String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id_e");
            actividad=Constantes.BITACORA_ACTIVIDADES_ACCION_ELIMINAR;
            mensaje = Propiedades.getMensaje(Constantes.CATALOGO_VEH_TIPO_SERVICIO_LISTA_ELIMINO);
            vehTipoServicio = vehTipoServicioFacade.find(Integer.valueOf(id));
            vehTipoServicio.setEstatusRow(-1);
            vehTipoServicioFacade.edit(vehTipoServicio);
              BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto,Integer.valueOf(id),actividad);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
            init();

        } catch (Exception e) {
            out.println("E/GVN ListaVehTipoServicio: " + e.getMessage());
            //Registrar en bitacora de exepciones.
            mensaje = Propiedades.getMensaje(Constantes.CATALOGO_ERROR_GENERAL);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, mensaje));

        }
    }

    public List<VehTipoServicio> getVehTipoServicioList() {
        return vehTipoServicioList;
    }

    public void setVehTipoServicioList(List<VehTipoServicio> vehTipoServicioList) {
        this.vehTipoServicioList = vehTipoServicioList;
    }

    public VehTipoServicio getVehTipoServicio() {
        return vehTipoServicio;
    }

    /**
     * Creates a new instance of ListaVehTipoServicioManagedBean
     */
    //Agregar control de sesion;
    public void setVehTipoServicio(VehTipoServicio vehTipoServicio) {
        this.vehTipoServicio = vehTipoServicio;
    }

    public Usuario getUsuarioLogueado() {
        return usuarioLogueado;
    }

    public void setUsuarioLogueado(Usuario usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
    }
    
    
}
