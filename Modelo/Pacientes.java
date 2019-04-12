/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;



/**
 *
 * @author Jose,Marco,Yuliana,Elver
 */
public class Pacientes {
    private int cedula;
    private String nombreCompleto;
    private String fecha;
    private String telefono;
    private String correo;

    public void setCedula(int cedula) {
        this.cedula = cedula;
        this.telefono = null;
        this.correo = null;
           this.fecha = null;
           this.nombreCompleto=null;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    
    public Pacientes() {
       this.cedula=0;
    }

    public int getCedula() {
        return cedula;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getFecha() {
        return fecha;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public Pacientes(int cedula, String nombreCompleto, String fecha, String telefono, String correo) {
        this.cedula = cedula;
        this.nombreCompleto = nombreCompleto;
        this.fecha = fecha;
        this.telefono = telefono;
        this.correo = correo;
    }

    public Pacientes(int cedula, String nombreCompleto, String fecha, String telefono) {
        this.cedula = cedula;
        this.nombreCompleto = nombreCompleto;
        this.fecha = fecha;
        this.telefono = telefono;
        this.correo = null;
    }

    public Pacientes(int cedula, String nombreCompleto, String  fecha) {
        this.cedula = cedula;
        this.nombreCompleto = nombreCompleto;
        this.fecha = fecha;
        this.telefono = null;
        this.correo = null;
    }

    public Pacientes(int cedula, String nombreCompleto) {
        this.cedula = cedula;
        this.nombreCompleto = nombreCompleto;
        this.fecha = null;
        this.telefono = null;
        this.correo = null;
    }


    public String calcularedad(String fecha) {
        if (!"".equals(fecha)) {
            DateTimeFormatter calendario = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate fechaNac = LocalDate.parse(fecha, calendario);
            LocalDate ahora = LocalDate.now();
            Period periodo = Period.between(fechaNac, ahora);
            return periodo.getYears() + " años y  " + periodo.getMonths() + " meses";
        }
        return null;
    }

    @Override
    public String toString() {
        return "Pacientes{" + "cedula=" + cedula + ", nombreCompleto=" + nombreCompleto + ", fecha=" + fecha + ", telefono=" + telefono + ", correo=" + correo + '}';
    }
    
    
}
