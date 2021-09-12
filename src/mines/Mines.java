
package mines;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;


class block 
{
    
    protected  boolean mineflag;
    protected  boolean pressed;
    protected int minecounter;
    protected JButton icon;
    public block()
    {
    icon=new JButton();
     this.mineflag = false;
     this.minecounter=0;
     this.pressed=false;

     icon.addMouseListener(new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent me) {
        }

        @Override
        public void mousePressed(MouseEvent me) {
            if(SwingUtilities.isLeftMouseButton(me))
            { 
            if(!mineflag)
            {
                icon.setBackground(Color.white);
                if(minecounter!=0)
                icon.setText(""+minecounter);
                
                icon.setEnabled(false);
               
                
            }
            }
            if(SwingUtilities.isRightMouseButton(me))
            {
            if(!(icon.getBackground().equals(Color.WHITE)))
            { if(!(icon.getBackground().equals(Color.RED)))
               {
                   icon.setBackground(Color.RED);
               }
              else
                   icon.setBackground(null);
                   
            }      
            }
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




public class Mines {

    public static void main(String[] args) {
        // TODO code application logic here
       starter x =new starter();
       x.setVisible(true);

    }
  
}
