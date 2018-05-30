
package handong.edu.csee.java.ChatCounter;// package name for ChatCounter


import java.io.*;// imports java.io library


import java.util.*;// imports java.util library

import java.util.regex.*;// imports java.util.regex  library
/**
 * DataReaderForCSV class to implement csv files.

 * @author
 *
 */

public class DataReaderForCSV {// DataReaderForCSV class

	private String message,myName;// instance variable

	private int year,month,day,hour,minute; // instance variable
	private boolean readName = false; // instance variable


	private ArrayList<String> names = new ArrayList<String>(); // list of names
	private ArrayList<String> myMessages = new ArrayList<String>(); // list of whole message

	Pattern myPattern1 = Pattern.compile("(\\d+)-(\\d+)-(\\d+)\\s(\\d+):(\\d+):(\\d+),\"(.+)\",\"(.+)"); 
	Pattern myPattern2 = Pattern.compile("(\\d+)-(\\d+)-(\\d+)\\s(\\d+):(\\d+):(\\d+),\"(.+)\",\"(.+)\""); 
	Pattern myPattern3 = Pattern.compile("(.+)\""); //

	/**
	 * This is the readFilesCSV() method
	 * 
	 * @param 
	 */
	// readFiles method
	public void readFilesCSV(File file) {
		names.clear(); // clear the names list when reading new/next file
		// try block
		try {
			BufferedReader inputStream = new BufferedReader(
					new InputStreamReader(
							new FileInputStream(file),"UTF-8")); //instance of BufferedReader class and reads it in UTF-8 form
			String line; // local variable

			while((line=inputStream.readLine())!=null) {
				Matcher myMatcher1 = myPattern2.matcher(line); // matcher to check  pattern in the line
				Matcher myMatcher2 = myPattern1.matcher(line); // matcher to check pattern in the line
				Matcher myMatcher3 = myPattern3.matcher(line); // matcher to check  pattern in the line

				if(line.contains("\"")) {//in line contain "quote" 

					if(line.contains("I think I got the answer")||(line.contains("confused")&&myName.equals("???"))) {
						message=""; 
						continue;//continue 
					}
					// if the first pattern matches,
					if(myMatcher1.matches()) {
						// store the first group 
						year = Integer.parseInt(myMatcher1.group(1)); 
						// store the second group 
						month = Integer.parseInt(myMatcher1.group(2)); 
						// store the third group 
						day = Integer.parseInt(myMatcher1.group(3)); 
						// store the fourth group 
						hour = Integer.parseInt(myMatcher1.group(4)); 
						// store the fifth group 
						minute = Integer.parseInt(myMatcher1.group(5)); 
						Integer.parseInt(myMatcher1.group(6));
						// store the seventh group 
						myName = myMatcher1.group(7); 
						// store the eighth group
						message = myMatcher1.group(8).replace("\"\"","\""); 

						if(!myMessages.contains(year+"-"+month+"-"+day+" "+hour+":"+minute+" "+myName+" "+message)) {// if myMessage not contain

							myMessages.add(year+"-"+month+"-"+day+" "+hour+":"+minute+" "+myName+" "+message); //add to the list  message 
							readName = true; // set the readName to true

						}

					}
					// if the myMatcher2 is found,
					else if(myMatcher2.find()) {
						// store the year
						year = Integer.parseInt(myMatcher2.group(1));
						// store the month
						month = Integer.parseInt(myMatcher2.group(2)); 
						// store the day 
						day = Integer.parseInt(myMatcher2.group(3)); 
						// store the hour 
						hour = Integer.parseInt(myMatcher2.group(4)); 
						// store the minute
						minute = Integer.parseInt(myMatcher2.group(5)); 
						Integer.parseInt(myMatcher2.group(6)); 
						// store myName
						myName = myMatcher2.group(7); 
						// store the message
						message = line.split(",")[2].replace("\"\"", "\""); 
					}
					// else if the third pattern is found,
					else if(myMatcher3.find()) {
						message = message + myMatcher3.group(1).replace("\"\"", "\""); 

						if(!myMessages.contains(year+"-"+month+"-"+day+" "+hour+":"+minute+" "+myName+" "+message)) {//if myMessages not contain
							myMessages.add(year+"-"+month+"-"+day+" "+hour+":"+minute+" "+myName+" "+message); //add to the list of message 
							readName = true; // set the readName to true
						}
					}


					else{

						if(!myMessages.contains(year+"-"+month+"-"+day+" "+hour+":"+minute+" "+myName+" "+message)) {//if myMessages not contain
							myMessages.add(year+"-"+month+"-"+day+" "+hour+":"+minute+" "+myName+" "+message); // add to the list of  message 
							readName = true; // set the readName to true
						} 
					}
				}


				if(readName) {// if the readName is true,
					names.add(myName); // add name value to the name list
					readName=false; // set the value of readName  false
				}
			}
			inputStream.close(); // close the inputStream
		} 

		catch (IOException e) {// catch Exception
			e.printStackTrace(); //  print the error
		} 
	}

	/**
	 * This is the receiveNames() method

	 * @return 
	 */
	// receiveNames method
	public ArrayList<String> receiveNames(){
		return names; // return  names
	}


	/**
	 * This is the getMessages() method

	 * @param myMessages 
	 */
	// addMessages method
	public void getMessages(ArrayList<String> myMessages) {

		for(String message : myMessages) {//  loop to check message from parameter 


			if(!this.myMessages.contains(message)) {// if the myMessages not not contain that message,
				this.myMessages.add(message); // add that message
			}
		}
	}

	/**
	 * This is the receiveMessages() method

	 * @return 
	 */
	// receiveMessages method
	public ArrayList<String> receiveMessages(){
		return myMessages; // return myMessages
	}


}
