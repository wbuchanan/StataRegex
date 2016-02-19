[![Project Status: WIP - Initial development is in progress, but there has not yet been a stable, usable release suitable for the public.](http://www.repostatus.org/badges/latest/wip.svg)](http://www.repostatus.org/#wip)

# Java-based Regular Expression Utilities for Stata
Still very early in development, but will provide methods to use the regular expression capabilities in Java.  Additionally, this will also provide methods of using regular expression methods across `varlists`, `macros`, and/or single variables.  This should provide users with significantly improved flexibility with regards to how regular expressions can be used in their work flows.  One of the more important features this brings to Stata is the use of traditional metacharacters used in regular expressions (e.g., {2, 5} curly brackets to specify the number of times a pattern must match as well as the limit of the number of matches, \d [a digit], \D [non-digits], etc...).  For additional information regarding the available meta characters and their usage, users are referred to the [Pattern class javadocs](https://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html).  

## Additional information
This is a single planned component of a slightly larger body of work for munging and working with string data in Stata.  Additional capabilities will include different string distance/fuzzy matching algorithms based on the metaphone, double methaphone, and other phonetics-based string encoding algorithms.




