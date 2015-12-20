package org.paces.Stata;

import com.stata.sfi.Data;
import com.stata.sfi.Macro;
import com.stata.sfi.SFIToolkit;
import org.apache.commons.lang3.ArrayUtils;
import org.paces.Stata.MetaData.Observations;

import java.util.regex.Matcher;

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
		if (args.length == 3 && !args[0].isEmpty() && !args[1].isEmpty() &&
				!args[2].isEmpty()) {
			Macro.setLocal("repfirst", args[0].replaceFirst(args[1], args[2]));
			return 0;
		} else {
			SFIToolkit.errorln("Must pass 3 non-empty arguments to method");
			return 1;
		}
	}

	/***
	 * Stata Interface for java.util.regex.Matcher.replaceFirst(String
	 * replacement)
	 * @param args String arguments used to construct the pattern, matcher,
	 *                etc...
	 * @return A success or failure indicator
	 */
	public static int replaceFirstVar(String[] args) {
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
		if (args.length == 2 && !args[0].isEmpty() && !args[1].isEmpty()) {
			Macro.setLocal("repfirst", args[0].replaceAll(args[1], args[2]));
			return 0;
		} else {
			SFIToolkit.errorln("Must pass two non-empty arguments to this method");
			return 1;
		}
	}

	/***
	 * Stata Interface for java.util.regex.Matcher.replaceAll(String
	 * replacement)
	 * @param args String arguments used to construct the pattern, matcher,
	 *                etc...
	 * @return A success or failure indicator
	 */
	public static int replaceAllVar(String[] args) {
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
		StPattern regpat = new StPattern();
		String regex = args[0];
		String toMatch = args[1];
		ArrayUtils.remove(args, 1);
		ArrayUtils.remove(args, 0);
		regpat.setRegEx(regex, args);
		Matcher matched = regpat.getPattern().matcher(toMatch);
		Macro.setLocal("groups", String.valueOf(matched.groupCount()));
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
