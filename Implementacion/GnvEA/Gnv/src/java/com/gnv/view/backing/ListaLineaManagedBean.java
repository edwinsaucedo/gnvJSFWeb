/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.view.backing;

import com.gnv.business.ejb.modelo.Linea;
import com.gnv.business.ejb.modelo.Objeto;
import com.gnv.business.ejb.modelo.Usuario;
import com.gnv.business.ejb.persistencia.LineaFacadeLocal;
import com.gnv.business.ejb.persistencia.ObjetoFacadeLocal;
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
 * @author Administrador
 */
@ManagedBean
@ViewScoped
public class ListaLineaManagedBean {

    @ManagedProperty("#{sessionManagedBean.usuario}")
    private Usuario usuarioLogueado;

    private List<Linea> lineaList;
    private Linea linea;

    @EJB
    private LineaFacadeLocal lineaFacade;

    @EJB
    private ObjetoFacadeLocal objetoFacade;

    @PostConstruct
    public void init() {
        try {
            lineaList = lineaFacade.findAll();
        } catch (Exception e) {
            out.println("E/GVN ListaLinea: " + e.getMessage());
            //Registrar en bitacora de exepciones.
        }
    }

    public ListaLineaManagedBean() {
    }

    public void eliminar() {
        String mensaje, actividad;
        try {
            String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id_e");
            Objeto objeto = objetoFacade.findByNombre(Constantes.OBJETO_LINEA);
//actividad = Propiedades.getMensaje(Constantes.BITACORA_ACTIVIDAD_ELIMINO_USUARIO); Falta registar en la bitacora de actividades.
            actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_ELIMINAR;
            mensaje = Propiedades.getMensaje(Constantes.CATALOGO_LINEA_LISTA_ELIMINO);
            linea = lineaFacade.find(Integer.valueOf(id));
            lineaFacade.remove(linea);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
            init();
            BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto, Integer.valueOf(id), actividad);
        } catch (Exception e) {
            out.println("E/GVN ListaLinea: " + e.getMessage());
            //Registrar en bitacora de exepciones.
            mensaje = Propiedades.getMensaje(Constantes.CATALOGO_ERROR_GENERAL);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, mensaje));

        }
    }

    public List<Linea> getLineaList() {
        return lineaList;
    }

    public void setLineaList(List<Linea> lineaList) {
        this.lineaList = lineaList;
    }

    public Linea getLinea() {
        return linea;
    }

    public void setLinea(Linea linea) {
        this.linea = linea;
    }

    public Usuario getUsuarioLogueado() {
        return usuarioLogueado;
    }

    public void setUsuarioLogueado(Usuario usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
    }
    
    

}
