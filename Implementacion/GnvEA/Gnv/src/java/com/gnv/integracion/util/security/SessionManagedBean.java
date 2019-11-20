/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.integracion.util.security;

import com.gnv.business.ejb.modelo.Estatus;
import com.gnv.business.ejb.modelo.Usuario;
import com.gnv.business.ejb.persistencia.EstatusFacadeLocal;
import com.gnv.business.ejb.persistencia.UsuarioFacadeLocal;
import com.gnv.views.utils.Constantes;
import com.gnv.views.utils.Propiedades;
import javax.faces.bean.SessionScoped;;
import java.io.Serializable;
import static java.lang.System.out;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;


/**
 *
 * @author Administrador
 */

@SessionScoped
@ManagedBean
public class SessionManagedBean implements Serializable {

    @EJB
    UsuarioFacadeLocal usuarioFacade;
    @EJB
    EstatusFacadeLocal estatusFacade;

    private Usuario usuario;
    private Boolean actualizarHash;
    private String hashActual;
    private String hashNueva;
    private String hashConfirmacion;
    private Estatus estatus;

    public SessionManagedBean()
    {
    }

    @PostConstruct
    public void init()
    {
        try
        {
            String mensaje = "";
            String claveUsuario = SecurityUtils.getSubject().getPrincipal().toString();
            estatus=estatusFacade.findByClase(Usuario.class.getName(),Constantes.ESTATUS_ACTIVO);
            if (null != claveUsuario)
            {
                usuario = usuarioFacade.findByClave(claveUsuario,estatus);
                if (usuario != null)
                {
                    actualizarHash = usuario.getActualizarHash();
                }
            } else
            {
                mensaje = Propiedades.getMensaje(Constantes.MENSAJE_ERROR);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.MENSAJE_ERROR, mensaje));

            }
        } catch (Exception ex)
        {
            out.println("Error en SessionBean en: " + ex.getMessage());
        }

    }
    public String logout()
    {
        if (SecurityUtils.getSubject().isAuthenticated())
        {
            SecurityUtils.getSubject().logout();
        }
        return "logout";
    }
    
    public void handleIdle()
    {
        if (SecurityUtils.getSubject().isAuthenticated())
        {
            SecurityUtils.getSubject().logout();
        }
    }
    
    public void cambiaHash()
    {
        try
        {
            hashActual = "";
            if (usuario != null)
            {
                hashActual = usuario.getHash();
            }
            else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "No se pudo reconocer el usuario logueado."));
                return;
            }
            if (new Md5Hash(this.hashNueva).toHex().equals(hashActual))
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "La contraseña nueva debe ser diferente a la anterior"));
            } else if (hashNueva.length() < 6)
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "La contraseña debe ser igual o mayor a 6 caracteres"));
            } else if (!hashNueva.equals(hashConfirmacion))
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "La contraseña no coincide con la confirmación"));
            } else
            {
                String hash = new Md5Hash(hashNueva).toHex();
                //si usuario no es nùlo:
                if (usuario != null)
                {
                    usuario.setActualizarHash(false);
                    usuario.setHash(hash);
                    usuario.setFechaModificacion(new Date());
                    usuarioFacade.edit(usuario);
                    actualizarHash = usuario.getActualizarHash();
                }
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "La contraseña se ha actualizado de manera exitosa"));
            }
        } catch (Exception e)
        {
              out.println("Error en session bean en: "+e.getMessage());
              //Falta imprimir en bitacora de exepciones.
              //BitacoraExcepcionesFactory.log(e, usuario);
        }
    }

    public Usuario getUsuario()
    {
        return usuario;
    }

    public void setUsuario(Usuario usuario)
    {
        this.usuario = usuario;
    }

    public Boolean getActualizarHash()
    {
        return actualizarHash;
    }

    public void setActualizarHash(Boolean actualizarHash)
    {
        this.actualizarHash = actualizarHash;
    }

    public String getHashActual()
    {
        return hashActual;
    }

    public void setHashActual(String hashActual)
    {
        this.hashActual = hashActual;
    }

    public String getHashNueva()
    {
        return hashNueva;
    }

    public void setHashNueva(String hashNueva)
    {
        this.hashNueva = hashNueva;
    }

    public String getHashConfirmacion()
    {
        return hashConfirmacion;
    }

    public void setHashConfirmacion(String hashConfirmacion)
    {
        this.hashConfirmacion = hashConfirmacion;
    }

}
