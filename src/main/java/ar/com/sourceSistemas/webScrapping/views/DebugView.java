package ar.com.sourceSistemas.webScrapping.views;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import ar.com.sourceSistemas.webScrapping.listeners.SaveListener;
import ar.com.sourceSistemas.webScrapping.presistence.Persistence;

public class DebugView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2196229163672091623L;
	private static JTextArea debugArea;
	private static String debugText;
	private static JButton clear;
	private static JButton browser;
	private static JButton save;
	private static JButton recover;
	private static boolean enabled;

	public DebugView() {

		setSize(200, 200);
		setLocation(200, 200);
		initialize();
		setVisible(true);

	}

	public void initialize() {

		debugArea = new JTextArea();
		JScrollPane scroll = new JScrollPane(debugArea);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		clear = new JButton("Clear");
		browser = new JButton("Open in browser");
		save = new JButton("Save");
		recover = new JButton("Recover");
		this.setLayout(new BorderLayout());
		this.add(scroll, BorderLayout.CENTER);
		this.add(clear, BorderLayout.PAGE_END);
		this.add(browser, BorderLayout.PAGE_START);
		this.add(save, BorderLayout.LINE_START);
		this.add(recover, BorderLayout.LINE_END);
		enabled = true;
		debugText = "";

		clear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				debugArea.setText("");

			}
		});

		SaveListener saveListener = new SaveListener();
		save.addActionListener(saveListener);

		recover.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Persistence.recover();

			}
		});

		browser.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String data = MainView.getTextArea().getText();
				StringTokenizer st = new StringTokenizer(data, "\n");
				while (st.hasMoreTokens()) {

					String token = st.nextToken();
					Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
					if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
						try {
							desktop.browse(new URI(token));

						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}

				}

			}
		});

	}

	public static void setText(String text) {

		if (enabled) {

			debugText = text + "\n";
			debugArea.setText(debugText);

		}

	}

	public static void appendText(String text) {
		debugArea.setText(debugArea.getText() + text + "\n");
	}

	public static void clearText() {

		debugArea.setText("");

	}
}
