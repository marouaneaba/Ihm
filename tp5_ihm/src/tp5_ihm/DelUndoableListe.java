package tp5_ihm;

import javax.swing.DefaultListModel;
import javax.swing.undo.AbstractUndoableEdit;

public class DelUndoableListe extends AbstractUndoableEdit{

	private String chaine;
	private int index;
	private DefaultListModel list;
	private String ch;
	
	
	public DelUndoableListe(String chaine,int index,DefaultListModel list){
		super();
		this.chaine = chaine;
		this.index = index;
		this.list = list;
	}
	
	public void undo(){
		super.undo();
		list.add(index,chaine);
		ch = super.getUndoPresentationName();
	}
	
	public void redo(){
		super.redo();
		list.removeElementAt(index);
		ch = super.getRedoPresentationName();
	}
	
	public String getPresentationName(){
		String res ="";
		res = ch;
		ch ="";
		return res;
	}
}
