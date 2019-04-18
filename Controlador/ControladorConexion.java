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

    private FrmServidor servidor;

    public ControladorConexion(FrmServidor servidor) {
        this.servidor = servidor;
    }

    public boolean comprobar() {
        BD bd = new BD();
        return bd.comprobar() == "Encendido";
    }

    public boolean conectar() {
       BD bd = new BD(this.servidor.getTxtIP().getText(), this.servidor.getTxtUsu().getText(), this.servidor.getTxtPass().getText(),
               this.servidor.getTxtbd().getText());
       bd.setRuta(this.servidor.getTxtRuta().getText());
        return bd.encender() != false;
    }

    public boolean desconectar() {
        BD bd = new BD();
        return bd.apagarservidor() == true;
    }


}
