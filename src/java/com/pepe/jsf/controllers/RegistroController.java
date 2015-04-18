package com.pepe.jsf.controllers;

import com.test.MappingHorario;
import com.test.ejbHorario;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;


@Named("registroController")
@ViewScoped
public class RegistroController implements Serializable {


    @EJB
    private ejbHorario ejbH;
  private List<MappingHorario>listMap=Collections.emptyList();

    public RegistroController() {
    }

    public ejbHorario getEjbH() {
        return ejbH;
    }

    public List<MappingHorario> getListMap() {
        if(listMap.isEmpty()){
            listMap=ejbH.listHorarios();
        }
        return listMap;
    }

    public void setListMap(List<MappingHorario> listMap) {
        this.listMap = listMap;
    }

   

}
