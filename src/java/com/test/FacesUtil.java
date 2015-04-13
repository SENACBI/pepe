/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Administrador
 */
public final class FacesUtil {
    
    public static void displayMessage(FacesMessage.Severity sev, String sumary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(sev, sumary, detail));
    }
}
