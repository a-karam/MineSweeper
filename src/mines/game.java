/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mines;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.math.*;


public class game extends JFrame {
    
   private  block[][] blocks ;
   private JPanel bottom;
   private JPanel space;
   private JLabel timelabel;
   private JLabel clock;
   private JLabel minecount;
   private int level;
   private String name;
   private int minenum;
   private int n ;
   private int indr;
   private java.util.Timer tt;
   
   public game(int l ,String name)
   {this.level=l;
   this.name=name;
       init();
   }
   ////////////////////////////////////
    private void init()
    {
        this.setBounds(0, 0, 1000, 700);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container con = this.getContentPane();
        space = new JPanel();
        bottom =new JPanel();
        timelabel = new JLabel("Time : ");
        clock  = new JLabel("0");
        tt=new java.util.Timer();
        int indr = 0;
        
        
        
        
        ///////////////// popup
        
        
       minecount = new JLabel("");
       minecount.setPreferredSize(new Dimension(300, 30));
        timer();
        ////////////////////////
         switch (level)
        {  case 1:
             n=7;
             minecount.setText(" |  Number of mines : 5");
            break;
           case 2:
             n=10;
             minecount.setText(" |  Number of mines : 20");
             break;
           case 3:
             n=20;
             minecount.setText(" |  Number of mines : 50");
             break;
        }
        con.add(space);
        con.add(bottom,BorderLayout.SOUTH);
        space.setLayout(new GridLayout(n,n));

        ///////////////////Intialize blocks
        
        blocks = new block[n][n];
        for(int i =0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            { blocks[i][j]= new block();
                space.add(blocks[i][j].icon);}
        }
        
        setmines();
        
        for(int i =0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            { 
                boolean bum=blocks[i][j].mineflag;
                int x = i; int y =j;
                
                blocks[i][j].icon.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
            }

            @Override
            public void mousePressed(MouseEvent me) {
                
                
                if(SwingUtilities.isLeftMouseButton(me))
            { 
             if(bum)
                {
                gamelost();
                
                }
             else
             {
                 gamewon(x,y);
             }

            }
//                if(SwingUtilities.isRightMouseButton(me))
//                {
//                    minecounterreduce();
//                }
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
            }

            @Override
            public void mouseExited(MouseEvent me) {
            }
        });
                
            }
        }
        
        
       for(int i =0;i<n;i++)
        {  for(int j =0 ;j<n;j++)
        {checker(i,j);
       // blocks[i][j].icon.setText(""+blocks[i][j].minecounter);// check+
        }
        }
        ////////////////// TIMER
        bottom.add(timelabel);
        bottom.add(clock);
        bottom.add(minecount);
        
        
    }
   ///////////////////// MINES SETTER
    public void setmines()
    {
        
        switch (level)
        {  case 1:
             minenum=5;
             break;
           case 2:
             minenum=15;
             break;
           case 3:
             minenum=50;
             break;
        }
        final int[] ints = new Random().ints(0,n*n).distinct().limit(minenum).toArray();
        for(int i =0;i<minenum;i++)
        {
            blocks[ints[i]/n][ints[i]%n].mineflag=true;
        }
//         for(int i =0;i<n;i++)
//        {
//            for(int j=0;j<n;j++)
//            {if( blocks[i][j].mineflag)
//                blocks[i][j].icon.setBackground(Color.yellow);
//            }
//        }
    }
    /////////// MINES COUNTER
    public void checker(int x,int y)
    { 
        if(x>0&&y>0&&blocks[x-1][y-1].mineflag)//1
            (blocks[x][y].minecounter)++;
        
        if(x>0&&blocks[x-1][y].mineflag)//2
            (blocks[x][y].minecounter)++;
        
        if(x>0&&y<(n-1)&&blocks[x-1][y+1].mineflag)//3
            (blocks[x][y].minecounter)++;
        
        if(y>0&&blocks[x][y-1].mineflag)//4
            (blocks[x][y].minecounter)++;
        
        if(y<(n-1)&&blocks[x][y+1].mineflag)//5
            (blocks[x][y].minecounter)++;
        
        if(x<(n-1)&&y>0&&blocks[x+1][y-1].mineflag)//6
            (blocks[x][y].minecounter)++;
        
        if(x<(n-1)&&blocks[x+1][y].mineflag)//7
            (blocks[x][y].minecounter)++;
        
        if(x<(n-1)&&y<(n-1)&&blocks[x+1][y+1].mineflag)//8
            (blocks[x][y].minecounter)++;
    }
    /////////////////////////clock
    private int time ;  
    public void timer()
    {
         
        tt.schedule(new TimerTask() {
            @Override
            public void run() {
               time = Integer.parseInt(clock.getText());
               
               time++;
                clock.setText(( time+"" ));
            }
        },0,1000);
                
    }
