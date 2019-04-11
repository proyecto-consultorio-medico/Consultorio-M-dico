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

    public Secretaria(String usuario) {
        this.usuario = usuario;
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
 public boolean comprobarCedula(){
        BD bd = new BD("SELECT * FROM secretarias WHERE Cedula=?");
      
        if (  bd.ejectuar(new Object []{this.cedula})==true) {
          try {
              bd.sentencia.getResultSet().next();
              if (bd.sentencia.getResultSet().getString(1).equals(this.cedula)) {
                  return false;
              }    
        } catch (SQLException ex) {
            Logger.getLogger(ControladorPacientes.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        return true;
 }     
 
 public boolean comprobarUsuario(){
BD bd = new BD("SELECT * FROM `secretarias` WHERE `Usuario`=?");
      
        if (  bd.ejectuar(new Object []{this.usuario})==true) {
          try {
              while (bd.sentencia.getResultSet().next()) {
                 if (bd.sentencia.getResultSet().getString(6).equals(this.usuario)) {
                  return false;
              }    
            }
             
        } catch (SQLException ex) {
            Logger.getLogger(ControladorPacientes.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        return true;
 }
}
