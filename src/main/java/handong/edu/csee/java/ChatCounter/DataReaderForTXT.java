package handong.edu.csee.java.ChatCounter;// Package name for ChatCounter

import java.io.*;// import java.io library
import java.util.*;// import java.util library
import java.util.regex.Matcher;// import util.regex.Matcher library
import java.util.regex.Pattern;// import java.util.regex.pattern

public class DataReaderForTXT {

	String message;// instance variable
	String studentName;// instance variable
	int minute, hour,day, month,year; // instance variable
	boolean name =false; // instance variable


	ArrayList<String> readMessages = new ArrayList<String>();//list all the message in chat
	ArrayList<String> readNames= new ArrayList<String>();// list of the name in chat message 
	ArrayList<String>messageTime = new ArrayList<String>();// list of time 

	Pattern myPattern1 = Pattern.compile("-+\\s(\\d+).\\s(\\d).\\s(\\d+).\\s.+\\s-+");
	Pattern myPattern2 = Pattern.compile("\\[(.+)\\]\\s\\[(.+)\\s(\\d+):(\\d+)\\]\\s(.+)");// deal the date 

	//method
	public void readFileTXT(File file) {    

		try {
			BufferedReader inputStream = new BufferedReader(
					new  InputStreamReader(
							new FileInputStream(file),"UTF8"));	// instantiate the BufferedReader class and read UTF8	
			String line;// local variable
			while((line = inputStream.readLine()) != null  ) {
				Matcher myMatcher1 = myPattern1.matcher(line);// check line
				Matcher myMatcher2 = myPattern2.matcher(line);//check line

				if (myMatcher1.find()) {
					studentName = myMatcher1.group(1); // store a group of name
					minute = Integer.parseInt(myMatcher1.group(4));//store a group of minute
					message = myMatcher1.group(5);
					hour = Integer.parseInt(myMatcher1.group(3));
					if(myMatcher1.group(2).contains(message)) {
						hour =Integer.parseInt(myMatcher1.group(3))+12;

					}

				}
				else if (myMatcher2.find()) {
					year = Integer.parseInt(myMatcher2.group(1));
					month = Integer.parseInt(myMatcher2.group(2));
					day = Integer.parseInt(myMatcher2.group(3));

				}

				else if(readMessages.contains(year+"-"+month+"-"+day+""+hour+":"+minute+""+studentName+""+message)) {
					readMessages.add(year+"-"+month+"-"+day+""+hour+":"+minute+""+studentName+""+message);
					name= true;
				}
				else if(name) {
					readNames.add(studentName);
					name = false;


				}
			}
			inputStream.close();// stop the out put
		}catch(IOException e) {// catch Exception
			e.printStackTrace();// print the error
		}	
		readNames.clear();

	}
	//method 
	public void AddMessages(ArrayList<String> readMessages) {
		for(String message : readMessages) {
			if(!this.readMessages.contains(message)) {
				this.readMessages.add(message);
			}
		}
	}
	// method
	public void aTime(ArrayList<String> time) {
		for(String line : time) {
			if(!this.messageTime.contains(line)) {
				this.messageTime.add(line);

			}
		}

	}
	// ArrayList Constructor
	public ArrayList<String>recieveMessages(){
		return readMessages;// return read messages
	}
	// ArrayList Constructor
	public ArrayList<String>recieveName(){
		return readNames;// return readName 
	}


}




