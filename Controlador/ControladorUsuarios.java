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
        if ("Secretaria".equals(frmUsuarios.getTxtTipo().getText())) {
            this.secretaria= new Secretaria(frmUsuarios.getTxtnombre().getText(),frmUsuarios.getTxtcedula().getText(),frmUsuarios.getTxtfecha().getText(),frmUsuarios.getTxtcorreo().getText(),frmUsuarios.getTxtTelefono().getText(),frmUsuarios.getTxtusuario().getText(),frmUsuarios.getTxtcontra().getText());
            BD bd=new BD("INSERT INTO secretarias VALUES (?,?,?,?,?,?,?)");
            bd.ejectuar(new Object[]{secretaria.getCedula(),secretaria.getNombre(),secretaria.getFecha(),secretaria.getCorreo(),secretaria.getTelefono(),secretaria.getUsuario(),secretaria.getPass()});
            return true;
        }
        
        if ("Medico".equals(frmUsuarios.getTxtTipo().getText())) {
            this.medico= new Medicos(frmUsuarios.getTxtnombre().getText(),frmUsuarios.getTxtcedula().getText(),frmUsuarios.getTxtfecha().getText(),
                    frmUsuarios.getTxtcorreo().getText(),frmUsuarios.getTxtTelefono().getText(),frmUsuarios.getTxtusuario().getText(),
                    frmUsuarios.getTxtcontra().getText(),frmUsuarios.getTxtEspecialidad().getText(),frmUsuarios.getTxtCondigo().getText(),
                    Double.parseDouble(frmUsuarios.getTxtSalario().getText()));
            BD bd=new BD("INSERT INTO medicos VALUES (?,?,?,?,?,?,?,?,?,?)");
            bd.ejectuar(new Object[]{medico.getCedula(),medico.getNombre(),medico.getFecha()
                    ,medico.getCorreo(),medico.getCodigo(),medico.getTelefono(),medico.getEspecialidad()
                    ,medico.getSalario(),medico.getUsuario(),medico.getPass()});
            return true;
        }
        return false;
    }
}
