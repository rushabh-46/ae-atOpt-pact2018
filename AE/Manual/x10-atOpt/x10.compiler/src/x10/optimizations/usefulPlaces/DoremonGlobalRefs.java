package x10.optimizations.usefulPlaces;

import java.util.*;

public class DoremonGlobalRefs {

	// public Stack <DoremonDetails> details;

	// create one stack

	/* static class name */
	public static String cname = "";

	// TODO: stack!!
	public static ArrayList<String> arguments;

	/* place argument */
	public static String placeArgument = "NULL";

	// flag to extract stream output
	private static boolean isOn = false;

	// extract stream output
	public static String streamOutput = "";

	// turn off streaming
	public static void stopStream() {
		isOn = false;

		int len = streamOutput.length();
		String temp = "";
		int i=0;
		while(i<len) {
			if (streamOutput.charAt(i) == '/' && i+1<len && streamOutput.charAt(i+1) == '*') {
				i+=2;
				while(i<len-1 && !(streamOutput.charAt(i) == '*' && streamOutput.charAt(i+1) == '/')) i++;
				i+=2;
			}
			if (i<len) temp = temp + streamOutput.charAt(i);
		}
		streamOutput = temp;
		int index = streamOutput.indexOf(",");
		if (index > 0)
			streamOutput = streamOutput.substring(0, index);
		else {
			// TODO: throw error?
		}
	}

	// turn on streaming
	public static void startStream() {
		isOn = true;
		streamOutput = "";
	}

	// write to the stream
	public static void writeStream(String str) {
		if (isOn) streamOutput = streamOutput + str;
	}
}
