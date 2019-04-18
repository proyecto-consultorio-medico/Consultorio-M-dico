/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.BD;
import Modelo.Medicos;
import Modelo.Secretaria;
import Vista.Usuarios;
import Vista.frmCambioPass;
import javax.swing.JOptionPane;

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
                 String fechas[]=frmUsuarios.getTxtfecha().getText().split("/");
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
                this.secretaria= new Secretaria(frmUsuarios.getTxtnombre().getText(),frmUsuarios.getTxtcedula().getText(),
                    frmUsuarios.getTxtcorreo().getText(),frmUsuarios.getTxtTelefono().getText(),frmUsuarios.getTxtusuario().getText(),
                    frmUsuarios.getTxtcontra().getText(),Integer.parseInt(fechas[2]),Integer.parseInt(fechas[1]),Integer.parseInt(fechas[0]));
                this.secretaria.ponerMayusculas();
                if (secretaria.contarDigitostel()==false) {
                     JOptionPane.showMessageDialog(null, "El numero de telefono es invalido");
                     return false;
                 }
                   BD bd=new BD("INSERT INTO secretarias VALUES (?,?,?,?,?,?,?)");
                   this.secretaria.validarFecha(this.secretaria.getAño(), this.secretaria.getMes(), secretaria.getDia());
                System.out.println(secretaria.getCedula()+" "+secretaria.getNombre()+secretaria.getFecha()+
                " "+secretaria.getCorreo()+" "+secretaria.getTelefono()+" "+secretaria.getUsuario()+" "+secretaria.getPass());
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
            String fechas[]=frmUsuarios.getTxtfecha().getText().split("/");
            medico=new Medicos();
            medico.setCedula(frmUsuarios.getTxtcedula().getText());
            if (medico.contarDigitosCedu()==false) {
                JOptionPane.showMessageDialog(null, "La cedula es invalida");
            }
            if ( comprobarCedulaMedic()==true) {
               
            if (comprobarUsuarioMedic(frmUsuarios)==false) {
                    frmUsuarios.setTxtmensaje("El usuario no esta disponible, por favor elija otro");
            }else{
                  this.medico= new Medicos(frmUsuarios.getTxtnombre().getText(),frmUsuarios.getTxtcedula().getText(),
                    frmUsuarios.getTxtcorreo().getText(),frmUsuarios.getTxtTelefono().getText(),frmUsuarios.getTxtusuario().getText(),
                    frmUsuarios.getTxtcontra().getText(), (String) frmUsuarios.getComboespe().getSelectedItem(),frmUsuarios.getTxtCondigo().getText(),
                    Double.parseDouble(frmUsuarios.getTxtSalario().getText()),Integer.parseInt(fechas[2]),Integer.parseInt(fechas[1]),Integer.parseInt(fechas[0]));
                   this.medico.ponerMayusculas();
                  if (medico.contarDigitostel()==false) {
                     JOptionPane.showMessageDialog(null, "El numero de telefono es invalido");
                     return false;
                 }
                    BD bd=new BD("INSERT INTO medicos VALUES (?,?,?,?,?,?,?,?,?,?)");
                   this.medico.validarFecha(this.medico.getAño(), this.medico.getMes(), this.medico.getDia());
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
   
      public void cambioContraSec(frmCambioPass frmcambiocont){
      BD bd= new BD("SELECT `Contra` FROM `secretarias` WHERE Usuario=?");
      secretaria= new Secretaria();
      secretaria.setUsuario(frmcambiocont.getTxtUsuario().getText());
          System.out.println(this.secretaria.getUsuario());
      bd.ejecutar(new Object[]{this.secretaria.getUsuario()});
      obj=bd.getObject();
          System.out.println(obj[0]);
          System.out.println(frmcambiocont.getTxtContraAnti().getText());
          if(obj[0].equals(frmcambiocont.getTxtContraAnti().getText())){
          JOptionPane.showMessageDialog(null,"Contraseña correcta");
          BD bd2= new BD("UPDATE `secretarias` SET `Contra`=? WHERE Usuario=?");
          if(frmcambiocont.getTxtContraNue().getText().equals(frmcambiocont.getTxtverificontra().getText())){
              System.out.println("Se agrego a la base");
              bd2.ejecutar(new Object[]{frmcambiocont.getTxtContraNue().getText(),secretaria.getUsuario()});
          }
          
      }
          System.out.println("Salto");
      }
      
     public void cambioContraMedic(){
         
     } 
}
