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
import javafx.scene.input.KeyCode;
import javax.swing.JOptionPane;

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
               private int año;
                private int mes;
            
    public double SalarioNeto;

    public Medicos(double Salario) {
        this.salario = Salario;
        this.SalarioNeto = 0;
    }

    public Medicos() {
        this.salario = 0;
        this.SalarioNeto = 0;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        if (salario > 0.0) {
            this.salario = salario;
        }
    }


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

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCedula(String cedula) {
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

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setSalarioNeto(double SalarioNeto) {
        this.SalarioNeto = SalarioNeto;
    }

  

    public String getEspecialidad() {
        return especialidad;
    }

    public String getCodigo() {
        return codigo;
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

    public Medicos(String usuario) {
        this.usuario = usuario;
    }


    
    
       
    public void calcularSalarioNeto() {
        if (this.salario < 817000) {
            this.deducionesDelSalario();
            System.out.println("Su salario es: " + this.salario + " y su salario neto es: " + this.SalarioNeto);

        }
        if (this.salario > 817001 && this.salario < 1226000) {
            double ImpuestosDeRenta10 = (this.salario * 10) / 100;
            this.deducionesDelSalario();
            this.SalarioNeto = this.SalarioNeto - ImpuestosDeRenta10;
            System.out.println("Su salario es: " + this.salario + " y su salario neto es: " + this.SalarioNeto);

        }
        if (this.salario > 1226001) {
            double ImpuestosDeRenta15 = (this.salario * 15) / 100;
            this.deducionesDelSalario();
            this.SalarioNeto = this.SalarioNeto - ImpuestosDeRenta15;
            System.out.println("Su salario es: " + this.salario + " y su salario neto es: " + this.SalarioNeto);
        }
    }

    public void deducionesDelSalario() {//Se calculan todas las deduciones y se le aplican de una vez al salario neto
        double EnfermadYMaternidad = (this.salario * 5.5) / 100;
        double InvalidezYMuerte = (this.salario * 3.64) / 100;
        double AporteDelTrabajador = (this.salario * 1) / 100;
        double AportaALaAsociaciónSolidarista = (this.salario * 3.3) / 100;
        double total = EnfermadYMaternidad + InvalidezYMuerte + AporteDelTrabajador + AportaALaAsociaciónSolidarista;
        this.SalarioNeto = this.salario - total;
    }

}
