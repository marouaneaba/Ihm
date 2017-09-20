package tp5_ihm;


import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.TransferHandler;
import javax.swing.event.UndoableEditEvent;
import javax.swing.undo.CompoundEdit;
import javax.swing.undo.UndoManager;


public class TransfertHandlerListe extends TransferHandler {
	int insertedindex = 0;
	int selectedindex = 0;

	JComponent sourceList;
	public UndoManager manager;

	TransfertHandlerListe(UndoManager manager) {
		this.manager = manager;
	}

	public int getSourceActions(JComponent c) {
		sourceList = c;
		return COPY_OR_MOVE;
	}

	public Transferable createTransferable(JComponent c) {
		DefaultListModel listModel = (DefaultListModel) ((JList)c).getModel();
		selectedindex = ((JList)c).getSelectedIndex();
		return new StringSelection((String)listModel.getElementAt(selectedindex));
	}

	public void exportDone(JComponent c, Transferable t, int action) {
		if (action == MOVE) {
			DefaultListModel listModel = (DefaultListModel) ((JList)c).getModel();
			listModel.remove(selectedindex);
		}
	}

	public boolean canImport(TransferSupport info) {
		// on importe uniquement des chaines de caracteres
		if (!info.isDataFlavorSupported(DataFlavor.stringFlavor)) {
			return false;
		}
		return true;
	}

	public boolean importData(TransferHandler.TransferSupport info) {
		if (!info.isDrop()) {
			return false;
		}

		// Verifie qu'on a une String flavor
		if (!info.isDataFlavorSupported(DataFlavor.stringFlavor)) {
			return false;
		}

		// Recupere la liste
		JList list = ((JList)info.getComponent());
		DefaultListModel listModel = (DefaultListModel) list.getModel();

		// Recupere la chaine de caracteres a transferer
		Transferable t = info.getTransferable();
		String data;
		try {
			data = (String)t.getTransferData(DataFlavor.stringFlavor);
		} 
		catch (Exception e) { return false; }

		insertedindex = list.getDropLocation().getIndex();

		listModel.insertElementAt(data, insertedindex);

		return true;
	}

}
