package handong.edu.csee.java.ChatCounter;// Package name for ChatCounter



import java.io.*;// import java IO file library 
import java.util.*;//import java.util library
import java.util.regex.Matcher;// import util.regex.Matcher library
import java.util.regex.Pattern;// import java.util.regex.pattern

public class DataReaderForCSV {

	String message;// instance variable
	String studentName;// instance variable
	int minute, hour,day, month,year, second;// instance variable
	boolean name = false;// instance variable

	ArrayList<String> readMessages = new ArrayList<String>();//list all the message in chat
	ArrayList<String> readNames= new ArrayList<String>();// list of the name in chat message 
	ArrayList<String>messageTime = new ArrayList<String>();// list of time

	Pattern myPattern = Pattern.compile("(\\d+)-(\\d+)-(\\d+)\\s(\\d+):(\\d+):(\\d+),\"(.+)\",\"(.+)\"");

	public void readFiles(File file) {

		try {
			BufferedReader inputStream = new BufferedReader(
					new InputStreamReader(
							new FileInputStream(file),"UTF8"));
			String line;
			while((line = inputStream.readLine())!=null);
			Matcher myMatch = myPattern.matcher(line);
			if(myMatch.find()) {
				message = myMatch.group(8);
				studentName = myMatch.group(7);
				second = Integer.parseInt(myMatch.group(6));
				minute = Integer.parseInt(myMatch.group(5));
				hour = Integer.parseInt(myMatch.group(4));
				day = Integer.parseInt(myMatch.group(3));
				month = Integer.parseInt(myMatch.group(2));
				year = Integer.parseInt(myMatch.group(1));

				if(readMessages.contains(year+"-"+month+"-"+day+" "+hour+":"+minute+" "+studentName+" "+message)) {
					readMessages.add(year+"-"+month+"-"+day+" "+hour+":"+minute+" "+studentName+" "+message);
					name = true;
				}

				if(name) {
					readNames.add(studentName);
					name = false;
				}
			}
			inputStream.close();// close input Stream

		} catch (IOException e) {// block Exception

			e.printStackTrace();// print the error

		}


	}
	//ArrayList constructor
	public ArrayList<String>recieveTime(){
		return messageTime;
	}
	// method
	public void aMessages(ArrayList<String> readMessages) {
		for(String message : readMessages) {
			if(!this.readMessages.contains(message)) {
				this.readMessages.add(message);
			}
		}
	}
	//ArrayList Constructor
	public ArrayList<String>recieveMessages(){
		return readMessages;// return readMessage 
	}
	//ArrayList Constructor
	public ArrayList<String>recieveName(){
		return readNames;// return readName
	}
}