
package tp2;

import java.awt.event.*;
import javax.swing.*;


public class FermetureFenetre extends WindowAdapter{

    private JFrame frame;
    
    public FermetureFenetre(JFrame frame){
        this.frame = frame;
    }
    @Override
    public void windowClosing(WindowEvent e) {
        System.out.println("Fermeture en cours");
       System.exit(0);
    }
    
}
