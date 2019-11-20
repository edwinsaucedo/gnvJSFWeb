/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.meri.simpleshirosecuredapplication.servlet;

import com.gnv.business.ejb.modelo.Estatus;
import com.gnv.business.ejb.modelo.Usuario;
import com.gnv.business.ejb.persistencia.EstatusFacadeLocal;
import com.gnv.business.ejb.persistencia.UsuarioFacadeLocal;
import com.gnv.views.utils.Constantes;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletRequest;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

/**
 *
 * @author Administrador
 */
public class VerboseFormAuthenticationFilter extends FormAuthenticationFilter{
      UsuarioFacadeLocal usuarioFacade = lookupUsuarioFacadeLocal();
      EstatusFacadeLocal estatusFacade= lookupEstatusFacadeLocal();

    
    
    @Override
    protected void setFailureAttribute(ServletRequest request, AuthenticationException ae) {

        String message = ae.getMessage();
        String user = request.getParameter("user");
        if (!message.equals("UsuarioInactivo")) {
            if (!message.equals("ExcessiveAttemptsException")) {
                if (ae.getMessage().contains("The credentials provided for account")) {
                    Estatus e= estatusFacade.findByClase(Usuario.class.getName(), Constantes.ESTATUS_ACTIVO);
                    Usuario u = usuarioFacade.findByClave(user,e);
//                    Integer intentos;
//                    if (u.getLoginAttemptCount() == null) {
//                        intentos = 0;
//                    } else {
//                        intentos = u.getLoginAttemptCount();
//                    }
//                    u.setLoginAttemptCount(intentos + 1);
//                    usersFacade.edit(u);
                }
            }
        }
        request.setAttribute(getFailureKeyAttribute(), message);
    }

    private UsuarioFacadeLocal lookupUsuarioFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (UsuarioFacadeLocal) c.lookup("java:global/GnvEA/Gnv-ejb/UsuarioFacade!com.gnv.business.ejb.persistencia.UsuarioFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
     private EstatusFacadeLocal lookupEstatusFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (EstatusFacadeLocal) c.lookup("java:global/GnvEA/Gnv-ejb/EstatusFacade!com.gnv.business.ejb.persistencia.EstatusFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
