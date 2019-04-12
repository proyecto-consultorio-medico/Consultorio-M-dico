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
    private Date fecha;
    private String telefono;
    private String correo;
    private int año;
    private int mes;
    private int dia;
    
    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public void setFecha(Date fecha) {
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

    public Date getFecha() {
        return fecha;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public int getAño() {
        return año;
    }

    public int getMes() {
        return mes;
    }

    public int getDia() {
        return dia;
    }
    
    
    public Pacientes(int cedula, String nombreCompleto, String telefono, String correo, int año, int mes, int dia) {
        this.cedula = cedula;
        this.nombreCompleto = nombreCompleto;
        this.telefono = telefono;
        this.correo = correo;
        this.año = año;
        this.mes = mes;
        this.dia = dia;
        this.fecha=null;
    }

    public Pacientes(int cedula, String nombreCompleto, String telefono, int año, int mes, int dia) {
        this.cedula = cedula;
        this.nombreCompleto = nombreCompleto;
        this.telefono = telefono;
        this.año = año;
        this.mes = mes;
        this.dia = dia;
          this.fecha=null;
    }

    public Pacientes(int cedula, String nombreCompleto, int año, int mes, int dia) {
        this.cedula = cedula;
        this.nombreCompleto = nombreCompleto;
        this.año = año;
        this.mes = mes;
        this.dia = dia;
          this.fecha=null;
    }

    public Pacientes(int cedula, String nombreCompleto) {
        this.cedula = cedula;
        this.nombreCompleto = nombreCompleto;
          this.fecha=null;
          this.año=0;
          this.mes=1;
          this.dia=0;
    }

    
    public void validarFecha(int año,int mes,int dia){
        año=año-1900;
        mes=mes-1;
        this.fecha= new Date(año,mes,dia);
        System.out.println(fecha);
    }

    public String calcularedad(String fecha) {
        if (!"".equals(fecha)) {
              String fechas;
            if (mes<10) {
              fechas=this.dia+"/"+"0"+this.mes+"/"+this.año;
            }else{
            fechas=this.dia+"/"+this.mes+"/"+this.año;
            }
            
            DateTimeFormatter calendario = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate fechaNac = LocalDate.parse(fechas, calendario);
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
