/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL.Helpers;

import java.sql.Date;
import java.time.LocalDateTime;

/**
 *
 * @author Administrador
 */
public class FormatterService {

    public static String formatData(int x) {
        if (x == -1) {
            return "NULL";
        }
        return String.valueOf(x);
    }

    public static String formatData(String x) {
        if (x.isEmpty()) {
            return "NULL";
        }
        return "'" + x + "'";
    }

    public static String formatData(Date x) {
        if (x == null) {
            return "NULL";
        }
        return "'" + x.toString() + "'";
    }

    public static String formatData(LocalDateTime x) {
        if (x == null) {
            return "NULL";
        }
        return "'" + x.toString() + "'";
    }

    public static String formatData(boolean x) {
        return x ? "'true'" : "'false'";
    }


}
