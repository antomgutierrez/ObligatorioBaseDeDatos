/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import BL.Helpers.DatabaseService;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.SwingConstants;

/**
 *
 * @author Administrador
 */
public class PanelLogin extends javax.swing.JPanel {

    /**
     * Creates new form PanelLogin
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
        passwordText = new javax.swing.JPasswordField();
        usernameText = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnLogin = new javax.swing.JButton();
        btnRegister = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Dubai", 0, 18)); // NOI18N
        jLabel1.setText("Contraseña");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 420, -1, -1));

        passwordText.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        add(passwordText, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 420, 225, -1));

        usernameText.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        add(usernameText, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 380, 225, -1));

        jLabel3.setFont(new java.awt.Font("Dubai", 0, 18)); // NOI18N
        jLabel3.setText("Usuario");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 380, -1, -1));

        btnLogin.setBackground(new java.awt.Color(0, 153, 51));
        btnLogin.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnLogin.setText("Ingresar");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        add(btnLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 490, 164, -1));

        btnRegister.setBackground(new java.awt.Color(0, 204, 204));
        btnRegister.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnRegister.setText("Registrarse");
        btnRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegisterActionPerformed(evt);
            }
        });
        add(btnRegister, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 490, 165, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 0, 0));
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 460, 340, 20));
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        // TODO add your handling code here:
        
        boolean is_valid_user = false;
        boolean is_connected_with_db = this.db.connectToDB("diego", "diego");
        if (is_connected_with_db) {
            try {
                is_valid_user = this.db.login(usernameText.getText(), passwordText.getText());
            } catch (SQLException ex) {
                Logger.getLogger(PanelLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (is_valid_user) {
                /**
                 * crear la persona y mandarla a panelhome
                 */
                System.out.println("usuario valido");
                this.setVisible(false);
                PanelHome panel = new PanelHome();
                this.frame.getContentPane().add(panel);
                this.frame.setVisible(true);
            } else {
                jLabel4.setHorizontalAlignment(SwingConstants.CENTER);
                jLabel4.setText("Usuario o contraseña inválidos");
            }
        } else {
            jLabel4.setHorizontalAlignment(SwingConstants.CENTER);
            jLabel4.setText("Imposible conectar a la Base de Datos");
        }
        this.db.closeConnectionDB();


    }//GEN-LAST:event_btnLoginActionPerformed

    private void btnRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisterActionPerformed
        this.setVisible(false);
        boolean is_connected_with_db = this.db.connectToDB("diego", "diego");
        if (is_connected_with_db) {
            PanelRegister panel = new PanelRegister(this.frame, this.db);
            this.frame.setSize(1075, 609);
            this.frame.getContentPane().add(panel);
            this.frame.setVisible(true);
        } else {
            jLabel4.setHorizontalAlignment(SwingConstants.CENTER);
            jLabel4.setText("Imposible conectar a la Base de Datos");
        }
    }//GEN-LAST:event_btnRegisterActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnRegister;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPasswordField passwordText;
    private javax.swing.JTextField usernameText;
    // End of variables declaration//GEN-END:variables
}
