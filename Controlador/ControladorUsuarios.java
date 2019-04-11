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
            if (secretaria.comprobarCedula()==true) {
                BD bd=new BD("INSERT INTO secretarias VALUES (?,?,?,?,?,?,?)");
            if (secretaria.comprobarUsuario()==false) {
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
                   if ( medico.comprobarCedula()==false) {
                BD bd=new BD("INSERT INTO medicos VALUES (?,?,?,?,?,?,?,?,?,?)");
            if (this.medico.comprobarUsuario()==false) {
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
              this.secretaria= new Secretaria(frmUsuarios.getTxtusuario().getText());
              if (secretaria.comprobarUsuario()==false) {
                frmUsuarios.setTxtmensaje("El usuario no esta disponible, por favor elija otro");
                return false;
                    }
        }
        if ("Medico".equals(frmUsuarios.getCombotipo().getSelectedItem())){
        this.medico= new Medicos(frmUsuarios.getTxtusuario().getText());
            if (medico.comprobarUsuario()==false) {
                 frmUsuarios.setTxtmensaje("El usuario no esta disponible, por favor elija otro");
                 return false;
            }
        } 
            
            frmUsuarios.setTxtmensaje("El usuario esta disponible");
        return true;
    }
}
