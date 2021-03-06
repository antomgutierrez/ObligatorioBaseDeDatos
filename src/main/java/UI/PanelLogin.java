/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import BL.Entities.Persona;
import BL.Helpers.DatabaseService;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingConstants;

/**
 *
 * @author Administrador
 */
public class PanelLogin extends javax.swing.JPanel {

    /**
     * Creates new form PanelLogin
     *
     * @param frame
     * @param db
     */
    public PanelLogin(JFrame frame, DatabaseService db) {
        this.frame = frame;
        this.db = db;
        initComponents();

    }
    JFrame frame;
    DatabaseService db;

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        inputPassword = new javax.swing.JPasswordField();
        inputNombreDeUsuario = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnLogin = new javax.swing.JButton();
        btnRegister = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(800, 600));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Dubai", 0, 18)); // NOI18N
        jLabel1.setText("Contraseña");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 220, -1, -1));

        inputPassword.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        add(inputPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 220, 225, -1));

        inputNombreDeUsuario.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        add(inputNombreDeUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 180, 225, -1));

        jLabel3.setFont(new java.awt.Font("Dubai", 0, 18)); // NOI18N
        jLabel3.setText("Usuario");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 180, -1, -1));

        btnLogin.setBackground(new java.awt.Color(0, 153, 51));
        btnLogin.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnLogin.setText("Ingresar");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        add(btnLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 340, 164, -1));

        btnRegister.setBackground(new java.awt.Color(0, 204, 204));
        btnRegister.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnRegister.setText("Registrarse");
        btnRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegisterActionPerformed(evt);
            }
        });
        add(btnRegister, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 340, 165, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 0, 0));
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 260, 340, 20));

        jButton1.setBackground(new java.awt.Color(255, 204, 51));
        jButton1.setForeground(new java.awt.Color(255, 102, 51));
        jButton1.setText("BBDD");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 90, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        // TODO add your handling code here:

        boolean isValidUser = false;
        boolean isConnectedWithDB = this.db.connectToDB();
        if (isConnectedWithDB) {
            try {
                isValidUser = this.db.login(inputNombreDeUsuario.getText(), String.valueOf(inputPassword.getText().hashCode()));
            } catch (SQLException ex) {
                Logger.getLogger(PanelLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (isValidUser) {
                Persona persona = null;
                try {
                    persona = this.db.getPersona(inputNombreDeUsuario.getText());
                } catch (SQLException ex) {
                    Logger.getLogger(PanelLogin.class.getName()).log(Level.SEVERE, null, ex);
                }

                PanelHome panel = new PanelHome(persona, this.frame, this.db);
                this.frame.setSize(panel.getPreferredSize());
                this.frame.getContentPane().add(panel);
                this.frame.setVisible(true);
                this.frame.remove(this);
            } else {
                jLabel4.setHorizontalAlignment(SwingConstants.CENTER);
                jLabel4.setText("Usuario o contraseña inválidos");
            }
            this.db.closeConnectionDB();
        } else {
            jLabel4.setHorizontalAlignment(SwingConstants.CENTER);
            jLabel4.setText("Imposible conectar a la Base de Datos");
        }

        this.db.closeConnectionDB();

    }//GEN-LAST:event_btnLoginActionPerformed

    private void btnRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisterActionPerformed
        this.setVisible(false);
        boolean isConnectedWithDB = this.db.connectToDB();
        if (isConnectedWithDB) {
            PanelRegister panel = new PanelRegister(this.frame, this.db);
            this.frame.setSize(panel.getPreferredSize());
            this.frame.getContentPane().add(panel);
            this.frame.remove(this);
        } else {
            jLabel4.setHorizontalAlignment(SwingConstants.CENTER);
            jLabel4.setText("Imposible conectar a la Base de Datos");
        }
        this.db.closeConnectionDB();
    }//GEN-LAST:event_btnRegisterActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JFrame frameDialog = new JFrame();
        PanelConfigureBBDD panel = new PanelConfigureBBDD(frameDialog, this.db);
        JDialog dialog = new JDialog(frameDialog, true);
        dialog.setSize(panel.getPreferredSize());
        dialog.setLocationRelativeTo(null);
        dialog.getContentPane().add(panel);
        dialog.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnRegister;
    private javax.swing.JTextField inputNombreDeUsuario;
    private javax.swing.JPasswordField inputPassword;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    // End of variables declaration//GEN-END:variables
}
