package ar.com.sourceSistemas.webScrapping.listeners;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ar.com.sourceSistemas.webScrapping.views.SelectFileView;

public class SelectFileViewListener implements ListSelectionListener {
	private SelectFileView selectFileView;
	private JList<String> list;

	public SelectFileViewListener(SelectFileView selectFileView, JList<String> list) {

		this.list = list;
		this.selectFileView = selectFileView;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting() == false) {

			if (list.getSelectedIndex() != -1) {
				// No selection, disable fire button.
				String value = list.getSelectedValue();

				RecoverListener.loadFile(value);
				shutdown();

			} else {
				// Selection, enable the fire button.
			}
		}

	}

	public void shutdown() {

		selectFileView.setVisible(false);
		selectFileView.dispose();

	}

}
