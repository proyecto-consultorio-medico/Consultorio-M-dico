/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Date;
import java.text.SimpleDateFormat;
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
    private Date fecha;
    private String telefono;
    private String correo;
    
    public void setCedula(String cedula) {
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
    }

    public String getCedula() {
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

    public Pacientes(String cedula, String nombreCompleto, String telefono, String correo,Date fecha) {
        this.cedula = cedula;
        this.nombreCompleto = nombreCompleto;
        this.telefono = telefono;
        this.correo = correo;
        this.fecha=fecha;
    }

    public Pacientes(String cedula, String nombreCompleto, String telefono,Date fecha) {
        this.cedula = cedula;
        this.nombreCompleto = nombreCompleto;
        this.telefono = telefono;
        this.fecha=fecha;
    }

    public Pacientes(String cedula, String nombreCompleto,Date fecha) {
        this.cedula = cedula;
        this.nombreCompleto = nombreCompleto;
       this.fecha=fecha;
      
    }

    public Pacientes(String cedula, String nombreCompleto) {
        this.cedula = cedula;
        this.nombreCompleto = nombreCompleto;
          this.fecha=null;
    }
   


    public String calcularedad(Date fecha) {
        if (fecha!=null) {
            SimpleDateFormat fechas= new SimpleDateFormat("yyyy/MM/dd");
            String fechaString=fechas.format(fecha);
            DateTimeFormatter calendario = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDate fechaNac = LocalDate.parse(fechaString, calendario);
            LocalDate ahora = LocalDate.now();
            Period periodo = Period.between(fechaNac, ahora);
            return periodo.getYears() + " años y  " + periodo.getMonths() + " meses";
        } 
        return null;
    }
    
    public boolean contarDigitosCedu(){
        this.cedula = cedula.replaceAll("[^0-9]","");
 return cedula.length()==9||cedula.length()==10;
        
    }
public boolean contarDigitostel(){
       this.telefono= telefono.replaceAll("[^0-9]","");
               return this.telefono.length()==8;
    }

public void ponerMayusculas(){
        String nombreConLasMayusculas = "";
        for (String palabra : this.nombreCompleto.split(" ")) {
            nombreConLasMayusculas += palabra.substring(0, 1).toUpperCase() + palabra.substring(1, palabra.length()).toLowerCase() + " ";
        }
        nombreConLasMayusculas = nombreConLasMayusculas.trim();
        this.nombreCompleto=nombreConLasMayusculas;
       
        }
    @Override
    public String toString() {
        return "Pacientes{" + "cedula=" + cedula + ", nombreCompleto=" + nombreCompleto + ", fecha=" + fecha + ", telefono=" + telefono + ", correo=" + correo + '}';
    }
    
    
}
