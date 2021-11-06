/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL.Helpers;

import UI.PanelLogin;
import javax.swing.JFrame;

/**
 *
 * @author DIEGO
 */
public class managerViews {

    public static void viewPanel(javax.swing.JPanel panel, JFrame frame) {
        
        int x = panel.getHeight();
        int y = panel.getWidth();   
        frame.setSize(x, y);
        frame.getContentPane().add(panel);
       
    }
}
