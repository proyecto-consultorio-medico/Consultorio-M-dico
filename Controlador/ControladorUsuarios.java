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
import Vista.Usuarios;
import Vista.frmCambioPass;
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
                java.sql.Date fecha = new java.sql.Date(frmUsuarios.getTxtFecha().getDate().getTime());
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
                    java.sql.Date fecha = new java.sql.Date(frmUsuarios.getTxtFecha().getDate().getTime());
                    System.out.println(fecha);
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
        System.out.println(obj);
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
        System.out.println(obj);
        System.out.println(this.medico.getUsuario());
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
        System.out.println(obj);
        System.out.println(this.secretaria.getCedula());
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
        System.out.println(obj);
        System.out.println(this.medico.getCedula());
        if (obj==null) {
            return true;
        }else{
         if (obj[0].equals(this.medico.getCedula())) {
          return false;
         }
        }
        return true;
    }
     
      public boolean eliminar(Usuarios frmUsuarios) {
            BD bd = new BD("DELETE FROM pacientes WHERE Cedula=?");
            bd.ejecutar(new Object[]{frmUsuarios.getTxtcedula()});
            return true;
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
          System.out.println(frmcambiocont.getTxtContraAnti().getText());
          if(obj[0].equals(frmcambiocont.getTxtContraAnti().getText())){
          JOptionPane.showMessageDialog(null,"Contraseña correcta");
          BD bd2= new BD("UPDATE `secretarias` SET `Contra`=? WHERE Usuario=?");
          if(frmcambiocont.getTxtContraNue().getText().equals(frmcambiocont.getTxtverificontra().getText())){
              bd2.ejecutar(new Object[]{frmcambiocont.getTxtContraNue().getText(),secretaria.getUsuario()});
              return true;
          }
          
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
          System.out.println(obj[0]);
          System.out.println(frmcambiocont.getTxtContraAnti().getText());
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
            DefaultTableModel modelo = (DefaultTableModel) frmpbuscar.getTablaUsuarios().getModel();
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
}
