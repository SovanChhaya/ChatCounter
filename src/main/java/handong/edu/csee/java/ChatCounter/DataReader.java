package handong.edu.csee.java.ChatCounter;// Package name for ChatCounter

import java.io.File;// Import file library
import java.util.ArrayList;// import ArrayList library

// Read get data class 
public class DataReader {

	public static void main(String[] args) {

		DataReader myReader = new DataReader();// instantiate DataReader Class 
		DataReaderForTXT readTXT = new DataReaderForTXT();
		DataReaderForCSV readCSV = new DataReaderForCSV();

		myReader.getData(args[0]);// Run getData Method


	}

	// method as arrayList
	public ArrayList<String> getData(String strDir){

		//(1) getDirectory
		File myDir = getDirectory(strDir);

		//(2) get File
		File[] file = getListOfFileFromDirectory(myDir);

		//(3)Read file
		ArrayList<String> messages = readFile(file);

		return messages;// return messages
	}

	// method File 
	private File getDirectory(String strDir) {

		File myDirectory = new File(strDir);
		return myDirectory;// return the object 

	}

	private File[] getListOfFileFromDirectory(File dataDir) {
		return dataDir.listFiles();// return listFile 

	}

	private ArrayList<String> readFile(File[] files){

		ArrayList<String> message = new ArrayList<String>();//instantiated
		DataReaderForTXT readTXT = new DataReaderForTXT();// instantiate DataReaderTXT class
		DataReaderForCSV readCSV = new DataReaderForCSV();// instantiate DataReaderCSV class


		for(File file : files) {// loop check file .txt & .csv
			if(file.toString().contains("txt")) {
				readTXT.aMessages(readCSV.recieveMessages());// pass recieveMessages from txt file 
				readTXT.aTime(readCSV.recieveTime());
				readTXT.readFileTXT(file);
				message.addAll(readTXT.recieveName());


			}
			else if(file.toString().contains("csv")) {
				readCSV.aMessages(readTXT.recieveMessages());
				readCSV.readFiles(file);
				message.addAll(readCSV.recieveName());
			}
		}
		return message;// return message
	}



}
