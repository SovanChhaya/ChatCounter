
package handong.edu.csee.java.ChatCounter;// package name for ChatCounter

// import java.util library
import java.util.*;

/**
 * ChatMessageCounter 
 *
 *
 * @author 
 *
 */
// ChatMessageCounter class
public class ChatMessageCounter {
	private ArrayList<String> storeListName,name; // ArrayList variable store name
	private HashMap<String,Integer> countMessage = new HashMap<>(); // HashMap of message list
	private HashMap<String,Integer> sortedMessage = new HashMap<>(); // sorted name
	private ArrayList<Integer> myCounter = new ArrayList<Integer>(); // ArrayList count message
	private ArrayList<String> listNames= new ArrayList<String>(); // ArrayList 

	/**
	 * This is the default constructor 
	 *
	 */
	// default constructor
	public ChatMessageCounter(){

	}

	/**
	 * This is the myData() method 
	 * count the message count of each names.
	 * @param 
	 * @param 
	 */
	// myData method that acts like a constructor 
	public void myData(ArrayList<String> listOfNames,ArrayList<String> names) {
		this.storeListName = listOfNames; // local variable
		this.name = names; // local variable
	}


	/**
	 * This is the calculateCounter() method


	 */
	// computingCounter method
	public void computChatCounter() {
		int count=0; // initializes the vale of count as 0

		for(String name:name) {//loop to repeat the 

			for(String listOfName:storeListName) {// if the name equals to the name in the list of names

				if(name.equals(listOfName)) {
					count++; // increase
				}
			}
			myCounter.add(count); // add each count of names to the ArrayList counter
			countMessage.put(name,count); // put the name 
			count=0; 
		}

		Collections.sort(myCounter); // sort the ArrayList counter 
		Collections.reverse(myCounter); // sort the ArrayList 

		for(int counter : myCounter) { // loop to get every message count values

			for(String name : name) {//loop to get name in ArrayList
				// if the sortedNames ArrayList 
				if(!listNames.contains(name)&&(countMessage.get(name)==counter)) {
					listNames.add(name); // add that name in ArrayList
					sortedMessage.put(name,countMessage.get(name)); // put that name 
				}
			}
		}
	}

	/**
	 * This is the recieveHashMap() method
	 * This is the getter method of the sorted HashMap returning the final value in 
	 * computCounter() method.
	 */
	// HashMap method
	public HashMap<String,Integer> recieveHashMap(){
		return sortedMessage; // returns sortedMessage
	}

	/**
	 * This is the reciveNames() method
	 * This is the getter method for sorted list of names
	 * @return 
	 */
	// recieveNames method
	public ArrayList<String> recieveName(){
		return listNames; // returns list name
	}

}
