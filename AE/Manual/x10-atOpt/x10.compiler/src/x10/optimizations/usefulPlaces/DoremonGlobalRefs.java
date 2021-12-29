package x10.optimizations.usefulPlaces;

import java.util.*;

public class DoremonGlobalRefs {

	// public Stack <DoremonDetails> details;

	// create one stack

	/* stack of all class names */
	public static Stack<String> stackOfCname = new Stack<String> ();

	// TODO: stack!!
	public static ArrayList<String> arguments;

	/* place argument */
	public static String placeArgument = "NULL";

	// flag to extract stream output
	private static boolean isOn = false;

	// current stream output
	public static String currStreamOutput = "";

	// stack of all the stream outputs  
	private static Stack<String> stackOfStreams = new Stack<String>();

	// push cname into stack
	public static void pushClassName(String cname) {
		stackOfCname.push(cname);
	}

	// pop cname from stack
	public static String popClassName() {
		return stackOfCname.pop();
	}

	// turn off streaming
	public static void stopStream() {
		isOn = false;
		String savedTemp = currStreamOutput;
		int len = currStreamOutput.length();
		String temp = "";
		int i=0;
		while(i<len) {
			if (currStreamOutput.charAt(i) == '/' && i+1<len && currStreamOutput.charAt(i+1) == '*') {
				i+=2;
				while(i<len-1 && !(currStreamOutput.charAt(i) == '*' && currStreamOutput.charAt(i+1) == '/')) i++;
				i+=2;
			}
			if (i<len) temp = temp + currStreamOutput.charAt(i);
			i++;
		}

		currStreamOutput = temp;
		i=0;
		len = currStreamOutput.length();
		temp = "";
		int bracketCount = 0;
		while(i<len) {
			if (currStreamOutput.charAt(i) == ',' && bracketCount == 0) break;
			if (currStreamOutput.charAt(i) == '(') bracketCount++;
			else if (currStreamOutput.charAt(i) == ')') bracketCount--;
			temp = temp + currStreamOutput.charAt(i);
			i++;
		}
		currStreamOutput = temp ;
		// stackOfStreams.push(currStreamOutput);
	}

	// turn on streaming
	public static void startStream() {
		isOn = true;
		stackOfStreams.push(currStreamOutput);
		currStreamOutput = "";
	}

	// write to the stream
	public static void writeStream(String str) {
		if (isOn) currStreamOutput = currStreamOutput + str;
	}

	// pop and return stream
	public static String popStreamOutput() {
		String str = currStreamOutput;
		currStreamOutput = stackOfStreams.pop();
		return str;
	}
}
