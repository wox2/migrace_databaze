package presentation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JTextField;

public class SelectDirectoryAction implements ActionListener {
	
	/**
	 * Instance layoutu 
	 */
	protected JTextField textField;
	
	/**
	 * Konstruktor musi obsahovat instanci layoutu 
	 * @param rootTF
	 */
	public SelectDirectoryAction(JTextField textField) {
		this.textField = textField;
	}
	
	/**
	 * Vraci instanci JFileChooser
	 * @return
	 */
	protected JFileChooser getJFileChooser() {
		return new JFileChooser();
	}
	
	/**
	 * Zpracovava akci na vyber adresare
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		//Create a file chooser
		JFileChooser fc = getJFileChooser();
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fc.setAcceptAllFileFilterUsed(false);
		
		if (fc.showOpenDialog(textField) == JFileChooser.APPROVE_OPTION) {
			textField.setText(fc.getSelectedFile().toString());
		}
	}
}
