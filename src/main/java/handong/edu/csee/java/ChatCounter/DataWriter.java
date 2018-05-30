package handong.edu.csee.java.ChatCounter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class DataWriter {
	private HashMap<Integer, String >Countermeassage = new HashMap<>();
	private ArrayList<String>NameInList, name ;

	

	
	
public DataWriter(ArrayList<String>NameInList, ArrayList<String>name) {
	this.NameInList = NameInList;
	this.name = name;
	
}

public void writeFile(File file) {
	try {
		BufferedWriter outputStream = new BufferedWriter(
				new  OutputStreamWriter(
						new FileOutputStream(file),"UTF-8"));
		outputStream.write("kakao_id ,count\n");
		
		for (String name : name ) {
			outputStream.write((name+","+Countermeassage.get(name) + " \n"));
		}
		outputStream.close();
	}
	catch (IOException e) {
		e.printStackTrace();
	}
	
}

}
