/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test;

import com.pepe.jpa.entities.AmbienteFormacion;
import com.pepe.jpa.entities.Competencia;
import com.pepe.jpa.entities.Ficha;
import com.pepe.jpa.entities.Programa;
import com.pepe.jpa.entities.ResultadoAprendizaje;
import com.pepe.jpa.entities.SabanaHorario;
import com.pepe.jpa.entities.Usuario;
import com.pepe.jpa.sesions.AbstractFacade;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author kevin
 */
@Stateless
public class ejbHorario extends AbstractFacade<Ficha> {

    private static final Logger log = Logger.getLogger(ejbHorario.class
            .getName());
    @PersistenceContext(unitName = "pepeAplicacionPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ejbHorario() {
        super(Ficha.class);
    }

    public List<Competencia> searchListCompetencia(Programa codigo) {
		// return
        // em.createNamedQuery("Competencia.findByCodigo").setParameter("codigo",
        // codigo).getResultList();
        Query q = em.createQuery(
                "SELECT c FROM Competencia c INNER JOIN c.programaList p WHERE p = :codigo")
                .setParameter("codigo", codigo);
        return q.getResultList();
    }

    public List<ResultadoAprendizaje> searchListResultado(int idCompetencia) {
        Query q = em.createQuery(
                "SELECT DISTINCT r FROM ResultadoAprendizaje r, Competencia c    WHERE c.idCompetencia=:id And c=r.idCompetencia")
                .setParameter("id", idCompetencia);
        return q.getResultList();
    }

    public List<Usuario> searchListUser() {
        Query q = em.createQuery("SELECT u FROM Usuario u ");
        return q.getResultList();
    }

    public void saveHorario(SabanaHorario h) {
        try {
            em.persist(h);
        } catch (Exception e) {
            e.getMessage();
        }
    }

	// validar si salon esta disponible
    public ArrayList<String> validator(SabanaHorario h) {
        ArrayList<String> listMessage = new ArrayList<String>();
        List<SabanaHorario> listSabana = Collections.emptyList();
        listSabana = em.createNamedQuery("SabanaHorario.findByIdAmbiente")
                .setParameter("idAmbiente", h.getIdAmbiente()).getResultList();
        for (SabanaHorario sb : listSabana) {
            // comparar rango de fechas sin horas
            Date fechaInicio = sb.getFechaInicio();
            Date fechaFinal = sb.getFechaFinal();
            fechaInicio.setHours(0);
            fechaInicio.setMinutes(0);
            fechaInicio.setSeconds(0);
            fechaFinal.setHours(0);
            fechaFinal.setMinutes(0);
            fechaFinal.setSeconds(0);
            Date resquestInicio = h.getFechaInicio();
            Date resquestFinal = h.getFechaFinal();
            resquestInicio.setHours(0);
            resquestInicio.setMinutes(0);
            resquestInicio.setSeconds(0);
            resquestFinal.setHours(0);
            resquestFinal.setMinutes(0);
            resquestFinal.setSeconds(0);
            // verificar rango fecha inicio
            boolean menor = false;
            boolean mayor = false;
            boolean verificarHora = false;
            if (resquestInicio.compareTo(fechaInicio) == 1
                    || resquestInicio.compareTo(fechaInicio) == 0) {
                menor = true;
            }
            if (resquestInicio.compareTo(fechaFinal) == -1
                    || resquestInicio.compareTo(fechaFinal) == 0) {
                mayor = true;
            }
            if (mayor && menor) {
                log.info("entre rango inicio");
                verificarHora = true;
            }
            // validacionfecha final
            menor = false;
            mayor = false;
            if (resquestFinal.compareTo(fechaInicio) == 1
                    || resquestFinal.compareTo(fechaInicio) == 0) {
                mayor = true;
            }
            if (resquestFinal.compareTo(fechaFinal) == -1
                    || resquestFinal.compareTo(fechaFinal) == 0) {
                menor = true;
            }
            if (mayor && menor) {
                log.info("entre rango inicio");
                verificarHora = true;
            }
            // esta entre el rango de fecha consultar hora disponible
            if (verificarHora) {
				// verificar horario
                // seteo de fecha lista en mysql el mes 0 es enero y date es
                // diciembre
                h.getHoraInicio().setYear(2015);
                h.getHoraInicio().setMonth(0);
                h.getHoraInicio().setDate(0);
                h.getHoraFinal().setYear(2015);
                h.getHoraFinal().setMonth(0);
                h.getHoraFinal().setDate(0);
                sb.getHoraInicio().setYear(2015);
                sb.getHoraInicio().setMonth(0);
                sb.getHoraInicio().setDate(0);
                sb.getHoraFinal().setYear(2015);
                sb.getHoraFinal().setMonth(0);
                sb.getHoraFinal().setDate(0);
                if (h.getHoraInicio().compareTo(sb.getHoraInicio()) >= 0
                        && h.getHoraInicio().compareTo(sb.getHoraFinal()) <= 0) {
                    Usuario instructor = (Usuario) em
                            .createNamedQuery("Usuario.findByIdUsuario")
                            .setParameter("idUsuario", sb.getIdInstructor())
                            .getSingleResult();
                    listMessage.add("el salon ya esta programado "
                            + new SimpleDateFormat("dd-MM-yyyy").format(sb
                                    .getFechaInicio())
                            + " desde las "
                            + new SimpleDateFormat("HH:mm:ss").format(sb
                                    .getHoraInicio())
                            + " hasta las "
                            + new SimpleDateFormat("HH:mm:ss").format(sb
                                    .getHoraFinal()) + " por el instructor "
                            + instructor.getNombre1() + " "
                            + instructor.getApellido1());

                }
                if (h.getHoraFinal().compareTo(sb.getHoraInicio()) >= 0
                        && h.getHoraFinal().compareTo(sb.getHoraFinal()) <= 0) {
                    Usuario instructor = (Usuario) em
                            .createNamedQuery("Usuario.findByIdUsuario")
                            .setParameter("idUsuario", sb.getIdInstructor())
                            .getSingleResult();
                    listMessage.add("el salon ya esta programado "
                            + new SimpleDateFormat("dd-MM-yyyy").format(sb
                                    .getFechaInicio())
                            + " desde las "
                            + new SimpleDateFormat("HH:mm:ss").format(sb
                                    .getHoraInicio())
                            + " hasta las "
                            + new SimpleDateFormat("HH:mm:ss").format(sb
                                    .getHoraFinal()) + " por el instructor "
                            + instructor.getNombre1() + " "
                            + instructor.getApellido1());
                }
            }
        }
        return listMessage;
    }

