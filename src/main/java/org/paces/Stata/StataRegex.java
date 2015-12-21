package org.paces.Stata;

import com.stata.sfi.Data;
import com.stata.sfi.Macro;
import com.stata.sfi.SFIToolkit;
import org.apache.commons.lang3.ArrayUtils;
import org.paces.Stata.MetaData.Observations;

/**
 * @author Billy Buchanan
 * @version 0.0.0
 */
public class StataRegex {


	/***
	 * Method to replace all instances in args[0] defined by the pattern in
	 * args[1] with the value of args[2]
	 * @param args A string array with length 3, where the 0th element is the
	 *                string of interest, 1st element is the regular
	 *                expression, and 2nd element is the string to use for
	 *                substitution.
	 * @return A success/failure indicator
	 */
	public static int replaceFirstString(String[] args) {

		// Check for sufficient arguments
		if (args.length == 3 && !args[0].isEmpty() && !args[1].isEmpty() &&
				!args[2].isEmpty()) {

			// Return a macro containing the first instance of the string
			// replaced
			Macro.setLocal("repfirst", args[0].replaceFirst(args[1], args[2]));

			// return success code
			return 0;

		// For insufficient arguments
		} else {

			// Print error message to the Stata console
			SFIToolkit.errorln("Must pass 3 non-empty arguments to method");

			// Return an error code
			return 1;

		} // End ELSE Block for invalid arguments

	} // End Method declaration

	/***
	 * Stata Interface for java.util.regex.Matcher.replaceFirst(String
	 * replacement)
	 * @param args String arguments used to construct the pattern, matcher,
	 *                etc...
	 * @return A success or failure indicator
	 */
	public static int replaceFirstVar(String[] args) {

		// Make sure there are sufficient arguments
		if (args.length >= 3 && !args[0].isEmpty() && !args[1].isEmpty() &&
				!args[2].isEmpty()) {

			// First argument of the string array must be the regex
			String regex = args[0];

			// Second argument needs to be the replacement string
			String repstring = args[1];

			// Remove the replacement string array
			ArrayUtils.remove(args, 1);

			// Remove the regex from the array
			ArrayUtils.remove(args, 0);

			// Initialize a new Observations object
			Observations obs = new Observations();

			// Loop over the observations
			for (Long obid : obs.getObservationIndex()) {

				// Loop over the variables passed to the method
				for (int varids = 0; varids < args.length; varids++) {

					// Initialize new string with cases matching regex
					// replaced the first time with the replacement string
					String repall =
						Data.getStr(Data.getVarIndex(args[varids]), obid)
							.replaceFirst(regex, repstring);

					// Replace the value in the data set with the regular
					// expression
					Data.storeStr(Data.getVarIndex(args[varids]), obid, repall);

				} // End Loop over variables passed to method

			} // End Loop over observations

			// Returns sucess code
			return 0;

		// If any of the tests above are incorrect
		} else {

			// Print error message to the Stata console
			SFIToolkit.errorln("Must pass 3 or more non-empty arguments to method");

			// Return error code
			return 1;

		} // End ELSE Block

	} // End Method declaration

	/***
	 * Method to replace all instances in args[0] defined by the pattern in
	 * args[1] with the value of args[2]
	 * @param args A string array with length 3, where the 0th element is the
	 *                string of interest, 1st element is the regular
	 *                expression, and 2nd element is the string to use for
	 *                substitution.
	 * @return A success/failure indicator
	 */
	public static int replaceAllString(String[] args) {

		// Test for valid method signature
		if (args.length == 3 && !args[0].isEmpty() && !args[1].isEmpty()) {

			// Return the string with all instances of args[1] replaced with
			// args[2] from the string args[0]
			Macro.setLocal("repfirst", args[0].replaceAll(args[1], args[2]));

			// Return success code
			return 0;

		// If invalid/insufficient arguments are passed
		} else {

			// Print error message to the Stata console
			SFIToolkit.errorln("Must pass three non-empty arguments to this method");

			// Return an error code
			return 1;

		} // End ELSE Block for invalid/insufficient arguments

	} // End Method declaration

	/***
	 * Stata Interface for java.util.regex.Matcher.replaceAll(String
	 * replacement)
	 * @param args String arguments used to construct the pattern, matcher,
	 *                etc...
	 * @return A success or failure indicator
	 */
	public static int replaceAllVar(String[] args) {

		// Test for correct method signature
		if (args.length >= 3 && !args[0].isEmpty() && !args[1].isEmpty() &&
				!args[2].isEmpty()) {

			// First argument of the string array must be the regex
			String regex = args[0];

			// Second argument needs to be the replacement string
			String repstring = args[1];

			// Remove the replacement string array
			ArrayUtils.remove(args, 1);

			// Remove the regex from the array
			ArrayUtils.remove(args, 0);

			// Initialize a new Observations object
			Observations obs = new Observations();

			// Loop over the observations
			for (Long obid : obs.getObservationIndex()) {

				// Loop over the variables passed to the method
				for (int varids = 0; varids < args.length; varids++) {

					// Initialize new string with cases matching regex
					// replaced the first time with the replacement string
					String repall =
							Data.getStr(Data.getVarIndex(args[varids]), obid)
									.replaceAll(regex, repstring);

					// Replace the value in the data set with the regular
					// expression
					Data.storeStr(Data.getVarIndex(args[varids]), obid, repall);

				} // End Loop over variables passed to method

			} // End Loop over observations

			// Returns sucess code
			return 0;

		// If any of the tests above are incorrect
		} else {

			// Print error message to the Stata console
			SFIToolkit.errorln("Must pass 3 or more non-empty arguments to method");

			// Return error code
			return 1;

		} // End ELSE Block

	} // End Method declaration

	/***
	 * Stata Interface for java.util.regex.Matcher.groupCount()
	 * @param args String arguments used to construct the pattern, matcher,
	 *                etc...
	 * @return A success or failure indicator
	 */
	public static int groupCountString(String[] args) {

		// Pull out the regular expression to use
		String regex = new StRegExParseArgs().getRegEx(args);

		// Get the string to match
		String toMatch = new StRegExParseArgs().getMatchString(args);

		// Create the matcher object
		StMatcher matched = new StMatcher(regex, toMatch,
				new StRegExParseArgs().getPatternOptions(args),
				new StRegExParseArgs().getMatcherOptions(args));

		Macro.setLocal("groups", String.valueOf(matched.matcher.groupCount()));
		return 0;
	}

	/***
	 * Method to return variables that are generated by splitting the string
	 * based on the provided regex
	 * @param args Passed from the javacall command from Stata
	 * @return A success/failure indicator
	 */
	public static int splitMatch(String[] args) {
		return 0;
	}

	/***
	 * Method to return a "boolean" indicating whether the string values
	 * match the given pattern
	 * @param args Passed from the javacall command from Stata
	 * @return A success/failure indicator
	 */
	public static int matches(String[] args) {

		return 0;
	}

	/***
	 * Stata Interface for java.util.regex.MatchResult.group()
	 * @param args String arguments used to construct the pattern, matcher,
	 *                etc...
	 * @return A success or failure indicator
	 */
	public static int getMatchGroups(String[] args) {

		return 0;
	}

	/***
	 * Stata Interface for java.util.regex.MatchResult.group(int group)
	 * @param args String arguments used to construct the pattern, matcher,
	 *                etc...
	 * @return A success or failure indicator
	 */
	public static int getMatchGroup(String[] args) {

		return 0;
	}






}
