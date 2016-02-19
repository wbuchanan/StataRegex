package org.paces.Stata;

import com.stata.sfi.Data;
import org.paces.Stata.MetaData.Meta;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

/**
 * @author Billy Buchanan
 * @version 0.0.0
 */
public class DataPrep {

	/**
	 * Object containing metadata
	 */
	private Meta metadata;

	/**
	 * Private member containing the variable index
	 */
	private List<Integer> varidx;

	/**
	 * Private member containing the observation index
	 */
	private List<Long> obidx;

	/**
	 * Member indicating if string data needs to be concatenated before
	 * operations
	 */
	private Boolean combineVars;

	/**
	 * Member contains the variable index where values should be returned
	 */
	private Integer returnIndex;

	/**
	 * String used to define concatenation string placed between variables
	 * when multiple variables are defined.
	 */
	private String sep = " ";

	/**
	 * Class constructor for data prep for regex methods
	 */
	public DataPrep() {
		String[] blank = new String[0];
		this.metadata = new Meta(blank);
		this.varidx = this.metadata.getVarindex();
		this.obidx = this.metadata.getObsindex();
		this.combineVars = this.varidx.size() > 2;
		this.returnIndex = this.varidx.get(this.varidx.size() - 1);
	}

	/**
	 * Method to access the string data whether or not it needs to be combined
	 * @return A map of long keys (observation indices) and String values
	 * (the single datum or combined data if multiple vars are passed)
	 */
	public Map<Long, String> getStringData() {
		Map<Long, String> returnValue = new HashMap<>();
		if (combineVars) {
			for(Long i : this.obidx) {
				StringJoiner comboString = new StringJoiner(this.sep);
				for(Integer j = 0; j < this.varidx.size() - 1; j++) {
					comboString.add(Data.getStr(this.varidx.get(j), i));
				}
				returnValue.put(i, comboString.toString());
			}
		} else {
			for(Long i : this.obidx) {
				returnValue.put(i, Data.getStr(this.varidx.get(0), i));
			}
		}
		return returnValue;
	}

	/**
	 * Method to return the variable index value used when populating
	 * returned values to the client
	 * @return An integer value used to identify the position of the variable
	 * where returned results will be stored.
	 */
	public Integer getReturnIndex() {
		return this.returnIndex;
	}

	public List<Long> getObsIndex() {
		return this.obidx;
	}

}
