package ar.com.sourceSistemas.webScrapping.presistence;

import java.io.Serializable;

public class Record implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -111953762388939222L;
	private String element;
	private String action;

	public Record() {
	}

	public Record(String anElement, String anAction) {

		this.element = anElement;
		this.action = anAction;

	}

	public String getElement() {
		return element;
	}

	public void setElement(String element) {
		this.element = element;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	@Override
	public String toString() {
		return "element: " + element + " action: " + action;
	}

}
