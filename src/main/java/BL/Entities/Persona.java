/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL.Entities;

import java.sql.Date;

/**
 *
 * @author Administrador
 */
public class Persona {

    private int ci;
    private String nombre;
    private String apellido;
    private Date fechaDeNacimiento;
    private int telefono;
    private String departamento;
    private String email;
    private String nombreDeUsuario;
    private String contraseña;
    private int saldoUCUCoins;
    private boolean emailConfirmed;
    private String nombreDepartamento;

    public Persona(int ci, String nombre, String apellido, Date fechaDeNacimiento, int telefono, String departamento, String email, String nombreDeUsuario, String contraseña, int saldoUCUCoins, boolean emailConfirmado) {
        this.ci = ci;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.telefono = telefono;
        this.departamento = departamento;
        this.email = email;
        this.nombreDeUsuario = nombreDeUsuario;
        this.contraseña = contraseña;
        this.saldoUCUCoins = saldoUCUCoins;
        this.emailConfirmed = emailConfirmado;
    }

    public boolean isEmailConfirmed() {
        return emailConfirmed;
    }

    public void setEmailConfirmed(boolean emailConfirmed) {
        this.emailConfirmed = emailConfirmed;
    }

    public int getCi() {
        return ci;
    }

    public void setCi(int ci) {
        this.ci = ci;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(Date fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombreDeUsuario() {
        return nombreDeUsuario;
    }

    public void setNombreDeUsuario(String nombreDeUsuario) {
        this.nombreDeUsuario = nombreDeUsuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public int getSaldoUCUCoins() {
        return saldoUCUCoins;
    }

    public void setSaldoUCUCoins(int saldoUCUCoins) {
        this.saldoUCUCoins = saldoUCUCoins;
    }

    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }
}
