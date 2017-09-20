package tp5_ihm;
import java.awt.event.*;
import java.util.*;
import java.util.Map;
import java.util.Vector;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.undo.*;

public class UndoGUI {
	private DefaultListModel listModel;
	private JTextField text;
	private JList list;
	/* manager c'est notre pile sur la quel on vas enregistrer l'ensemble des modification */
	private UndoManager manager = new UndoManager();
	
	JToolBar toolBar;
	JButton boutonSupprimer;
	JButton boutonAnnuler;
	JButton boutonRefaire;
	
	public UndoGUI() {
		// JFrame
		JFrame fen = new JFrame("Undo/Redo");
		//historiques = new Vector<historique>();
		fen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		// Liste
		listModel = new DefaultListModel();
		listModel.addElement("Universite Lille 1");
		listModel.addElement("Master informatique");
		listModel.addElement("IHM");
		list = new JList(listModel);
		JScrollPane listScroller = new JScrollPane(list);
		text = new JTextField();
		text.setDragEnabled(true);
		list.setDragEnabled(true);
		JButton boutonAjouter = new JButton("Ajouter");
		boutonAjouter.addActionListener(new ajouterIte());
		
		
		
		// Menu
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Edition");
		menuBar.add(menu);
		JMenuItem menuItem = new JMenuItem("Supprimer");
		menu.add(menuItem);
		menuItem = new JMenuItem("Annuler");
		menu.add(menuItem);
		menuItem = new JMenuItem("Refaire");
		menu.add(menuItem);
		
		// ToolBar
		toolBar = new JToolBar("Barre d'outils");
		boutonSupprimer = new JButton("Supprimer");
		boutonAnnuler = new JButton("Annuler");
		boutonRefaire = new JButton("Refaire");
		
		boutonSupprimer.addActionListener(new supprimerIte());
		boutonAnnuler.addActionListener(new annulation());
		boutonRefaire.addActionListener(new Refairation());
		
		
		toolBar.add(boutonSupprimer);
		toolBar.add(boutonAnnuler);
		toolBar.add(boutonRefaire);
		
		fen.setJMenuBar(menuBar);
		fen.add(toolBar);
		fen.getContentPane().setLayout(new BoxLayout(fen.getContentPane(),BoxLayout.Y_AXIS));
		fen.getContentPane().add(listScroller);
		fen.getContentPane().add(text);
		fen.getContentPane().add(boutonAjouter);
		fen.pack();
		fen.setVisible(true);		
	}
	
	public void updateUndoRedoButons(){
		if(!manager.canUndo()){
			boutonRefaire.setEnabled(false);
			
		}else {
			boutonRefaire.setEnabled(true);
			boutonRefaire.setToolTipText(manager.getPresentationName());
		}
		
		
		if(!manager.canRedo()){
			boutonAnnuler.setEnabled(false);
		}else {
			boutonAnnuler.setEnabled(true);
			boutonAnnuler.setToolTipText(manager.getPresentationName());
		}
		
		
	}
	
	// ajouter un item
	private class ajouterIte implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if (text.getText().length() > 0) {
				/* vas ajouter dans la pile un ensemble des objet déja modifier */
				
				listModel.addElement(text.getText());
				manager.addEdit(new AddUndoableListe(text.getText(),listModel.size()-1,listModel));
				text.setText("");
				updateUndoRedoButons();
			} 
		}
	}
	// supprimer un item
	private class supprimerIte implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
				int index = list.getSelectedIndex();
				
			    if (index != -1) {
			    	manager.addEdit(new DelUndoableListe((String)listModel.get(index),index,listModel));
			    	listModel.remove(index);
			    	updateUndoRedoButons();
			    }
		} 
	}
	// annulation
	
	private class Refairation implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(manager.canUndo())
				manager.undo();
			updateUndoRedoButons();
		} 
	}
	private class annulation implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(manager.canRedo())
				manager.redo();
			updateUndoRedoButons();
		} 
	}
	
	
	public static void main(String[] args) {
	    //Schedule a job for the event-dispatching thread:
	    //creating and showing this application's GUI.
	    javax.swing.SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
			new UndoGUI();
		    }
		});
	}

}