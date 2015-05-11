package com.pepe.jsf.controllers;

import com.pepe.jpa.entities.GuiaAprendizaje;
import com.pepe.jpa.entities.Proyecto;
import com.pepe.jpa.sesions.GuiaAprendizajeFacade;
import com.pepe.jpa.sesions.ProyectoFacade;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Adsit
 */
@ManagedBean
@SessionScoped
public class GuiaAprendizajeController implements Serializable {

    private GuiaAprendizaje guiaAprendizajeActual;
    private List<GuiaAprendizaje> listaguiaAprendizaje = null;
    @EJB
    private GuiaAprendizajeFacade guiaAprendizajeFacade;
    @EJB
    private ProyectoFacade proyectoFacade;

    public GuiaAprendizajeController() {
    }

    public GuiaAprendizaje getGuiaAprendizajeActual() {
        if (guiaAprendizajeActual == null) {
            guiaAprendizajeActual = new GuiaAprendizaje();
        }
        return guiaAprendizajeActual;
    }

    public void setGuiaAprendizajeActual(GuiaAprendizaje guiaAprendizajeActual) {
        this.guiaAprendizajeActual = guiaAprendizajeActual;
    }

    public List<GuiaAprendizaje> getListaguiaAprendizaje() {
        if (listaguiaAprendizaje == null) {
            try {
                listaguiaAprendizaje = getGuiaAprendizajeFacade().findAll();
            } catch (Exception e) {
                addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            }
        }
        return listaguiaAprendizaje;
    }

    public void setListaguiaAprendijaza(List<GuiaAprendizaje> listaguiaAprendizaje) {
        this.listaguiaAprendizaje = listaguiaAprendizaje;
    }

    public GuiaAprendizajeFacade getGuiaAprendizajeFacade() {
        return guiaAprendizajeFacade;
    }

    public void setGuiaAprendizajeFacade(GuiaAprendizajeFacade guiaAprendizajeFacade) {
        this.guiaAprendizajeFacade = guiaAprendizajeFacade;
    }

    public ProyectoFacade getProyectoFacade() {
        return proyectoFacade;
    }

    public void setProyectoFacade(ProyectoFacade proyectoFacade) {
        this.proyectoFacade = proyectoFacade;
    }

     public  List<Proyecto> getListaProyectoSelectOne() {
        return getProyectoFacade().findAll();
    }

    private void recargarlista() {
        listaguiaAprendizaje = null;
    }

    public String prepareCreate() {
        guiaAprendizajeActual = new GuiaAprendizaje();
        return "/GuiaAprendizaje/crear_guiaAprendizaje";
    }

    public String prepareEdit() {
        return "/GuiaAprendizaje/editar_guiaAprendizaje";
    }

    public String prepareView() {
        return "/GuiaAprendizaje/ver_guiaAprendizaje.xhtml";
    }

    public String prepareList() {
        recargarlista();
        return "/GuiaAprendizaje/lista_Guia_Aprendizaje";
    }

    public String addGuiaAprendizaje() {
        try {
            getGuiaAprendizajeFacade().create(guiaAprendizajeActual);
            recargarlista();
            return "lista_Guia_Aprendizaje";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }

    public String updateGuiaAprendizaje() {
        try {
            getGuiaAprendizajeFacade().edit(guiaAprendizajeActual);
            recargarlista();
            return "lista_Guia_Aprendizaje";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }

        public String deleteGuiaAprendizaje(){
        try{
            getGuiaAprendizajeFacade().remove(guiaAprendizajeActual);
            addSuccessMessage("Eliminado Exitosamente", "Guia Aprendizaje eliminada");
            recargarlista();
            } catch (Exception e){
                addErrorMessage ("Error closing resource " + e.getClass().getName(), "Message" + e.getMessage());
                
            }   
       return "lista_Guia_Aprendizaje";
    }

    private void addErrorMessage(String title, String msg) {
        FacesMessage facesMsg
                = new FacesMessage(FacesMessage.SEVERITY_ERROR, title, msg);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }

    private void addSuccessMessage(String title, String msg) {
        FacesMessage facesMsg
                = new FacesMessage(FacesMessage.SEVERITY_INFO, title, msg);
        FacesContext.getCurrentInstance().addMessage("successInfo", facesMsg);
    }
    
    
        public GuiaAprendizaje getGuiaAprendizaje(java.lang.Integer id) {
        return getGuiaAprendizajeFacade().find(id);
    }

    public List<GuiaAprendizaje> getItemsAvailableSelectMany() {
        return getGuiaAprendizajeFacade().findAll();
    }

    public List<GuiaAprendizaje> getItemsAvailableSelectOne() {
        return getGuiaAprendizajeFacade().findAll();
    }

    @FacesConverter(forClass = GuiaAprendizaje.class)
    public static class GuiaAprendizajeControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            GuiaAprendizajeController controller = (GuiaAprendizajeController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "guiaAprendizajeController");
            return controller.getGuiaAprendizaje(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof GuiaAprendizaje) {
                GuiaAprendizaje o = (GuiaAprendizaje) object;
                return getStringKey(o.getIdGuiaAprendizaje());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), GuiaAprendizaje.class.getName()});
                return null;
            }
        }

    }
}
