package ar.com.sourceSistemas.webScrapping.presistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;

import ar.com.sourceSistemas.webScrapping.views.DebugView;
import ar.com.sourceSistemas.webScrapping.views.MainView;

public class Persistence {

	private static List<Record> records = new LinkedList<Record>();
	private static String folderPath = System.getProperty("user.home") + "/" + "webScrapper/";

	public static void addRecord(Record record) {
		records.add(record);

	}

	public static void persist(String fileName) {

		try {
			File file = new File(folderPath + fileName);
			file.createNewFile();
			FileOutputStream f = new FileOutputStream(file);

			ObjectOutputStream o = new ObjectOutputStream(f);

			// Write objects to file
			DebugView.appendText("saving to file");
			o.writeObject(records);

			o.close();
			f.close();
			DebugView.appendText("file saved");

		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Error initializing stream");
		}

	}

	@SuppressWarnings("unchecked")
	public static void recover(String fileName) {
		records.clear();
		FileInputStream fi;
		try {
			fi = new FileInputStream(new File(folderPath + fileName));

			ObjectInputStream oi = new ObjectInputStream(fi);

			// Read objects
			records = (List<Record>) oi.readObject();
			MainView.clearText();

			records.stream().forEach((record) -> {

				DebugView.appendText(record.toString());

			});

			oi.close();
			fi.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static String[] listDirectory() {
		String[] files = new File(folderPath).list();
		String[] filesAux = new String[files.length];
		int counter = 0;
		for (int i = 0; i < files.length; i++) {
			if (files[i].indexOf(".") == -1) {

				filesAux[counter] = files[i];
				counter++;
			}

		}
		return filesAux;

	}

	public static boolean forlderExists() {
		File f = new File(folderPath);
		if (f.exists() && f.isDirectory()) {
			return true;
		} else {
			new File(folderPath).mkdir();
			return false;

		}
	}

	public static List<Record> getRecords() {

		return records;

	}

}
