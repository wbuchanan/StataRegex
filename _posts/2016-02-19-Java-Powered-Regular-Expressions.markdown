---
layout: post
title:  "Java Powered Regular Expressions"
date:   2016-02-19 06:30:00
categories: post
---

Since 2005, people have had questions about regular expressions and their usage in Stata; [a FAQ on regular expressions from Stata](http://www.stata.com/support/faqs/data-management/regular-expressions/) confirms how long the use of regular expressions in Stata has raised questions.  With the recent implementation of [Unicode support](http://www.stata.com/new-in-stata/unicode/) in Stata, regular expressions got a bit of a facelift, with several new functions beginning with the character 'u' being added.  One challenge with this is the difference in API between the ASCII and Unicode based regular expression functions and the other is the lack of support for POSIX standards that provide tools like character class metacharacters and metacharacters that allow users to specify conditions (e.g., {2,3} to indicate the match must happen twice but not more than three times, etc...).  With [jregex](https://github.com/wbuchanan/StataRegex) this is about to change.  

Currently the package only includes regular expression replacement functions, but the program will provide a single API to multiple regular expression functions using subcommands (e.g., `jregex replace ...`).  Additionally, this program provides access to setting all of the compilation flag options listed in the [Pattern API](https://docs.oracle.com/javase/8/docs/api/java/util/regex/Pattern.html) as well as options to set several of the options available in the [Matcher API](https://docs.oracle.com/javase/8/docs/api/java/util/regex/Matcher.html).  

See the about page for some basic examples of how to use `jregex` in your own work.
