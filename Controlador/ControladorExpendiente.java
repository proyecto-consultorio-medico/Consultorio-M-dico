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
import Vista.ExpedienteSensillo;
import Vista.FrmSesion;
import java.sql.Date;
import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author kille
 */
public class ControladorExpendiente {
    private MoCitas citas;
    private Medicos medico;
    private Secretaria secretaria;
    private Pacientes pacientes;

    public ControladorExpendiente() {
        citas=null;
        medico=null;
        secretaria=null;
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
}
