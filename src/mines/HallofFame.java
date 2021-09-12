
package mines;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.math.*;
import java.io.*;


public class HallofFame extends JFrame {
    
    private JPanel top ;
    private JPanel center;
    private JPanel bottom;
    
    private JButton back;
   private JLabel header ;
   private JLabel[] scores;
   private JLabel[] heros;
   
   
   public HallofFame()
   {
       this.setSize(300,600);
       this.setDefaultCloseOperation(EXIT_ON_CLOSE);
       Container c = this.getContentPane();
       top=new JPanel();
       center=new JPanel();
       bottom=new JPanel();
       back=new JButton("Back");
       header=new JLabel("Hall Of Fame");
       scores =new JLabel[10];
       heros = new JLabel[10];
       
       
       c.add(top,BorderLayout.NORTH);
       c.add(center);
       c.add(bottom,BorderLayout.SOUTH);
       
      top.add(header);
      bottom.add(back);
      back.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent ae) {
               
               backer();
           }
           
           
           
       });
       for(int i=0 ; i<10 ; i++)
       {
           scores[i]= new JLabel();
           heros[i]=new JLabel();
           scores[i].setAlignmentY(CENTER_ALIGNMENT);
           
       }
       center.setLayout(new GridLayout(10,2));
       
       reader();
 
       
   }
   
   public void backer()
   {
       this.setVisible(false);
       starter x=new starter();
       x.setVisible(true);
   }
         
   public void reader()
   {
       
       ArrayList<String> anames = new ArrayList<String>();
       ArrayList<String> ascores = new ArrayList<String>();
       String str;
        FileReader fr;
           BufferedReader br;
        try {
            fr = new FileReader("scores.txt");
            br= new BufferedReader(fr) ;
            
            while((str=br.readLine())!=null)
            {
                anames.add(br.readLine());
                ascores.add(str);
                
               
            }
            
            
             br.close();
        } catch (IOException ex) {
            
        }
        
        ///////////////////////// SOrting
        int[] oldscores = new int[ascores.size()];
         for(int i = 0; i<ascores.size();i++)
        {
            oldscores[i]=Integer.parseInt(ascores.get(i));
        }
        String[] names = new String[anames.size()];
        for(int i = 0; i<anames.size();i++)
        {
            names[i]=anames.get(i);
        }
        
        
        
        int key;
        String skey;
        for(int j =0;j<oldscores.length;j++ )
        { for( int k=oldscores.length-1;k>=j+1;k--)
        {
            if(oldscores[k]<oldscores[k-1])
            {
                key=oldscores[k];
                skey=names[k];
                
                oldscores[k]=oldscores[k-1];
                names[k]=names[k-1];
                
                oldscores[k-1]=key;
                names[k-1]=skey;
            }  
        }
            
        }
     
// Arrays.sort(oldscores);
 String[] newscores = new String[10];
 int k=0;
        for(int j = 0 ;j<oldscores.length;j++)
        {
            if(oldscores[j]!=0&&k!=10)
            {
                scores[k].setText(""+oldscores[j]);
                heros[k].setText(k+1+") "+names[j]+" : ");
                 center.add(heros[k]);
                 center.add(scores[k]);
               
                k++;
            }
        }
         
          
            
     }

        
        
   }
   

  
    

