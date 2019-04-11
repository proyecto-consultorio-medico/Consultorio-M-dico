/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.BD;
import Modelo.Medicos;
import Modelo.Pacientes;
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
        if ("Secretaria".equals(frmUsuarios.getTxtTipo())) {
            this.secretaria= new Secretaria(frmUsuarios.getTxtnombre().getText(),
                    frmUsuarios.getTxtcedula().getText(),frmUsuarios.getTxtfecha().getText(),frmUsuarios.getTxtcorreo().getText(),
                    frmUsuarios.getTxtTelefono().getText(),frmUsuarios.getTxtusuario().getText(),frmUsuarios.getTxtcontra().getText());
            BD bd=new BD("INSERT INTO secretarias VALUES (?,?,?,?,?,?,?)");
            if ( bd.ejectuar(new Object[]{secretaria.getCedula(),secretaria.getNombre(),secretaria.getFecha(),
                secretaria.getCorreo(),secretaria.getTelefono(),secretaria.getUsuario(),secretaria.getPass()})==false) {
                frmUsuarios.setTxtmensaje("El usuario no esta disponible, por favor elija otro");
            }else{
                 bd.ejectuar(new Object[]{secretaria.getCedula(),secretaria.getNombre(),secretaria.getFecha(),
                secretaria.getCorreo(),secretaria.getTelefono(),secretaria.getUsuario(),secretaria.getPass()});
                 frmUsuarios.setTxtmensaje("");
                  return true;
            }
        }
        
        if ("Medico".equals(frmUsuarios.getTxtTipo())) {
        
            this.medico= new Medicos(frmUsuarios.getTxtnombre().getText(),frmUsuarios.getTxtcedula().getText(),frmUsuarios.getTxtfecha().getText(),
                    frmUsuarios.getTxtcorreo().getText(),frmUsuarios.getTxtTelefono().getText(),frmUsuarios.getTxtusuario().getText(),
                    frmUsuarios.getTxtcontra().getText(), (String) frmUsuarios.getTxtespe(),frmUsuarios.getTxtCondigo().getText(),
                    Double.parseDouble(frmUsuarios.getTxtSalario().getText()));
            BD bd=new BD("INSERT INTO medicos VALUES (?,?,?,?,?,?,?,?,?,?)");
            if (bd.ejectuar(new Object[]{medico.getCedula(),medico.getNombre(),medico.getFecha()
                    ,medico.getCorreo(),medico.getCodigo(),medico.getTelefono(),medico.getEspecialidad()
                    ,medico.getSalario(),medico.getUsuario(),medico.getPass()})==false) {
                    frmUsuarios.setTxtmensaje("El usuario no esta disponible, por favor elija otro");
            }else{
            bd.ejectuar(new Object[]{medico.getCedula(),medico.getNombre(),medico.getFecha()
                    ,medico.getCorreo(),medico.getCodigo(),medico.getTelefono(),medico.getEspecialidad()
                    ,medico.getSalario(),medico.getUsuario(),medico.getPass()});
                    frmUsuarios.setTxtmensaje("");
              return true;
            }
        }
        return false;
        
    }
    public boolean eliminar() {
            BD bd = new BD("DELETE FROM pacientes WHERE Cedula=?");
            bd.ejectuar(new Object[]{frmUsuarios.getTxtcedula()});
            return true;
        }
}
