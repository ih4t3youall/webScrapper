package ar.com.sourceSistemas.webScrapping.views;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import ar.com.sourceSistemas.webScrapping.listeners.SelectFileViewListener;
import ar.com.sourceSistemas.webScrapping.presistence.Persistence;

public class SelectFileView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7680283674503504031L;

	List<JLabel> labels = new LinkedList<JLabel>();
	JList<String> list;

	public SelectFileView() {
		String[] files = Persistence.listDirectory();

		for (int i = 0; i < files.length; i++) {
			JLabel label = new JLabel(files[i]);
			labels.add(label);
		}

		list = new JList<String>(files); // data has type Object[]
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		list.setVisibleRowCount(-1);
		JScrollPane listScroller = new JScrollPane(list);
		listScroller.setPreferredSize(new Dimension(250, 80));

		setLayout(new FlowLayout());
		add(listScroller);
		setSize(400, 400);
		setLocation(200, 200);
		setVisible(true);
		list.addListSelectionListener(new SelectFileViewListener(this, list));
	}

}
