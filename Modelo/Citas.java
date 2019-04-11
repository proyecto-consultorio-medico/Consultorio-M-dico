/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author kille
 */
public class Citas {
    private String hora;
    private String fecha;
    private int ID;
    private Pacientes paciente;
    private Medicos medico;

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

    public Citas(String hora, String fecha) {
        this.hora = hora;
        this.fecha = fecha;
        this.paciente = null;
        this.medico = null;
    }
    
    
}
