package ar.com.sourceSistemas.webScrapping.exceptions;

import org.apache.log4j.Logger;

public class InvalidUrlException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6477251276608932484L;

	final static Logger logger = Logger.getLogger(InvalidUrlException.class);

	public InvalidUrlException(String message) {

		logger.error(message);

	}
}
