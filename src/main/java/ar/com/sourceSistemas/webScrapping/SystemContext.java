package ar.com.sourceSistemas.webScrapping;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SystemContext {

	private static Document document;
	private static Elements elements;
	private static Element element;

	public static Document getDocument() {
		return document;
	}

	public static void setDocument(Document document1) {
		document = document1;
	}

	public static void setElements(Elements fewElements) {
		elements = fewElements;

	}

	public static Elements getElements() {
		return elements;
	}

	public static Element getElement() {
		return element;
	}

	public static void setElement(Element anElement) {
		element = anElement;
	}

}
