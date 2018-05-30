package handong.edu.csee.java.ChatCounter;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataReaderForTXT {

	String message;
	String studentName;
	int minute, hour,day, month,year;
	boolean name =false;


	ArrayList<String> readMessages = new ArrayList<String>();
	ArrayList<String> readNames= new ArrayList<String>();
	ArrayList<String>messageTime = new ArrayList<String>();

	Pattern myPattern1 = Pattern.compile("-+\\s(\\d+).\\s(\\d).\\s(\\d+).\\s.+\\s-+");
	Pattern myPattern2 = Pattern.compile("\\[(.+)\\]\\s\\[(.+)\\s(\\d+):(\\d+)\\]\\s(.+)");


	public void readFileTXT(File file) {    

		try {
			BufferedReader inputStream = new BufferedReader(
					new  InputStreamReader(
							new FileInputStream(file),"UTF8"));		
			String line;
			while((line = inputStream.readLine()) != null  ) {
				Matcher myMatcher1 = myPattern1.matcher(line);
				Matcher myMatcher2 = myPattern2.matcher(line);

				if (myMatcher1.find()) {
					studentName = myMatcher1.group(1);
					minute = Integer.parseInt(myMatcher1.group(4));
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
			inputStream.close();
		}catch(IOException e) {
			e.printStackTrace();
		}	
		readNames.clear();

	}

	public void aMessages(ArrayList<String> readMessages) {
		for(String message : readMessages) {
			if(!this.readMessages.contains(message)) {
				this.readMessages.add(message);
			}
		}
	}

	public void aTime(ArrayList<String> time) {
		for(String line : time) {
			if(!this.messageTime.contains(line)) {
				this.messageTime.add(line);

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




