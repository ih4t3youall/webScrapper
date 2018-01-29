package ar.com.sourceSistemas.webScrapping;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import org.apache.log4j.Logger;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import ar.com.sourceSistemas.webScrapping.helper.UrlHelper;

public class Inicio {
	public static final String url = "https://www.fabio.com.ar/";
	final static Logger logger = Logger.getLogger(Inicio.class);

	public Inicio() {

		// Compruebo si me da un 200 al hacer la petición
		if (getStatusConnectionCode(url) == 200) {

			// Obtengo el HTML de la web en un objeto Document
			Document document = getHtmlDocument(url);

			// Busco todas las entradas que estan dentro de:
			Elements entradas = document.select("h1");
			entradas.stream().forEach((element) -> {
				String title = element.select("a").attr("title");
				if (title.indexOf("Ruleta") != -1) {
					String link = element.select("a").attr("href");
					link = UrlHelper.formatUrl(link);
					if (UrlHelper.isPageValid(link)) {
						Document ruleta = getHtmlDocument(link);
						Elements balas = ruleta.getElementById("ruleta").select("a");
						balas.stream().forEach((bala) -> {

							String target = bala.attr("href");

							if (target.indexOf("bala.php") != -1) {
								String image = bala.attr("href");
								System.out.println(image);
								try {
									getImages(image);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

							}

						});
					} else
						logger.error("Ocurrio un error");

				} else {
				}

			});

			// Paseo cada una de las entradas
			// for (Element elem : entradas) {
			// String titulo = elem.getElementsByClass("tituloPost").text();
			// String autor = elem.getElementsByClass("autor").toString();
			// String fecha = elem.getElementsByClass("fecha").text();
			//
			// System.out.println(titulo+"\n"+autor+"\n"+fecha+"\n\n");
			//
			// // Con el método "text()" obtengo el contenido que hay dentro de las
			// etiquetas HTML
			// // Con el método "toString()" obtengo todo el HTML con etiquetas incluidas
			// }
			//
		} else
			System.out.println("El Status Code no es OK es: " + getStatusConnectionCode(url));
	}

	public static int getStatusConnectionCode(String url) {

		Response response = null;

		try {
			response = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(100000).ignoreHttpErrors(true).execute();
		} catch (IOException ex) {
			System.out.println("Excepción al obtener el Status Code: " + ex.getMessage());
		}
		return response.statusCode();
	}

	private static void getImages(String src) throws IOException {

		String folder = null;

		// Exctract the name of the image from the src attribute
		int indexname = src.lastIndexOf("/");

		if (indexname == src.length()) {
			src = src.substring(1, indexname);
		}

		indexname = src.lastIndexOf("/");
		String name = src.substring(indexname, src.length());

		System.out.println(name);

		// Open a URL Stream
		URL url = new URL(src);
		InputStream in = url.openStream();

		OutputStream out = new BufferedOutputStream(new FileOutputStream("/Users/lequerica/Desktop/images" + name));

		for (int b; (b = in.read()) != -1;) {
			out.write(b);
		}
		out.close();
		in.close();

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