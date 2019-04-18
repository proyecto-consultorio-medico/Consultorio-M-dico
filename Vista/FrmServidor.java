/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.ControladorConexion;
import Modelo.ArchivosIniL;
import java.awt.Color;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Jose,Marco,Yuliana,Elver
 */
public class FrmServidor extends javax.swing.JInternalFrame {
    private ControladorConexion conexion;

    public JTextField getTxtIP() {
        return txtIP;
    }

    public JTextField getTxtPass() {
        return txtPass;
    }

    public JTextField getTxtUsu() {
        return txtUsu;
    }

    public JTextField getTxtbd() {
        return txtbd;
    }

    public JMenu getTxtRuta() {
        return txtRuta;
    }


    public FrmServidor() {
        initComponents();
              this.getContentPane().setBackground(new Color(85,151,248));
            ArchivosIniL ini= new ArchivosIniL();
            ini.leerArchivo("C:\\Users\\dell\\Desktop\\Consultorio Medico\\Configuracion.ini");
           conexion = new ControladorConexion(this);
           txtIP.setText(ini.getProperties().getProperty("IP","default value"));
           txtPass.setText(ini.getProperties().getProperty("Pass","default value"));
           txtUsu.setText(ini.getProperties().getProperty("Usuario","default value"));
           txtbd.setText(ini.getProperties().getProperty("BD","default value"));
           txtRuta.setText(ini.getProperties().getProperty("ruta","default value"));
           
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu2 = new javax.swing.JMenu();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        txtIP = new javax.swing.JTextField();
        txtbd = new javax.swing.JTextField();
        txtUsu = new javax.swing.JTextField();
        txtPass = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        txtRuta = new javax.swing.JMenu();

        jMenu2.setText("jMenu2");

        setBackground(new java.awt.Color(85, 151, 248));
        setClosable(true);
        setIconifiable(true);
        setTitle("Configuración");

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/565148.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Metro-Shut-Down-Blue-256.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        txtIP.setFont(new java.awt.Font("Lucida Calligraphy", 0, 12)); // NOI18N
        txtIP.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Direccion IP", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Calligraphy", 0, 12))); // NOI18N

        txtbd.setFont(new java.awt.Font("Lucida Calligraphy", 0, 12)); // NOI18N
        txtbd.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Base de Datos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("sansserif", 0, 12))); // NOI18N
        txtbd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtbdActionPerformed(evt);
            }
        });

        txtUsu.setFont(new java.awt.Font("Lucida Calligraphy", 0, 12)); // NOI18N
        txtUsu.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Usuario", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Calligraphy", 0, 12))); // NOI18N

        txtPass.setFont(new java.awt.Font("Lucida Calligraphy", 0, 12)); // NOI18N
        txtPass.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Contraseña", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("sansserif", 0, 12))); // NOI18N
        txtPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPassActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icono-19-283x300.png"))); // NOI18N
        jButton2.setPreferredSize(new java.awt.Dimension(78, 62));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jMenu1.setText("Ruta");
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu1);
        jMenuBar1.add(txtRuta);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtIP, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtbd, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtIP, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtbd, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (conexion.desconectar()==true) {
            FrmConsultorio.estado.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/error-base-datos-wordpress.png")));
            FrmConsultorio.Expendientes.setEnabled(false);
            FrmConsultorio.RegistroUsu.setEnabled(false);
            FrmConsultorio.RegistroPaci.setEnabled(false);
            FrmConsultorio.RegistroCitas.setEnabled(false);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        if (this.conexion.conectar()==true) {
            FrmConsultorio.estado.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/accept-database-icon.png")));
            FrmConsultorio.Expendientes.setEnabled(true);
            FrmConsultorio.RegistroUsu.setEnabled(true);
            FrmConsultorio.RegistroPaci.setEnabled(true);
            FrmConsultorio.RegistroCitas.setEnabled(true);
        }else{
            FrmConsultorio.estado.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/error-base-datos-wordpress.png")));
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtbdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbdActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (conexion.comprobar()==true) {
            FrmConsultorio.estado.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/accept-database-icon.png")));
            FrmConsultorio.Expendientes.setEnabled(true);
            FrmConsultorio.RegistroUsu.setEnabled(true);
            FrmConsultorio.RegistroPaci.setEnabled(true);
            FrmConsultorio.RegistroCitas.setEnabled(true);
            this.dispose();
        }else{
            JOptionPane.showMessageDialog(null, "No hay conexion a la Base de Datos");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPassActionPerformed

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
      JFileChooser buscador= new JFileChooser();
        buscador.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
buscador.setMultiSelectionEnabled(false);
int selecionado=buscador.showOpenDialog(null);
        if (selecionado==JFileChooser.APPROVE_OPTION) {
           txtRuta.setText(buscador.getSelectedFile().getAbsolutePath());
        }
    }//GEN-LAST:event_jMenu1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JTextField txtIP;
    private javax.swing.JTextField txtPass;
    private javax.swing.JMenu txtRuta;
    private javax.swing.JTextField txtUsu;
    private javax.swing.JTextField txtbd;
    // End of variables declaration//GEN-END:variables
}
