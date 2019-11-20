/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.view.backing;

import com.gnv.business.ejb.modelo.Marca;
import com.gnv.business.ejb.modelo.Objeto;
import com.gnv.business.ejb.modelo.TipoMarca;
import com.gnv.business.ejb.modelo.Usuario;
import com.gnv.business.ejb.persistencia.MarcaFacadeLocal;
import com.gnv.business.ejb.persistencia.ObjetoFacadeLocal;
import com.gnv.business.ejb.persistencia.TipoMarcaFacadeLocal;
import com.gnv.views.factories.BitacoraActividadesFactory;
import com.gnv.views.utils.Constantes;
import com.gnv.views.utils.Propiedades;
import java.io.Serializable;
import static java.lang.System.out;
import java.util.Date;
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
public class NuevaMarcaManagedBean implements Serializable {

    @ManagedProperty("#{sessionManagedBean.usuario}")
    private Usuario usuarioLogueado;

    @EJB
    private MarcaFacadeLocal marcaFacade;

    @EJB
    private ObjetoFacadeLocal objetoFacade;

    @EJB
    private TipoMarcaFacadeLocal tipoMarcaFacade;

    private Objeto objeto;
    private Marca marca;
    private TipoMarca tipoMarca;
    private List<TipoMarca> tipoMarcaList;
    private String titulo;

    @PostConstruct
    public void init() {
        try {
            String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");

            objeto = objetoFacade.findByNombre(Constantes.OBJETO_MARCA);
            tipoMarcaList = tipoMarcaFacade.findAll();
            if (id != null) {
                marca = marcaFacade.find(Integer.valueOf(id));
                titulo = Constantes.MARCA_EDITAR_TITULO;
                tipoMarca = marca.getTipoMarca();
            } else {
                marca = new Marca();
                tipoMarca = new TipoMarca();
                titulo = Constantes.MARCA_NUEVO_TITULO;
            }
        } catch (Exception e) {
            out.println("E/GVN NuevoMarcas: " + e.getMessage());
            //Registrar en bitacora de exepciones.
        }
    }

    public void guardar() {
        String mensaje = "", actividad = "";
        try {
            marca.setEstatusRow(1);
            marca.setTipoMarca(tipoMarca);
            if (marca.getId() != null) {
                //Falta establecer el tipo de activadad pra llenar la bitacora y falta el registro en bitacora.
                mensaje = Propiedades.getMensaje(Constantes.CATALOGO_MARCA_NUEVO_EDITO);
                actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_EDITAR;
                marcaFacade.edit(marca);
                BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto, marca.getId(), actividad);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
            } else {
                //Cambiar por id del usuario logueado.
                actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_NUEVO;
                marca.setFechaIns(new Date());
                marca.setUsuarioIns(usuarioLogueado.getId());
                marcaFacade.create(marca);
                if (marca.getId() != null) {
                    mensaje = Propiedades.getMensaje(Constantes.CATALOGO_MARCA_NUEVO_GUARDO);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
                    BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto, marca.getId(), actividad);
                    marca = new Marca();
                    tipoMarca = new TipoMarca();
                } else {
                    mensaje = Propiedades.getMensaje(Constantes.CATALOGO_MARCA_NUEVO_GUARDO_ERROR);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, mensaje));
                }

            }
        } catch (Exception e) {
            out.println("E/GVN NuevoMarcas: " + e.getMessage());
            //Registrar en bitacora de exepciones.
            mensaje = Propiedades.getMensaje(Constantes.CATALOGO_ERROR_GENERAL);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, mensaje));
        }

    }

    public NuevaMarcaManagedBean() {
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca tmarca) {
        this.marca = tmarca;
    }

    public String getTitulo() {
        return titulo;
    }

    public TipoMarca getTipoMarca() {
        return tipoMarca;
    }

    public void setTipoMarca(TipoMarca tipoMarca) {
        this.tipoMarca = tipoMarca;
    }

    public List<TipoMarca> getTipoMarcaList() {
        return tipoMarcaList;
    }

    public void setTipoMarcaList(List<TipoMarca> tipoMarcaList) {
        this.tipoMarcaList = tipoMarcaList;
    }

    public Usuario getUsuarioLogueado() {
        return usuarioLogueado;
    }

    public void setUsuarioLogueado(Usuario usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
    }

}
