package Modelo;


import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kille
 */
public class Archivo {
    private String nombre;
    private String ruta;
    private String tipo;
    private File archivo;
    private BufferedReader lector;
    protected BufferedWriter escritor;
    public  NodeList listaCaracteristicas;
    protected  NodeList textos;
    public  Node nodo ;
    private String rutacompleta;
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
    /**
     * 
     * @param nombre
     * @param ruta
     * @param tipo 
     */
    public Archivo(String nombre, String ruta, String tipo) {
        this.nombre = nombre;
        this.ruta = ruta;
        this.tipo = tipo;
        this.abrir();
    }
   
       public Archivo(String ruta) {
       
        this.ruta = ruta;
   
        this.abrirXML();
    }
   
       public void abrirXML(){
        try {
            archivo= new File(this.ruta);
        if (!existe()) {
            this.crear();
        }
             DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder construdocumenento = dbf.newDocumentBuilder();
            Document documento = construdocumenento.parse(archivo);
            documento.getDocumentElement().normalize();
            listaCaracteristicas = documento.getElementsByTagName("Cita");
           
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    public void abrir(){
        try {
            archivo= new File(this.ruta+"\\"+this.nombre+this.tipo);
        if (!existe()) {
            this.crear();
              lector = new BufferedReader(new FileReader(archivo));
            escritor= new BufferedWriter(new FileWriter(archivo,true));
       escribir("<Cita>");
       escribir("<FechaDeLaCita>"+"</FechaDeLaCita>");
       escribir("<HoraDeLaCita>"+"</HoraDeLaCita>");
       escribir("<CedulaMedico>"+"</CedulaMedico>");
       escribir("<NombreMedico>"+"</NombreMedico>");
       escribir("<Especialidad>"+"</Especialidad>");
       escribir("<CedulaPaciente>"+"</CedulaPaciente>");
       escribir("<NombrePaciente>"+"</NombrePaciente>");
       escribir("<FechaNacimientoPaciente>"+"</FechaNacimientoPaciente>");
       escribir("<Comentario>"+"</Comentario>");
       escribir("</Cita>");
            guardar();     
        }
         this.ruta=this.ruta+"\\"+this.nombre+this.tipo;
          abrirXML();
       
         
        }catch (IOException ex) {
            Logger.getLogger(Archivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   public void cerrar(){
        try {
            escritor.close();
            lector.close();
        } catch (IOException ex) {
            Logger.getLogger(Archivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void crear(){
        try {
            archivo.createNewFile();
           
        } catch (IOException ex) {
            Logger.getLogger(Archivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean existe(){
        return archivo.exists();
    }
    
public void guardar(){
        try {
            escritor.flush();
        } catch (IOException ex) {
            Logger.getLogger(Archivo.class.getName()).log(Level.SEVERE, null, ex);
        }
}

public String leer(){
        try {
            return lector.readLine();
        } catch (IOException ex) {
            Logger.getLogger(Archivo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
}

public void escribir(String string){
        try {
            escritor.write(string);
              escritor.newLine();
        } catch (IOException ex) {
            Logger.getLogger(Archivo.class.getName()).log(Level.SEVERE, null, ex);
        }
}

public void borrar(){
    this.cerrar();
    archivo.delete();
}

public void guardarComo(String nombre,String ruta,String tipo,String lectura,int tamanio,String color,String color2,String fuente){
   Archivo archivo= new Archivo(nombre,ruta,tipo);
   archivo.limpiar();
   archivo.escribir("<Caracteristicas>");
   archivo.escribir("<Tamanio>"+tamanio+"</Tamanio>");
   archivo.escribir("<Colorletra>"+color+"</Colorletra>");
   archivo.escribir("<ColorFondo>"+color2+"</ColorFondo>");
   archivo.escribir("<Fuente>"+fuente+"</Fuente>");
   archivo.escribir("<Texto>"+lectura+"</Texto>");
   archivo.escribir("</Caracteristicas>");
   archivo.guardar();
}

public void cambiarNombre(String nombre){
File nuevoArchivo=new File(nombre);
this.archivo.renameTo(nuevoArchivo);
}
public void limpiar(){
        try {
            escritor= new BufferedWriter(new FileWriter(archivo,false));
        } catch (IOException ex) {
            Logger.getLogger(Archivo.class.getName()).log(Level.SEVERE, null, ex);
        }
}
}
