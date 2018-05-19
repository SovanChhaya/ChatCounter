package handong.edu.csee.java.ChatCounter;// Package name for ChatCounter

import java.io.File;// Import file library
import java.util.ArrayList;// import ArrayList library

// Read get data class 
public class DataReader {

	public static void main(String[] args) {

		DataReader myReader = new DataReader();// instantiate DataReader Class 
		
		myReader.getData(null);// Run getData Method

	}

	// method as arrayList
	public ArrayList<String> getData(String strDir){

		//(1) getDirectory
		File myDir = getDirectory(null);

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

		return message;// return message
	}



}
