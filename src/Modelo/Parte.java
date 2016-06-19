/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author UsueUrruela
 */
public class Parte {
    private String kmPrin;
    private String kmLleg;
    private boolean cerrado;
    private boolean validado;
    private java.sql.Date fecha;
    private String gasoil;
    private String autopista;
    private String dietas;
    private String otros;
    private String incidencias;
    private ArrayList<Albaran> albaranes;
    private String dniLog;
    private String dniAdm;
    private String id;
    

    public Parte(String kmPrin, String kmLleg, boolean cerrado, Date fecha, String gasoil, String autopista, String dietas, String otros, String incidencias) {
        this.kmPrin = kmPrin;
        this.kmLleg = kmLleg;
        this.cerrado = cerrado;
        this.fecha = fecha;
        this.gasoil = gasoil;
        this.autopista = autopista;
        this.dietas = dietas;
        this.otros = otros;
        this.incidencias = incidencias;
        albaranes=new ArrayList();
    }

    public ArrayList<Albaran> getAlbaranes() {
        return albaranes;
    }

    public void setAlbaranes(Albaran a) {
        albaranes.add(a);
    }

    public void setAlbaranesArray(ArrayList<Albaran> a) {
        this.albaranes = a;
    }
    
    public String getKmPrin() {
        return kmPrin;
    }

    public void setKmPrin(String kmPrin) {
        this.kmPrin = kmPrin;
    }

    public String getKmLleg() {
        return kmLleg;
    }

    public void setKmLleg(String kmLleg) {
        this.kmLleg = kmLleg;
    }

    public boolean isCerrado() {
        return cerrado;
    }

    public void setCerrado(boolean cerrado) {
        this.cerrado = cerrado;
    }

    public boolean isValidado() {
        return validado;
    }

    public void setValidado(boolean validado) {
        this.validado = validado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getGasoil() {
        return gasoil;
    }

    public void setGasoil(String gasoil) {
        this.gasoil = gasoil;
    }

    public String getAutopista() {
        return autopista;
    }

    public void setAutopista(String autopista) {
        this.autopista = autopista;
    }

    public String getDietas() {
        return dietas;
    }

    public void setDietas(String dietas) {
        this.dietas = dietas;
    }

    public String getOtros() {
        return otros;
    }

    public void setOtros(String otros) {
        this.otros = otros;
    }

    public String getIncidencias() {
        return incidencias;
    }

    public void setIncidencias(String incidencias) {
        this.incidencias = incidencias;
    }

    public String getDniLog() {
        return dniLog;
    }

    public void setDniLog(String dniLog) {
        this.dniLog = dniLog;
    }

    public String getDniAdm() {
        return dniAdm;
    }

    public void setDniAdm(String dniAdm) {
        this.dniAdm = dniAdm;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    
    
}
