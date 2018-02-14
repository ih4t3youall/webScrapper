package ar.com.sourceSistemas.webScrapping.views;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;

import org.apache.log4j.Logger;

import ar.com.sourceSistemas.webScrapping.listeners.ConnectToHostListener;

public class MainView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1708547142500740340L;
	// Where the GUI is created:
	private JMenuBar menuBar;
	private JMenuItem connectToHost;
	private JMenu menu;
	private String urlToConnect;
	private static JTextArea jtextArea;

	final static Logger logger = Logger.getLogger(MainView.class);

	public static JTextArea getTextArea() {

		return jtextArea;

	}

	public MainView() {

		setSize(700, 400);
		setLocation(200, 200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		init();

		createMenu();
		setVisible(true);
	}

	private void init() {
		logger.info("initializing components");
		urlToConnect = "";

		jtextArea = new JTextArea();
		JScrollPane scroll = new JScrollPane(jtextArea);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		// init panels
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(scroll, BorderLayout.CENTER);
		this.add(panel);
		this.repaint();
		this.repaint(1);
		new DebugView();
		new RenderSelection();

	}

	public void createMenu() {
		logger.info("creating menu");
		// Create the menu bar.
		menuBar = new JMenuBar();

		// Build the first menu.
		menu = new JMenu("Connect");
		menu.getAccessibleContext().setAccessibleDescription("Main utilities menu");
		menuBar.add(menu);

		// a group of JMenuItems
		connectToHost = new JMenuItem("Connect to host", KeyEvent.VK_T);
		connectToHost.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
		connectToHost.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
		menu.add(connectToHost);

		JMenuItem menuitem = new JMenuItem("scrap Menu");
		menuitem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new RenderSelection();

			}
		});
		menu.add(menuitem);
		JMenuItem debugMenu = new JMenuItem("debug Menu");
		debugMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new DebugView();

			}
		});
		menu.add(debugMenu);
		// Listeners
		connectToHost.addActionListener(new ConnectToHostListener(urlToConnect));

		setJMenuBar(menuBar);
	}

	public static void setText(String text) {

		jtextArea.setText(text);

	}

	public static void appendText(String text) {
		jtextArea.setText(jtextArea.getText() + text + "\n");
	}

	public static void clearText() {

		jtextArea.setText("");

	}
}
