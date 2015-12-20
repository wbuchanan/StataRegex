package org.paces.Stata;

import java.util.regex.Matcher;

/**
 * @author Billy Buchanan
 * @version 0.0.0
 */
public class StataMatcher {

	private Boolean matched;

	/***
	 * An instance of a java.util.regex.Matcher class object
	 */
	public Matcher matcher;

	/***
	 * A member variable used to set the starting index for the search region
	 */
	private Integer startIdx;

	/***
	 * A member variable used to set the ending index for the search region
	 */
	private Integer endIdx;

	/***
	 * A member variable used to set the anchor bounds property of the
	 * Matcher class object
	 */
	private Boolean anchorBounds;

	/***
	 * A member variable used to set the transparent/opaque bounds property
	 * of the Matcher class object
	 */
	private Boolean transparentBounds;

	public StataMatcher(StPattern regex, String[] options) {
	}

	/***
	 * Method to set the match region for the regex to search
	 * @param start Starting index for the region
	 */
	public void setStartIndex(String start) {
		this.startIdx = Integer.valueOf(start);
	}

	/***
	 * Method to set the match region for the regex to search
	 * @param end Ending index for the region
	 */
	public void setEndIndex(String end) {
		this.endIdx = Integer.valueOf(end);
	}

	/***
	 * Method used to turn off using boundary characters (e.g., ^, $, etc...)
	 * for the regex processing
	 * @param args If this string is not empty it turns off the use of
	 *                anchoring bounds
	 */
	public void setBoundaryCharactersOff(String args) {
		if (!args.isEmpty()) this.anchorBounds = false;
	}

	/***
	 * Method used to set using transparent bounds instead of default
	 * opaque bounds.
	 * @param args If this string is not empty it turns off the use of
	 *                opaque bounds
	 */
	public void setTransparentBounds(String args) {
		if (!args.isEmpty()) this.transparentBounds = true;
	}


}