//////////////////////////// Winning method
public void gamewon(int x ,int y)
{
if (!(blocks[x][y].pressed))
{         indr++;
blocks[x][y].pressed=true;
}
        
    if ((level==1&&indr==44)||(level==2&&indr==85)||(level==3&&indr==350))
    {
        
 ////////////////////////////// SCORE SAVER       
         ArrayList<String> str = new ArrayList<String>();
         String Temp = new String();
         ArrayList<String> names = new ArrayList<String>();
        FileReader fr;
           BufferedReader br;
        try {
            fr = new FileReader("scores.txt");
            br= new BufferedReader(fr) ;
            
            while((Temp=br.readLine())!=null)
            {   str.add(Temp);
                names.add(br.readLine());
            }

             br.close();
        } catch (IOException ex) {
            
        }
////////////////////////////////////////
        
          FileWriter fw;
           PrintWriter pw;
        try {
            fw = new FileWriter("scores.txt");
            pw= new PrintWriter(fw) ;
                    for(int j=0;j<str.size();j++)
                    {
                        pw.println(str.get(j));
                        pw.println(names.get(j));
                    }
             pw.println(clock.getText());
             pw.println(name);
            
             pw.close();
        } catch (IOException ex) {
        }
///////////////////////////////////////////////////////
        GameWin win = new GameWin(Integer.parseInt(clock.getText()),name);
         win.setVisible(true);
         tt.cancel();
                  this.setVisible(false);

    }

    if (blocks[x][y].minecounter==0)
    {
        if(x>0&&y>0&&!(blocks[x-1][y-1].pressed))//1
        {blocks[x-1][y-1].icon.setEnabled(false);
        blocks[x-1][y-1].icon.setBackground(Color.WHITE);
        if(blocks[x-1][y-1].minecounter!=0)
        { blocks[x-1][y-1].icon.setText(""+blocks[x-1][y-1].minecounter);
        
        }
         gamewon(x-1,y-1);   
        
        }
        if(x>0&&!(blocks[x-1][y].pressed))//2
        { 
            blocks[x-1][y].icon.setEnabled(false);
        blocks[x-1][y].icon.setBackground(Color.WHITE);
        if(blocks[x-1][y].minecounter!=0)
         blocks[x-1][y].icon.setText(""+blocks[x-1][y].minecounter);
         gamewon(x-1,y);
        }
        if(x>0&&y<(n-1)&&!(blocks[x-1][y+1].pressed))//3
        {  blocks[x-1][y+1].icon.setEnabled(false);
        blocks[x-1][y+1].icon.setBackground(Color.WHITE);
        if(blocks[x-1][y+1].minecounter!=0)
         blocks[x-1][y+1].icon.setText(""+blocks[x-1][y+1].minecounter);
         gamewon(x-1,y+1);
        }
        if(y>0&&!(blocks[x][y-1].pressed))//4
        {   blocks[x][y-1].icon.setEnabled(false);
        blocks[x][y-1].icon.setBackground(Color.WHITE);
        if(blocks[x][y-1].minecounter!=0)
         blocks[x][y-1].icon.setText(""+blocks[x][y-1].minecounter);
         gamewon(x,y-1);
        }
        if(y<(n-1)&&!(blocks[x][y+1].pressed))//5
        {   blocks[x][y+1].icon.setEnabled(false);
        blocks[x][y+1].icon.setBackground(Color.WHITE);
        if(blocks[x][y+1].minecounter!=0)
         blocks[x][y+1].icon.setText(""+blocks[x][y+1].minecounter);
         gamewon(x,y+1);
        }
        if(x<(n-1)&&y>0&&!(blocks[x+1][y-1].pressed))//6
        {    blocks[x+1][y-1].icon.setEnabled(false);
        blocks[x+1][y-1].icon.setBackground(Color.WHITE);
        if(blocks[x+1][y-1].minecounter!=0)
        {blocks[x+1][y-1].icon.setText(""+blocks[x+1][y-1].minecounter);}
         gamewon(x+1,y-1);
        }
        if(x<(n-1)&&!(blocks[x+1][y].pressed))//7
        {   blocks[x+1][y].icon.setEnabled(false);
        blocks[x+1][y].icon.setBackground(Color.WHITE);
        if(blocks[x+1][y].minecounter!=0)
         blocks[x+1][y].icon.setText(""+blocks[x+1][y].minecounter);
         gamewon(x+1,y);
        }
        if(x<(n-1)&&y<(n-1)&&!(blocks[x+1][y+1].pressed))
        {  blocks[x+1][y+1].icon.setEnabled(false);
            blocks[x+1][y+1].icon.setBackground(Color.WHITE);
         if(blocks[x+1][y+1].minecounter!=0)
         blocks[x+1][y+1].icon.setText(""+blocks[x+1][y+1].minecounter);
         gamewon(x+1,y+1);
        }  
     }
}
public void gamelost()
{
     
                gameend cc = new gameend(name);
                cc.setVisible(true);
                this.setVisible(false);
}

//public void minecounterreduce()
//{
//    int x = Integer.parseInt(minecount.getText());
//    
//    x--;
//    minecount.setText(""+x);
//}
   

}
