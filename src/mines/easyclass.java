/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mines;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class easyclass extends JFrame {
    
   private JButton newgamme = new JButton("new game");
    
   public easyclass()
   {
       init();
   }
    private void init()
    {
        this.setBounds(50, 50, 400, 300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container con = this.getContentPane();
        
        newgamme.setBounds(100, 100, 45, 45);
        
        con.add(newgamme);
        
    }
}
