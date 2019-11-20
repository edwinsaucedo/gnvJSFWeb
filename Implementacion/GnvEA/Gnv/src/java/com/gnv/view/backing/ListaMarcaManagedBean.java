/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.view.backing;

import com.gnv.business.ejb.modelo.Marca;
import com.gnv.business.ejb.modelo.Objeto;
import com.gnv.business.ejb.modelo.Usuario;
import com.gnv.business.ejb.persistencia.MarcaFacadeLocal;
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
 * @author Rene vl
 */
@ManagedBean
@ViewScoped
public class ListaMarcaManagedBean {

    @ManagedProperty("#{sessionManagedBean.usuario}")
    private Usuario usuarioLogueado;

    private List<Marca> marcaList;
    private Marca marca;

    @EJB
    private MarcaFacadeLocal marcaFacade;

    @EJB
    private ObjetoFacadeLocal objetoFacade;

    public ListaMarcaManagedBean() {
    }

    @PostConstruct
    public void init() {
        try {

            marcaList = marcaFacade.findByEstatusRow();
        } catch (Exception e) {
            out.println("E/GVN ListaMarca: " + e.getMessage());
            //Registrar en bitacora de exepciones.
        }
    }

    public void eliminar() {
        String mensaje, actividad;
        try {
            String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id_e");
            Objeto objeto = objetoFacade.findByNombre(Constantes.OBJETO_MARCA);
            actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_ELIMINAR;
            //actividad = Propiedades.getMensaje(Constantes.BITACORA_ACTIVIDAD_ELIMINO_USUARIO); Falta registar en la bitacora de actividades.
            mensaje = Propiedades.getMensaje(Constantes.CATALOGO_MARCA_LISTA_ELIMINO);
            marca = marcaFacade.find(Integer.valueOf(id));
            marca.setEstatusRow(-1);
            marcaFacade.edit(marca);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
            init();
            BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto, Integer.valueOf(id), actividad);
        } catch (Exception e) {
            out.println("E/GVN ListaMarcas: " + e.getMessage());
            //Registrar en bitacora de exepciones.
            mensaje = Propiedades.getMensaje(Constantes.CATALOGO_ERROR_GENERAL);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, mensaje));

        }
    }

    public List<Marca> getMarcaList() {
        return marcaList;
    }

    public void setMarcaList(List<Marca> marcaList) {
        this.marcaList = marcaList;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Usuario getUsuarioLogueado() {
        return usuarioLogueado;
    }

    public void setUsuarioLogueado(Usuario usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
    }
    
    

}
