---
layout: page
title: about
permalink: /about/
---

# Examples

## Example 1. Replace string values in place

```
clear 

// Input a dataset to use for examples
input str55 addy
"6675,+Old+Canton+RD,+Ridgeland,+MS,+39157"
"12313,+33RD+Ave+Nort,+SEATTLE,+wa,+98125"
"310,+Cahir+StreeT,+Providence,+Rhode+Island,+02903"
"22,+Oaklawn+Ave,+Cranston,+RI,+02920"
"61,+pine+st,+Attleboro,+MA,+02703"
"10,+larkspur+R0ad,+Warwick,+ri,+02886"
"91,+FaLLon+Ave,+Providence,+RI,+02908"
"195,+Arlington+AVE,+Providence,+RI,+02906"
"74,+REGENT+aVenuE,+Providence,+RI,+02908"
end

// Replace the first instance of the character `+` with a
// Long string indicating something was replaced
// This would replace the values inplace (e.g., overwrite the values in the addy variable where necessary)
jregex replace addy, p(`"\+"') rep(`"_this is a replacement string_"') repf 

```

This results in the strings in the variable addy becoming:

```
list

     +---------------------------------------------------------------------------------+
     |                                                                            addy |
     |---------------------------------------------------------------------------------|
  1. |          6675,_this is a replacement string_Old+Canton+RD,+Ridgeland,+MS,+39157 |
  2. |           12313,_this is a replacement string_33RD+Ave+Nort,+SEATTLE,+wa,+98125 |
  3. | 310,_this is a replacement string_Cahir+StreeT,+Providence,+Rhode+Island,+02903 |
  4. |               22,_this is a replacement string_Oaklawn+Ave,+Cranston,+RI,+02920 |
  5. |                  61,_this is a replacement string_pine+st,+Attleboro,+MA,+02703 |
     |---------------------------------------------------------------------------------|
  6. |              10,_this is a replacement string_larkspur+R0ad,+Warwick,+ri,+02886 |
  7. |              91,_this is a replacement string_FaLLon+Ave,+Providence,+RI,+02908 |
  8. |          195,_this is a replacement string_Arlington+AVE,+Providence,+RI,+02906 |
  9. |           74,_this is a replacement string_REGENT+aVenuE,+Providence,+RI,+02908 |
     +---------------------------------------------------------------------------------+

```

## Example 2. Replace string values into a new variable

``` 
// Undo the previous changes and put the resulting change in a new variable named newaddy
jregex replace addy newaddy, rep(`"\+"') p(`"_this is a replacement string_"')

// Now we can look at the new variable that was just created
list newaddy

     +----------------------------------------------------+
     |                                            newaddy |
     |----------------------------------------------------|
  1. |          6675,+Old+Canton+RD,+Ridgeland,+MS,+39157 |
  2. |           12313,+33RD+Ave+Nort,+SEATTLE,+wa,+98125 |
  3. | 310,+Cahir+StreeT,+Providence,+Rhode+Island,+02903 |
  4. |               22,+Oaklawn+Ave,+Cranston,+RI,+02920 |
  5. |                  61,+pine+st,+Attleboro,+MA,+02703 |
     |----------------------------------------------------|
  6. |              10,+larkspur+R0ad,+Warwick,+ri,+02886 |
  7. |              91,+FaLLon+Ave,+Providence,+RI,+02908 |
  8. |          195,+Arlington+AVE,+Providence,+RI,+02906 |
  9. |           74,+REGENT+aVenuE,+Providence,+RI,+02908 |
     +----------------------------------------------------+
```

## Example 3. Using POSIX character classes

```
// Replaces each punctuation class character with a single space
jregex replace newaddy, p(`"\p{Punct}"') rep(" ")

// And this is what the results look like:
list newaddy

     +----------------------------------------------------+
     |                                            newaddy |
     |----------------------------------------------------|
  1. |          6675  Old Canton RD  Ridgeland  MS  39157 |
  2. |           12313  33RD Ave Nort  SEATTLE  wa  98125 |
  3. | 310  Cahir StreeT  Providence  Rhode Island  02903 |
  4. |               22  Oaklawn Ave  Cranston  RI  02920 |
  5. |                  61  pine st  Attleboro  MA  02703 |
     |----------------------------------------------------|
  6. |              10  larkspur R0ad  Warwick  ri  02886 |
  7. |              91  FaLLon Ave  Providence  RI  02908 |
  8. |          195  Arlington AVE  Providence  RI  02906 |
  9. |           74  REGENT aVenuE  Providence  RI  02908 |
     +----------------------------------------------------+
```

