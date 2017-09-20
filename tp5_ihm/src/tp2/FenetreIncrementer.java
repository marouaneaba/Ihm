/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;

/**
 *
 * @author abk
 */
public class FenetreIncrementer implements ActionListener{
    public ReponseAuClic reponse;
    
    public FenetreIncrementer(){
        reponse = new ReponseAuClic();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("fenetre");
    }
    
    
    
    private class ReponseAuClic implements ActionListener{

    private JLabel label;
    
    public ReponseAuClic(){
        
    }
    
    public ReponseAuClic(JLabel label){
        this.label = label;
        
    }

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("reponse");
        }

    }
    
}
