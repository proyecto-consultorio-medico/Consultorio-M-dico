/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.BD;
import Modelo.Medicos;
import Modelo.Secretaria; 
import Vista.FrmBuscarUsuario;
import Vista.FrmSesion;
import Vista.Usuarios;
import Vista.frmCambioPass;
import java.sql.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jose,Marco,Yuliana,Elver
 */
public class ControladorUsuarios {
    private Secretaria secretaria;
    private Medicos medico;
    private  Object obj[];
    public ControladorUsuarios() {
        this.secretaria = null;
        this.medico= null;
    }
    
    public boolean agregar(Usuarios frmUsuarios){
        if (!"".equals(frmUsuarios.getTxtcedula().getText())) {
             if ("Secretaria".equals(frmUsuarios.getCombotipo().getSelectedItem())) {
                 secretaria= new Secretaria();
                 secretaria.setCedula(frmUsuarios.getTxtcedula().getText());
                 if (secretaria.contarDigitosCedu()==false) {
                     JOptionPane.showMessageDialog(null, "La cedula es invalida");
                     return false;
                 }
            if (comprobarCedulaSecre()==true) {
             
            if (comprobarUsuarioSecre(frmUsuarios)==false) {
                frmUsuarios.setTxtmensaje("El usuario no esta disponible, por favor elija otro");
            }else{
                Date fecha = new Date(frmUsuarios.getTxtFecha().getDate().getTime());
                this.secretaria= new Secretaria(frmUsuarios.getTxtnombre().getText(),frmUsuarios.getTxtcedula().getText(),
                    frmUsuarios.getTxtcorreo().getText(),frmUsuarios.getTxtTelefono().getText(),frmUsuarios.getTxtusuario().getText(),
                    frmUsuarios.getTxtcontra().getText(),fecha);
                this.secretaria.ponerMayusculas();
                if (secretaria.contarDigitostel()==false) {
                     JOptionPane.showMessageDialog(null, "El numero de telefono es invalido");
                     return false;
                 }
                   BD bd=new BD("INSERT INTO secretarias VALUES (?,?,?,?,?,?,?)");
                 bd.ejecutar(new Object[]{secretaria.getCedula(),secretaria.getNombre(),secretaria.getFecha(),
                secretaria.getCorreo(),secretaria.getTelefono(),secretaria.getUsuario(),secretaria.getPass()});
                 frmUsuarios.setTxtmensaje("");
                  return true;
            }
            }else{
            frmUsuarios.setTxtMensajeC("La cedula ya existe");
            return false;
            }
             }
        }
        
        if ("Medico".equals(frmUsuarios.getCombotipo().getSelectedItem())) {
            medico=new Medicos();
            medico.setCedula(frmUsuarios.getTxtcedula().getText());
            if (medico.contarDigitosCedu()==false) {
                JOptionPane.showMessageDialog(null, "La cedula es invalida");
            }
            if ( comprobarCedulaMedic()==true) {
               
            if (comprobarUsuarioMedic(frmUsuarios)==false) {
                    frmUsuarios.setTxtmensaje("El usuario no esta disponible, por favor elija otro");
            }else{
                    Date fecha = new Date(frmUsuarios.getTxtFecha().getDate().getTime());
                  
                  this.medico= new Medicos(frmUsuarios.getTxtnombre().getText(),frmUsuarios.getTxtcedula().getText(),
                    frmUsuarios.getTxtcorreo().getText(),frmUsuarios.getTxtTelefono().getText(),frmUsuarios.getTxtusuario().getText(),
                    frmUsuarios.getTxtcontra().getText(), (String) frmUsuarios.getComboespe().getSelectedItem(),frmUsuarios.getTxtCondigo().getText(),
                    Double.parseDouble(frmUsuarios.getTxtSalario().getText()),fecha);
                   this.medico.ponerMayusculas();
                  if (medico.contarDigitostel()==false) {
                     JOptionPane.showMessageDialog(null, "El numero de telefono es invalido");
                     return false;
                 }
                    BD bd=new BD("INSERT INTO medicos VALUES (?,?,?,?,?,?,?,?,?,?)");
            bd.ejecutar(new Object[]{medico.getCedula(),medico.getNombre(),medico.getFecha()
                    ,medico.getCorreo(),medico.getCodigo(),medico.getTelefono(),medico.getEspecialidad()
                    ,medico.getSalario(),medico.getUsuario(),medico.getPass()});
                    frmUsuarios.setTxtmensaje("");
              return true;
            }
            }else{
                   frmUsuarios.setTxtMensajeC("La cedula ya existe");
                   return false;
                   }
        }
        
         return false;
    }
    
