package x10.optimizations.usefulPlaces;

import java.util.*;

public class DoremonGlobalRefs {
	/* stack of all class names */
	public static Stack<String> stackOfCname = new Stack<String> ();

	// TODO: stack!!
	public static ArrayList<String> arguments;

	/* place argument */
	public static String placeArgument = "NULL";

	// flag to extract stream output
	private static boolean isOn = false;

	// // flag to know if to consider evalAt opt or not
	// public static boolean evalOn = true;

	// current stream output
	public static String currStreamOutput = "";

	// stack of all the stream outputs  
	private static Stack<String> stackOfStreams = new Stack<String>();

	// /* stack of all evalAt template names */
	// public static Stack<String> stackOfevalTemplates = new Stack<String> ();

	// push cname into stack
	public static void pushClassName(String cname) {
		stackOfCname.push(cname);
	}

	// pop cname from stack
	public static String popClassName() {
		if (stackOfCname.empty()) return "BRRR";  // TODO: remove this
		return stackOfCname.pop();
	}

	// // push evalAt template into stack
	// public static void pushevalTemplates(String template) {
	// 	stackOfevalTemplates.push(template);
	// }

	// // pop evalAt template from stack
	// public static String popevalTemplates() {
	// 	return stackOfevalTemplates.pop();
	// }

	// turn off streaming
	public static void stopStream() {
		isOn = false;
		String savedTemp = currStreamOutput;
		int len = currStreamOutput.length();
		String temp = "";
		int i=0;

		// remove star-comments
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

		// only count upto first comma
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

	// // don't perform evalAt opt
	// public static void turnOffEval() {
	// 	evalOn = false;
	// }

	// // can perform evalAt opt
	// public static void turnOnEval() {
	// 	evalOn = true;
	// }
}
