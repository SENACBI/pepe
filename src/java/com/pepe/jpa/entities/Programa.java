/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pepe.jpa.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Junior Cabal
 */
@Entity
@Table(name = "programa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Programa.findAll", query = "SELECT p FROM Programa p"),
    @NamedQuery(name = "Programa.findByCodigo", query = "SELECT p FROM Programa p WHERE p.programaPK.codigo = :codigo"),
     @NamedQuery(name = "Programa.encontrarCodigoVersion", query = "SELECT p FROM Programa p WHERE p.programaPK.codigo = :codigo AND p.programaPK.version = :version"),
    @NamedQuery(name = "Programa.findByCodigo", query = "SELECT p FROM Programa p WHERE p.programaPK.codigo = :codigo"),
    @NamedQuery(name = "Programa.encontrarNombrePrograma", query = "SELECT p FROM Programa p WHERE p.nombrePrograma LIKE :nombrePrograma"),
    @NamedQuery(name = "Programa.findByNombrePrograma", query = "SELECT p FROM Programa p WHERE p.nombrePrograma like :nombrePrograma"),
    @NamedQuery(name = "Programa.findByVersion", query = "SELECT p FROM Programa p WHERE p.programaPK.version = :version"),
    @NamedQuery(name = "Programa.findByDuracionTrimestresLectiva", query = "SELECT p FROM Programa p WHERE p.duracionTrimestresLectiva = :duracionTrimestresLectiva"),
    @NamedQuery(name = "Programa.findByEstado", query = "SELECT p FROM Programa p WHERE p.estado = :estado"),
    @NamedQuery(name = "Programa.findByDuracionTrimestresProductiva", query = "SELECT p FROM Programa p WHERE p.duracionTrimestresProductiva = :duracionTrimestresProductiva")})
public class Programa implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProgramaPK programaPK;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "nombre_programa")
    private String nombrePrograma;
    @Basic(optional = false)
    @NotNull
    @Column(name = "duracion_trimestres_lectiva")
    private short duracionTrimestresLectiva;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private short estado;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 2147483647)
    @Column(name = "justificacion")
    private String justificacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "duracion_trimestres_productiva")
    private short duracionTrimestresProductiva;
    @ManyToMany(mappedBy = "programaList")
    private List<Competencia> competenciaList;
    @JoinColumn(name = "id_linea_tecnologica", referencedColumnName = "id_linea_tecnologica")
    @ManyToOne(optional = false)
    private LineaTecnologica idLineaTecnologica;
    @JoinColumn(name = "id_modalidad_formacion", referencedColumnName = "id_modalidad_formacion")
    @ManyToOne(optional = false)
    private ModalidadFormacion idModalidadFormacion;
    @JoinColumn(name = "id_nivel_formacion", referencedColumnName = "id_nivel_formacion")
    @ManyToOne(optional = false)
    private NivelFormacion idNivelFormacion;
    @JoinColumn(name = "id_perfil_entrada", referencedColumnName = "id_perfil_entrada")
    @ManyToOne(optional = false)
    private PerfilEntrada idPerfilEntrada;
    @JoinColumn(name = "id_tipo_formacion", referencedColumnName = "id_tipo_formacion")
    @ManyToOne(optional = false)
    private TipoFormacion idTipoFormacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "programa")
    private List<Ficha> fichaList;

    public Programa() {
    }

    public Programa(ProgramaPK programaPK) {
        this.programaPK = programaPK;
    }

    public Programa(ProgramaPK programaPK, String nombrePrograma, short duracionTrimestresLectiva, short estado, String justificacion, short duracionTrimestresProductiva) {
        this.programaPK = programaPK;
        this.nombrePrograma = nombrePrograma;
        this.duracionTrimestresLectiva = duracionTrimestresLectiva;
        this.estado = estado;
        this.justificacion = justificacion;
        this.duracionTrimestresProductiva = duracionTrimestresProductiva;
    }

    public Programa(String codigo, String version) {
        this.programaPK = new ProgramaPK(codigo, version);
    }

    public ProgramaPK getProgramaPK() {
        return programaPK;
    }

    public void setProgramaPK(ProgramaPK programaPK) {
        this.programaPK = programaPK;
    }

    public String getNombrePrograma() {
        return nombrePrograma;
    }

    public void setNombrePrograma(String nombrePrograma) {
        this.nombrePrograma = nombrePrograma;
    }

    public short getDuracionTrimestresLectiva() {
        return duracionTrimestresLectiva;
    }

    public void setDuracionTrimestresLectiva(short duracionTrimestresLectiva) {
        this.duracionTrimestresLectiva = duracionTrimestresLectiva;
    }

    public short getEstado() {
        return estado;
    }

    public void setEstado(short estado) {
        this.estado = estado;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    public short getDuracionTrimestresProductiva() {
        return duracionTrimestresProductiva;
    }

    public void setDuracionTrimestresProductiva(short duracionTrimestresProductiva) {
        this.duracionTrimestresProductiva = duracionTrimestresProductiva;
    }

    @XmlTransient
    public List<Competencia> getCompetenciaList() {
        return competenciaList;
    }

    public void setCompetenciaList(List<Competencia> competenciaList) {
        this.competenciaList = competenciaList;
    }

    public LineaTecnologica getIdLineaTecnologica() {
        return idLineaTecnologica;
    }

    public void setIdLineaTecnologica(LineaTecnologica idLineaTecnologica) {
        this.idLineaTecnologica = idLineaTecnologica;
    }

    public ModalidadFormacion getIdModalidadFormacion() {
        return idModalidadFormacion;
    }

    public void setIdModalidadFormacion(ModalidadFormacion idModalidadFormacion) {
        this.idModalidadFormacion = idModalidadFormacion;
    }

    public NivelFormacion getIdNivelFormacion() {
        return idNivelFormacion;
    }

    public void setIdNivelFormacion(NivelFormacion idNivelFormacion) {
        this.idNivelFormacion = idNivelFormacion;
    }

    public PerfilEntrada getIdPerfilEntrada() {
        return idPerfilEntrada;
    }

    public void setIdPerfilEntrada(PerfilEntrada idPerfilEntrada) {
        this.idPerfilEntrada = idPerfilEntrada;
    }

    public TipoFormacion getIdTipoFormacion() {
        return idTipoFormacion;
    }

    public void setIdTipoFormacion(TipoFormacion idTipoFormacion) {
        this.idTipoFormacion = idTipoFormacion;
    }

    @XmlTransient
    public List<Ficha> getFichaList() {
        return fichaList;
    }

    public void setFichaList(List<Ficha> fichaList) {
        this.fichaList = fichaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (programaPK != null ? programaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Programa)) {
            return false;
        }
        Programa other = (Programa) object;
        if ((this.programaPK == null && other.programaPK != null) || (this.programaPK != null && !this.programaPK.equals(other.programaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombrePrograma;
    }
    
}
