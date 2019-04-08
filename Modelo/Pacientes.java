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
    private String cedula;
    private String nombreCompleto;
    private String fecha;
    private String telefono;
    private String correo;

    public String getCedula() {
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

    public Pacientes(String cedula, String nombreCompleto, String fecha, String telefono, String correo) {
        this.cedula = cedula;
        this.nombreCompleto = nombreCompleto;
        this.fecha = fecha;
        this.telefono = telefono;
        this.correo = correo;
    }

    public Pacientes(String cedula, String nombreCompleto, String fecha, String telefono) {
        this.cedula = cedula;
        this.nombreCompleto = nombreCompleto;
        this.fecha = fecha;
        this.telefono = telefono;
        this.correo = null;
    }

    public Pacientes(String cedula, String nombreCompleto, String fecha) {
        this.cedula = cedula;
        this.nombreCompleto = nombreCompleto;
        this.fecha = fecha;
        this.telefono = null;
        this.correo = null;
    }

    public Pacientes(String cedula, String nombreCompleto) {
        this.cedula = cedula;
        this.nombreCompleto = nombreCompleto;
        this.fecha = null;
        this.telefono = null;
        this.correo = null;
    }

    public Pacientes(String cedula) {
        this.cedula = cedula;
    }

    public String calcularedad(String fecha) {
        if (fecha != "") {
            DateTimeFormatter calendario = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate fechaNac = LocalDate.parse(fecha, calendario);
            LocalDate ahora = LocalDate.now();
            Period periodo = Period.between(fechaNac, ahora);
            return periodo.getYears() + " años y  " + periodo.getMonths() + " meses";
        }
        return null;
    }
}
