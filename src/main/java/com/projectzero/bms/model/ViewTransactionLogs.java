package com.projectzero.bms.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ViewTransactionLogs {

	public static void viewTransactions()
	{
		readFromFile();
	}
	private static void readFromFile() {
		FileInputStream reader = null;
		try {
			File file = new File("logs//transactions.log");
			if (file.exists()) {
				reader = new FileInputStream(file);
				int i = 0;
				while ((i = reader.read()) != -1) {
					System.out.print((char) i);
				}
			} else {
				System.out.println("File does not exists");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
