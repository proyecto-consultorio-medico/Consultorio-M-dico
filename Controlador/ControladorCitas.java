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
   /**
    * guarda la cita en la base de datos
    * @param frmcitas 
    */
  public void guardarCita(Citas frmcitas){
      int cont;
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
                BD bd= new BD("INSERT INTO `citas` VALUES (null,?,?,?,?)");
                   bd.ejecutar(new Object[]{cita.getFecha(),cita.getHora(),cita.getPaciente().getCedula(),cita.getMedico().getCedula()});
                }
        }
    } 
    /**
     * busca el medico para saber si existe
     * @param frmcitas
     * @return retorna un estado booleano
     */
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
   /**
    * busca el paciente para saber si existe
    * @param frmcitas
    * @return retorna un estado booleano
    */
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
     /**
      * limita la citas a los medicos a 4 por fecha y hora
      * @param frmcitas
      * @return retorna un estado booleano
      */
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
       JOptionPane.showMessageDialog(null, "Cantidad máxima de citas alcanzada!");
         return false;
        
     }else{
     this.guardarCita(frmcitas);
     return true;
     }
}
/**
 * extra: agrega datos del medico para saber a quien le peretece la cedula que se busco
 * @param frmcitas 
 */
     public void agregarDatosMedico(Citas frmcitas){
      BD bd = new BD("SELECT NombreCompleto,Especialidad FROM `medicos` WHERE Cedula=?"); 
        medico= new Medicos();
        medico.setCedula(frmcitas.getTxtCedulaMedic().getText());
           bd.ejecutar(new Object[]{medico.getCedula()});
          obj=bd.getObject();
            frmcitas.setTxtNombreMedico((String) obj[0]);
            frmcitas.setTxtEspecialidad((String) obj[1]);
     }
     /**
      * extra:  agrega datos del paciente para saber a quien le peretece la cedula que se busco
      * @param frmcitas 
      */
     public void agregarDatosPaciente(Citas frmcitas){
      BD bd = new BD("SELECT Nombre,Fecha FROM `pacientes` WHERE Cedula=?"); 
        paciente= new Pacientes();
        paciente.setCedula(frmcitas.getTxtCedulaPaciente().getText());
           bd.ejecutar(new Object[]{paciente.getCedula()});
          obj=bd.getObject();
          frmcitas.setTxtNombrePaciente((String) obj[0]);
          frmcitas.setTxtFechaPaciente((Date) obj[1]);
     }
     /**
      * busca todas las citas en la base de datos
      * @param frmcitas 
      */
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
     /**
      * busca cita por fecha en la base de datos
      * @param frmcitas 
      */
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
     /**
      * elimina un cita en la base de datos
      * @param frmcitas 
      */
     public void eliminar(Citas frmcitas){
     BD bd=new BD("Delete FROM citas where ID=?");
     cita=new MoCitas();
     cita.setID(frmcitas.getId());
     bd.ejecutar(new Object[]{cita.getID()});
     }
  /**
   * actualiza una cita en la base de datos
   * @param frmcitas 
   */
     public void actualizarCita(Citas frmcitas){
     BD bd= new BD("UPDATE `citas` SET `Fecha`=?,`Hora`=?,`Paciente`=?,`Medico`=? WHERE ID=?");
     Date fecha = new Date(frmcitas.getTxtFecha().getDate().getTime());
     this.medico=new Medicos();
     this.paciente=new Pacientes();
     cita= new MoCitas(frmcitas.getId(), Integer.parseInt(frmcitas.getHoras().getSelectedItem().toString()), paciente, medico, fecha);
     cita.getMedico().setCedula(frmcitas.getTxtCedulaMedic().getText());
     cita.getPaciente().setCedula(frmcitas.getTxtCedulaPaciente().getText());
     bd.ejecutar(new Object[]{cita.getFecha(),cita.getHora(),cita.getPaciente().getCedula(),cita.getMedico().getCedula(),cita.getID()});
     }
}