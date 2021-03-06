

package com.pepe.jsf.controllers;

import com.pepe.jpa.entities.Actividad;
import com.pepe.jpa.entities.Proyecto;
import com.pepe.jpa.entities.TecnicaDidactica;
import com.pepe.jpa.sesions.ActividadFacade;
import com.pepe.jpa.sesions.ProyectoFacade;
import com.pepe.jpa.sesions.TecnicaDidacticaFacade;
import controller.util.JsfUtil;
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


@ManagedBean
@SessionScoped
public class TecnicaDidacticaController implements Serializable {

    private TecnicaDidactica tecnicaActual;
    private List<TecnicaDidactica> listaTecnica = null;
    @EJB
    private TecnicaDidacticaFacade tecnicaFacade;
    
    public TecnicaDidacticaController() {
        
    }

    public TecnicaDidactica getTecnicaActual() {
        if (tecnicaActual == null ){
            tecnicaActual = new TecnicaDidactica();
                    }         
        return tecnicaActual;
    }

    public void setTecnicaActual(TecnicaDidactica tecnicaActual) {
        this.tecnicaActual = tecnicaActual;
    }

    public List<TecnicaDidactica> getListaTecnica() {
        return listaTecnica;
    }

    public void setListaTecnica(List<TecnicaDidactica> listaTecnica) {
        this.listaTecnica = listaTecnica;
    }

    public TecnicaDidacticaFacade getTecnicaFacade() {
        return tecnicaFacade;
    }

    public void setTecnicaFacade(TecnicaDidacticaFacade tecnicaFacade) {
        this.tecnicaFacade = tecnicaFacade;
    }
    
    public List<TecnicaDidactica> getListaTecnicaDidacticas(){
        if (listaTecnica == null){
            try{
                listaTecnica = getTecnicaFacade().findAll();
            }catch (Exception e){
               addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            }
        }
        return listaTecnica;
    }
    
    private void recargarlista(){
        listaTecnica = null;
    }
    
     public String prepareCreate(){
      tecnicaActual = new TecnicaDidactica();
        return "/TecnicaDidactica/crear_Tecnica";
}
    public String prepareEdit (){
    return "/TecnicaDidactica/editar_TecnicaDidactica";
}
    public String prepareView (){
    return "/TecnicaDidactica/ver_tecnicaDidactica";
}
    public String prepareList (){
    recargarlista();
        return "/TecnicaDidactica/lista_Tecnica_Didactica";
}
    
     public String addTecnica(){
        try{
        getTecnicaFacade().create(tecnicaActual);
        recargarlista();
        return "/TecnicaDidactica/lista_Tecnica_Didactica";
        }catch (Exception e){
        addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }
     
     public String updateTecnica(){
        try{
        getTecnicaFacade().edit(tecnicaActual);
        recargarlista();
        return "ver_tecnicaDidactica";
        }catch (Exception e){
        addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }
     
       public String deletetecnica(){
        try{
            getTecnicaFacade().remove(tecnicaActual);
            addSuccessMessage("Eliminado Exitosamente", "Tecnica Didactica eliminada");
            recargarlista();
            } catch (Exception e){
                addErrorMessage ("Error closing resource " + e.getClass().getName(), "Message" + e.getMessage());
                
            }   
       return "lista_Tecnica_Didactica";
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
    public TecnicaDidactica getTecnicaDidactica(java.lang.Integer id) {
        return getTecnicaFacade().find(id);
    }

    @FacesConverter(forClass = TecnicaDidactica.class)
    public static class TecnicaDidacticaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TecnicaDidacticaController controller = (TecnicaDidacticaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tecnicaDidacticaController");
            return controller.getTecnicaDidactica(getKey(value));
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
            if (object instanceof TecnicaDidactica) {
                TecnicaDidactica o = (TecnicaDidactica) object;
                return getStringKey(o.getIdTecnicaDidactica());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + TecnicaDidactica.class.getName());
            }
        }

    }

         
         
}
