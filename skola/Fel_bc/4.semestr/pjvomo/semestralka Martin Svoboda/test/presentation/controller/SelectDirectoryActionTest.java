package presentation.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Component;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JTextField;

import org.junit.Test;

public class SelectDirectoryActionTest {
	
	protected boolean onlyDirs = false;
	protected boolean acceptAll = false;

	@Test
	public void testActionPerformed() {
		SelectDirectoryAction sda = new SelectDirectoryAction(new JTextField("sdsdf")) {
			@Override
			protected JFileChooser getJFileChooser() {
				return new JFileChooser() {
					private static final long serialVersionUID = -750942408994312528L;

					@Override
					public int showOpenDialog(Component parent) throws HeadlessException {
						return JFileChooser.APPROVE_OPTION;
					}
					
					@Override
					public void setFileSelectionMode(int mode) {
						if (mode == JFileChooser.DIRECTORIES_ONLY) {
							onlyDirs = true;
						}
					}
					
					@Override
					public void setAcceptAllFileFilterUsed(boolean b) {
						if (b == false) {
							acceptAll = true;
						}
					}
					
					@Override
					public File getSelectedFile() {
						return new File("cesta k souboru");
					}
				};
			}
		};
		
		sda.actionPerformed(new ActionEvent(new Object(), 10, "some"));
		
		assertEquals(sda.textField.getText(), new File("cesta k souboru").toString());
		assertTrue(onlyDirs);
		assertTrue(acceptAll);
	}
}
