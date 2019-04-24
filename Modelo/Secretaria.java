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

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

             
             
    public Secretaria() {
       usuario=null;
    }

    public Secretaria(String nombre, String cedula, String correo, String telefono,String usuario,String pass,Date fecha) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.fecha = fecha;
        this.correo = correo;
        this.telefono = telefono;
        this.usuario= usuario;
        this.pass= pass;
    }
     
    public Secretaria(Object []obj){
        this.nombre = (String) obj[1];
        this.cedula = (String) obj[0];
        this.fecha = (Date) obj[2];
        this.correo = (String) obj[3];
        this.telefono = (String) obj[4];
        this.usuario= (String) obj[5];
        this.pass= (String) obj[6];
    }
 
    public boolean contarDigitosCedu(){
        this.cedula = cedula.replaceAll("[^0-9]","");
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

public void ponerMayusculas(){
        String nombreConLasMayusculas = "";
        for (String palabra : this.nombre.split(" ")) {
            nombreConLasMayusculas += palabra.substring(0, 1).toUpperCase() + palabra.substring(1, palabra.length()).toLowerCase() + " ";
        }
        nombreConLasMayusculas = nombreConLasMayusculas.trim();
        this.nombre=nombreConLasMayusculas;
        }
public Object[] toObject(){
    return new Object[]{this.cedula,this.nombre,this.fecha,this.correo,this.telefono,this.usuario,this.pass};
    }
}
