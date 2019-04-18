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
import javax.swing.table.DefaultTableModel;

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
     bd.ejecutar(new Object[]{cita.getFecha(),cita.getHora(),cita.getPaciente().getCedula(),cita.getMedico().getCedula()});
        }
    }
    
    public boolean buscarMedico(){
        BD bd = new BD("SELECT `Cedula` FROM `medicos` WHERE Cedula=?"); 
        medico= new Medicos();
        medico.setCedula(frmcitas.getTxtCedulaMedic().getText());
        if (medico.contarDigitosCedu()==true) {
             bd.ejecutar(new Object[]{medico.getCedula()});
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
             bd.ejecutar(new Object[]{paciente.getCedula()});
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
     int cont;
     medico= new Medicos();
     medico.setCedula(frmcitas.getTxtCedulaMedic().getText());
 
      String fechas[]=frmcitas.getTxtFecha().getText().split("/");
          medico.setDia(Integer.parseInt(fechas[0]));
          medico.setMes(Integer.parseInt(fechas[1]));
          medico.setAño(Integer.parseInt(fechas[2]));
          medico.validarFecha(medico.getAño(), medico.getMes(), medico.getDia());
        BD bd= new BD("Select count(*) FROM citas join medicos on citas.Medico = medicos.cedula "
             + "where medicos.cedula=?"+ " and " + "citas.fecha=?");
           bd.ejecutar(new Object[]{this.medico.getCedula(),this.medico.getFecha()});
     obj=bd.getObject();
   cont = Integer.parseInt(obj[0].toString());
     if(cont>=4){
         System.out.println("Cantidad máxima de citas alcanzada!");
        
     }else{
     this.guardarCita();
     }
}

     public void agregarDatosMedico(){
      BD bd = new BD("SELECT NombreCompleto,FechaDeNacimiento,Especialidad FROM `medicos` WHERE Cedula=?"); 
        medico= new Medicos();
        medico.setCedula(frmcitas.getTxtCedulaMedic().getText());
           bd.ejecutar(new Object[]{medico.getCedula()});
           DefaultTableModel modelo = (DefaultTableModel) frmcitas.getTablamedicos().getModel();
            modelo.addRow(bd.getObject());
     }
     
     public void agregarDatosPaciente(){
      BD bd = new BD("SELECT Nombre,Fecha FROM `pacientes` WHERE Cedula=?"); 
        paciente= new Pacientes();
        paciente.setCedula(frmcitas.getTxtCedulaPaciente().getText());
           bd.ejecutar(new Object[]{paciente.getCedula()});
           DefaultTableModel modelo = (DefaultTableModel) frmcitas.getTablaPaciente().getModel();
            modelo.addRow(bd.getObject());
     }
}
