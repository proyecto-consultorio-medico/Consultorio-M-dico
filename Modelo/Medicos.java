/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Jose,Marco,Yuliana,Elver
 */
public class Medicos {
    private String nombre;
     private String cedula;
      private String fecha;
       private String correo;
        private String telefono;
         private String usuario;
          private String pass;
           private String especialidad;
            private String codigo;
             private double salario;

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

    public Medicos() {
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public String getCodigo() {
        return codigo;
    }

    public Double getSalario() {
        return salario;
    }
    public Medicos(String nombre, String cedula, String fecha, String correo, String telefono, String usuario, String pass, String especialidad, String codigo, double salario) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.fecha = fecha;
        this.correo = correo;
        this.telefono = telefono;
        this.usuario = usuario;
        this.pass = pass;
        this.especialidad = especialidad;
        this.codigo = codigo;
        this.salario = salario;
    }
    
    
}
