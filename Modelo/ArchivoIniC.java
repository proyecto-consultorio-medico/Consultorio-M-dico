/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kille
 */
public class ArchivoIniC {
    private String nombre;
    private String ruta;
    private String tipo;
    private File archivo;
    private BufferedReader lector;
    private BufferedWriter escritor;
    private ArchivosIniL inileer;
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
   
       public ArchivoIniC(){
         this.nombre="Configuracion";
         this.ruta=System.getProperty("user.dir");
         this.tipo=".ini";
          this.abrirIni();
       }
       public ArchivoIniC(String nombre) {
       this.nombre=nombre;
       this.ruta=System.getProperty("user.dir");
       this.tipo=".ini";
        this.abrirIni();
        
    }
   
       public void abrirIni(){
        try {
            archivo= new File(ruta+"\\"+nombre+tipo);
        if (!existe()) {
            this.crear();
            lector = new BufferedReader(new FileReader(archivo));
            escritor= new BufferedWriter(new FileWriter(archivo,true));
            escribir("[Configuracion]");
            escribir("BD=");
            escribir("IP=");
            escribir("Usuario=");
            escribir("Pass=");
            this.guardar();
        }
          lector = new BufferedReader(new FileReader(archivo));
            escritor= new BufferedWriter(new FileWriter(archivo,true));   
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

   public void cerrar(){
        try {
            escritor.close();
            lector.close();
        } catch (IOException ex) {
            Logger.getLogger(ArchivoIniC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void crear(){
        try {
            archivo.createNewFile();
           
        } catch (IOException ex) {
            Logger.getLogger(ArchivoIniC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean existe(){
        return archivo.exists();
    }
    
public void guardar(){
        try {
            escritor.flush();
        } catch (IOException ex) {
            Logger.getLogger(ArchivoIniC.class.getName()).log(Level.SEVERE, null, ex);
        }
}

public String leer(){
        try {
            return lector.readLine();
        } catch (IOException ex) {
            Logger.getLogger(ArchivoIniC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
}

public void escribir(String string){
        try {
            escritor.write(string);
              escritor.newLine();
        } catch (IOException ex) {
            Logger.getLogger(ArchivoIniC.class.getName()).log(Level.SEVERE, null, ex);
        }
}

public void borrar(){
    this.cerrar();
    archivo.delete();
}

//public void guardarComo(String nombre,String ruta,String tipo,String lectura,int tamanio,String color,String color2,String fuente){
//   Archivo archivo= new Archivo(nombre,ruta,tipo);
//   archivo.limpiar();
//   archivo.escribir("<Caracteristicas>");
//   archivo.escribir("<Tamanio>"+tamanio+"</Tamanio>");
//   archivo.escribir("<Colorletra>"+color+"</Colorletra>");
//   archivo.escribir("<ColorFondo>"+color2+"</ColorFondo>");
//   archivo.escribir("<Fuente>"+fuente+"</Fuente>");
//   archivo.escribir("<Texto>"+lectura+"</Texto>");
//   archivo.escribir("</Caracteristicas>");
//   archivo.guardar();
//}

public void cambiarNombre(String nombre){
File nuevoArchivo=new File(nombre);
this.archivo.renameTo(nuevoArchivo);
}
public void limpiar(){
        try {
            escritor= new BufferedWriter(new FileWriter(archivo,false));
        } catch (IOException ex) {
            Logger.getLogger(ArchivoIniC.class.getName()).log(Level.SEVERE, null, ex);
        }
}
}
