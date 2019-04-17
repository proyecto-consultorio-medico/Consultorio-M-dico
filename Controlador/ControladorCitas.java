/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.BD;
import Modelo.Medicos;
import Modelo.MoCitas;
import Modelo.Pacientes;
import Vista.Citas;

/**
 *
 * @author Jose,Marco,Yuliana,Elver
 */

public class ControladorCitas {
    private Medicos medico;
    private Pacientes paciente;
    private MoCitas cita;
    private Citas frmcitas;
    private Object[] obj;

    public ControladorCitas(Citas frmcitas) {
        this.frmcitas = frmcitas;
        medico=null;
        paciente=null;
    }
    
    public void guardarCita(){
     String fechas[]=frmcitas.getTxtFecha().getText().split("/");
     cita= new MoCitas(0,(String) frmcitas.getHoras().getSelectedItem(),paciente,medico,Integer.parseInt(fechas[2]),Integer.parseInt(fechas[1]),Integer.parseInt(fechas[0]));
     cita.getPaciente().setCedula(frmcitas.getTxtCedulaPaciente().getText());
     cita.getMedico().setCedula(frmcitas.getTxtCedulaMedic().getText());
        if (buscarMedico()==true && buscarPaciente()==true) {
            cita.validarFecha(cita.getAño(), cita.getMes(), cita.getDia());
     BD bd= new BD("INSERT INTO `citas` VALUES (null,?,?,?,?)");
     bd.ejectuar(new Object[]{cita.getFecha(),cita.getHora(),cita.getPaciente().getCedula(),cita.getMedico().getCedula()});
        }
     
    }
    
    public boolean buscarMedico(){
        BD bd = new BD("SELECT `Cedula` FROM `medicos` WHERE Cedula=?"); 
        medico= new Medicos();
        medico.setCedula(frmcitas.getTxtCedulaMedic().getText());
        if (medico.contarDigitosCedu()==true) {
             bd.ejectuar(new Object[]{medico.getCedula()});
              obj=bd.getObject();
        System.out.println(obj);
        System.out.println(this.medico.getCedula());
        if (obj==null) {
            return false;
        }else{
         if (obj[0].equals(this.medico.getCedula())) {
          return true;
         }
        }
        }
       return false;
    }
     public boolean buscarPaciente(){
        BD bd = new BD("SELECT `Cedula` FROM `pacientes` WHERE Cedula=?"); 
        paciente= new Pacientes();
        paciente.setCedula(frmcitas.getTxtCedulaPaciente().getText());
        if (paciente.contarDigitosCedu()==true) {
             bd.ejectuar(new Object[]{paciente.getCedula()});
              obj=bd.getObject();
        System.out.println(obj);
        System.out.println(this.paciente.getCedula());
        if (obj==null) {
            return false;
        }else{
         if (obj[0].equals(this.paciente.getCedula())) {
          return true;
         }
        }
        }
       return false;
    }
     public void limitarCitas(){
   
   
}
}
