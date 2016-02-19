package org.paces.Stata;

import com.stata.sfi.Data;

import java.util.List;
import java.util.regex.Matcher;

/**
 * @author Billy Buchanan
 * @version 0.0.0
 */
public class RegexReplace {

	private RegexOperator regex;
	private Boolean anchor;
	private DataPrep data;

	/**
	 * Class Constructor for regular expression replacement operations
	 * @param dataAccessor A Data Prep object used to access the data.
	 * @param pattern The string to use as the regular expression pattern
	 * @param replaceWith The string to replace matched patterns with
	 * @param options A list of options for the Pattern class object
	 * @param anchor Boolean indicating whether the Matcher class should use
	 *                  anchoringBounds or not
	 * @param all Boolean indicating whether the class should replaceAll
	 *               (true) or replaceFirst (false)
	 */
	public RegexReplace(DataPrep dataAccessor,
						String pattern, String replaceWith,
						List<String> options, Boolean anchor, Boolean all) {

		this.data = dataAccessor;
		this.regex = new RegexOperator(pattern, options);
		this.anchor = anchor;
		setReplace(replaceWith, all);
	}


	/**
	 * Method called to replace the values
	 * @param replaceWith The string to replace matched values with
	 * @param all Anchoring vs Transparent bounds indicator
	 */
	private void setReplace(String replaceWith, Boolean
			all) {
		for(Long i : data.getObsIndex()) {
			Matcher m = this.regex.setMatch(data.getStringData().get(i), this.anchor);
			if (all) Data.storeStr(data.getReturnIndex(), i, m.replaceAll(replaceWith));
			else Data.storeStr(data.getReturnIndex(), i, m.replaceFirst(replaceWith));
		}
	}




}
