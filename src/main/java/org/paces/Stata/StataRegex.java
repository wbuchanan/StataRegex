package org.paces.Stata;

import java.util.Arrays;
import java.util.List;

/**
 * @author Billy Buchanan
 * @version 0.0.0
 */
public class StataRegex {

	static DataPrep dataAccessor;

	/**
	 * Method for regular expression replacement
	 * <table summary="Position of method arguments in the string array args">
	 *     <th>
	 *         <td>Position</td>
	 *         <td>Element</td>
	 *     </th>
	 *     <tr>
	 *         <td>0</td>
	 *         <td>pattern</td>
	 *     </tr>
	 *     <tr>
	 *         <td>1</td>
	 *         <td>replace</td>
	 *     </tr>
	 *     <tr>
	 *         <td>2</td>
	 *     	   <td>canon_eq</td>
	 *     </tr>
	 *     <tr>
	 *         <td>3</td>
	 *         <td>case_insensitive</td>
	 *     </tr>
	 *     <tr>
	 *         <td>4</td>
	 *         <td>comments</td>
	 *     </tr>
	 *     <tr>
	 *         <td>5</td>
	 *         <td>dotall</td>
	 *     </tr>
	 *     <tr>
	 *         <td>6</td>
	 *         <td>literal</td>
	 *     </tr>
	 *     <tr>
	 *         <td>7</td>
	 *         <td>multiline</td>
	 *     </tr>
	 *     <tr>
	 *         <td>8</td>
	 *         <td>unicode_case</td>
	 *     </tr>
	 *     <tr>
	 *         <td>9</td>
	 *         <td>unicode_character_case</td>
	 *     </tr>
	 *     <tr>
	 *         <td>10</td>
	 *         <td>unix_lines</td>
	 *     </tr>
	 *     <tr>
	 *         <td>11</td>
	 *         <td>Anchor</td>
	 *     </tr>
	 *     <tr>
	 *         <td>12</td>
	 *         <td>Replace All</td>
	 *     </tr>
	 * </table>
	 * @param args Arguments passed to the replace method.  Used to call
	 *                Matcher.replaceFirst or Matcher.replaceAll.
	 * @return Success/Failure Indicator
	 */
	public static int replace(String[] args) {
		String pattern = args[0];
		String replaceWith = args[1];
		Boolean anchor = Boolean.valueOf(args[11]);
		Boolean replaceAll = Boolean.valueOf(args[12]);
		dataAccessor = new DataPrep();
		List<String> options = Arrays.asList(Arrays.copyOfRange(args, 2, 11));
		new RegexReplace(dataAccessor, pattern, replaceWith, options, anchor,
				replaceAll);
		return 0;
	}

	public static int match(String[] args) {
		String pattern = args[0];
		String replaceWith = args[1];
		Boolean anchor = Boolean.valueOf(args[11]);
		dataAccessor = new DataPrep();
		List<String> options = Arrays.asList(Arrays.copyOfRange(args, 2, 11));
		new RegexMatch(dataAccessor, pattern, options, anchor);
		return 0;
	}



}
