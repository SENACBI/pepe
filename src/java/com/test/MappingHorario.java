
package com.test;

import com.pepe.jpa.entities.AmbienteFormacion;
import com.pepe.jpa.entities.Ficha;
import com.pepe.jpa.entities.ResultadoAprendizaje;
import com.pepe.jpa.entities.Usuario;
import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

 
public class MappingHorario implements Serializable {
     private Integer id;
     private Ficha ficha;
     private AmbienteFormacion ambiente;
     private Usuario instrunctor;
     private ResultadoAprendizaje resultadoAprendizaje;
     private Date fechaInicio;
     private Date fechaFinal;
     private Date horaInicio;
     private Date horaFinal;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Ficha getFicha() {
        return ficha;
    }

    public void setFicha(Ficha ficha) {
        this.ficha = ficha;
    }

    public AmbienteFormacion getAmbiente() {
        return ambiente;
    }

    public void setAmbiente(AmbienteFormacion ambiente) {
        this.ambiente = ambiente;
    }

    public Usuario getInstrunctor() {
        return instrunctor;
    }

    public void setInstrunctor(Usuario instrunctor) {
        this.instrunctor = instrunctor;
    }

    public ResultadoAprendizaje getResultadoAprendizaje() {
        return resultadoAprendizaje;
    }

    public void setResultadoAprendizaje(ResultadoAprendizaje resultadoAprendizaje) {
        this.resultadoAprendizaje = resultadoAprendizaje;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(Date horaFinal) {
        this.horaFinal = horaFinal;
    }
     
}
