
package handong.edu.csee.java.ChatCounter;// package name of ChatCounter


import java.io.*;// imports java.io library

import java.util.*;// imports java.util library

import java.util.regex.*;// imports java.util.regex library

/**
 * DataReaderForTXT class to implement  txt files.
 * 
 * @author 
 *
 */

public class DataReaderForTXT {// DataReaderForTXT class

	private String myMessages,myName; //instance variable
	int minute,day, year,month,hour; //instance variable
	boolean readName = false; // instance variable
	private ArrayList<String> myNames = new ArrayList<String>(); // list of names
	private ArrayList<String> txtMessages = new ArrayList<String>(); // list of  messages
	Pattern myPattern1 = Pattern.compile("\\[(.+)\\]\\s\\[(.+)\\s(\\d+):(\\d+)\\]\\s(.+)"); 
	Pattern myPattern2 = Pattern.compile("-+\\s(\\d+).\\s(\\d).\\s(\\d+).\\s.+\\s-+"); //dealing with the date


	/**
	 * This is the readFilesTXT() method
	 *
	 * @param 
	 */
	// readFilesTXT method
	public void readFileTXT(File file) {
		myNames.clear(); // clear the list names

		try {// try block
			BufferedReader inputStream = new BufferedReader(
					new InputStreamReader(
							new FileInputStream(file),"UTF-8")); // instance of BufferedReader class and reads it in UTF-8 form
			String line; //local variable

			while((line=inputStream.readLine())!=null) {
				Matcher myMatcher1 = myPattern1.matcher(line); // matcher to check  in the line
				Matcher myMatcher2 = myPattern2.matcher(line); // matcher to check  in the line

				if(myMatcher2.find()) {// if myMatcher2 is find
					// store the day
					day = Integer.parseInt(myMatcher2.group(3)); 
					// store the month
					month = Integer.parseInt(myMatcher2.group(2)); 
					// store the year 
					year = Integer.parseInt(myMatcher2.group(1)); 


				}


				if(myMatcher1.find()) {// if myMatcher1 is find
					// store the hour
					hour = Integer.parseInt(myMatcher1.group(3)); 
					// store the myMessages
					myMessages = myMatcher1.group(5); 
					// store the minute
					minute = Integer.parseInt(myMatcher1.group(4));
					// store the name
					myName = myMatcher1.group(1); 





					if(myMessages.equals("??")) {//if the (myMessages equals to the locate"??"

						myMessages = "Photo"; 
					}

					if(myMatcher1.group(2).contains("??")&&hour==12) {// if the second group 
						hour = 0;
					}

					if(myMatcher1.group(2).contains("??")&&hour!=12) {// if the second group
						hour = Integer.parseInt(myMatcher1.group(3))+12; // sets the value of hour as the third group 
					}




					if(!txtMessages.contains(year+"-"+month+"-"+day+" "+hour+":"+minute+" "+myName+" "+myMessages)) {// if the message does not contain 
						txtMessages.add(year+"-"+month+"-"+day+" "+hour+":"+minute+" "+myName+" "+myMessages); //  add to the list
						readName = true; // set the readName to true
					}

					if(readName) {// if the readName is true,
						myNames.add(myName); // add that name value to list
						readName=false; // set the value of readName false
					}
				}
			}
			inputStream.close(); // close the inputStream
		} 

		catch (IOException e) {// catch exception
			e.printStackTrace(); //print the error
		}
	}

	/**
	 * This is the getMessages() method
	 *
	 * @param myMessages 
	 */
	// getMessages method
	public void getMessages(ArrayList<String> myMessages) {

		for(String message : myMessages) {// loop to check message from parameter


			if(!this.txtMessages.contains(message)) {// if the allMessages of this class does not not contain 
				this.txtMessages.add(message); // add that message to  message 			}
			}
		}

	}

	/**
	 * This is the receiveNames() method
	 *
	 * @return 
	 */
	// method receiveName
	public ArrayList<String> receiveNames(){
		return myNames; // return myNames
	}


	/**
	 * This is the receiveMessages() method

	 * @return 
	 */
	// getMessages method
	public ArrayList<String> receiveMessages(){
		return txtMessages; // return txtMessages
	}


}

