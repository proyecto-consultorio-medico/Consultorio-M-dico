/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.BD;
import Modelo.Pacientes;
import Vista.FrmPacientes;
import java.sql.Date;
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
                    && frmpacientes.getTxtFecha().getDate()!=null && !"".equals(frmpacientes.getTxtTelefono().getText())
                    && !"".equals(frmpacientes.getTxtcorreo().getText())) {
                 BD bd = new BD("INSERT INTO pacientes VALUES (?,?,?,?,?)");
                Date fecha = new Date(frmpacientes.getTxtFecha().getDate().getTime());
                pacientes = new Pacientes(frmpacientes.getTxtcedula().getText(), frmpacientes.getTxtnombre().getText(),
                frmpacientes.getTxtTelefono().getText(), frmpacientes.getTxtcorreo().getText(),fecha);
                this.pacientes.ponerMayusculas();
                if (this.pacientes.contarDigitosCedu() == false) {
                    JOptionPane.showMessageDialog(null, "La cedula es invalida");
                    return false;
                }
                if (this.pacientes.contarDigitostel() == false) {
                    JOptionPane.showMessageDialog(null, "El numero es invalido");
                    return false;
                }
                bd.ejecutar(new Object[]{this.pacientes.getCedula(), this.pacientes.getNombreCompleto(),
                    this.pacientes.getFecha(), this.pacientes.getCorreo(), this.pacientes.getTelefono()});
                return true;
            }
            
            if (!"".equals(frmpacientes.getTxtcedula().getText()) && !"".equals(frmpacientes.getTxtnombre().getText())
                    &&  frmpacientes.getTxtFecha().getDate()!=null && !"".equals(frmpacientes.getTxtTelefono().getText())) {
                       BD bd = new BD("INSERT INTO pacientes VALUES (?,?,?,?,?)");
               Date fecha = new Date(frmpacientes.getTxtFecha().getDate().getTime());
                pacientes = new Pacientes(frmpacientes.getTxtcedula().getText(), frmpacientes.getTxtnombre().getText(),
                  frmpacientes.getTxtTelefono().getText(), frmpacientes.getTxtcorreo().getText(),fecha);
                this.pacientes.ponerMayusculas();
                if (this.pacientes.contarDigitosCedu() == false) {
                    JOptionPane.showMessageDialog(null, "La cedula es invalida");
                    return false;
                }       
                if (this.pacientes.contarDigitostel() == false) {
                    JOptionPane.showMessageDialog(null, "El numero es invalido");
                    return false;
                }
         
                bd.ejecutar(new Object[]{this.pacientes.getCedula(), this.pacientes.getNombreCompleto(),
                    this.pacientes.getFecha(), this.pacientes.getCorreo(), this.pacientes.getTelefono()});
                return true;
            }
        }
        
        if (!"".equals(frmpacientes.getTxtcedula().getText()) && !"".equals(frmpacientes.getTxtnombre().getText())
                && frmpacientes.getTxtFecha().getDate()!=null) {
              BD bd = new BD("INSERT INTO pacientes VALUES (?,?,?,?,?)");
         Date fecha = new Date(frmpacientes.getTxtFecha().getDate().getTime());
            pacientes = new Pacientes(frmpacientes.getTxtcedula().getText(), frmpacientes.getTxtnombre().getText(),
             frmpacientes.getTxtTelefono().getText(), frmpacientes.getTxtcorreo().getText(), fecha);   
            this.pacientes.ponerMayusculas();
            if (this.pacientes.contarDigitosCedu() == false) {
                JOptionPane.showMessageDialog(null, "La cedula es invalida");
                return false;
            }
          
            bd.ejecutar(new Object[]{this.pacientes.getCedula(), this.pacientes.getNombreCompleto(),
                this.pacientes.getFecha(), this.pacientes.getCorreo(), this.pacientes.getTelefono()});
            return true;
        }
        
        if (!"".equals(frmpacientes.getTxtcedula().getText()) && !"".equals(frmpacientes.getTxtnombre().getText())) {
                     BD bd = new BD("INSERT INTO pacientes VALUES (?,?,?,?,?)");
       Date fecha = new Date(frmpacientes.getTxtFecha().getDate().getTime());
            pacientes = new Pacientes(frmpacientes.getTxtcedula().getText(), frmpacientes.getTxtnombre().getText(),
                    frmpacientes.getTxtTelefono().getText(), frmpacientes.getTxtcorreo().getText(),fecha);
            this.pacientes.ponerMayusculas();
            if (this.pacientes.contarDigitosCedu() == false) {
                JOptionPane.showMessageDialog(null, "La cedula es invalida");
                return false;
            }
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
               Date fecha = new Date(frmpacientes.getTxtFecha().getDate().getTime());
        return pacientes.calcularedad(fecha);
    }

    public boolean eliminar() {
        if (!"".equals(frmpacientes.getTxtBuscar().getText())) {
            BD bd = new BD("DELETE FROM pacientes WHERE Cedula=?");
            pacientes = new Pacientes();
            pacientes.setCedula(frmpacientes.getTxtBuscar().getText());   
            bd.ejecutar(new Object[]{pacientes.getCedula()});
            return true;
        }
        return false;
    }

    public boolean actualizarPaciente() {
        if (!"".equals(frmpacientes.getTxtcedula().getText())) {
            if (!"".equals(frmpacientes.getTxtcedula().getText()) && !"".equals(frmpacientes.getTxtnombre().getText())
                    && frmpacientes.getTxtFecha()!=null && !"".equals(frmpacientes.getTxtTelefono().getText())
                    && !"".equals(frmpacientes.getTxtcorreo().getText())) {
                 BD bd = new BD("UPDATE `pacientes` SET `Nombre`=?,`Fecha`=?,`Correo`=?,`Telefono`=? WHERE Cedula=?");
                Date fecha = new Date(frmpacientes.getTxtFecha().getDate().getTime());
                pacientes = new Pacientes(frmpacientes.getTxtcedula().getText(), frmpacientes.getTxtnombre().getText(),
                        frmpacientes.getTxtTelefono().getText(), frmpacientes.getTxtcorreo().getText(),fecha);
                this.pacientes.ponerMayusculas();
                if (this.pacientes.contarDigitosCedu() == false) {
                    JOptionPane.showMessageDialog(null, "La cedula es invalida");
                    return false;
                }
                if (this.pacientes.contarDigitostel() == false) {
                    JOptionPane.showMessageDialog(null, "El numero es invalido");
                    return false;
                }
                bd.ejecutar(new Object[]{this.pacientes.getNombreCompleto(),
                    this.pacientes.getFecha(), this.pacientes.getCorreo(), this.pacientes.getTelefono(), this.pacientes.getCedula()});
                return true;
            }
            
            if (!"".equals(frmpacientes.getTxtcedula().getText()) && !"".equals(frmpacientes.getTxtnombre().getText())
                    && frmpacientes.getTxtFecha().getDate()!=null && !"".equals(frmpacientes.getTxtTelefono().getText())) {
                  BD bd = new BD("UPDATE `pacientes` SET `Nombre`=?,`Fecha`=?,`Correo`=?,`Telefono`=? WHERE Cedula=?");
                Date fecha = new Date(frmpacientes.getTxtFecha().getDate().getTime());
                pacientes = new Pacientes(frmpacientes.getTxtcedula().getText(), frmpacientes.getTxtnombre().getText(),
                        frmpacientes.getTxtTelefono().getText(), frmpacientes.getTxtcorreo().getText(),fecha);
                this.pacientes.ponerMayusculas();
                if (this.pacientes.contarDigitosCedu() == false) {
                    JOptionPane.showMessageDialog(null, "La cedula es invalida");
                    return false;
                }
                if (this.pacientes.contarDigitostel() == false) {
                    JOptionPane.showMessageDialog(null, "El numero es invalido");
                    return false;
                }
                bd.ejecutar(new Object[]{this.pacientes.getNombreCompleto(),
                    this.pacientes.getFecha(), this.pacientes.getCorreo(), this.pacientes.getTelefono()});
                return true;
            }
            
            if (!"".equals(frmpacientes.getTxtcedula().getText()) && !"".equals(frmpacientes.getTxtnombre().getText())
                    &&frmpacientes.getTxtFecha().getDate()!=null) {
                  BD bd = new BD("UPDATE `pacientes` SET `Nombre`=?,`Fecha`=?,`Correo`=?,`Telefono`=? WHERE Cedula=?");
                 Date fecha = new Date(frmpacientes.getTxtFecha().getDate().getTime());
                pacientes = new Pacientes(frmpacientes.getTxtcedula().getText(), frmpacientes.getTxtnombre().getText(),
                        frmpacientes.getTxtTelefono().getText(), frmpacientes.getTxtcorreo().getText(),fecha);
                
                this.pacientes.ponerMayusculas();
                if (this.pacientes.contarDigitosCedu() == false) {
                    JOptionPane.showMessageDialog(null, "La cedula es invalida");
                    return false;
                }
              
                bd.ejecutar(new Object[]{this.pacientes.getNombreCompleto(),
                    this.pacientes.getFecha(), this.pacientes.getCorreo(), this.pacientes.getTelefono()});
                return true;
            }
            
            if (!"".equals(frmpacientes.getTxtcedula().getText()) && !"".equals(frmpacientes.getTxtnombre().getText())) {
                   BD bd = new BD("UPDATE `pacientes` SET `Nombre`=?,`Fecha`=?,`Correo`=?,`Telefono`=? WHERE Cedula=?");
              Date fecha = new Date(frmpacientes.getTxtFecha().getDate().getTime());
                pacientes = new Pacientes(frmpacientes.getTxtcedula().getText(), frmpacientes.getTxtnombre().getText(),
                        frmpacientes.getTxtTelefono().getText(), frmpacientes.getTxtcorreo().getText(),fecha);     
                this.pacientes.ponerMayusculas();
                if (this.pacientes.contarDigitosCedu() == false) {
                    JOptionPane.showMessageDialog(null, "La cedula es invalida");
                    return false;
                }
                bd.ejecutar(new Object[]{this.pacientes.getNombreCompleto(),
                    this.pacientes.getFecha(), this.pacientes.getCorreo(), this.pacientes.getTelefono()});
                return true;
            }
        }
        return false;
    }
}
