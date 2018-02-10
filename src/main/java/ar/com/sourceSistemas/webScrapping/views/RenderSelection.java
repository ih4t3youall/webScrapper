package ar.com.sourceSistemas.webScrapping.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import ar.com.sourceSistemas.webScrapping.presistence.Persistence;
import ar.com.sourceSistemas.webScrapping.presistence.Record;
import ar.com.sourceSistemas.webScrapping.render.RenderEngine;

public class RenderSelection extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -215650951250132289L;
	private JTextField textField;
	private static JRadioButton attr;
	private static JRadioButton getById;
	private static JRadioButton comboSelector;
	private JRadioButton comboDocument;
	private JRadioButton comboElement;
	private JRadioButton comboElements;
	private JButton button;

	public RenderSelection() {
		initialize();

		JPanel panel = new JPanel();
		GroupLayout layout = new GroupLayout(panel);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(textField)
						.addComponent(attr).addComponent(getById).addComponent(comboSelector))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(comboDocument)
						.addComponent(comboElements).addComponent(comboElement))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(button)));
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(textField))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(attr)
						.addComponent(comboDocument))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(getById)
						.addComponent(comboElements))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(comboSelector)
						.addComponent(comboElement))
				.addComponent(button));
		panel.setLayout(layout);
		this.add(panel);

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String selector = textField.getText();
				Record record;
				switch (whosSelectedObject()) {
				case "Document":
					record = new Record("Document", selector);
					Persistence.addRecord(record);
					RenderEngine.renderDocument(selector);
					break;
				case "elements":
					record = new Record("elements", selector);
					Persistence.addRecord(record);
					RenderEngine.renderElements(selector);
					break;
				case "element":
					record = new Record("element", selector);
					Persistence.addRecord(record);
					RenderEngine.renderElement(selector);
					break;
				}
				;
			}
		});

	}

	private void initialize() {
		setSize(300, 300);
		setLocation(300, 300);
		setVisible(true);
		textField = new JTextField();
		attr = new JRadioButton("attr");
		getById = new JRadioButton("getById");
		comboSelector = new JRadioButton("selector");
		comboDocument = new JRadioButton("Document");
		comboElements = new JRadioButton("elements");
		comboElement = new JRadioButton("element");
		button = new JButton("submit!");
	}

	public String whosSelectedObject() {
		if (comboDocument.isSelected())
			return "Document";
		if (comboElement.isSelected())
			return "element";
		if (comboElements.isSelected())
			return "elements";
		return "";

	}

	public static String getSelectedSelector() {

		if (attr.isSelected())
			return "attr";
		if (getById.isSelected())
			return "getById";
		if (comboSelector.isSelected())
			return "selector";
		return "";

	}

}
