package org.paces.Stata;

import com.stata.sfi.Data;
import org.paces.Stata.MetaData.Meta;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;

/**
 * @author Billy Buchanan
 * @version 0.0.0
 */
public class RegexReplace {

	private Meta meta;
	private Integer oldvarIndex;
	private Integer newvarIndex;
	private RegexOperator regex;
	private Boolean anchor;

	/**
	 * Class Constructor for regular expression replacement operations
	 * @param pattern The string to use as the regular expression pattern
	 * @param replaceWith The string to replace matched patterns with
	 * @param options A list of options for the Pattern class object
	 * @param anchor Boolean indicating whether the Matcher class should use
	 *                  anchoringBounds or not
	 * @param all Boolean indicating whether the class should replaceAll
	 *               (true) or replaceFirst (false)
	 */
	public RegexReplace(String pattern, String replaceWith,
						String[] options, Boolean anchor, Boolean all) {

		this.meta = new Meta(options);
		this.regex = new RegexOperator(pattern, Arrays.asList(options));
		this.anchor = anchor;
		varindices();
		setReplace(replaceWith, all);
	}

	/**
	 * Method used to get the old and new variable indices
	 */
	private void varindices() {
		List<Integer> idx = this.meta.getVarindex();
		this.oldvarIndex = idx.get(0);
		if (idx.size() == 2) this.newvarIndex = idx.get(1);
		else this.newvarIndex = idx.get(0);
	}

	/**
	 * Method called to replace the values
	 * @param replaceWith The string to replace matched values with
	 * @param all Anchoring vs Transparent bounds indicator
	 */
	private void setReplace(String replaceWith, Boolean all) {
		for(Long i : this.meta.getObsindex()) {
			Matcher m = this.regex.setMatch(Data.getStr(this.oldvarIndex, i), this.anchor);
			if (all) Data.storeStr(this.newvarIndex, i, m.replaceAll(replaceWith));
			else Data.storeStr(this.newvarIndex, i, m.replaceFirst(replaceWith));
		}
	}




}
