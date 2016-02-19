{smcl}
{* *! version 0.0.0  19FEB2016}{...}
{cmd:help jregex}
{hline}

{title:Title}

{hi:jregex {hline 2}} Java powered regular expression utilities.

{title:Syntax}

{p 4 4 4}{cmd:jregex} {opt subcommand} {var:existing string variable} 
[{newvar:to store altered values}] {ifin}, {cmdab:p:attern(string)} [
{cmdab:can:on} {cmdab:comm:ents} {cmdab:insens:itive} {cmdab:dot:all} 
{cmdab:lit:eral} {cmdab:m:ultiline} {cmdab:unic:ase} {cmdab:unicl:ass} 
{cmdab:unixl:ines} {cmd:noanchor} {cmdab:rep:lace(string)} {cmdab:repf:irst}]

{title:Description}

{p 4 4 4}{cmd:jregex} is a Java-based plugin for regular expressions in Stata.  
Currently, the program only implements regular expression replacement methods.  
{p_end}

{title:Options}
{p 4 4 8}{cmdab:p:attern(string)} the regular expression to use.  Additional 
information regarging meta characters, character classes, and other options can 
be found in the {browse "https://docs.oracle.com/javase/8/docs/api/java/util/regex/Pattern.html": documentation for the Pattern class}.{p_end}
{p 4 4 8}{cmdab:can:on} enables canonical equivalence. {p_end}
{p 4 4 8}{cmdab:insens:itive} enables case-insensitive matching.{p_end}
{p 4 4 8}{cmdab:comm:ents} permits whitespace and comments in pattern.{p_end}
{p 4 4 8}{cmdab:dot:all} enables dotall mode.{p_end}
{p 4 4 8}{cmdab:lit:eral} enables literal parsing of the pattern.{p_end}
{p 4 4 8}{cmdab:m:ultiline} enables multiline mode.{p_end}
{p 4 4 8}{cmdab:unic:ase} enables Unicode-aware case folding.{p_end}
{p 4 4 8}{cmdab:unicl:ass} enables the Unicode version of Predefined character 
classes and POSIX character classes.{p_end}
{p 4 4 8}{cmdab:unixl:ines} enables Unix lines mode.{p_end}
{p 4 4 8}{cmd:noanchor} tells the underlying {browse "http://docs.oracle.com/javase/8/docs/api/java/util/regex/Matcher.html":Matcher class} object to use transparent bounds instead of anchoring bounds.{p_end}
{p 4 4 8}{cmdab:rep:lace(string)} the string used to replace matching values in the string variable.{p_end}
{p 4 4 8}{cmdab:repf:irst} if set will replace only the first match, otherwise all matches will be replaced.{p_end}

{title:Examples}

{p 4 4 4}Create data used by the {browse "http://github.com/wbuchanan/StataJSON":ggeocode} program.{p_end}

{p 8 8 12}input str52 addy{p_end}
{p 8 8 12}"6675,+Old+Canton+RD,+Ridgeland,+MS,+39157" {p_end}
{p 8 8 12}"12313,+33RD+Ave+North,+SEATTLE,+wa,+98125" {p_end}
{p 8 8 12}"310,+Cahir+StreeT,+Providence,+Rhode+Island,+02903" {p_end}
{p 8 8 12}"22,+Oaklawn+Ave,+Cranston,+RI,+02920" {p_end}
{p 8 8 12}"61,+pine+st,+Attleboro,+MA,+02703" {p_end}
{p 8 8 12}"10,+larkspur+R0ad,+Warwick,+ri,+02886" {p_end}
{p 8 8 12}"91,+FaLLon+Ave,+Providence,+RI,+02908" {p_end}
{p 8 8 12}"195,+Arlington+AVE,+Providence,+RI,+02906" {p_end}
{p 8 8 12}"74,+REGENT+aVenuE,+Providence,+RI,+02908" {p_end}
{p 8 8 12}end{p_end}
{p 8 8 12}{p_end}

{p 8 8 12}This will replace the first instance of the '+' character with the 
string passed to the rep argument.{p_end}
{stata jregex replace addy, p(`"\+"') rep(`"_this is a replacement string_"') repf}

{p 8 8 12}Now we can replace the replacement string to recreate the original 
variable.{p_end}
{p 8 8 12}{stata jregex replace addy newaddy, rep(`"\+"') p(`"_this is a replacement string_"')}

{p 8 8 12}You can also use POSIX character classes to replace values, like punctuation marks with a single space{p_end}
{p 8 8 12}{stata jregex replace newaddy, p(`"\p{Punct}"') rep(" ")}{p_end}

{title: Author}{break}
{p 1 1 1} William R. Buchanan, Ph.D. {p_end}
{p 1 1 1} Data Scientist {p_end}
{p 1 1 1} {browse "http://mpls.k12.mn.us":Minneapolis Public Schools} {p_end}
{p 1 1 1} William.Buchanan at mpls [dot] k12 [dot] mn [dot] us {p_end}

