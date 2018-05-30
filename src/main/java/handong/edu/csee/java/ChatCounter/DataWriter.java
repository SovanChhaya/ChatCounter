package handong.edu.csee.java.ChatCounter;// Package name for ChatCounter

import java.io.BufferedWriter;// import java.io.BufferedWriter library
import java.io.File;// import file IO library
import java.io.FileOutputStream;// import file out put stream library
import java.io.IOException;// import Exception library
import java.io.OutputStreamWriter;// import Out put library 
import java.util.ArrayList;// import ArrayList library
import java.util.HashMap;// import HashMap library

public class DataWriter {
	private HashMap<Integer, String >Countermeassage = new HashMap<>();// list of message 
	private ArrayList<String>NameInList, name ;// list of name in message 

	

	
//DataWriter method	
public DataWriter(ArrayList<String>NameInList, ArrayList<String>name) {
	this.NameInList = NameInList;//local library
	this.name = name;//local library
	
}

public void writeFile(File file) {// method no return value
	try {
		BufferedWriter outputStream = new BufferedWriter(
				new  OutputStreamWriter(
						new FileOutputStream(file),"UTF-8"));// instantiate the BufferedReader class and read UTF8
		outputStream.write("kakao_id ,count\n");// out put Kakao_Id
		
		for (String name : name ) {
			outputStream.write((name+","+Countermeassage.get(name) + " \n"));
		}
		outputStream.close();// stop the out put
	}
	catch (IOException e) {// catch Exception
		e.printStackTrace();// print the error
	}
	
}

}
