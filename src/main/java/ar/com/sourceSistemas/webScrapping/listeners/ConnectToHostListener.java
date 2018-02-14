package ar.com.sourceSistemas.webScrapping.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import ar.com.sourceSistemas.webScrapping.render.RenderEngine;

public class ConnectToHostListener implements ActionListener {

	final static Logger logger = Logger.getLogger(ConnectToHostListener.class);
	private String urlToConnect;

	public ConnectToHostListener(String urlToConnect) {
		this.urlToConnect = urlToConnect;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		/*
		 * String host = JOptionPane.showInputDialog("Url");
		 * logger.info("host to connect: " + host); host = UrlHelper.formatUrl(host); if
		 * (UrlHelper.isPageValid(host)) { urlToConnect = host;
		 * 
		 * logger.info("valid URL: " + urlToConnect);
		 * 
		 * Record record = new Record("Document", urlToConnect);
		 * Persistence.addRecord(record); new RenderEngine(new
		 * HtmlGetter(urlToConnect)); } else {
		 * 
		 * logger.error("the url is invalid: " + host); urlToConnect = null;
		 * 
		 * }
		 */
		String host = JOptionPane.showInputDialog("Url");
		RenderEngine.connectToHost(host);

	}

}
