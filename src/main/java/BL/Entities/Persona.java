/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL.Entities;

import BL.Helpers.FormatterService;
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

    private static final String columnas = "CI, Nombre, Apellido, FechaDeNac, Telefono, Departamento, Email, NombreDeUsuario, Contraseña, SaldoUCUCoins";

    public Persona(int ci, String nombre, String apellido, Date fechaDeNacimiento, int telefono, String departamento, String email, String nombreDeUsuario, String contraseña, int saldoUCUCoins) {
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

    public String insertarPersona() {
        String valores = String.format("&s,&s,&s,&s,&s,&s,&s,&s,&s,&s", getCiQuery(), getNombreQuery(), getApellidoQuery(), getFechaDeNacimientoQuery(),
                getTelefonoQuery(), getDepartamentoQuery(), getEmailQuery(), getNombreDeUsuarioQuery(), getContraseñaQuery(), getSaldoUCUCoinsQuery());

        return String.format("INSERT INTO Publicaciones(&s) VALUES(&s)", columnas, valores);
    }

    private String getCiQuery() {
        return FormatterService.formatData(ci);
    }

    private String getNombreQuery() {
        return FormatterService.formatData(nombre);
    }

    private String getApellidoQuery() {
        return FormatterService.formatData(apellido);
    }

    private String getFechaDeNacimientoQuery() {
        return FormatterService.formatData(fechaDeNacimiento);
    }

    private String getTelefonoQuery() {
        return FormatterService.formatData(telefono);
    }

    private String getDepartamentoQuery() {
        return FormatterService.formatData(departamento);
    }

    private String getEmailQuery() {
        return FormatterService.formatData(email);
    }

    private String getNombreDeUsuarioQuery() {
        return FormatterService.formatData(nombreDeUsuario);
    }

    private String getContraseñaQuery() {
        return FormatterService.formatData(contraseña);
    }

    private String getSaldoUCUCoinsQuery() {
        return FormatterService.formatData(saldoUCUCoins);
    }
}
