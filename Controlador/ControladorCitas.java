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
import Modelo.Secretaria;
import Vista.Citas;
import Vista.ExpedienteSensillo;
import Vista.FrmSesion;
import java.sql.Date;
import javax.swing.JOptionPane;
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

    public ControladorCitas() {
        this.frmcitas = frmcitas;
        medico=null;
        paciente=null;
    }
   
  public void guardarCita(Citas frmcitas){
      int cont;
      int cont2;
      int cont3;
       Date fecha = new Date(frmcitas.getTxtFecha().getDate().getTime());
     cita= new MoCitas(0, Integer.parseInt(frmcitas.getHoras().getSelectedItem().toString()),paciente,medico, fecha);
     cita.getPaciente().setCedula(frmcitas.getTxtCedulaPaciente().getText());
     cita.getMedico().setCedula(frmcitas.getTxtCedulaMedic().getText());
        if (buscarMedico(frmcitas)==true && buscarPaciente(frmcitas)==true) {
            BD bd2= new BD("Select count(*) from citas join pacientes on citas.Paciente= pacientes.cedula WHERE  Paciente=? and citas.fecha=? and citas.hora=?");
        bd2.ejecutar(new Object[]{this.paciente.getCedula(),cita.getFecha(),cita.getHora()});
        obj=bd2.getObject();
        cont = Integer.parseInt(obj[0].toString());
            if (cont>=1) {
                JOptionPane.showMessageDialog(null,"El paciente ya tiene una cita en ese dia y a esa misma hora");
            }else{
           BD bd3= new BD("Select count(*) from citas join pacientes on citas.Paciente=pacientes.cedula WHERE  Medico=? and citas.fecha=? and citas.hora=?");
            bd3.ejecutar(new Object[]{this.medico.getCedula(),this.cita.getFecha(),this.cita.getHora()});
            obj=bd3.getObject();
            cont2=Integer.parseInt(obj[0].toString());
                if (cont2>=1) {
                    JOptionPane.showMessageDialog(null,"El medico ya tiene una cita a esa hora");
                }else{
                BD bd4= new BD("Select count(*) from citas join pacientes on citas.Paciente= pacientes.cedula WHERE  Paciente=? and citas.fecha=? and citas.hora!=? and Medico=?");
             bd4.ejecutar(new Object[]{this.paciente.getCedula(),cita.getFecha(),cita.getHora(), this.medico.getCedula()});
             obj=bd4.getObject();
             cont3=Integer.parseInt(obj[0].toString());
                if(cont3>=1) {
                    JOptionPane.showMessageDialog(null,"El medico ya atendio ese paciente");
                }else{
                BD bd= new BD("INSERT INTO `citas` VALUES (null,?,?,?,?)");
                   bd.ejecutar(new Object[]{cita.getFecha(),cita.getHora(),cita.getPaciente().getCedula(),cita.getMedico().getCedula()});
                }
                }
            }
     
        }
    }
//       
    
    public boolean buscarMedico(Citas frmcitas){
        BD bd = new BD("SELECT `Cedula` FROM `medicos` WHERE Cedula=?"); 
        medico= new Medicos();
        medico.setCedula(frmcitas.getTxtCedulaMedic().getText());
        if (medico.contarDigitosCedu()==true) {
             bd.ejecutar(new Object[]{medico.getCedula()});
              obj=bd.getObject();
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
   
     public boolean buscarPaciente(Citas frmcitas){
        BD bd = new BD("SELECT `Cedula` FROM `pacientes` WHERE Cedula=?"); 
        paciente= new Pacientes();
        paciente.setCedula(frmcitas.getTxtCedulaPaciente().getText());
        if (paciente.contarDigitosCedu()==true) {
             bd.ejecutar(new Object[]{paciente.getCedula()});
              obj=bd.getObject();
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
     
     public boolean limitarCitas(Citas frmcitas){
     int cont;
  
     cita=new MoCitas();
     cita.setMedico(medico);
     cita.getMedico().setCedula(frmcitas.getTxtCedulaMedic().getText());
      Date fecha = new Date(frmcitas.getTxtFecha().getDate().getTime());
      cita.setFecha(fecha);
      cita.setHora(Integer.parseInt(frmcitas.getHoras().getSelectedItem().toString()));
     
      
        BD bd= new BD("Select count(*) FROM citas join medicos on citas.Medico = medicos.cedula where medicos.cedula=? and citas.fecha=? and citas.hora=?");
           bd.ejecutar(new Object[]{this.cita.getMedico().getCedula(),this.cita.getFecha(),this.cita.getHora()});
     obj=bd.getObject();
   cont = Integer.parseInt(obj[0].toString());
     if(cont>=4){
         System.out.println("Cantidad máxima de citas alcanzada!");
         return false;
        
     }else{
     this.guardarCita(frmcitas);
     return true;
     }
}

     public void agregarDatosMedico(Citas frmcitas){
      BD bd = new BD("SELECT NombreCompleto,Especialidad FROM `medicos` WHERE Cedula=?"); 
        medico= new Medicos();
        medico.setCedula(frmcitas.getTxtCedulaMedic().getText());
           bd.ejecutar(new Object[]{medico.getCedula()});
          obj=bd.getObject();
            frmcitas.setTxtNombreMedico((String) obj[0]);
            frmcitas.setTxtEspecialidad((String) obj[1]);
     }
     
     public void agregarDatosPaciente(Citas frmcitas){
      BD bd = new BD("SELECT Nombre,Fecha FROM `pacientes` WHERE Cedula=?"); 
        paciente= new Pacientes();
        paciente.setCedula(frmcitas.getTxtCedulaPaciente().getText());
           bd.ejecutar(new Object[]{paciente.getCedula()});
          obj=bd.getObject();
          frmcitas.setTxtNombrePaciente((String) obj[0]);
          frmcitas.setTxtFechaPaciente((Date) obj[1]);
     }
     
     public void buscarTodasLasCitas(Citas frmcitas){
         BD bd= new BD("SELECT citas.ID,citas.Fecha,citas.Hora,citas.Paciente,citas.Medico FROM `citas` JOIN medicos on citas.Medico = medicos.Cedula JOIN pacientes on citas.Paciente = pacientes.Cedula");
         bd.ejectuar();
         DefaultTableModel modelo = (DefaultTableModel) frmcitas.getTablaCitas().getModel();
                  modelo.setNumRows(0);
             do {
                 obj = bd.getObject();
            if (obj != null) {
                
                modelo.addRow(obj);
            }
        } while (obj!=null);
      
     }
     
     public void buscarCitaPorFecha(Citas frmcitas){
        BD bd=new BD("SELECT * FROM `citas` WHERE Fecha=?");
        Date fecha = new Date(frmcitas.getTxtFecha2().getDate().getTime());
        cita= new MoCitas();
        cita.setFecha(fecha);
        bd.ejecutar(new Object[]{cita.getFecha()});
         DefaultTableModel modelo = (DefaultTableModel) frmcitas.getTablaCitas().getModel();
         modelo.setNumRows(0);
       
       do {
                 obj = bd.getObject();
            if (obj != null) {
                
                modelo.addRow(obj);
            }
        } while (obj!=null);
       
                 

     }
     
     
     public void eliminar(Citas frmcitas){
     BD bd=new BD("Delete FROM citas where ID=?");
     cita=new MoCitas();
     cita.setID(frmcitas.getId());
     bd.ejecutar(new Object[]{cita.getID()});
     }
  
}