    public boolean comprobarUs(Usuarios frmUsuarios){
        if ("Secretaria".equals(frmUsuarios.getCombotipo().getSelectedItem())) {
              this.secretaria= new Secretaria();
              secretaria.setUsuario(frmUsuarios.getTxtusuario().getText());
              if (comprobarUsuarioSecre(frmUsuarios)==false) {
                frmUsuarios.setTxtmensaje("El usuario no esta disponible, por favor elija otro");
                return false;
                    }
        }
        if ("Medico".equals(frmUsuarios.getCombotipo().getSelectedItem())){
        this.medico= new Medicos();
        this.medico.setUsuario(frmUsuarios.getTxtusuario().getText());
            if (comprobarUsuarioMedic(frmUsuarios)==false) {
                 frmUsuarios.setTxtmensaje("El usuario no esta disponible, por favor elija otro");
                 return false;
            }
        } 
            
            frmUsuarios.setTxtmensaje("El usuario esta disponible");
        return true;
    }
    public boolean comprobarUsuarioSecre(Usuarios frmUsuarios){
       this.secretaria= new Secretaria();
       secretaria.setUsuario(frmUsuarios.getTxtusuario().getText());
        BD bd= new BD("SELECT `Usuario` FROM `secretarias` WHERE Usuario=?");
        bd.ejecutar(new Object[]{this.secretaria.getUsuario()});
       obj=bd.getObject();
        if (obj==null) {
            return true;
        }else{
         if (obj[0].equals(this.secretaria.getUsuario())) {
          return false;
         }
        }
        return true;
        
    }
    
    public boolean comprobarUsuarioMedic(Usuarios frmUsuarios){
        this.medico=new Medicos();
        this.medico.setUsuario(frmUsuarios.getTxtusuario().getText());
    BD bd= new BD("SELECT `Usuario` FROM `medicos` WHERE Usuario=?");
    bd.ejecutar(new Object[]{this.medico.getUsuario()});
    obj=bd.getObject();
        if (obj==null) {
            return true;
        }else{
         if (obj[0].equals(this.medico.getUsuario())) {
          return false;
         }
        }
        return true;
    }
   public boolean comprobarCedulaSecre(){
    BD bd= new BD("SELECT `Cedula` FROM `secretarias` WHERE Cedula=?");
    bd.ejecutar(new Object[]{this.secretaria.getCedula()});
    obj=bd.getObject();
        if (obj==null) {
            return true;
        }else{
         if (obj[0].equals(this.secretaria.getCedula())) {
          return false;
         }
        }
        return true;
    }
     public boolean comprobarCedulaMedic(){
       BD bd= new BD("SELECT `Cedula` FROM `medicos` WHERE Cedula=?");
       bd.ejecutar(new Object[]{this.medico.getCedula()});
        obj=bd.getObject();
        if (obj==null) {
            return true;
        }else{
         if (obj[0].equals(this.medico.getCedula())) {
          return false;
         }
        }
        return true;
    }
     
     public boolean buscarcitamedico(Usuarios frmUsuarios){
     BD bd= new BD("SELECT * FROM `medicos` JOIN citas ON medicos.Cedula=citas.Medico  WHERE Cedula=?");
        this.medico= new Medicos();
        this.medico.setCedula(frmUsuarios.getTxtcedula().getText());
        bd.ejecutar(new Object[]{this.medico.getCedula()});
        obj=bd.getObject();
         if (obj==null) {
             return false;
         }else{
             return true;
         }
         
     }
     
      public boolean eliminar(Usuarios frmUsuarios) {
          if (frmUsuarios.combotipo.getSelectedItem().equals("Secretaria")) {
              BD bd = new BD("DELETE FROM secretarias WHERE Cedula=?");
              this.secretaria=new Secretaria();
              this.secretaria.setCedula(frmUsuarios.getTxtcedula().getText());
            bd.ejecutar(new Object[]{this.secretaria.getCedula()});
            return true;
          }
             if (frmUsuarios.combotipo.getSelectedItem().equals("Medico")) {
                BD bd = new BD("DELETE FROM medicos WHERE Cedula=?");
                this.medico=new Medicos();
                this.medico.setCedula(frmUsuarios.getTxtcedula().getText());
            bd.ejecutar(new Object[]{this.medico.getCedula()});
            return true;
          }
          return false;
        }
   
