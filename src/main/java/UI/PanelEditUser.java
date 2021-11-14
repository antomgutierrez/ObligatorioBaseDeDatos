/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import BL.Entities.Persona;
import BL.Helpers.DatabaseService;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import java.time.Period;
import java.time.ZoneId;
import java.util.Arrays;

/**
 *
 * @author Administrador
 */
public class PanelEditUser extends javax.swing.JPanel {

    /**
     * Creates new form PanelLogin
     *
     * @param frame
     * @param db
     * @param persona
     */
    public PanelEditUser(JFrame frame, DatabaseService db, Persona persona) {
        initComponents();

        this.frame = frame;
        this.db = db;
        this.db.connectToDB();
        this.persona = persona;
        populateComboDepartment();
        initTexts();

    }
    JFrame frame;
    DatabaseService db;
    Persona persona;

    private void resetTexts() {
        nameError.setText("");
        lastNameError.setText("");
        dateError.setText("");
        phoneError.setText("");
        departmentError.setText("");
        ciError.setText("");
        emailError.setText("");
        usernameError.setText("");
        passwordError.setText("");
        repeatPasswordError.setText("");
    }

    private void initTexts() {
        name.setText(persona.getNombre());
        lastName.setText(persona.getApellido());
        jDateChooser.setDate(persona.getFechaDeNacimiento());
        phone.setText(String.valueOf(persona.getTelefono()));
        comboDepartment.setSelectedIndex(Integer.valueOf(persona.getDepartamento()));
        ci.setText(String.valueOf(persona.getCi()));
        ci.setEditable(false);
        email.setText(persona.getEmail());
        username.setText(persona.getNombreDeUsuario());
    }

    public boolean validateData() {
        boolean result = true;
        resetTexts();
        if (!validateName()) {
            nameError.setText("El nombre no puede estar vacio.");
            result = false;
        }

        if (!validateLastName()) {
            lastNameError.setText("El apellido no puede estar vacio.");
            result = false;
        }

        if (!validateDate()) {
            dateError.setText("La fecha ingresada no es correcta");
            result = false;
        } else if (!validateGreaterThan18yo()) {
            dateError.setText("Debe ser mayor de 18 años");
            result = false;
        }

        if (!validatePhone()) {
            phoneError.setText("El telefono no puede estar vacio");
            result = false;
        }

        if (!validateDepartment()) {
            departmentError.setText("Elija un departamento");
            result = false;
        }

        if (!validateCI()) {
            ciError.setText("Cédula invalida");
            result = false;
        }

        if (!validateEmail()) {
            emailError.setText("Email inválido");
            result = false;
        }

        if (!validateUsername()) {
            usernameError.setText("Nombre de usuario invalido");
            result = false;
        }

        if (!validateRepeatPassword()) {
            repeatPasswordError.setText("Las contraseñas deben ser iguales");
            result = false;
        }

        return result;
    }

    public boolean validateName() {
        return !name.getText().isEmpty();
    }

    public boolean validateLastName() {
        return !lastName.getText().isEmpty();
    }

    public boolean validateDate() {
        Date fecha = jDateChooser.getDate();
        return (fecha != null);
    }

    public boolean validateGreaterThan18yo() {
        Date fecha = jDateChooser.getDate();
        LocalDate date = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate today = LocalDate.now();
        Period p = Period.between(date, today);
        return (p.getYears() >= 18);
    }

    public boolean validatePhone() {
        if (!phone.getText().isEmpty()) {
            try {
                Integer.parseInt(phone.getText());
                return true;
            } catch (NumberFormatException e) {
                phoneError.setText("Solo se aceptan numeros");
                return false;
            }
        }
        phoneError.setText("El telefono no puede estar vacio");
        return false;
    }

    public boolean validateDepartment() {
        return comboDepartment.getSelectedIndex() > 0;
    }

