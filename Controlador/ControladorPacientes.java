/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.BD;
import Modelo.Pacientes;
import Vista.FrmPacientes;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

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

    public boolean guardarPaciente() {
        
        if (!"".equals(frmpacientes.getTxtcedula().getText())) {
            if (!"".equals(frmpacientes.getTxtcedula().getText()) && !"".equals(frmpacientes.getTxtnombre().getText())
                    && !"".equals(frmpacientes.getTxtfecha().getText()) && !"".equals(frmpacientes.getTxtTelefono().getText())
                    && !"".equals(frmpacientes.getTxtcorreo().getText())) {

                String fechas[] = frmpacientes.getTxtfecha().getText().split("/");
                pacientes = new Pacientes(frmpacientes.getTxtcedula().getText(), frmpacientes.getTxtnombre().getText(),
                        frmpacientes.getTxtTelefono().getText(), frmpacientes.getTxtcorreo().getText(), Integer.parseInt(fechas[2]), Integer.parseInt(fechas[1]), Integer.parseInt(fechas[0]));
                this.pacientes.validarFecha(this.pacientes.getAño(), this.pacientes.getMes(), this.pacientes.getDia());
                this.pacientes.ponerMayusculas();
                if (this.pacientes.contarDigitosCedu() == false) {
                    JOptionPane.showMessageDialog(null, "La cedula es invalida");
                    return false;
                }
                if (this.pacientes.contarDigitostel() == false) {
                    JOptionPane.showMessageDialog(null, "El numero es invalido");
                    return false;
                }
                BD bd = new BD("INSERT INTO pacientes VALUES (?,?,?,?,?)");
                bd.ejecutar(new Object[]{this.pacientes.getCedula(), this.pacientes.getNombreCompleto(),
                    this.pacientes.getFecha(), this.pacientes.getCorreo(), this.pacientes.getTelefono()});
                return true;
            }
            
            if (!"".equals(frmpacientes.getTxtcedula().getText()) && !"".equals(frmpacientes.getTxtnombre().getText())
                    && !"".equals(frmpacientes.getTxtfecha().getText()) && !"".equals(frmpacientes.getTxtTelefono().getText())) {
                String fechas[] = frmpacientes.getTxtfecha().getText().split("/");
                pacientes = new Pacientes(frmpacientes.getTxtcedula().getText(), frmpacientes.getTxtnombre().getText(),
                        frmpacientes.getTxtTelefono().getText(), frmpacientes.getTxtcorreo().getText(), Integer.parseInt(fechas[2]), Integer.parseInt(fechas[1]), Integer.parseInt(fechas[0]));
                this.pacientes.validarFecha(this.pacientes.getAño(), this.pacientes.getMes(), this.pacientes.getDia()); 
                this.pacientes.ponerMayusculas();
                if (this.pacientes.contarDigitosCedu() == false) {
                    JOptionPane.showMessageDialog(null, "La cedula es invalida");
                    return false;
                }
                
                if (this.pacientes.contarDigitostel() == false) {
                    JOptionPane.showMessageDialog(null, "El numero es invalido");
                    return false;
                }
                BD bd = new BD("INSERT INTO pacientes VALUES (?,?,?,?,?)");
                bd.ejecutar(new Object[]{this.pacientes.getCedula(), this.pacientes.getNombreCompleto(),
                    this.pacientes.getFecha(), this.pacientes.getCorreo(), this.pacientes.getTelefono()});
                return true;
            }
        }
        if (!"".equals(frmpacientes.getTxtcedula().getText()) && !"".equals(frmpacientes.getTxtnombre().getText())
                && !"".equals(frmpacientes.getTxtfecha().getText())) {
            String fechas[] = frmpacientes.getTxtfecha().getText().split("/");
            pacientes = new Pacientes(frmpacientes.getTxtcedula().getText(), frmpacientes.getTxtnombre().getText(),
                    frmpacientes.getTxtTelefono().getText(), frmpacientes.getTxtcorreo().getText(), Integer.parseInt(fechas[2]), Integer.parseInt(fechas[1]), Integer.parseInt(fechas[0]));
            this.pacientes.validarFecha(this.pacientes.getAño(), this.pacientes.getMes(), this.pacientes.getDia());
            this.pacientes.ponerMayusculas();
            if (this.pacientes.contarDigitosCedu() == false) {
                JOptionPane.showMessageDialog(null, "La cedula es invalida");
                return false;
            }
            BD bd = new BD("INSERT INTO pacientes VALUES (?,?,?,?,?)");
            bd.ejecutar(new Object[]{this.pacientes.getCedula(), this.pacientes.getNombreCompleto(),
                this.pacientes.getFecha(), this.pacientes.getCorreo(), this.pacientes.getTelefono()});
            return true;
        }
        if (!"".equals(frmpacientes.getTxtcedula().getText()) && !"".equals(frmpacientes.getTxtnombre().getText())) {
            String fechas[] = frmpacientes.getTxtfecha().getText().split("/");
            pacientes = new Pacientes(frmpacientes.getTxtcedula().getText(), frmpacientes.getTxtnombre().getText(),
                    frmpacientes.getTxtTelefono().getText(), frmpacientes.getTxtcorreo().getText(), Integer.parseInt(fechas[2]), Integer.parseInt(fechas[1]), Integer.parseInt(fechas[0]));
            this.pacientes.validarFecha(this.pacientes.getAño(), this.pacientes.getMes(), this.pacientes.getDia());
            this.pacientes.ponerMayusculas();
            if (this.pacientes.contarDigitosCedu() == false) {
                JOptionPane.showMessageDialog(null, "La cedula es invalida");
                return false;
            }
            BD bd = new BD("INSERT INTO pacientes VALUES (?,?,?,?,?)");
            bd.ejecutar(new Object[]{this.pacientes.getCedula(), this.pacientes.getNombreCompleto(),
                this.pacientes.getFecha(), this.pacientes.getCorreo(), this.pacientes.getTelefono()});
            return true;
        }

        return false;

    }

    public void buscarpaciente() {
        if (!"".equals(frmpacientes.getTxtBuscar().getText())) {
            pacientes = new Pacientes();
            pacientes.setCedula(frmpacientes.getTxtBuscar().getText());
            BD bd = new BD("SELECT *  FROM `pacientes` WHERE Cedula=?");
            bd.ejecutar(new Object[]{pacientes.getCedula()});
            DefaultTableModel modelo = (DefaultTableModel) frmpacientes.getTablaPacientes().getModel();
            modelo.addRow(bd.getObject());
        }

    }

    public String edad() {
        return pacientes.calcularedad(frmpacientes.getTxtfecha().getText());
    }

    public boolean eliminar() {
        if (!"".equals(frmpacientes.getTxtBuscar().getText())) {
            pacientes = new Pacientes();
            pacientes.setCedula(frmpacientes.getTxtBuscar().getText());
            BD bd = new BD("DELETE FROM pacientes WHERE Cedula=?");
            bd.ejecutar(new Object[]{pacientes.getCedula()});
            return true;
        }
        return false;
    }

    public boolean actualizarPaciente() {
        if (!"".equals(frmpacientes.getTxtcedula().getText())) {
            if (!"".equals(frmpacientes.getTxtcedula().getText()) && !"".equals(frmpacientes.getTxtnombre().getText())
                    && !"".equals(frmpacientes.getTxtfecha().getText()) && !"".equals(frmpacientes.getTxtTelefono().getText())
                    && !"".equals(frmpacientes.getTxtcorreo().getText())) {
                String fechas[] = frmpacientes.getTxtfecha().getText().split("/");
                pacientes = new Pacientes(frmpacientes.getTxtcedula().getText(), frmpacientes.getTxtnombre().getText(),
                        frmpacientes.getTxtTelefono().getText(), frmpacientes.getTxtcorreo().getText(), Integer.parseInt(fechas[2]), Integer.parseInt(fechas[1]), Integer.parseInt(fechas[0]));
                this.pacientes.validarFecha(this.pacientes.getAño(), this.pacientes.getMes(), this.pacientes.getDia());
                this.pacientes.ponerMayusculas();
                if (this.pacientes.contarDigitosCedu() == false) {
                    JOptionPane.showMessageDialog(null, "La cedula es invalida");
                    return false;
                }
                if (this.pacientes.contarDigitostel() == false) {
                    JOptionPane.showMessageDialog(null, "El numero es invalido");
                    return false;
                }
                BD bd = new BD("UPDATE `pacientes` SET `Nombre`=?,`Fecha`=?,`Correo`=?,`Telefono`=? WHERE Cedula=?");
                bd.ejecutar(new Object[]{this.pacientes.getNombreCompleto(),
                    this.pacientes.getFecha(), this.pacientes.getCorreo(), this.pacientes.getTelefono(), this.pacientes.getCedula()});
                return true;
            }
            if (!"".equals(frmpacientes.getTxtcedula().getText()) && !"".equals(frmpacientes.getTxtnombre().getText())
                    && !"".equals(frmpacientes.getTxtfecha().getText()) && !"".equals(frmpacientes.getTxtTelefono().getText())) {
                String fechas[] = frmpacientes.getTxtfecha().getText().split("/");
                pacientes = new Pacientes(frmpacientes.getTxtcedula().getText(), frmpacientes.getTxtnombre().getText(),
                        frmpacientes.getTxtTelefono().getText(), frmpacientes.getTxtcorreo().getText(), Integer.parseInt(fechas[2]), Integer.parseInt(fechas[1]), Integer.parseInt(fechas[0]));
                this.pacientes.validarFecha(this.pacientes.getAño(), this.pacientes.getMes(), this.pacientes.getDia());
                this.pacientes.ponerMayusculas();
                if (this.pacientes.contarDigitosCedu() == false) {
                    JOptionPane.showMessageDialog(null, "La cedula es invalida");
                    return false;
                }
                if (this.pacientes.contarDigitostel() == false) {
                    JOptionPane.showMessageDialog(null, "El numero es invalido");
                    return false;
                }
                BD bd = new BD("UPDATE `pacientes` SET `Nombre`=?,`Fecha`=?,`Correo`=?,`Telefono`=? WHERE Cedula=?");
                bd.ejecutar(new Object[]{this.pacientes.getNombreCompleto(),
                    this.pacientes.getFecha(), this.pacientes.getCorreo(), this.pacientes.getTelefono()});
                return true;
            }
            if (!"".equals(frmpacientes.getTxtcedula().getText()) && !"".equals(frmpacientes.getTxtnombre().getText())
                    && !"".equals(frmpacientes.getTxtfecha().getText())) {
                String fechas[] = frmpacientes.getTxtfecha().getText().split("/");
                pacientes = new Pacientes(frmpacientes.getTxtcedula().getText(), frmpacientes.getTxtnombre().getText(),
                        frmpacientes.getTxtTelefono().getText(), frmpacientes.getTxtcorreo().getText(), Integer.parseInt(fechas[2]), Integer.parseInt(fechas[1]), Integer.parseInt(fechas[0]));
                this.pacientes.validarFecha(this.pacientes.getAño(), this.pacientes.getMes(), this.pacientes.getDia());
                this.pacientes.ponerMayusculas();
                if (this.pacientes.contarDigitosCedu() == false) {
                    JOptionPane.showMessageDialog(null, "La cedula es invalida");
                    return false;
                }
                BD bd = new BD("UPDATE `pacientes` SET `Nombre`=?,`Fecha`=?,`Correo`=?,`Telefono`=? WHERE Cedula=?");
                bd.ejecutar(new Object[]{this.pacientes.getNombreCompleto(),
                    this.pacientes.getFecha(), this.pacientes.getCorreo(), this.pacientes.getTelefono()});
                return true;
            }
            if (!"".equals(frmpacientes.getTxtcedula().getText()) && !"".equals(frmpacientes.getTxtnombre().getText())) {
                String fechas[] = frmpacientes.getTxtfecha().getText().split("/");
                pacientes = new Pacientes(frmpacientes.getTxtcedula().getText(), frmpacientes.getTxtnombre().getText(),
                        frmpacientes.getTxtTelefono().getText(), frmpacientes.getTxtcorreo().getText(), Integer.parseInt(fechas[2]), Integer.parseInt(fechas[1]), Integer.parseInt(fechas[0]));
                this.pacientes.validarFecha(this.pacientes.getAño(), this.pacientes.getMes(), this.pacientes.getDia());
                this.pacientes.ponerMayusculas();
                if (this.pacientes.contarDigitosCedu() == false) {
                    JOptionPane.showMessageDialog(null, "La cedula es invalida");
                    return false;
                }
                BD bd = new BD("UPDATE `pacientes` SET `Nombre`=?,`Fecha`=?,`Correo`=?,`Telefono`=? WHERE Cedula=?");
                bd.ejecutar(new Object[]{this.pacientes.getNombreCompleto(),
                    this.pacientes.getFecha(), this.pacientes.getCorreo(), this.pacientes.getTelefono()});
                return true;
            }
        }

        return false;

    }
}
