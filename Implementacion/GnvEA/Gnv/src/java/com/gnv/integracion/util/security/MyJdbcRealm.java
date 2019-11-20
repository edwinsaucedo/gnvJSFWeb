/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnv.integracion.util.security;

import com.gnv.business.ejb.modelo.Estatus;
import com.gnv.business.ejb.modelo.Usuario;
import com.gnv.business.ejb.persistencia.EstatusFacadeLocal;
import com.gnv.business.ejb.persistencia.RolesPermissionsFacadeLocal;
import com.gnv.business.ejb.persistencia.UserRolesFacadeLocal;
import com.gnv.business.ejb.persistencia.UsuarioFacadeLocal;
import com.gnv.views.utils.Constantes;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.JdbcUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Administrador
 */
public class MyJdbcRealm extends JdbcRealm{
    
    private RolesPermissionsFacadeLocal rolesPermissionsFacade = lookupRolesPermissionsFacadeLocal();
    private UserRolesFacadeLocal userRolesFacade = lookupUserRolesFacadeLocal();
    private UsuarioFacadeLocal usuarioFacade = lookupUsuarioFacadeLocal();
    private EstatusFacadeLocal estatusFacade= lookupEstatusFacadeLocal();
    private static final Logger LOG = LoggerFactory.getLogger(JdbcRealm.class);

    public MyJdbcRealm() throws NamingException
    {
        super();
        //get the DataSource that JSecurity's JdbcRealm
        //should use to find the user's password
        //using the provided username
        //see context.xml for this DataSource's properties
        InitialContext ic;
        try
        {
            ic = new InitialContext();
            dataSource = (DataSource) ic.lookup("jdbc/Gnv");
            this.setDataSource(dataSource);
        } catch (NamingException e)
        {
            out.println("E/GNV en: "+e.getMessage());
            throw e;
        }
    }
    
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals)
    {
        String nickName = principals.toString();
        Estatus estatus=estatusFacade.findByClase(Usuario.class.getName(),Constantes.ESTATUS_ACTIVO);
        Usuario u = usuarioFacade.findByClave(nickName,estatus);
        List<String> roles = userRolesFacade.findRolesNamesByUser(nickName);
        SimpleAuthorizationInfo sai = new SimpleAuthorizationInfo();
        roles.stream().map((s) ->
        {
            sai.addRole(s);
            return s;
        }).map((s) -> rolesPermissionsFacade.findByRol(s)).forEachOrdered((listaPermisos) ->
        {
            listaPermisos.forEach((rp) ->
            {
                sai.addStringPermission(rp.getPermission());
            });
        });
        return sai;
    }
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException
    {
        Connection conn = null;
        AuthenticationInfo info = null;
        try
        {
            UsernamePasswordToken upToken = (UsernamePasswordToken) token;
            Estatus estatus=estatusFacade.findByClase(Usuario.class.getName(),Constantes.ESTATUS_ACTIVO);
            String username = upToken.getUsername();

            // Null username is invalid
            if (username == null)
            {
                throw new AccountException("Null usernames are not allowed by this realm.");
            }

            try
            {
                conn = dataSource.getConnection();

                String password = getPasswordForUser(conn, username,estatus);

                if (password == null)
                {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, Constantes.MENSAJE_ERROR, "Credenciales incorrectas."));
                    throw new UnknownAccountException("No se encontró ninguna cuenta para [" + username + "].");
                }

                info = super.doGetAuthenticationInfo(token);


            } catch (SQLException e)
            {
                final String message = "Ocurrió un error al autenticar a  [" + username + "], intente más tarde.";
                if (LOG.isErrorEnabled())
                {
                    LOG.error(message, e);
                }

                // Rethrow any SQL errors as an authentication exception
                throw new AuthenticationException(message, e);
            } finally
            {
                JdbcUtils.closeConnection(conn);
            }
        } catch (Exception e)
        {
            
            out.println("E/GNV en: "+e.getMessage());

        }
        return info;
    }
      private String getPasswordForUser(Connection conn, String username,Estatus estatus) throws SQLException
    {

        PreparedStatement ps = null;
        ResultSet rs = null;
        String password = null;
        try
        {
            ps = conn.prepareStatement(authenticationQuery);
            ps.setString(1, username);
            //ps.setInt(2,estatus.getId());

            // Execute query
            rs = ps.executeQuery();

            // Loop over results - although we are only expecting one result, since usernames should be unique
            boolean foundResult = false;
            while (rs.next())
            {

                // Check to ensure only one row is processed
                if (foundResult)
                {
                    throw new AuthenticationException("Se encontró más de un registro para el usuario: [" + username + "]. Usuarios deben de ser unique.");
                }

                password = rs.getString(1);

                foundResult = true;
            }
        } finally
        {
            JdbcUtils.closeResultSet(rs);
            JdbcUtils.closeStatement(ps);
        }

        return password;
    }
    private UsuarioFacadeLocal lookupUsuarioFacadeLocal()
    {
        try
        {
            Context c = new InitialContext();
            return (UsuarioFacadeLocal) c.lookup("java:global/GnvEA/Gnv-ejb/UsuarioFacade!com.gnv.business.ejb.persistencia.UsuarioFacadeLocal");
        } catch (NamingException ne)
        {
            java.util.logging.Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            out.println("E/GNV en: "+ne.getMessage());
            throw new RuntimeException(ne);
        }
    }

    private UserRolesFacadeLocal lookupUserRolesFacadeLocal()
    {
        try
        {
            Context c = new InitialContext();
            return (UserRolesFacadeLocal) c.lookup("java:global/GnvEA/Gnv-ejb/UserRolesFacade!com.gnv.business.ejb.persistencia.UserRolesFacadeLocal");
        } catch (NamingException ne)
        {
            java.util.logging.Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            out.println("E/GNV en: "+ne.getMessage());
            throw new RuntimeException(ne);
        }
    }

    private RolesPermissionsFacadeLocal lookupRolesPermissionsFacadeLocal()
    {
        try
        {
            Context c = new InitialContext();
            return (RolesPermissionsFacadeLocal) c.lookup("java:global/GnvEA/Gnv-ejb/RolesPermissionsFacade!com.gnv.business.ejb.persistencia.RolesPermissionsFacadeLocal");
        } catch (NamingException ne)
        {
            java.util.logging.Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            out.println("E/GNV en: "+ne.getMessage());
            throw new RuntimeException(ne);
        }
    }
    
    private EstatusFacadeLocal lookupEstatusFacadeLocal()
    {
        try
        {
            Context c = new InitialContext();
            return (EstatusFacadeLocal) c.lookup("java:global/GnvEA/Gnv-ejb/EstatusFacade!com.gnv.business.ejb.persistencia.EstatusFacadeLocal");
        } catch (NamingException ne)
        {
            java.util.logging.Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            out.println("E/GNV en: "+ne.getMessage());
            throw new RuntimeException(ne);
        }
    }
    
}
