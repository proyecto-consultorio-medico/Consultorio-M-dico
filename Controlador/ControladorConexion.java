/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.BD;
import Vista.FrmServidor;

/**
 *
 * @author Jose,Marco,Yuliana,Elver
 */
public class ControladorConexion {

    FrmServidor servidor;

    public ControladorConexion(FrmServidor servidor) {
        this.servidor = servidor;
    }

    public boolean comprobar() {
        BD bd = new BD();
        if (bd.comprobar() == "Encendido") {
            return true;
        }
        return false;
    }

    public boolean conectar() {
       BD bd = new BD(this.servidor.getTxtIP().getText(), this.servidor.getTxtUsu().getText(), this.servidor.getTxtPass().getText(), this.servidor.getTxtbd().getText());
     
       if (bd.encender()==false) {
            return false;
        }
        return true;
    }

    public boolean desconectar() {
        BD bd = new BD();
        return bd.apagarservidor() == true;
    }


}
