/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.view.backing;

import com.gnv.business.ejb.modelo.Objeto;
import com.gnv.business.ejb.modelo.Rol;
import com.gnv.business.ejb.modelo.RolesPermissions;
import com.gnv.business.ejb.modelo.Usuario;
import com.gnv.business.ejb.persistencia.ObjetoFacadeLocal;
import com.gnv.business.ejb.persistencia.RolFacadeLocal;
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
 * @author Administrador
 */
@ManagedBean
@ViewScoped
public class NuevoRolManagedBean implements Serializable {

    private String title;

    @ManagedProperty("#{sessionManagedBean.usuario}")
    private Usuario usuarioLogueado;

    @EJB
    private RolFacadeLocal rolFacade;

    @EJB
    private ObjetoFacadeLocal objetoFacade;

    private Rol rol;
    private Objeto objeto;

    public NuevoRolManagedBean() {
    }

    @PostConstruct
    public void init() {
        try {
            String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
            if (null != id) {
                rol = rolFacade.find(Integer.valueOf(id));
                title = Constantes.ROL_EDITAR_TITULO;

            } else {
                title = Constantes.ROL_NUEVO_TITULO;
                rol = new Rol();
            }
        } catch (Exception e) {
            out.println("E/GVN NuevoRol: " + e.getMessage());
        }
    }

    public void guardar() {
        try {
            String actividad = "";
            objeto = objetoFacade.findByNombre(Constantes.OBJETO_ROL);
            int id=0;
            if (null != rol.getId()) {
                rolFacade.edit(rol);
                actividad = Constantes.BITACORA_ACTIVIDADES_ACCION_EDITAR;
                String mensaje = Propiedades.getMensaje(Constantes.CATALOGO_ROL_NUEVO_EDITO);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));

            } else if (!existeRol()) {
                rol.setFechaRegistro(new Date());
                rolFacade.create(rol);
                actividad=Constantes.BITACORA_ACTIVIDADES_ACCION_NUEVO;
                id=rol.getId();
                rol = new Rol();
                String mensaje = Propiedades.getMensaje(Constantes.CATALOGO_ROL_NUEVO);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.MENSAJE_EXITO, mensaje));
            } else {
                String mensaje = Propiedades.getMensaje(Constantes.CATALOGO_ROL_EXISTE_ROL);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, Constantes.MENSAJE_ERROR, mensaje));
            }
            BitacoraActividadesFactory.ckeckIn(usuarioLogueado, objeto,rol.getId()==null?id:rol.getId(), actividad);
        } catch (Exception e) {
            out.println("E/GVN NuevoRol: " + e.getMessage());
        }
    }

    private Boolean existeRol() {
        Boolean flag = false;
        try {
            flag = rolFacade.existeRol(rol.getNombre());
        } catch (Exception e) {
            out.println("E/GVN NuevoRol: " + e.getMessage());
        }
        return flag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Usuario getUsuarioLogueado() {
        return usuarioLogueado;
    }

    public void setUsuarioLogueado(Usuario usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
    }

    public RolFacadeLocal getRolFacade() {
        return rolFacade;
    }

    public void setRolFacade(RolFacadeLocal rolFacade) {
        this.rolFacade = rolFacade;
    }

}
