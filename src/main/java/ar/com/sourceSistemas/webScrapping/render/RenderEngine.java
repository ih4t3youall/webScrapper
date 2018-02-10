package ar.com.sourceSistemas.webScrapping.render;

import javax.swing.JOptionPane;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import ar.com.sourceSistemas.webScrapping.SystemContext;
import ar.com.sourceSistemas.webScrapping.domain.HtmlGetter;
import ar.com.sourceSistemas.webScrapping.views.DebugView;
import ar.com.sourceSistemas.webScrapping.views.MainView;
import ar.com.sourceSistemas.webScrapping.views.RenderSelection;

public class RenderEngine {

	public static boolean firstTime = true;

	public RenderEngine(HtmlGetter htmlGetter) {

		Document document = htmlGetter.getHtml();
		SystemContext.setDocument(document);
		MainView.setText(document.toString());

	}

	public static void getAttr(String selector) {
		if (firstTime) {
			JOptionPane.showMessageDialog(null, "you cant do this the first time!");
		} else {
			MainView.clearText();
			DebugView.setText("Produces Strings");
			SystemContext.getElements().stream().forEach((element) -> {

				String selected = element.attr(selector);

				DebugView.setText(selected);
				MainView.appendText(selected);
			});
		}
	}

	public static void getSelector(String selector) {
		if (firstTime) {
			Elements elements = SystemContext.getDocument().select(selector);
			MainView.setText(elements.toString());
			SystemContext.setElements(elements);
			DebugView.setText("Produces elements");
			firstTime = false;
		}
	}

	public static void getById(String selector) {

		if (firstTime) {
			JOptionPane.showMessageDialog(null, "you cant do this the first time!");
		} else {
			DebugView.setText("Produce elemnts");
			Elements elementsAux = new Elements();
			SystemContext.getElements().stream().forEach((element) -> {
				Element selected = element.getElementById(selector);
				elementsAux.add(selected);
				DebugView.setText(selected.toString());
				MainView.appendText(selected.toString());

			}

			);

			DebugView.setText("Old elements overriden.");
			SystemContext.setElements(elementsAux);

		}
	}

	public static void renderDocument(String selector) {
		String selection = RenderSelection.getSelectedSelector();

		switch (selection) {
		case "attr":
			String attr = SystemContext.getDocument().attr(selector);
			MainView.clearText();
			DebugView.setText("renderDocument attr: " + attr);
			DebugView.setText("No System Context variable modified.");
			MainView.setText(attr);
			break;
		case "getById":
			Element elementById = SystemContext.getDocument().getElementById(selector);
			SystemContext.setElement(elementById);
			DebugView.setText("SystemContext.Element has been modified");
			DebugView.setText("Document getById: " + elementById);
			MainView.clearText();
			MainView.setText(elementById.toString());
			break;
		case "selector":
			Elements select = SystemContext.getDocument().select(selector);
			MainView.clearText();
			SystemContext.setElements(select);
			DebugView.setText("Document selector.");
			DebugView.setText("SystemContext elements has been modified.");
			select.stream().forEach((element) -> {

				MainView.appendText(element.toString());

			});

			break;
		}

	}

	public static void renderElements(String selector) {
		String selection = RenderSelection.getSelectedSelector();
		switch (selection) {
		case "attr":

			MainView.clearText();
			DebugView.setText("elements attr");
			DebugView.setText("No SystemContext variable has been modified.");
			SystemContext.getElements().stream().forEach((element) -> {

				String attr = element.attr(selector);

				MainView.appendText(attr);

			});
			break;
		case "getById":
			Elements elements = SystemContext.getElements();
			MainView.clearText();
			Elements elementsAux = new Elements();
			DebugView.setText("Elements getById");
			DebugView.setText("SystemContext elements has been modified");

			elements.stream().forEach((element) -> {

				Element elementById = element.getElementById("selector");
				MainView.appendText(elementById.toString());
				elementsAux.add(elementById);

			});

			JOptionPane.showMessageDialog(null, "Elements where saved !");
			SystemContext.setElements(elementsAux);
			break;
		case "selector":
			Elements select = SystemContext.getDocument().select(selector);
			MainView.clearText();
			SystemContext.setElements(select);
			DebugView.setText("Elements selector");
			DebugView.setText("Elements has been modified");
			select.stream().forEach((element) -> {

				MainView.appendText(element.toString());

			});

			break;
		}
	}

	public static void renderElement(String selector) {
		String selection = RenderSelection.getSelectedSelector();
		switch (selection) {
		case "attr":
			SystemContext.getElement().attr(selector);
			break;
		case "getById":
			SystemContext.getElement().getElementById(selector);
			break;
		case "selector":
			SystemContext.getElement().select(selector);
			break;
		}

	}

}
