package org.paces.Stata;

import com.stata.sfi.SFIToolkit;

import java.util.Map;
import java.util.regex.Pattern;

/**
 * <h1>Class used to parse and initialize a Pattern class object when
 * called from the Java API in Stata.</h1>
 * @author Billy Buchanan
 * @version 20dec2015
 *
 */
public class StPattern {

	/***
	 * A map object storing the Pattern class object's option flags with
	 * String keys that can be easily referenced from Stata.
	 */
	protected Map<String, Integer> optionmap;

	/***
	 * A Pattern class object (used to store the regular expression).
	 */
	protected Pattern pat;

	/***
	 * Integer object used to store the user provided option flags passed to
	 * the compile method of the Pattern class.  If no options are passed, or
	 * if any of the setter methods are called with null valued options
	 * parameters, the default value of 0 will be populated.
	 */
	protected Integer patternOptions;

	/***
	 * Method used to create the Pattern object by calling the compile method
	 * with the appropriate option flags
	 * @param regex The string pattern to use as the regular expression
	 * @param options A string array of optional flags used when calling the
	 *                   compile method of the Pattern class
	 */
	public void setRegEx(String regex, String[] options) {
		try {
			setOptions(options);
		} catch (NullPointerException e) {
			SFIToolkit.errorDebug("Null options passed to StPattern.setRegEx");
		} finally {
			this.pat = Pattern.compile(regex, getOptions());
		}
	}


	/***
	 * Method used to create a map object used to return mapping from Stata
	 * options to Pattern compile options in Java
	 */
	public StPattern() {
		optionmap.put("canon_eq", Pattern.CANON_EQ);
		optionmap.put("case_insensitive", Pattern.CASE_INSENSITIVE);
		optionmap.put("comments", Pattern.COMMENTS);
		optionmap.put("dotall", Pattern.DOTALL);
		optionmap.put("literal", Pattern.LITERAL);
		optionmap.put("multiline", Pattern.MULTILINE);
		optionmap.put("unicode_case", Pattern.UNICODE_CASE);
		optionmap.put("unicode_character_case", Pattern.UNICODE_CHARACTER_CLASS);
		optionmap.put("unix_lines", Pattern.UNIX_LINES);
	}

	/***
	 * Method that converts text options to integer representation of Java
	 * regex compile options
	 * @param opts An array of string options
	 */
	public void setOptions(String[] opts) {

		// Local variable to combine all of the options passed to the method
		Integer options = null;

		// Try block for handling null pointer issues
		try {

			// If there are two or more options they need to be combined with
			// the bitwise OR operator
			if (opts.length >= 2) {

				// Loop over the options passed to the method
				for (int i = 1; i < opts.length; i++) {

					// Combine all of the options using the bitwise OR
					// operator and store them in a single variable that can
					// be passed to the compile method
					options = options | optionmap.get(opts[i]);

				} // End Loop over option flags

			// If there is only a single option flag
			} else if (opts.length == 1) {

				// Retrieve the option flag and populate the options variable
				// with that value
				options = optionmap.get(opts[0]);

			// If there are 0 arguments
			} else {

				// Set the local variable to the default which is no option
				// flags
				options = 0;

			} // End ELSE Block for no arguments

		// If null/empty array passed to the method
		} catch (NullPointerException e) {

			// If nothing passed to the method, set the value of 0, which is
			// what would be returned if the object is created with the
			// single parameter compile method.
			options = 0;

		} // End of Catch block

		// Set the options variable of the class
		this.patternOptions = options;

	} // End of method declaration

	/***
	 * Method used to retrieve the option flags that will be passed to the
	 * compile method of the Pattern class
	 * @return The integer bit mask containing the compilation option flags
	 * for the Pattern class object
	 */
	public Integer getOptions() {
		return this.patternOptions;
	}

	/***
	 * Method to access the Pattern object in the class
	 * @return A java regex pattern class object
	 */
	public Pattern getPattern() {
		return this.pat;
	}

}
