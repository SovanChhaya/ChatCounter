
package handong.edu.csee.java.ChatCounter;// package name of ChatCounter


import java.util.*;// import java.util library

import java.io.*;// import java.io library 

/**
 * DataWriter class
 * 
 * @author 
 *
 */

public class DataWriter {// DataWriter class
	private HashMap<String,Integer> countMessages = new HashMap<>(); // HashMap that stores name and message count for each of them
	private ArrayList<String> myNames = new ArrayList<>(); // ArrayList names that stores list of names 

	/**
	 * This is the default constructor 

	 */
	// default constructor
	public DataWriter(){

	}

	/**
	 * This is the constructor 
	 * @param 
	 * @param 
	 */
	// constructor with parameters
	public DataWriter(HashMap<String,Integer> countMessages,ArrayList<String> myNames) {
		this.countMessages = countMessages; // sets the messageCounter as the parameter passed
		this.myNames = myNames; // sets the list of names as the parameter passed
	}

	/**
	 * This is the myFile() method.
	 * @param 
	 */
	// writeFile method
	public void myFile(File file) {

		try {// try block
			BufferedWriter outputStream = new BufferedWriter(
					new OutputStreamWriter(
							new FileOutputStream(file),"UTF-8")); // instance of BufferedWriter and writes it in UTF-8
			outputStream.write("kakao_id,count\n"); // calls BufferedWriter to write file

			for(String myName:myNames) {//loop to go every  name list
				outputStream.write(myName+","+countMessages.get(myName)+"\n"); 
			}
			outputStream.close(); // closes the outputStream
		} 

		catch(IOException e){// catch block for i/o exception
			e.printStackTrace(); // print the error
		}
	}
}
