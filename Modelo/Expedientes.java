/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;



/**
 *
 * @author Jose,Marco,Yuliana,Elver
 */

public class Expedientes {

    private String nombrepaciente, nombredoctor, horacita;
    private int cedulapaciente, ceduladoctor, idcita, fechadenacimiento;

    public String getNombrepaciente() {
        return nombrepaciente;
    }

    public void setNombrepaciente(String nombrepaciente) {
        this.nombrepaciente = nombrepaciente;
    }

    public String getNombredoctor() {
        return nombredoctor;
    }

    public void setNombredoctor(String nombredoctor) {
        this.nombredoctor = nombredoctor;
    }

    public String getHoracita() {
        return horacita;
    }

    public void setHoracita(String horacita) {
        this.horacita = horacita;
    }

    public int getCedulapaciente() {
        return cedulapaciente;
    }

    public void setCedulapaciente(int cedulapaciente) {
        this.cedulapaciente = cedulapaciente;
    }

    public int getCeduladoctor() {
        return ceduladoctor;
    }

    public void setCeduladoctor(int ceduladoctor) {
        this.ceduladoctor = ceduladoctor;
    }

    public int getIdcita() {
        return idcita;
    }

    public void setIdcita(int idcita) {
        this.idcita = idcita;
    }

    public int getFechadenacimiento() {
        return fechadenacimiento;
    }

    public void setFechadenacimiento(int fechadenacimiento) {
        this.fechadenacimiento = fechadenacimiento;
    }
    private static String obtenerValor(String tag,Element Persona){
        Node Valor= Persona.getElementsByTagName(tag).item(0).getFirstChild();
    return Valor.getNodeValue();
   }
    

    public void obtenerExpedientes() {
        ArrayList<Expedientes> listaExpedientes= new ArrayList<Expedientes>();
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
           DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = (Document) docBuilder.parse(new File("xmlsrc/Expedientes.xml"));
            doc.getDocumentElement().normalize();
            NodeList nodosExpedientes= doc.getElementsByTagName("pacientes");
            for (int i = 0; i <nodosExpedientes.getLength() ; i++) {
                Node Expedientes = nodosExpedientes.item(i);
                if (Expedientes.getNodeType()== Node.ELEMENT_NODE) {
                    Element Elemento = (Element) Expedientes;
                    Expedientes expediente= new Expedientes();
                    expediente.setNombrepaciente(obtenerValor("nombre", Elemento));
                    expediente.setCedulapaciente(Integer.parseInt(obtenerValor("cedula paciente", Elemento)));
                    expediente.setFechadenacimiento(Integer.parseInt(obtenerValor("fecha de nacimiento", Elemento)));
                  expediente.setNombredoctor(obtenerValor("nombre doctor", Elemento));
                    expediente.setCeduladoctor(Integer.parseInt(obtenerValor("cedula doctor", Elemento)));
                    expediente.setHoracita(obtenerValor("hora cita", Elemento));
                    expediente.setIdcita(Integer.parseInt(obtenerValor("id cita", Elemento)));
                  listaExpedientes.add(expediente);
                }
            }
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Expedientes.class.getName()).log(Level.SEVERE, null, ex);

        } catch (SAXException ex) {
            Logger.getLogger(Expedientes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Expedientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

