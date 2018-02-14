package ar.com.sourceSistemas.webScrapping.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ar.com.sourceSistemas.webScrapping.presistence.Persistence;
import ar.com.sourceSistemas.webScrapping.render.RenderEngine;
import ar.com.sourceSistemas.webScrapping.views.DebugView;
import ar.com.sourceSistemas.webScrapping.views.SelectFileView;

public class RecoverListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {

		new SelectFileView();
	}

	public static void loadFile(String filename) {
		Persistence.recover(filename);
		DebugView.clearText();
		DebugView.appendText("record sended to the engine.");
		RenderEngine.renderRecoveredFile();

	}

}
