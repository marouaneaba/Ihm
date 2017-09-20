/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2;


import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import static java.awt.event.MouseEvent.BUTTON1;
import java.util.*;
import javax.swing.*;

public class ArdoiseMagique extends Applet  {
    
    int xd,yd;
    
    
    
    public void init(){
        int button = 9;
        
        addMouseListener(new MouseAdapter() {
            
            public void mousePressed(MouseEvent e){
                int button =  e.getButton();
                xd = e.getX();
                yd = e.getY();
                if(button == 3){
                    repaint();

                }
            }
        });
        
        addMouseMotionListener(new MouseMotionAdapter(){
            public void mouseDragged(MouseEvent e){
                    int x = e.getX();
                    int y = e.getY();
                    Graphics g = getGraphics();
                    g.drawLine(xd, yd, x, y);
                    xd = x; yd = y;
                    g.dispose();
                }
        });
    }
    public void paint(Graphics  g){
        super.paint(g); 
        int largeur = getSize().width;
        int hauteur = getSize().height;
        
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, largeur, hauteur);
        
    }
    
    public Insets getInsets() {
        Insets normal = super.getInsets();
        
            return new Insets(normal.top + 10, normal.left + 10,
        normal.bottom + 10, normal.right + 10);
    }
    
    
    
    
}
