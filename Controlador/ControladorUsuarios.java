/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.BD;
import Modelo.Medicos;
import Modelo.Secretaria;
import Modelo.Usuario;
import Vista.Usuarios;
import javax.swing.JOptionPane;

/**
 *
 * @author Jose,Marco,Yuliana,Elver
 */
public class ControladorUsuarios {
    private Secretaria secretaria;
    private Medicos medico;
    private Usuarios frmUsuarios;

    public ControladorUsuarios(Usuarios frmUsuarios) {
        this.frmUsuarios = frmUsuarios;
        this.secretaria = null;
        this.medico= null;
    }
    
    public boolean agregar(){
        if (!"".equals(frmUsuarios.getTxtcedula().getText())) {
             if ("Secretaria".equals(frmUsuarios.getCombotipo().getSelectedItem())) {
            this.secretaria= new Secretaria(frmUsuarios.getTxtnombre().getText(),
                    frmUsuarios.getTxtcedula().getText(),frmUsuarios.getTxtfecha().getText(),frmUsuarios.getTxtcorreo().getText(),
                    frmUsuarios.getTxtTelefono().getText(),frmUsuarios.getTxtusuario().getText(),frmUsuarios.getTxtcontra().getText());
            if (comprobarCedulaSecre()==true) {
                BD bd=new BD("INSERT INTO secretarias VALUES (?,?,?,?,?,?,?)");
            if (comprobarUsuarioSecre()==false) {
                frmUsuarios.setTxtmensaje("El usuario no esta disponible, por favor elija otro");
            }else{
                 bd.ejectuar(new Object[]{secretaria.getCedula(),secretaria.getNombre(),secretaria.getFecha(),
                secretaria.getCorreo(),secretaria.getTelefono(),secretaria.getUsuario(),secretaria.getPass()});
                 frmUsuarios.setTxtmensaje("");
                  return true;
            }
            }else{
            frmUsuarios.setTxtMensajeC("La cedula ya existe");
            }
            
        }
        
        if ("Medico".equals(frmUsuarios.getCombotipo().getSelectedItem())) {
        
            this.medico= new Medicos(frmUsuarios.getTxtnombre().getText(),frmUsuarios.getTxtcedula().getText(),frmUsuarios.getTxtfecha().getText(),
                    frmUsuarios.getTxtcorreo().getText(),frmUsuarios.getTxtTelefono().getText(),frmUsuarios.getTxtusuario().getText(),
                    frmUsuarios.getTxtcontra().getText(), (String) frmUsuarios.getComboespe().getSelectedItem(),frmUsuarios.getTxtCondigo().getText(),
                    Double.parseDouble(frmUsuarios.getTxtSalario().getText()));
                   if ( comprobarCedulaMedic()==false) {
                BD bd=new BD("INSERT INTO medicos VALUES (?,?,?,?,?,?,?,?,?,?)");
            if (comprobarUsuarioMedic()==false) {
                    frmUsuarios.setTxtmensaje("El usuario no esta disponible, por favor elija otro");
            }else{
            bd.ejectuar(new Object[]{medico.getCedula(),medico.getNombre(),medico.getFecha()
                    ,medico.getCorreo(),medico.getCodigo(),medico.getTelefono(),medico.getEspecialidad()
                    ,medico.getSalario(),medico.getUsuario(),medico.getPass()});
                    frmUsuarios.setTxtmensaje("");
              return true;
            }
            }else{
                   frmUsuarios.setTxtMensajeC("La cedula ya existe");
                   }
            
        }
        return false;
        }
         return false;
    }
    
    public boolean comprobarUs(){
        if ("Secretaria".equals(frmUsuarios.getCombotipo().getSelectedItem())) {
              this.secretaria= new Secretaria();
              secretaria.setUsuario(frmUsuarios.getTxtusuario().getText());
              if (comprobarUsuarioSecre()==false) {
                frmUsuarios.setTxtmensaje("El usuario no esta disponible, por favor elija otro");
                return false;
                    }
        }
        if ("Medico".equals(frmUsuarios.getCombotipo().getSelectedItem())){
        this.medico= new Medicos();
        this.medico.setUsuario(frmUsuarios.getTxtusuario().getText());
            if (comprobarUsuarioMedic()==false) {
                 frmUsuarios.setTxtmensaje("El usuario no esta disponible, por favor elija otro");
                 return false;
            }
        } 
            
            frmUsuarios.setTxtmensaje("El usuario esta disponible");
        return true;
    }
    public boolean comprobarUsuarioSecre(){
        BD bd= new BD("SELECT `Usuario` FROM `secretarias` WHERE Usuario=?");
        bd.ejectuar(new Object[]{this.secretaria.getUsuario()});
        if (bd.getObject().equals(this.secretaria.getUsuario())) {
             return false;
         }
         return true;
    }
    
    public boolean comprobarUsuarioMedic(){
    BD bd= new BD("SELECT `Usuario` FROM `medicos` WHERE Usuario=?");
    bd.ejectuar(new Object[]{this.medico.getUsuario()});
    if (bd.getObject().equals(this.medico.getUsuario())) {
             return false;
         }
         return true;
    }
   public boolean comprobarCedulaSecre(){
    BD bd= new BD("SELECT `Cedula` FROM `secretarias` WHERE Cedula=?");
    bd.ejectuar(new Object[]{this.secretaria.getCedula()});
    if (bd.getObject().equals(this.secretaria.getCedula())) {
             return false;
         }
         return true;
    }
     public boolean comprobarCedulaMedic(){
       BD bd= new BD("SELECT `Cedula` FROM `medicos` WHERE Cedula=?");
       bd.ejectuar(new Object[]{this.medico.getCedula()});
         if (bd.getObject().equals(this.medico.getCedula())) {
             return false;
         }
         return true;
    }
   
}
