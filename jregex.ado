
// Drops the program from memory if already loaded
cap prog drop jregex

// Defines the program
prog def jregex

	// Defines the version of Stata required for the program
    version 14

	// Defines the syntax with which the program is called
    syntax anything(name=subcmd id="Sub-Expression varlist" equalok) [if]  ///
	[in] , Pattern(string) [ CANon COMMents INSENSitive DOTall LITeral  	 ///   
	Multiline UNICase UNICLass UNIXLines noANCHOR REPlace(string) REPFirst ]
	
	// If set enables canonical equivalence
	if `"`canon'"' != "" loc canon "canon_eq"
	else loc canon `""""'
	
	// If set enables case-insensitive matching
	if `"`insensitive'"' != "" loc insensitive "case_insensitive"
	else loc insensitive `""""'
	
	// If set permits whitespace and comments in the pattern
	if `"`comments'"' != "" loc comments "comments"
	else loc comments `""""'
	
	// If set enables dotall mode
	if `"`dotall'"' != "" loc dotall "dotall"
	else loc dotall `""""'
	
	// If set enables literal parsing of the pattern
	if `"`literal'"' != "" loc literal "literal"
	else loc literal `""""'
	
	// If set enables multiline mode
	if `"`multiline'"' != "" loc multiline "multiline"
	else loc multiline `""""'
	
	// If set Enables Unicode-aware case folding.
	if `"`unicase'"' != "" loc unicase "unicode_case"
	else loc unicase `""""'
	
	// If set Enables the Unicode version of Predefined character classes and 
	// POSIX character classes.
	if `"`uniclass'"' != "" loc uniclass "unicode_character_class"
	else loc uniclass `""""'

	// If set Enables Unix lines mode.
	if `"`unixlines'"' != "" loc unixlines "unix_lines"
	else loc unixlines `""""'

	// If set will force the regular expression engine to use anchoring bounds
	if `"`anchor'"' != "noanchor" loc anchor "true"
	
	// Otherwise will use transparent bounds
	else loc anchor "false"
	
	// If repall option specified will replace all instances of the string
	if `"`repfirst'"' != "" loc repall "false"
	
	// Otherwise will only replace the first instance of the string
	else loc repall "true"

	// Get the sub command distinct from the variable list
	gettoken jregexcmd varlist : subcmd
		
	// Test if the sub command is replace
	if `"`jregexcmd'"' == "replace" {

	    loc wrds = `: word count `varlist''

		if `wrds' >= 2 cap: g `: word `wrds' of `varlist'' = ""
	
		// Calls the Java class/method to use for regular expression replacement
		javacall org.paces.Stata.StataRegex replace `varlist' `if' `in', 	 ///   
		args("`pattern'" "`replace'" `canon' `insensitive' `comments' 		 ///   
		`dotall' `literal' `multiline' `unicase' `uniclass' `unixlines' 	 ///   
		`anchor' `repall')
		
	} // End IF Block for regular expression replace

    else if `"`jregexcmd'"' == "match" {

	    loc wrds = `: word count `varlist''

		if `wrds' >= 2 cap: g byte `: word `wrds' of `varlist'' = .

		// Calls the Java class/method to use for regular expression replacement
		javacall org.paces.Stata.StataRegex match `varlist' `if' `in', 	 ///
		args("`pattern'" "`replace'" `canon' `insensitive' `comments' 		 ///
		`dotall' `literal' `multiline' `unicase' `uniclass' `unixlines' 	 ///
		`anchor' `repall')

    }

// End of program	
end

	
