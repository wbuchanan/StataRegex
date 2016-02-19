package org.paces.Stata;

import com.stata.sfi.Data;
import com.stata.sfi.ValueLabel;

import java.util.List;
import java.util.regex.Matcher;

/**
 * @author Billy Buchanan
 * @version 0.0.0
 */
public class RegexMatch {

	private RegexOperator regex;
	private Boolean anchor;
	private DataPrep data;

	/**
	 * Class Constructor for regular expression replacement operations
	 * @param dataAccessor A Data Prep object used to access the data.
	 * @param pattern The string to use as the regular expression pattern
	 * @param options A list of options for the Pattern class object
	 * @param anchor Boolean indicating whether the Matcher class should use
	 *                  anchoringBounds or not
	 */
	public RegexMatch(DataPrep dataAccessor, String pattern,
						List<String> options, Boolean anchor) {

		this.data = dataAccessor;
		this.regex = new RegexOperator(pattern, options);
		this.anchor = anchor;
		addValueLabel();
		getMatch();
	}

	private void addValueLabel() {
		String varnm = Data.getVarName(data.getReturnIndex());
		ValueLabel.createLabel(varnm);
		ValueLabel.setLabelValue(varnm, 0, "Not a Match");
		ValueLabel.setLabelValue(varnm, 1, "Is a Match");
		ValueLabel.setVarValueLabel(data.getReturnIndex(), varnm);
	}


	/**
	 * Method called to replace the values
	 */
	private void getMatch() {
		for(Long i : data.getObsIndex()) {
			Matcher m = this.regex.setMatch(data.getStringData().get(i), this.anchor);
			if (m.matches()) Data.storeNum(data.getReturnIndex(), i, 1);
			else Data.storeNum(data.getReturnIndex(), i, 0);
		}
	}





}
