/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JColorChooser;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author abk
 */
public class SelecteurDeCouleur extends JPanel {
    
    public JSlider slider1,slider2,slider3;
    public JTextField textField1,textField2,textField3,textField4;
    public JPanel panel1,panel2;
    public Box box1,box2,box3;
    public String Chainecolor,hexaColor;
    public int color1,color2,color3;
    public Color color;
    public Color color_r;
    public JColorChooser chooser;
    int nbr = 0;
    
    
    public SelecteurDeCouleur(){
        //setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    
    
    
        slider1 = new JSlider(0,255);
        slider1.setPreferredSize(new Dimension(250,5));
        slider1.setValue(0);
        slider2 = new JSlider(0,255);
        slider2.setValue(0);
        slider2.setPreferredSize(new Dimension(250,5));
        slider3 = new JSlider(0,255);
        slider3.setValue(0);
        slider3.setPreferredSize(new Dimension(250,5));
        
        textField1 = new JTextField("0");
        textField1.setPreferredSize( new Dimension( 30, 24 ) );
        textField2 = new JTextField("0");
        textField3 = new JTextField("0");
        textField4 = new JTextField("0");
        
        panel1 = new JPanel();
        panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        
        Box b1 = Box.createHorizontalBox();
        b1.add(slider1);
        b1.add(textField1);
        
        Box b2 = Box.createHorizontalBox();
        b2.add(slider2);
        b2.add(textField2);
        
        Box b3 = Box.createHorizontalBox();
        b3.add(slider3);
        b3.add(textField3);
  
        Box b4 = Box.createVerticalBox();
        b4.setSize(2, 2);
        b4.setBackground(Color.red);
        b4.add(b1);
        b4.add(b2);
        b4.add(b3);
        
        panel1.add(b4);
        
        
        Box b1_1 = Box.createVerticalBox();
        textField4.setPreferredSize( new Dimension( 3, 20 ) );
        textField4.setMinimumSize(new Dimension(3,20));
      textField4.setMaximumSize(new Dimension(170,20));
        b1_1.add(textField4);
        b1_1.setPreferredSize(new Dimension(3,20));

        
        Label l = new Label();
        l.setPreferredSize(new Dimension(150, 150));
        l.setBackground(Color.red);
        
        panel2.setLayout(new BorderLayout());
        panel2.add(b1_1,BorderLayout.NORTH);
        panel2.add(l,BorderLayout.SOUTH);
        
        
        
        slider1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                
                textField1.setText(""+slider1.getValue());
                String hexa = Integer.toHexString(color_r.getRGB());
                textField4.setText(hexa.substring(2));
                
            }
        });
        slider2.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                textField2.setText(""+slider2.getValue());
                String hexa = Integer.toHexString(color_r.getRGB());
                textField4.setText(hexa.substring(2));
            }
        });
        slider3.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                textField3.setText(""+slider3.getValue());
                String hexa = Integer.toHexString(color_r.getRGB());
                textField4.setText(hexa.substring(2));
            }
        });
        textField1.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                try{
                color1 = Integer.parseInt(textField1.getText());
                //color2 = Integer.parseInt(textField2.getText());
                //color3 = Integer.parseInt(textField3.getText());
                
                slider1.setValue(Integer.parseInt(textField1.getText()));
                color_r = new Color(color1,color2,color3);
                l.setBackground(color_r);
                
                String hexa = Integer.toHexString(color_r.getRGB());
                textField4.setText(hexa.substring(2));
                
                }catch(IllegalStateException ert  ){
                    System.out.println("slider Error");
                }catch(NumberFormatException er ){
                    System.out.println("error 2 ");
                }catch(java.lang.IllegalArgumentException ee){
                    System.out.println("error 22 ");
                }
            }
        });
        
        textField2.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                try{
                //color1 = Integer.parseInt(textField1.getText());
                color2 = Integer.parseInt(textField2.getText());
                //color3 = Integer.parseInt(textField3.getText());
                
              slider2.setValue(Integer.parseInt(textField2.getText()));
                color_r = new Color(color1,color2,color3);
                l.setBackground(color_r);
                
                String hexa = Integer.toHexString(color_r.getRGB());
                textField4.setText(hexa.substring(2));
                }catch(java.lang.NumberFormatException er){
                    System.out.println("eror format");
                }
            }
        });
        
        textField3.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                try{
                //color1 = Integer.parseInt(textField1.getText());
                //color2 = Integer.parseInt(textField2.getText());
                color3 = Integer.parseInt(textField3.getText());
                
                slider3.setValue(Integer.parseInt(textField3.getText()));
                color_r = new Color(color1,color2,color3);
                l.setBackground(color_r);
                
                String hexa = Integer.toHexString(color_r.getRGB());
                textField4.setText(hexa.substring(2));
                }catch(java.lang.NumberFormatException er){
                    System.out.println("eror format");
                }
            }
        });
        
        textField4.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                String chaine = textField4.getText();
                
               try{
                   
                   color = Color.decode("0x"+chaine);
                   l.setBackground(color);
                   textField1.setText(""+color.getRed());
                   textField2.setText(""+color.getGreen());
                   textField3.setText(""+color.getBlue());
                   color1 = Integer.parseInt(textField1.getText());
                   color2 = Integer.parseInt(textField2.getText());
                   color3 = Integer.parseInt(textField3.getText());
                   slider1.setValue(Integer.parseInt(textField1.getText()));
                   slider2.setValue(Integer.parseInt(textField2.getText()));
                   slider3.setValue(Integer.parseInt(textField3.getText()));
                   
                   
               }catch(NumberFormatException  ex){
                   System.out.println("is not color");
               }
               
               
        }});
        
        add(panel1);
        add(panel2);
        
        
        this.setVisible(true);

        
     
        
    }
}
