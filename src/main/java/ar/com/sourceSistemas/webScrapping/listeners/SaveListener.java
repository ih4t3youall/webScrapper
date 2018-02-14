package ar.com.sourceSistemas.webScrapping.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import ar.com.sourceSistemas.webScrapping.presistence.Persistence;

public class SaveListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		Persistence.forlderExists();
		String fileName = JOptionPane.showInputDialog(null, "insert a file name.");
		Persistence.persist(fileName);

	}

}
