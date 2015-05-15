/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepe.jsf.controllers;

import com.pepe.jpa.entities.Usuario;
import com.pepe.jpa.sesions.UsuarioFacade;
import java.io.Serializable;
import java.security.Principal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@ManagedBean
@SessionScoped
public class LoginController implements Serializable {

    private static final Logger log = Logger.getLogger(LoginController.class.getName());
    private String username;
    private String password;
    private Usuario usuario;
    @EJB
    private UsuarioFacade usuarioFacade;

    /*public LoginController() {
     HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
     if (session != null) {
     session.invalidate();
     }
     }*/
    public String getUsername() {
        return username;
    }

    private UsuarioFacade getUsuarioFacade() {
        return usuarioFacade;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAuthenticated() {
        return getRequest().getUserPrincipal() != null;
    }

    public Principal getPrincipal() {
        return getRequest().getUserPrincipal();
    }

    private HttpServletRequest getRequest() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        Object request = externalContext.getRequest();
        return request instanceof HttpServletRequest ? (HttpServletRequest) request : null;
    }

    private String getLogueado() {
        return getPrincipal().getName();
    }

    public Usuario getUserLogueado() {
        return getUsuarioFacade().findByDocumento(getLogueado());
    }

    public boolean isAdministrador() {
        return getRequest().isUserInRole("webAdmin");
    }

    public boolean isUser() {
        return getRequest().isUserInRole("webUser");
    }

    public boolean isApren() {
        return getRequest().isUserInRole("webApren");
    }

    public boolean isGest() {
        return getRequest().isUserInRole("webGest");
    }

    public boolean isPedag() {
        return getRequest().isUserInRole("webPedag");
    }

    public String login() {
        try {
            //Login via the Servlet Context
            getRequest().login(username, password);

            usuario = getUserLogueado();
            limpiar();

            //Cancela login para usuarios inactivos
            if (!usuario.getEstado()) {
                logout();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario Inactivo", null));
                return "/index.xhtml";
            }
            //Redirigir a la página de portada
            return "/landingpage.xhtml";
        } catch (ServletException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario o Contraseña Invalida", null));
            return "/index.xhtml";
        }
    }

    public String logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

        try {
            request.logout();
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            session.invalidate();
            limpiar();
            return "/index.xhtml";
        } catch (ServletException e) {
            log.log(Level.SEVERE, "Failed to logout user!", e);
            return "/index.xhtml";
        }
    }

    private void limpiar() {
        username = "";
        password = "";
    }
}