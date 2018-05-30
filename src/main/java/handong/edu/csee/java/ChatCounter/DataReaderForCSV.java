package handong.edu.csee.java.ChatCounter;



import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataReaderForCSV {

	String message;
	String studentName;
	int minute, hour,day, month,year, second;
	boolean name = false;

	ArrayList<String> readMessages = new ArrayList<String>();
	ArrayList<String> readNames= new ArrayList<String>();
	ArrayList<String>messageTime = new ArrayList<String>();

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
			inputStream.close();

		} catch (IOException e) {

			e.printStackTrace();

		}


	}
	public ArrayList<String>recieveTime(){
		return messageTime;
	}

	public void aMessages(ArrayList<String> readMessages) {
		for(String message : readMessages) {
			if(!this.readMessages.contains(message)) {
				this.readMessages.add(message);
			}
		}
	}

	public ArrayList<String>recieveMessages(){
		return readMessages;
	}
	public ArrayList<String>recieveName(){
		return readNames;
	}
}