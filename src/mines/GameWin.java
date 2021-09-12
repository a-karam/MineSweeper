package mines;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.math.*;

public class GameWin  extends JFrame{

protected JLabel messege ;
private JLabel score;
    private JButton exit ;
    private JButton newgame;
    private JPanel bot;
    private int time;
    private String oldname;

    
    public GameWin(int t,String oldname)
    {
    
    this.time = t;
    this.oldname=oldname;
    score= new JLabel("Your time : " +time);
     Container c = this.getContentPane();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(200, 100);
        this.setAlwaysOnTop(true);
        c.add(score);
        messege = new JLabel("YOU WIN");
        exit = new JButton("Exit");
        exit.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
System.exit(0);        }
    });
        newgame = new JButton("New Game");
        bot=new JPanel();
       newgame.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
             starter s = new starter(oldname);
                s.setVisible(true);
                e2fl();
        }
    });
        
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
