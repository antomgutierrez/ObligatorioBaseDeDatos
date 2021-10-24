/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL;

import java.sql.Date;
import java.time.LocalDateTime;

/**
 *
 * @author Administrador
 */
public class FormatterService {
    
    public static String formatData(int x) {
        return String.valueOf(x);
    }
    
    public static String formatData(String x) {
        return "'" + x + "'";
    }
    
    public static String formatData(Date x) {
        return "'" + x + "'";
    }
    
    public static String formatData(LocalDateTime x) {
        return "'" + x + "'";
    }
}
