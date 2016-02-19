package org.paces.Stata;

import com.stata.sfi.SFIToolkit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author Billy Buchanan
 * @version 0.0.0
 */
public class PatternOptions {

	/***
	 * A map object storing the Pattern class object's option flags with
	 * String keys that can be easily referenced from Stata.
	 */
	private Map<String, Integer> optionmap = new HashMap<>();

	/***
	 * Integer object used to store the user provided option flags passed to
	 * the compile method of the Pattern class.  If no options are passed, or
	 * if any of the setter methods are called with null valued options
	 * parameters, the default value of 0 will be populated.
	 */
	private Integer patternOptions = 0;

	/**
	 * Class constructor for pattern options
	 */
	public PatternOptions() {
		optionmap.put("canon_eq", Pattern.CANON_EQ);
		optionmap.put("case_insensitive", Pattern.CASE_INSENSITIVE);
		optionmap.put("comments", Pattern.COMMENTS);
		optionmap.put("dotall", Pattern.DOTALL);
		optionmap.put("literal", Pattern.LITERAL);
		optionmap.put("multiline", Pattern.MULTILINE);
		optionmap.put("unicode_case", Pattern.UNICODE_CASE);
		optionmap.put("unicode_character_class", Pattern.UNICODE_CHARACTER_CLASS);
		optionmap.put("unix_lines", Pattern.UNIX_LINES);
	}

	/**
	 * Method used to set Pattern compile options
	 * @param options A list of string objects containing values that
	 *                   translate to compilation options for Pattern class
	 *                   objects
	 * @return The PatternOptions object to allow fluent programming
	 * interface for the class.
	 */
	public PatternOptions setOptions(List<String> options) {
		if (options.size() >= 1 && checkOptions(options)) {
			for(String opt : options) {
				SFIToolkit.displayln("Pattern options: " + opt);
				if (!opt.isEmpty()) {
					this.patternOptions = patternOptions | optionmap.get(opt);
				} else {
					this.patternOptions = patternOptions | 0;
				}
			}
		} else {
			this.patternOptions = 0;
		}
		return this;
	}

	/**
	 * Checks for all empty elements of the list object
	 * @param options List of string options
	 * @return A boolean indicating whether all the elements are empty or not
	 */
	private Boolean checkOptions(List<String> options) {
		Boolean validArg = false;
		for (String i : options) {
			if (!i.isEmpty()) validArg = true;
		}
		return validArg;
	}

	/**
	 * Method to access the pattern compilation options
	 * @return An integer value that represents the combination of user
	 * provided compilation options.
	 */
	public Integer getPatternOptions() {
		return patternOptions;
	}


}
