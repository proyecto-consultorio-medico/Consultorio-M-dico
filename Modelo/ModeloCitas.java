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
public class ModeloCitas {
    private String hora;
    private String fecha;
    private int ID;
    private Pacientes paciente;
    private Medicos medico;
    private String Observaciones;

    public ModeloCitas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getObservaciones() {
        return Observaciones;
    }

    public void setObservaciones(String Observaciones) {
        this.Observaciones = Observaciones;
    }
    
    

    public String getHora() {
        return hora;
    }

    public String getFecha() {
        return fecha;
    }

    public int getID() {
        return ID;
    }

    public Pacientes getPaciente() {
        return paciente;
    }

    public Medicos getMedico() {
        return medico;
    }

   

    public ModeloCitas(String hora, String fecha) {
        this.hora = hora;
        this.fecha = fecha;
        this.paciente = null;
        this.medico = null;
    }
    
    
}
