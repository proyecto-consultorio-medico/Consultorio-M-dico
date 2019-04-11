/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.BD;
import Modelo.Pacientes;
import Vista.BuscaPaciente;
import Vista.FrmPacientes;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Jose,Marco,Yuliana,Elver
 */
public class ControladorPacientes {

    private FrmPacientes frmpacientes;

    private Pacientes pacientes;

    public ControladorPacientes(FrmPacientes frmpacientes) {
        this.frmpacientes = frmpacientes;
        pacientes = null;
    }
    
        public boolean actualizarPaciente() {
        if (!"".equals(frmpacientes.getTxtcedula().getText())) {
            if (!"".equals(frmpacientes.getTxtcedula().getText()) && !"".equals(frmpacientes.getTxtnombre().getText())
                    && !"".equals(frmpacientes.getTxtfecha().getText()) && !"".equals(frmpacientes.getTxtTelefono().getText())
                    && !"".equals(frmpacientes.getTxtcorreo().getText())) {
                pacientes = new Pacientes(frmpacientes.getTxtcedula().getText(), frmpacientes.getTxtnombre().getText(),
                        frmpacientes.getTxtfecha().getText(), frmpacientes.getTxtTelefono().getText(), frmpacientes.getTxtcorreo().getText());
                BD bd = new BD("UPDATE INTO pacientes VALUES (?,?,?,?,?)");
                bd.ejectuar(new Object[]{this.pacientes.getCedula(), this.pacientes.getNombreCompleto(),
                    this.pacientes.getFecha(), this.pacientes.getCorreo(), this.pacientes.getTelefono()});
                return true;
            }
            if (!"".equals(frmpacientes.getTxtcedula().getText()) && !"".equals(frmpacientes.getTxtnombre().getText())
                    && !"".equals(frmpacientes.getTxtfecha().getText()) && !"".equals(frmpacientes.getTxtTelefono().getText())) {
                pacientes = new Pacientes(frmpacientes.getTxtcedula().getText(), frmpacientes.getTxtnombre().getText(),
                        frmpacientes.getTxtfecha().getText(), frmpacientes.getTxtTelefono().getText(), frmpacientes.getTxtcorreo().getText());
                BD bd = new BD("UPDATE INTO pacientes VALUES (?,?,?,?,?)");
                bd.ejectuar(new Object[]{this.pacientes.getCedula(), this.pacientes.getNombreCompleto(),
                    this.pacientes.getFecha(), this.pacientes.getCorreo(), this.pacientes.getTelefono()});
                return true;
            }
            if (!"".equals(frmpacientes.getTxtcedula().getText()) && !"".equals(frmpacientes.getTxtnombre().getText())
                    && !"".equals(frmpacientes.getTxtfecha().getText())) {
                pacientes = new Pacientes(frmpacientes.getTxtcedula().getText(), frmpacientes.getTxtnombre().getText(),
                        frmpacientes.getTxtfecha().getText(), frmpacientes.getTxtTelefono().getText(), frmpacientes.getTxtcorreo().getText());
                BD bd = new BD("UPDATE INTO pacientes VALUES (?,?,?,?,?)");
                bd.ejectuar(new Object[]{this.pacientes.getCedula(), this.pacientes.getNombreCompleto(),
                    this.pacientes.getFecha(), this.pacientes.getCorreo(), this.pacientes.getTelefono()});
                return true;
            }
            if (!"".equals(frmpacientes.getTxtcedula().getText()) && !"".equals(frmpacientes.getTxtnombre().getText())) {
                pacientes = new Pacientes(frmpacientes.getTxtcedula().getText(), frmpacientes.getTxtnombre().getText(),
                        frmpacientes.getTxtfecha().getText(), frmpacientes.getTxtTelefono().getText(), frmpacientes.getTxtcorreo().getText());
                BD bd = new BD("UPDATE INTO pacientes VALUES (?,?,?,?,?)");
                bd.ejectuar(new Object[]{this.pacientes.getCedula(), this.pacientes.getNombreCompleto(),
                    this.pacientes.getFecha(), this.pacientes.getCorreo(), this.pacientes.getTelefono()});
                return true;
            }
        }

        return false;

    }

