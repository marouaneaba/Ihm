package tp5_ihm;

import javax.swing.DefaultListModel;
import javax.swing.undo.*;

public class AddUndoableListe extends AbstractUndoableEdit {
	
	private String chaine;
	private int id;
	private DefaultListModel list;
	private String ch;
	
	public AddUndoableListe(String chaine,int id,DefaultListModel list){
		this.chaine = chaine;
		this.id = id;
		this.list = list;
	}
	
	public void undo(){
		super.undo();
		list.removeElementAt(id);
		ch =  super.getUndoPresentationName();
	}
	public void redo(){
		super.redo();
		list.add(id,chaine);
		ch = super.getRedoPresentationName();
	}
	
	public String getPresentationName(){
		String res ="";
		res = ch;
		ch ="";
		return res;
	}
}
