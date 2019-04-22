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
public class MoCitas {
    private String hora;
    private Date fecha;
    private int ID;
    private Pacientes paciente;
    private Medicos medico;
    private int año;
    private int mes;
    private int dia;
 
    public String getHora() {
        return hora;
    }

    public Date getFecha() {
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

    public int getAño() {
        return año;
    }

    public int getMes() {
        return mes;
    }

    public int getDia() {
        return dia;
    }

    public MoCitas(int ID,String hora,Pacientes paciente,Medicos medico,Date fecha) {
        this.ID=ID;
        this.hora=hora;
        this.fecha=fecha;
        this.paciente=paciente;
        this.medico=medico;
    }
}
