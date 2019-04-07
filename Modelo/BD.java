package Modelo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kille
 */
public class BD{
    private static Connection conexion;
    private PreparedStatement sentencia;
    private ResultSet datos;
    private static String ip;
    private static String usuario;
    private static String pass;
    private ArchivosIniL ini;
    private ArchivoIniC iniCrear;
    

    public String getIp() {
        return ip;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getPass() {
        return pass;
    }

    public BD(String ip, String usuario, String pass) {
        this.ip = ip;
        this.usuario = usuario;
        this.pass = pass;
        this.conectar();
    }
    
    public BD(){
    this.conectar();
    }
    
    public BD(String sql){
    this.conectar();
    this.setSentencia(sql);
    }
    
    private boolean conectar(){
        if (this.conexion==null) {
             try {
            ini = new ArchivosIniL();
            ini.leerArchivo("C:\\Users\\kille\\Documents\\NetBeansProjects\\Consultorio Medico\\Configuracion.ini");
                 System.out.println(ini.getProperties().getProperty("IP","default value")+ini.getProperties().getProperty("Usuario","default value")+ini.getProperties().getProperty("Pass","default value"));
                 
            Class.forName("com.mysql.jdbc.Driver");
            this.conexion= DriverManager.getConnection("jdbc:mysql://"+ini.getProperties().getProperty("IP","default value")+"/java?useServerPrepStmts=true",ini.getProperties().getProperty("Usuario","default value"),ini.getProperties().getProperty("Pass","default value"));
            return true;
        } catch (ClassNotFoundException ex) {
           System.out.println("Driver no cargado");
           return false;
        } catch (SQLException ex) {
             System.out.println("Error al conectar");
             return false;
        }
        }
       return true;
    }
    
    public boolean setSentencia(String sql){
        try {
            this.sentencia=this.conexion.prepareStatement(sql);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            return false;
        }
    }
    public void ingresarCliente(){
    
    }
    
    public boolean setParametros(Object parametro[]){
        for (int i = 0; i < parametro.length; i++) {
            if (parametro[i] instanceof java.lang.String) {
                try {
                    this.sentencia.setString(i+1, parametro[i].toString());
                } catch (SQLException ex) {
                    Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
                            return false;
                }                    
            }
            if (parametro[i] instanceof java.lang.Integer) {
                try {
                    this.sentencia.setInt(i+1, Integer.parseInt(parametro[i].toString()));
                } catch (SQLException ex) {
                    Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
                            return false;
                }                
        }
    }
        return true;
    }
    
   public boolean ejectuar(){
        try {
            this.sentencia.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
   }
   
     public boolean ejectuar(Object [] parametro){
         this.setParametros(parametro);
         return this.ejectuar();
    }
    
     public void conectarBase(String ip,String usuario,String pass){
             iniCrear= new ArchivoIniC();
             iniCrear.limpiar();
             this.ip=ip;
             this.usuario=usuario;
             this.pass=pass;
             iniCrear.escribir("[Configuracion]");
              iniCrear.escribir("IP="+ip);
               iniCrear.escribir("Usuario="+usuario);
                iniCrear.escribir("Pass="+pass);
                iniCrear.guardar();
                iniCrear.cerrar();
                this.conectar();   
     }
     
      public boolean apagarservidor(){
        try {
            conexion.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
      }
      
      public String comprobar(){
            if (this.conectar()==false) {
                 return "Apagado";
            }     
        return "Encendido";
      }
      
       public boolean reiniciarBase(){
           if (this.conectar()==true) {
               try {
                   this.conexion.close();
                   iniCrear= new ArchivoIniC();
                   iniCrear.borrar();
                   return true;
               } catch (SQLException ex) {
                   Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
               }
           }else{
            return false;   
               
           }
    return false;
}
}
