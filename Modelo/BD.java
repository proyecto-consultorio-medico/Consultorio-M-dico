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
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Jose,Marco,Yuliana,Elver
 */
public class BD{
    private static Connection conexion;
    private PreparedStatement sentencia;
    private ResultSet datos;
    private static String ip;
    private static String usuario;
    private static String pass;
    private static String bd;
    private ArchivosIniL ini;
    private ArchivoIniC iniCrear;
    protected String mesaje;

    public String getMesaje() {
        return mesaje;
    }

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
    
    private String conectar(){
         
        if (this.conexion==null) {
             try {
            ini = new ArchivosIniL();
            ini.leerArchivo("C:\\Users\\kille\\Documents\\NetBeansProjects\\Consultorio Medico\\Configuracion.ini");
            Class.forName("com.mysql.jdbc.Driver");
            this.conexion= DriverManager.getConnection("jdbc:mysql://"+ini.getProperties().getProperty("IP","default value")+
                    "/"+ini.getProperties().getProperty("BD","default value")+"?useServerPrepStmts=true",
                    ini.getProperties().getProperty("Usuario","default value"),
                    ini.getProperties().getProperty("Pass","default value"));
            return "Conecto";
        } catch (ClassNotFoundException ex) {
           System.out.println("Driver no cargado");
            return "Fallo";
        } catch (SQLException ex) {
             System.out.println("Error al conectar");
             return "Fallo";
          
        }
        }
       return "Existe";
    }
    
    public boolean setSentencia(String sql){
        try {
            this.sentencia=this.conexion.prepareStatement(sql);
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getErrorCode()+" "+" "+ ex.getMessage());
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
                   JOptionPane.showMessageDialog(null, ex.getErrorCode()+" "+" "+ ex.getMessage());
                            return false;
                }                    
            }
            if (parametro[i] instanceof java.lang.Integer) {
                try {
                    this.sentencia.setInt(i+1, Integer.parseInt(parametro[i].toString()));
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex.getErrorCode()+" "+" "+ ex.getMessage());
                            return false;
                }      
   
        }
            if (parametro[i] instanceof java.lang.Double) {
                try {
                    this.sentencia.setDouble(i+1, Double.parseDouble(parametro[i].toString()));
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex.getErrorCode()+" "+" "+ ex.getMessage());
                            return false;
                }     
    }   
            if (parametro[i] instanceof java.util.Date) {
                try {
                    this.sentencia.setDate(i+1, (java.sql.Date) parametro[i]);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex.getErrorCode()+" "+" "+ ex.getMessage());
                            return false;
                }     
    }   
        
    }
   return true;
    }

    
     
       public Object[] getObject(){
        try {
            if (this.datos.next()) {
                ArrayList<Object> obj= new ArrayList();
                for (int i = 1; i <=this.datos.getMetaData().getColumnCount(); i++) {
                    obj.add(this.datos.getObject(i));
                }
                return obj.toArray();
            }
        } catch (SQLException ex) {
            Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
  
     public boolean ejectuar(Object [] parametro){
         this.setParametros(parametro);
         return this.ejectuar();
    }
      public boolean ejectuar(){
        try {
            if (this.sentencia.execute()) {
                this.datos=this.sentencia.getResultSet();
            }
            return true;
        } catch (SQLException ex) {
          JOptionPane.showMessageDialog(null, ex.getErrorCode()+" "+" "+ ex.getMessage());
        }
        return false;
   }
      
      public String comprobar(){
            if (this.conectar()=="Existe") {
                return "Encendido";
            }     
         return "Apagado";
      }
      
     public boolean apagarservidor(){
        try {
            conexion.close();
            this.conexion=null;
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getErrorCode()+" "+" "+ ex.getMessage());
        }
        return false;
      }
      
     public Boolean encender(){
        this.iniCrear = new ArchivoIniC();
         this.iniCrear.limpiar();
         this.iniCrear.escribir("[Configuracion]");
          this.iniCrear.escribir("BD="+this.bd);
           this.iniCrear.escribir("IP="+this.ip);
            this.iniCrear.escribir("Usuario="+this.usuario);
             this.iniCrear.escribir("Pass="+this.pass);
             this.iniCrear.guardar();
             this.iniCrear.cerrar();
             this.conexion=null;
             if (this.conectar()=="Conecto") {
             return true;
         }
             return false;
     }
     
    
}
