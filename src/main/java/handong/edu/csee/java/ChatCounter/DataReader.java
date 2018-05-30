
package handong.edu.csee.java.ChatCounter;// package name of ChatCounter


import org.apache.commons.cli.*;// import  org.apache.commons.cli library

import java.io.*;// imports java.io library

import java.util.*;// imports java.util library

/**
 * DataReader class is the main class having the main method it reads the file 

 * @author 
 */
// DataReader class the main class
public class DataReader {
	private static String inputMydir; // input directory file 
	private static String outputMyfile; // output directory file 

	/**
	 * This is the main method implement all the class
	 * @param 
	 */

	public static void main(String[] args) {// main method to implement all the class 

		Options myOptions = setUpOptions(); // instance CreateOption 


		if(myOptions(myOptions, args)){	
			DataReader myReader = new DataReader(); // instantiate DataReader class
			ArrayList<String> storeListName = myReader.receiveData(inputMydir); // stores the list of names 
			MessageFilter myFilter = new MessageFilter(storeListName); // instantiate MessageFilter class

			myFilter.computingName(); // call method of MessageFilter class

			HashMap<String,Integer> messageCounter = myFilter.recieveHashMap(); // stores HashMap message counter
			storeListName = myFilter.recieveName(); // call method recieveName in MessageFilter class

			DataWriter myWriter = new DataWriter(messageCounter,storeListName); // instantiate DataWriter class
			File outputFile = new File(outputMyfile); 
			myWriter.myFile(outputFile); //call method from DataWriter class
		}
	}

	/**
	 * This is the receiveData() method

	 * @param 
	 * @return
	 */
	// receiveData method
	public ArrayList<String> receiveData(String strDir){

		//call method to get the directory name
		File myDir = getDirectory(strDir);
		//get file 
		File[] file = getListOfFilesFromDirectory(myDir); 

		ArrayList<String> listOfNames = readFiles(file); 

		return listOfNames; // returns the list of names
	}

	/**
	 * This is the getDirectory() method

	 * @param 
	 * @return
	 */
	// getDirectory method
	private File getDirectory(String strDir){
		File myDirectory = new File(strDir); // creates File 
		return myDirectory; // returns file
	}

	/**
	 * This is the getListOfFilesFromDirectory() method
	 *
	 * @param
	 * @return
	 */
	// getListOfFilesFromDirectory method
	private File[] getListOfFilesFromDirectory(File dataDir){
		return dataDir.listFiles(); // returns list of files
	}

	/**
	 * This is the readFiles() method
	 * 
	 * @param 
	 * @return 
	 */
	// readFiles method as ArrayList
	private ArrayList<String> readFiles(File[] files){
		ArrayList<String> listOfNames = new ArrayList<String>(); // ArrayList listofNames store list of names

		DataReaderForTXT readTXT = new DataReaderForTXT(); //  instantiate DataReaderForTXT class
		DataReaderForCSV readCSV = new DataReaderForCSV(); // instantiate  DataReaderForCSV class


		for(File file : files) {//  loop to go through  file

			if(file.toString().contains("csv")) {// if that file contains csv

				readCSV.getMessages(readTXT.receiveMessages()); // passes the  message from the txt 
				readCSV.readFilesCSV(file); // reads the file 
				listOfNames.addAll(readCSV.receiveNames()); // adds list of names from the csv 
			}

			else if (file.toString().contains("txt")){// if that file contains txt
				readTXT.getMessages(readCSV.receiveMessages()); // passes the  message from csv 
				readTXT.readFileTXT(file); // reads the file 
				listOfNames.addAll(readTXT.receiveNames()); // adds  list of names  from the txt 
			}
		}
		return listOfNames; // returns list of names
	}

	/**
	 * This is the createOptions() method.
	 * 
	 * @return
	 */
	// createOptions method as Option
	public static Options setUpOptions() {
		Options options = new Options(); // instantiate Options class 
		options.addOption(Option.builder("i").longOpt("inputdir") // calls addOption
				.desc("Set a directory path that contains input files")
				.hasArg() // checks arguments
				.argName("Directory path") //  argument name
				.required() // the option is required
				.build()); // builds the option

		options.addOption(Option.builder("o").longOpt("outputfile") // calls addOption 
				.desc("Set a directory path that contains output files") 
				.argName("File path") // argument name
				.required() //  option required
				.build()); // builds the option
		return options; // returns  options
	}

	/**
	 * This is the myPrinting() method

	 * @param options
	 */
	// myPrint method
	public static void myprinting(Options options) {

		HelpFormatter formatter = new HelpFormatter(); // instantiate HelpFormatter
		String header = "CLI test program"; // set the header
		String footer ="\nPlease report issues at https://github.com/sekkaro/ChatCounter"; // sets the  footer
		formatter.printHelp("CLIExample", header, options, footer, true); // calls  printHelp method
	}

	/**
	 * This is the parseOptions() method

	 * @param 
	 * @param 
	 * @return 
	 */
	// myOptions method
	public static boolean myOptions(Options options, String[] args) {
		CommandLineParser myParser = new DefaultParser(); // instantiate DefaulParser class

		// try block 
		try {

			CommandLine cmd = myParser.parse(options, args); // command line

			inputMydir = cmd.getOptionValue("i"); // stores the option 
			outputMyfile = cmd.getOptionValue("o"); // stores the option 

		} 

		catch (Exception e) {// catch Exception

			myprinting(options); //myPrinting statement
			return false; // return false
		}

		return true; // returns true
	}


}
