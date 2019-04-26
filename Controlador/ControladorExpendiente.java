/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Archivo;
import Modelo.BD;
import Modelo.Medicos;
import Modelo.MoCitas;
import Modelo.Modeloexpediente;
import Modelo.Pacientes;
import Vista.ExpedienteSensillo;
import Vista.FrmSesion;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Jose,Marco,Yuliana,Elver
 */
public class ControladorExpendiente {
    private MoCitas citas;
    private Medicos medico;
    private Pacientes pacientes;
    private Modeloexpediente modelo;
    private  Archivo arch;
    private Object []obj;
    
    public ControladorExpendiente() {
        citas=null;
        medico=null;
        pacientes=null;
    }
    /**
     * busca datos del paciente para que se muestren
     * @param exp 
     */
   public void buscarpaciente(ExpedienteSensillo exp) {
        if (!"".equals(exp.getTxtCedulaPaciente().getText())) {
            this.pacientes = new Pacientes();
            this.pacientes.setCedula(exp.getTxtCedulaPaciente().getText());
            BD bd = new BD("SELECT Nombre,Fecha  FROM `pacientes` WHERE Cedula=?");
            bd.ejecutar(new Object[]{this.pacientes.getCedula()});
            Object[]obj=bd.getObject();
            exp.setTxtNombrePaciente(obj[0].toString());
            SimpleDateFormat fechas=new SimpleDateFormat("dd/MM/yyyy");
            exp.setTxtFechaPaciente(fechas.format(obj[1]));
        }
   }
   /**
    * carga datos del medico cuando inicie sesion al expediente 
    * @param frmsesion
    * @param exp 
    */
   public void cargarMedico(FrmSesion frmsesion,ExpedienteSensillo exp){
       BD bd = new BD("SELECT `NombreCompleto`,`Cedula`,`Especialidad` FROM `medicos` WHERE Usuario=?" );
       this.medico=new Medicos();
          this.medico.setUsuario(frmsesion.getTxtUsuario().getText());
          bd.ejecutar(new Object[]{this.medico.getUsuario()});
         Object[] obj=bd.getObject();
          exp.setTxtMedico(obj[0].toString());
          exp.setTxtEspecialidad(obj[2].toString());
          exp.setTxtCedulaMedic(obj[1].toString());
       }
   /**
    * busca las citas del dia, del medico que esta atendido mediante la fecha 
    * @param frmsesion
    * @param exp 
    */
   public void buscarFecha(FrmSesion frmsesion,ExpedienteSensillo exp){
         BD bd=new BD("SELECT Cedula FROM `medicos` WHERE Usuario=?");
         this.medico=new Medicos();
            this.medico.setUsuario(frmsesion.getTxtUsuario().getText());
         bd.ejecutar(new Object[]{this.medico.getUsuario()});
            this.obj = bd.getObject();
         BD bd2=new BD("SELECT ID,Fecha,Hora,Paciente FROM `citas`  WHERE Medico=? and Fecha=? order by Hora");
                   Date fecha = new Date(frmsesion.getTxtFechaAtencion().getDate().getTime());
                   this.citas=new MoCitas();
              this.citas.setFecha(fecha);
         bd2.ejecutar(new Object[]{this.obj[0],this.citas.getFecha()});
         Object obj2[];
         DefaultTableModel modelo = (DefaultTableModel) exp.getTablaCitasDelDia().getModel();
         modelo.setNumRows(0);
       do {
            obj2 =bd2.getObject();
            if (obj2 != null) {
                modelo.addRow(obj2);
            }
        } while (obj2!=null);
     }
  /**
   * verifica la existencia de un expendiente 
   * @param exp
   * @return 
   */
   public boolean verificarExistenciaDeExpediente(ExpedienteSensillo exp){
        try {
            int cont;
            BD bd= new BD("SELECT COUNT(*) FROM `expediente` WHERE Fecha=? AND Paciente=? AND Hora=?");
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date utilDate = formato.parse(exp.getTxtFechaAtencion().getText());
            java.sql.Date fecha = new java.sql.Date(utilDate.getTime());
             this.pacientes=new Pacientes();
             this.modelo=new Modeloexpediente();
             this.modelo.setFecha(fecha);
             this.modelo.setPaciente(pacientes);
             this.modelo.getPaciente().setCedula(exp.getTxtCedulaPaciente().getText());
             this.modelo.setHora(exp.getTxtHora().getText());
             bd.ejecutar(new Object[]{this.modelo.getFecha(),this.modelo.getPaciente().getCedula(),this.modelo.getHora()});
             this.obj=bd.getObject();
             cont=Integer.parseInt(this.obj[0].toString());
             if (cont>=1) {
                return false;
            }else{
                 exportarDatos(exp);
                 exportarXML(exp);
                 return true;
             }
             
        } catch (ParseException ex) {
            Logger.getLogger(ControladorExpendiente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
   }
  /**
   * guarda el expediente en la base de datos
   * @param exp 
   */ 
   public void exportarDatos(ExpedienteSensillo exp){
        try {
            BD bd= new BD("INSERT INTO expediente VALUES (?,?,?,?,?)");
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
           java.util.Date utilDate = formato.parse(exp.getTxtFechaAtencion().getText());
           java.sql.Date fecha = new java.sql.Date(utilDate.getTime());
            this.medico=new Medicos();
            this.pacientes=new Pacientes();
            this.modelo = new Modeloexpediente(fecha, exp.getTxtHora().getText(), medico, pacientes,exp.getTxtComentario().getText());
            this.modelo.getMedico().setCedula(exp.getTxtCedulaMedic().getText());
            this.modelo.getPaciente().setCedula(exp.getTxtCedulaPaciente().getText());
            bd.ejecutar(new Object[]{this.modelo.getFecha(),this.modelo.getHora(),this.modelo.getMedico().getCedula(),this.modelo.getPaciente().getCedula(),this.modelo.getDescripcion()});
        } catch (ParseException ex) {
            Logger.getLogger(ControladorExpendiente.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   /**
    * guarda y acutaliza el expediente en un archivo XML
    * @param exp 
    */
   public void exportarXML(ExpedienteSensillo exp){
   BD bd= new BD("Select expediente.Fecha,Hora,Medico,medicos.NombreCompleto,medicos.Especialidad,Paciente,pacientes.Nombre,pacientes.Fecha,Descripcion FROM `expediente` JOIN pacientes on expediente.Paciente=pacientes.Cedula JOIN medicos ON expediente.Medico=medicos.Cedula WHERE Paciente=?");
   this.modelo= new Modeloexpediente();
   this.pacientes=new Pacientes();
   this.modelo.setPaciente(this.pacientes);
   this.modelo.getPaciente().setCedula(exp.getTxtCedulaPaciente().getText());
   bd.ejecutar(new Object[]{this.modelo.getPaciente().getCedula()});
                 String[]fecha=exp.getTxtFechaAtencion().getText().split("/");
   this.arch = new Archivo("C:\\Users\\kille\\Documents\\NetBeansProjects\\Consultorio Medico\\Citas"+"\\"+this.modelo.getPaciente().getCedula()+"["+fecha[0]+"-"+fecha[1]+"-"+fecha[2]+"]"+".xml");
    this.arch.limpiar(); 
this.arch.escribir("<Expediente>");
  do {
     this.obj=bd.getObject();
           if (this.obj!=null) {
       this.arch.escribir("<Cita>");
       this.arch.escribir("<FechaDeLaCita>"+this.obj[0].toString()+"</FechaDeLaCita>");
       this.arch.escribir("<HoraDeLaCita>"+this.obj[1].toString()+"</HoraDeLaCita>");
       this.arch.escribir("<CedulaMedico>"+this.obj[2].toString()+"</CedulaMedico>");
       this.arch.escribir("<NombreMedico>"+this.obj[3].toString()+"</NombreMedico>");
       this.arch.escribir("<Especialidad>"+this.obj[4].toString()+"</Especialidad>");
       this.arch.escribir("<CedulaPaciente>"+this.obj[5].toString()+"</CedulaPaciente>");
       this.arch.escribir("<NombrePaciente>"+this.obj[6].toString()+"</NombrePaciente>");
       this.arch.escribir("<FechaNacimientoPaciente>"+this.obj[7].toString()+"</FechaNacimientoPaciente>");
       this.arch.escribir("<Comentario>"+this.obj[8].toString()+"</Comentario>");
       this.arch.escribir("</Cita>");
    
           }
       } while (this.obj!=null);
  this.arch.escribir("</Expediente>");
      this.arch.guardar(); 
   }
  /**
   * busca las observaciones que ha tenido anteriormente el paciente que esta atendiendo
   * @param exp 
   */ 
   public void buscarObservaciones(ExpedienteSensillo exp){
       BD bd= new BD("SELECT Fecha,Descripcion FROM `expediente` WHERE Paciente=?");
       this.modelo=new Modeloexpediente();
       this.pacientes=new Pacientes();
       this.modelo.setPaciente(this.pacientes);
       this.modelo.getPaciente().setCedula(exp.getTxtCedulaPaciente().getText());
       bd.ejecutar(new Object[]{this.modelo.getPaciente().getCedula()});
          DefaultTableModel modelo = (DefaultTableModel) exp.getTablaObser().getModel();
         modelo.setNumRows(0);
         do {
              this.obj=bd.getObject();
             if (obj!=null) {
              modelo.addRow(this.obj);
             }
       } while (obj!=null);
   }
   /**
    * actualiza el expendiente en la base de datos
    * @param exp 
    */
   public void acutalizarExpediente(ExpedienteSensillo exp){
        try {
            BD bd= new BD("UPDATE `expediente` SET `Descripcion`=?  WHERE Medico=? and Fecha=? AND Hora=? AND Paciente=?");
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date utilDate = formato.parse(exp.getTxtFechaAtencion().getText());
            java.sql.Date fecha = new java.sql.Date(utilDate.getTime());
            this.medico=new Medicos();
            this.pacientes=new Pacientes();
            this.modelo=new Modeloexpediente(fecha, exp.getTxtHora().getText(), this.medico, this.pacientes, exp.getTxtComentario().getText());
            this.modelo.getMedico().setCedula(exp.getTxtCedulaMedic().getText());
            this.modelo.getPaciente().setCedula(exp.getTxtCedulaPaciente().getText());
            bd.ejecutar(new Object[]{this.modelo.getDescripcion(),this.modelo.getMedico().getCedula(),this.modelo.getFecha(),this.modelo.getHora(),this.modelo.getPaciente().getCedula()});
                    } catch (ParseException ex) {
            Logger.getLogger(ControladorExpendiente.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
}
