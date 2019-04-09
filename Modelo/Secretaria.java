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
     private String cedula;
      private String fecha;
       private String correo;
        private String telefono;
         private String usuario;
          private String pass;
           

    public String getNombre() {
        return nombre;
    }

    public String getCedula() {
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

    
    public Secretaria(String nombre, String cedula, String fecha, String correo, String telefono,String usuario,String pass) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.fecha = fecha;
        this.correo = correo;
        this.telefono = telefono;
        this.usuario= usuario;
        this.pass= pass;
    }

           
    
}
