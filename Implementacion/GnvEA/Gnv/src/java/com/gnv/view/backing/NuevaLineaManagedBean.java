/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.view.backing;

import com.gnv.business.ejb.modelo.Linea;
import com.gnv.business.ejb.modelo.Marca;
import com.gnv.business.ejb.modelo.Objeto;
import com.gnv.business.ejb.modelo.TipoMarca;
import com.gnv.business.ejb.modelo.Usuario;
import com.gnv.business.ejb.persistencia.LineaFacadeLocal;
import com.gnv.business.ejb.persistencia.MarcaFacadeLocal;
import com.gnv.business.ejb.persistencia.ObjetoFacadeLocal;
import com.gnv.business.ejb.persistencia.TipoMarcaFacadeLocal;
import com.gnv.views.factories.BitacoraActividadesFactory;
import com.gnv.views.utils.Constantes;
import com.gnv.views.utils.Propiedades;
import java.io.Serializable;
import static java.lang.System.out;
import java.util.ArrayList;
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
public class NuevaLineaManagedBean implements Serializable {

    @ManagedProperty("#{sessionManagedBean.usuario}")
    private Usuario usuarioLogueado;

    @EJB
    private LineaFacadeLocal lineaFacade;
    @EJB
    private MarcaFacadeLocal marcaFacade;
    @EJB
    private TipoMarcaFacadeLocal tipoMarcaFacade;

    @EJB
    private ObjetoFacadeLocal objetoFacade;

    private List<Marca> marcaList;
    private List<TipoMarca> tipoMarcaList;
    private Linea linea;
    private Marca marca;
    private TipoMarca tipoMarca;
    private String titulo;
    private Objeto objeto;

    @PostConstruct
    public void init() {
        try {
            String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
            tipoMarcaList = tipoMarcaFacade.findAll();
            if (id != null) {
                linea = lineaFacade.find(Integer.valueOf(id));
                titulo = Constantes.LINEA_EDITAR_TITULO;
                marca = linea.getMarca();
                tipoMarca = linea.getMarca().getTipoMarca();
                marcaList = marcaFacade.findByTipoMarca(tipoMarca);
            } else {
                linea = new Linea();
                marca = new Marca();
                tipoMarca = new TipoMarca();
                marcaList = new ArrayList<>();
                titulo = Constantes.LINEA_NUEVO_TITULO;
            }
        } catch (Exception e) {
            out.println("E/GVN NuevaLinea: " + e.getMessage());
            //Registrar en bitacora de exepciones.
        }
    }

    public NuevaLineaManagedBean() {
    }

    public void onTipoMarcaOnChange() {
        try {
            if (tipoMarca != null) {
                marcaList = marcaFacade.findByTipoMarca(tipoMarca);
            } else {
                marcaList = new ArrayList<>();
            }

        } catch (Exception e) {
            out.println("E/GVN NuevoLinea: " + e.getMessage());
        }
    }

    public void guardar() {
        String mensaje = "", actividad = "";
        try {
            objeto = objetoFacade.findByNombre(Constantes.OBJETO_CONVERSION);
            linea.setMarca(marca);
            if (linea.getId() != null) {
                //Falta establecer el tipo de activadad pra llenar la bitacora y falta el registro en bitacora.
                mensaje = Propiedades.getMensaje(Constantes.CATALOGO_LINEA_NUEVO_EDITO);
                actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_EDITAR;
                lineaFacade.edit(linea);
                BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto, linea.getId(), actividad);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
            } else {
                //Cambiar por id del usuario logueado.
                actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_EDITAR;
                lineaFacade.create(linea);
                if (linea.getId() != null) {
                    mensaje = Propiedades.getMensaje(Constantes.CATALOGO_LINEA_NUEVO_GUARDO);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
                    BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto, linea.getId(), actividad);
                    linea = new Linea();
                    marca = new Marca();
                    tipoMarca = new TipoMarca();
                    marcaList = new ArrayList<>();
                } else {
                    mensaje = Propiedades.getMensaje(Constantes.CATALOGO_LINEA_NUEVO_GUARDO_ERROR);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, mensaje));
                }

            }
        } catch (Exception e) {
            out.println("E/GVN NuevoLinea: " + e.getMessage());
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

    public Linea getLinea() {
        return linea;
    }

    public void setLinea(Linea linea) {
        this.linea = linea;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Marca> getMarcaList() {
        return marcaList;
    }

    public void setMarcaList(List<Marca> marcaList) {
        this.marcaList = marcaList;
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
