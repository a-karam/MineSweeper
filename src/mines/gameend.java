
package mines;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.math.*;


public class gameend extends JFrame{
    protected JLabel messege ;
    private JButton exit ;
    private JButton newgame;
    private JPanel bot;
    private String oldname;

    public gameend(String oldname)
    {
        Container c = this.getContentPane();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(200, 100);
        this.setAlwaysOnTop(true);
        this.oldname=oldname;



        messege = new JLabel("YOU LOST !!");
        c.add(messege);
        exit = new JButton("Exit");
        newgame = new JButton("New Game");
        bot=new JPanel();
        
        exit.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        }
        );
        
        newgame.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                starter s = new starter(oldname);
                s.setVisible(true);
                e2fl();
            }
        }
        );
               ////////
               c.add(messege,BorderLayout.NORTH);
               c.add(bot,BorderLayout.SOUTH);
               bot.add(exit);
               bot.add(newgame);
              
                       
    }
    
    public void e2fl(){
        this.setVisible(false);
    }
    
    
}
