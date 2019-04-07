/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.BD;
import Modelo.Pacientes;
import Vista.FrmPacientes;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kille
 */
public class ControladorPacientes {
    private FrmPacientes frmpacientes;
    
    private Pacientes pacientes;

    public ControladorPacientes(FrmPacientes frmpacientes) {
        this.frmpacientes = frmpacientes;
        pacientes=null;
    }
    
    
    public boolean guardarPaciente(){
        if (frmpacientes.getTxtcedula().getText()!=null&&frmpacientes.getTxtnombre().getText()!=null && frmpacientes.getTxtfecha().getText()!=null && frmpacientes.getTxtTelefono().getText()!=null && frmpacientes.getTxtcorreo().getText()!=null) {
         pacientes = new Pacientes(frmpacientes.getTxtcedula().getText(),frmpacientes.getTxtnombre().getText(),frmpacientes.getTxtfecha().getText(),frmpacientes.getTxtTelefono().getText(),frmpacientes.getTxtcorreo().getText());
         BD bd = new BD("INSERT INTO pacientes VALUES (?,?,?,?,?)");
         bd.ejectuar(new Object[]{this.pacientes.getCedula(),this.pacientes.getNombreCompleto(),this.pacientes.getFecha(),this.pacientes.getCorreo(),this.pacientes.getTelefono()});
         return true;
        }
         if (frmpacientes.getTxtcedula().getText()!=null&&frmpacientes.getTxtnombre().getText()!=null && frmpacientes.getTxtfecha().getText()!=null && frmpacientes.getTxtTelefono().getText()!=null && frmpacientes.getTxtcorreo().getText()==null) {
         pacientes = new Pacientes(frmpacientes.getTxtcedula().getText(),frmpacientes.getTxtnombre().getText(),frmpacientes.getTxtfecha().getText(),frmpacientes.getTxtTelefono().getText());
         return true;
        }
         if (frmpacientes.getTxtcedula().getText()!=null&&frmpacientes.getTxtnombre().getText()!=null && frmpacientes.getTxtfecha().getText()!=null && frmpacientes.getTxtTelefono().getText()==null && frmpacientes.getTxtcorreo().getText()==null) {
         pacientes = new Pacientes(frmpacientes.getTxtcedula().getText(),frmpacientes.getTxtnombre().getText(),frmpacientes.getTxtfecha().getText());
         return true;
        }
         if (frmpacientes.getTxtcedula().getText()!=null&&frmpacientes.getTxtnombre().getText()!=null && frmpacientes.getTxtfecha().getText()==null && frmpacientes.getTxtTelefono().getText()==null && frmpacientes.getTxtcorreo().getText()==null) {
         pacientes = new Pacientes(frmpacientes.getTxtcedula().getText(),frmpacientes.getTxtnombre().getText());
         return true;
        }
         return false;
        
        
    }
}
