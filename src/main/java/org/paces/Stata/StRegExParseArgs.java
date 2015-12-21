package org.paces.Stata;

import java.util.Arrays;

/**
 * @author Billy Buchanan
 * @version 0.0.0
 */
public class StRegExParseArgs {

	/***
	 * Method used to return the regular expression from the String array of
	 * arguments passed from javacall
	 * @param args A String array of arguments passed from Javacall
	 * @return A string containing a regular expression used to construct a
	 * Pattern class object
	 */
	public String getRegEx(String[] args) {
		return Arrays.copyOfRange(args, 0, 0).toString();
	}

	/***
	 * Method used to return the string that will be matched against
	 * @param args A String array of arguments passed from Javacall
	 * @return A string containing the string that is to be matched against
	 */
	public String getMatchString(String[] args) {
		return Arrays.copyOfRange(args, 1, 1).toString();
	}

	/***
	 * Method used to return the set of options for the Pattern.compile() method
	 * @param args A String array of arguments passed from Javacall
	 * @return A string array containing values corresponding to the static
	 * final values of the Pattern class to set optional parameters for the
	 * regex engine.
	 */
	public String[] getPatternOptions(String[] args) {
		return Arrays.copyOfRange(args, 2, 10);
	}

	/***
	 * Method used to return options passed to the Matcher class
	 * @param args A String array of arguments passed from Javacall
	 * @return A string array containing optional arguments used with the
	 * Matcher class
	 */
	public String[] getMatcherOptions(String[] args) {
		return Arrays.copyOfRange(args, 11, args.length);
	}

} // Ends Class declaration
