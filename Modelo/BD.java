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
 * @author Jose,Marco,Yuliana,Elver
 */
public class BD{
    private static Connection conexion;
    public PreparedStatement sentencia;
    private ResultSet datos;
    private static String ip;
    private static String usuario;
    private static String pass;
    private static String bd;
    private ArchivosIniL ini;
    private ArchivoIniC iniCrear;

    public static String getBd() {
        return bd;
    }
    
    
    public static String getIp() {
        return ip;
    }

    public static String getUsuario() {
        return usuario;
    }

    public static String getPass() {
        return pass;
    }

    public BD(String ip, String usuario, String pass,String bd) {
        this.ip = ip;
        this.usuario = usuario;
        this.pass = pass;
        this.bd=bd;
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
            Class.forName("com.mysql.jdbc.Driver");
            this.conexion= DriverManager.getConnection("jdbc:mysql://"+ini.getProperties().getProperty("IP","default value")+
                    "/"+ini.getProperties().getProperty("BD","default value")+"?useServerPrepStmts=true",
                    ini.getProperties().getProperty("Usuario","default value"),
                    ini.getProperties().getProperty("Pass","default value"));
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
            if (parametro[i] instanceof java.lang.Double) {
                try {
                    this.sentencia.setDouble(i+1, Double.parseDouble(parametro[i].toString()));
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
    
     public boolean conectarBase(String ip,String usuario,String pass,String bd){
             iniCrear= new ArchivoIniC();
             iniCrear.limpiar();
             this.ip=ip;
             this.usuario=usuario;
             this.pass=pass;
             this.bd=bd;
             iniCrear.escribir("[Configuracion]");
              iniCrear.escribir("BD="+bd);
              iniCrear.escribir("IP="+ip);
               iniCrear.escribir("Usuario="+usuario);
                iniCrear.escribir("Pass="+pass);
                iniCrear.guardar();
                iniCrear.cerrar();
                return this.conectar();   
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
