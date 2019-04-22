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
public class Medicos {
    private String nombre;
     private String cedula;
      private Date fecha;
       private String correo;
        private String telefono;
         private String usuario;
          private String pass;
           private String especialidad;
            private String codigo;
             private double salario;
               private int año;
                private int mes;
                  private int dia;
            
    public double SalarioNeto;

    public Medicos(double Salario) {
        this.salario = Salario;
        this.SalarioNeto = 0;
    }

    public Medicos() {
        this.cedula=null;
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

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public void setFecha(Date fecha) {
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

    public int getAño() {
        return año;
    }

    public int getMes() {
        return mes;
    }

    public int getDia() {
        return dia;
    }


    public String getEspecialidad() {
        return especialidad;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public Medicos(String nombre, String cedula, String correo, String telefono, String usuario, String pass, String especialidad, String codigo, double salario,Date fecha) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.correo = correo;
        this.telefono = telefono;
        this.fecha=fecha;
        this.usuario = usuario;
        this.pass = pass;
        this.especialidad = especialidad;
        this.codigo = codigo;
        this.salario = salario;
    }
    
        public Medicos(Object[] obj){
            this.nombre=(String) obj[1];
            this.cedula=(String) obj[0];
            this.correo=(String) obj[3];
            this.telefono=(String) obj[5];
            this.fecha=(Date) obj[2];
            this.usuario=(String) obj[8];
            this.pass=(String) obj[9];
            this.especialidad=(String) obj[6];
            this.codigo=(String) obj[4];
            this.salario=(double) obj[7];
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
    return new Object[]{this.cedula,this.nombre,this.fecha,this.correo,this.codigo,this.telefono,this.especialidad,this.salario,this.usuario,this.pass};
    }
}
