package org.paces.Stata;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <h1>Class used to parse and initialize a Pattern class object when
 * called from the Java API in Stata.</h1>
 * @author Billy Buchanan
 * @version 20dec2015
 *
 */
public class RegexOperator {

	/***
	 * A Pattern class object (used to store the regular expression).
	 */
	private Pattern pat;

	/**
	 * A pattern options class object.  Used to parse pattern options and
	 * provide the integer value used when compiling the pattern object.
	 */
	private PatternOptions opts = new PatternOptions();

	/***
	 * Method used to create a map object used to return mapping from Stata
	 * options to Pattern compile options in Java
	 * @param regexPattern The regular expression to use for matching
	 * @param options A list of String values corresponding to Pattern
	 *                   .compile() flags.
	 */
	public RegexOperator(String regexPattern, List<String> options) {
		if (!options.isEmpty()) {
			this.opts.setOptions(options);
			this.pat = Pattern.compile(regexPattern, this.opts.getPatternOptions());
		} else {
			this.pat = Pattern.compile(regexPattern);
		}

	}

	/**
	 * Default Matcher class constructor
	 * @param varvalue the string value
	 * @return Method returns a Matcher class object used to perform the
	 * replacement operations.
	 */
	public Matcher setMatch(String varvalue) {
		return setMatch(varvalue, true);
	}

	/**
	 * Method used to construct Matcher object and define the
	 * anchoring/transparent bounds option
	 * @param varvalue The value against which the pattern should search for
	 *                    a match.
	 * @param anchor Boolean indicating whether the matcher should use
	 *                  anchoringBounds (true) or transparentBounds (false)
	 * @return A Matcher class object further used to replace values or
	 * perform subsequent operations.
	 */
	public Matcher setMatch(String varvalue, Boolean anchor) {
		Matcher m = this.pat.matcher(varvalue);
		if (anchor) m.useAnchoringBounds(true);
		else m.useTransparentBounds(true);
		return m;
	}




}
