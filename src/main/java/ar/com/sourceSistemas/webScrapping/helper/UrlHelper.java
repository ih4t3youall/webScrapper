package ar.com.sourceSistemas.webScrapping.helper;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class UrlHelper {
	final static Logger logger = Logger.getLogger(UrlHelper.class);

	public static String formatUrl(String url) {
		logger.info("url inbound: " + url);

		int val = url.indexOf("/");
		while (val == 0) {

			url = url.substring(1);
			val = url.indexOf("/");
		}

		if (url.indexOf("http") < 0) {

			String aux = url;
			url = "http://" + aux;

		}
		logger.info("url outbound: " + url);
		return url;
	}

	public static boolean isPageValid(String url) {
		Response response = null;

		try {
			response = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(100000).ignoreHttpErrors(true).execute();
		} catch (IOException ex) {
			logger.error("Excepción al obtener el Status Code: " + ex.getMessage());
		}
		return response.statusCode() == 200 ? true : false;
	}

	public static Document getHtmlDocument(String url) {

		Document doc = null;
		try {
			doc = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(100000).get();
		} catch (IOException ex) {
			System.out.println("Excepción al obtener el HTML de la página" + ex.getMessage());
		}
		return doc;
	}
}
