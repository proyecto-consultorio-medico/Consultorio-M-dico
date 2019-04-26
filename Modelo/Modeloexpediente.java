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
public class Modeloexpediente {
private Date fecha;
private String hora;
private Medicos medico;
private Pacientes paciente;
private String descripcion;

public Modeloexpediente(Date fecha,String hora,Medicos medico, Pacientes paciente,String descripcion){
this.fecha=fecha;
this.hora=hora;
this.medico=medico;
this.paciente=paciente;
this.descripcion=descripcion;
}

    public void setHora(String hora) {
        this.hora = hora;
    }

    public void setMedico(Medicos medico) {
        this.medico = medico;
    }

    public void setPaciente(Pacientes paciente) {
        this.paciente = paciente;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Modeloexpediente() {
    }


    public Date getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public Medicos getMedico() {
        return medico;
    }

    public Pacientes getPaciente() {
        return paciente;
    }

    public String getDescripcion() {
        return descripcion;
    }
    


    
}