    public boolean guardarPaciente() {
        if (!"".equals(frmpacientes.getTxtcedula().getText())) {
            if (!"".equals(frmpacientes.getTxtcedula().getText()) && !"".equals(frmpacientes.getTxtnombre().getText())
                    && !"".equals(frmpacientes.getTxtfecha().getText()) && !"".equals(frmpacientes.getTxtTelefono().getText())
                    && !"".equals(frmpacientes.getTxtcorreo().getText())) {
                pacientes = new Pacientes(frmpacientes.getTxtcedula().getText(), frmpacientes.getTxtnombre().getText(),
                        frmpacientes.getTxtfecha().getText(), frmpacientes.getTxtTelefono().getText(), frmpacientes.getTxtcorreo().getText());
                BD bd = new BD("INSERT INTO pacientes VALUES (?,?,?,?,?)");
                bd.ejectuar(new Object[]{this.pacientes.getCedula(), this.pacientes.getNombreCompleto(),
                    this.pacientes.getFecha(), this.pacientes.getCorreo(), this.pacientes.getTelefono()});
                return true;
            }
            if (!"".equals(frmpacientes.getTxtcedula().getText()) && !"".equals(frmpacientes.getTxtnombre().getText())
                    && !"".equals(frmpacientes.getTxtfecha().getText()) && !"".equals(frmpacientes.getTxtTelefono().getText())) {
                pacientes = new Pacientes(frmpacientes.getTxtcedula().getText(), frmpacientes.getTxtnombre().getText(),
                        frmpacientes.getTxtfecha().getText(), frmpacientes.getTxtTelefono().getText(), frmpacientes.getTxtcorreo().getText());
                BD bd = new BD("INSERT INTO pacientes VALUES (?,?,?,?,?)");
                bd.ejectuar(new Object[]{this.pacientes.getCedula(), this.pacientes.getNombreCompleto(),
                    this.pacientes.getFecha(), this.pacientes.getCorreo(), this.pacientes.getTelefono()});
                return true;
            }
            if (!"".equals(frmpacientes.getTxtcedula().getText()) && !"".equals(frmpacientes.getTxtnombre().getText())
                    && !"".equals(frmpacientes.getTxtfecha().getText())) {
                pacientes = new Pacientes(frmpacientes.getTxtcedula().getText(), frmpacientes.getTxtnombre().getText(),
                        frmpacientes.getTxtfecha().getText(), frmpacientes.getTxtTelefono().getText(), frmpacientes.getTxtcorreo().getText());
                BD bd = new BD("INSERT INTO pacientes VALUES (?,?,?,?,?)");
                bd.ejectuar(new Object[]{this.pacientes.getCedula(), this.pacientes.getNombreCompleto(),
                    this.pacientes.getFecha(), this.pacientes.getCorreo(), this.pacientes.getTelefono()});
                return true;
            }
            if (!"".equals(frmpacientes.getTxtcedula().getText()) && !"".equals(frmpacientes.getTxtnombre().getText())) {
                pacientes = new Pacientes(frmpacientes.getTxtcedula().getText(), frmpacientes.getTxtnombre().getText(),
                        frmpacientes.getTxtfecha().getText(), frmpacientes.getTxtTelefono().getText(), frmpacientes.getTxtcorreo().getText());
                BD bd = new BD("INSERT INTO pacientes VALUES (?,?,?,?,?)");
                bd.ejectuar(new Object[]{this.pacientes.getCedula(), this.pacientes.getNombreCompleto(),
                    this.pacientes.getFecha(), this.pacientes.getCorreo(), this.pacientes.getTelefono()});
                return true;
            }
        }

        return false;

    }

    public String buscarpaciente() {
        pacientes = new Pacientes(frmpacientes.getTxtBuscar().getText());
        BD bd = new BD("SELECT * FROM pacientes WHERE Cedula=?");
        bd.ejectuar(new Object[]{pacientes.getCedula()});
        try {
            while (bd.sentencia.getResultSet().next()) {
                return bd.sentencia.getResultSet().getString(1) + "|" + bd.sentencia.getResultSet().getString(2) + "|"
                        + bd.sentencia.getResultSet().getString(3) + "|"
                        + bd.sentencia.getResultSet().getString(4) + "|" + bd.sentencia.getResultSet().getString(5);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorPacientes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String edad() {
        return pacientes.calcularedad(frmpacientes.getTxtfecha().getText());
    }

    public boolean eliminar() {
        if (!"".equals(frmpacientes.getTxtBuscar().getText())) {
            pacientes = new Pacientes(frmpacientes.getTxtBuscar().getText());
            BD bd = new BD("DELETE FROM pacientes WHERE Cedula=?");
            bd.ejectuar(new Object[]{pacientes.getCedula()});
            return true;
        }
        return false;
    }
}
