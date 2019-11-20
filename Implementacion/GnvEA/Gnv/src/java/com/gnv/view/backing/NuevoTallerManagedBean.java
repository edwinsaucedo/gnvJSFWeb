/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.view.backing;

import com.gnv.business.ejb.modelo.Compania;
import com.gnv.business.ejb.modelo.Empresa;
import com.gnv.business.ejb.modelo.Objeto;
import com.gnv.business.ejb.modelo.Taller;
import com.gnv.business.ejb.modelo.Usuario;
import com.gnv.business.ejb.persistencia.CompaniaFacadeLocal;
import com.gnv.business.ejb.persistencia.EmpresaFacadeLocal;
import com.gnv.business.ejb.persistencia.ObjetoFacadeLocal;
import com.gnv.business.ejb.persistencia.TallerFacadeLocal;
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
 * @author PC2
 */
@ManagedBean
@ViewScoped
public class NuevoTallerManagedBean implements Serializable {

    
    @EJB
    private CompaniaFacadeLocal companiaFacade;

    @EJB
    private TallerFacadeLocal tallerFacade;
    @EJB
    private EmpresaFacadeLocal empresaFacade;
    @EJB
    private ObjetoFacadeLocal objetoFacade;
    
    @ManagedProperty("#{sessionManagedBean.usuario}")
    private Usuario usuarioLogueado;

    private Taller taller;
    private Compania compania;
    private Empresa empresa;
    private Objeto objeto;
    private List<Empresa> listaEmpresa;
    private List<Compania> listaCompania;
    private String titulo;

    @PostConstruct
    public void init() {
        try {
            String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
            listaEmpresa = empresaFacade.findByEstatusRow();
            listaCompania = companiaFacade.findAll();
            if (id != null) {
                taller = tallerFacade.find(Integer.valueOf(id));
                titulo = Constantes.TALLER_EDITAR_TITULO;
                empresa = taller.getEmpresa();
                compania = taller.getCompania();
            } else {
                taller = new Taller();
                empresa = new Empresa();
                compania = new Compania();
                titulo = Constantes.TALLER_NUEVO_TITULO;
            }
        } catch (Exception e) {
            out.println("E/GVN NuevoTaller: " + e.getMessage());
            //Registrar en bitacora de exepciones.
        }
    }

    public void guardar() {
        String mensaje = "",actividad="";
        int id=0;
        try {
            objeto=objetoFacade.findByNombre(Constantes.OBJETO_TALLER);
            taller.setEstatusRow(1);
            taller.setEmpresa(empresa);
            taller.setCompania(compania);
            if (taller.getId() != null) {
                actividad=Constantes.BITACORA_ACTIVIDADES_ACCION_EDITAR;
                mensaje = Propiedades.getMensaje(Constantes.CATALOGO_TALLER_NUEVO_EDITO);
                tallerFacade.edit(taller);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
            } else {
                actividad=Constantes.BITACORA_ACTIVIDADES_ACCION_NUEVO;  
                taller.setFechaIns(new Date());
                taller.setUsuarioIns(usuarioLogueado.getId());
                tallerFacade.create(taller);
                if (taller.getId() != null) {
                    mensaje = Propiedades.getMensaje(Constantes.CATALOGO_TALLER_NUEVO_GUARDO);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
                    id=taller.getId();
                    taller = new Taller();
                    empresa = new Empresa();
                    compania = new Compania();

                } else {
                    mensaje = Propiedades.getMensaje(Constantes.CATALOGO_TALLER_NUEVO_GUARDO_ERROR);        
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, mensaje));
                }

            }
            BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto,taller.getId()==null?id:taller.getId(), actividad);
        } catch (Exception e) {
            out.println("E/GVN NuevoTaller: " + e.getMessage());
            //Registrar en bitacora de exepciones.
            mensaje = Propiedades.getMensaje(Constantes.CATALOGO_ERROR_GENERAL);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, mensaje));
        }
    }

    public NuevoTallerManagedBean() {
    }

    public Taller getTaller() {
        return taller;
    }

    public void setTaller(Taller taller) {
        this.taller = taller;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public List<Empresa> getListaEmpresa() {
        return listaEmpresa;
    }

    public void setListaEmpresa(List<Empresa> listaEmpresa) {
        this.listaEmpresa = listaEmpresa;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Usuario getUsuarioLogueado() {
        return usuarioLogueado;
    }

    public void setUsuarioLogueado(Usuario usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
    }

    public Compania getCompania() {
        return compania;
    }

    public void setCompania(Compania compania) {
        this.compania = compania;
    }

    public List<Compania> getListaCompania() {
        return listaCompania;
    }

    public void setListaCompania(List<Compania> listaCompania) {
        this.listaCompania = listaCompania;

    }
}