      public boolean cambioContraSec(frmCambioPass frmcambiocont){
      BD bd= new BD("SELECT `Contra` FROM `secretarias` WHERE Usuario=?");
      secretaria= new Secretaria();
      secretaria.setUsuario(frmcambiocont.getTxtUsuario().getText());
      bd.ejecutar(new Object[]{this.secretaria.getUsuario()});
      obj=bd.getObject();
          if (obj==null) {
              return false;
          }else{
          if(obj[0].equals(frmcambiocont.getTxtContraAnti().getText())){
          JOptionPane.showMessageDialog(null,"Contraseña correcta");
          BD bd2= new BD("UPDATE `secretarias` SET `Contra`=? WHERE Usuario=?");
          if(frmcambiocont.getTxtContraNue().getText().equals(frmcambiocont.getTxtverificontra().getText())){
              bd2.ejecutar(new Object[]{frmcambiocont.getTxtContraNue().getText(),secretaria.getUsuario()});
              return true;
          }else{
              
          }
          JOptionPane.showMessageDialog(null, "Las contraseña no son iguales");
      }
          }
          
         return false;
      }
      
     public boolean cambioContraMedic(frmCambioPass frmcambiocont){
          BD bd= new BD("SELECT `Contraseña` FROM `medicos` WHERE Usuario=?");
      medico= new Medicos();
      medico.setUsuario(frmcambiocont.getTxtUsuario().getText());
      bd.ejecutar(new Object[]{this.medico.getUsuario()});
      obj=bd.getObject();
          if (obj==null) {
             return false;
         }else{
          if(obj[0].equals(frmcambiocont.getTxtContraAnti().getText())){
          JOptionPane.showMessageDialog(null,"Contraseña correcta");
          BD bd2= new BD("UPDATE `medicos` SET `Contraseña`=? WHERE Usuario=?");
          if(frmcambiocont.getTxtContraNue().getText().equals(frmcambiocont.getTxtverificontra().getText())){
              bd2.ejecutar(new Object[]{frmcambiocont.getTxtContraNue().getText(),medico.getUsuario()});
                   return true;
          }
          
      }
          }
          
          return false;
     } 
       public void buscarMedicoCedu(FrmBuscarUsuario frmpbuscar) {
        if (!"".equals(frmpbuscar.getTxtBuscar().getText())) {
           medico= new Medicos();
            medico.setCedula(frmpbuscar.getTxtBuscar().getText());
            BD bd = new BD("SELECT *  FROM `medicos` WHERE Cedula=?");
            bd.ejecutar(new Object[]{medico.getCedula()});
            DefaultTableModel modelo = (DefaultTableModel) frmpbuscar.getTablaUsuarios().getModel();
            modelo.addRow(bd.getObject());
        }
       }
       
       public void buscarSecretariaCedu(FrmBuscarUsuario frmpbuscar) {
        if (!"".equals(frmpbuscar.getTxtBuscar().getText())) {
           secretaria = new Secretaria();
            secretaria.setCedula(frmpbuscar.getTxtBuscar().getText());
            BD bd = new BD("SELECT *  FROM `secretarias` WHERE Cedula=?");
            bd.ejecutar(new Object[]{secretaria.getCedula()});
            DefaultTableModel modelo = (DefaultTableModel) frmpbuscar.getTablaUsuarios2().getModel();
            modelo.addRow(bd.getObject());
        }
       }
       
       public void buscarMedicoNom(FrmBuscarUsuario frmpbuscar){
          medico= new Medicos();
        medico.setNombre(frmpbuscar.getTxtBuscar().getText());
           BD bd = new BD("SELECT * FROM `medicos` WHERE NombreCompleto LIKE ?"); 
             bd.ejecutar(new Object[]{"%"+medico.getNombre()+"%"});
              DefaultTableModel modelo = (DefaultTableModel)
                      frmpbuscar.getTablaUsuarios().getModel();
              modelo.setNumRows(0);
             do {
                 obj = bd.getObject();
            if (obj != null) {
                this.medico = new Medicos(obj);
                modelo.addRow(medico.toObject());
            }
        } while (obj!=null);
       }
       
