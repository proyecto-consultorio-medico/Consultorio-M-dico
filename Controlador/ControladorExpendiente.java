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
import Modelo.Secretaria;
import Vista.ExpedienteSensillo;
import Vista.FrmSesion;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import org.w3c.dom.Element;
import org.w3c.dom.Node;


/**
 *
 * @author kille
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
    
   public void buscarpaciente(ExpedienteSensillo exp) {
        if (!"".equals(exp.getTxtCedulaPaciente().getText())) {
            pacientes = new Pacientes();
            pacientes.setCedula(exp.getTxtCedulaPaciente().getText());
            BD bd = new BD("SELECT Nombre,Fecha  FROM `pacientes` WHERE Cedula=?");
            bd.ejecutar(new Object[]{pacientes.getCedula()});
            Object[]obj=bd.getObject();
            exp.setTxtNombrePaciente(obj[0].toString());
            SimpleDateFormat fechas=new SimpleDateFormat("dd/MM/yyyy");
            exp.setTxtFechaPaciente(fechas.format(obj[1]));
        }
   }
   
   public void cargarMedico(FrmSesion frmsesion,ExpedienteSensillo exp){
       BD bd = new BD("SELECT `NombreCompleto`,`Cedula`,`Especialidad` FROM `medicos` WHERE Usuario=?" );
       this.medico=new Medicos();
          this.medico.setUsuario(frmsesion.getTxtUsuario().getText());
          bd.ejecutar(new Object[]{medico.getUsuario()});
         Object[] obj=bd.getObject();
          exp.setTxtMedico(obj[0].toString());
          exp.setTxtEspecialidad(obj[2].toString());
          exp.setTxtCedulaMedic(obj[1].toString());
       }
   
   public void buscarFecha(FrmSesion frmsesion,ExpedienteSensillo exp){
         BD bd=new BD("SELECT Cedula FROM `medicos` WHERE Usuario=?");
         medico=new Medicos();
            medico.setUsuario(frmsesion.getTxtUsuario().getText());
         bd.ejecutar(new Object[]{medico.getUsuario()});
            Object[] obj = bd.getObject();
         BD bd2=new BD("SELECT ID,Fecha,Hora,Paciente FROM `citas`  WHERE Medico=? and Fecha=? order by Hora");
                   Date fecha = new Date(frmsesion.getTxtFechaAtencion().getDate().getTime());
                   this.citas=new MoCitas();
              this.citas.setFecha(fecha);
         bd2.ejecutar(new Object[]{obj[0],citas.getFecha()});
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
   public boolean verificarExistenciaDeExpediente(ExpedienteSensillo exp){
        try {
            int cont;
            BD bd= new BD("SELECT COUNT(*) FROM `expediente` WHERE Fecha=? AND Paciente=? AND Hora=?");
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date utilDate = formato.parse(exp.getTxtFechaAtencion().getText());
            java.sql.Date fecha = new java.sql.Date(utilDate.getTime());
             pacientes=new Pacientes();
             modelo=new Modeloexpediente();
             modelo.setFecha(fecha);
             modelo.setPaciente(pacientes);
             modelo.getPaciente().setCedula(exp.getTxtCedulaPaciente().getText());
             modelo.setHora(exp.getTxtHora().getText());
             bd.ejecutar(new Object[]{modelo.getFecha(),modelo.getPaciente().getCedula(),modelo.getHora()});
             obj=bd.getObject();
             cont=Integer.parseInt(obj[0].toString());
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
   
   public void exportarDatos(ExpedienteSensillo exp){
        try {
            BD bd= new BD("INSERT INTO expediente VALUES (?,?,?,?,?)");
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
           java.util.Date utilDate = formato.parse(exp.getTxtFechaAtencion().getText());
           java.sql.Date fecha = new java.sql.Date(utilDate.getTime());
            System.out.println(fecha);
            medico=new Medicos();
            pacientes=new Pacientes();
            modelo = new Modeloexpediente(fecha, exp.getTxtHora().getText(), medico, pacientes,exp.getTxtComentario().getText());
            System.out.println(exp.getTxtCedulaMedic().getText());
            modelo.getMedico().setCedula(exp.getTxtCedulaMedic().getText());
            System.out.println(modelo.getMedico().getCedula());
            modelo.getPaciente().setCedula(exp.getTxtCedulaPaciente().getText());
            bd.ejecutar(new Object[]{modelo.getFecha(),modelo.getHora(),modelo.getMedico().getCedula(),modelo.getPaciente().getCedula(),modelo.getDescripcion()});
        } catch (ParseException ex) {
            Logger.getLogger(ControladorExpendiente.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
   public void exportarXML(ExpedienteSensillo exp){
   BD bd= new BD("Select expediente.Fecha,Hora,Medico,medicos.NombreCompleto,medicos.Especialidad,Paciente,pacientes.Nombre,pacientes.Fecha,Descripcion FROM `expediente` JOIN pacientes on expediente.Paciente=pacientes.Cedula JOIN medicos ON expediente.Medico=medicos.Cedula WHERE Paciente=?");
   this.modelo= new Modeloexpediente();
   this.pacientes=new Pacientes();
   this.modelo.setPaciente(this.pacientes);
   this.modelo.getPaciente().setCedula(exp.getTxtCedulaPaciente().getText());
   bd.ejecutar(new Object[]{this.modelo.getPaciente().getCedula()});
                 String[]fecha=exp.getTxtFechaAtencion().getText().split("/");
   arch = new Archivo("C:\\Users\\kille\\Documents\\NetBeansProjects\\Consultorio Medico\\Citas"+"\\"+this.modelo.getPaciente().getCedula()+"["+fecha[0]+"-"+fecha[1]+"-"+fecha[2]+"]"+".xml");
    arch.limpiar(); 
arch.escribir("<Expediente>");
  do {
     obj=bd.getObject();
           if (obj!=null) {
         arch.escribir("<Cita>");
       arch.escribir("<FechaDeLaCita>"+obj[0].toString()+"</FechaDeLaCita>");
       arch.escribir("<HoraDeLaCita>"+obj[1].toString()+"</HoraDeLaCita>");
       arch.escribir("<CedulaMedico>"+obj[2].toString()+"</CedulaMedico>");
       arch.escribir("<NombreMedico>"+obj[3].toString()+"</NombreMedico>");
       arch.escribir("<Especialidad>"+obj[4].toString()+"</Especialidad>");
       arch.escribir("<CedulaPaciente>"+obj[5].toString()+"</CedulaPaciente>");
       arch.escribir("<NombrePaciente>"+obj[6].toString()+"</NombrePaciente>");
       arch.escribir("<FechaNacimientoPaciente>"+obj[7].toString()+"</FechaNacimientoPaciente>");
       arch.escribir("<Comentario>"+obj[8].toString()+"</Comentario>");
       arch.escribir("</Cita>");
    
           }
       } while (obj!=null);
  arch.escribir("</Expediente>");
      arch.guardar(); 
   }
   
   public void buscarObservaciones(ExpedienteSensillo exp){
       BD bd= new BD("SELECT Fecha,Descripcion FROM `expediente` WHERE Paciente=?");
       this.modelo=new Modeloexpediente();
       this.pacientes=new Pacientes();
       this.modelo.setPaciente(pacientes);
       this.modelo.getPaciente().setCedula(exp.getTxtCedulaPaciente().getText());
       bd.ejecutar(new Object[]{this.modelo.getPaciente().getCedula()});
          DefaultTableModel modelo = (DefaultTableModel) exp.getTablaObser().getModel();
         modelo.setNumRows(0);
         do {
              obj=bd.getObject();
             if (obj!=null) {
              modelo.addRow(obj);
             }
       } while (obj!=null);
   }
   
   public void acutalizarExpediente(ExpedienteSensillo exp){
        try {
            BD bd= new BD("UPDATE `expediente` SET `Descripcion`=?  WHERE Medico=? and Fecha=? AND Hora=? AND Paciente=?");
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date utilDate = formato.parse(exp.getTxtFechaAtencion().getText());
            java.sql.Date fecha = new java.sql.Date(utilDate.getTime());
            this.medico=new Medicos();
            this.pacientes=new Pacientes();
            this.modelo=new Modeloexpediente(fecha, exp.getTxtHora().getText(), medico, pacientes, exp.getTxtComentario().getText());
            this.modelo.getMedico().setCedula(exp.getTxtCedulaMedic().getText());
            this.modelo.getPaciente().setCedula(exp.getTxtCedulaPaciente().getText());
            bd.ejecutar(new Object[]{this.modelo.getDescripcion(),this.modelo.getMedico().getCedula(),this.modelo.getFecha(),this.modelo.getHora(),this.modelo.getPaciente().getCedula()});
                    } catch (ParseException ex) {
            Logger.getLogger(ControladorExpendiente.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
}
