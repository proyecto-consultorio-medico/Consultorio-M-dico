/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Date;

/**
 *
 * @author Jose,Marco,Yuliana,Elver
 */
public class Secretaria {
    private String nombre;
     private String cedula;
      private Date fecha;
       private String correo;
        private String telefono;
         private String usuario;
          private String pass;
           private int año;
            private int mes;
             private int dia;

    public String getNombre() {
        return nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getCorreo() {
        return correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getPass() {
        return pass;
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

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

             
             
    public Secretaria() {
       usuario=null;
    }

    public Secretaria(String nombre, String cedula, String correo, String telefono,String usuario,String pass,int año ,int mes,int dia) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.fecha = null;
        this.correo = correo;
        this.telefono = telefono;
        this.usuario= usuario;
        this.pass= pass;
        this.dia=dia;
        this.mes=mes;
        this.año=año;
    }
     
    public void validarFecha(int año,int mes,int dia){
        año=año-1900;
        mes=mes-1;
        this.fecha= new Date(año,mes,dia);
    }
    
    public boolean contarDigitosCedu(){
        this.cedula = cedula.replaceAll("[^0-9]","");
        System.out.println(this.cedula);
        if (cedula.length()>9) {
            return false;
        }
            return true;
        
    }
public boolean contarDigitostel(){
       this.telefono= telefono.replaceAll("[^0-9]","");
        if (this.telefono.length()>8) {
            return false;
        }
            return true;
    }
}
