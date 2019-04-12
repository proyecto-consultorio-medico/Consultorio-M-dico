/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Controlador.ControladorPacientes;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jose,Marco,Yuliana,Elver
 */
public class Secretaria {
    private String nombre;
     private int cedula;
      private String fecha;
       private String correo;
        private String telefono;
         private String usuario;
          private String pass;
           

    public String getNombre() {
        return nombre;
    }

    public int getCedula() {
        return cedula;
    }

    public String getFecha() {
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

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Secretaria() {
         this.nombre = null;
        this.cedula = 0;
        this.fecha = null;
        this.correo = null;
        this.telefono = null;
        this.usuario= null;
        this.pass= null;
    }

    
    public Secretaria(String nombre, int cedula, String fecha, String correo, String telefono,String usuario,String pass) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.fecha = fecha;
        this.correo = correo;
        this.telefono = telefono;
        this.usuario= usuario;
        this.pass= pass;
    }
 
}
