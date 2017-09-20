/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2;


import java.awt.Dimension;
import javax.swing.*;
import java.awt.event.*;

public class IhmTp4 extends JPanel implements ActionListener{

    public JButton button = new JButton("Button 1");
    public JButton button2 = new JButton("Button 2");
    public JButton button3 = new JButton("Button 3");
    
    public JLabel label = new JLabel("0");
    public Box box1,box2,box3,box4;
    int nbr = 0;
    
    
    public IhmTp4(){
    	
        super(true);

    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    box1 = new Box(BoxLayout.X_AXIS);
    box2 = new Box(BoxLayout.X_AXIS);
    box3 =  new Box(BoxLayout.X_AXIS);
    box4 = new Box(BoxLayout.X_AXIS);
    //box1.add(new JSeparator());
    box1.add(label);
    box2.add(button);
    box3.add(button2);
    box4.add(button3);  
    
//Question 2 et 3 :
    //button.addActionListener(new ReponseAuClic(label));
    
//Question 4 :
    //FenetreIncrementer fen = new FenetreIncrementer();
    // button.addActionListener(fen);
    //button.addActionListener(fen.reponse);
    
//Question 5 :
    // instancié un objet d'une class qui n'a pas de nom,anonyme.
    //dérive de Objet, et implemente l'interface actionPerformed
    // l'instance a été crée en utilisant le constructeur par défault  (constructeur sans parametre).
    /*
    button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nbr++;
                label.setText(new String(""+nbr));
            }
        });
    */
  //Question 7 :
  
    
    button.addActionListener(this);
    button2.addActionListener(this);
    button3.addActionListener(this);
    
  add(box1);
    add(box2);
    add(box3);
    add(box4);
    }
   
    //Question 4 :
    // version 2:
    /*private class FenetreIncrementer implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent e) {
            String tmp = label.getText();
            int nbr = Integer.parseInt(tmp);
            nbr++;
            label.setText(new String(""+nbr));
        }
    }*/
    
    
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("TP-IHM Gerer des évenement");

    //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //Question 6 :
    frame.addWindowListener(new FermetureFenetre(frame));
    //frame.setContentPane(new IhmTp4());
    //Question 8 :
    //ArdoiseMagique a =new ArdoiseMagique();
    //a.init();
    //frame.add(a);
    //Question 9 :
    frame.add(new SelecteurDeCouleur());
    frame.setLocation(400, 150);
    frame.setMinimumSize(new Dimension(200, 100));
    frame.pack();
    frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == button){
            label.setText("button 1");
            System.out.println("click button 1");
        }else if(e.getSource() == button2){
            label.setText("button 2");
            System.out.println("click button 2");
        }else if(e.getSource() == button3){
            label.setText("button 3");
            System.out.println("click button 3");
        }
    }
    
    
    
}
