package com.test;

import com.pepe.jpa.entities.AmbienteFormacion;
import com.pepe.jpa.entities.Competencia;
import com.pepe.jpa.entities.Ficha;
import com.pepe.jpa.entities.Programa;
import com.pepe.jpa.entities.ResultadoAprendizaje;
import com.pepe.jpa.entities.SabanaHorario;
import com.pepe.jpa.entities.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named("horarioController")
@ViewScoped
public class Horario implements Serializable {

    private static final Logger log = Logger.getLogger(Horario.class.getName());
    private Ficha current;
    private Programa currentP;
    private AmbienteFormacion currentA;
    private Usuario currentU;
    private ResultadoAprendizaje currentR;
    private ArrayList<String> listMessage = new ArrayList<String>();
    private Date dateIni;
    private Date dateEnd;
    private Date hourIni;
    private Date hourEnd;
    private boolean saveDialog = false;

    @EJB
    private com.pepe.jpa.sesions.FichaFacade ejbFacade;
    @EJB
    private com.pepe.jpa.sesions.ProgramaFacade ejbFacadeP;
    @EJB
    private com.pepe.jpa.sesions.AmbienteFormacionFacade ejbFacadeA;
    @EJB
    private com.pepe.jpa.sesions.UsuarioFacade ejbFacadeU;
    @EJB
    private com.pepe.jpa.sesions.CompetenciaFacade ejbFacadeC;
    @EJB
    private com.test.ejbHorario ejbFacadeH;

    public void testMapping() {
        ejbFacadeH.listHorarios();
    }

    private List<Ficha> listFicha = Collections.emptyList();
    private List<AmbienteFormacion> listAmbientes = Collections.emptyList();
    private List<Usuario> listUsuario = Collections.emptyList();
    private List<Competencia> listCompetencias = Collections.emptyList();
    private List<ResultadoAprendizaje> listResultado = Collections.emptyList();

    SabanaHorario horario;

    public void save() {
        saveDialog = false;
        dateIni.setHours(0);
        dateIni.setMinutes(0);
        dateIni.setSeconds(0);
        dateEnd.setMinutes(0);
        dateEnd.setHours(0);
        dateEnd.setSeconds(0);
        hourIni.setYear(2015);
        hourEnd.setYear(2015);
        hourIni.setMonth(0);
        hourEnd.setMonth(0);
        hourIni.setDate(0);
        hourEnd.setDate(0);
        horario = new SabanaHorario();
        horario.setIdFicha(current.getIdFicha());
        horario.setIdAmbiente(currentA.getIdAmbienteFormacion());
        horario.setIdInstructor(currentU.getIdUsuario());
        horario.setIdResultado(currentR.getIdResultadoAprendizaje());
        horario.setFechaInicio(dateIni);
        horario.setFechaFinal(dateEnd);
        horario.setHoraInicio(hourIni);
        horario.setHoraFinal(hourEnd);
        int result = horario.getFechaInicio()
                .compareTo(horario.getFechaFinal());
        int resultHours = horario.getHoraInicio().compareTo(
                horario.getHoraFinal());
        if (resultHours != -1 || resultHours == 0) {
            FacesUtil.displayMessage(FacesMessage.SEVERITY_ERROR,
                    "la hora final debe ser mayor a la de inicio", "");
        } else if (result >= 0) {
            FacesUtil.displayMessage(FacesMessage.SEVERITY_ERROR,
                    "la fecha de inicio debe ser mayor a la limite", "");
        } else {
            // validaciones
            listMessage = new ArrayList<String>();
            listMessage = ejbFacadeH.validator(horario);
            saveDialog = true;
            if (getListMessage().size() > 0) {

                for (int i = 0; i < listMessage.size(); i++) {
                    FacesUtil.displayMessage(FacesMessage.SEVERITY_ERROR, ""
                            + listMessage.get(i), "");
                }
            } else {
                log.info("no permitor registro");
            }
        }
    }

    public void saveHorario() {
        try {
            ejbFacadeH.saveHorario(horario);
            FacesUtil.displayMessage(FacesMessage.SEVERITY_INFO,
                    "Guardo correcto", "");
            saveDialog = false;

            return;
        } catch (Exception e) {
            FacesUtil.displayMessage(FacesMessage.SEVERITY_ERROR,
                    "" + e.getMessage(), "");
        }
    }

    public void cancel() {
        saveDialog = false;
    }

