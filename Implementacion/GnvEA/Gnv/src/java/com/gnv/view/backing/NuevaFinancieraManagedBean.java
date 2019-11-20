/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.view.backing;

import com.gnv.business.ejb.modelo.Compania;
import com.gnv.business.ejb.modelo.Empresa;
import com.gnv.business.ejb.modelo.Financiera;
import com.gnv.business.ejb.modelo.Financiera;
import com.gnv.business.ejb.modelo.Objeto;
import com.gnv.business.ejb.modelo.Usuario;
import com.gnv.business.ejb.persistencia.CompaniaFacadeLocal;
import com.gnv.business.ejb.persistencia.FinancieraFacadeLocal;
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
public class NuevaFinancieraManagedBean implements Serializable {

    @ManagedProperty("#{sessionManagedBean.usuario}")
    private Usuario usuarioLogueado;

    @EJB
    private FinancieraFacadeLocal financieraFacade;

    @EJB
    private CompaniaFacadeLocal companiaFacade;

    @EJB
    private ObjetoFacadeLocal objetoFacade;

    private Financiera financiera;
    private Compania compania;
    private String titulo;
    private Objeto objeto;
    private List<Compania> companiaList;

    public NuevaFinancieraManagedBean() {
    }

    @PostConstruct
    public void init() {
        try {
            companiaList = companiaFacade.findAll();
            String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
            if (id != null) {
                financiera = financieraFacade.find(Integer.valueOf(id));
                titulo = Constantes.FINANCIERA_EDITAR_TITULO;
                compania = financiera.getCompania();
            } else {
                financiera = new Financiera();
                titulo = Constantes.FINANCIERA_NUEVO_TITULO;
            }
        } catch (Exception e) {
            out.println("E/GVN NuevoFinanciera: " + e.getMessage());
            //Registrar en bitacora de exepciones.
        }
    }

    public void guardar() {
        String mensaje = "", actividad = "";
        try {
            financiera.setEstatusRow(1);
            financiera.setCompania(compania);
            objeto = objetoFacade.findByNombre(Constantes.OBJETO_FINANCIERA);
            if (financiera.getId() != null) {
                mensaje = Propiedades.getMensaje(Constantes.CATALOGO_FINANCIERA_NUEVO_EDITO);
                actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_EDITAR;
                financieraFacade.edit(financiera);
                 BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto,financiera.getId(), actividad);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
            } else {
                actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_NUEVO;
                financieraFacade.create(financiera);
                 BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto,financiera.getId(), actividad);
                if (financiera.getId() != null) {
                    mensaje = Propiedades.getMensaje(Constantes.CATALOGO_FINANCIERA_NUEVO_GUARDO);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
                    
                } else {
                    mensaje = Propiedades.getMensaje(Constantes.CATALOGO_FINANCIERA_NUEVO_GUARDO_ERROR);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, mensaje));

                }
            }
           
           

        } catch (Exception e) {
            out.println("E/GVN NuevaFinanciera: " + e.getMessage());
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

    public Financiera getFinanciera() {
        return financiera;
    }

    public void setFinanciera(Financiera financiera) {
        this.financiera = financiera;
    }

    public Compania getCompania() {
        return compania;
    }

    public void setCompania(Compania compania) {
        this.compania = compania;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Compania> getCompaniaList() {
        return companiaList;
    }

    public void setCompaniaList(List<Compania> companiaList) {
        this.companiaList = companiaList;
    }
    
    
}
