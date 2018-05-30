
package handong.edu.csee.java.ChatCounter;// package name for ChatCounter


import java.util.*;// import of java.util library

/**
 * MessageFilter class is a subclass of ChatMessageCounter.
 .
 * @author 
 *
 */

public class MessageFilter extends ChatMessageCounter{// MessageFilter class subclass of ChatMessageCounter class

	private ArrayList<String> storeListName = new ArrayList<String>(); // list of names
	private ArrayList<String> names = new ArrayList<String>(); // ArrayList name

	/**
	 * This is the default constructor 
	 */
	// default constructor
	public MessageFilter() {

	}

	/**
	 * This constructor of MessageFilter 
	 * 
	 * @param 	
	 *  */
	// constructor parameters
	public MessageFilter(ArrayList<String> storeListName) {
		super(); // calls default constructor in super class
		this.storeListName = storeListName; // sets the value  list of names to the parameter
	}

	/**
	 * This is the computingNames() method
	 * 
	 */
	// computingNames method
	public void computingName() {
		names = recieveNames(storeListName); // instance recieveNames() private method 
		super.myData(storeListName, names); // instance myData() method in superclass
		super.computChatCounter(); // computing counter 
	}

	/**
	 * This is the recieveNames method()

	 * @param 
	 * @return 
	 * 
	 */
	// recieveNames method
	private ArrayList<String> recieveNames(ArrayList<String> storeListName) {
		ArrayList<String> myNames = new ArrayList<String>(); // new list of names

		for(String myName : storeListName) { 
			// if the list of names does not contain that myName then,
			if(!myNames.contains(myName)) {
				myNames.add(myName); // add that name to new list of myNames
			}
		}
		return myNames; // return myName
	}

	/**
	 * This is the recieveHashMap() method
	 *
	 * @return
	 */
	public HashMap<String,Integer> recieveHashMap(){
		return super.recieveHashMap(); // instance superclass recieveHashMap
	}

	/**
	 * This is the recieveNames() method
	 * 
	 * @return 
	 */

	public ArrayList<String> recieveName(){
		return super.recieveName(); // return super class receiveName
	}
}
