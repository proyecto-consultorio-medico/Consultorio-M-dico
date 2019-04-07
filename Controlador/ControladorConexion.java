/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.BD;
import consultorio.medico.FrmServidor;

/**
 *
 * @author kille
 */
public class ControladorConexion {
    FrmServidor servidor;

    public ControladorConexion(FrmServidor servidor) {
        this.servidor = servidor;
    }
    
    public boolean comprobar(){
        BD bd= new BD();
        if (bd.comprobar()=="Encendido") {
            return true;
        }
        return false;
    }
    
    public void conectar(){
     BD bd= new BD(this.servidor.getTxtIP().getText(),this.servidor.getTxtUsu().getText(),this.servidor.getTxtPass().getText());
    bd.conectarBase(bd.getIp(), bd.getUsuario(), bd.getPass());
    }
    
    public boolean desconectar(){
    BD bd= new BD();
        return bd.apagarservidor()==true;
    }
    
    public void cambiar(){
        BD bd = new BD();
        bd.reiniciarBase();
    
    }
}
