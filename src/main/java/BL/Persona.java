/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL;

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
    
    public String insertarPersona() {
        String valores = String.format("&s,&s,&s,&s,&s,&s,&s,&s,&s,&s", getCiQuery(), getNombreQuery(), getApellidoQuery(), getFechaDeNacimientoQuery(), 
                getTelefonoQuery(), getDepartamentoQuery(), getEmailQuery(), getNombreDeUsuarioQuery(), getContraseñaQuery(), getSaldoUCUCoinsQuery());
        
        return String.format("INSERT INTO Publicaciones(&s) VALUES(&s)", columnas, valores);
    }
    
    public int getCi() {
        return ci;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public Date getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public int getTelefono() {
        return telefono;
    }

    public String getDepartamento() {
        return departamento;
    }

    public String getEmail() {
        return email;
    }

    public String getNombreDeUsuario() {
        return nombreDeUsuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public int getSaldoUCUCoins() {
        return saldoUCUCoins;
    }
    
    public String getCiQuery() {
        return FormatterService.formatData(ci);
    }
    
    public String getNombreQuery() {
        return FormatterService.formatData(nombre);
    }

    public String getApellidoQuery() {
        return FormatterService.formatData(apellido);
    }

    public String getFechaDeNacimientoQuery() {
        return FormatterService.formatData(fechaDeNacimiento);
    }

    public String getTelefonoQuery() {
        return FormatterService.formatData(telefono);
    }

    public String getDepartamentoQuery() {
        return FormatterService.formatData(departamento);
    }

    public String getEmailQuery() {
        return FormatterService.formatData(email);
    }

    public String getNombreDeUsuarioQuery() {
        return FormatterService.formatData(nombreDeUsuario);
    }

    public String getContraseñaQuery() {
        return FormatterService.formatData(contraseña);
    }

    public String getSaldoUCUCoinsQuery() {
        return FormatterService.formatData(saldoUCUCoins);
    }
}
