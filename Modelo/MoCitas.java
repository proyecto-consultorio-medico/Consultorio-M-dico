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
    private int hora;
    private Date fecha;
    private int ID;
    private Pacientes paciente;
    private Medicos medico;

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
   
 
    public int getHora() {
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

    public void setID(int ID) {
        this.ID = ID;
    }
  
    public MoCitas() {
    }

    public void setHora(int hora) {
        this.hora = hora;
    }


    public void setMedico(Medicos medico) {
        this.medico = medico;
    }
    

 

    public MoCitas(int ID,int hora,Pacientes paciente,Medicos medico,Date fecha) {
        this.ID=ID;
        this.hora=hora;
        this.fecha=fecha;
        this.paciente=paciente;
        this.medico=medico;
    }
    
    public void aleatorio(){
        int id2= (int)(Math.random()+60);
        this.ID=id2;
    }
}
