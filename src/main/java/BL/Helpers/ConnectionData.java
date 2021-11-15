/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL.Helpers;

/**
 *
 * @author Administrador
 */
public class ConnectionData {

    public static String getDB_USER() {
        return DB_USER;
    }

    public static void setDB_USER(String DB_USER) {
        ConnectionData.DB_USER = DB_USER;
    }

    public static String getDB_PASSWORD() {
        return DB_PASSWORD;
    }

    public static void setDB_PASSWORD(String DB_PASSWORD) {
        ConnectionData.DB_PASSWORD = DB_PASSWORD;
    }

    public static String getDB_ADDRESS() {
        return DB_ADDRESS;
    }

    public static void setDB_ADDRESS(String DB_ADDRESS) {
        ConnectionData.DB_ADDRESS = DB_ADDRESS;
    }

    public static String getDB_PORT() {
        return DB_PORT;
    }

    public static void setDB_PORT(String DB_PORT) {
        ConnectionData.DB_PORT = DB_PORT;
    }

    public static String getDB_NAME() {
        return DB_NAME;
    }

    public static void setDB_NAME(String DB_NAME) {
        ConnectionData.DB_NAME = DB_NAME;
    }

    public static String getEMAIL() {
        return EMAIL;
    }

    public static void setEMAIL(String EMAIL) {
        ConnectionData.EMAIL = EMAIL;
    }

    public static String getEMAIL_PASSWORD() {
        return EMAIL_PASSWORD;
    }

    public static void setEMAIL_PASSWORD(String EMAIL_PASSWORD) {
        ConnectionData.EMAIL_PASSWORD = EMAIL_PASSWORD;
    }
    
    // Database connection utils
    public static String DB_USER = "diego";
    public static String DB_PASSWORD = "diego";
    public static String DB_ADDRESS = "192.168.56.2";
    public static String DB_PORT = "5432";
    public static String DB_NAME = "proyectoBBDD2";

    // Email service utils
    public static String EMAIL = "ucutrueque@gmail.com";
    public static String EMAIL_PASSWORD = "basededatos2021";
    
    
}