    public boolean validateCI() {
        if (!ci.getText().equals(String.valueOf(persona.getCi()))) {
            if (!ci.getText().isEmpty()) {
                try {
                    return this.db.validateCI(ci.getText());
                } catch (SQLException ex) {
                    Logger.getLogger(PanelEditUser.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
            }
        } else {
            return true;
        }

        return false;
    }

    public boolean validateEmail() {
        if (email.getText().isEmpty()) {
            return false;
        } else if (persona.getEmail().equals(email.getText())) {
            return true;
        }
        try {
            return this.db.validateEmail(email.getText());
        } catch (SQLException ex) {
            Logger.getLogger(PanelEditUser.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean validateUsername() {
        if (!username.getText().isEmpty()) {
            if (!username.getText().equals(persona.getNombreDeUsuario())) {
                try {
                    return this.db.validateUserName(username.getText());
                } catch (SQLException ex) {
                    Logger.getLogger(PanelEditUser.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return true;
        }
        usernameError.setText("El nombre de usuario no puede estar vacio");
        return false;
    }

    public boolean validateRepeatPassword() {
        String pass = Arrays.toString(password.getPassword());
        return (pass.equals(Arrays.toString(repeatPassword.getPassword())));
    }

    private void populateComboDepartment() {
        try {

            String[] departments = db.getDepartments();
            comboDepartment.removeAllItems();
            comboDepartment.addItem("Select");
            for (String department : departments) {
                comboDepartment.addItem(department);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PanelEditUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        password = new javax.swing.JPasswordField();
        username = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnActualizar = new javax.swing.JButton();
        usernameError = new javax.swing.JLabel();
        passwordError = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        repeatPassword = new javax.swing.JPasswordField();
        repeatPasswordError = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        nameError = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lastNameError = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        dateError = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        departmentError = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        emailError = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        comboDepartment = new javax.swing.JComboBox<>();
        phoneError = new javax.swing.JLabel();
        ciError = new javax.swing.JLabel();
        email = new javax.swing.JTextField();
        ci = new javax.swing.JTextField();
        phone = new javax.swing.JTextField();
        name = new javax.swing.JTextField();
        lastName = new javax.swing.JTextField();
        jDateChooser = new com.toedter.calendar.JDateChooser();
        SQLerrorText = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(962, 650));

        jLabel1.setFont(new java.awt.Font("Dubai", 0, 18)); // NOI18N
        jLabel1.setText("Contraseña");
        jLabel1.setToolTipText("");

        password.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        username.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Dubai", 0, 18)); // NOI18N
        jLabel3.setText("Nombre de usuario");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setText("Editar usuario");

        btnActualizar.setBackground(new java.awt.Color(51, 153, 0));
        btnActualizar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        usernameError.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        usernameError.setForeground(new java.awt.Color(255, 0, 0));

        passwordError.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        passwordError.setForeground(new java.awt.Color(255, 0, 0));

        btnCancelar.setBackground(new java.awt.Color(0, 51, 204));
        btnCancelar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Dubai", 0, 18)); // NOI18N
        jLabel4.setText("Reperir contraseña");

        repeatPassword.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        repeatPasswordError.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        repeatPasswordError.setForeground(new java.awt.Color(255, 0, 0));

        jLabel5.setFont(new java.awt.Font("Dubai", 0, 18)); // NOI18N
        jLabel5.setText("Nombre");

        nameError.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nameError.setForeground(new java.awt.Color(255, 0, 0));

        jLabel6.setFont(new java.awt.Font("Dubai", 0, 18)); // NOI18N
        jLabel6.setText("Apellido");

        lastNameError.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lastNameError.setForeground(new java.awt.Color(255, 0, 0));

        jLabel7.setFont(new java.awt.Font("Dubai", 0, 18)); // NOI18N
        jLabel7.setText("Fecha de nacimiento");

        dateError.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        dateError.setForeground(new java.awt.Color(255, 0, 0));

        jLabel9.setFont(new java.awt.Font("Dubai", 0, 18)); // NOI18N
        jLabel9.setText("Departamento");

        departmentError.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        departmentError.setForeground(new java.awt.Color(255, 0, 0));

        jLabel10.setFont(new java.awt.Font("Dubai", 0, 18)); // NOI18N
        jLabel10.setText("Email");

        emailError.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        emailError.setForeground(new java.awt.Color(255, 0, 0));

        jLabel14.setFont(new java.awt.Font("Dubai", 0, 18)); // NOI18N
        jLabel14.setText("Telefono");

        jLabel15.setFont(new java.awt.Font("Dubai", 0, 18)); // NOI18N
        jLabel15.setText("CI");

        comboDepartment.setModel(new javax.swing.DefaultComboBoxModel<>());

        phoneError.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        phoneError.setForeground(new java.awt.Color(255, 0, 0));

        ciError.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ciError.setForeground(new java.awt.Color(255, 0, 0));
        ciError.setLabelFor(ci);

        email.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        ci.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        phone.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        phone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phoneActionPerformed(evt);
            }
        });

        name.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        lastName.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        SQLerrorText.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        SQLerrorText.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel14))
                                .addGap(60, 60, 60)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(phone, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(phoneError, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(nameError, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(lastName)
                                            .addComponent(jDateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(dateError, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(lastNameError, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(ci, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addGap(102, 102, 102)
                                        .addComponent(comboDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(repeatPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ciError, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(departmentError, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addGap(400, 400, 400))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(emailError, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(usernameError, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(passwordError, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(repeatPasswordError, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(SQLerrorText, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 535, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nameError, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5)
                                .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lastNameError, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel6)
                                .addComponent(lastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(dateError, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(phoneError, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14)
                        .addComponent(phone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(departmentError, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(comboDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(ciError, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(ci, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(emailError, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(usernameError, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(passwordError, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(repeatPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCancelar)
                            .addComponent(btnActualizar)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(repeatPasswordError, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(SQLerrorText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    private java.sql.Date convert(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        resetTexts();
        boolean isConnectedWithDB = this.db.connectToDB();
        boolean dataValid = validateData();

        if (isConnectedWithDB && dataValid) {
            java.sql.Date sqlDate = convert(jDateChooser.getDate());

            this.persona.setNombre(name.getText());
            this.persona.setApellido(lastName.getText());
            if (!password.getText().isBlank()) {
                this.persona.setContraseña(String.valueOf(password.getText().hashCode()));
            }
            this.persona.setDepartamento(String.format("%s", comboDepartment.getSelectedIndex()));
            this.persona.setEmail(email.getText());
            this.persona.setFechaDeNacimiento(sqlDate);
            this.persona.setTelefono(Integer.valueOf(phone.getText()));
            this.persona.setNombreDeUsuario(username.getText());

            try {
                this.db.updateUser(this.persona);
            } catch (SQLException ex) {
                System.out.println(ex);
            }

            this.db.closeConnectionDB();

        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed

    }//GEN-LAST:event_btnCancelarActionPerformed

    private void phoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_phoneActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel SQLerrorText;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JTextField ci;
    private javax.swing.JLabel ciError;
    private javax.swing.JComboBox<String> comboDepartment;
    private javax.swing.JLabel dateError;
    private javax.swing.JLabel departmentError;
    private javax.swing.JTextField email;
    private javax.swing.JLabel emailError;
    private com.toedter.calendar.JDateChooser jDateChooser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField lastName;
    private javax.swing.JLabel lastNameError;
    private javax.swing.JTextField name;
    private javax.swing.JLabel nameError;
    private javax.swing.JPasswordField password;
    private javax.swing.JLabel passwordError;
    private javax.swing.JTextField phone;
    private javax.swing.JLabel phoneError;
    private javax.swing.JPasswordField repeatPassword;
    private javax.swing.JLabel repeatPasswordError;
    private javax.swing.JTextField username;
    private javax.swing.JLabel usernameError;
    // End of variables declaration//GEN-END:variables
}