       public void buscarSecretariaNom(FrmBuscarUsuario frmpbuscar){
          secretaria= new Secretaria();
        secretaria.setNombre(frmpbuscar.getTxtBuscar().getText());
           BD bd = new BD("SELECT * FROM `secretarias` WHERE 	`Nombre Completo` LIKE ?"); 
             bd.ejecutar(new Object[]{"%"+secretaria.getNombre()+"%"});
              DefaultTableModel modelo = (DefaultTableModel)
                      frmpbuscar.getTablaUsuarios2().getModel();
              modelo.setNumRows(0);
             do {
                 obj = bd.getObject();
            if (obj != null) {
                this.secretaria = new Secretaria(obj);
                modelo.addRow(secretaria.toObject());
            }
        } while (obj!=null);
       }
       
       public boolean contarUsuarios(){
           int cont;
           BD bd= new BD("Select count(*) FROM `medicos` WHERE Cedula=Cedula");
           bd.ejectuar();
           obj=bd.getObject();
           cont=Integer.parseInt(obj[0].toString());
           if (cont>0) {
               return true;
           }else{
               return false;
           }
       }
       public String salarioNeto(Usuarios frmUsuarios){
           this.medico= new Medicos();
           this.medico.setSalario(Double.parseDouble(frmUsuarios.getTxtSalario().getText()));
           return this.medico.calcularSalarioNeto();
       }
       public String salarioNeto2(FrmBuscarUsuario frmbuscar, String salario){
           this.medico= new Medicos();
           this.medico.setSalario(Double.parseDouble(salario));
           return this.medico.calcularSalarioNeto();
       }
       
       public boolean actualizar(Usuarios frmUsuarios){
        if (frmUsuarios.combotipo.getSelectedItem().equals("Secretaria")) {
              BD bd = new BD("DELETE FROM secretarias WHERE Cedula=?");
              this.secretaria=new Secretaria();
              this.secretaria.setCedula(frmUsuarios.getTxtcedula().getText());
            bd.ejecutar(new Object[]{this.secretaria.getCedula()});
            return true;
          }
             if (frmUsuarios.combotipo.getSelectedItem().equals("Medico")) {
                BD bd = new BD("UPDATE `medicos` SET `NombreCompleto`=?,`FechaDeNacimiento`=?,`CorreoElectronico`=?,`CodigoDeColegio`=?,`NumeroTelefono`=?,`Especialidad`=?,`Salario`=?,`Usuario`=? WHERE Cedula=?");
               Date fecha = new Date(frmUsuarios.getTxtFecha().getDate().getTime());
                this.medico=new Medicos(frmUsuarios.getTxtnombre().getText(), frmUsuarios.getTxtcedula().getText(), frmUsuarios.getTxtcorreo().getText(), frmUsuarios.getTxtTelefono().getText(), frmUsuarios.getTxtusuario().getText(), frmUsuarios.getTxtcontra().getText(), (String) frmUsuarios.comboespe.getSelectedItem(), frmUsuarios.getTxtCondigo().getText(),Double.parseDouble(frmUsuarios.getTxtSalario().getText()), fecha);
            bd.ejecutar(new Object[]{this.medico.getNombre(),this.medico.getFecha(),this.medico.getCorreo(),this.medico.getCodigo(),this.medico.getTelefono(),this.medico.getEspecialidad(),this.medico.getSalario(),this.medico.getUsuario(),this.medico.getCedula()});
            return true;
          }
          return false;
       }
       
       public boolean iniciarSeccion(FrmSesion frmsesion){
          BD bd = new BD("SELECT `Usuario`,`Contraseña` FROM `medicos` WHERE Usuario=? and Contraseña=?");
          this.medico=new Medicos();
          this.medico.setUsuario(frmsesion.getTxtUsuario().getText());
          this.medico.setPass(frmsesion.getTxtContra().getText());
          bd.ejecutar(new Object[]{medico.getUsuario(),medico.getPass()});
          obj=bd.getObject();
           if (obj==null) {
               return false;
           }else{
               if (obj[0].equals(this.medico.getUsuario())&&obj[1].equals(this.medico.getPass())) {
                   return true;
               }
           }
          return false;
       }
       
       
}
