package ar.com.sourceSistemas.webScrapping.domain;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlGetter {
	private String url;

	final static Logger logger = Logger.getLogger(HtmlGetter.class);

	public HtmlGetter(String url) {
		this.url = url;

	}

	public Document getHtmlDocument(String url) {

		Document doc = null;
		try {
			doc = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(100000).get();
		} catch (IOException ex) {
			System.out.println("Excepción al obtener el HTML de la página" + ex.getMessage());
		}
		return doc;
	}

	public Document getHtml() {

		return getHtmlDocument(this.url);

	}

	public Element getElement(Elements elements) {

		return null;

	}
}