    public List<MappingHorario> listHorarios() {
        List<SabanaHorario> listH = em
                .createNamedQuery("SabanaHorario.findAll").getResultList();
        List<MappingHorario> listFinal = new ArrayList<MappingHorario>();

        Query q = em.createQuery(" SELECT DISTINCT f,a,u,r,s  FROM Ficha f ,"
                + "AmbienteFormacion a,Usuario u,"
                + "ResultadoAprendizaje r,SabanaHorario s"
                + " WHERE F.idFicha=s.idFicha"
                + " AND a.idAmbienteFormacion=s.idAmbiente"
                + " AND u.idUsuario=s.idInstructor "
                + "AND r.idResultadoAprendizaje=s.idResultado");
        ;

        List<Object[]> result = q.getResultList();
        for (int i = 0; i < result.size(); i++) {
            MappingHorario temp = new MappingHorario();
            SabanaHorario sabana = (SabanaHorario) result.get(i)[4];
            temp.setFicha((Ficha) result.get(i)[0]);
            temp.setAmbiente((AmbienteFormacion) result.get(i)[1]);
            temp.setInstrunctor((Usuario) result.get(i)[2]);
            temp.setResultadoAprendizaje((ResultadoAprendizaje) result.get(i)[3]);
            temp.setFechaInicio(sabana.getFechaInicio());
            temp.setFechaFinal(sabana.getFechaFinal());
            temp.setHoraInicio(sabana.getHoraInicio());
            temp.setHoraFinal(sabana.getHoraFinal());
            log.info("" + temp.getFicha().getCodigoFicha());
            log.info("" + temp.getAmbiente().getDenominacionAmbienteFormacion());
            log.info("" + temp.getInstrunctor().getNombre1());
            log.info(""
                    + temp.getResultadoAprendizaje()
                    .getNombreResultadoAprendizaje());
            log.info("" + temp.getFechaInicio());
            log.info("" + temp.getFechaFinal());
            log.info("" + temp.getHoraInicio());
            log.info("" + temp.getHoraFinal());
            listFinal.add(temp);
        }
        return listFinal;
    }

}