    public void changeFichaList(ValueChangeEvent e) {
        log.info("el id ficha es" + current.getIdFicha());
        if (current.getIdFicha() != null) {
            Ficha f = ejbFacade.find(getCurrent().getIdFicha());
            getCurrentP();
            setCurrentP(f.getPrograma());
            log.info("programa" + getCurrentP().getProgramaPK().getCodigo());
            listCompetencias = ejbFacadeH.searchListCompetencia(getCurrentP());
            listResultado = new ArrayList<>();
            for (Competencia c : listCompetencias) {
                List<ResultadoAprendizaje> temp = ejbFacadeH
                        .searchListResultado(c.getIdCompetencia());
                if (temp.size() > 0) {
                    listResultado.addAll(temp);
                }
            }
            // user
            listUsuario = ejbFacadeH.searchListUser();
        }
    }

    public Ficha getCurrent() {
        if (current == null) {
            current = new Ficha();
        }
        return current;
    }

    public void setCurrent(Ficha current) {

        this.current = current;
    }

    public com.pepe.jpa.sesions.FichaFacade getEjbFacade() {
        return ejbFacade;
    }

    public List<Ficha> getListFicha() {
        if (listFicha.isEmpty()) {
            listFicha = ejbFacade.findAll();
        }
        return listFicha;
    }

    public void setListFicha(List<Ficha> listFicha) {
        this.listFicha = listFicha;
    }

    public Programa getCurrentP() {
        if (currentP == null) {
            currentP = new Programa();
        }
        return currentP;
    }

    public void setCurrentP(Programa currentP) {
        this.currentP = currentP;
    }

    public com.pepe.jpa.sesions.ProgramaFacade getEjbFacadeP() {
        return ejbFacadeP;
    }

    public Date getDateIni() {
        return dateIni;
    }

    public void setDateIni(Date dateIni) {
        this.dateIni = dateIni;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Date getHourIni() {
        return hourIni;
    }

    public void setHourIni(Date hourIni) {
        this.hourIni = hourIni;
    }

    public Date getHourEnd() {
        return hourEnd;
    }

    public void setHourEnd(Date hourEnd) {
        this.hourEnd = hourEnd;
    }

    public AmbienteFormacion getCurrentA() {
        if (currentA == null) {
            currentA = new AmbienteFormacion();
        }
        return currentA;
    }

    public void setCurrentA(AmbienteFormacion currentA) {
        this.currentA = currentA;
    }

    public com.pepe.jpa.sesions.AmbienteFormacionFacade getEjbFacadeA() {
        return ejbFacadeA;
    }

    public List<AmbienteFormacion> getListAmbientes() {
        if (listAmbientes.isEmpty()) {
            listAmbientes = ejbFacadeA.findAll();
        }
        return listAmbientes;
    }

    public void setListAmbientes(List<AmbienteFormacion> listAmbientes) {
        this.listAmbientes = listAmbientes;
    }

    public Usuario getCurrentU() {
        if (currentU == null) {
            currentU = new Usuario();
        }
        return currentU;
    }

    public void setCurrentU(Usuario currentU) {
        this.currentU = currentU;
    }

    public com.pepe.jpa.sesions.UsuarioFacade getEjbFacadeU() {
        return ejbFacadeU;
    }

    public List<Usuario> getListUsuario() {
        if (listUsuario.isEmpty()) {
            listUsuario = new ArrayList<>();
        }
        return listUsuario;
    }

    public void setListUsuario(List<Usuario> listUsuario) {
        this.listUsuario = listUsuario;
    }

    public List<Competencia> getListCompetencias() {
        if (listCompetencias.isEmpty()) {
            listCompetencias = new ArrayList<>();
        }
        return listCompetencias;
    }

    public void setListCompetencias(List<Competencia> listCompetencias) {
        this.listCompetencias = listCompetencias;
    }

    public com.pepe.jpa.sesions.CompetenciaFacade getEjbFacadeC() {
        return ejbFacadeC;
    }

    public com.test.ejbHorario getEjbFacadeH() {
        return ejbFacadeH;
    }

    public List<ResultadoAprendizaje> getListResultado() {
        return listResultado;
    }

    public void setListResultado(List<ResultadoAprendizaje> listResultado) {
        this.listResultado = listResultado;
    }

    public ResultadoAprendizaje getCurrentR() {
        if (currentR == null) {
            currentR = new ResultadoAprendizaje();
        }
        return currentR;
    }

    public void setCurrentR(ResultadoAprendizaje currentR) {
        this.currentR = currentR;
    }

    public ArrayList<String> getListMessage() {
        return listMessage;
    }

    public void setListMessage(ArrayList<String> listMessage) {
        this.listMessage = listMessage;
    }

    public boolean isSaveDialog() {
        return saveDialog;
    }

    public void setSaveDialog(boolean saveDialog) {
        this.saveDialog = saveDialog;
    }

}
