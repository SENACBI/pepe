/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pepe.jpa.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kevin
 */
@Entity
@Table(name = "sabana_horario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SabanaHorario.findAll", query = "SELECT s FROM SabanaHorario s"),
    @NamedQuery(name = "SabanaHorario.findByIdsabanaHorario", query = "SELECT s FROM SabanaHorario s WHERE s.idsabanaHorario = :idsabanaHorario"),
    @NamedQuery(name = "SabanaHorario.findByIdFicha", query = "SELECT s FROM SabanaHorario s WHERE s.idFicha = :idFicha"),
    @NamedQuery(name = "SabanaHorario.findByIdAmbiente", query = "SELECT s FROM SabanaHorario s WHERE s.idAmbiente = :idAmbiente"),
    @NamedQuery(name = "SabanaHorario.findByIdInstructor", query = "SELECT s FROM SabanaHorario s WHERE s.idInstructor = :idInstructor"),
    @NamedQuery(name = "SabanaHorario.findByIdResultado", query = "SELECT s FROM SabanaHorario s WHERE s.idResultado = :idResultado"),
    @NamedQuery(name = "SabanaHorario.findByFechaInicio", query = "SELECT s FROM SabanaHorario s WHERE s.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "SabanaHorario.findByFechaFinal", query = "SELECT s FROM SabanaHorario s WHERE s.fechaFinal = :fechaFinal"),
    @NamedQuery(name = "SabanaHorario.findByHoraInicio", query = "SELECT s FROM SabanaHorario s WHERE s.horaInicio = :horaInicio"),
    @NamedQuery(name = "SabanaHorario.findByHoraFinal", query = "SELECT s FROM SabanaHorario s WHERE s.horaFinal = :horaFinal")})
public class SabanaHorario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idsabana_horario")
    private Integer idsabanaHorario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_ficha")
    private int idFicha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_ambiente")
    private int idAmbiente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_instructor")
    private int idInstructor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_resultado")
    private int idResultado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_final")
    @Temporal(TemporalType.DATE)
    private Date fechaFinal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "hora_inicio")
    @Temporal(TemporalType.TIME)
    private Date horaInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "hora_final")
    @Temporal(TemporalType.TIME)
    private Date horaFinal;

    public SabanaHorario() {
    }

    public SabanaHorario(Integer idsabanaHorario) {
        this.idsabanaHorario = idsabanaHorario;
    }

    public SabanaHorario(Integer idsabanaHorario, int idFicha, int idAmbiente, int idInstructor, int idResultado, Date fechaInicio, Date fechaFinal, Date horaInicio, Date horaFinal) {
        this.idsabanaHorario = idsabanaHorario;
        this.idFicha = idFicha;
        this.idAmbiente = idAmbiente;
        this.idInstructor = idInstructor;
        this.idResultado = idResultado;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
        this.horaInicio = horaInicio;
        this.horaFinal = horaFinal;
    }

    public Integer getIdsabanaHorario() {
        return idsabanaHorario;
    }

    public void setIdsabanaHorario(Integer idsabanaHorario) {
        this.idsabanaHorario = idsabanaHorario;
    }

    public int getIdFicha() {
        return idFicha;
    }

    public void setIdFicha(int idFicha) {
        this.idFicha = idFicha;
    }

    public int getIdAmbiente() {
        return idAmbiente;
    }

    public void setIdAmbiente(int idAmbiente) {
        this.idAmbiente = idAmbiente;
    }

    public int getIdInstructor() {
        return idInstructor;
    }

    public void setIdInstructor(int idInstructor) {
        this.idInstructor = idInstructor;
    }

    public int getIdResultado() {
        return idResultado;
    }

    public void setIdResultado(int idResultado) {
        this.idResultado = idResultado;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsabanaHorario != null ? idsabanaHorario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SabanaHorario)) {
            return false;
        }
        SabanaHorario other = (SabanaHorario) object;
        if ((this.idsabanaHorario == null && other.idsabanaHorario != null) || (this.idsabanaHorario != null && !this.idsabanaHorario.equals(other.idsabanaHorario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dao.SabanaHorario[ idsabanaHorario=" + idsabanaHorario + " ]";
    }
    
}
